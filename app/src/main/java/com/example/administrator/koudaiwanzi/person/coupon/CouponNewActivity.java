package com.example.administrator.koudaiwanzi.person.coupon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.shopcar.OrderActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/09/21.
 * 新的（未使用的优惠券的页面）
 */
@ContentView(R.layout.activity_coupon_new)
public class CouponNewActivity extends AppCompatActivity {

    //lisetview的id
    @ViewInject(R.id.coupon_new_listview)
    private ListView coupon_new_listview;
    //适配器的
    private CouponNewAdapter adapter;
    //数据集合
    private static ArrayList<CouponNewBean> data;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        //接收传过来的url 这个是从OrderActivity传过来的   这个url解析出来的是 优惠券的集合
        String url = getIntent().getStringExtra("COUPONURL");
        data = new ArrayList<>();

        RequestParams params = new RequestParams(MyUrl.url + url);
        Log.d("dsdawadsda123", MyUrl.url + url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                //如果只是这种形式的 没有中括号的一定要用type
                Type type = new TypeToken<List<CouponNewBean>>() {
                }.getType();

                data = gson.fromJson(s, type);
                //通用适配器添加数据 初始化adapter的方法
                adapter = new CouponNewAdapter(CouponNewActivity.this, data, R.layout.coupon_not_use_listview_item);
                coupon_new_listview.setAdapter(adapter);

                //listview的航布局监听      （每一张优惠券的点击事件）
                coupon_new_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                       //单张优惠券的网址 返回的数据是 DetailUrl，和msg（1，-1,0）  MyUrl.url + data.get(position).getDetailUrl()
                        RequestParams params = new RequestParams(MyUrl.url + data.get(position).getDetailUrl());
                        x.http().get(params, new CommonCallback<String>() {
                            @Override
                            public void onSuccess(String s) {
                                Gson gson = new Gson();
                                CodeBean codebean = gson.fromJson(s,CodeBean.class);
                                codebean.getMsg();
                                //如果优惠券的返回结果是1 说明优惠券可以使用 这样就跳转到“订单详情页面”
                                //跳转结束之后finish
                                if(codebean.getMsg() == 1){
//
                                    Intent intent = new Intent(CouponNewActivity.this, OrderActivity.class);
                                    startActivity(intent);
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




    public static final ArrayList<CouponNewBean> getData(){
        return data;
    }
}
