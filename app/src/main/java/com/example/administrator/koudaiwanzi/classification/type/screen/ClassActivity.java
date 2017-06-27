package com.example.administrator.koudaiwanzi.classification.type.screen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.details.DetailsActivity;
import com.example.administrator.koudaiwanzi.details.detail.size.ShowPop;
import com.example.administrator.koudaiwanzi.dialog.CustomProgressDialog;
import com.example.administrator.koudaiwanzi.person.order.OrderAllActivity;
import com.example.administrator.koudaiwanzi.product.RangeSeekBar;
import com.example.administrator.koudaiwanzi.shopcar.ShopCarFragment;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016/6/25.
 * 分类的详情界面（带标签的筛选界面）
 */

@ContentView(R.layout.activity_class)
public class ClassActivity extends AppCompatActivity {
    private String url1 = MyUrl.url;
    private ClassAdapter adapter;
    private String DetailUrl;
    private Handler handler;
    private String shopUrl;
    private String strGBK = "";
    private String priceUrl;

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

    //最上面标签的textview
    @ViewInject(R.id.tv_class_top)
    private TextView tv_class_top;

    //最外层的DrawerLayout
    @ViewInject(R.id.class_drawerLayout)
    private DrawerLayout ll_hide;

    //价格滑动条
    @ViewInject(R.id.rl_price)
    private RelativeLayout mLayput;

    //绑定最小价格
    @ViewInject(R.id.tv_class_price_min_product)
    private TextView tv_min;

    //绑定最大价格
    @ViewInject(R.id.tv_class_price_max_product)
    private TextView tv_max;

    //绑定RadioGroup
    @ViewInject(R.id.rg_class)
    private RadioGroup rg_class;

    //绑定gridView
    @ViewInject(R.id.show_gridview)
    private GridView gridView;


    //绑定筛选的完成按钮的ID
    @ViewInject(R.id.tv_class_complete)
    private TextView tv_complete;

    @ViewInject(R.id.scrollView)
    private HorizontalScrollView scrollView;

    //默认
    @ViewInject(R.id.tv_default_black)
    private TextView tv_default;

    //销量
    @ViewInject(R.id.tv_sales_black)
    private TextView tv_sales;

    //黑色的价格
    @ViewInject(R.id.tv_price_black)
    private TextView tv_price_black;

    //点击后红色的价格
    @ViewInject(R.id.ck_price_red)
    private CheckBox ck_price_red;

    //绑定drawerLayout的ID
    @ViewInject(R.id.class_drawerLayout)
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        final CustomProgressDialog dialog = new CustomProgressDialog(ClassActivity.this, R.style.CommProgressDialog, "加载中...", R.anim.frame2);
        dialog.setProgressStyle(R.style.CommProgressDialog);
        dialog.show();
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


