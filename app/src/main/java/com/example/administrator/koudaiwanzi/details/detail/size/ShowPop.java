package com.example.administrator.koudaiwanzi.details.detail.size;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.details.detail.CollectBean;
import com.example.administrator.koudaiwanzi.person.login.LoginActivity;
import com.example.administrator.koudaiwanzi.product.ShowProductActivity;
import com.example.administrator.koudaiwanzi.shopcar.MergeGetBean;
import com.example.administrator.koudaiwanzi.shopcar.OrderActivity;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/6/24.
 * 商品规格界面   选择尺寸、颜色、规格等
 */
public class ShowPop extends PopupWindow {
//    private String url_use;
    private String url_merge;
    private String url_all;
    private GridView gvSize;// 尺码
    private SizeAdapter sizeAdapter;// 尺码适配器
    //    private String url = "http://192.168.1.203/Items.svc//item//3b7e436c-0c12-4f16-8362-da477b71632a/1";
    // 关闭按钮
    private ImageView mBtnClose, iv_pic;
    private TextView tv_name;
    //添加数量
    private Button btn_add, btn_reduce, btn_num, btn_shopCar, btn_right, btn_buy;
    private int a = 1;
    private String info;
    private String url1 = MyUrl.url;
    private String style;

    //详情页的构造方法
    public ShowPop(final Context context, View contentView, final Handler handler, String url) {
        super(contentView);

        //gridView绑定ID
        this.gvSize = (GridView) contentView.findViewById(R.id.gv_size);
        //点击关闭按键绑定ID
        this.mBtnClose = (ImageView) contentView.findViewById(R.id.btnClose);
        //点击关闭
        this.mBtnClose.setOnClickListener(new CloseClickEvent());
        //增加按钮
        this.btn_add = (Button) contentView.findViewById(R.id.btn_add_pop);
        //减少按钮
        this.btn_reduce = (Button) contentView.findViewById(R.id.btn_reduce_pop);
        //数量
        this.btn_num = (Button) contentView.findViewById(R.id.number_btn);
        //加入购物车的ID绑定
        this.btn_shopCar = (Button) contentView.findViewById(R.id.btn_size_shopCar);

        this.iv_pic = (ImageView) contentView.findViewById(R.id.iv_pic);
        this.tv_name = (TextView) contentView.findViewById(R.id.tv_name);
        this.btn_buy = (Button) contentView.findViewById(R.id.btn_size_buy);
        // 设置属性
        setProperty();

        SharedPreferences share = context.getSharedPreferences("logIn", Context.MODE_PRIVATE);
        info = share.getString("login", "");

        RequestParams params = new RequestParams(url);
        Log.d("showpop", url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                final SizeBean bean = gson.fromJson(s, SizeBean.class);
                x.image().bind(iv_pic, url1 + bean.getICOURL());
                tv_name.setText(bean.getTRADENAME() + "");
                sizeAdapter = new SizeAdapter(context, bean.getSTANDARDS(), R.layout.item_size);
                gvSize.setAdapter(sizeAdapter);

                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        a++;
                        btn_num.setText(a + "");
                        handler.sendMessage(handler.obtainMessage(11, a));
                    }
                });

                btn_reduce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (a == 1) {
                        } else {
                            a--;
                            btn_num.setText(a + "");
                            handler.sendMessage(handler.obtainMessage(11, a));
                        }
                    }
                });

