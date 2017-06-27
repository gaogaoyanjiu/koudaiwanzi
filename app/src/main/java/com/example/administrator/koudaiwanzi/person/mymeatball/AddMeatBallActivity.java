package com.example.administrator.koudaiwanzi.person.mymeatball;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.koudaiwanzi.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/2.
 * 新增丸子说界面
 *
 */

@ContentView(R.layout.activity_add_meat_ball)
public class AddMeatBallActivity extends AppCompatActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }
}
