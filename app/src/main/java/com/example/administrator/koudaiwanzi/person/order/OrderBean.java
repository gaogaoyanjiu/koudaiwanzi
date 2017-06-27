package com.example.administrator.koudaiwanzi.person.order;

import java.util.List;

/**
 * Created by Administrator on 2016/7/1.
 */
public class OrderBean {

    /**
     * DetailUrl : null
     * msg : 0
     * BONUM : 214971416131750
     * CONSIGNEE : null
     * CREATETIME : 0
     * DELEORDER : Users.svc/deleteorder/db534290-4e37-4db1-ac51-b7caffec874d/233d025a-075d-4da9-b238-ac4f629e5a1e
     * FULLADRESS : null
     * MENU : {"ALLSTUTE":0,"ALLURL":null,"OVERSTATUE":0,"OVERURL":null,"UNGETSTATUE":0,"UNGETURL":null,"UNPAYSTATUE":0,"UNPAYURL":null,"UNSENDSTATUE":0,"UNSENDURL":null}
     * NID : 233d025a-075d-4da9-b238-ac4f629e5a1e
     * ORDERDETAILURL : Users.svc/orderdetail/db534290-4e37-4db1-ac51-b7caffec874d/
     * ORDERITEMS : [{"DetailUrl":null,"msg":0,"BARGAIN":49.2,"BONUM":null,"DISCOUNT":4,"ICOURL":null,"NID":"233d025a-075d-4da9-b238-ac4f629e5a1e","POSTAGE":111,"PRICE":123,"QUANTITY":11,"STANDARD":"11","TRADENAME":"AA"}]
     * PAYORDER : Pay.svc/wxpay/233d025a-075d-4da9-b238-ac4f629e5a1e/db534290-4e37-4db1-ac51-b7caffec874d/
     * POSTAGE : 111.0
     * PRICE : 541.2
     * QUANTITY : 11
     * STATUEBUTTONURL : Users.svc/orderpayment/233d025a-075d-4da9-b238-ac4f629e5a1e/db534290-4e37-4db1-ac51-b7caffec874d
     * STATUSNAME : 待付款
     * STATUSTYPE : 0
     * TEL : null
     */

    private Object DetailUrl;
    private int msg;
    private String BONUM;
    private Object CONSIGNEE;
    private int CREATETIME;
    private String DELEORDER;
    private Object FULLADRESS;
    private String INFORMATION;
    /**
     * ALLSTUTE : 0
     * ALLURL : null
     * OVERSTATUE : 0
     * OVERURL : null
     * UNGETSTATUE : 0
     * UNGETURL : null
     * UNPAYSTATUE : 0
     * UNPAYURL : null
     * UNSENDSTATUE : 0
     * UNSENDURL : null
     */

    private String COMMENTURL;
    private MENUBean MENU;
    private String NID;
    private String ORDERDETAILURL;
    private String PAYORDER;
    private double POSTAGE;
    private double PRICE;
    private String QUANTITY;
    private String STATUEBUTTONURL;
    private String STATUSNAME;
    private int STATUSTYPE;
    private Object TEL;

    public String getRECIPIENTSUCCESSURL() {
        return RECIPIENTSUCCESSURL;
    }

    public void setRECIPIENTSUCCESSURL(String RECIPIENTSUCCESSURL) {
        this.RECIPIENTSUCCESSURL = RECIPIENTSUCCESSURL;
    }

    private String RECIPIENTSUCCESSURL;

    public String getCOMMENTURL() {
        return COMMENTURL;
    }

    public void setCOMMENTURL(String COMMENTURL) {
        this.COMMENTURL = COMMENTURL;
    }

    public String getINFORMATION() {
        return INFORMATION;
    }

    public void setINFORMATION(String INFORMATION) {
        this.INFORMATION = INFORMATION;
    }

    /**
     * DetailUrl : null
     * msg : 0
     * BARGAIN : 49.2
     * BONUM : null
     * DISCOUNT : 4.0
     * ICOURL : null
     * NID : 233d025a-075d-4da9-b238-ac4f629e5a1e
     * POSTAGE : 111.0
     * PRICE : 123.0
     * QUANTITY : 11
     * STANDARD : 11
     * TRADENAME : AA
     */



    private List<ORDERITEMSBean> ORDERITEMS;

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

    public String getBONUM() {
        return BONUM;
    }

    public void setBONUM(String BONUM) {
        this.BONUM = BONUM;
    }

    public Object getCONSIGNEE() {
        return CONSIGNEE;
    }

    public void setCONSIGNEE(Object CONSIGNEE) {
        this.CONSIGNEE = CONSIGNEE;
    }

    public int getCREATETIME() {
        return CREATETIME;
    }

    public void setCREATETIME(int CREATETIME) {
        this.CREATETIME = CREATETIME;
    }

    public String getDELEORDER() {
        return DELEORDER;
    }

    public void setDELEORDER(String DELEORDER) {
        this.DELEORDER = DELEORDER;
    }

    public Object getFULLADRESS() {
        return FULLADRESS;
    }

    public void setFULLADRESS(Object FULLADRESS) {
        this.FULLADRESS = FULLADRESS;
    }

    public MENUBean getMENU() {
        return MENU;
    }

    public void setMENU(MENUBean MENU) {
        this.MENU = MENU;
    }

    public String getNID() {
        return NID;
    }

    public void setNID(String NID) {
        this.NID = NID;
    }

    public String getORDERDETAILURL() {
        return ORDERDETAILURL;
    }

    public void setORDERDETAILURL(String ORDERDETAILURL) {
        this.ORDERDETAILURL = ORDERDETAILURL;
    }

