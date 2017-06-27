package com.example.administrator.koudaiwanzi.shopcar;

import java.util.List;

/**
 * Created by Administrator on 2016/7/2.
 */
public class OrderBean {

    /**
     * DetailUrl : Users.svc/neworder//b2fd42d6-a6f0-4f26-8483-cc6e3f07ecee
     * msg : 0
     * ADDRESS : {"DetailUrl":null,"msg":0,"AID":"a0d00a55-d560-4b1c-9e7c-ae4f89c95a66","AREA":"枞阳县","CITY":"安庆市","CONSIGNEE":"硕硕","DELETEADDRESS":null,"FULLADRESS":"呵呵","ISDEF":1,"MODIFYADDRESS":null,"PROVINCE":"安徽省","SETADDRESS":null,"TEL":"15524575712","ZIPCODE":"246000"}
     * ADDRESSURL : Users.svc/selectaderss/b2fd42d6-a6f0-4f26-8483-cc6e3f07ecee
     * COUPON : null
     * COUPONNUM : 0
     * DISCOUNTED : 0.0
     * EXCHANGEURL : Users.svc/exchange/b2fd42d6-a6f0-4f26-8483-cc6e3f07ecee
     * ITEMS : [{"ICOURL":"/Image/ico/1757df20-fb05-4560-934e-630688b8cabf.jpg","QUANTITY":0}]
     * JoinSingle : Items.svc/join/b2fd42d6-a6f0-4f26-8483-cc6e3f07ecee/
     * POINT : 25
     * POSTAGE : 60
     * PRICE : 88.0
     * QUANTITY : 1
     * TOTAL : 28.0
     */

    private String DetailUrl;
    private int msg;
    /**
     * DetailUrl : null
     * msg : 0
     * AID : a0d00a55-d560-4b1c-9e7c-ae4f89c95a66
     * AREA : 枞阳县
     * CITY : 安庆市
     * CONSIGNEE : 硕硕
     * DELETEADDRESS : null
     * FULLADRESS : 呵呵
     * ISDEF : 1
     * MODIFYADDRESS : null
     * PROVINCE : 安徽省
     * SETADDRESS : null
     * TEL : 15524575712
     * ZIPCODE : 246000
     */

    private ADDRESSBean ADDRESS;
    private String ADDRESSURL;
    private Object COUPON;
    private int COUPONNUM;
    private double DISCOUNTED;
    private String EXCHANGEURL;
    private String JoinSingle;
    private int POINT;
    private int POSTAGE;
    private double PRICE;
    private int QUANTITY;
    private int TOTAL;
    private String COUPONURL;

    public String getCOUPONURL() {
        return COUPONURL;
    }

    public void setCOUPONURL(String COUPONURL) {
        this.COUPONURL = COUPONURL;
    }

    /**
     * ICOURL : /Image/ico/1757df20-fb05-4560-934e-630688b8cabf.jpg
     * QUANTITY : 0
     */



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

