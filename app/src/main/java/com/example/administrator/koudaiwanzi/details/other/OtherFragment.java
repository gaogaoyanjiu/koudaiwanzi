package com.example.administrator.koudaiwanzi.details.other;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.BaseFragment;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.details.DetailsActivity;
import com.example.administrator.koudaiwanzi.details.DetailsTwoActivity;
import com.example.administrator.koudaiwanzi.details.detail.DetailBean;
import com.example.administrator.koudaiwanzi.product.ShowProductActivity;
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
 * Created by Administrator on 2016/6/21.
 */
@ContentView(R.layout.fragment_other)
public class OtherFragment extends BaseFragment {
    private String url1 = MyUrl.url;
    private String url2 ;
    private OtherAdapter adapter;
    private String url;
    private List<OtherBean> data;
    private RequestQueue queue;
    private String info;
    private int num = 1;

    @ViewInject(R.id.gv_other)
    private GridView gv;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        queue = Volley.newRequestQueue(getActivity());
        adapter = new OtherAdapter(getActivity());
        url = url1 + url2;
        Intent intent = getActivity().getIntent();
        String getUrl = intent.getStringExtra("key");
        SharedPreferences share = getActivity().getSharedPreferences("logIn", Context.MODE_PRIVATE);
        info = share.getString("login","");

        if (info.equals("")){
            url = url1 + getUrl + "/" + 1;
        }else {
            url = url1 + getUrl +  "/" + info;
        }
        RequestParams params = new RequestParams(url);
        Log.d("dwasxa123", url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String o) {

                Gson gson = new Gson();
                final DetailBean bean = gson.fromJson(o, DetailBean.class);
                url2 = bean.getOTHERURL();

         StringRequest request = new StringRequest(url1+url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<OtherBean>>() {
                }.getType();
                data = gson.fromJson(response, type);
                adapter.addData(data);
                gv.setAdapter(adapter);

                gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent1 = new Intent(getActivity(), DetailsTwoActivity.class);
                        intent1.putExtra("key", data.get(position).getDetailUrl());
                        startActivity(intent1);
                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
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
