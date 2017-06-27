package com.example.administrator.koudaiwanzi.person.point.exchangticket;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.refresh.PullToRefreshLayout;
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
 * Created by Administrator on 2016/6/3.
 * 积分兑换界面
 */
@ContentView(R.layout.activity_exchange_ticket)
public class ExchangeActivity extends AppCompatActivity {
    private ExchangeAdapter adapter;
//    101.201.77.29:3971
    private String url2 = MyUrl.url+"Users.svc//exrecord//db534290-4e37-4db1-ac51-b7caffec874d";
//    db534290-4e37-4db1-ac51-b7caffec874d
    @ViewInject(R.id.exchange_ticket_listview)
    private ListView listView;

    private List<ExchangeBean> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        //上拉加载和下拉刷新的监听
        ((PullToRefreshLayout) findViewById(R.id.refresh_exchange))
                .setOnRefreshListener(new MyListener());

        SharedPreferences share = getSharedPreferences("logIn", Context.MODE_PRIVATE);
        String UID = share.getString("login","");
        String url = url2 + UID;

        adapter = new ExchangeAdapter(this);
        Log.e("exchange", url);
        RequestParams params = new RequestParams(url2);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String o) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<ExchangeBean>>() {
                }.getType();
                data = gson.fromJson(o, type);
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

    }

    //返回按键
    @Event(R.id.exchange_ticket_title_back)
    private void imaClick(View view){
        finish();
    }


    //刷新和加载的监听
    public class MyListener implements PullToRefreshLayout.OnRefreshListener {

        //刷新
        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            // 下拉刷新操作
            new Handler() {
                @Override
                public void handleMessage(Message msg) {

                    // 千万别忘了告诉控件刷新完毕了哦！
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 1000);
        }

        //加载
        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            // 上拉加载操作
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
//                    String a = "";
//                    int i = 99987070;
//                    i++;
//                    String url1 = a + i;

                    // 千万别忘了告诉控件加载完毕了哦！
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 1000);
        }
    }


}