        scrollView.setHorizontalScrollBarEnabled(false);
//        SharedPreferences share = ClassActivity.this.getSharedPreferences("Class", Context.MODE_PRIVATE);
//        DetailUrl = share.getString("class", "");
        DetailUrl = getIntent().getStringExtra("classKey");
        adapter = new ClassAdapter(this, handler2);
        String url = url1 + DetailUrl;

//        if (DetailUrl == "") {
//
//        } else {
//            try {
//                strGBK = URLEncoder.encode(DetailUrl, "GBK");
//                Log.e("gdfg", strGBK);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }



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
        RequestParams params = new RequestParams(url);
        Log.e("oiowjg", url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                final ProductBean bean = gson.fromJson(s, ProductBean.class);

                tv_class_top.setText(bean.getTITLE());

                adapter.addData(bean.getDITEMS());
                gridView.setAdapter(adapter);
                dialog.cancel();
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent1 = new Intent(ClassActivity.this, DetailsActivity.class);
                        intent1.putExtra("key", bean.getDITEMS().get(position).getDetailUrl());
                        startActivity(intent1);
                    }
                });

                //动态添加标签
                //动态添加radio button
                for (int i = 0; i < bean.getCMENU().size(); i++) {
                    final RadioButton radio = new RadioButton(ClassActivity.this);
                    radio.setMaxEms(8);
                    radio.setMaxLines(1);
                    //是什么布局要定义什么布局所以用RadioGroup
                    RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(
                            RadioGroup.LayoutParams.WRAP_CONTENT,
                            RadioGroup.LayoutParams.WRAP_CONTENT
                    );
                    params.setMargins(30, 10, 10, 0);
                    //去掉按钮
                    radio.setButtonDrawable(android.R.color.transparent);
                    //添加背景
                    radio.setBackgroundResource(R.drawable.radio_group2);
                    radio.setText(bean.getCMENU().get(i).getCODENAME());
                    radio.setTextSize(15);
                    radio.setLayoutParams(params);

                    if (bean.getCMENU().get(i).getMsg() == 1) {
                        radio.setChecked(true);
                        radio.setTextColor(Color.parseColor("#ffffff"));
                        priceUrl =   bean.getCMENU().get(i).getLID()+"/"+bean.getCMENU().get(i).getTID();

                    }
                    //radio button的监听
                    final int finalI = i;
                    radio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                            if (radio.isChecked()) {
                                radio.setVisibility(View.GONE);
                                String msg = bean.getCMENU().get(finalI).getDetailUrl();
//                                SharedPreferences share = ClassActivity.this.getSharedPreferences("Class", Context.MODE_PRIVATE);
//                                SharedPreferences.Editor editor = share.edit();
//                                editor.putString("class", msg);
//                                editor.apply();
                                Intent intent = new Intent(ClassActivity.this, ClassActivity.class);
                                intent.putExtra("classKey", msg);
                                startActivity(intent);
                                ClassActivity.this.overridePendingTransition(0, 0);
                                finish();
                            } else {

                            }
                        }
                    });
                    rg_class.addView(radio);
                }
                if (bean.getDEFAULTSTATUE() == 1) {
                    tv_default.setTextColor(0xFFf24e30);
                } else {
                    tv_default.setTextColor(0xFF000000);

                }
                if (bean.getPEICESTATUE() == 0) {
                    tv_price_black.setVisibility(View.VISIBLE);
                    ck_price_red.setVisibility(View.GONE);
                } else {
                    tv_price_black.setVisibility(View.GONE);
                    ck_price_red.setVisibility(View.VISIBLE);
                }
                if (bean.getSALESSTATUE() == 0) {
                    tv_sales.setTextColor(0xFF000000);
                } else {
                    tv_sales.setTextColor(0xFFf24e30);
                }
                tv_default.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (bean.getDEFAULTSTATUE() == 1) {
                            tv_default.setTextColor(0xFFf24e30);

                        } else {
                            tv_default.setTextColor(0xFF000000);

                        }
                        if (bean.getPEICESTATUE() == 0) {
                            tv_price_black.setVisibility(View.VISIBLE);
                            ck_price_red.setVisibility(View.GONE);
                        } else {
                            tv_price_black.setVisibility(View.GONE);
                            ck_price_red.setVisibility(View.VISIBLE);
                        }

                        if (bean.getSALESSTATUE() == 0) {
                            tv_sales.setTextColor(0xFF000000);

                        } else {
                            tv_sales.setTextColor(0xFFf24e30);
                        }
                        adapter.addData(bean.getDITEMS());
                        gridView.setAdapter(adapter);
                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent1 = new Intent(ClassActivity.this, DetailsActivity.class);
                                intent1.putExtra("key", bean.getDITEMS().get(position).getDetailUrl());
                                startActivity(intent1);
                            }
                        });
                    }
                });
                tv_complete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String min = tv_min.getText().toString();
                        String max = tv_max.getText().toString();
                        String url = url1 + bean.getFILTER() + min + "/" + max;
                        RequestParams params1 = new RequestParams(url);
                        dialog.show();
                        handler = new Handler() {
                            public void handleMessage(Message msg) {
                                dialog.cancel();
                            }
                        };
                        x.http().get(params1, new CommonCallback<String>() {
                            @Override
                            public void onSuccess(String s) {
                                Gson gson = new Gson();
                                final ProductBean bean = gson.fromJson(s, ProductBean.class);
                                if (bean.getDEFAULTSTATUE() == 1) {
                                    tv_default.setTextColor(0xFFf24e30);
                                } else {
                                    tv_default.setTextColor(0xFF000000);
                                }
                                if (bean.getPEICESTATUE() == 0) {
                                    tv_price_black.setVisibility(View.VISIBLE);
                                    ck_price_red.setVisibility(View.GONE);
                                } else {
                                    tv_price_black.setVisibility(View.GONE);
                                    ck_price_red.setVisibility(View.VISIBLE);
                                }

                                if (bean.getSALESSTATUE() == 0) {
                                    tv_sales.setTextColor(0xFF000000);

                                } else {
                                    tv_sales.setTextColor(0xFFf24e30);
                                }
                                adapter.addData(bean.getDITEMS());
                                gridView.setAdapter(adapter);
                                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent1 = new Intent(ClassActivity.this, DetailsActivity.class);
                                        intent1.putExtra("key", bean.getDITEMS().get(position).getDetailUrl());
                                        startActivity(intent1);
                                    }
                                });
                                drawerLayout.closeDrawers();
                                handler.sendEmptyMessage(0);
                            }

                            @Override
                            public void onError(Throwable throwable, boolean b) {
                                handler.sendEmptyMessage(0);
                                drawerLayout.closeDrawers();
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

                tv_sales.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.show();
                        handler = new Handler() {
                            public void handleMessage(Message msg) {
                                dialog.cancel();
                            }
                        };
                        String def = url1 + bean.getSALESVOLURL();
                        RequestParams params1 = new RequestParams(def);
                        x.http().get(params1, new CommonCallback<String>() {
                            @Override
                            public void onSuccess(String o) {
                                Gson gson = new Gson();
                                final ProductBean bean = gson.fromJson(o, ProductBean.class);
                                if (bean.getDEFAULTSTATUE() == 1) {
                                    tv_default.setTextColor(0xFFf24e30);

                                } else {
                                    tv_default.setTextColor(0xFF000000);

                                }
                                if (bean.getPEICESTATUE() == 0) {
                                    tv_price_black.setVisibility(View.VISIBLE);
                                    ck_price_red.setVisibility(View.GONE);
                                } else {
                                    tv_price_black.setVisibility(View.GONE);
                                    ck_price_red.setVisibility(View.VISIBLE);
                                }

                                if (bean.getSALESSTATUE() == 0) {
                                    tv_sales.setTextColor(0xFF000000);

                                } else {
                                    tv_sales.setTextColor(0xFFf24e30);
                                }
                                adapter.addData(bean.getDITEMS());
                                gridView.setAdapter(adapter);
                                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent1 = new Intent(ClassActivity.this, DetailsActivity.class);
                                        intent1.putExtra("key", bean.getDITEMS().get(position).getDetailUrl());
                                        startActivity(intent1);
                                    }
                                });
                                handler.sendEmptyMessage(0);
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
                });


                tv_price_black.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.show();
                        handler = new Handler() {
                            public void handleMessage(Message msg) {
                                dialog.cancel();
                            }
                        };
                        String def = url1 + bean.getPEICEURL();
                        RequestParams params1 = new RequestParams(def);
                        x.http().get(params1, new CommonCallback<String>() {
                            @Override
                            public void onSuccess(String o) {
                                Gson gson = new Gson();
                                final ProductBean bean = gson.fromJson(o, ProductBean.class);
                                if (bean.getDEFAULTSTATUE() == 1) {
                                    tv_default.setTextColor(0xFFf24e30);

                                } else {
                                    tv_default.setTextColor(0xFF000000);

                                }
                                if (bean.getPEICESTATUE() == 0) {
                                    tv_price_black.setVisibility(View.VISIBLE);
                                    ck_price_red.setVisibility(View.GONE);
                                } else {
                                    tv_price_black.setVisibility(View.GONE);
                                    ck_price_red.setVisibility(View.VISIBLE);
                                }

                                if (bean.getSALESSTATUE() == 0) {
                                    tv_sales.setTextColor(0xFF000000);

                                } else {
                                    tv_sales.setTextColor(0xFFf24e30);
                                }

                                adapter.addData(bean.getDITEMS());
                                gridView.setAdapter(adapter);
                                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent1 = new Intent(ClassActivity.this, DetailsActivity.class);
                                        intent1.putExtra("key", bean.getDITEMS().get(position).getDetailUrl());
                                        startActivity(intent1);
                                    }
                                });
                                handler.sendEmptyMessage(0);
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
                });

                ck_price_red.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.show();
                        handler = new Handler() {
                            public void handleMessage(Message msg) {
                                dialog.cancel();
                            }
                        };
                        if (ck_price_red.isChecked()) {
                            String def = url1 + "Items.svc/catelistprice/"+priceUrl+"/0";
                            Log.d("dsadsadsad1", def);
//                            String def = url1 + "Items.svc/catelistprice/37946890-49d1-4b21-8a12-1a0e0fba6cfb/1e4b15ba-a3ca-48d3-baa0-974a2d4a8C98/0";
                            RequestParams params1 = new RequestParams(def);
                            x.http().get(params1, new CommonCallback<String>() {
                                @Override
                                public void onSuccess(String o) {
                                    Gson gson = new Gson();
                                    final ProductBean bean = gson.fromJson(o, ProductBean.class);
                                    if (bean.getDEFAULTSTATUE() == 1) {
                                        tv_default.setTextColor(0xFFf24e30);

                                    } else {
                                        tv_default.setTextColor(0xFF000000);

                                    }
                                    if (bean.getPEICESTATUE() == 0) {
                                        tv_price_black.setVisibility(View.VISIBLE);
                                        ck_price_red.setVisibility(View.GONE);
                                    } else {
                                        tv_price_black.setVisibility(View.GONE);
                                        ck_price_red.setVisibility(View.VISIBLE);
                                    }

                                    if (bean.getSALESSTATUE() == 0) {
                                        tv_sales.setTextColor(0xFF000000);

                                    } else {
                                        tv_sales.setTextColor(0xFFf24e30);
                                    }

                                    adapter.addData(bean.getDITEMS());
                                    gridView.setAdapter(adapter);
                                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            Intent intent1 = new Intent(ClassActivity.this, DetailsActivity.class);
                                            intent1.putExtra("key", bean.getDITEMS().get(position).getDetailUrl());
                                            startActivity(intent1);
                                        }
                                    });
                                    handler.sendEmptyMessage(0);
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

                            String def = url1 + "Items.svc/catelistprice/"+priceUrl+"/1";
                            Log.d("dsadsadsad2", def);
//                            String def = url1 + "Items.svc/catelistprice/37946890-49d1-4b21-8a12-1a0e0fba6cfb/1e4b15ba-a3ca-48d3-baa0-974a2d4a8C98/1";
                            RequestParams params1 = new RequestParams(def);
                            x.http().get(params1, new CommonCallback<String>() {
                                @Override
                                public void onSuccess(String o) {
                                    Gson gson = new Gson();
                                    final ProductBean bean = gson.fromJson(o, ProductBean.class);
                                    if (bean.getDEFAULTSTATUE() == 1) {
                                        tv_default.setTextColor(0xFFf24e30);

                                    } else {
                                        tv_default.setTextColor(0xFF000000);

                                    }
                                    if (bean.getPEICESTATUE() == 0) {
                                        tv_price_black.setVisibility(View.VISIBLE);
                                        ck_price_red.setVisibility(View.GONE);
                                    } else {
                                        tv_price_black.setVisibility(View.GONE);
                                        ck_price_red.setVisibility(View.VISIBLE);
                                    }

                                    if (bean.getSALESSTATUE() == 0) {
                                        tv_sales.setTextColor(0xFF000000);

                                    } else {
                                        tv_sales.setTextColor(0xFFf24e30);
                                    }


                                    adapter.addData(bean.getDITEMS());
                                    gridView.setAdapter(adapter);
                                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            Intent intent1 = new Intent(ClassActivity.this, DetailsActivity.class);
                                            intent1.putExtra("key", bean.getDITEMS().get(position).getDetailUrl());
                                            startActivity(intent1);
                                        }
                                    });
                                    handler.sendEmptyMessage(0);
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

    //筛选按键
    @Event(R.id.tv_choose_product)
    private void textClick(View view) {
        drawerLayout.openDrawer(Gravity.RIGHT);
    }

    //返回按键
    @Event(R.id.iv_class_back)
    private void imaClick(View view) {
        finish();
    }


    private Handler handler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 28) {
                shopUrl = (String) msg.obj;
                Log.e("ersfhg", shopUrl);
                // 标题和主页开始播放动画
                drawerLayout.startAnimation(mScalInAnimation1);

                // 弹出sPopupWindow
                mShowPopup = new ShowPop(mLayoutInflater.inflate(
                        R.layout.view_pop, null), ClassActivity.this, url1 + shopUrl);

                mShowPopup.setOnDismissListener(new OnPopupDismissListener());
                mShowPopup.showAtLocation(ClassActivity.this.findViewById(R.id.class_drawerLayout),
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
            drawerLayout.startAnimation(mScalOutAnimation);
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
            drawerLayout.startAnimation(mScalInAnimation2);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

}
