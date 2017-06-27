package com.example.administrator.koudaiwanzi.shopcar;

/**
 * Created by Administrator on 2016/7/28.
 */
public class MergeGetBean {
    /**
     * DetailUrl : null
     * msg : 0
     * DISCOUNT : 0
     * TOTAL : 0
     */

    private String DetailUrl;
    private int msg;
    private double DISCOUNT;
    private double TOTAL;

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

    public double getDISCOUNT() {
        return DISCOUNT;
    }

    public void setDISCOUNT(double DISCOUNT) {
        this.DISCOUNT = DISCOUNT;
    }

    public double getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(double TOTAL) {
        this.TOTAL = TOTAL;
    }
}
