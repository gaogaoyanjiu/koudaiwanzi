package com.example.administrator.koudaiwanzi.shopcartwo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.MainActivity;
import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.details.DetailsActivity;
import com.example.administrator.koudaiwanzi.dialog.CustomProgressDialog;
import com.example.administrator.koudaiwanzi.refresh.swipemenulistview.SwipeMenu;
import com.example.administrator.koudaiwanzi.refresh.swipemenulistview.SwipeMenuCreator;
import com.example.administrator.koudaiwanzi.refresh.swipemenulistview.SwipeMenuItem;
import com.example.administrator.koudaiwanzi.refresh.xlistview.XListViewSwipe;
import com.example.administrator.koudaiwanzi.shopcar.MergeActivity;
import com.example.administrator.koudaiwanzi.shopcar.OrderActivity;
import com.example.administrator.koudaiwanzi.shopcar.ShopCarAdapter;
import com.example.administrator.koudaiwanzi.shopcar.ShopCarBean;
import com.example.administrator.koudaiwanzi.shopcar.ShopCarFragment;
import com.example.administrator.koudaiwanzi.shopcar.ShopCarGridViewAdapter;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Administrator on 2016/9/20.
 */

@ContentView(R.layout.activity_shop)
public class ShopActivity extends AppCompatActivity {
    private String UID;
    private ShopCarAdapter adapter;
    private String url1 = "Items.svc/shopcart/";
    private String url;
    private String urlUse = MyUrl.url;
    private Handler mHandler;
    private Handler handler8;
    private CheckBox ck_all;
    private int a = 0;
    private ShopCarGridViewAdapter gridViewAdapter;
    //感兴趣商品的gridview
    private GridView gridView;
    //绑定去凑单的ID
    @ViewInject(R.id.tv_sc_cou)
    private TextView tv_sc_cou;

    //购物车为空时显示
    @ViewInject(R.id.shopping_car_textView)
    private ImageView shopping_car_textView;

    //绑定编辑按钮的ID
    @ViewInject(R.id.tv_sc_bj)
    private TextView tv_bj;
    //绑定完成按钮的ID
    @ViewInject(R.id.tv_sc_wc)
    private TextView tv_wc;

    //绑定下方的两种布局
    @ViewInject(R.id.rl_sc_1)
    private RelativeLayout rl1;
    @ViewInject(R.id.rl_sc_2)
    private RelativeLayout rl2;

    //绑定编辑样式下的全选checkBox
    @ViewInject(R.id.shopping_car_checkbox1)
    private CheckBox ck_delete;
    //总价绑定ID
    @ViewInject(R.id.shopping_car_price)
    private TextView tv_all;

    //绑定listView
    @ViewInject(R.id.shop_car_listview)
    private XListViewSwipe listView;

    //结算的数量
    @ViewInject(R.id.balance)
    private TextView tv_balance;

    //优惠的价格
    @ViewInject(R.id.tv_favourable)
    private TextView tv_favourable;

    @ViewInject(R.id.sp_rl_store)
    private RelativeLayout rl_store;

//    @ViewInject(R.id.rl_sc_safe)
//    private RelativeLayout rl_safe;

    @ViewInject(R.id.tv_sc_title)
    private TextView tv_title;

    @ViewInject(R.id.tv_sc_title1)
    private RelativeLayout tv_title1;

