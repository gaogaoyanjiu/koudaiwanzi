package com.example.administrator.koudaiwanzi.rank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.BaseFragment;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.details.DetailsActivity;
import com.example.administrator.koudaiwanzi.details.detail.size.ShowPop;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/29.
 */
@ContentView(R.layout.fragment_rank_two)
public class RankTwoActivity extends AppCompatActivity {

    @ViewInject(R.id.tv_rank_num)
    private TextView tv_num;

    @ViewInject(R.id.brand_listview)
    private ListView listView;

    @ViewInject(R.id.ll_brand)
    private LinearLayout ll_brand;

    private ImageView iv_head;
    private String UID;

    private View view;
    private String url1 = MyUrl.url;
    private String shopUrl;
    private String getUrl;
    private RankTwoAdapter adapter;
    private RankAdapter rankAdapter;


    private String url;

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

    private int shopNum;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        view = LayoutInflater.from(RankTwoActivity.this).inflate(R.layout.acitivity_head_rank, null);
        iv_head = (ImageView) view.findViewById(R.id.iv_head_top);
        listView.addHeaderView(view);
        SharedPreferences share = getSharedPreferences("logIn", Context.MODE_PRIVATE);
        UID = share.getString("login", "");

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
        mScalInAnimation1.setAnimationListener(new ScalInAnimation1());

        adapter = new RankTwoAdapter(this.getApplication(),handler);

        if (UID.equals("")){
            url = url1 + "/Items.svc/newrankingall/1";
        }else {
            url = url1 + "/Items.svc/newrankingall/" + UID;

        }

        Log.d("sdasdasdaw", url);
        RequestParams params  = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                final RankBean bean = gson.fromJson(s,RankBean.class);
                Log.d("sdasdasdaw1", url);

                adapter.addData(bean.getTOPITEMS());

//                rankAdapter = new RankAdapter(RankTwoActivity.this, bean.getTOPITEMS(), R.layout.item_top, handler);

                listView.setAdapter(adapter);

                //判断登录购物车的数量  没有登录购物车数量就是0
                if (UID.equals("")){
                    tv_num.setText(0 + "");
                }else {
                    tv_num.setText(bean.getPAYCOUNT() + "");
                    shopNum = bean.getPAYCOUNT();
                }

                x.image().bind(iv_head,url1+bean.getTOPBANNER().get(0).getIMGURL());

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(RankTwoActivity.this, DetailsActivity.class);
                        intent.putExtra("key", bean.getTOPITEMS().get((int) parent.getItemIdAtPosition(position)).getDetailUrl());
                        startActivity(intent);
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


    @Event(R.id.iv_rank_back)
    private void imaClick(View view) {
        finish();
    }


    private class OnPopupDismissListener implements
            android.widget.PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // 标题和主页开始播放动画
            ll_brand.startAnimation(mScalOutAnimation);
        }
    }

    public class ScalInAnimation1 implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            ll_brand.startAnimation(mScalInAnimation2);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 30) {
                shopUrl = (String) msg.obj;


                // 标题和主页开始播放动画
                ll_brand.startAnimation(mScalInAnimation1);

                // 弹出sPopupWindow
                mShowPopup = new ShowPop(RankTwoActivity.this, mLayoutInflater.inflate(
                        R.layout.view_pop, null),handler2,url1 + shopUrl,1);

                mShowPopup.setOnDismissListener(new OnPopupDismissListener());
                mShowPopup.showAtLocation(RankTwoActivity.this.findViewById(R.id.ll_brand),
                        Gravity.CENTER, 0, 0);
            }

        }
    };
    private Handler handler2 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 13){
                int q = (int) msg.obj;
                shopNum += q;
                tv_num.setText(shopNum + "");
            }
            super.handleMessage(msg);
        }
    };
}
