package com.example.administrator.koudaiwanzi.person.order;

import java.util.List;

/**
 * Created by Administrator on 2016/9/30.
 */
public class OrderDetailBean {

    /**
     * DetailUrl : null
     * msg : 0
     * BONUM : 356855981105345
     * COMMENTURL : null
     * CONSIGNEE : 何京华
     * CREATETIME : 1482288825
     * DELEORDER : Users.svc/deleteorder/763d6daf-1e8d-4e30-b9b4-dd7037feda3a/e7841692-f928-45e4-b229-803ce1b82ed7
     * FULLADRESS : 麦子店街39号四层
     * INFORMATION : Users.svc/information/e7841692-f928-45e4-b229-803ce1b82ed7/763d6daf-1e8d-4e30-b9b4-dd7037feda3a
     * MENU : {"ALLSTUTE":0,"ALLURL":null,"OVERSTATUE":0,"OVERURL":null,"UNGETSTATUE":0,"UNGETURL":null,"UNPAYSTATUE":0,"UNPAYURL":null,"UNSENDSTATUE":0,"UNSENDURL":null}
     * NID : e7841692-f928-45e4-b229-803ce1b82ed7
     * ORDERDETAILURL : null
     * ORDERITEMS : [{"DetailUrl":null,"msg":0,"BARGAIN":85.5,"BONUM":null,"CID":null,"DISCOUNT":9,"ICOURL":"/Image/ico/9d755258-1c66-45e8-94ed-9793d44083fa.jpg","ISOUTSTOCK":0,"NID":"e7841692-f928-45e4-b229-803ce1b82ed7","POSTAGE":0,"PRICE":95,"QUANTITY":1,"STANDARD":"600ml","TRADENAME":"SEA BREEZE 清爽去屑洗发水"},{"DetailUrl":null,"msg":0,"BARGAIN":68.4,"BONUM":null,"CID":null,"DISCOUNT":9,"ICOURL":"/Image/ico/c33e85b5-87c8-4fe9-98be-86301fff6d84.jpg","ISOUTSTOCK":0,"NID":"e7841692-f928-45e4-b229-803ce1b82ed7","POSTAGE":0,"PRICE":76,"QUANTITY":1,"STANDARD":"500ml","TRADENAME":"Indefinie　无硅洗发水（甜美花香型）"},{"DetailUrl":null,"msg":0,"BARGAIN":0,"BONUM":null,"CID":null,"DISCOUNT":0,"ICOURL":"/Image/ico/f9878be6-b3fd-4048-935e-c5bd71b3e12c.jpg","ISOUTSTOCK":0,"NID":"e7841692-f928-45e4-b229-803ce1b82ed7","POSTAGE":0,"PRICE":67,"QUANTITY":1,"STANDARD":"550ml","TRADENAME":"高丝 男士去异味洗发水"},{"DetailUrl":null,"msg":0,"BARGAIN":0,"BONUM":null,"CID":null,"DISCOUNT":0,"ICOURL":"/Image/ico/b82fa2d4-d768-4e06-bdb2-c344b3d8aa72.jpg","ISOUTSTOCK":0,"NID":"e7841692-f928-45e4-b229-803ce1b82ed7","POSTAGE":0,"PRICE":88,"QUANTITY":1,"STANDARD":"450ml","TRADENAME":"MA CHERIE 水润无硅洗发水"},{"DetailUrl":null,"msg":0,"BARGAIN":0,"BONUM":null,"CID":null,"DISCOUNT":0,"ICOURL":"/Image/ico/bd6d901b-3361-4118-8456-21e4409dfc27.jpg","ISOUTSTOCK":0,"NID":"e7841692-f928-45e4-b229-803ce1b82ed7","POSTAGE":0,"PRICE":39,"QUANTITY":3,"STANDARD":"5片","TRADENAME":"三次元防2.5 口罩"},{"DetailUrl":null,"msg":0,"BARGAIN":0,"BONUM":null,"CID":null,"DISCOUNT":0,"ICOURL":"/Image/ico/a58410dd-32b9-471c-9ac3-303954868b1e.jpg","ISOUTSTOCK":0,"NID":"e7841692-f928-45e4-b229-803ce1b82ed7","POSTAGE":0,"PRICE":39,"QUANTITY":2,"STANDARD":"5片","TRADENAME":"三次元防2.5口罩 "}]
     * PAYORDER : Users.svc/userpay/e7841692-f928-45e4-b229-803ce1b82ed7/763d6daf-1e8d-4e30-b9b4-dd7037feda3a
     * POSTAGE : 0.0
     * PRICE : 503.9
     * QUANTITY : 9
     * RECIPIENTSUCCESSURL : Users.svc/recipientsucess/763d6daf-1e8d-4e30-b9b4-dd7037feda3a/e7841692-f928-45e4-b229-803ce1b82ed7
     * STATUEBUTTONURL : null
     * STATUSNAME : 已付款
     * STATUSTYPE : 1
     * TEL : 13701064791
     */

