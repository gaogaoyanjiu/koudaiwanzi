package com.example.administrator.koudaiwanzi.person.point.pointmall;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.person.login.LoginActivity;
import com.example.administrator.koudaiwanzi.person.point.exchangticket.ExchangeActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Administrator on 2016/9/21.
 * 参加抽奖的activity
 */

@ContentView(R.layout.activity_reward)
public class RewardActivity extends AppCompatActivity implements View.OnClickListener , TigerMachineFragment.CallBackValue {
    //老虎机
    @ViewInject(R.id.rb_teger)
    private RadioButton rb_tiger;
    //摇一摇
    @ViewInject(R.id.rb_yao)
    private RadioButton rb_yao;

    @ViewInject(R.id.tv_mall_score_reward)
    private TextView tv_score;

    private TigerMachineFragment fragment1;
    private YaoFragment fragment2;

    private String UID = "";
    private static String url ;
    //跳转到积分兑换页面
    @Event(R.id.tv_exchange_ticket_reward)
    private void textClick(View view){
        Intent intent = new Intent(RewardActivity.this, ExchangeActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        url = getIntent().getStringExtra("tiger");
        SharedPreferences share = getSharedPreferences("logIn", Context.MODE_PRIVATE);
        UID = share.getString("login", "");

        if(UID == "" || UID.equals("")){
            Intent intent = new Intent(RewardActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else {
            rb_tiger.setOnClickListener(this);
            rb_yao.setOnClickListener(this);
            select(0);
        }
    }

    private void select(int i) {
        FragmentManager fm = getFragmentManager();  //获得Fragment管理器
        FragmentTransaction ft = fm.beginTransaction(); //开启一个事务
        hidtFragment(ft);   //先隐藏 Fragment

        switch (i) {
            case 0:
                if (fragment1 == null) {
                    fragment1 = new TigerMachineFragment();
                    ft.add(R.id.fragment_container, fragment1);
                } else {
                    ft.show(fragment1);
                }
                break;
            case 1:
                if (fragment2 == null) {

                  //  Log.d("titi",getIntent().getStringExtra("tiger"));

                    fragment2 = new YaoFragment();
                    ft.add(R.id.fragment_container, fragment2);
                } else {
                    ft.show(fragment2);
                }
                break;
        }
        ft.commit();   //提交事务
    }

    public static String getURL(){

        return url;
    }
    //隐藏所有Fragment
    private void hidtFragment(FragmentTransaction fragmentTransaction) {
        if (fragment1 != null) {
            fragmentTransaction.hide(fragment1);
        }
        if (fragment2 != null) {
            fragmentTransaction.hide(fragment2);
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.rb_teger:
                select(0);
                if (rb_yao.isChecked()) {
                    rb_yao.setTextColor(Color.parseColor("#ffffff"));
                } else {
                    rb_yao.setTextColor(Color.parseColor("#f24e30"));
                }

                if (rb_tiger.isChecked()) {
                    rb_tiger.setTextColor(Color.parseColor("#ffffff"));
                } else {
                    rb_tiger.setTextColor(Color.parseColor("#f24e30"));
                }
                break;
            case R.id.rb_yao:
                select(1);
                if (rb_yao.isChecked()) {
                    rb_yao.setTextColor(Color.parseColor("#ffffff"));
                } else {
                    rb_yao.setTextColor(Color.parseColor("#ff0033"));
                }

                if (rb_tiger.isChecked()) {
                    rb_tiger.setTextColor(Color.parseColor("#ffffff"));
                } else {
                    rb_tiger.setTextColor(Color.parseColor("#ff0033"));
                }
                break;
        }


    }

    //返回键监听
    @Event(R.id.iv_tiger)
    private void ivClick(View view){
        finish();
    }


    @Override
    public void sendMessageValue(int value) {
        tv_score.setText(value + "");
    }
}
