package com.example.administrator.koudaiwanzi.person.question;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.koudaiwanzi.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

/**
 * Created by Administrator on 2016/9/1.
 */
@ContentView(R.layout.activity_order_question)
public class OrderQuestionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }
    @Event(R.id.iv_order_back)
    private void rlClick(View view) {
        Intent intent = new Intent(OrderQuestionActivity.this,QuestionActivity.class);
        startActivity(intent);
        finish();

    }
}
