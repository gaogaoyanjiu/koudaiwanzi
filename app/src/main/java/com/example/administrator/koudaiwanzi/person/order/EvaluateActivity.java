package com.example.administrator.koudaiwanzi.person.order;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Administrator on 2016/9/30.
 * 商品订单评价
 */

@ContentView(R.layout.activity_evaluate)
public class EvaluateActivity extends AppCompatActivity{
    private String url;
    private String myUrl = MyUrl.url;
    private EvaluateOrderAdapter adapter;
    private String str ="@GyKdwz,@";//button后面和CID后面拼这个
    private String str2 = "@GyKdwz;@";//text后面拼这个
    private int a,b,c;
    private String text;

    @ViewInject(R.id.lv_evaluate_order)
    private ListView listView;
    //评价按键
    @ViewInject(R.id.rl_evaluate)
    private RelativeLayout rl_evaluate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        String url_p = getIntent().getStringExtra("pingjia");
        url = myUrl + url_p;
        Log.e("dfgfb", url);
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                final EvaluateOrderBean bean = gson.fromJson(s, EvaluateOrderBean.class);
                adapter = new EvaluateOrderAdapter(EvaluateActivity.this, bean.getORDERITEM(), R.layout.activity_ordercm_item, handler);
                listView.setAdapter(adapter);
                final String c = bean.getDetailUrl();

                rl_evaluate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (text == null||text.equals("")){
                            Toast.makeText(EvaluateActivity.this, "输入的评价不能为空", Toast.LENGTH_SHORT).show();
                        }else {
                            String q = bean.getORDERITEM().get(0).getCID();
                            String url_1 = myUrl+ c + q + str + a + str + text;
                            Log.e("fdzv", url_1);
                            finish();
                            RequestParams params = new RequestParams(url_1);
                            x.http().get(params, new CommonCallback<String>() {
                                @Override
                                public void onSuccess(String s) {
                                    Log.e("fdsfv", s);
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


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                a = (int) msg.obj;
            }else if (msg.what == 2){
                a = (int) msg.obj;
            }else if (msg.what == 3){
                a = (int) msg.obj;
            }else if(msg.what == 4){
                text = (String) msg.obj;
            }
        }
    };


    //返回键监听
    @Event(R.id.iv_back_evaluate)
    private void ivClick(View view){
        finish();
    }

}