    public ADDRESSBean getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(ADDRESSBean ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getADDRESSURL() {
        return ADDRESSURL;
    }

    public void setADDRESSURL(String ADDRESSURL) {
        this.ADDRESSURL = ADDRESSURL;
    }

    public Object getCOUPON() {
        return COUPON;
    }

    public void setCOUPON(Object COUPON) {
        this.COUPON = COUPON;
    }

    public int getCOUPONNUM() {
        return COUPONNUM;
    }

    public void setCOUPONNUM(int COUPONNUM) {
        this.COUPONNUM = COUPONNUM;
    }

    public double getDISCOUNTED() {
        return DISCOUNTED;
    }

    public void setDISCOUNTED(double DISCOUNTED) {
        this.DISCOUNTED = DISCOUNTED;
    }

    public String getEXCHANGEURL() {
        return EXCHANGEURL;
    }

    public void setEXCHANGEURL(String EXCHANGEURL) {
        this.EXCHANGEURL = EXCHANGEURL;
    }

    public String getJoinSingle() {
        return JoinSingle;
    }

    public void setJoinSingle(String JoinSingle) {
        this.JoinSingle = JoinSingle;
    }

    public int getPOINT() {
        return POINT;
    }

    public void setPOINT(int POINT) {
        this.POINT = POINT;
    }

    public int getPOSTAGE() {
        return POSTAGE;
    }

    public void setPOSTAGE(int POSTAGE) {
        this.POSTAGE = POSTAGE;
    }

    public double getPRICE() {
        return PRICE;
    }

    public void setPRICE(double PRICE) {
        this.PRICE = PRICE;
    }

    public int getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(int QUANTITY) {
        this.QUANTITY = QUANTITY;
    }

    public int getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(int TOTAL) {
        this.TOTAL = TOTAL;
    }

    public List<ITEMSBean> getITEMS() {
        return ITEMS;
    }

    public void setITEMS(List<ITEMSBean> ITEMS) {
        this.ITEMS = ITEMS;
    }

    public static class ADDRESSBean {
        private Object DetailUrl;
        private int msg;
        private String AID;
        private String AREA;
        private String CITY;
        private String CONSIGNEE;
        private Object DELETEADDRESS;
        private String FULLADRESS;
        private int ISDEF;
        private Object MODIFYADDRESS;
        private String PROVINCE;
        private Object SETADDRESS;
        private String TEL;
        private String ZIPCODE;

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

        public String getAID() {
            return AID;
        }

        public void setAID(String AID) {
            this.AID = AID;
        }

        public String getAREA() {
            return AREA;
        }

        public void setAREA(String AREA) {
            this.AREA = AREA;
        }

        public String getCITY() {
            return CITY;
        }

        public void setCITY(String CITY) {
            this.CITY = CITY;
        }

        public String getCONSIGNEE() {
            return CONSIGNEE;
        }

        public void setCONSIGNEE(String CONSIGNEE) {
            this.CONSIGNEE = CONSIGNEE;
        }

        public Object getDELETEADDRESS() {
            return DELETEADDRESS;
        }

        public void setDELETEADDRESS(Object DELETEADDRESS) {
            this.DELETEADDRESS = DELETEADDRESS;
        }

        public String getFULLADRESS() {
            return FULLADRESS;
        }

        public void setFULLADRESS(String FULLADRESS) {
            this.FULLADRESS = FULLADRESS;
        }

        public int getISDEF() {
            return ISDEF;
        }

        public void setISDEF(int ISDEF) {
            this.ISDEF = ISDEF;
        }

        public Object getMODIFYADDRESS() {
            return MODIFYADDRESS;
        }

        public void setMODIFYADDRESS(Object MODIFYADDRESS) {
            this.MODIFYADDRESS = MODIFYADDRESS;
        }

        public String getPROVINCE() {
            return PROVINCE;
        }

        public void setPROVINCE(String PROVINCE) {
            this.PROVINCE = PROVINCE;
        }

        public Object getSETADDRESS() {
            return SETADDRESS;
        }

        public void setSETADDRESS(Object SETADDRESS) {
            this.SETADDRESS = SETADDRESS;
        }

        public String getTEL() {
            return TEL;
        }

        public void setTEL(String TEL) {
            this.TEL = TEL;
        }

        public String getZIPCODE() {
            return ZIPCODE;
        }

        public void setZIPCODE(String ZIPCODE) {
            this.ZIPCODE = ZIPCODE;
        }
    }

    public static class ITEMSBean {
        private String ICOURL;
        private int QUANTITY;

        public String getICOURL() {
            return ICOURL;
        }

        public void setICOURL(String ICOURL) {
            this.ICOURL = ICOURL;
        }

        public int getQUANTITY() {
            return QUANTITY;
        }

        public void setQUANTITY(int QUANTITY) {
            this.QUANTITY = QUANTITY;
        }
    }
}
