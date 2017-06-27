package com.example.administrator.koudaiwanzi.classification.type;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.BaseFragment;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.dialog.CustomProgressDialog;
import com.example.administrator.koudaiwanzi.refresh.PullToRefreshLayout;
import com.example.administrator.koudaiwanzi.refresh.PullableExpandableListView;
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
 * Created by Administrator on 2016/6/3.
 */

@ContentView(R.layout.fragment_type)
public class TypeFragment extends BaseFragment {
    private ExpandableListViewAdapter adapter;
//    String url = "http://101.201.77.29:3971/Items.svc/catemenu";
    String url = MyUrl.url + "Items.svc/catemenu";
    private  List<TypeBean> typeData;
    private  Handler handler;


    @ViewInject(R.id.expandableListView)
    private PullableExpandableListView expandableListView;


//loadDateThreah()线程类为：
    class loadDateThreah implements Runnable {
        @Override
        public void run() {

        //    ....这里是联网下载数据，下载完成后执行下列的方法，handlder会调用前面覆写的handleMessage方法，在那里关闭加载提示框...
            }
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new ExpandableListViewAdapter(getActivity());
        final CustomProgressDialog dialog =new CustomProgressDialog(getActivity(),R.style.CommProgressDialog, "加载中...",R.anim.frame2);
//        dialog.setProgressStyle(R.style.CommProgressDialog);
        dialog.show();

        final RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String o) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<TypeBean>>(){}.getType();
                typeData = gson.fromJson(o, type);
                List<List<String>> childs = new ArrayList<>();
                for (int i = 0; i < typeData.size(); i++) {
                    List<String> child = new ArrayList<>();
                    for (int j = 0; j < typeData.get(i).getSECLEVEL().size(); j++) {
                        child.add(typeData.get(i).getSECLEVEL().get(j).getCODENAME());
                    }
                    childs.add(child);
                }

                adapter.addData(typeData,childs);
                expandableListView.setAdapter(adapter);
                expandableListView.expandGroup(0);
                dialog.cancel();
                Log.d("010132542", "111111111");
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });





        //上拉加载和下拉刷新的监听
        ((PullToRefreshLayout) getActivity().findViewById(R.id.refresh_type))
                .setOnRefreshListener(new MyListener());
        //去掉线
        expandableListView.setDivider(null);

        //对expandableListView的group进行监听
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent,
                                        View v, int groupPosition, long id) {
                return false;
            }
        });

        //点击其他项 之前点开的child自动关闭
        //group的展开监听
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {



                for (int i = 0; i < 8; i++) {
                    if (groupPosition != i){
                        expandableListView.collapseGroup(i);
                    }
                }
            }
        });

        //对expandableListView的child进行监听
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                Toast.makeText(getActivity(), "position=" + groupPosition + "||" + childPosition,
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });

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
                            Type type = new TypeToken<List<TypeBean>>(){}.getType();
                            typeData = gson.fromJson(o, type);
                            List<List<String>> childs = new ArrayList<>();
                            for (int i = 0; i < typeData.size(); i++) {
                                List<String> child = new ArrayList<>();
                                for (int j = 0; j < typeData.get(i).getSECLEVEL().size(); j++) {
                                    child.add(typeData.get(i).getSECLEVEL().get(j).getCODENAME());
                                }
                                childs.add(child);
                            }

                            adapter.addData(typeData,childs);
                            expandableListView.setAdapter(adapter);
                            expandableListView.expandGroup(0);

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
