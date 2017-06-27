package com.example.administrator.koudaiwanzi.person.point.pointmall;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.BaseFragmentAndroid;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Administrator on 2016/9/21.
 */

@ContentView(R.layout.fragment_yao)
public class YaoFragment extends BaseFragmentAndroid implements SensorEventListener {
    private SensorManager sensorManager;
    private Vibrator vibrator;
    private TextView tv;
    private SoundPool sp;
    private ImageView iv;
    private FrameLayout iv2;
    private AnimationSet animationSet;
    private boolean b = true;

    //点击按钮出来中奖纪录
    @ViewInject(R.id.yaoBtn)
    private Button yaoBtn;

    private String Url;

    @ViewInject(R.id.yao_TV)
    private TextView yao_TV;

    private String url;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Url = RewardActivity.getURL();
        tv = (TextView) view.findViewById(R.id.tv);
        iv = (ImageView) view.findViewById(R.id.iv);
        iv2 = (FrameLayout) view.findViewById(R.id.iv2);
        sensorManager = (SensorManager) getActivity().getSystemService(getActivity().SENSOR_SERVICE);
        vibrator = (Vibrator) getActivity().getSystemService(Service.VIBRATOR_SERVICE);
        //获得声音服务
//        sp = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);

        animationSet = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0.95f);

        translateAnimation.setDuration(1000);
        animationSet.addAnimation(translateAnimation);

        yaoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WindowActivity.class);
                intent.putExtra("window",MyUrl.url + Url);
                startActivity(intent);

            }
        });
    }


    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //当传感器精度改变时回调该方法，Do nothing.
        Log.e("eee", "wwwwwwwwwwww");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();
        //values[0]:X轴，values[1]：Y轴，values[2]：Z轴
        float[] values = event.values;
        if (sensorType == Sensor.TYPE_ACCELEROMETER) {

            if ((Math.abs(values[0]) > 17 || Math.abs(values[1]) > 17 || Math
                    .abs(values[2]) > 17)) {
                if (b == true) {
                    b = false;
                    Log.d("sensor x ", "============ values[0] = " + values[0]);
                    Log.d("sensor y ", "============ values[1] = " + values[1]);
                    Log.d("sensor z ", "============ values[2] = " + values[2]);

                    vibrator.vibrate(500);
                    iv2.setVisibility(View.INVISIBLE);
                    iv.setVisibility(View.VISIBLE);
                    iv.startAnimation(animationSet);
//                    sp.load(this, R.raw.uiui, 1); //第二个参数是音乐资源文件
//                    sp.play(1, 1, 1, 0, 0, 1);//播放声音
                    new Handler().postDelayed(new Runnable() {

                        public void run() {
                            //显示图片
                            iv.setVisibility(View.INVISIBLE);
                            iv2.setVisibility(View.VISIBLE);

                            SharedPreferences share = getActivity().getSharedPreferences("share_url", Context.MODE_PRIVATE);
                            url = share.getString("share_url", "");

                            RequestParams params = new RequestParams(MyUrl.url + url);
                            x.http().get(params, new Callback.CommonCallback<String>() {
                                @Override
                                public void onSuccess(String s) {
                                    Gson gson = new Gson();
                                    YaoFragmentBean bean = gson.fromJson(s,YaoFragmentBean.class);
                                    if(bean.getMsg() == 1){
                                        yao_TV.setText("没有中奖请再接再厉");
                                        yao_TV.setVisibility(View.VISIBLE);
                                    }else {
                                        yao_TV.setText(MyAdapter.getReward());
                                        yao_TV.setVisibility(View.VISIBLE);
                                    }


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



                            b = true;
                        }

                    }, 1000);   //1秒
                }
            }
        }
    }
}
