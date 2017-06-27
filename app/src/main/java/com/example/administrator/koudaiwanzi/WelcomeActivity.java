package com.example.administrator.koudaiwanzi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.koudaiwanzi.guidepage.GuidePageActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/12.
 */

@ContentView(R.layout.activity_welcome)
public class WelcomeActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Handler handler;
    private Thread thread;
    private boolean mWorking;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        start();

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
//                        imageView.setImageResource(R.mipmap.www);
                        break;
                    case 2:
                        sharedPreferences = getSharedPreferences("Shop", Context.MODE_PRIVATE);
                        if (sharedPreferences.getBoolean("first", true)) {
                            editor = sharedPreferences.edit();
                            editor.putBoolean("first", false);
                            editor.commit();
                            Intent intent = new Intent(WelcomeActivity.this, GuidePageActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        break;
                }
                return false;
            }
        });

//        thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 1; i < 3; i++){
//                    try {
//                        Thread.sleep(1000);
//                        handler.sendEmptyMessage(i);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        }).start();

//        thread = new Thread(){
//            @Override
//            public void run() {
//                super.run();
//
//                for (int i = 1; i < 3; i++){
//                    try {
//                        Thread.sleep(1000);
//                        handler.sendEmptyMessage(i);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//        thread.start();

    }


    public void start() {
        mWorking = true;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {

                if (mWorking) {
                    for (int i = 1; i < 3; i++) {
                        try {
                            Thread.sleep(1000);
                            handler.sendEmptyMessage(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        thread.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWorking = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mWorking = false;
    }
}
