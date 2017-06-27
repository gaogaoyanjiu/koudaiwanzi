package com.example.administrator.koudaiwanzi.person.mymeatball;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.meatball.DetailBallActivity;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/2.
 * 我的丸子说界面
 */

@ContentView(R.layout.activity_my_meat_ball)
public class MyMeatBallActivity extends AppCompatActivity{

private MyMeatBallActivity myActivity;



    private MyMeatBallAdapter adapter;
    //需要上传的网址
    private String url;
    private String urlll;

    @ViewInject(R.id.wanzishuo_listview)
    private ListView listView;

    private static String HeadUrl;
    private static String UseName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
      //  url = getIntent().getStringExtra("meatballstory");
        //Log.d("url999",url);

        SharedPreferences share =getSharedPreferences("meatballstory", Context.MODE_PRIVATE);
        url  =share.getString("meatballstory", "");


        RequestParams params_my = new RequestParams(url);

        x.http().get(params_my, new Callback.CommonCallback<String>(){

            @Override
            public void onSuccess(String s) {

                Gson gson = new Gson();

                final MyNewMeatBallBean bean = gson.fromJson(s, MyNewMeatBallBean.class);


                HeadUrl  = bean.getHEADURL();
                UseName = bean.getUSERNAME();



                urlll = bean.getDetailUrl();




                adapter = new MyMeatBallAdapter(MyMeatBallActivity.this, bean.getStory(), R.layout.wanzishuo_listview_item);

                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MyMeatBallActivity.this,DetailBallActivity.class);

                        intent.putExtra("pictureBall",bean.getStory().get(position).getDetailUrl());
                        intent.putExtra("time",bean.getStory().get(position).getCREATETIME()+"");
                        Log.d("time",bean.getStory().get(position).getCREATETIME()+"");
                        intent.putExtra("name",bean.getUSERNAME());
                        Log.d("name",bean.getUSERNAME());
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

    public static String  getUrl(){

        return HeadUrl;

    }

    public static String getName(){
        return UseName;
    }


    public static String getStringTime(String cc_time){

        String time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long time_cc = Long.valueOf(cc_time);
        time = sdf.format(new Date(time_cc*1000L));
        return time;
    }

    //跳转到新增丸子说界面
    @Event(R.id.tv_add_meatBall)
    private void addClick(View view){
        Intent intent = new Intent(MyMeatBallActivity.this, AddMeatBallPActivity.class);
        intent.putExtra("Mymeat_url",urlll);
        startActivity(intent);
        finish();

    }


    //返回按键监听
    @Event(R.id.iv_back_myMeatBall)
    private void backClick(View view){
        finish();
    }
}
