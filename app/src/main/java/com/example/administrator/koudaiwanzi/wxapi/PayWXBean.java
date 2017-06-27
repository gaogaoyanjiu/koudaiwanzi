package com.example.administrator.koudaiwanzi.wxapi;

/**
 * Created by Administrator on 2016/9/22.
 */
public class PayWXBean {



    /**
     * DetailUrl : Users.svc/userpay/c259a4a7-a848-4d6e-80f5-9ab9e84c2495/b2fd42d6-a6f0-4f26-8483-cc6e3f07ecee
     * msg : 1
     */

    private String DetailUrl;
    private int msg;

    public String getDetailUrl() {
        return DetailUrl;
    }

    public void setDetailUrl(String DetailUrl) {
        this.DetailUrl = DetailUrl;
    }

    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }
}