    public String getPAYORDER() {
        return PAYORDER;
    }

    public void setPAYORDER(String PAYORDER) {
        this.PAYORDER = PAYORDER;
    }

    public double getPOSTAGE() {
        return POSTAGE;
    }

    public void setPOSTAGE(double POSTAGE) {
        this.POSTAGE = POSTAGE;
    }

    public double getPRICE() {
        return PRICE;
    }

    public void setPRICE(double PRICE) {
        this.PRICE = PRICE;
    }

    public String getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(String QUANTITY) {
        this.QUANTITY = QUANTITY;
    }

    public String getSTATUEBUTTONURL() {
        return STATUEBUTTONURL;
    }

    public void setSTATUEBUTTONURL(String STATUEBUTTONURL) {
        this.STATUEBUTTONURL = STATUEBUTTONURL;
    }

    public String getSTATUSNAME() {
        return STATUSNAME;
    }

    public void setSTATUSNAME(String STATUSNAME) {
        this.STATUSNAME = STATUSNAME;
    }

    public int getSTATUSTYPE() {
        return STATUSTYPE;
    }

    public void setSTATUSTYPE(int STATUSTYPE) {
        this.STATUSTYPE = STATUSTYPE;
    }

    public Object getTEL() {
        return TEL;
    }

    public void setTEL(Object TEL) {
        this.TEL = TEL;
    }

    public List<ORDERITEMSBean> getORDERITEMS() {
        return ORDERITEMS;
    }

    public void setORDERITEMS(List<ORDERITEMSBean> ORDERITEMS) {
        this.ORDERITEMS = ORDERITEMS;
    }

    public static class MENUBean {
        private int ALLSTUTE;
        private Object ALLURL;
        private int OVERSTATUE;
        private Object OVERURL;
        private int UNGETSTATUE;
        private Object UNGETURL;
        private int UNPAYSTATUE;
        private Object UNPAYURL;
        private int UNSENDSTATUE;
        private Object UNSENDURL;

        public int getALLSTUTE() {
            return ALLSTUTE;
        }

        public void setALLSTUTE(int ALLSTUTE) {
            this.ALLSTUTE = ALLSTUTE;
        }

        public Object getALLURL() {
            return ALLURL;
        }

        public void setALLURL(Object ALLURL) {
            this.ALLURL = ALLURL;
        }

        public int getOVERSTATUE() {
            return OVERSTATUE;
        }

        public void setOVERSTATUE(int OVERSTATUE) {
            this.OVERSTATUE = OVERSTATUE;
        }

        public Object getOVERURL() {
            return OVERURL;
        }

        public void setOVERURL(Object OVERURL) {
            this.OVERURL = OVERURL;
        }

        public int getUNGETSTATUE() {
            return UNGETSTATUE;
        }

        public void setUNGETSTATUE(int UNGETSTATUE) {
            this.UNGETSTATUE = UNGETSTATUE;
        }

        public Object getUNGETURL() {
            return UNGETURL;
        }

        public void setUNGETURL(Object UNGETURL) {
            this.UNGETURL = UNGETURL;
        }

        public int getUNPAYSTATUE() {
            return UNPAYSTATUE;
        }

        public void setUNPAYSTATUE(int UNPAYSTATUE) {
            this.UNPAYSTATUE = UNPAYSTATUE;
        }

        public Object getUNPAYURL() {
            return UNPAYURL;
        }

        public void setUNPAYURL(Object UNPAYURL) {
            this.UNPAYURL = UNPAYURL;
        }

        public int getUNSENDSTATUE() {
            return UNSENDSTATUE;
        }

        public void setUNSENDSTATUE(int UNSENDSTATUE) {
            this.UNSENDSTATUE = UNSENDSTATUE;
        }

        public Object getUNSENDURL() {
            return UNSENDURL;
        }

        public void setUNSENDURL(Object UNSENDURL) {
            this.UNSENDURL = UNSENDURL;
        }
    }

    public static class ORDERITEMSBean {
        private Object DetailUrl;
        private int msg;
        private double BARGAIN;
        private Object BONUM;
        private double DISCOUNT;
        private Object ICOURL;
        private String NID;
        private double POSTAGE;
        private double PRICE;
        private int QUANTITY;
        private String STANDARD;
        private String TRADENAME;

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

        public double getBARGAIN() {
            return BARGAIN;
        }

        public void setBARGAIN(double BARGAIN) {
            this.BARGAIN = BARGAIN;
        }

        public Object getBONUM() {
            return BONUM;
        }

        public void setBONUM(Object BONUM) {
            this.BONUM = BONUM;
        }

        public double getDISCOUNT() {
            return DISCOUNT;
        }

        public void setDISCOUNT(double DISCOUNT) {
            this.DISCOUNT = DISCOUNT;
        }

        public Object getICOURL() {
            return ICOURL;
        }

        public void setICOURL(Object ICOURL) {
            this.ICOURL = ICOURL;
        }

        public String getNID() {
            return NID;
        }

        public void setNID(String NID) {
            this.NID = NID;
        }

        public double getPOSTAGE() {
            return POSTAGE;
        }

        public void setPOSTAGE(double POSTAGE) {
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

        public String getSTANDARD() {
            return STANDARD;
        }

        public void setSTANDARD(String STANDARD) {
            this.STANDARD = STANDARD;
        }

        public String getTRADENAME() {
            return TRADENAME;
        }

        public void setTRADENAME(String TRADENAME) {
            this.TRADENAME = TRADENAME;
        }
    }
}
