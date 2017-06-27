package com.example.administrator.koudaiwanzi.person.point.pointmall;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/09/29.
 */
public class WindowActivity extends Activity {
    private LinearLayout layout;
    private TextView textview;

    private ListView listview;
    private MyAdapter adapter;
    private ArrayList<YaoBean.HISLODRAWBean> data;
    private String  url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window);
        listview = (ListView) findViewById(R.id.listview);
        url = getIntent().getStringExtra("window");
        Log.d("youurl",url);
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                YaoBean bean = gson.fromJson(s,YaoBean.class);
                data = new ArrayList<YaoBean.HISLODRAWBean>();

                for (int i = 0; i <bean.getHISLODRAW().size() ; i++) {
                    data.add(bean.getHISLODRAW().get(i));
                }

                adapter = new MyAdapter(WindowActivity.this,data);
                listview.setAdapter(adapter);

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
//        data = new ArrayList<>();
//        data.add("领更新： 10月1号领取了5个");
//        data.add("令心如： 10月2号领取了5个");
//        data.add("凌俊杰： 10月3号领取了5个");



        layout=(LinearLayout)findViewById(R.id.exit_layout);
        textview = (TextView) findViewById(R.id.textview);

        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                            Toast.makeText(getApplicationContext(), "提示：点击窗口外部关闭窗口！",
//                                          Toast.LENGTH_SHORT).show();
            }
        });
    }
    //

    @Override
    public boolean onTouchEvent(MotionEvent event){
        finish();
        return true;
    }

    public void exitbutton1(View v) {
        this.finish();
    }
    public void exitbutton0(View v) {
        this.finish();
        finish();//关闭Main 这个Activity
    }}
