package com.example.administrator.koudaiwanzi.jdt;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.administrator.koudaiwanzi.R;


public class JdtActivity extends Activity {
//    //该程序模拟天成长度为100的数组
//    private int[] data = new int[100];
    private TextView jdt;

    int progressStatus = 93;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jdt);

        ProgressBar bar = (ProgressBar) findViewById(R.id.bar);
        jdt = (TextView) findViewById(R.id.tv_jdt);
        jdt.setText("快来抢啊还剩"+String.valueOf(100-progressStatus)+"%");

        bar.setProgress(progressStatus);


        // 创建一个复杂更新进度的Handler
//        final Handler handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                if (msg.what == 0x111) {
//
//                    bar.setProgress(80);
//                }
//            }
//        };
        // 启动线程来执行任务
//        new Thread() {
//            public void run() {
//                while (progressStatus < 100) {
//                    // 获取耗时的完成百分比
//                    progressStatus = hasData;
//                    Message m = new Message();
//                    m.what = 0x111;
//                    // 发送消息到Handler
//                    handler.sendMessage(m);
//                }
//            }
//        }.start();
    }

    //模拟一个耗时的操作
//    private int doWork() {
////        data[hasData++] = (int) (Math.random() * 100);
////        try {
////            Thread.sleep(100);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//        return hasData;
//    }



}