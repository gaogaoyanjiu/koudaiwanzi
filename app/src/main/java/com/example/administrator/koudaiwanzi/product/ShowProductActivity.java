package com.example.administrator.koudaiwanzi.product;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.details.DetailsActivity;
import com.example.administrator.koudaiwanzi.details.detail.size.ShowPop;
import com.example.administrator.koudaiwanzi.details.detail.size.SizeBean;
import com.example.administrator.koudaiwanzi.dialog.CustomProgressDialog;
import com.example.administrator.koudaiwanzi.home.search.SearchActivity;
import com.example.administrator.koudaiwanzi.refresh.PullToRefreshLayout;
import com.example.administrator.koudaiwanzi.refresh.PullableGridView;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/5/9.
 */
@ContentView(value = R.layout.activity_showproduct)
public class ShowProductActivity extends AppCompatActivity {
    private String UID;
    private String where;
    private ShowProductAdapter showProductAdapter;
    private String url1 = MyUrl.url;
    private List<String> getData;
    private ShowAdapter showAdapter;
    private int q = 0;
    private String tag;
    private FilterBean.ItemsBean item;
    private String shopUrl;
    private String getUrl;
    private Handler handler8;
    // 弹出的popupwindow
    private ShowPop mShowPopup;
    // Layoutinflater
    private LayoutInflater mLayoutInflater;
    // 主页缩放动画
    private Animation mScalInAnimation1;
    // 主页缩放完毕小幅回弹动画
    private Animation mScalInAnimation2;
    // 主页回弹正常状态动画
    private Animation mScalOutAnimation;
    // 标题恢复动画
    private Animation mTranInAnimation;
    // 标题消失动画
    private Animation mTranOutAnimation;


    //最外层的DrawerLayout
    @ViewInject(R.id.showProduct_ll)
    private LinearLayout linearLayout;

    //最外层的DrawerLayout
    @ViewInject(R.id.dl_drawerLayout)
    private DrawerLayout ll_hide;

    //筛选中的标签用的gridview
    @ViewInject(R.id.gv_class)
    private GridView gv_tag;

    //默认按键
    @ViewInject(R.id.tv_sp_default_black)
    private TextView tv_default_black;

    //销量按键
    @ViewInject(R.id.tv_sp_sales_black)
    private TextView tv_sales_black;

    //红色的销量按键
    @ViewInject(R.id.tv_sp_sales_red)
    private TextView tv_sales_red;

    //价格按键
    @ViewInject(R.id.tv_sp_price_black)
    private TextView tv_price_black;

    //红色的价格按键
    @ViewInject(R.id.ck_sp_price_red)
    private CheckBox ck_price_red;

    //绑定listView的ID
    @ViewInject(R.id.show_listview)
    private PullableGridView gridView;

    //绑定drawerLayout的ID
    @ViewInject(R.id.dl_drawerLayout)
    private DrawerLayout drawerLayout;

    //价格滑动条
    @ViewInject(R.id.rl_price_show)
    private RelativeLayout mLayput;

    //最小价格
    @ViewInject(R.id.tv_class_price_min)
    private TextView tv_min;

    //最大价格
    @ViewInject(R.id.tv_class_price_max)
    private TextView tv_max;

    //筛选中完成按键
    @ViewInject(R.id.tv_sp_complete)
    private TextView tv_complete;