    //loadDateThreah()线程类为：
    class loadDateThreah implements Runnable {
        @Override
        public void run() {
            //    ....这里是联网下载数据，下载完成后执行下列的方法，handlder会调用前面覆写的handleMessage方法，在那里关闭加载提示框...
            SharedPreferences share = getSharedPreferences("logIn", Context.MODE_PRIVATE);
            UID = share.getString("login", "");
            url = urlUse + url1 + UID;
            Log.d("ghjghfhf", url);
            RequestParams params = new RequestParams(url);
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String s) {
                    Gson gson = new Gson();
                    final ShopCarBean bean = gson.fromJson(s, ShopCarBean.class);
                    adapter = new ShopCarAdapter(ShopActivity.this, bean.getSHOPITEM(), handler);
                    if (bean.getSELECTSTUTE() == 1) {
                        ck_all.setChecked(true);
                    } else {
                        ck_all.setChecked(false);
                    }
                    //list view的设置
                    listView.setPullLoadEnable(true);
                    listView.setAdapter(adapter);
                    tv_all.setText("" + bean.getTOTAL());
                    tv_balance.setText("结算(" + bean.getNUM() + ")");
                    a = bean.getNUM();
                    tv_favourable.setText("已优惠" + bean.getPREFERENTIALPRICE() + "元");
                    gridViewAdapter = new ShopCarGridViewAdapter(ShopActivity.this, bean.getLIKEITEMS(), R.layout.item_shop_car_gridview);
                    gridView.setAdapter(gridViewAdapter);
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(ShopActivity.this, DetailsActivity.class);
                            intent.putExtra("key", bean.getLIKEITEMS().get(position).getDetailUrl());
                            startActivity(intent);
                            finish();

                        }
                    });
                    handler8.sendEmptyMessage(0);

                }

                @Override
                public void onError(Throwable throwable, boolean b) {
                    handler8.sendEmptyMessage(0);
                }

                @Override
                public void onCancelled(CancelledException e) {

                }

                @Override
                public void onFinished() {

                }
            });


        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        //添加监听
        listView.setXListViewListener(new MyListener());
        //handlerc初始化
        mHandler = new Handler();
        View view_foot = LayoutInflater.from(ShopActivity.this).inflate(R.layout.foot_shop, null);
        gridView = (GridView) view_foot.findViewById(R.id.gv_shop);
        listView.addFooterView(view_foot);
        SharedPreferences share = getSharedPreferences("logIn", Context.MODE_PRIVATE);
        UID = share.getString("login", "");
        url = urlUse + url1 + UID;
        ck_all = (CheckBox) findViewById(R.id.shopping_car_checkbox);
        //点击编辑和完成按钮相互切换布局
        tv_bj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl1.setVisibility(View.VISIBLE);
                rl2.setVisibility(View.GONE);
                tv_bj.setVisibility(View.GONE);
                tv_wc.setVisibility(View.VISIBLE);

            }
        });

        tv_wc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl1.setVisibility(View.GONE);
                rl2.setVisibility(View.VISIBLE);
                tv_bj.setVisibility(View.VISIBLE);
                tv_wc.setVisibility(View.GONE);
            }
        });

        final CustomProgressDialog dialog = new CustomProgressDialog(ShopActivity.this, R.style.CommProgressDialog, "加载中...", R.anim.frame2);
