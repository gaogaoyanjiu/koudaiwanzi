package com.example.administrator.koudaiwanzi.meatball;

import java.util.List;

/**
 * Created by Administrator on 2016/7/11.
 */
public class DetailVideoBean {

    /**
     * DetailUrl : null
     * msg : 0
     * BLOGIMG : []
     * COMMENT : [{"COMMENTARY":"大蚂蚁","CREATETIME":1467648000,"HEADURL":"/Image/UserHeadImg/4aed4061-824a-43df-b49b-3297e15562d8.jpg","NICKNAME":"把卡司机"},{"COMMENTARY":"彪额！！！！！","CREATETIME":1467648000,"HEADURL":"/Image/UserHeadImg/b3ec1371-32b3-4fd8-98df-7fd5b8fa4a32.jpg","NICKNAME":"似不似彪"}]
     * HEADS : [{"HEADURL":"/Image/UserHeadImg/b3ec1371-32b3-4fd8-98df-7fd5b8fa4a32.jpg"},{"HEADURL":"/Image/UserHeadImg/4aed4061-824a-43df-b49b-3297e15562d8.jpg"}]
     * HEADURL : /Image/manager/HeadImg/head.jpg
     * ITEMS : [{"DetailUrl":"Items.svc//item//6a8d746c-c7b4-467d-97a1-754bbdd6b7c0//db534290-4e37-4db1-ac51-b7caffec874d","msg":0,"BARGAIN":50,"CID":"6a8d746c-c7b4-467d-97a1-754bbdd6b7c0","DISCOUNT":5,"ICOURL":"/Image/ico/77eb68bd-b823-46dc-ae58-12c1b6aa96ff.jpg","NID":null,"PRICE":100,"TRADENAME":"大虾米"},{"DetailUrl":"Items.svc//item//4e5672ac-522d-4066-bcb2-475e58327781//db534290-4e37-4db1-ac51-b7caffec874d","msg":0,"BARGAIN":160.3,"CID":"4e5672ac-522d-4066-bcb2-475e58327781","DISCOUNT":7,"ICOURL":"/Image/ico/abbef866-dbcc-42b8-94ea-ccc1d830ace5.jpg","NID":null,"PRICE":229,"TRADENAME":"眼线"},{"DetailUrl":"Items.svc//item//3b7e436c-0c12-4f16-8362-da477b71632a//db534290-4e37-4db1-ac51-b7caffec874d","msg":0,"BARGAIN":70.2,"CID":"3b7e436c-0c12-4f16-8362-da477b71632a","DISCOUNT":9,"ICOURL":"/Image/ico/ebd63921-a256-4eca-afb2-7e09b30a8d74.jpg","NID":null,"PRICE":78,"TRADENAME":"BB霜"}]
     * LIKES : 2
     * NID : 5f560e76-b058-4307-8663-ad1ddf68ab4f
     * NOTES : null
     * PREVIEW : /Image/blog/Img/bw.jpg
     * THEMETYPE : 1
     * USERNAME : 无敌大山炮
     * VIDEOURL : /Image/blog/video/1134.mp4
     */
    private String LIKESURL;

    public String getLIKESURL() {
        return LIKESURL;
    }

    public void setLIKESURL(String LIKESURL) {
        this.LIKESURL = LIKESURL;
    }
  private  String ADDCONCEMURL;

    public String getADDCONCEMURL() {
        return ADDCONCEMURL;
    }



    public void setADDCONCEMURL(String ADDCONCEMURL) {
        this.ADDCONCEMURL = ADDCONCEMURL;
    }

    public String getDELCONCEMURL() {
        return DELCONCEMURL;
    }

    public void setDELCONCEMURL(String DELCONCEMURL) {
        this.DELCONCEMURL = DELCONCEMURL;
    }

    public int getCONCEMSTATE() {
        return CONCEMSTATE;
    }

    public void setCONCEMSTATE(int CONCEMSTATE) {
        this.CONCEMSTATE = CONCEMSTATE;
    }
    private String COMMENTURL;

    public String getCOMMENTURL() {
        return COMMENTURL;
    }

    public void setCOMMENTURL(String COMMENTURL) {
        this.COMMENTURL = COMMENTURL;
    }