    private String strGBK;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        SharedPreferences share = this.getSharedPreferences("logIn", Context.MODE_PRIVATE);
        UID = share.getString("login", "");
        Intent intent = this.getIntent();
        where = intent.getStringExtra("where");
        getUrl = intent.getStringExtra("showKey");
        try {
            strGBK = URLEncoder.encode(getUrl, "GBK");
            Log.e("gdfg", strGBK);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.e("asdgss", strGBK);
        final String TID = intent.getStringExtra("TID");
        final CustomProgressDialog dialog = new CustomProgressDialog(this, R.style.CommProgressDialog, "加载中...", R.anim.frame2);
        mLayoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // 动画初始化
        mScalInAnimation1 = AnimationUtils.loadAnimation(this,
                R.anim.root_in);
        mScalInAnimation2 = AnimationUtils.loadAnimation(this,
                R.anim.root_in2);
        mScalOutAnimation = AnimationUtils.loadAnimation(this,
                R.anim.root_out);
        mTranInAnimation = AnimationUtils.loadAnimation(this,
                R.anim.title_in);
        mTranOutAnimation = AnimationUtils.loadAnimation(this,
                R.anim.title_out);


        // 注册事件回调
//        rl_size.setOnClickListener(new BtnClickEvent());
        mScalInAnimation1.setAnimationListener(new ScalInAnimation1());

        showProductAdapter = new ShowProductAdapter(this.getApplication(), handler);
        String url = url1 + strGBK;

        //价格滚动条
        RangeSeekBar<Integer> seekBar = new RangeSeekBar<Integer>(0, 2000, this);
        seekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar,
                                                    Integer minValue, Integer maxValue) {
                tv_min.setText(minValue + "");
                tv_max.setText(maxValue + "");
                //System.out.println("min=="+minValue+"  max=="+maxValue);
            }
        });
        seekBar.setNotifyWhileDragging(true);
        mLayput.addView(seekBar);

        if (where.equals("4")) {
            //跳转到搜索界面
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                Intent intent1 = new Intent(ShowProductActivity.this, SearchActivity.class);
//                startActivity(intent1);
                    finish();
                }
            });
        } else {
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(ShowProductActivity.this, SearchActivity.class);
                    startActivity(intent1);
                    finish();
                }
            });
        }

        RequestParams params = new RequestParams(url);
        Log.e("giolz", url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String o) {
                Gson gson = new Gson();
                final ShowProductBean bean = gson.fromJson(o, ShowProductBean.class);


                if (bean.getDEFAULTSTATUE() == 1) {
                    tv_default_black.setTextColor(0xFFf24e30);

                } else {
                    tv_default_black.setTextColor(0xFF000000);

                }

                if (bean.getPEICESTATUE() == 0) {
                    tv_price_black.setVisibility(View.VISIBLE);
                    ck_price_red.setVisibility(View.GONE);
                } else {
                    tv_price_black.setVisibility(View.GONE);
                    ck_price_red.setVisibility(View.VISIBLE);
                }

                if (bean.getSALESSTATUE() == 0) {
                    tv_sales_black.setVisibility(View.VISIBLE);
                    tv_sales_red.setVisibility(View.GONE);
                } else {
                    tv_sales_black.setVisibility(View.GONE);
                    tv_sales_red.setVisibility(View.VISIBLE);
                }

                //适配器添加数据
                showProductAdapter.addData(bean.getDITEMS());
                gridView.setAdapter(showProductAdapter);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent1 = new Intent(ShowProductActivity.this, DetailsActivity.class);
                        intent1.putExtra("key", bean.getDITEMS().get(position).getDetailUrl());
                        startActivity(intent1);
                    }
                });

                tv_default_black.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.show();
                        handler8 = new Handler() {
                            public void handleMessage(Message msg) {
                                dialog.cancel();
                            }
                        };
                        if (bean.getDEFAULTSTATUE() == 1) {
                            tv_default_black.setTextColor(0xFFf24e30);

                        } else {
                            tv_default_black.setTextColor(0xFF000000);

                        }

                        if (bean.getPEICESTATUE() == 0) {
                            tv_price_black.setVisibility(View.VISIBLE);
                            ck_price_red.setVisibility(View.GONE);
                        } else if (bean.getPEICESTATUE() == 1) {
                            tv_price_black.setVisibility(View.GONE);
                            ck_price_red.setVisibility(View.VISIBLE);
                            ck_price_red.setChecked(true);
                        } else {
                            tv_price_black.setVisibility(View.GONE);
                            ck_price_red.setVisibility(View.VISIBLE);
                            ck_price_red.setChecked(false);
                        }

                        if (bean.getSALESSTATUE() == 0) {
                            tv_sales_black.setVisibility(View.VISIBLE);
                            tv_sales_red.setVisibility(View.GONE);
                        } else {
                            tv_sales_black.setVisibility(View.GONE);
                            tv_sales_red.setVisibility(View.VISIBLE);
                        }
                        showProductAdapter.addData(bean.getDITEMS());
                        gridView.setAdapter(showProductAdapter);
                        handler8.sendEmptyMessage(0);
                    }
                });

                //销量按键的监听
                tv_sales_black.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.show();
                        handler8 = new Handler() {
                            public void handleMessage(Message msg) {
                                dialog.cancel();
                            }
                        };
                        String def = MyUrl.url + bean.getSALESVOLURL();
                        RequestParams params1 = new RequestParams(def);
                        x.http().get(params1, new CommonCallback<String>() {
                            @Override
                            public void onSuccess(String o) {
                                Gson gson = new Gson();
                                final ShowProductBean bean = gson.fromJson(o, ShowProductBean.class);
                                if (bean.getDEFAULTSTATUE() == 1) {
                                    tv_default_black.setTextColor(0xFFf24e30);

                                } else {
                                    tv_default_black.setTextColor(0xFF000000);

                                }

                                if (bean.getPEICESTATUE() == 0) {
                                    tv_price_black.setVisibility(View.VISIBLE);
                                    ck_price_red.setVisibility(View.GONE);
                                } else if (bean.getPEICESTATUE() == 1) {
                                    tv_price_black.setVisibility(View.GONE);
                                    ck_price_red.setVisibility(View.VISIBLE);
                                    ck_price_red.setChecked(true);
                                } else {
                                    tv_price_black.setVisibility(View.GONE);
                                    ck_price_red.setVisibility(View.VISIBLE);
                                    ck_price_red.setChecked(false);
                                }

                                if (bean.getSALESSTATUE() == 0) {
                                    tv_sales_black.setVisibility(View.VISIBLE);
                                    tv_sales_red.setVisibility(View.GONE);
                                } else {
                                    tv_sales_black.setVisibility(View.GONE);
                                    tv_sales_red.setVisibility(View.VISIBLE);
                                }
                                showProductAdapter.addData(bean.getDITEMS());
                                gridView.setAdapter(showProductAdapter);
                                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent1 = new Intent(ShowProductActivity.this, DetailsActivity.class);
                                        intent1.putExtra("key", bean.getDITEMS().get(position).getDetailUrl());
                                        startActivity(intent1);
                                    }
                                });
                                handler8.sendEmptyMessage(0);
                            }

                            @Override
                            public void onError(Throwable throwable, boolean b) {
//                                Toast.makeText(ShowProductActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(CancelledException e) {

                            }

                            @Override
                            public void onFinished() {

                            }
                        });
                    }
                });

                //价格按键监听
                tv_price_black.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.show();
                        handler8 = new Handler() {
                            public void handleMessage(Message msg) {
                                dialog.cancel();
                            }
                        };
                        String def = MyUrl.url + bean.getPEICEURL();
                        RequestParams params1 = new RequestParams(def);
                        x.http().get(params1, new CommonCallback<String>() {
                            @Override
                            public void onSuccess(String o) {
                                Gson gson = new Gson();
                                final ShowProductBean bean = gson.fromJson(o, ShowProductBean.class);
                                if (bean.getDEFAULTSTATUE() == 1) {
                                    tv_default_black.setTextColor(0xFFf24e30);

                                } else {
                                    tv_default_black.setTextColor(0xFF000000);

                                }

                                if (bean.getPEICESTATUE() == 0) {
                                    tv_price_black.setVisibility(View.VISIBLE);
                                    ck_price_red.setVisibility(View.GONE);
                                } else if (bean.getPEICESTATUE() == 1) {
                                    tv_price_black.setVisibility(View.GONE);
                                    ck_price_red.setVisibility(View.VISIBLE);
                                    ck_price_red.setChecked(true);
                                } else {
                                    tv_price_black.setVisibility(View.GONE);
                                    ck_price_red.setVisibility(View.VISIBLE);
                                    ck_price_red.setChecked(false);
                                }

                                if (bean.getSALESSTATUE() == 0) {
                                    tv_sales_black.setVisibility(View.VISIBLE);
                                    tv_sales_red.setVisibility(View.GONE);
                                } else {
                                    tv_sales_black.setVisibility(View.GONE);
                                    tv_sales_red.setVisibility(View.VISIBLE);
                                }
                                showProductAdapter.addData(bean.getDITEMS());
                                gridView.setAdapter(showProductAdapter);
                                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent1 = new Intent(ShowProductActivity.this, DetailsActivity.class);
                                        intent1.putExtra("key", bean.getDITEMS().get(position).getDetailUrl());
                                        startActivity(intent1);
                                    }
                                });
                                handler8.sendEmptyMessage(0);
                            }

                            @Override
                            public void onError(Throwable throwable, boolean b) {
//                                Toast.makeText(ShowProductActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(CancelledException e) {

                            }

                            @Override
                            public void onFinished() {

                            }
                        });
                    }
                });

                //红色价格按键监听
                ck_price_red.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.show();
                        handler8 = new Handler() {
                            public void handleMessage(Message msg) {
                                dialog.cancel();
                            }
                        };
                        if (ck_price_red.isChecked()) {
                            String def = MyUrl.url + "Items.svc//displaypice//" + TID + "//0";

                            RequestParams params1 = new RequestParams(def);
                            x.http().get(params1, new CommonCallback<String>() {
                                @Override
                                public void onSuccess(String o) {
                                    Gson gson = new Gson();
                                    final ShowProductBean bean = gson.fromJson(o, ShowProductBean.class);
                                    if (bean.getDEFAULTSTATUE() == 1) {
                                        tv_default_black.setTextColor(0xFFf24e30);

                                    } else {
                                        tv_default_black.setTextColor(0xFF000000);

                                    }
                                    if (bean.getPEICESTATUE() == 0) {
                                        tv_price_black.setVisibility(View.VISIBLE);
                                        ck_price_red.setVisibility(View.GONE);
                                    } else if (bean.getPEICESTATUE() == 1) {
                                        tv_price_black.setVisibility(View.GONE);
                                        ck_price_red.setVisibility(View.VISIBLE);

                                    } else {
                                        tv_price_black.setVisibility(View.GONE);
                                        ck_price_red.setVisibility(View.VISIBLE);

                                    }

                                    if (bean.getSALESSTATUE() == 0) {
                                        tv_sales_black.setVisibility(View.VISIBLE);
                                        tv_sales_red.setVisibility(View.GONE);
                                    } else {
                                        tv_sales_black.setVisibility(View.GONE);
                                        tv_sales_red.setVisibility(View.VISIBLE);
                                    }
                                    showProductAdapter.addData(bean.getDITEMS());
                                    gridView.setAdapter(showProductAdapter);
                                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            Intent intent1 = new Intent(ShowProductActivity.this, DetailsActivity.class);
                                            intent1.putExtra("key", bean.getDITEMS().get(position).getDetailUrl());
                                            startActivity(intent1);
                                        }
                                    });
                                    handler8.sendEmptyMessage(0);
                                }

                                @Override
                                public void onError(Throwable throwable, boolean b) {
//                                    Toast.makeText(ShowProductActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCancelled(CancelledException e) {

                                }

                                @Override
                                public void onFinished() {

                                }
                            });

                        } else {
                            String def = MyUrl.url + "Items.svc//displaypice//" + TID + "//1";
                            RequestParams params1 = new RequestParams(def);
                            x.http().get(params1, new CommonCallback<String>() {
                                @Override
                                public void onSuccess(String o) {
                                    Gson gson = new Gson();
                                    final ShowProductBean bean = gson.fromJson(o, ShowProductBean.class);
                                    if (bean.getDEFAULTSTATUE() == 1) {
                                        tv_default_black.setTextColor(0xFFf24e30);
                                    } else {
                                        tv_default_black.setTextColor(0xFF000000);

                                    }
                                    if (bean.getPEICESTATUE() == 0) {
                                        tv_price_black.setVisibility(View.VISIBLE);
                                        ck_price_red.setVisibility(View.GONE);
                                    } else if (bean.getPEICESTATUE() == 1) {
                                        tv_price_black.setVisibility(View.GONE);
                                        ck_price_red.setVisibility(View.VISIBLE);

                                    } else {
                                        tv_price_black.setVisibility(View.GONE);
                                        ck_price_red.setVisibility(View.VISIBLE);

                                    }

                                    if (bean.getSALESSTATUE() == 0) {
                                        tv_sales_black.setVisibility(View.VISIBLE);
                                        tv_sales_red.setVisibility(View.GONE);
                                    } else {
                                        tv_sales_black.setVisibility(View.GONE);
                                        tv_sales_red.setVisibility(View.VISIBLE);
                                    }
                                    showProductAdapter.addData(bean.getDITEMS());
                                    gridView.setAdapter(showProductAdapter);
                                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            Intent intent1 = new Intent(ShowProductActivity.this, DetailsActivity.class);
                                            intent1.putExtra("key", bean.getDITEMS().get(position).getDetailUrl());
                                            startActivity(intent1);
                                        }
                                    });
                                    handler8.sendEmptyMessage(0);

                                }

                                @Override
                                public void onError(Throwable throwable, boolean b) {
//                                    Toast.makeText(ShowProductActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
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


                Log.e("djfkhdskjghdkjghkdj", MyUrl.url + bean.getFILTER());
                RequestParams params1 = new RequestParams(MyUrl.url + bean.getFILTER());
                x.http().get(params1, new CommonCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Gson gson = new Gson();
                        final FilterBean bean = gson.fromJson(s, FilterBean.class);
                        showAdapter = new ShowAdapter(ShowProductActivity.this, bean.getItems(), R.layout.item_show_select);
                        gv_tag.setAdapter(showAdapter);
                        //判断标签只能选择5个
                        gv_tag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                item = (FilterBean.ItemsBean) parent.getItemAtPosition(position);

                                if (q < 5) {
                                    item.setNameIsSelect(!item.getNameIsSelect());
                                    if (item.getNameIsSelect() == true) {
                                        if (q < 5) {
                                            q++;
//                                            getData.add(item.getLID());
                                            showAdapter.notifyDataSetChanged();
                                        }
                                    } else {
                                        q--;
//                                        getData.remove(0);
                                        showAdapter.notifyDataSetChanged();
                                    }
                                    Log.e("gdfcxgfg", q + "");
                                } else {
                                    item.setNameIsSelect(!item.getNameIsSelect());
                                    if (item.getNameIsSelect() == true) {
                                        item.setNameIsSelect(false);
                                        Toast.makeText(ShowProductActivity.this, "只能选择5个", Toast.LENGTH_SHORT).show();
                                    } else {
                                        q--;
                                        showAdapter.notifyDataSetChanged();
                                    }

                                }

                            }
                        });

                        //点击完成筛选的监听
                        tv_complete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getData = new ArrayList<>();
                                int p = bean.getItems().size();
                                for (int i = 0; i < p; i++) {
                                    if (bean.getItems().get(i).getNameIsSelect() == true) {
                                        getData.add(bean.getItems().get(i).getLID());
                                    }
                                }

                                int num = getData.size();

                                String a = (String) tv_min.getText();
                                String b = (String) tv_max.getText();
                                Log.e("fdfdxbvh", a + "");
                                Log.e("fdfdxbvh", b + "");

                                if (num == 0) {
                                    Toast.makeText(ShowProductActivity.this, "请选择类别", Toast.LENGTH_SHORT).show();
                                } else if (num == 1) {
                                    tag = getData.get(0);
                                } else if (num == 2) {
                                    tag = getData.get(0) + ";" + getData.get(1);
                                } else if (num == 3) {
                                    tag = getData.get(0) + ";" + getData.get(1) + ";" + getData.get(2);
                                } else if (num == 4) {
                                    tag = getData.get(0) + ";" + getData.get(1) + ";" + getData.get(2) + ";" + getData.get(3);
                                } else if (num == 5) {
                                    tag = getData.get(0) + ";" + getData.get(1) + ";" + getData.get(2) + ";" + getData.get(3) + ";" + getData.get(4);
                                }

                                RequestParams params = new RequestParams(url1 + "Items.svc/displayfilter/" + TID + "/" + tag + "/" + a + "/" + b);
                                Log.d("kankancuozaina", url1 + "Items.svc/displayfilter/" + TID + "/" + tag + "/" + a + "/" + b);

                                x.http().get(params, new CommonCallback<String>() {
                                    @Override
                                    public void onSuccess(String o) {
                                        Log.e("nuoiuioejo", "woshidfhgjkjk");
                                        Gson gson = new Gson();
                                        final ShowProductBean bean = gson.fromJson(o, ShowProductBean.class);
                                        showProductAdapter.addData(bean.getDITEMS());
                                        gridView.setAdapter(showProductAdapter);
                                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                Intent intent1 = new Intent(ShowProductActivity.this, DetailsActivity.class);
                                                intent1.putExtra("key", bean.getDITEMS().get(position).getDetailUrl());
                                                startActivity(intent1);
                                            }
                                        });
                                        drawerLayout.closeDrawers();
                                    }

                                    @Override
                                    public void onError(Throwable throwable, boolean b) {
//                                        Toast.makeText(ShowProductActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCancelled(CancelledException e) {

                                    }

                                    @Override
                                    public void onFinished() {

                                    }
                                });


                            }


                        });


                    }

                    @Override
                    public void onError(Throwable throwable, boolean b) {
//                        Toast.makeText(ShowProductActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(CancelledException e) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });


            }

            @Override
            public void onError(Throwable throwable, boolean b) {
//                Toast.makeText(ShowProductActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });
        //上拉加载和下拉刷新的监听
        ((PullToRefreshLayout) findViewById(R.id.refresh_show))
                .setOnRefreshListener(new MyListenerCollection());

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 30) {
                shopUrl = (String) msg.obj;
                Log.e("ersfhg", url1 + shopUrl);
                Log.e("ersfhg", strGBK);
                // 标题和主页开始播放动画
                ll_hide.startAnimation(mScalInAnimation1);

                // 弹出sPopupWindow
                mShowPopup = new ShowPop(ShowProductActivity.this, mLayoutInflater.inflate(
                        R.layout.view_pop, null), url1 + shopUrl);

                mShowPopup.setOnDismissListener(new OnPopupDismissListener());
                mShowPopup.showAtLocation(ShowProductActivity.this.findViewById(R.id.dl_drawerLayout),
                        Gravity.CENTER, 0, 0); // ����layout��PopupWindow����ʾ��λ��
            }
        }
    };


    /**
     * popupwindow消失的回调
     */
    private class OnPopupDismissListener implements
            android.widget.PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // 标题和主页开始播放动画
            ll_hide.startAnimation(mScalOutAnimation);
        }
    }

    /**
     * 缩小动画的回调
     */
    public class ScalInAnimation1 implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            ll_hide.startAnimation(mScalInAnimation2);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }


    //返回按键
    @Event(R.id.iv_class_back)
    private void imaClick(View view) {
        finish();
    }

    //筛选按键
    @Event(R.id.tv_choose)
    private void textClick(View view) {
        drawerLayout.openDrawer(Gravity.RIGHT);
    }

    //刷新和加载的监听
    public class MyListenerCollection implements PullToRefreshLayout.OnRefreshListener {

        //刷新
        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            // 下拉刷新操作
            new Handler() {
                @Override
                public void handleMessage(Message msg) {

                    // 千万别忘了告诉控件刷新完毕了哦！
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 1000);
        }

        //加载
        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            // 上拉加载操作
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
//                    String a = "";
//                    int i = 99987070;
//                    i++;
//                    String url1 = a + i;

                    // 千万别忘了告诉控件加载完毕了哦！
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 1000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setContentView(R.layout.view_null);
        Log.e("des", "onDestroy: ");
    }

}