//        dialog.setProgressStyle(R.style.CommProgressDialog);
        dialog.show();
        Thread thread = new Thread(new ShopActivity.loadDateThreah());
        thread.start();
        handler8 = new Handler() {
            public void handleMessage(Message msg) {
                dialog.cancel();
            }
        };


        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                final ShopCarBean bean = gson.fromJson(s, ShopCarBean.class);

                if (bean.getSHOPITEM().size() == 0) {
                    tv_sc_cou.setVisibility(View.GONE);
                    shopping_car_textView.setVisibility(View.VISIBLE);
                    rl_store.setVisibility(View.GONE);
//                    rl_safe.setVisibility(View.GONE);
                    tv_bj.setVisibility(View.GONE);
                    rl2.setVisibility(View.GONE);
                    listView.setVisibility(View.GONE);
                    tv_title.setVisibility(View.VISIBLE);
                    tv_title1.setVisibility(View.VISIBLE);

                } else {
                    tv_sc_cou.setVisibility(View.VISIBLE);
                    shopping_car_textView.setVisibility(View.GONE);
                    rl_store.setVisibility(View.VISIBLE);
//                    rl_safe.setVisibility(View.VISIBLE);
                    tv_bj.setVisibility(View.VISIBLE);
                    rl2.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.VISIBLE);
                    tv_title.setVisibility(View.GONE);
                    tv_title1.setVisibility(View.GONE);
                }


                tv_title1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ShopActivity.this, MainActivity.class);
                        intent.putExtra("class","5");
                        startActivity(intent);
                    }
                });

                tv_balance.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (a == 0) {
                            Toast.makeText(ShopActivity.this, "请选择商品", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(ShopActivity.this,OrderActivity.class);
                            startActivity(intent);

                            String order = bean.getDetailUrl();
                            SharedPreferences share = getSharedPreferences("Order", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = share.edit();
                            editor.putString("order", order);
                            editor.apply();
                            finish();
                        }
                    }
                });

                //加入横滑的图片文字等
                SwipeMenuCreator creator = new SwipeMenuCreator() {

                    @Override
                    public void create(SwipeMenu menu) {
                        // create "delete" item
                        SwipeMenuItem deleteItem = new SwipeMenuItem(
                                ShopActivity.this);
                        // set item background
                        deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                                0x3F, 0x25)));
                        // set item width
                        deleteItem.setWidth(dp2px(90));
                        // set a icon
                        deleteItem.setIcon(R.mipmap.ic_delete);
                        menu.addMenuItem(deleteItem);
                    }
                };

                //listView加入creator
                listView.setMenuCreator(creator);


                //侧滑出来的菜单加入功能  这里就加了一个删除
                listView.setOnMenuItemClickListener(new XListViewSwipe.OnMenuItemClickXlistListener() {
                    @Override
                    public void onMenuItemClick(final int position, SwipeMenu menu, int index) {
                        switch (index) {
                            case 0:
                                RequestParams params = new RequestParams(urlUse + bean.getSHOPITEM().get(position).getDELURL());
                                x.http().get(params, new CommonCallback<String>() {
                                    @Override
                                    public void onSuccess(String s) {
                                        Gson gson = new Gson();
                                        ShopCarBean bean = gson.fromJson(s, ShopCarBean.class);
                                        adapter.del(position);

                                        listView.setAdapter(adapter);
                                        tv_all.setText("" + bean.getTOTAL());
                                        tv_balance.setText("结算(" + bean.getNUM() + ")");
                                        a = bean.getNUM();
                                        tv_favourable.setText("已优惠" + bean.getPREFERENTIALPRICE() + "元");
                                        if (bean.getSHOPITEM().size() == 0) {
                                            tv_sc_cou.setVisibility(View.GONE);
                                            shopping_car_textView.setVisibility(View.VISIBLE);
                                        } else {
                                            tv_sc_cou.setVisibility(View.VISIBLE);
                                            shopping_car_textView.setVisibility(View.GONE);
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable throwable, boolean b) {

                                    }

                                    @Override
                                    public void onCancelled(CancelledException e) {

                                    }

                                    @Override
                                    public void onFinished() {

                                    }
                                });

                                break;

                        }
                    }
                });


                ck_all.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final CustomProgressDialog dialog = new CustomProgressDialog(ShopActivity.this, R.style.CommProgressDialog, "加载中...", R.anim.frame2);
                        dialog.show();
                        if (ck_all.isChecked()) {
//                            String all = "Items.svc//selectall//d17ada19-b1f0-4003-b857-f1e671f6a0f0//0";
                            String all = "Items.svc//selectall//";
                            String allUrl = urlUse + all + UID + "//0";
                            RequestParams params = new RequestParams(allUrl);
                            x.http().get(params, new CommonCallback<String>() {
                                @Override
                                public void onSuccess(String s) {

                                    handler8 = new Handler() {
                                        public void handleMessage(Message msg) {

                                            dialog.cancel();
                                        }
                                    };
                                    Gson gson = new Gson();
                                    final ShopCarBean bean = gson.fromJson(s, ShopCarBean.class);
                                    adapter = new ShopCarAdapter(ShopActivity.this, bean.getSHOPITEM(), handler);
                                    if (bean.getSELECTSTUTE() == 1) {
                                        ck_all.setChecked(true);
                                    } else {
                                        ck_all.setChecked(false);
                                    }
                                    //list view的设置
                                    listView.setPullLoadEnable(true);
                                    listView.setAdapter(adapter);
                                    tv_all.setText("" + bean.getTOTAL());
                                    tv_balance.setText("结算(" + bean.getNUM() + ")");
                                    a = bean.getNUM();
                                    tv_favourable.setText("已优惠" + bean.getPREFERENTIALPRICE() + "元");
                                    handler8.sendEmptyMessage(0);

                                }

                                @Override
                                public void onError(Throwable throwable, boolean b) {

                                }

                                @Override
                                public void onCancelled(CancelledException e) {

                                }

                                @Override
                                public void onFinished() {

                                }
                            });
                        } else {
//                            String all = "Items.svc//selectall//d17ada19-b1f0-4003-b857-f1e671f6a0f0//1";
                            String all = "Items.svc//selectall//";
                            String allUrl = urlUse + all + UID + "//1";
                            RequestParams params = new RequestParams(allUrl);
                            x.http().get(params, new CommonCallback<String>() {
                                @Override
                                public void onSuccess(String s) {
                                    handler8 = new Handler() {
                                        public void handleMessage(Message msg) {

                                            dialog.cancel();
                                        }
                                    };
                                    Gson gson = new Gson();
                                    final ShopCarBean bean = gson.fromJson(s, ShopCarBean.class);
                                    adapter = new ShopCarAdapter(ShopActivity.this, bean.getSHOPITEM(), handler);
                                    if (bean.getSELECTSTUTE() == 1) {
                                        ck_all.setChecked(true);
                                    } else {
                                        ck_all.setChecked(false);
                                    }
                                    //list view的设置
                                    listView.setPullLoadEnable(true);
                                    listView.setAdapter(adapter);
                                    tv_all.setText("" + bean.getTOTAL());
                                    tv_balance.setText("结算(" + bean.getNUM() + ")");
                                    a = bean.getNUM();
                                    tv_favourable.setText("已优惠" + bean.getPREFERENTIALPRICE() + "元");
                                    handler8.sendEmptyMessage(0);
                                }

                                @Override
                                public void onError(Throwable throwable, boolean b) {

                                }

                                @Override
                                public void onCancelled(CancelledException e) {

                                }

                                @Override
                                public void onFinished() {

                                }
                            });
                        }


                    }
                });
                ck_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final CustomProgressDialog dialog = new CustomProgressDialog(ShopActivity.this, R.style.CommProgressDialog, "加载中...", R.anim.frame2);
                        dialog.show();
                        if (ck_delete.isChecked()) {
//                            String all = "Items.svc//selectall//d17ada19-b1f0-4003-b857-f1e671f6a0f0//0";
                            String all = "Items.svc//selectall//";
                            String allUrl = urlUse + all + UID + "//0";
                            RequestParams params = new RequestParams(allUrl);
                            x.http().get(params, new CommonCallback<String>() {
                                @Override
                                public void onSuccess(String s) {

                                    handler8 = new Handler() {
                                        public void handleMessage(Message msg) {

                                            dialog.cancel();
                                        }
                                    };
                                    Gson gson = new Gson();
                                    final ShopCarBean bean = gson.fromJson(s, ShopCarBean.class);
                                    adapter = new ShopCarAdapter(ShopActivity.this, bean.getSHOPITEM(), handler);
                                    if (bean.getSELECTSTUTE() == 1) {
                                        ck_all.setChecked(true);
                                    } else {
                                        ck_all.setChecked(false);
                                    }
                                    //list view的设置
                                    listView.setPullLoadEnable(true);
                                    listView.setAdapter(adapter);
                                    tv_all.setText("" + bean.getTOTAL());
                                    tv_balance.setText("结算(" + bean.getNUM() + ")");
                                    a = bean.getNUM();
                                    tv_favourable.setText("已优惠" + bean.getPREFERENTIALPRICE() + "元");
                                    handler8.sendEmptyMessage(0);

                                }

                                @Override
                                public void onError(Throwable throwable, boolean b) {

                                }

                                @Override
                                public void onCancelled(CancelledException e) {

                                }

                                @Override
                                public void onFinished() {

                                }
                            });
                        } else {
//                            String all = "Items.svc//selectall//d17ada19-b1f0-4003-b857-f1e671f6a0f0//1";
                            String all = "Items.svc//selectall//";
                            String allUrl = urlUse + all + UID + "//1";
                            RequestParams params = new RequestParams(allUrl);
                            x.http().get(params, new CommonCallback<String>() {
                                @Override
                                public void onSuccess(String s) {
                                    handler8 = new Handler() {
                                        public void handleMessage(Message msg) {

                                            dialog.cancel();
                                        }
                                    };
                                    Gson gson = new Gson();
                                    final ShopCarBean bean = gson.fromJson(s, ShopCarBean.class);
                                    adapter = new ShopCarAdapter(ShopActivity.this, bean.getSHOPITEM(), handler);
                                    if (bean.getSELECTSTUTE() == 1) {
                                        ck_all.setChecked(true);
                                    } else {
                                        ck_all.setChecked(false);
                                    }
                                    //list view的设置
                                    listView.setPullLoadEnable(true);
                                    listView.setAdapter(adapter);
                                    tv_all.setText("" + bean.getTOTAL());
                                    tv_balance.setText("结算(" + bean.getNUM() + ")");
                                    a = bean.getNUM();
                                    tv_favourable.setText("已优惠" + bean.getPREFERENTIALPRICE() + "元");
                                    handler8.sendEmptyMessage(0);
                                }

                                @Override
                                public void onError(Throwable throwable, boolean b) {

                                }

                                @Override
                                public void onCancelled(CancelledException e) {

                                }

                                @Override
                                public void onFinished() {

                                }
                            });
                        }

                    }
                });

            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });


    }

    //接收改变价格的消息
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 10) {    //更改选中商品的总价格
                Bundle b = new Bundle();
                b = (Bundle) msg.obj;
                String add = b.getString("addUrl");
                String addUrl = urlUse + add;
                final CustomProgressDialog dialog = new CustomProgressDialog(ShopActivity.this, R.style.CommProgressDialog, "加载中...", R.anim.frame2);
                dialog.show();
                Log.d("addUrl", add);
                RequestParams params = new RequestParams(addUrl);
                x.http().get(params, new org.xutils.common.Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String s) {

                        handler8 = new Handler() {
                            public void handleMessage(Message msg) {

                                dialog.cancel();
                            }
                        };
                        Gson gson = new Gson();
                        final ShopCarBean bean = gson.fromJson(s, ShopCarBean.class);
                        adapter = new ShopCarAdapter(ShopActivity.this, bean.getSHOPITEM(), handler);
                        if (bean.getSELECTSTUTE() == 1) {
                            ck_all.setChecked(true);
                        } else {
                            ck_all.setChecked(false);
                        }
                        //list view的设置
                        listView.setPullLoadEnable(true);
                        listView.setAdapter(adapter);
                        tv_all.setText("" + bean.getTOTAL());
                        tv_balance.setText("结算(" + bean.getNUM() + ")");
                        a = bean.getNUM();
                        tv_favourable.setText("已优惠" + bean.getPREFERENTIALPRICE() + "元");
                        handler8.sendEmptyMessage(0);
                    }

                    @Override
                    public void onError(Throwable throwable, boolean b) {

                    }

                    @Override
                    public void onCancelled(CancelledException e) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });

            } else if (msg.what == 11) {
                Bundle b1 = new Bundle();
                b1 = (Bundle) msg.obj;
                String minus = b1.getString("minusUrl");
                String minusUrl = urlUse + minus;
                Log.d("minusUrl", url1);
                final CustomProgressDialog dialog = new CustomProgressDialog(ShopActivity.this, R.style.CommProgressDialog, "加载中...", R.anim.frame2);
                dialog.show();
                RequestParams params = new RequestParams(minusUrl);
                x.http().get(params, new org.xutils.common.Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String s) {

                        handler8 = new Handler() {
                            public void handleMessage(Message msg) {

                                dialog.cancel();
                            }
                        };
                        Gson gson = new Gson();
                        final ShopCarBean bean = gson.fromJson(s, ShopCarBean.class);
                        adapter = new ShopCarAdapter(ShopActivity.this, bean.getSHOPITEM(), handler);
                        if (bean.getSELECTSTUTE() == 1) {
                            ck_all.setChecked(true);
                        } else {
                            ck_all.setChecked(false);
                        }
                        //list view的设置
                        listView.setPullLoadEnable(true);
                        listView.setAdapter(adapter);
                        tv_all.setText("" + bean.getTOTAL());
                        tv_balance.setText("结算(" + bean.getNUM() + ")");
                        a = bean.getNUM();
                        tv_favourable.setText("已优惠" + bean.getPREFERENTIALPRICE() + "元");
                        handler8.sendEmptyMessage(0);
                    }

                    @Override
                    public void onError(Throwable throwable, boolean b) {

                    }

                    @Override
                    public void onCancelled(CancelledException e) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });


            } else if (msg.what == 12) {
                Bundle b2 = new Bundle();
                b2 = (Bundle) msg.obj;
                String chose = b2.getString("choseUrl");
                String choseUrl = urlUse + chose;
                final CustomProgressDialog dialog = new CustomProgressDialog(ShopActivity.this, R.style.CommProgressDialog, "加载中...", R.anim.frame2);
                dialog.show();
                RequestParams params = new RequestParams(choseUrl);
                x.http().get(params, new org.xutils.common.Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String s) {

                        handler8 = new Handler() {
                            public void handleMessage(Message msg) {

                                dialog.cancel();
                            }
                        };
                        Gson gson = new Gson();
                        final ShopCarBean bean = gson.fromJson(s, ShopCarBean.class);
                        adapter = new ShopCarAdapter(ShopActivity.this, bean.getSHOPITEM(), handler);
                        if (bean.getSELECTSTUTE() == 1) {
                            ck_all.setChecked(true);
                        } else {
                            ck_all.setChecked(false);
                        }
                        //list view的设置
                        listView.setPullLoadEnable(true);
                        listView.setAdapter(adapter);
                        tv_all.setText("" + bean.getTOTAL());
                        tv_balance.setText("结算(" + bean.getNUM() + ")");
                        a = bean.getNUM();
                        tv_favourable.setText("已优惠" + bean.getPREFERENTIALPRICE() + "元");
                        handler8.sendEmptyMessage(0);
                    }

                    @Override
                    public void onError(Throwable throwable, boolean b) {

                    }

                    @Override
                    public void onCancelled(CancelledException e) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });


            } else if (msg.what == 13) {

            }

        }
    };


    public class MyListener implements XListViewSwipe.IXListViewListener {

        @Override
        public void onRefresh() {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //刷新写在这里
                    SharedPreferences share = getSharedPreferences("logIn", Context.MODE_PRIVATE);
                    UID = share.getString("login", "");
                    url = urlUse + url1 + UID;
                    RequestParams params = new RequestParams(url);
                    x.http().get(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String s) {
                            Gson gson = new Gson();
                            final ShopCarBean bean = gson.fromJson(s, ShopCarBean.class);

                            if (bean.getSHOPITEM().size() == 0) {
                                tv_sc_cou.setVisibility(View.GONE);
                                shopping_car_textView.setVisibility(View.VISIBLE);
                                rl_store.setVisibility(View.GONE);
//                                rl_safe.setVisibility(View.GONE);
                                tv_bj.setVisibility(View.GONE);
                                rl2.setVisibility(View.GONE);
                                listView.setVisibility(View.GONE);
                                rl1.setVisibility(View.GONE);
                                tv_wc.setVisibility(View.GONE);
                                tv_title.setVisibility(View.VISIBLE);
                                tv_title1.setVisibility(View.VISIBLE);

                            } else {
                                tv_sc_cou.setVisibility(View.VISIBLE);
                                shopping_car_textView.setVisibility(View.GONE);
                                rl_store.setVisibility(View.VISIBLE);
//                                rl_safe.setVisibility(View.VISIBLE);
                                tv_bj.setVisibility(View.VISIBLE);
                                rl2.setVisibility(View.VISIBLE);
                                listView.setVisibility(View.VISIBLE);
                                tv_title.setVisibility(View.GONE);
                                tv_title1.setVisibility(View.GONE);
                            }
                            adapter = new ShopCarAdapter(ShopActivity.this, bean.getSHOPITEM(), handler);
                            if (bean.getSELECTSTUTE() == 1) {
                                ck_all.setChecked(true);
                            } else {
                                ck_all.setChecked(false);
                            }
                            //list view的设置
                            listView.setPullLoadEnable(true);
                            listView.setAdapter(adapter);
                            tv_all.setText("" + bean.getTOTAL());
                            tv_balance.setText("结算(" + bean.getNUM() + ")");
                            a = bean.getNUM();
                            tv_favourable.setText("已优惠" + bean.getPREFERENTIALPRICE() + "元");
                        }

                        @Override
                        public void onError(Throwable throwable, boolean b) {

                        }

                        @Override
                        public void onCancelled(CancelledException e) {

                        }

                        @Override
                        public void onFinished() {

                        }
                    });
                    onLoad();
                }
            }, 2000);
        }



        @Override
        public void onLoadMore() {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //加载写在这里

                    onLoad();
                }
            }, 2000);
        }

    }

    private void onLoad() {
        listView.stopRefresh();
        listView.stopLoadMore();
        listView.setRefreshTime("您正在安全购物环境中 请安心购物");
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    //去凑单的按钮
    @Event(R.id.tv_sc_cou)
    private void couClick(View view) {
        String a = tv_all.getText().toString();
        Log.d("qucoudan", a);

        String b = "0";
        if (b.equals(a)) {
            Toast.makeText(ShopActivity.this, "请选择一件商品", Toast.LENGTH_SHORT).show();
        } else {
            String s1 = String.valueOf(a);
            String s2 = s1.substring(0, s1.indexOf("."));
            int i = Integer.parseInt(s2);
            Log.d("qucoudan", String.valueOf(i));
            Intent intent = new Intent(ShopActivity.this, MergeActivity.class);
            intent.putExtra("merge", String.valueOf(i));
            intent.putExtra("type", "4");
            startActivity(intent);
        }

    }

    @Event(R.id.tv_sc_delete)
    private void deleteClick(View view) {
        if (ck_delete.isChecked()) {
            String url = urlUse + "Items.svc//delshopall//" + UID;
            RequestParams params = new RequestParams(url);
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String s) {
                    Gson gson = new Gson();
                    ShopCarBean bean = gson.fromJson(s, ShopCarBean.class);
                    adapter.delALL();
                    listView.setAdapter(adapter);
                    tv_all.setText("" + bean.getTOTAL());
                    tv_balance.setText("结算(" + bean.getNUM() + ")");
                    tv_favourable.setText("已优惠" + bean.getPREFERENTIALPRICE() + "元");
                }

                @Override
                public void onError(Throwable throwable, boolean b) {

                }

                @Override
                public void onCancelled(CancelledException e) {

                }

                @Override
                public void onFinished() {

                }
            });
        } else {
            Toast.makeText(ShopActivity.this, "请点击全选", Toast.LENGTH_SHORT).show();
        }
    }



    @Event(R.id.iv_back_shop)
    private void backClick(View view){
        finish();
    }

}
