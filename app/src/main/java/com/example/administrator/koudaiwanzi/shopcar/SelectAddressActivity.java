package com.example.administrator.koudaiwanzi.shopcar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.person.address.AddressActivity;
import com.example.administrator.koudaiwanzi.person.address.NewAddressActivity;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/23.
 * 提交订单页面跳转的收货地址
 */
@ContentView(R.layout.acitivity_select_address)
public class SelectAddressActivity extends AppCompatActivity {
    @ViewInject(R.id.lv_select_address)
    private ListView lv_select;

    private String url;
    private String url1 = MyUrl.url;
    private SelectAddressAdapter adapter;

    //新建收货地址
    @ViewInject(R.id.tv_address_order)
    private TextView tv_address_order;

    @ViewInject(R.id.tv_address_order)
    private TextView tv_manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        String url2 = "Users.svc/selectaderss/";
        SharedPreferences share = this.getSharedPreferences("logIn", Context.MODE_PRIVATE);
        String UID = share.getString("login", "");
        url = url1 + url2 + UID;
        Log.d("zhezhonglingdao", url);
        RequestParams params = new RequestParams(url);
        adapter = new SelectAddressAdapter(this);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                final SelectAddressBean bean = gson.fromJson(s,SelectAddressBean.class);
                adapter.addData(bean.getADDRESS());
                lv_select.setAdapter(adapter);
                lv_select.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                          String a = bean.getADDRESS().get(position).getDetailUrl();
                          String urlGo = url1 + a;
                          RequestParams params1 = new RequestParams(urlGo);
                          x.http().get(params1, new CommonCallback<String>() {
                            @Override
                            public void onSuccess(String s) {
                                Intent intent = new Intent(SelectAddressActivity.this,OrderActivity.class);
                                startActivity(intent);
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
                tv_manager.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        SharedPreferences share = SelectAddressActivity.this.getSharedPreferences("address_order", Context.MODE_PRIVATE);
//                        String url_address = share.getString("address_order", "");
//                        Log.e("zxcvs", url_address);

                        SharedPreferences share2 = getSharedPreferences("AddressBack", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor2 = share2.edit();
                        editor2.putString("AddressBack", "1");
                        editor2.apply();


                        Intent intent = new Intent(SelectAddressActivity.this, AddressActivity.class);
                        intent.putExtra("address", bean.getDetailUrl());

                        startActivity(intent);
                        finish();
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

    //管理地址按键
    @Event(R.id.tv_address_order)
    private void textClick(View view){

    }

    //返回键的监听
    @Event(R.id.iv_back_address_order)
    private void ivClick(View view){
        finish();
    }


}
