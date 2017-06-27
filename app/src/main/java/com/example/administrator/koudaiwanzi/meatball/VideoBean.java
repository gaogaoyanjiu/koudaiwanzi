package com.example.administrator.koudaiwanzi.meatball;

import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 */
public class VideoBean {


    /**
     * DetailUrl : Items.svc//blogdetailed//5f560e76-b058-4307-8663-ad2ddf68ab4f//db534290-4e37-4db1-ac51-b7caffec874d
     * msg : 1
     * HEADURL : /Image/manager/HeadImg/head.jpg
     * ITEMS : []
     * LIKES : 1
     * NID : 5f560e76-b058-4307-8663-ad2ddf68ab4f
     * NOTES : 夏天到了，不想冬天那么冷...
     * PREVIEW : /Image/blog/Img/bw.jpg
     * THEMENAME : 夏天穿棉袄？  当然可能了  北极10日游专为夏日北极之旅打造，人类极少踏足之地。
     * THEMETYPE : 0
     * US : db534290-4e37-4db1-ac51-b7caffec874d
     * USERNAME : 无敌大山炮
     * VIDEOURL : null
     */

    private String DetailUrl;
    private int msg;
    private String HEADURL;
    private int LIKES;
    private String LIKESURL;

    public void setLIKESURL(String LIKESURL) {
        this.LIKESURL = LIKESURL;
    }

    public String getLIKESURL() {

        return LIKESURL;
    }

    private String NID;
    private String NOTES;
    private String PREVIEW;
    private String THEMENAME;
    private int THEMETYPE;
    private String US;
    private String USERNAME;
    private Object VIDEOURL;
    private List<ITEMSBean> ITEMS;

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

    public String getHEADURL() {
        return HEADURL;
    }

    public void setHEADURL(String HEADURL) {
        this.HEADURL = HEADURL;
    }

    public int getLIKES() {
        return LIKES;
    }

    public void setLIKES(int LIKES) {
        this.LIKES = LIKES;
    }

    public String getNID() {
        return NID;
    }

    public void setNID(String NID) {
        this.NID = NID;
    }

    public String getNOTES() {
        return NOTES;
    }

    public void setNOTES(String NOTES) {
        this.NOTES = NOTES;
    }

    public String getPREVIEW() {
        return PREVIEW;
    }

    public void setPREVIEW(String PREVIEW) {
        this.PREVIEW = PREVIEW;
    }

    public String getTHEMENAME() {
        return THEMENAME;
    }

    public void setTHEMENAME(String THEMENAME) {
        this.THEMENAME = THEMENAME;
    }

    public int getTHEMETYPE() {
        return THEMETYPE;
    }

    public void setTHEMETYPE(int THEMETYPE) {
        this.THEMETYPE = THEMETYPE;
    }

    public String getUS() {
        return US;
    }

    public void setUS(String US) {
        this.US = US;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public Object getVIDEOURL() {
        return VIDEOURL;
    }

    public void setVIDEOURL(Object VIDEOURL) {
        this.VIDEOURL = VIDEOURL;
    }

    public List<ITEMSBean> getITEMS() {
        return ITEMS;
    }

    public void setITEMS(List<ITEMSBean> ITEMS) {
        this.ITEMS = ITEMS;
    }
    public static class ITEMSBean {
        private String DetailUrl;
        private int msg;
        private String ADCART;
        private double BARGAIN;
        private String CID;
        private double DISCOUNT;
        private String ICOURL;
        private double PRICE;
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
}
