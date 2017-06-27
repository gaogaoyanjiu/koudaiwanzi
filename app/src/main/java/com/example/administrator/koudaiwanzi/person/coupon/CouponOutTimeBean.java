package com.example.administrator.koudaiwanzi.person.coupon;

import java.util.List;

/**
 * Created by Administrator on 2016/6/24.
 */
public class CouponOutTimeBean {

    /**
     * DetailUrl : null
     * msg : 0
     * CP : [{"CNNAME":"AMS优惠","CNPICURL":"/Image/coupon/9af6e44e-3834-4ded-a86f-0e055c3bd6fb.jpeg","CNPRICE":50,"ISUSED":0,"PEROFVAL":1464537600}]
     * MENU : {"NOTUSED":{"NUM":0,"URL":null},"OUTOFDATE":{"NUM":0,"URL":null},"USED":{"NUM":0,"URL":null}}
     */

    private Object DetailUrl;
    private int msg;
    /**
     * NOTUSED : {"NUM":0,"URL":null}
     * OUTOFDATE : {"NUM":0,"URL":null}
     * USED : {"NUM":0,"URL":null}
     */

    private MENUBean MENU;
    /**
     * CNNAME : AMS优惠
     * CNPICURL : /Image/coupon/9af6e44e-3834-4ded-a86f-0e055c3bd6fb.jpeg
     * CNPRICE : 50.0
     * ISUSED : 0
     * PEROFVAL : 1464537600
     */

    private List<CPBean> CP;

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

    public List<CPBean> getCP() {
        return CP;
    }

    public void setCP(List<CPBean> CP) {
        this.CP = CP;
    }

    public static class MENUBean {
        /**
         * NUM : 0
         * URL : null
         */

        private NOTUSEDBean NOTUSED;
        /**
         * NUM : 0
         * URL : null
         */

        private OUTOFDATEBean OUTOFDATE;
        /**
         * NUM : 0
         * URL : null
         */

        private USEDBean USED;

        public NOTUSEDBean getNOTUSED() {
            return NOTUSED;
        }

        public void setNOTUSED(NOTUSEDBean NOTUSED) {
            this.NOTUSED = NOTUSED;
        }

        public OUTOFDATEBean getOUTOFDATE() {
            return OUTOFDATE;
        }

        public void setOUTOFDATE(OUTOFDATEBean OUTOFDATE) {
            this.OUTOFDATE = OUTOFDATE;
        }

        public USEDBean getUSED() {
            return USED;
        }

        public void setUSED(USEDBean USED) {
            this.USED = USED;
        }

        public static class NOTUSEDBean {
            private int NUM;
            private Object URL;

            public int getNUM() {
                return NUM;
            }

            public void setNUM(int NUM) {
                this.NUM = NUM;
            }

            public Object getURL() {
                return URL;
            }

            public void setURL(Object URL) {
                this.URL = URL;
            }
        }

        public static class OUTOFDATEBean {
            private int NUM;
            private Object URL;

            public int getNUM() {
                return NUM;
            }

            public void setNUM(int NUM) {
                this.NUM = NUM;
            }

            public Object getURL() {
                return URL;
            }

            public void setURL(Object URL) {
                this.URL = URL;
            }
        }

        public static class USEDBean {
            private int NUM;
            private Object URL;

            public int getNUM() {
                return NUM;
            }

            public void setNUM(int NUM) {
                this.NUM = NUM;
            }

            public Object getURL() {
                return URL;
            }

            public void setURL(Object URL) {
                this.URL = URL;
            }
        }
    }

    public static class CPBean {
        private String CNNAME;
        private String CNPICURL;
        private double CNPRICE;
        private int ISUSED;
        private int PEROFVAL;

        public String getCNNAME() {
            return CNNAME;
        }

        public void setCNNAME(String CNNAME) {
            this.CNNAME = CNNAME;
        }

        public String getCNPICURL() {
            return CNPICURL;
        }

        public void setCNPICURL(String CNPICURL) {
            this.CNPICURL = CNPICURL;
        }

        public double getCNPRICE() {
            return CNPRICE;
        }

        public void setCNPRICE(double CNPRICE) {
            this.CNPRICE = CNPRICE;
        }

        public int getISUSED() {
            return ISUSED;
        }

        public void setISUSED(int ISUSED) {
            this.ISUSED = ISUSED;
        }

        public int getPEROFVAL() {
            return PEROFVAL;
        }

        public void setPEROFVAL(int PEROFVAL) {
            this.PEROFVAL = PEROFVAL;
        }
    }
}
