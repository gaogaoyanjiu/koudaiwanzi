package com.example.administrator.koudaiwanzi.person.concern;

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
 * Created by Administrator on 2016/8/2.
 * 关注界面
 */

@ContentView(R.layout.activity_my_concern)
public class ConcernActivity extends AppCompatActivity {
    private ConcernAdapter adapter;
//    private List<String> data;
    private String url1 = MyUrl.url;
    private List<ConcernBean> data;

    @ViewInject(R.id.my_concern_listview)
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        Intent intent = getIntent();
        String url2 = intent.getStringExtra("concern");
        Log.d("bangwokanyixia", url1+url2);
        RequestParams params = new RequestParams(url1+url2);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<ConcernBean>>() {
                }.getType();
                data = gson.fromJson(s, type);
                adapter = new ConcernAdapter(ConcernActivity.this, data, R.layout.my_concern_listview_item);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ConcernActivity.this, ConcernPersonActivity.class);
                        intent.putExtra("Concern",data.get(position).getDetailUrl());
                        intent.putExtra("ConcernName",data.get(position).getUSERNAME());
                        intent.putExtra("ConcernBLOYNUM",data.get(position).getBLOYNUM()+"");
                        intent.putExtra("ConcernCONUM",data.get(position).getCONUM()+"");
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

    //返回键监听
    @Event(R.id.iv_back_concern)
    private void backClick(View view) {
        finish();
    }
}
