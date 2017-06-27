package com.example.administrator.koudaiwanzi.person.question;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.meatball.BackBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016/8/30.
 */
@ContentView(R.layout.activity_question)
public class QuestionActivity extends AppCompatActivity {
    private String UID;

    @ViewInject(R.id.rl_title1)
    private RelativeLayout rl_titlel;

    @ViewInject(R.id.rl_title2)
    private RelativeLayout rl_title2;

    @ViewInject(R.id.rl_title3)
    private RelativeLayout rl_title3;

    @ViewInject(R.id.rl_title4)
    private RelativeLayout rl_title4;

    @ViewInject(R.id.rl_title5)
    private RelativeLayout rl_title5;

    @ViewInject(R.id.rl_title6)
    private RelativeLayout rl_title6;

    @ViewInject(R.id.rl_title7)
    private RelativeLayout rl_title7;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        SharedPreferences share = QuestionActivity.this.getSharedPreferences("logIn", Context.MODE_PRIVATE);
        UID = share.getString("login", "");
        rl_titlel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionActivity.this, IntegralActivity.class);
                startActivity(intent);
                finish();
            }
        });

        rl_title2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionActivity.this, TariffActivity.class);
                startActivity(intent);
                finish();
            }
        });

        rl_title3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionActivity.this, DiscountActivity.class);
                startActivity(intent);
                finish();
            }
        });
        rl_title4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionActivity.this, OrderQuestionActivity.class);
                startActivity(intent);
                finish();
            }
        });

        rl_title5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionActivity.this, LogisticsActivity.class);
                startActivity(intent);
                finish();
            }
        });
        rl_title6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionActivity.this, ServerQuestionActivity.class);
                startActivity(intent);
                finish();
            }
        });
        rl_title7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionActivity.this, ServerEmailActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Event(R.id.iv_q_a_back)
    private void rlClick(View view) {
        finish();
    }
}
