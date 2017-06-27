package com.example.administrator.koudaiwanzi.base;

import android.view.View;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/10/20.
 * 用来防止手机卡顿时造成的多次监听， 但是监听中有延时这个方法应该不好用了
 * 把onClikcListener换成NoDoubleClickListener
 */

public abstract class NoDoubleClickListener implements View.OnClickListener {
    public static final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0;


    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onNoDoubleClick(v);
        }

    }

    protected abstract void onNoDoubleClick(View v);

}
