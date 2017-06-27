package com.example.administrator.koudaiwanzi.newproduct;

/**
 * Created by Administrator on 2016/8/31.
 */
public class NEWITEMBean {

    private String DetailUrl;
    private int msg;
    private double BARGAIN;
    private String BONAME;
    private double CAPACITY;
    private String CID;
    private double DISCOUNT;
    private String ICOURL;
    private double PRICE;
    private int SALESVOL;
    private int SORTPRICE;
    private String TRADENAME;

    public int getISOUTSTOCK() {
        return ISOUTSTOCK;
    }

    public void setISOUTSTOCK(int ISOUTSTOCK) {
        this.ISOUTSTOCK = ISOUTSTOCK;
    }

    private int ISOUTSTOCK;

    public String getSTANDARD() {
        return STANDARD;
    }

    public void setSTANDARD(String STANDARD) {
        this.STANDARD = STANDARD;
    }

    private String STANDARD;

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

    public String getBONAME() {
        return BONAME;
    }

    public void setBONAME(String BONAME) {
        this.BONAME = BONAME;
    }

    public double getCAPACITY() {
        return CAPACITY;
    }

    public void setCAPACITY(double CAPACITY) {
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
