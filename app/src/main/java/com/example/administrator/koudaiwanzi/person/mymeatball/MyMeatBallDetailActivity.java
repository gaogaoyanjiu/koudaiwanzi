package com.example.administrator.koudaiwanzi.person.mymeatball;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/3.
 */
@ContentView(R.layout.activity_my_meat_ball_detail)
public class MyMeatBallDetailActivity extends AppCompatActivity{
    @ViewInject(R.id.tv_mbd_delete)
    private TextView tv_delete;
    private MyMeatBallAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        final String a = intent.getStringExtra("mtb");
        x.view().inject(this);
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
