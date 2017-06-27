package com.example.administrator.koudaiwanzi.meatball;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.BaseFragment;
import com.example.administrator.koudaiwanzi.base.MyUrl;
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
 * Created by Administrator on 2016/5/30.
 * 丸子说界面
 */

@ContentView(R.layout.fragment_meatball)
public class MeatBallFragment extends BaseFragment {
    private MeatBallAdapterTwo adapter;
    private String url1 = MyUrl.url + "Items.svc/blogstory/";
    private List<VideoBean> data;
    private Handler mHandler;
    private String UID;
    private String url;

    //绑定listView的ID
    @ViewInject(R.id.meatBall_listview)
//    private XListViewSwipe recyclerView;
    private RecyclerView recyclerView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//
//        //上拉加载和下拉刷新的监听
////        recyclerView.setXListViewListener(new MyListener());
//        mHandler = new Handler();
//        SharedPreferences share = getActivity().getSharedPreferences("logIn", Context.MODE_PRIVATE);
//        UID = share.getString("login", "");
//        if (UID == ""){
//            url = url1 + 1;
//        }else {
//            url = url1 + UID;
//        }
//        adapter = new MeatBallAdapterTwo(getActivity());
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        RequestParams params = new RequestParams(url);
//        Log.e("jipoaon", url);
//        x.http().get(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String s) {
//                Gson gson = new Gson();
//                Type type = new TypeToken<List<VideoBean>>() {
//                }.getType();
////              VideoBean bean = gson.fromJson(response,VideoBean.class);
//                data = gson.fromJson(s, type);
//                if (data == null) {
//
//                } else {
//                    Log.e("www", data.size() + "");
//                    adapter.addData(data);
//
//                    recyclerView.setAdapter(adapter);
//
//
//                }
//
//            }
//
//            @Override
//            public void onError(Throwable throwable, boolean b) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException e) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });

    }


}