    private Object DetailUrl;
    private int msg;
    private String BONUM;
    private Object COMMENTURL;
    private String CONSIGNEE;
    private int CREATETIME;
    private String DELEORDER;
    private String FULLADRESS;
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

    private MENUBean MENU;
    private String NID;
    private Object ORDERDETAILURL;
    private String PAYORDER;
    private double POSTAGE;
    private double PRICE;
    private String QUANTITY;
    private String RECIPIENTSUCCESSURL;
    private Object STATUEBUTTONURL;
    private String STATUSNAME;
    private int STATUSTYPE;
    private String TEL;
    /**
     * DetailUrl : null
     * msg : 0
     * BARGAIN : 85.5
     * BONUM : null
     * CID : null
     * DISCOUNT : 9.0
     * ICOURL : /Image/ico/9d755258-1c66-45e8-94ed-9793d44083fa.jpg
     * ISOUTSTOCK : 0
     * NID : e7841692-f928-45e4-b229-803ce1b82ed7
     * POSTAGE : 0
     * PRICE : 95.0
     * QUANTITY : 1
     * STANDARD : 600ml
     * TRADENAME : SEA BREEZE 清爽去屑洗发水
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

    public Object getCOMMENTURL() {
        return COMMENTURL;
    }

    public void setCOMMENTURL(Object COMMENTURL) {
        this.COMMENTURL = COMMENTURL;
    }

    public String getCONSIGNEE() {
        return CONSIGNEE;
    }

    public void setCONSIGNEE(String CONSIGNEE) {
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

    public String getFULLADRESS() {
        return FULLADRESS;
    }

    public void setFULLADRESS(String FULLADRESS) {
        this.FULLADRESS = FULLADRESS;
    }

    public String getINFORMATION() {
        return INFORMATION;
    }

    public void setINFORMATION(String INFORMATION) {
        this.INFORMATION = INFORMATION;
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

    public Object getORDERDETAILURL() {
        return ORDERDETAILURL;
    }

    public void setORDERDETAILURL(Object ORDERDETAILURL) {
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

    public String getRECIPIENTSUCCESSURL() {
        return RECIPIENTSUCCESSURL;
    }

    public void setRECIPIENTSUCCESSURL(String RECIPIENTSUCCESSURL) {
        this.RECIPIENTSUCCESSURL = RECIPIENTSUCCESSURL;
    }

    public Object getSTATUEBUTTONURL() {
        return STATUEBUTTONURL;
    }

    public void setSTATUEBUTTONURL(Object STATUEBUTTONURL) {
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

    public String getTEL() {
        return TEL;
    }

    public void setTEL(String TEL) {
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
        private Object CID;
        private double DISCOUNT;
        private String ICOURL;
        private int ISOUTSTOCK;
        private String NID;
        private int POSTAGE;
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

        public Object getCID() {
            return CID;
        }

        public void setCID(Object CID) {
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

        public int getISOUTSTOCK() {
            return ISOUTSTOCK;
        }

        public void setISOUTSTOCK(int ISOUTSTOCK) {
            this.ISOUTSTOCK = ISOUTSTOCK;
        }

        public String getNID() {
            return NID;
        }

        public void setNID(String NID) {
            this.NID = NID;
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
