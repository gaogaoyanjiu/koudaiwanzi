package com.example.administrator.koudaiwanzi.person.coupon;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.google.gson.Gson;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
import java.util.ArrayList;
import java.util.List;

/**
 * 优惠券页面
 * Created by Administrator on 2016/6/8.
 */

@ContentView(R.layout.activity_coupon)
public class CouponActivity extends AppCompatActivity {
    private String url3 =MyUrl.url + "Users.svc//nousecoupon//db534290-4e37-4db1-ac51-b7caffec874d";
    private TabLayout tab_title;  //定义tabLayout
    private ViewPager viewPager;  //定义viewPager
    private CouponViewPagerAdapter adapter; //定义adapter
    private List<Fragment> list_fragment; //定义要装fragment的列表
    private List<String> list_Title; //tab名称列表
    private String url2 = MyUrl.url;
    private String url = "";
    private CouponNotUseFragment couponNotUseFragment; //定义未使用优惠券fragment
    private CouponAlreadyUseFragment couponAlreadyUseFragment; //定义已使用优惠券fragment
    private CouponOutTimeFragment couponOutTimeFragment; //定义已过期优惠券fragment
    private Dialog dialog;
    private EditText dialog_editext;
    //绑定兑换码
    @ViewInject(R.id.conversion_code)
    private TextView conversion_code;
    private String codeUrll_half,codeUrll_send;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
        String a = getIntent().getStringExtra("coupon");
        url = url2 + a;
        Log.d("uiiy",url);

        //解析优惠券
        RequestParams param = new RequestParams(url);
        x.http().get(param, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson  = new Gson();
                CouponBean couponbean = gson.fromJson(s,CouponBean.class);
                codeUrll_half = couponbean.getDetailUrl();

                codeUrll_send  = url2 + codeUrll_half;


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

    private void initView() {
        tab_title = (TabLayout) findViewById(R.id.coupon_tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        //初始化各fragment
        couponNotUseFragment = new CouponNotUseFragment();
        couponAlreadyUseFragment = new CouponAlreadyUseFragment();
        couponOutTimeFragment = new CouponOutTimeFragment();

        //将fragment装进列表中
        list_fragment = new ArrayList<>();
        list_fragment.add(couponNotUseFragment);
        list_fragment.add(couponAlreadyUseFragment);
        list_fragment.add(couponOutTimeFragment);

        final RequestParams params = new RequestParams(url3);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                CouponBean bean = gson.fromJson(s, CouponBean.class);

                //将名称加载tab名字列表
                list_Title = new ArrayList<>();
                list_Title.add("未使用" + "(" + bean.getMENU().getNOTUSED().getNUM() + ")");
                list_Title.add("已使用" + "(" + bean.getMENU().getUSED().getNUM() + ")");
                list_Title.add("已过期" + "(" + bean.getMENU().getOUTOFDATE().getNUM() + ")");

                //设置Tablayout的模式
                tab_title.setTabMode(TabLayout.MODE_FIXED);

                //为TabLayout添加tab名称
                tab_title.addTab(tab_title.newTab().setText(list_Title.get(0)));
                tab_title.addTab(tab_title.newTab().setText(list_Title.get(1)));
                tab_title.addTab(tab_title.newTab().setText(list_Title.get(2)));

                adapter = new CouponViewPagerAdapter(getSupportFragmentManager(), list_fragment, list_Title);

                //viewpager加载adapter
                viewPager.setAdapter(adapter);
                tab_title.setupWithViewPager(viewPager);
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

    //返回按键
    @Event(R.id.iv_coupon_back)
    private void imaClick(View view) {
        finish();
    }


    @Event(R.id.conversion_code)
    private void conversion(View view){
        LayoutInflater inflater = LayoutInflater.from(CouponActivity.this);
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.dialog_coupon,null);
         dialog_editext = (EditText) layout.findViewById(R.id.dialog_editext);
        dialog = new AlertDialog.Builder(CouponActivity.this).create();
        dialog.show();
        dialog.getWindow().setContentView(layout);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        Button btnOK = (Button) layout.findViewById(R.id.dialog_ok);

        Button dialog_close = (Button)layout.findViewById(R.id.dialog_close);

        dialog_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                RequestParams pa = new RequestParams(codeUrll_send+dialog_editext.getText().toString());
                Log.d("mama",codeUrll_send +dialog_editext.getText().toString());
                x.http().get(pa, new Callback.CommonCallback<String>(){

                    @Override
                    public void onSuccess(String s) {
                        Gson gson = new Gson();
                        CodeBean codebean = gson.fromJson(s,CodeBean.class);
                        codebean.getMsg();
                        Log.d("mama", String.valueOf(codebean.getMsg()));

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

                dialog.dismiss();

            }
        });


    }

}
