package com.example.administrator.koudaiwanzi.person.collection;

/**
 * Created by Administrator on 2016/6/25.
 */
public class CollectionBean {

    /**
     * DetailUrl : Items.svc//item//4e5672ac-522d-4066-bcb2-475e58327781
     * msg : 0
     * ADCART : Items.svc//additem//d17ada19-b1f0-4003-b857-f1e671f6a0f0//4e5672ac-522d-4066-bcb2-475e58327781
     * BARGAIN : 160.3
     * CID : 4e5672ac-522d-4066-bcb2-475e58327781
     * DISCOUNT : 7.0
     * ICOURL : /Image/ico/abbef866-dbcc-42b8-94ea-ccc1d830ace5.jpg
     * PRICE : 229.0
     * TRADENAME : 眼线
     */

    private String DetailUrl;
    private int msg;
    private String ADCART;
    private double BARGAIN;
    private String CID;
    private double DISCOUNT;
    private String ICOURL;
    private double PRICE;
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

    public String getADCART() {
        return ADCART;
    }

    public void setADCART(String ADCART) {
        this.ADCART = ADCART;
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

    public String getTRADENAME() {
        return TRADENAME;
    }

    public void setTRADENAME(String TRADENAME) {
        this.TRADENAME = TRADENAME;
    }
}
