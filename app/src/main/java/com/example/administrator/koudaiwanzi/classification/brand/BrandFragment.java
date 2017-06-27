package com.example.administrator.koudaiwanzi.classification.brand;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.BaseFragment;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.product.ShowProductActivity;
import com.example.administrator.koudaiwanzi.selling.NEWITEMSBean;
import com.example.administrator.koudaiwanzi.selling.SellBean;
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
 * 品牌页面
 */
@ContentView(R.layout.fragment_brand)
public class BrandFragment extends BaseFragment {
    private String url = "Items.svc//newbrand";
    private String url1 = MyUrl.url;
    private BrandAdapter adapter;
    private List<BrandBean> datas;
    private List<List<SECLEVELBean>> childs;

    @ViewInject(R.id.el_brand)
    private ExpandableListView expandableListView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new BrandAdapter(getActivity());

        datas = new ArrayList<>();
        childs = new ArrayList<>();

        RequestParams params = new RequestParams(url1 + url);
        Log.e("wwwzzz", url1 + url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Log.e("wwwzzz", "eeeeee");
                Gson gson = new Gson();
                Type type = new TypeToken<List<BrandBean>>() {
                }.getType();

                Log.e("wwwzzz", "wwwwww");

                datas = gson.fromJson(s, type);

                for (int i = 0; i < datas.size(); i++) {
                    List<SECLEVELBean> child = new ArrayList<SECLEVELBean>();
                    for (int j = 0; j < datas.get(i).getSECLEVEL().size(); j++) {
                        child.add(datas.get(i).getSECLEVEL().get(j));
                    }
                    childs.add(child);
                }

                adapter.addData(datas, childs);
                expandableListView.setAdapter(adapter);


                for (int i = 0; i < datas.size(); i++) {
                    expandableListView.expandGroup(i);
                }

                expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                        Intent intent = new Intent(getActivity(), ShowProductActivity.class);
                        intent.putExtra("showKey", datas.get(groupPosition).getDetailUrl());
                        intent.putExtra("TID", datas.get(groupPosition).getTID());
                        intent.putExtra("where", "5");
                        startActivity(intent);
                        return true;
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
}
