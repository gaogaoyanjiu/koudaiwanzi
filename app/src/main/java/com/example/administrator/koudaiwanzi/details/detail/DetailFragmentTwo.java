package com.example.administrator.koudaiwanzi.details.detail;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.BaseFragment;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.details.DetailsActivity;
import com.example.administrator.koudaiwanzi.details.detail.size.ShowPop;
import com.example.administrator.koudaiwanzi.dialog.CustomProgressDialog;
import com.example.administrator.koudaiwanzi.person.login.LoginActivity;
import com.example.administrator.koudaiwanzi.refresh.MyScrollListView;
import com.example.administrator.koudaiwanzi.shopcar.OrderActivity;
import com.example.administrator.koudaiwanzi.shopcartwo.ShopActivity;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/17.
 */

@ContentView(R.layout.fragment_detail)
public class DetailFragmentTwo extends BaseFragment{

    private String cid;
    private String shopUrl;
    private DetailPictureAdapter detailPictureAdapter;
    private ImageView iv;
    private List<View> data;
    private String info;
    private int num;
    private String str;
    private String style;
    private String url1 = MyUrl.url;
    private GridViewAdapter gvAdapter;
    private String url;
    private Handler handler1;
    private int shopNum;
    private Adapter adapter;

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

    private String sizeAcolor;
    private String UID = "1";
    //购物车的数量
    @ViewInject(R.id.tv_detail_num)
    private TextView tv_num;

    //整个购物车的你布局
    @ViewInject(R.id.rl_detail_sc)
    private RelativeLayout rl_detail;

    //显示商品的数量
    @ViewInject(R.id.tv_product_shuliang)
    private TextView tv_detail_nums;

    //显示选择的颜色和尺码
    @ViewInject(R.id.tv_detail_size)
    private TextView tv_detail_size;

    //绑定viewPager----仿ios版淘宝特效
    @ViewInject(R.id.viewPager_detail)
    private ViewPager viewPager;

    //动态添加的布局的LinearLayout
    @ViewInject(R.id.ll_detail)
    private MyScrollListView myScrollListView;

    //最外层的linearLayout
    @ViewInject(R.id.ll_hide)
    private LinearLayout ll_hide;

    //绑定选择尺寸、颜色的ID
    @ViewInject(R.id.rl_detail)
    private RelativeLayout rl_size;

    //绑定商品名称
    @ViewInject(R.id.tv_detail_name)
    private TextView tv_name;

    //绑定商品折扣
    @ViewInject(R.id.tv_detail_discount)
    private TextView tv_discount;

    //绑定商品现价
    @ViewInject(R.id.tv_product_now)
    private TextView tv_now;

    //绑定商品现价
    @ViewInject(R.id.tv_product_before)
    private TextView tv_before;

    @ViewInject(R.id.tv_detail_freight)
    private TextView tv_freight;

    @ViewInject(R.id.tv_detail_send)
    private TextView tv_send;

    @ViewInject(R.id.tv_detail_change)
    private TextView tv_change;

    @ViewInject(R.id.iv_product_shoucang)
    private ImageView iv_collect;

    @ViewInject(R.id.iv_product_shoucang1)
    private ImageView iv_collect1;

    //加入购物车按钮
    @ViewInject(R.id.rl_detail_addShopCar)
    private RelativeLayout rl_add;

    @ViewInject(R.id.gridView_detail)
    private GridView gv_detail;

    @ViewInject(R.id.rl_detail_buy)
    private RelativeLayout rl_buy;

    @ViewInject(R.id.ll_detail2)
    private LinearLayout ll_detail2;
    @ViewInject(R.id.ll_detail1)
    private LinearLayout ll_detail1;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences share = getActivity().getSharedPreferences("logIn", Context.MODE_PRIVATE);
        info = share.getString("login", "");
        data = new ArrayList<>();
        viewPager.setFocusable(true);
        viewPager.setFocusableInTouchMode(true);
        viewPager.requestFocus();
        adapter = new Adapter(getActivity());
        gvAdapter = new GridViewAdapter(getActivity());
        final CustomProgressDialog dialog = new CustomProgressDialog(getActivity(), R.style.CommProgressDialog, "加载中...", R.anim.frame2);
        dialog.show();
        Intent intent = getActivity().getIntent();
        final String getUrl = intent.getStringExtra("key");
        rl_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShopActivity.class);
