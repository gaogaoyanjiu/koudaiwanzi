package com.example.administrator.koudaiwanzi.person.coupon;

/**
 * Created by Administrator on 2016/09/20.
 */
public class CodeBean {
    private String DetailUrl;
    private int msg;

    public void setDetailUrl(String detailUrl) {
        DetailUrl = detailUrl;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }

    public String getDetailUrl() {
        return DetailUrl;
    }

    public int getMsg() {
        return msg;
    }
}
