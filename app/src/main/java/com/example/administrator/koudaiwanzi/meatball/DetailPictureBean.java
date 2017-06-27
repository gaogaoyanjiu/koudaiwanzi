package com.example.administrator.koudaiwanzi.meatball;

import java.util.List;

/**
 * Created by Administrator on 2016/7/8.
 */
public class DetailPictureBean {
    /**
     * DetailUrl : null
     * msg : 0
     * BLOGIMG : [{"IMGINDEX":1,"IMGURL":"/Image/icotable/0c1012a0-3851-4b51-9dd6-43bca8d4814f.jpg"},{"IMGINDEX":2,"IMGURL":"/Image/icotable/0ddf78fc-ecfb-479f-b504-9d6ad15bb2e7.jpg"}]
     * COMMENT : [{"COMMENTARY":"我的妞","CREATETIME":1467648000,"HEADURL":"/Image/UserHeadImg/b3ec1371-32b3-4fd8-98df-7fd5b8fa4a32.jpg","NICKNAME":"似不似彪"}]
     * HEADS : [{"HEADURL":"/Image/UserHeadImg/b3ec1371-32b3-4fd8-98df-7fd5b8fa4a32.jpg"}]
     * HEADURL : /Image/manager/HeadImg/head.jpg
     * ITEMS : []
     * LIKES : 1
     * NID : 5f560e76-b058-4307-8663-ad2ddf68ab4f
     * NOTES : 夏天到了，不想冬天那么冷，不像秋天那么干，不想春天那么潮，可以光着屁股满街跑了。穿一件衣服是穿，穿两件衣服也是穿为什么不多穿几件呢？ 热？索性不穿衣服轻松度过炎热的夏天。（友情提示裸奔，夏天裸奔与警察局最配了）。
     * PREVIEW : /Image/blog/Img/bw.jpg
     * THEMETYPE : 0
     * USERNAME : 无敌大山炮
     * VIDEOURL : null
     */
    private String LIKESURL;
    private String USERHEAD;

    public void setUSERHEAD(String USERHEAD) {
        this.USERHEAD = USERHEAD;
    }

    public String getUSERHEAD() {
        return USERHEAD;
    }

    public String getLIKESURL() {
        return LIKESURL;
    }

    public void setLIKESURL(String LIKESURL) {
        this.LIKESURL = LIKESURL;
    }

    private Object DetailUrl;
    private int msg;
    private String HEADURL;
    private int LIKES;
    private String NID;
    private String NOTES;
    private String PREVIEW;
    private int THEMETYPE;
    private String USERNAME;
    private Object VIDEOURL;
    private String ADDCONCEMURL;
    private int CONCEMSTATE;
    private String COMMENTURL;

    public void setCOMMENTURL(String COMMENTURL) {
        this.COMMENTURL = COMMENTURL;
    }

    public String getCOMMENTURL() {

        return COMMENTURL;
    }

    public void setCONCEMSTATE(int CONCEMSTATE) {
        this.CONCEMSTATE = CONCEMSTATE;
    }

    public int getCONCEMSTATE() {

        return CONCEMSTATE;
    }

    public void setDELCONCEMURL(String DELCONCEMURL) {
        this.DELCONCEMURL = DELCONCEMURL;
    }

    public void setADDCONCEMURL(String ADDCONCEMURL) {

        this.ADDCONCEMURL = ADDCONCEMURL;
    }

    public String getADDCONCEMURL() {

        return ADDCONCEMURL;
    }

    public String getDELCONCEMURL() {

        return DELCONCEMURL;
    }

    private String DELCONCEMURL;

    /**
     * IMGINDEX : 1
     * IMGURL : /Image/icotable/0c1012a0-3851-4b51-9dd6-43bca8d4814f.jpg
     */

    private List<BLOGIMGBean> BLOGIMG;
    /**
     * COMMENTARY : 我的妞
     * CREATETIME : 1467648000
     * HEADURL : /Image/UserHeadImg/b3ec1371-32b3-4fd8-98df-7fd5b8fa4a32.jpg
     * NICKNAME : 似不似彪
     */

    private List<COMMENTBean> COMMENT;
    /**
     * HEADURL : /Image/UserHeadImg/b3ec1371-32b3-4fd8-98df-7fd5b8fa4a32.jpg
     */

    private List<HEADSBean> HEADS;
    private List<?> ITEMS;

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

    public Object getVIDEOURL() {
        return VIDEOURL;
    }

    public void setVIDEOURL(Object VIDEOURL) {
        this.VIDEOURL = VIDEOURL;
    }

    public List<BLOGIMGBean> getBLOGIMG() {
        return BLOGIMG;
    }

    public void setBLOGIMG(List<BLOGIMGBean> BLOGIMG) {
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

    public List<?> getITEMS() {
        return ITEMS;
    }

    public void setITEMS(List<?> ITEMS) {
        this.ITEMS = ITEMS;
    }

    public static class BLOGIMGBean {
        private int IMGINDEX;
        private String IMGURL;

        public int getIMGINDEX() {
            return IMGINDEX;
        }

        public void setIMGINDEX(int IMGINDEX) {
            this.IMGINDEX = IMGINDEX;
        }

        public String getIMGURL() {
            return IMGURL;
        }

        public void setIMGURL(String IMGURL) {
            this.IMGURL = IMGURL;
        }
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
}
