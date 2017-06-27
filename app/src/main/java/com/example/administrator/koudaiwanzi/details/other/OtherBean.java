package com.example.administrator.koudaiwanzi.details.other;

/**
 * Created by Administrator on 2016/7/9.
 */
public class OtherBean {

    /**
     * DetailUrl : Items.svc//item//1d320602-e0db-4e38-9a44-6a2d5e758061
     * msg : 0
     * BARGAIN : 0
     * CID : 1d320602-e0db-4e38-9a44-6a2d5e758061
     * DISCOUNT : 0
     * ICOURL : null
     * PRICE : 0
     * SALESVOL : 0
     * TRADENAME :
     */

    private String DetailUrl;
    private int msg;
    private double BARGAIN;
    private String CID;
    private double DISCOUNT;
    private String ICOURL;
    private double PRICE;
    private int SALESVOL;
    private String TRADENAME;

    public int getISOUTSTOCK() {
        return ISOUTSTOCK;
    }

    public void setISOUTSTOCK(int ISOUTSTOCK) {
        this.ISOUTSTOCK = ISOUTSTOCK;
    }

    private int ISOUTSTOCK;

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

    public double getBARGAIN() {
        return BARGAIN;
    }

    public void setBARGAIN(double BARGAIN) {
        this.BARGAIN = BARGAIN;
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

    public String getTRADENAME() {
        return TRADENAME;
    }

    public void setTRADENAME(String TRADENAME) {
        this.TRADENAME = TRADENAME;
    }
}
