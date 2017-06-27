package com.example.administrator.koudaiwanzi.person.point.pointmall;

import java.util.List;

/**
 * Created by Administrator on 2016/09/29.
 */
public class YaoBean {


    /**
     * DetailUrl : Integral.svc/lotdraw/1
     * msg : 0
     * GETDRAWURL : Integral.svc/GetNewPrize/1
     * HISDRAW : [{"DetailUrl":null,"msg":0,"CREATTIME":1475117950,"NICKNAME":null,"ZID":"5分"},{"DetailUrl":null,"msg":0,"CREATTIME":1475051755,"NICKNAME":"到底啥子情况嘞","ZID":"5分"},{"DetailUrl":null,"msg":0,"CREATTIME":1475051013,"NICKNAME":"到底啥子情况嘞","ZID":"5分"},{"DetailUrl":null,"msg":0,"CREATTIME":1475048955,"NICKNAME":"WZ_4044828768","ZID":"5分"},{"DetailUrl":null,"msg":0,"CREATTIME":1475048953,"NICKNAME":"WZ_4044828768","ZID":"30分"}]
     * HISLODRAW : [{"DetailUrl":null,"msg":0,"CREATTIME":1460000000,"NICKNAME":"到底啥子情况嘞","ZID":"单人飞往日本往返机票"}]
     * HISPOINTURL : Integral.svc/hispoint/1
     * POINT : 0
     */

    private String DetailUrl;
    private int msg;
    private String GETDRAWURL;
    private String HISPOINTURL;
    private int POINT;
    /**
     * DetailUrl : null
     * msg : 0
     * CREATTIME : 1475117950
     * NICKNAME : null
     * ZID : 5分
     */

    private List<HISDRAWBean> HISDRAW;
    /**
     * DetailUrl : null
     * msg : 0
     * CREATTIME : 1460000000
     * NICKNAME : 到底啥子情况嘞
     * ZID : 单人飞往日本往返机票
     */

    private List<HISLODRAWBean> HISLODRAW;

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

    public String getGETDRAWURL() {
        return GETDRAWURL;
    }

    public void setGETDRAWURL(String GETDRAWURL) {
        this.GETDRAWURL = GETDRAWURL;
    }

    public String getHISPOINTURL() {
        return HISPOINTURL;
    }

    public void setHISPOINTURL(String HISPOINTURL) {
        this.HISPOINTURL = HISPOINTURL;
    }

    public int getPOINT() {
        return POINT;
    }

    public void setPOINT(int POINT) {
        this.POINT = POINT;
    }

    public List<HISDRAWBean> getHISDRAW() {
        return HISDRAW;
    }

    public void setHISDRAW(List<HISDRAWBean> HISDRAW) {
        this.HISDRAW = HISDRAW;
    }

    public List<HISLODRAWBean> getHISLODRAW() {
        return HISLODRAW;
    }

    public void setHISLODRAW(List<HISLODRAWBean> HISLODRAW) {
        this.HISLODRAW = HISLODRAW;
    }

    public static class HISDRAWBean {
        private Object DetailUrl;
        private int msg;
        private int CREATTIME;
        private Object NICKNAME;
        private String ZID;

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

        public int getCREATTIME() {
            return CREATTIME;
        }

        public void setCREATTIME(int CREATTIME) {
            this.CREATTIME = CREATTIME;
        }

        public Object getNICKNAME() {
            return NICKNAME;
        }

        public void setNICKNAME(Object NICKNAME) {
            this.NICKNAME = NICKNAME;
        }

        public String getZID() {
            return ZID;
        }

        public void setZID(String ZID) {
            this.ZID = ZID;
        }
    }

    public static class HISLODRAWBean {
        private Object DetailUrl;
        private int msg;
        private int CREATTIME;
        private String NICKNAME;
        private String ZID;

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

        public int getCREATTIME() {
            return CREATTIME;
        }

        public void setCREATTIME(int CREATTIME) {
            this.CREATTIME = CREATTIME;
        }

        public String getNICKNAME() {
            return NICKNAME;
        }

        public void setNICKNAME(String NICKNAME) {
            this.NICKNAME = NICKNAME;
        }

        public String getZID() {
            return ZID;
        }

        public void setZID(String ZID) {
            this.ZID = ZID;
        }
    }
}
