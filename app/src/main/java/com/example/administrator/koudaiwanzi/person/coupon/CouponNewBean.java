package com.example.administrator.koudaiwanzi.person.coupon;

/**
 * Created by Administrator on 2016/09/21.
 */
public class CouponNewBean {

    /**
     * DetailUrl : Users.svc/updatecoupon/0730ad4e-3a6d-49d7-b07f-dd2742b7d549
     * msg : 0
     * CNNAME : 瑞丽
     * CNPRICE : 100
     * FULLCUT : 0
     * ISUSED : 0
     * PEROFVAL : 1475164800
     * UR_CP_ID : 0730ad4e-3a6d-49d7-b07f-dd2742b7d549
     */

    private String DetailUrl;
    private int msg;
    private String CNNAME;
    private String CNPRICE;
    private String FULLCUT;
    private int ISUSED;
    private int PEROFVAL;
    private String UR_CP_ID;

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

    public String getCNNAME() {
        return CNNAME;
    }

    public void setCNNAME(String CNNAME) {
        this.CNNAME = CNNAME;
    }

    public String getCNPRICE() {
        return CNPRICE;
    }

    public void setCNPRICE(String CNPRICE) {
        this.CNPRICE = CNPRICE;
    }

    public String getFULLCUT() {
        return FULLCUT;
    }

    public void setFULLCUT(String FULLCUT) {
        this.FULLCUT = FULLCUT;
    }

    public int getISUSED() {
        return ISUSED;
    }

    public void setISUSED(int ISUSED) {
        this.ISUSED = ISUSED;
    }

    public int getPEROFVAL() {
        return PEROFVAL;
    }

    public void setPEROFVAL(int PEROFVAL) {
        this.PEROFVAL = PEROFVAL;
    }

    public String getUR_CP_ID() {
        return UR_CP_ID;
    }

    public void setUR_CP_ID(String UR_CP_ID) {
        this.UR_CP_ID = UR_CP_ID;
    }
}
