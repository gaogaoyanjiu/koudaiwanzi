package com.example.administrator.koudaiwanzi.person.collection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.details.DetailsActivity;
import com.example.administrator.koudaiwanzi.person.MyMeatBallNewBean;
import com.example.administrator.koudaiwanzi.person.PersonBean;
import com.example.administrator.koudaiwanzi.person.address.AddressActivity;
import com.example.administrator.koudaiwanzi.person.concern.ConcernActivity;
import com.example.administrator.koudaiwanzi.person.coupon.CouponActivity;
import com.example.administrator.koudaiwanzi.person.inviter.InviterActivity;
import com.example.administrator.koudaiwanzi.person.point.MyPointActivity;
import com.example.administrator.koudaiwanzi.person.reject.MyRejectActivity;
import com.example.administrator.koudaiwanzi.person.set.AccountActivity;
import com.example.administrator.koudaiwanzi.refresh.PullToRefreshLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 */

@ContentView(R.layout.activity_mycollection)
public class CollectionActivity extends AppCompatActivity {
    private CollectionAdapter collectionAdapter;//定义适配器对象
    private String url1 = MyUrl.url;
    private String url;
    private List<CollectionBean> data;
    private LocalBroadcastManager broadcastManager;
    private IntentFilter intentFilter;
    private BroadcastReceiver mReceiver;

    //绑定gridView
    @ViewInject(R.id.collect_gridView)
    private GridView gridView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        //上拉加载和下拉刷新的监听
        ((PullToRefreshLayout) findViewById(R.id.refresh_collection))
                .setOnRefreshListener(new MyListener());

        final Intent intent = getIntent();
        String collectUrl = intent.getStringExtra("collect");


        SharedPreferences share = getSharedPreferences("logIn", Context.MODE_PRIVATE);
        final String UID = share.getString("login","");
        url = url1 + collectUrl;
        Log.e("shoucang", url);
        //初始化适配器
        collectionAdapter = new CollectionAdapter(this);

        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String o) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<CollectionBean>>() {
                }.getType();
                data = gson.fromJson(o, type);

                collectionAdapter.addData(data);
                //listView添加适配器
                gridView.setAdapter(collectionAdapter);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(CollectionActivity.this, DetailsActivity.class);
                        intent.putExtra("key", data.get(position).getDetailUrl());
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

        broadcastManager = LocalBroadcastManager.getInstance(CollectionActivity.this.getApplication());
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.administrator.myapplication.main.main.person.broadcast");
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                RequestParams params = new RequestParams(url);
                x.http().get(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String o) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<CollectionBean>>() {
                        }.getType();
                        data = gson.fromJson(o, type);

                        collectionAdapter.addData(data);
                        //listView添加适配器
                        gridView.setAdapter(collectionAdapter);

                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(CollectionActivity.this, DetailsActivity.class);
                                intent.putExtra("key", data.get(position).getDetailUrl());
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
        };
        broadcastManager.registerReceiver(mReceiver, intentFilter);

    }

    //返回键的监听
    @Event(R.id.mycollection_back)
    private void imgClick(View view) {
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
                    final RequestParams params = new RequestParams(url);
                    x.http().get(params, new org.xutils.common.Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String o) {
                            Gson gson = new Gson();

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

                    // 千万别忘了告诉控件加载完毕了哦！
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                }
            }.sendEmptyMessageDelayed(0, 1000);
        }
    }




}
