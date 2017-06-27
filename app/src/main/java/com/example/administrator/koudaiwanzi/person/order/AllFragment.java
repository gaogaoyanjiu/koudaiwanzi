package com.example.administrator.koudaiwanzi.person.order;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.BaseFragment;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.refresh.PullToRefreshLayout;
import com.example.administrator.koudaiwanzi.refresh.PullableListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2016/6/7.
 */

@ContentView(R.layout.fragment_order_all)
public class AllFragment extends BaseFragment {
    private String url1 = MyUrl.url;
    private String url2 = "Users.svc//order//";
    private String UID ;
    private List<OrderBean> data;

    private OrderAllAdapter orderAllAdapter;
    @ViewInject(R.id.listView_order_all)
    private PullableListView listView;



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        orderAllAdapter = new OrderAllAdapter(getActivity(),handler);
        SharedPreferences share = getActivity().getSharedPreferences("logIn", Context.MODE_PRIVATE);
        UID = share.getString("login", "");
        String url = url1 + url2 + UID+"/-1";
        Log.e("oqpotbn",url);
        final RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String o) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<OrderBean>>() {
                }.getType();
                data = gson.fromJson(o, type);
                orderAllAdapter.addData(data);
                listView.setAdapter(orderAllAdapter);

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




        //上拉加载和下拉刷新的监听
        ((PullToRefreshLayout) getActivity().findViewById(R.id.refresh_order_all))
                .setOnRefreshListener(new MyListener());
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

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0){
                SharedPreferences share = getActivity().getSharedPreferences("logIn", Context.MODE_PRIVATE);
                UID = share.getString("login", "");
                String url = url1 + url2 + UID+"/-1";
                Log.e("oqpotbn",url);
                final RequestParams params = new RequestParams(url);
                x.http().get(params, new org.xutils.common.Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String o) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<OrderBean>>() {
                        }.getType();
                        data = gson.fromJson(o, type);
                        orderAllAdapter.addData(data);
                        listView.setAdapter(orderAllAdapter);

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
            super.handleMessage(msg);
        }
    };

}
