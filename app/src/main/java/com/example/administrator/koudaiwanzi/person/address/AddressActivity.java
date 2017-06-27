package com.example.administrator.koudaiwanzi.person.address;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.administrator.koudaiwanzi.MainActivity;
import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.dialog.CustomProgressDialog;
import com.example.administrator.koudaiwanzi.shopcar.OrderActivity;
import com.example.administrator.koudaiwanzi.shopcar.SelectAddressActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
@ContentView(R.layout.activity_manage_address)
public class AddressActivity extends AppCompatActivity {
    private AddressAdapter adapter;
    private String url1 = MyUrl.url;
    private String url2;
    private String url;
    private List<AddressBean> data;
    private String back;
    @ViewInject(R.id.lv_manage)
    private ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
//        SharedPreferences share = AddressActivity.this.getSharedPreferences("logIn", Context.MODE_PRIVATE);
//        String UID = share.getString("login", "");
        final CustomProgressDialog dialog = new CustomProgressDialog(AddressActivity.this, R.style.CommProgressDialog, "加载中...", R.anim.frame2);
        dialog.setProgressStyle(R.style.CommProgressDialog);
        dialog.show();
        SharedPreferences share1 = AddressActivity.this.getSharedPreferences("AddressBack", Context.MODE_PRIVATE);
        back = share1.getString("AddressBack", "");

        url2 = getIntent().getStringExtra("address");

        url = url1 + url2;
        adapter = new AddressAdapter(this, handler);
        Log.d("wodetianlaoye", url);
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String o) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<AddressBean>>() {
                }.getType();
                data = gson.fromJson(o, type);
                adapter.addData(data);
                listView.setAdapter(adapter);
                dialog.cancel();
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

    //新建收货地址
    @Event(R.id.rl_new_address)
    private void rlClick(View view) {
        SharedPreferences share = AddressActivity.this.getSharedPreferences("Address", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putString("AddressName", "");
        editor.putString("AddressNum", "");
        editor.putString("AddressStreet", "");
        editor.apply();
        Intent intent = new Intent(AddressActivity.this, NewAddressActivity.class);
        startActivity(intent);
        finish();
    }

    //返回键监听
    @Event(R.id.iv_manage_back)
    private void backClick(View view) {

        if (back.equals("1")){
            Intent intent = new Intent(AddressActivity.this, SelectAddressActivity.class);
            startActivity(intent);
            finish();
        }else {
            finish();
        }

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 10) {
                Bundle b = new Bundle();
                b = (Bundle) msg.obj;
                String set = b.getString("setUrl");
                String setUrl = url1 + set;
                RequestParams params = new RequestParams(setUrl);
                x.http().get(params, new org.xutils.common.Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<AddressBean>>() {
                        }.getType();
                        data = gson.fromJson(s, type);
                        adapter.addData(data);
                        listView.setAdapter(adapter);

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

            }
        }
    };

}
