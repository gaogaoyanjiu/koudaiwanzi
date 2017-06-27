package com.example.administrator.koudaiwanzi.person.point.pointmall.scoremall;

import java.util.List;

/**
 * Created by Administrator on 2016/7/14.
 */
public class ScoreBean {
    /**
     * DetailUrl : Users.svc//exrecord//d17ada19-b1f0-4003-b857-f1e671f6a0f0
     * msg : 0
     * ITEMS : [{"DetailUrl":null,"msg":0,"CANAME":"低价雷克斯","ICOURL":"/Image/img/76c46d01-c032-4eed-8aee-3f38965cfa76.jpg","POINTS":80,"POSTAGE":100,"TETYPE":0},{"DetailUrl":null,"msg":0,"CANAME":"阿玛吸烟路","ICOURL":"/Image/img/09be780f-21bd-42d6-b04c-38fccc123e06.jpg","POINTS":50,"POSTAGE":50,"TETYPE":0}]
     * POINTS : 0
     * SERIVCE : [{"DetailUrl":null,"msg":0,"CANAME":"日本豪华双人型","ICOURL":"/Image/type/f2.jpg","POINTS":2000,"POSTAGE":20,"TETYPE":1}]
     */

    private String DetailUrl;
    private int msg;
    private int POINTS;
    /**
     * DetailUrl : null
     * msg : 0
     * CANAME : 低价雷克斯
     * ICOURL : /Image/img/76c46d01-c032-4eed-8aee-3f38965cfa76.jpg
     * POINTS : 80
     * POSTAGE : 100.0
     * TETYPE : 0
     */

    private List<ITEMSBean> ITEMS;
    /**
     * DetailUrl : null
     * msg : 0
     * CANAME : 日本豪华双人型
     * ICOURL : /Image/type/f2.jpg
     * POINTS : 2000
     * POSTAGE : 20.0
     * TETYPE : 1
     */

    private List<SERIVCEBean> SERIVCE;

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

    public int getPOINTS() {
        return POINTS;
    }

    public void setPOINTS(int POINTS) {
        this.POINTS = POINTS;
    }

    public List<ITEMSBean> getITEMS() {
        return ITEMS;
    }

    public void setITEMS(List<ITEMSBean> ITEMS) {
        this.ITEMS = ITEMS;
    }

    public List<SERIVCEBean> getSERIVCE() {
        return SERIVCE;
    }

    public void setSERIVCE(List<SERIVCEBean> SERIVCE) {
        this.SERIVCE = SERIVCE;
    }

    public static class ITEMSBean {
        private Object DetailUrl;
        private int msg;
        private String CANAME;
        private String ICOURL;
        private int POINTS;
        private double POSTAGE;
        private int TETYPE;

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

        public String getCANAME() {
            return CANAME;
        }

        public void setCANAME(String CANAME) {
            this.CANAME = CANAME;
        }

        public String getICOURL() {
            return ICOURL;
        }

        public void setICOURL(String ICOURL) {
            this.ICOURL = ICOURL;
        }

        public int getPOINTS() {
            return POINTS;
        }

        public void setPOINTS(int POINTS) {
            this.POINTS = POINTS;
        }

        public double getPOSTAGE() {
            return POSTAGE;
        }

        public void setPOSTAGE(double POSTAGE) {
            this.POSTAGE = POSTAGE;
        }

        public int getTETYPE() {
            return TETYPE;
        }

        public void setTETYPE(int TETYPE) {
            this.TETYPE = TETYPE;
        }
    }

    public static class SERIVCEBean {
        private Object DetailUrl;
        private int msg;
        private String CANAME;
        private String ICOURL;
        private int POINTS;
        private double POSTAGE;
        private int TETYPE;

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

        public String getCANAME() {
            return CANAME;
        }

        public void setCANAME(String CANAME) {
            this.CANAME = CANAME;
        }

        public String getICOURL() {
            return ICOURL;
        }

        public void setICOURL(String ICOURL) {
            this.ICOURL = ICOURL;
        }

        public int getPOINTS() {
            return POINTS;
        }

        public void setPOINTS(int POINTS) {
            this.POINTS = POINTS;
        }

        public double getPOSTAGE() {
            return POSTAGE;
        }

        public void setPOSTAGE(double POSTAGE) {
            this.POSTAGE = POSTAGE;
        }

        public int getTETYPE() {
            return TETYPE;
        }

        public void setTETYPE(int TETYPE) {
            this.TETYPE = TETYPE;
        }
    }
}
