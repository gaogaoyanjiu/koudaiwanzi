package com.example.administrator.koudaiwanzi.person.point;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.person.point.pointmall.scoremall.ScoreMallActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Administrator on 2016/6/3.
 * 我的积分界面
 */
@ContentView(R.layout.activity_my_point)
public class MyPointActivity extends AppCompatActivity {
    private String score;

    @ViewInject(R.id.current_point)
    private TextView tv_point;

    @ViewInject(R.id.tv_myPointShop)
    private TextView tv_myPointShop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        score = getIntent().getStringExtra("score");
        tv_point.setText(score);

        tv_myPointShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPointActivity.this, ScoreMallActivity.class);
                intent.putExtra("score",score);
                startActivity(intent);
            }
        });


    }


    @Event(R.id.iv_back_point)
    private void imgClick(View view){
        finish();
    }


//    @Event(R.id.tv_myPointShop)
//    private void mallClick(View view){
//        Intent intent = new Intent(MyPointActivity.this, ScoreMallActivity.class);
//        startActivity(intent);
//        finish();
//    }


    @Event(R.id.point_note)
    private void textClick(View view){
        Intent intent = new Intent(MyPointActivity.this, PointRuleActivity.class);
        startActivity(intent);
        finish();
    }


}
