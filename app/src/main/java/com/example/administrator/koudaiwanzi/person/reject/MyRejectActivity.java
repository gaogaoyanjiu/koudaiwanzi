package com.example.administrator.koudaiwanzi.person.reject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 */


@ContentView(R.layout.activity_myreject)
public class MyRejectActivity extends AppCompatActivity{
    private MyRejectAdapter adapter;
    private String url;
    private String myUrl = MyUrl.url;
    private List<MyRejectBean> data;


    @ViewInject(R.id.lv_myReject)
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        data = new ArrayList<>();

        String url_reject = getIntent().getStringExtra("reject");
        url = myUrl + url_reject;

        RequestParams params = new RequestParams(url);
        Log.e("zdffge", url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
//                MyRejectBean bean = gson.fromJson(s, MyRejectBean.class);
                Type type = new TypeToken<List<MyRejectBean>>(){}.getType();
                data = gson.fromJson(s, type);

                adapter = new MyRejectAdapter(MyRejectActivity.this, data, R.layout.item_myreject);
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

    }

    //返回键监听
    @Event(R.id.iv_back_myreject)
    private void ivClick(View view){
        finish();
    }

}