//                intent.putExtra("Merge", "3");
                startActivity(intent);
            }
        });
        if (info.equals("")) {
            url = url1 + getUrl + "/" + 1;
        } else {
            url = url1 + getUrl + "/" + info;
        }
        Log.e("asszxcs", url);
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String o) {
                Gson gson = new Gson();
                final DetailBean bean = gson.fromJson(o, DetailBean.class);

                if (bean.getISOUTSTOCK() ==1){
                    ll_detail2.setVisibility(View.VISIBLE);
                    ll_detail1.setVisibility(View.GONE);
                }else {
                    ll_detail2.setVisibility(View.GONE);
                    ll_detail1.setVisibility(View.VISIBLE);
                }

                //商品点击量计算
                RequestParams params = new RequestParams( url1+"Users.svc/clickrate/"+info+"/"+bean.getCID());
                x.http().get(params, new CommonCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Log.d("wodxasxzawq", s);

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
                /**
                 *
                 */

                //判断登录购物车的数量  没有登录购物车数量就是0
                if (info.equals("")){
                    tv_num.setText(0 + "");
                }else {
                    tv_num.setText(bean.getPAYCOUNT() + "");
                }

                Log.e("ssss", bean.getPAYCOUNT()+"" );

                shopNum = bean.getPAYCOUNT();


                //尺寸、颜色的监听
                rl_size.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 标题和主页开始播放动画
                        ll_hide.startAnimation(mScalInAnimation1);

                        // 弹出sPopupWindow
                        mShowPopup = new ShowPop(getActivity(), mLayoutInflater.inflate(
                                R.layout.view_pop, null), handler, url);
                        Log.e("llll", url1 + bean.getCOMMENT() + "/" + 1);
                        mShowPopup.setOnDismissListener(new OnPopupDismissListener());
                        mShowPopup.showAtLocation(getActivity().findViewById(R.id.ll_hide),
                                Gravity.CENTER, 0, 0);
                    }
                });


                //拉取商品名称
                String name = bean.getTRADENAME();
                tv_name.setText(name);
                //拉取商品折扣
                double discount = bean.getDISCOUNT();
                double now = bean.getBARGAIN();
                double before = bean.getPRICE();
                if (discount == 0) {
                    tv_now.setText("￥" + before);
                    tv_discount.setVisibility(View.GONE);
                    tv_before.setVisibility(View.GONE);
                } else {
                    tv_discount.setText(discount + "折");
                    tv_now.setText("￥" + now);
                    tv_before.setText("￥" + before);
                }


                tv_freight.setText(bean.getPOSTAGE() + "");
                tv_send.setText(bean.getSTANDARDS().get(0).getSTANDARD()+"");
                tv_change.setText(bean.getNOTES());

                ImageOptions options = new ImageOptions.Builder()
                        //图片大小
                        .setSize(300, 250)
                        // 是否忽略GIF格式的图片
                        .setIgnoreGif(false)
                        // 图片缩放模式
//                        .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                        // 得到ImageOptions对象
                        .build();

                int ima = bean.getIMGS().size();
                for (int i = 0; i < ima; i++) {
                    View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_detail, null);
                    iv = (ImageView) view.findViewById(R.id.iv_detail_picture);
                    Log.e("eiiyi", url1 + bean.getIMGS().get(i).getIMGURL());

                    //FRESCO缓存图片
//                    Uri imageUri = Uri.parse(url1 + bean.getIMGS().get(i).getIMGURL());
//                    ImageRequest request = ImageRequestBuilder.newBuilderWithSource(imageUri)
//                            .setResizeOptions(new ResizeOptions(400, 300))
//                            .build();
//                    DraweeController controller = Fresco.newDraweeControllerBuilder()
//                            .setOldController(iv.getController())
//                            .setImageRequest(request)
//                            .build();
//                    iv.setController(controller);
                    x.image().bind(iv, url1 + bean.getIMGS().get(i).getIMGURL(), options);
                    data.add(view);
                }


