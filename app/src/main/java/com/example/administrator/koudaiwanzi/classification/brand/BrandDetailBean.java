package com.example.administrator.koudaiwanzi.classification.brand;

/**
 * Created by Administrator on 2016/8/31.
 */
public class BrandDetailBean {


    /**
     * DetailUrl : Items.svc//item//47e72c2c-cac3-48d6-bf31-e2147c48bdfc
     * msg : 0
     * BARGAIN : 0
     * BONAME : null
     * CAPACITY : 0
     * CID : 47e72c2c-cac3-48d6-bf31-e2147c48bdfc
     * DISCOUNT : 0.0
     * ICOURL : /Image/ico/964b177e-f128-4621-873b-3bfa88978782.jpg
     * PRICE : 12.0
     * SALESVOL : 0
     * SORTPRICE : 0
     * TRADENAME : bake经典 浓滑奶油芝士味朱古力
     */

    private String DetailUrl;
    private int msg;
    private int BARGAIN;
    private Object BONAME;
    private int CAPACITY;
    private String CID;
    private double DISCOUNT;
    private String ICOURL;
    private double PRICE;
    private int SALESVOL;
    private int SORTPRICE;
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

    public Object getBONAME() {
        return BONAME;
    }

    public void setBONAME(Object BONAME) {
        this.BONAME = BONAME;
    }

    public int getCAPACITY() {
        return CAPACITY;
    }

    public void setCAPACITY(int CAPACITY) {
        this.CAPACITY = CAPACITY;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
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

    public double getPRICE() {
        return PRICE;
    }

    public void setPRICE(double PRICE) {
        this.PRICE = PRICE;
    }

    public int getSALESVOL() {
        return SALESVOL;
    }

    public void setSALESVOL(int SALESVOL) {
        this.SALESVOL = SALESVOL;
    }

    public int getSORTPRICE() {
        return SORTPRICE;
    }

    public void setSORTPRICE(int SORTPRICE) {
        this.SORTPRICE = SORTPRICE;
    }

    public String getTRADENAME() {
        return TRADENAME;
    }

    public void setTRADENAME(String TRADENAME) {
        this.TRADENAME = TRADENAME;
    }
}
