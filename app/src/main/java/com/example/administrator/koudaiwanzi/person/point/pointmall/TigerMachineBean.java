package com.example.administrator.koudaiwanzi.person.point.pointmall;

import java.util.List;

/**
 * Created by Administrator on 2016/8/24.
 */
public class TigerMachineBean {

    /**
     * DetailUrl : null
     * msg : 0
     * GETDRAWURL : Integral.svc/GetPrize/1
     * HISDRAW : [{"DetailUrl":null,"msg":0,"CREATETIME":0,"NICKNAME":"WZ_3956197222","ZID":"单肩包"}]
     * HISPOINTURL : Integral.svc/hispoint/1
     * POINT : 0
     */

    private Object DetailUrl;
    private int msg;
    private String GETDRAWURL;
    private String HISPOINTURL;
    private int POINT;
    /**
     * DetailUrl : null
     * msg : 0
     * CREATETIME : 0
     * NICKNAME : WZ_3956197222
     * ZID : 单肩包
     */

    private List<HISDRAWBean> HISDRAW;

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

    public static class HISDRAWBean {
        private Object DetailUrl;
        private int msg;
        private int CREATETIME;
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

        public int getCREATETIME() {
            return CREATETIME;
        }

        public void setCREATETIME(int CREATETIME) {
            this.CREATETIME = CREATETIME;
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
