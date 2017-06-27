package com.example.administrator.koudaiwanzi.person.reject;

/**
 * Created by Administrator on 2016/9/29.
 */
public class MyRejectBean {


    /**
     * DetailUrl : Users.svc//retumrequest//5d1cd6f9-f7a0-4065-821c-0168d1dae513/b2fd42d6-a6f0-4f26-8483-cc6e3f07ecee
     * msg : 0
     * BARGAIN : 0
     * BONAME : AROMA RESORT
     * DISCOUNT : 0.0
     * ICOURL : /Image/ico/2b12530e-bcea-456d-81dd-c445d3b3db7d.jpg
     * ISRETURN : 0
     * MID : 5d1cd6f9-f7a0-4065-821c-0168d1dae513
     * PRICE : 26.0
     * QUANTITY : 1
     * TRADENAME : lululun面膜 加强保湿型 7片装
     */

    private String DetailUrl;
    private int msg;
    private int BARGAIN;
    private String BONAME;
    private double DISCOUNT;
    private String ICOURL;
    private int ISRETURN;
    private String MID;
    private double PRICE;
    private int QUANTITY;
    private String TRADENAME;

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

    public int getBARGAIN() {
        return BARGAIN;
    }

    public void setBARGAIN(int BARGAIN) {
        this.BARGAIN = BARGAIN;
    }

    public String getBONAME() {
        return BONAME;
    }

    public void setBONAME(String BONAME) {
        this.BONAME = BONAME;
    }

    public double getDISCOUNT() {
        return DISCOUNT;
    }

    public void setDISCOUNT(double DISCOUNT) {
        this.DISCOUNT = DISCOUNT;
    }

    public String getICOURL() {
        return ICOURL;
    }

    public void setICOURL(String ICOURL) {
        this.ICOURL = ICOURL;
    }

    public int getISRETURN() {
        return ISRETURN;
    }

    public void setISRETURN(int ISRETURN) {
        this.ISRETURN = ISRETURN;
    }

    public String getMID() {
        return MID;
    }

    public void setMID(String MID) {
        this.MID = MID;
    }

    public double getPRICE() {
        return PRICE;
    }

    public void setPRICE(double PRICE) {
        this.PRICE = PRICE;
    }

    public int getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(int QUANTITY) {
        this.QUANTITY = QUANTITY;
    }

    public String getTRADENAME() {
        return TRADENAME;
    }

    public void setTRADENAME(String TRADENAME) {
        this.TRADENAME = TRADENAME;
    }
}