//                 根据MSG判断是否收藏 =0时 为未收藏 显黑心 =1时 为收藏 显红心
                int god = bean.getMsg();
                if (god == 0) {
                    iv_collect.setVisibility(View.VISIBLE);
                    iv_collect1.setVisibility(View.INVISIBLE);
                } else {
                    iv_collect.setVisibility(View.INVISIBLE);
                    iv_collect1.setVisibility(View.VISIBLE);
                }

                //点黑心收藏的监听
                iv_collect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (info.equals("")) {
                            Toast.makeText(getActivity(), "请您进行登录", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                            intent.putExtra("me", 1);
                            intent.putExtra("fanhui", getUrl);
                            startActivity(intent);
                            getActivity().finish();
                        } else {
                            if (bean.getADDFAV() == null) {
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);
                            } else {
                                String die = bean.getADDFAV();
                                RequestParams pa = new RequestParams(url1 + die);
                                x.http().get(pa, new CommonCallback<String>() {
                                    @Override
                                    public void onSuccess(String s) {
                                        Gson gson = new Gson();
                                        CollectBean bean = gson.fromJson(s, CollectBean.class);
                                        int a = bean.getMsg();

                                        if (a == 1) {
                                            iv_collect.setVisibility(View.INVISIBLE);
                                            iv_collect1.setVisibility(View.VISIBLE);
                                            Toast.makeText(getActivity(), "收藏成功", Toast.LENGTH_SHORT).show();
                                        } else {
                                            iv_collect.setVisibility(View.VISIBLE);
                                            iv_collect1.setVisibility(View.INVISIBLE);
                                            Toast.makeText(getActivity(), "收藏失败", Toast.LENGTH_SHORT).show();
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
                    }
                });

                //点红心取消收藏的监听
                iv_collect1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (info.equals("")) {
                            Toast.makeText(getActivity(), "请您进行登录", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                            intent.putExtra("me", 1);
                            intent.putExtra("fanhui", getUrl);
                            startActivity(intent);
                            getActivity().finish();

                        } else {

                            String die = bean.getDELFAV();
                            RequestParams pa = new RequestParams(url1 + die);
                            x.http().get(pa, new CommonCallback<String>() {
                                @Override
                                public void onSuccess(String s) {
                                    Gson gson = new Gson();
                                    CollectBean bean = gson.fromJson(s, CollectBean.class);
                                    int a = bean.getMsg();

                                    if (a == 1) {
                                        iv_collect.setVisibility(View.VISIBLE);
                                        iv_collect1.setVisibility(View.INVISIBLE);
                                        Toast.makeText(getActivity(), "取消收藏", Toast.LENGTH_SHORT).show();
                                    } else {
                                        iv_collect.setVisibility(View.INVISIBLE);
                                        iv_collect1.setVisibility(View.VISIBLE);
                                        Toast.makeText(getActivity(), "取消失败", Toast.LENGTH_SHORT).show();
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

                cid = bean.getCID();
                style = str;
                //点击加入购物车
                rl_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            Log.e("shuliang", num + "");
                            shopUrl = cid + "/" + info + "/" + num + "/" + style;
                            Log.d("woded", url1 + "Items.svc/additem/" + shopUrl);
                            if (style == null && num != 0) {
                                ll_hide.startAnimation(mScalInAnimation1);

                                // 弹出sPopupWindow
                                mShowPopup = new ShowPop(getActivity(), mLayoutInflater.inflate(
                                        R.layout.view_pop, null), handler, url);
                                Log.e("llll", url1 + bean.getCOMMENT() + "/" + 1);
                                mShowPopup.setOnDismissListener(new OnPopupDismissListener());
                                mShowPopup.showAtLocation(getActivity().findViewById(R.id.ll_hide),
                                        Gravity.CENTER, 0, 0);

                            } else if (num == 0 && style != null) {
                                ll_hide.startAnimation(mScalInAnimation1);

                                // 弹出sPopupWindow
                                mShowPopup = new ShowPop(getActivity(), mLayoutInflater.inflate(
                                        R.layout.view_pop, null), handler, url);
                                Log.e("llll", url1 + bean.getCOMMENT() + "/" + 1);
                                mShowPopup.setOnDismissListener(new OnPopupDismissListener());
                                mShowPopup.showAtLocation(getActivity().findViewById(R.id.ll_hide),
                                        Gravity.CENTER, 0, 0);

                            } else if (style == null && num == 0) {
                                ll_hide.startAnimation(mScalInAnimation1);

                                // 弹出sPopupWindow
                                mShowPopup = new ShowPop(getActivity(), mLayoutInflater.inflate(
                                        R.layout.view_pop, null), handler, url);
                                Log.e("llll", url1 + bean.getCOMMENT() + "/" + 1);
                                mShowPopup.setOnDismissListener(new OnPopupDismissListener());
                                mShowPopup.showAtLocation(getActivity().findViewById(R.id.ll_hide),
                                        Gravity.CENTER, 0, 0);

                            } else {
                                RequestParams pa = new RequestParams(url1 + "Items.svc/additem/" + shopUrl);
                                x.http().get(pa, new CommonCallback<String>() {
                                    @Override
                                    public void onSuccess(String s) {
                                        Gson gson = new Gson();
                                        CollectBean bean = gson.fromJson(s, CollectBean.class);
                                        int a = bean.getMsg();

                                        if (a == 1) {
                                            Toast.makeText(getActivity(), "加入购物车成功", Toast.LENGTH_SHORT).show();

                                            shopNum += num;
                                            tv_num.setText(shopNum + "");
                                        } else {
                                            Toast.makeText(getActivity(), "加入失败", Toast.LENGTH_SHORT).show();
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
                rl_buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                            shopUrl = cid + "/" + info + "/" + num + "/" + style;
                            if (style == null && num != 0) {
                                ll_hide.startAnimation(mScalInAnimation1);
                                // 弹出sPopupWindow
                                mShowPopup = new ShowPop(getActivity(), mLayoutInflater.inflate(
                                        R.layout.view_pop, null), handler, url);
                                mShowPopup.setOnDismissListener(new OnPopupDismissListener());
                                mShowPopup.showAtLocation(getActivity().findViewById(R.id.ll_hide),
                                        Gravity.CENTER, 0, 0);

                            } else if (num == 0 && style != null) {
                                ll_hide.startAnimation(mScalInAnimation1);

                                // 弹出sPopupWindow
                                mShowPopup = new ShowPop(getActivity(), mLayoutInflater.inflate(
                                        R.layout.view_pop, null), handler, url);
                                mShowPopup.setOnDismissListener(new OnPopupDismissListener());
                                mShowPopup.showAtLocation(getActivity().findViewById(R.id.ll_hide),
                                        Gravity.CENTER, 0, 0);

                            } else if (style == null && num == 0) {
                                ll_hide.startAnimation(mScalInAnimation1);
                                // 弹出sPopupWindow
                                mShowPopup = new ShowPop(getActivity(), mLayoutInflater.inflate(
                                        R.layout.view_pop, null), handler, url);
                                mShowPopup.setOnDismissListener(new OnPopupDismissListener());
                                mShowPopup.showAtLocation(getActivity().findViewById(R.id.ll_hide),
                                        Gravity.CENTER, 0, 0);

                            } else {
                                RequestParams pa = new RequestParams(url1 + "/Items.svc/toworder/" + shopUrl);
                                x.http().get(pa, new CommonCallback<String>() {
                                    @Override
                                    public void onSuccess(String s) {
                                        Gson gson = new Gson();
                                        CollectBean bean = gson.fromJson(s, CollectBean.class);
                                        int a = bean.getMsg();

                                        if (a == 1) {
                                            String order = bean.getDetailUrl();
                                            SharedPreferences share = getActivity().getSharedPreferences("Order", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = share.edit();
                                            editor.putString("order", order);
                                            editor.apply();
                                            Intent intent = new Intent(getActivity(), OrderActivity.class);
                                            startActivity(intent);
//                                            Toast.makeText(getActivity(), "成功", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
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

                adapter.addData(data);
                viewPager.setAdapter(adapter);

                //相关推荐添加数据
                gvAdapter.addData(bean.getOTHER());
                gv_detail.setAdapter(gvAdapter);
                gv_detail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), DetailsActivity.class);
                        intent.putExtra("key", bean.getOTHER().get(position).getDetailUrl());
                        startActivity(intent);
                        getActivity().finish();

                    }
                });


                detailPictureAdapter = new DetailPictureAdapter(getActivity(), bean.getPICS(), R.layout.detail_picture);
                //动态添加图片---商品的图片
//                    for (int i = 0; i < bean.getPICS().size(); i++) {
//                        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.item_detail_fragment,null);
//
//                        iv2 = (ImageView) view1.findViewById(R.id.iv_fragment_detail);
//                        x.image().bind(iv2, url1 + bean.getPICS().get(i).getIMGURL());
//                        myScrollListView.addView(view1);
//                    }
                myScrollListView.setAdapter(detailPictureAdapter);
                handler1.sendEmptyMessage(0);
            }


            @Override
            public void onError(Throwable throwable, boolean b) {
                handler1.sendEmptyMessage(0);
                Toast.makeText(getActivity(), "当前网络不稳定", Toast.LENGTH_SHORT).show();
            }

            @Override

            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });

        handler1 = new Handler() {
            public void handleMessage(Message msg) {
                dialog.cancel();
            }
        };


        mLayoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // 动画初始化
        mScalInAnimation1 = AnimationUtils.loadAnimation(getActivity(),
                R.anim.root_in);
        mScalInAnimation2 = AnimationUtils.loadAnimation(getActivity(),
                R.anim.root_in2);
        mScalOutAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.root_out);
        mTranInAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.title_in);
        mTranOutAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.title_out);


        // 注册事件回调
//        rl_size.setOnClickListener(new BtnClickEvent());
        mScalInAnimation1.setAnimationListener(new ScalInAnimation1());
    }

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

//    class loadDateThreah implements Runnable {
//
//        @Override
//        public void run() {
//
//            //    ....这里是联网下载数据，下载完成后执行下列的方法，handlder会调用前面覆写的handleMessage方法，在那里关闭加载提示框...
//        }
//    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 10) {
                str = (String) msg.obj;
            } else if (msg.what == 11) {
                num = (int) msg.obj;
                tv_detail_nums.setText(num + "件");
            } else if (msg.what == 12) {
                sizeAcolor = (String) msg.obj;
                if (sizeAcolor == null) {
                    sizeAcolor = "请选择规格";
                    tv_detail_size.setText("  " + sizeAcolor);
                } else {
                    tv_detail_size.setText("  " + sizeAcolor);
                }

            } else if (msg.what == 13) {
                int q = (int) msg.obj;
                shopNum += q;
                tv_num.setText(shopNum + "");
            }
        }
    };

}
