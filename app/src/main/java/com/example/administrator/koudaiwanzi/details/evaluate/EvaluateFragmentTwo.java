package com.example.administrator.koudaiwanzi.details.evaluate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.BaseFragment;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.details.detail.DetailBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Administrator on 2016/11/17.
 */

@ContentView(R.layout.fragment_evaluate)
public class EvaluateFragmentTwo extends BaseFragment{


    private EvaluateAdapter evaluateAdapter;
    private String url1 = MyUrl.url;
    private  String url;

    //全部
    @ViewInject(R.id.tv_evaluate_all)
    private TextView all;
    //全部红色
    @ViewInject(R.id.tv_evaluate_all1)
    private TextView all1;
    //好评
    @ViewInject(R.id.tv_evaluate_good)
    private TextView good;
    //好评红色
    @ViewInject(R.id.tv_evaluate_good1)
    private TextView good1;
    //中评
    @ViewInject(R.id.tv_evaluate_notGood)
    private TextView notGood;
    //中评红色
    @ViewInject(R.id.tv_evaluate_notGood1)
    private TextView notGood1;
    //差评
    @ViewInject(R.id.tv_evaluate_bad)
    private TextView bad;
    //差评红色
    @ViewInject(R.id.tv_evaluate_bad1)
    private TextView bad1;
    //绑定listView的id
    @ViewInject(R.id.lv_evaluate)
    private ListView lv_evaluate;



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        evaluateAdapter = new EvaluateAdapter(getActivity());

        Intent intent = getActivity().getIntent();
        String getUrl = intent.getStringExtra("key");
        SharedPreferences share = getActivity().getSharedPreferences("logIn", Context.MODE_PRIVATE);
        String info = share.getString("login","");

        if (info.equals("")){
            url = url1 + getUrl + "/" + 1;
        }else {
            url = url1 + getUrl +  "/" + info;

        }
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                DetailBean bean = gson.fromJson(s,  DetailBean.class);



                String urlComment = url1+ bean.getCOMMENT();
                RequestParams pa = new RequestParams(urlComment);
                x.http().get(pa, new CommonCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Gson gson = new Gson();
                        final CommentBean bean = gson.fromJson(s, CommentBean.class);
                        if(bean.getMENU().getALLSTATUE() == 1){
                            all1.setVisibility(View.VISIBLE);
                            good1.setVisibility(View.GONE);
                            notGood1.setVisibility(View.GONE);
                            bad1.setVisibility(View.GONE);
                        }else if(bean.getMENU().getGOODSTATUE() ==1){
                            all1.setVisibility(View.GONE);
                            good1.setVisibility(View.VISIBLE);
                            notGood1.setVisibility(View.GONE);
                            bad1.setVisibility(View.GONE);
                        }
                        else if(bean.getMENU().getSECSTATUE() ==1){
                            all1.setVisibility(View.GONE);
                            good1.setVisibility(View.GONE);
                            notGood1.setVisibility(View.VISIBLE);
                            bad1.setVisibility(View.GONE);

                        }else{
                            all1.setVisibility(View.GONE);
                            good1.setVisibility(View.GONE);
                            notGood1.setVisibility(View.GONE);
                            bad1.setVisibility(View.VISIBLE);
                        }
                        evaluateAdapter.addData(bean.getCOMITEMS());
                        lv_evaluate.setAdapter(evaluateAdapter);

                        all.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                all1.setVisibility(View.VISIBLE);
                                good1.setVisibility(View.GONE);
                                notGood1.setVisibility(View.GONE);
                                bad1.setVisibility(View.GONE);

                                RequestParams params = new RequestParams(url1+bean.getMENU().getALLCOM());
                                x.http().get(params, new CommonCallback<String>() {
                                    @Override
                                    public void onSuccess(String s) {
                                        Gson gson = new Gson();
                                        CommentBean bean = gson.fromJson(s, CommentBean.class);
                                        evaluateAdapter.addData(bean.getCOMITEMS());
                                        lv_evaluate.setAdapter(evaluateAdapter);

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
                        });

                        good.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                all1.setVisibility(View.GONE);
                                good1.setVisibility(View.VISIBLE);
                                notGood1.setVisibility(View.GONE);
                                bad1.setVisibility(View.GONE);
                                RequestParams params = new RequestParams(url1+bean.getMENU().getGOODCOM());
                                x.http().get(params, new CommonCallback<String>() {
                                    @Override
                                    public void onSuccess(String s) {
                                        Gson gson = new Gson();
                                        CommentBean bean = gson.fromJson(s, CommentBean.class);
                                        evaluateAdapter.addData(bean.getCOMITEMS());
                                        lv_evaluate.setAdapter(evaluateAdapter);

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
                        });

                        notGood.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                all1.setVisibility(View.GONE);
                                good1.setVisibility(View.GONE);
                                notGood1.setVisibility(View.VISIBLE);
                                bad1.setVisibility(View.GONE);
                                RequestParams params = new RequestParams(url1+bean.getMENU().getSECCOM());
                                x.http().get(params, new CommonCallback<String>() {
                                    @Override
                                    public void onSuccess(String s) {
                                        Gson gson = new Gson();
                                        CommentBean bean = gson.fromJson(s, CommentBean.class);
                                        evaluateAdapter.addData(bean.getCOMITEMS());
                                        lv_evaluate.setAdapter(evaluateAdapter);

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
                        });

                        bad.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                all1.setVisibility(View.GONE);
                                good1.setVisibility(View.GONE);
                                notGood1.setVisibility(View.GONE);
                                bad1.setVisibility(View.VISIBLE);
                                RequestParams params = new RequestParams(url1+bean.getMENU().getBADCOM());
                                x.http().get(params, new CommonCallback<String>() {
                                    @Override
                                    public void onSuccess(String s) {
                                        Gson gson = new Gson();
                                        CommentBean bean = gson.fromJson(s, CommentBean.class);
                                        evaluateAdapter.addData(bean.getCOMITEMS());
                                        lv_evaluate.setAdapter(evaluateAdapter);

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
