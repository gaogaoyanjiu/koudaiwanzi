package com.example.administrator.koudaiwanzi.shopcar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.MainActivity;
import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.details.detail.size.ShowPop;
import com.example.administrator.koudaiwanzi.shopcartwo.ShopActivity;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


/**
 * Created by Administrator on 2016/7/6.
 * 凑单页面
 */

@ContentView(R.layout.fragment_merge)
public class MergeActivity extends AppCompatActivity {
    private String a;
    private Handler handler2;
    private Context context;
    private int nums = 1;
    private double price2, priceAll;
    private MergerAdapter adapter;
    private String url;
    private String num;
    private String joinSingUrl;

    @ViewInject(R.id.merge_gv)
    private GridView gv;

    @ViewInject(R.id.finish_merge)
    private TextView tv_finish;

    //原价
    @ViewInject(R.id.tv_price_merge)
    private TextView tv_price_merge;

    //优惠
    @ViewInject(R.id.tv_discount_merge)
    private TextView tv_discount_merge;

    //最外层的linerlayout布局
    @ViewInject(R.id.ll_merge)
    private LinearLayout ll_merge;

    @ViewInject(R.id.tv_merge_num1)
    private TextView tv_mun;

    @ViewInject(R.id.iv_back_merge)
    private ImageView iv_back;


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

    private String UID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        final Intent intent = getIntent();
        a = intent.getStringExtra("merge");
        num = intent.getStringExtra("type");

        adapter = new MergerAdapter(this, handler);
        SharedPreferences share = getSharedPreferences("logIn", Context.MODE_PRIVATE);
        UID = share.getString("login", "");

        if (num.equals("4")){
            //购物车中点击凑单
            url = MyUrl.url + "Items.svc/join/" + UID + "/" + a;
        }else {
            //订单中点击凑单
            url = MyUrl.url + a;
        }

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
        mScalInAnimation1.setAnimationListener(new ScalInAnimation1());


        RequestParams params = new RequestParams(url);
        Log.d("woyaokanyikan", url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                final MergeBean bean = gson.fromJson(s, MergeBean.class);
                Log.d("woyaokanyikan", bean.getPAYCOUNT() + "");
                tv_mun.setText(bean.getPAYCOUNT() + "");
//                bean.getDITEMS().get(0).getDetailUrl();
                //凑单商品的string
                joinSingUrl = bean.getJOINSINGURL();

                adapter.addData(bean.getDITEMS());
                gv.setAdapter(adapter);

                //点击完成凑单
                tv_finish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String url_right = MyUrl.url + bean.getFINSHSINGURL();
                        Log.d("woqunidaye", url_right);
                        RequestParams params = new RequestParams(url_right);
                        x.http().get(params, new CommonCallback<String>() {
                            @Override
                            public void onSuccess(String s) {
                                Log.e("qweqwe", s);

                                if (num.equals("2")){
                                    Intent intent = new Intent(MergeActivity.this, OrderActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    Intent intent1 = new Intent("com.example.administrator.koudaiwanzi.shopca.broadcast");
                                    LocalBroadcastManager.getInstance(MergeActivity.this).sendBroadcast(intent1);
                                    finish();
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
                });

                iv_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String a = MyUrl.url + bean.getDELETEDINGURL();
                        Log.d("woqunidaye", a);
                        RequestParams params = new RequestParams(a);
                        x.http().get(params, new CommonCallback<String>() {
                            @Override
                            public void onSuccess(String s) {
                                finish();
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
     * popupwindow消失的回调
     */
    private class OnPopupDismissListener implements
            android.widget.PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // 标题和主页开始播放动画
            ll_merge.startAnimation(mScalOutAnimation);
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
            ll_merge.startAnimation(mScalInAnimation2);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    //适配器的handler
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 18) {
                String urlllll = (String) msg.obj;
                // 标题和主页开始播放动画
                ll_merge.startAnimation(mScalInAnimation1);

                // 弹出sPopupWindow
                mShowPopup = new ShowPop(MergeActivity.this, handler, mLayoutInflater.inflate(
                        R.layout.view_pop2, null), urlllll + UID, joinSingUrl);
                Log.e("llll", urlllll + UID);
                mShowPopup.setOnDismissListener(new OnPopupDismissListener());
                mShowPopup.showAtLocation(MergeActivity.this.findViewById(R.id.ll_merge),
                        Gravity.CENTER, 0, 0);
            } else if (msg.what == 22) {
                double price = (double) msg.obj;
//                price2 += price * nums;
                tv_discount_merge.setText("已优惠" + price + "元");
            } else if (msg.what == 23) {
                double priceDiscount = (double) msg.obj;
//                priceAll += priceDiscount * nums;
                tv_price_merge.setText(priceDiscount + "元");
            } else if (msg.what == 21) {
                nums = (int) msg.obj;
            }
        }
    };


}