    private int CONCEMSTATE;
    private String DELCONCEMURL;
    private Object DetailUrl;
    private int msg;
    private String HEADURL;
    private int LIKES;
    private String NID;
    private String NOTES;
    private String PREVIEW;
    private int THEMETYPE;
    private String USERNAME;
    private String VIDEOURL;
    private List<?> BLOGIMG;
    /**
     * COMMENTARY : 大蚂蚁
     * CREATETIME : 1467648000
     * HEADURL : /Image/UserHeadImg/4aed4061-824a-43df-b49b-3297e15562d8.jpg
     * NICKNAME : 把卡司机
     */

    private List<COMMENTBean> COMMENT;
    /**
     * HEADURL : /Image/UserHeadImg/b3ec1371-32b3-4fd8-98df-7fd5b8fa4a32.jpg
     */

    private List<HEADSBean> HEADS;
    /**
     * DetailUrl : Items.svc//item//6a8d746c-c7b4-467d-97a1-754bbdd6b7c0//db534290-4e37-4db1-ac51-b7caffec874d
     * msg : 0
     * BARGAIN : 50.0
     * CID : 6a8d746c-c7b4-467d-97a1-754bbdd6b7c0
     * DISCOUNT : 5.0
     * ICOURL : /Image/ico/77eb68bd-b823-46dc-ae58-12c1b6aa96ff.jpg
     * NID : null
     * PRICE : 100.0
     * TRADENAME : 大虾米
     */

    private List<ITEMSBean> ITEMS;

    public Object getDetailUrl() {
        return DetailUrl;
    }

    public void setDetailUrl(Object DetailUrl) {
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

    public int getTHEMETYPE() {
        return THEMETYPE;
    }

    public void setTHEMETYPE(int THEMETYPE) {
        this.THEMETYPE = THEMETYPE;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getVIDEOURL() {
        return VIDEOURL;
    }

    public void setVIDEOURL(String VIDEOURL) {
        this.VIDEOURL = VIDEOURL;
    }

    public List<?> getBLOGIMG() {
        return BLOGIMG;
    }

    public void setBLOGIMG(List<?> BLOGIMG) {
        this.BLOGIMG = BLOGIMG;
    }

    public List<COMMENTBean> getCOMMENT() {
        return COMMENT;
    }

    public void setCOMMENT(List<COMMENTBean> COMMENT) {
        this.COMMENT = COMMENT;
    }

    public List<HEADSBean> getHEADS() {
        return HEADS;
    }

    public void setHEADS(List<HEADSBean> HEADS) {
        this.HEADS = HEADS;
    }

    public List<ITEMSBean> getITEMS() {
        return ITEMS;
    }

    public void setITEMS(List<ITEMSBean> ITEMS) {
        this.ITEMS = ITEMS;
    }

    public static class COMMENTBean {
        private String COMMENTARY;
        private int CREATETIME;
        private String HEADURL;
        private String NICKNAME;

        public String getCOMMENTARY() {
            return COMMENTARY;
        }

        public void setCOMMENTARY(String COMMENTARY) {
            this.COMMENTARY = COMMENTARY;
        }

        public int getCREATETIME() {
            return CREATETIME;
        }

        public void setCREATETIME(int CREATETIME) {
            this.CREATETIME = CREATETIME;
        }

        public String getHEADURL() {
            return HEADURL;
        }

        public void setHEADURL(String HEADURL) {
            this.HEADURL = HEADURL;
        }

        public String getNICKNAME() {
            return NICKNAME;
        }

        public void setNICKNAME(String NICKNAME) {
            this.NICKNAME = NICKNAME;
        }
    }

    public static class HEADSBean {
        private String HEADURL;

        public String getHEADURL() {
            return HEADURL;
        }

        public void setHEADURL(String HEADURL) {
            this.HEADURL = HEADURL;
        }
    }

    public static class ITEMSBean {
        private String DetailUrl;
        private int msg;
        private double BARGAIN;
        private String CID;
        private double DISCOUNT;
        private String ICOURL;
        private Object NID;
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

        public Object getNID() {
            return NID;
        }

        public void setNID(Object NID) {
            this.NID = NID;
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
