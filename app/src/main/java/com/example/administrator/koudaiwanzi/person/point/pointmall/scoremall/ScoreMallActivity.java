package com.example.administrator.koudaiwanzi.person.point.pointmall.scoremall;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.person.PersonBean;
import com.example.administrator.koudaiwanzi.person.login.LoginActivity;
import com.example.administrator.koudaiwanzi.person.point.exchangticket.ExchangeActivity;
import com.example.administrator.koudaiwanzi.person.point.pointmall.RewardActivity;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
@ContentView(R.layout.activity_score_mall)
public class ScoreMallActivity extends AppCompatActivity {
    private List<Fragment> data;
    private ScoreMallAdapter adapter;
    private String url1 = MyUrl.url;
    private String UID = "1";
    private String url;

    @ViewInject(R.id.tv_mall_score)
    private TextView tv_mall_score;

    @ViewInject(R.id.tl_score_mall)
    private TabLayout tl_score;

    @ViewInject(R.id.tv_exchange_ticket)
    private TextView tv_ticket;

    @ViewInject(R.id.vp_score_mall)
    private ViewPager vp_score;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        SharedPreferences share = getSharedPreferences("logIn",MODE_PRIVATE);
        UID = share.getString("login", "");

        if (UID.equals("")||UID == null || UID == ""){
            Intent intent = new Intent(ScoreMallActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        }else {
            url = url1 + "Users.svc/useritem/" + UID;
        }
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                final PersonBean p = gson.fromJson(s, PersonBean.class);

                tv_mall_score.setText(p.getPOINT() + "");
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






        data = new ArrayList<>();
        data.add(new ScoreGiftFragment());
        data.add(new ScoreServerFragment());
        //tab不能滚动，平分屏幕宽度
        //tabLayout.setTabMode(TabLayout.MODE_FIXED);
        // 设置TabLayout模式
        //tab可以滚动，tab宽度根据内容自动缩放
        //tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        adapter = new ScoreMallAdapter(data,ScoreMallActivity.this,getSupportFragmentManager());
        vp_score.setAdapter(adapter);
        tl_score.setupWithViewPager(vp_score);
        for (int i = 0; i < 2; i++) {
            TabLayout.Tab tab = tl_score.getTabAt(i);
            if (tab != null){
                tab.setCustomView(adapter.getTabView(i));
            }
        }
        //用getTabView的方法必须写这句话
        vp_score.setCurrentItem(0);
        //获得焦点
        tl_score.setFocusable(true);
        tl_score.setFocusableInTouchMode(true);
        tl_score.requestFocus();
        tv_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreMallActivity.this, ExchangeActivity.class);
                startActivity(intent);
            }
        });
    }


}