//                final int nums = ;

                handler.sendMessage(handler.obtainMessage(12, bean.getSTANDARDS().get(0).getSTANDARD()));
                handler.sendMessage(handler.obtainMessage(10, bean.getSTANDARDS().get(0).getSD_TB_ID()));
                handler.sendMessage(handler.obtainMessage(11, a));
                style = bean.getSTANDARDS().get(0).getSD_TB_ID();


                gvSize.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        final SizeBean.STANDARDSBean item = (SizeBean.STANDARDSBean) parent.getItemAtPosition(position);
                        item.setNameIsSelect(!item.getNameIsSelect());

                        //选中规格传值到商品详情界面(DetailFragment)
                        if (item.getNameIsSelect() == true) {
                            handler.sendMessage(handler.obtainMessage(12, bean.getSTANDARDS().get(position).getSTANDARD()));
                            handler.sendMessage(handler.obtainMessage(10, bean.getSTANDARDS().get(position).getSD_TB_ID()));
                            handler.sendMessage(handler.obtainMessage(11, a));
                            style = bean.getSTANDARDS().get(position).getSD_TB_ID();
                        } else {
                            handler.sendMessage(handler.obtainMessage(12, null));
                            handler.sendMessage(handler.obtainMessage(10, null));
                            handler.sendMessage(handler.obtainMessage(11, 0));
                            style = null;
                        }

                        int b = bean.getSTANDARDS().size();
                        for (int i = 0; i < b; i++) {
                            if (i != position) {
                                SizeBean.STANDARDSBean standardsBean = bean.getSTANDARDS().get(i);
                                standardsBean.setNameIsSelect(false);
                            }
                        }
                        sizeAdapter.notifyDataSetChanged();


                    }
                });






                //点击加入购物车
                btn_shopCar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (info == "") {
                            Toast.makeText(context, "请您进行登录", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, LoginActivity.class);
                            intent.putExtra("me", 1);
                            dismiss();
                            context.startActivity(intent);
                        } else {
                            String cid = bean.getCID();
                            String ShopUrl;
                            Log.e("shuliang", a + "");
                            ShopUrl = cid + "/" + info + "/" + a + "/" + style;
                            Log.d("woded", ShopUrl);
                            RequestParams pa = new RequestParams(url1 + "Items.svc/additem/" + ShopUrl);
                            Log.e("aaasss", url1 + "Items.svc/additem/" + ShopUrl);
                            x.http().get(pa, new CommonCallback<String>() {
                                @Override
                                public void onSuccess(String s) {
                                    Gson gson = new Gson();
                                    CollectBean bean = gson.fromJson(s, CollectBean.class);
                                    int c = bean.getMsg();
                                    if (style == null && a != 0) {
                                        Toast.makeText(context, "请选择商品类型", Toast.LENGTH_SHORT).show();
                                    } else if (style != null && a == 0) {
                                        Toast.makeText(context, "请选择商品数量", Toast.LENGTH_SHORT).show();
                                    } else if (style == null && a == 0) {
                                        Toast.makeText(context, "请选择商品类型和数量", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (c == 1) {
                                            Toast.makeText(context, "加入购物车成功", Toast.LENGTH_SHORT).show();
                                            handler.sendMessage(handler.obtainMessage(13, a));
                                            dismiss();
                                        } else {
                                            Toast.makeText(context, "加入失败", Toast.LENGTH_SHORT).show();
                                        }
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
                        }

                    }
                });

                btn_buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (info == "") {
                            Toast.makeText(context, "请您进行登录", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, LoginActivity.class);
                            intent.putExtra("me", 1);
                            dismiss();
                            context.startActivity(intent);
                        } else {
                            String cid = bean.getCID();
                            String ShopUrl;
                            ShopUrl = cid + "/" + info + "/" + a + "/" + style;
                            RequestParams pa = new RequestParams(url1 + "/Items.svc/toworder/" + ShopUrl);
                            x.http().get(pa, new CommonCallback<String>() {
                                @Override
                                public void onSuccess(String s) {
                                    Gson gson = new Gson();
                                    CollectBean bean = gson.fromJson(s, CollectBean.class);
                                    int c = bean.getMsg();
                                    if (style == null && a != 0) {
                                        Toast.makeText(context, "请选择商品类型", Toast.LENGTH_SHORT).show();
                                    } else if (style != null && a == 0) {
                                        Toast.makeText(context, "请选择商品数量", Toast.LENGTH_SHORT).show();
                                    } else if (style == null && a == 0) {
                                        Toast.makeText(context, "请选择商品类型和数量", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (c == 1) {
                                            String order = bean.getDetailUrl();
                                            SharedPreferences share = context.getSharedPreferences("Order", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = share.edit();
                                            editor.putString("order", order);
                                            editor.apply();
                                            Intent intent = new Intent(context, OrderActivity.class);
                                            context.startActivity(intent);
                                            dismiss();
                                        } else {
                                            Toast.makeText(context, "失败", Toast.LENGTH_SHORT).show();
                                        }
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


    //凑单页的构造方法
    public ShowPop(final Context context, final Handler handler, View contentView, String url, final String joinSingUrl) {
        super(contentView);
        //gridView绑定ID
        this.gvSize = (GridView) contentView.findViewById(R.id.gv_size);
        //点击关闭按键绑定ID
        this.mBtnClose = (ImageView) contentView.findViewById(R.id.btnClose);
        //点击关闭
        this.mBtnClose.setOnClickListener(new CloseClickEvent());
        //增加按钮
        this.btn_add = (Button) contentView.findViewById(R.id.btn_add_pop);
        //减少按钮
        this.btn_reduce = (Button) contentView.findViewById(R.id.btn_reduce_pop);
        //数量
        this.btn_num = (Button) contentView.findViewById(R.id.number_btn);
        //加入确定ID绑定
        this.btn_right = (Button) contentView.findViewById(R.id.btn_view_pop2_right);

        this.iv_pic = (ImageView) contentView.findViewById(R.id.iv_pic);
        this.tv_name = (TextView) contentView.findViewById(R.id.tv_name);
        // 设置属性
        setProperty();

        SharedPreferences share = context.getSharedPreferences("logIn", Context.MODE_PRIVATE);
        info = share.getString("login", "");
//        url_use = "Items.svc/addsingle/" + info + "/";

        a = 1;
        handler.sendMessage(handler.obtainMessage(21, a));
        RequestParams params = new RequestParams(url);
        Log.e("ngdvhra", url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                final SizeBean bean = gson.fromJson(s, SizeBean.class);
                sizeAdapter = new SizeAdapter(context, bean.getSTANDARDS(), R.layout.item_size);
                gvSize.setAdapter(sizeAdapter);
                x.image().bind(iv_pic, url1 + bean.getICOURL());
                tv_name.setText(bean.getTRADENAME() + "");

                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        a++;
                        btn_num.setText(a + "");
                        handler.sendMessage(handler.obtainMessage(21, a));
                        url_merge = url1 + joinSingUrl + "/" + bean.getCID() + "/" + a + "/" + style;
                    }
                });

                btn_reduce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (a == 1) {

                        } else {
                            a--;
                            btn_num.setText(a + "");
                            handler.sendMessage(handler.obtainMessage(21, a));
                            url_merge = url1 + joinSingUrl + "/" + bean.getCID() + "/" + a + "/" + style;
                        }
                    }
                });


                style = bean.getSTANDARDS().get(0).getSD_TB_ID();
                Log.e("qqqq", style);
                url_merge = url1 + joinSingUrl + "/" + bean.getCID() + "/" + a + "/" + style;

                gvSize.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        final SizeBean.STANDARDSBean item = (SizeBean.STANDARDSBean) parent.getItemAtPosition(position);
                        item.setNameIsSelect(!item.getNameIsSelect());

                        //选中规格传值到商品详情界面(DetailFragment)
                        if (item.getNameIsSelect() == true) {

                            style = bean.getSTANDARDS().get(position).getSD_TB_ID();
                            Log.e("qqqq", style);
                            url_merge = url1 + joinSingUrl + "/" + bean.getCID() + "/" + a + "/" + style;
                        }
//                        else {
//                            style = null;
//                        }
                        int b = bean.getSTANDARDS().size();
                        for (int i = 0; i < b; i++) {
                            if (i != position) {
                                SizeBean.STANDARDSBean standardsBean = bean.getSTANDARDS().get(i);
                                standardsBean.setNameIsSelect(false);
                            }
                        }
                        sizeAdapter.notifyDataSetChanged();
                    }
                });

                //点击确定
                btn_right.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("iouobzc", "gggggg");

                        if (style == null && a != 0) {
                            Toast.makeText(context, "请选择商品类型", Toast.LENGTH_SHORT).show();
                        } else if (style != null && a == 0) {
                            Toast.makeText(context, "请选择商品数量", Toast.LENGTH_SHORT).show();
                        } else if (style == null && a == 0) {
                            Toast.makeText(context, "请选择商品类型和数量", Toast.LENGTH_SHORT).show();
                        } else {
                            RequestParams pa = new RequestParams(url_merge);
                            Log.e("shuliang", url_merge);
                            x.http().get(pa, new CommonCallback<String>() {
                                @Override
                                public void onSuccess(String s) {
                                    Gson gson = new Gson();
                                    Log.e("36512356", "gggggg");
//                                    Type type = new TypeToken<List<MergeGetBean>>(){}.getType();
                                    MergeGetBean bean1 = gson.fromJson(s, MergeGetBean.class);
                                    Log.e("dfgge", bean1.getTOTAL() + "");
                                    handler.sendMessage(handler.obtainMessage(22, bean1.getDISCOUNT()));
                                    handler.sendMessage(handler.obtainMessage(23, bean1.getTOTAL()));
                                    Log.e("uoiuro", bean1.getTOTAL() + "");
                                    dismiss();
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

    /**
     * 设置属性
     */
    private void setProperty() {
        setAnimationStyle(R.style.AnimBottom);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setFocusable(true);
        ColorDrawable dw = new ColorDrawable(00000000);
        setBackgroundDrawable(dw);
    }

    /**
     * 关闭按钮点击
     */
    private class CloseClickEvent implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            dismiss();
        }
    }

    //带标签的筛选构造方法
    public ShowPop(View contentView, final Context context, String url) {
        super(contentView);

        //gridView绑定ID
        this.gvSize = (GridView) contentView.findViewById(R.id.gv_size);
        //点击关闭按键绑定ID
        this.mBtnClose = (ImageView) contentView.findViewById(R.id.btnClose);
        //点击关闭
        this.mBtnClose.setOnClickListener(new CloseClickEvent());
        //增加按钮
        this.btn_add = (Button) contentView.findViewById(R.id.btn_add_pop);
        //减少按钮
        this.btn_reduce = (Button) contentView.findViewById(R.id.btn_reduce_pop);
        //数量
        this.btn_num = (Button) contentView.findViewById(R.id.number_btn);
        //加入购物车的ID绑定
        this.btn_shopCar = (Button) contentView.findViewById(R.id.btn_size_shopCar);

        this.iv_pic = (ImageView) contentView.findViewById(R.id.iv_pic);
        this.tv_name = (TextView) contentView.findViewById(R.id.tv_name);
        this.btn_buy = (Button) contentView.findViewById(R.id.btn_size_buy);
        // 设置属性
        setProperty();

        SharedPreferences share = context.getSharedPreferences("logIn", Context.MODE_PRIVATE);
        info = share.getString("login", "");
        if (info == "") {
            url_all = url + "/" + 1;
        } else {
            url_all = url + "/" + info;
        }

        RequestParams params = new RequestParams(url_all);
        Log.d("showpop", url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                final SizeBean bean = gson.fromJson(s, SizeBean.class);
                x.image().bind(iv_pic, url1 + bean.getICOURL());
                tv_name.setText(bean.getTRADENAME() + "");
                sizeAdapter = new SizeAdapter(context, bean.getSTANDARDS(), R.layout.item_size);
                gvSize.setAdapter(sizeAdapter);

                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        a++;
                        btn_num.setText(a + "");

                    }
                });

                btn_reduce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (a == 1) {
                        } else {
                            a--;
                            btn_num.setText(a + "");

                        }
                    }
                });


                style = bean.getSTANDARDS().get(0).getSD_TB_ID();
                gvSize.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        final SizeBean.STANDARDSBean item = (SizeBean.STANDARDSBean) parent.getItemAtPosition(position);
                        item.setNameIsSelect(!item.getNameIsSelect());

                        //选中规格传值到商品详情界面(DetailFragment)
                        if (item.getNameIsSelect() == true) {

                            style = bean.getSTANDARDS().get(position).getSD_TB_ID();
                        } else {

                            style = null;
                        }
                        int b = bean.getSTANDARDS().size();
                        for (int i = 0; i < b; i++) {
                            if (i != position) {
                                SizeBean.STANDARDSBean standardsBean = bean.getSTANDARDS().get(i);
                                standardsBean.setNameIsSelect(false);
                            }
                        }
                        sizeAdapter.notifyDataSetChanged();


                    }
                });

                //点击加入购物车
                btn_shopCar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (info == "") {
                            Toast.makeText(context, "请您进行登录", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, LoginActivity.class);
                            intent.putExtra("me", 1);
                           dismiss();
                            context.startActivity(intent);
                        } else {
                            String cid = bean.getCID();
                            String ShopUrl;
                            Log.e("shuliang", a + "");
                            ShopUrl = cid + "/" + info + "/" + a + "/" + style;
                            Log.d("woded", ShopUrl);
                            RequestParams pa = new RequestParams(url1 + "Items.svc/additem/" + ShopUrl);
                            Log.e("aaasss", url1 + "Items.svc/additem/" + ShopUrl);
                            x.http().get(pa, new CommonCallback<String>() {
                                @Override
                                public void onSuccess(String s) {
                                    Gson gson = new Gson();
                                    CollectBean bean = gson.fromJson(s, CollectBean.class);
                                    int c = bean.getMsg();
                                    if (style == null && a != 0) {
                                        Toast.makeText(context, "请选择商品类型", Toast.LENGTH_SHORT).show();
                                    } else if (style != null && a == 0) {
                                        Toast.makeText(context, "请选择商品数量", Toast.LENGTH_SHORT).show();
                                    } else if (style == null && a == 0) {
                                        Toast.makeText(context, "请选择商品类型和数量", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (c == 1) {
                                            Toast.makeText(context, "加入购物车成功", Toast.LENGTH_SHORT).show();

                                            dismiss();
                                        } else {
                                            Toast.makeText(context, "加入失败", Toast.LENGTH_SHORT).show();
                                        }
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
                        }

                    }
                });

                //立即购买
                btn_buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (info == "") {
                            Toast.makeText(context, "请您进行登录", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, LoginActivity.class);
                            intent.putExtra("me", 1);
                            dismiss();
                            context.startActivity(intent);
                        } else {
                            String cid = bean.getCID();
                            String ShopUrl;
                            ShopUrl = cid + "/" + info + "/" + a + "/" + style;
                            RequestParams pa = new RequestParams(url1 + "/Items.svc/toworder/" + ShopUrl);
                            x.http().get(pa, new CommonCallback<String>() {
                                @Override
                                public void onSuccess(String s) {
                                    Gson gson = new Gson();
                                    CollectBean bean = gson.fromJson(s, CollectBean.class);
                                    int c = bean.getMsg();
                                    if (style == null && a != 0) {
                                        Toast.makeText(context, "请选择商品类型", Toast.LENGTH_SHORT).show();
                                    } else if (style != null && a == 0) {
                                        Toast.makeText(context, "请选择商品数量", Toast.LENGTH_SHORT).show();
                                    } else if (style == null && a == 0) {
                                        Toast.makeText(context, "请选择商品类型和数量", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (c == 1) {
                                            String order = bean.getDetailUrl();
                                            SharedPreferences share = context.getSharedPreferences("Order", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = share.edit();
                                            editor.putString("order", order);
                                            editor.apply();
                                            Intent intent = new Intent(context, OrderActivity.class);
                                            context.startActivity(intent);

                                        } else {
                                            Toast.makeText(context, "失败", Toast.LENGTH_SHORT).show();
                                        }
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


    //不带标签的筛选构造方法
    public ShowPop(final Context context, View contentView, String url) {
        super(contentView);
        //gridView绑定ID
        this.gvSize = (GridView) contentView.findViewById(R.id.gv_size);
        //点击关闭按键绑定ID
        this.mBtnClose = (ImageView) contentView.findViewById(R.id.btnClose);
        //点击关闭
        this.mBtnClose.setOnClickListener(new CloseClickEvent());
        //增加按钮
        this.btn_add = (Button) contentView.findViewById(R.id.btn_add_pop);
        //减少按钮
        this.btn_reduce = (Button) contentView.findViewById(R.id.btn_reduce_pop);
        //数量
        this.btn_num = (Button) contentView.findViewById(R.id.number_btn);
        //加入购物车的ID绑定
        this.btn_shopCar = (Button) contentView.findViewById(R.id.btn_size_shopCar);

        this.iv_pic = (ImageView) contentView.findViewById(R.id.iv_pic);
        this.tv_name = (TextView) contentView.findViewById(R.id.tv_name);
        this.btn_buy = (Button) contentView.findViewById(R.id.btn_size_buy);
        // 设置属性
        setProperty();

        SharedPreferences share = context.getSharedPreferences("logIn", Context.MODE_PRIVATE);
        info = share.getString("login", "");

        if (info == "") {
            url_all = url + "/" + 1;
        } else {
            url_all = url + "/" + info;
        }

        RequestParams params = new RequestParams(url_all);
        Log.d("showpop", url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                final SizeBean bean = gson.fromJson(s, SizeBean.class);
                x.image().bind(iv_pic, url1 + bean.getICOURL());
                tv_name.setText(bean.getTRADENAME() + "");
                sizeAdapter = new SizeAdapter(context, bean.getSTANDARDS(), R.layout.item_size);
                gvSize.setAdapter(sizeAdapter);

                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        a++;
                        btn_num.setText(a + "");

                    }
                });

                btn_reduce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (a == 1) {
                        } else {
                            a--;
                            btn_num.setText(a + "");

                        }
                    }
                });

                style = bean.getSTANDARDS().get(0).getSD_TB_ID();
                gvSize.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        final SizeBean.STANDARDSBean item = (SizeBean.STANDARDSBean) parent.getItemAtPosition(position);
                        item.setNameIsSelect(!item.getNameIsSelect());

                        //选中规格传值到商品详情界面(DetailFragment)
                        if (item.getNameIsSelect() == true) {

                            style = bean.getSTANDARDS().get(position).getSD_TB_ID();
                        } else {

                            style = null;
                        }
                        int b = bean.getSTANDARDS().size();
                        for (int i = 0; i < b; i++) {
                            if (i != position) {
                                SizeBean.STANDARDSBean standardsBean = bean.getSTANDARDS().get(i);
                                standardsBean.setNameIsSelect(false);
                            }
                        }
                        sizeAdapter.notifyDataSetChanged();


                    }
                });

                //点击加入购物车
                btn_shopCar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (info == "") {
                            Toast.makeText(context, "请您进行登录", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, LoginActivity.class);
                            intent.putExtra("me", 1);
                            dismiss();
                            context.startActivity(intent);

                        } else {
                            String cid = bean.getCID();
                            String ShopUrl;
                            Log.e("shuliang", a + "");
                            ShopUrl = cid + "/" + info + "/" + a + "/" + style;
                            Log.d("woded", ShopUrl);
                            RequestParams pa = new RequestParams(url1 + "Items.svc/additem/" + ShopUrl);
                            Log.e("aaasss", url1 + "Items.svc/additem/" + ShopUrl);
                            x.http().get(pa, new CommonCallback<String>() {
                                @Override
                                public void onSuccess(String s) {
                                    Gson gson = new Gson();
                                    CollectBean bean = gson.fromJson(s, CollectBean.class);
                                    int c = bean.getMsg();
                                    if (style == null && a != 0) {
                                        Toast.makeText(context, "请选择商品类型", Toast.LENGTH_SHORT).show();
                                    } else if (style != null && a == 0) {
                                        Toast.makeText(context, "请选择商品数量", Toast.LENGTH_SHORT).show();
                                    } else if (style == null && a == 0) {
                                        Toast.makeText(context, "请选择商品类型和数量", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (c == 1) {
                                            Toast.makeText(context, "加入购物车成功", Toast.LENGTH_SHORT).show();

                                            dismiss();
                                        } else {
                                            Toast.makeText(context, "加入失败", Toast.LENGTH_SHORT).show();
                                        }
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
                        }

                    }
                });

                //立即购买
                btn_buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (info == "") {
                            Toast.makeText(context, "请您进行登录", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, LoginActivity.class);
                            intent.putExtra("me", 1);
                            dismiss();
                            context.startActivity(intent);

                        } else {
                            String cid = bean.getCID();
                            String ShopUrl;
                            ShopUrl = cid + "/" + info + "/" + a + "/" + style;
                            RequestParams pa = new RequestParams(url1 + "/Items.svc/toworder/" + ShopUrl);
                            x.http().get(pa, new CommonCallback<String>() {
                                @Override
                                public void onSuccess(String s) {
                                    Gson gson = new Gson();
                                    CollectBean bean = gson.fromJson(s, CollectBean.class);
                                    int c = bean.getMsg();
                                    if (style == null && a != 0) {
                                        Toast.makeText(context, "请选择商品类型", Toast.LENGTH_SHORT).show();
                                    } else if (style != null && a == 0) {
                                        Toast.makeText(context, "请选择商品数量", Toast.LENGTH_SHORT).show();
                                    } else if (style == null && a == 0) {
                                        Toast.makeText(context, "请选择商品类型和数量", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (c == 1) {
                                            String order = bean.getDetailUrl();
                                            SharedPreferences share = context.getSharedPreferences("Order", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = share.edit();
                                            editor.putString("order", order);
                                            editor.apply();
                                            Intent intent = new Intent(context, OrderActivity.class);
                                            context.startActivity(intent);
                                        } else {
                                            Toast.makeText(context, "失败", Toast.LENGTH_SHORT).show();
                                        }
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


    //详情页的构造方法
    public ShowPop(final Context context, View contentView, final Handler handler, String url,int c) {
        super(contentView);

        //gridView绑定ID
        this.gvSize = (GridView) contentView.findViewById(R.id.gv_size);
        //点击关闭按键绑定ID
        this.mBtnClose = (ImageView) contentView.findViewById(R.id.btnClose);
        //点击关闭
        this.mBtnClose.setOnClickListener(new CloseClickEvent());
        //增加按钮
        this.btn_add = (Button) contentView.findViewById(R.id.btn_add_pop);
        //减少按钮
        this.btn_reduce = (Button) contentView.findViewById(R.id.btn_reduce_pop);
        //数量
        this.btn_num = (Button) contentView.findViewById(R.id.number_btn);
        //加入购物车的ID绑定
        this.btn_shopCar = (Button) contentView.findViewById(R.id.btn_size_shopCar);

        this.iv_pic = (ImageView) contentView.findViewById(R.id.iv_pic);
        this.tv_name = (TextView) contentView.findViewById(R.id.tv_name);
        this.btn_buy = (Button) contentView.findViewById(R.id.btn_size_buy);
        // 设置属性
        setProperty();

        SharedPreferences share = context.getSharedPreferences("logIn", Context.MODE_PRIVATE);
        info = share.getString("login", "");
        if (info == "") {
            url_all = url + "/" + 1;
        } else {
            url_all = url + "/" + info;
        }
        RequestParams params = new RequestParams(url_all);
        Log.d("showpop", url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                final SizeBean bean = gson.fromJson(s, SizeBean.class);
                x.image().bind(iv_pic, url1 + bean.getICOURL());
                tv_name.setText(bean.getTRADENAME() + "");
                sizeAdapter = new SizeAdapter(context, bean.getSTANDARDS(), R.layout.item_size);
                gvSize.setAdapter(sizeAdapter);

                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        a++;
                        btn_num.setText(a + "");
                        handler.sendMessage(handler.obtainMessage(11, a));
                    }
                });

                btn_reduce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (a == 1) {
                        } else {
                            a--;
                            btn_num.setText(a + "");
                            handler.sendMessage(handler.obtainMessage(11, a));
                        }
                    }
                });

//                final int nums = ;

                handler.sendMessage(handler.obtainMessage(12, bean.getSTANDARDS().get(0).getSTANDARD()));
                handler.sendMessage(handler.obtainMessage(10, bean.getSTANDARDS().get(0).getSD_TB_ID()));
                handler.sendMessage(handler.obtainMessage(11, a));
                style = bean.getSTANDARDS().get(0).getSD_TB_ID();


                gvSize.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        final SizeBean.STANDARDSBean item = (SizeBean.STANDARDSBean) parent.getItemAtPosition(position);
                        item.setNameIsSelect(!item.getNameIsSelect());

                        //选中规格传值到商品详情界面(DetailFragment)
                        if (item.getNameIsSelect() == true) {
                            handler.sendMessage(handler.obtainMessage(12, bean.getSTANDARDS().get(position).getSTANDARD()));
                            handler.sendMessage(handler.obtainMessage(10, bean.getSTANDARDS().get(position).getSD_TB_ID()));
                            handler.sendMessage(handler.obtainMessage(11, a));
                            style = bean.getSTANDARDS().get(position).getSD_TB_ID();
                        } else {
                            handler.sendMessage(handler.obtainMessage(12, null));
                            handler.sendMessage(handler.obtainMessage(10, null));
                            handler.sendMessage(handler.obtainMessage(11, 0));
                            style = null;
                        }

                        int b = bean.getSTANDARDS().size();
                        for (int i = 0; i < b; i++) {
                            if (i != position) {
                                SizeBean.STANDARDSBean standardsBean = bean.getSTANDARDS().get(i);
                                standardsBean.setNameIsSelect(false);
                            }
                        }
                        sizeAdapter.notifyDataSetChanged();


                    }
                });






                //点击加入购物车
                btn_shopCar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (info == "") {
                            Toast.makeText(context, "请您进行登录", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, LoginActivity.class);
                            intent.putExtra("me", 1);
                            dismiss();
                            context.startActivity(intent);
                        } else {
                            String cid = bean.getCID();
                            String ShopUrl;
                            Log.e("shuliang", a + "");
                            ShopUrl = cid + "/" + info + "/" + a + "/" + style;
                            Log.d("woded", ShopUrl);
                            RequestParams pa = new RequestParams(url1 + "Items.svc/additem/" + ShopUrl);
                            Log.e("aaasss", url1 + "Items.svc/additem/" + ShopUrl);
                            x.http().get(pa, new CommonCallback<String>() {
                                @Override
                                public void onSuccess(String s) {
                                    Gson gson = new Gson();
                                    CollectBean bean = gson.fromJson(s, CollectBean.class);
                                    int c = bean.getMsg();
                                    if (style == null && a != 0) {
                                        Toast.makeText(context, "请选择商品类型", Toast.LENGTH_SHORT).show();
                                    } else if (style != null && a == 0) {
                                        Toast.makeText(context, "请选择商品数量", Toast.LENGTH_SHORT).show();
                                    } else if (style == null && a == 0) {
                                        Toast.makeText(context, "请选择商品类型和数量", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (c == 1) {
                                            Toast.makeText(context, "加入购物车成功", Toast.LENGTH_SHORT).show();
                                            handler.sendMessage(handler.obtainMessage(13, a));
                                            dismiss();
                                        } else {
                                            Toast.makeText(context, "加入失败", Toast.LENGTH_SHORT).show();
                                        }
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
                        }

                    }
                });

                btn_buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (info == "") {
                            Toast.makeText(context, "请您进行登录", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, LoginActivity.class);
                            intent.putExtra("me", 1);
                            dismiss();
                            context.startActivity(intent);
                        } else {
                            String cid = bean.getCID();
                            String ShopUrl;
                            ShopUrl = cid + "/" + info + "/" + a + "/" + style;
                            RequestParams pa = new RequestParams(url1 + "/Items.svc/toworder/" + ShopUrl);
                            x.http().get(pa, new CommonCallback<String>() {
                                @Override
                                public void onSuccess(String s) {
                                    Gson gson = new Gson();
                                    CollectBean bean = gson.fromJson(s, CollectBean.class);
                                    int c = bean.getMsg();
                                    if (style == null && a != 0) {
                                        Toast.makeText(context, "请选择商品类型", Toast.LENGTH_SHORT).show();
                                    } else if (style != null && a == 0) {
                                        Toast.makeText(context, "请选择商品数量", Toast.LENGTH_SHORT).show();
                                    } else if (style == null && a == 0) {
                                        Toast.makeText(context, "请选择商品类型和数量", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (c == 1) {
                                            String order = bean.getDetailUrl();
                                            SharedPreferences share = context.getSharedPreferences("Order", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = share.edit();
                                            editor.putString("order", order);
                                            editor.apply();
                                            Intent intent = new Intent(context, OrderActivity.class);
                                            context.startActivity(intent);
                                            dismiss();
                                        } else {
                                            Toast.makeText(context, "失败", Toast.LENGTH_SHORT).show();
                                        }
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

}
