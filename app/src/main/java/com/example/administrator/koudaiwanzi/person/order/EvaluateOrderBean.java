package com.example.administrator.koudaiwanzi.person.order;

import java.util.List;

/**
 * Created by Administrator on 2016/9/30.
 */
public class EvaluateOrderBean {


    /**
     * DetailUrl : Users.svc/finishcomorder/d88d3148-07bd-494d-86b3-dd697f258f76/
     * msg : 0
     * ORDERITEM : [{"DetailUrl":null,"msg":0,"BARGAIN":0,"BONUM":null,"CID":"2ee99c1d-165c-4bf2-ab71-ed76db86c801","DISCOUNT":0,"ICOURL":"/Image/ico/942b4399-3afd-42b8-b326-8f303727e0a4.jpg","NID":"07b1490f-8b9f-432c-816f-298733147d95","POSTAGE":0,"PRICE":29,"QUANTITY":1,"STANDARD":"130g","TRADENAME":"碧柔 光滑素肌去角质型保湿洗面奶"},{"DetailUrl":null,"msg":0,"BARGAIN":0,"BONUM":null,"CID":"fb7a0449-5b40-4670-91f9-127a87176fc1","DISCOUNT":0,"ICOURL":"/Image/ico/77b908db-f448-4db8-a8d9-8af1f400f804.jpg","NID":"07b1490f-8b9f-432c-816f-298733147d95","POSTAGE":0,"PRICE":649,"QUANTITY":1,"STANDARD":"155mm","TRADENAME":"三星刀 155mm"}]
     */

    private String DetailUrl;
    private int msg;
    /**
     * DetailUrl : null
     * msg : 0
     * BARGAIN : 0
     * BONUM : null
     * CID : 2ee99c1d-165c-4bf2-ab71-ed76db86c801
     * DISCOUNT : 0.0
     * ICOURL : /Image/ico/942b4399-3afd-42b8-b326-8f303727e0a4.jpg
     * NID : 07b1490f-8b9f-432c-816f-298733147d95
     * POSTAGE : 0
     * PRICE : 29.0
     * QUANTITY : 1
     * STANDARD : 130g
     * TRADENAME : 碧柔 光滑素肌去角质型保湿洗面奶
     */

    private List<ORDERITEMBean> ORDERITEM;

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

    public List<ORDERITEMBean> getORDERITEM() {
        return ORDERITEM;
    }

    public void setORDERITEM(List<ORDERITEMBean> ORDERITEM) {
        this.ORDERITEM = ORDERITEM;
    }

    public static class ORDERITEMBean {
        private Object DetailUrl;
        private int msg;
        private double BARGAIN;
        private double BONUM;
        private String CID;
        private double DISCOUNT;
        private String ICOURL;
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

        public double getBONUM() {
            return BONUM;
        }

        public void setBONUM(double BONUM) {
            this.BONUM = BONUM;
        }

        public String getCID() {
            return CID;
        }

        public void setCID(String CID) {
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
