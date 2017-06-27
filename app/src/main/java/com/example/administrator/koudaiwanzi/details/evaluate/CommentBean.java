package com.example.administrator.koudaiwanzi.details.evaluate;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class CommentBean {


   /**
     * DetailUrl : null
     * msg : 0
     * COMITEMS : [{"DetailUrl":null,"msg":0,"COMMENTNOTE":"8错","CREATETIME":1462896000,"HEADURL":"/Image/UserHeadImg/b3ec1371-32b3-4fd8-98df-7fd5b8fa4a32.jpg","NICKNAME":"似不似彪"}]
     * MENU : {"DetailUrl":null,"msg":0,"ALLCOM":"Items.svc//comment//3//3b7e436c-0c12-4f16-8362-da477b71632a","ALLSTATUE":1,"BADCOM":"Items.svc//comment//2//3b7e436c-0c12-4f16-8362-da477b71632a","BADSTATUE":0,"GOODCOM":"Items.svc//comment//0//3b7e436c-0c12-4f16-8362-da477b71632a","GOODSTATUE":0,"SECCOM":"Items.svc//comment//1//3b7e436c-0c12-4f16-8362-da477b71632a","SECSTATUE":0}
     */

    private Object DetailUrl;
    private int msg;
    /**
     * DetailUrl : null
     * msg : 0
     * ALLCOM : Items.svc//comment//3//3b7e436c-0c12-4f16-8362-da477b71632a
     * ALLSTATUE : 1
     * BADCOM : Items.svc//comment//2//3b7e436c-0c12-4f16-8362-da477b71632a
     * BADSTATUE : 0
     * GOODCOM : Items.svc//comment//0//3b7e436c-0c12-4f16-8362-da477b71632a
     * GOODSTATUE : 0
     * SECCOM : Items.svc//comment//1//3b7e436c-0c12-4f16-8362-da477b71632a
     * SECSTATUE : 0
     */

    private MENUBean MENU;
    /**
     * DetailUrl : null
     * msg : 0
     * COMMENTNOTE : 8错
     * CREATETIME : 1462896000
     * HEADURL : /Image/UserHeadImg/b3ec1371-32b3-4fd8-98df-7fd5b8fa4a32.jpg
     * NICKNAME : 似不似彪
     */

    private List<COMITEMSBean> COMITEMS;

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

    public MENUBean getMENU() {
        return MENU;
    }

    public void setMENU(MENUBean MENU) {
        this.MENU = MENU;
    }

    public List<COMITEMSBean> getCOMITEMS() {
        return COMITEMS;
    }

    public void setCOMITEMS(List<COMITEMSBean> COMITEMS) {
        this.COMITEMS = COMITEMS;
    }

    public static class MENUBean {
        private Object DetailUrl;
        private int msg;
        private String ALLCOM;
        private int ALLSTATUE;
        private String BADCOM;
        private int BADSTATUE;
        private String GOODCOM;
        private int GOODSTATUE;
        private String SECCOM;
        private int SECSTATUE;

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

        public String getALLCOM() {
            return ALLCOM;
        }

        public void setALLCOM(String ALLCOM) {
            this.ALLCOM = ALLCOM;
        }

        public int getALLSTATUE() {
            return ALLSTATUE;
        }

        public void setALLSTATUE(int ALLSTATUE) {
            this.ALLSTATUE = ALLSTATUE;
        }

        public String getBADCOM() {
            return BADCOM;
        }

        public void setBADCOM(String BADCOM) {
            this.BADCOM = BADCOM;
        }

        public int getBADSTATUE() {
            return BADSTATUE;
        }

        public void setBADSTATUE(int BADSTATUE) {
            this.BADSTATUE = BADSTATUE;
        }

        public String getGOODCOM() {
            return GOODCOM;
        }

        public void setGOODCOM(String GOODCOM) {
            this.GOODCOM = GOODCOM;
        }

        public int getGOODSTATUE() {
            return GOODSTATUE;
        }

        public void setGOODSTATUE(int GOODSTATUE) {
            this.GOODSTATUE = GOODSTATUE;
        }

        public String getSECCOM() {
            return SECCOM;
        }

        public void setSECCOM(String SECCOM) {
            this.SECCOM = SECCOM;
        }

        public int getSECSTATUE() {
            return SECSTATUE;
        }

        public void setSECSTATUE(int SECSTATUE) {
            this.SECSTATUE = SECSTATUE;
        }
    }

    public static class COMITEMSBean {
        private Object DetailUrl;
        private int msg;
        private String COMMENTNOTE;
        private int CREATETIME;
        private String HEADURL;
        private String NICKNAME;

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

        public String getCOMMENTNOTE() {
            return COMMENTNOTE;
        }

        public void setCOMMENTNOTE(String COMMENTNOTE) {
            this.COMMENTNOTE = COMMENTNOTE;
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
}
