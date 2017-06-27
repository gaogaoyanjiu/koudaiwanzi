package com.example.administrator.koudaiwanzi.person.coupon;

import java.util.List;

/**
 * Created by Administrator on 2016/6/24.
 */
public class CouponNotUsedBean {

    /**
     * DetailUrl : null
     * msg : 0
     * CP : [{"CNNAME":"Intel年终折扣优惠","CNPICURL":"/Image/coupon/30f7141d-b6b2-44b9-a135-1fcd49702ddb.jpeg","CNPRICE":10,"ISUSED":0,"PEROFVAL":1467216000}]
     * MENU : {"NOTUSED":{"NUM":1,"URL":"Users.svc//nousecoupon//db534290-4e37-4db1-ac51-b7caffec874d"},"OUTOFDATE":{"NUM":1,"URL":"Users.svc//outdatecoupon//db534290-4e37-4db1-ac51-b7caffec874d"},"USED":{"NUM":1,"URL":"Users.svc//usedcoupon//db534290-4e37-4db1-ac51-b7caffec874d"}}
     */

    private Object DetailUrl;
    private int msg;
    /**
     * NOTUSED : {"NUM":1,"URL":"Users.svc//nousecoupon//db534290-4e37-4db1-ac51-b7caffec874d"}
     * OUTOFDATE : {"NUM":1,"URL":"Users.svc//outdatecoupon//db534290-4e37-4db1-ac51-b7caffec874d"}
     * USED : {"NUM":1,"URL":"Users.svc//usedcoupon//db534290-4e37-4db1-ac51-b7caffec874d"}
     */

    private MENUBean MENU;
    /**
     * CNNAME : Intel年终折扣优惠
     * CNPICURL : /Image/coupon/30f7141d-b6b2-44b9-a135-1fcd49702ddb.jpeg
     * CNPRICE : 10.0
     * ISUSED : 0
     * PEROFVAL : 1467216000
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
         * NUM : 1
         * URL : Users.svc//nousecoupon//db534290-4e37-4db1-ac51-b7caffec874d
         */

        private NOTUSEDBean NOTUSED;
        /**
         * NUM : 1
         * URL : Users.svc//outdatecoupon//db534290-4e37-4db1-ac51-b7caffec874d
         */

        private OUTOFDATEBean OUTOFDATE;
        /**
         * NUM : 1
         * URL : Users.svc//usedcoupon//db534290-4e37-4db1-ac51-b7caffec874d
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
            private String URL;

            public int getNUM() {
                return NUM;
            }

            public void setNUM(int NUM) {
                this.NUM = NUM;
            }

            public String getURL() {
                return URL;
            }

            public void setURL(String URL) {
                this.URL = URL;
            }
        }

        public static class OUTOFDATEBean {
            private int NUM;
            private String URL;

            public int getNUM() {
                return NUM;
            }

            public void setNUM(int NUM) {
                this.NUM = NUM;
            }

            public String getURL() {
                return URL;
            }

            public void setURL(String URL) {
                this.URL = URL;
            }
        }

        public static class USEDBean {
            private int NUM;
            private String URL;

            public int getNUM() {
                return NUM;
            }

            public void setNUM(int NUM) {
                this.NUM = NUM;
            }

            public String getURL() {
                return URL;
            }

            public void setURL(String URL) {
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
