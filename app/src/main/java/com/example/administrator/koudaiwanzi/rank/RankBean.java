package com.example.administrator.koudaiwanzi.rank;

import java.util.List;

/**
 * Created by Administrator on 2016/8/29.
 */
public class RankBean {


    /**
     * DetailUrl : null
     * msg : 0
     * PAYCOUNT : 0
     * TOPBANNER : [{"DetailUrl":null,"msg":0,"IMGINDEX":0,"IMGURL":"/Image/banner//76f0b9bf-eaf5-43eb-b61e-c5a7c188b560.jpg"},{"DetailUrl":null,"msg":0,"IMGINDEX":0,"IMGURL":"/Image/banner//57f9fda2-a446-4dda-aa3b-d633b18cd117.jpg"},{"DetailUrl":null,"msg":0,"IMGINDEX":0,"IMGURL":"/Image/banner//f4f95fed-3c0d-42ae-bdfb-6cd207d68080.jpg"},{"DetailUrl":null,"msg":0,"IMGINDEX":0,"IMGURL":"/Image/banner//b13ca5ba-bf07-4533-9f26-492cd87c9c9d.jpg"},{"DetailUrl":null,"msg":0,"IMGINDEX":0,"IMGURL":"/Image/banner//7823f03f-db95-4945-a5a4-193f20c2cc10.jpg"},{"DetailUrl":null,"msg":0,"IMGINDEX":0,"IMGURL":"/Image/banner//7823f03f-db95-4945-a5a4-193f20c2cc10.jpg"}]
     * TOPITEMS : [{"DetailUrl":"Items.svc//item//89ddc07a-5d22-4c69-860d-9539bacd88d5","msg":0,"BARGAIN":0,"BONAME":"三星刃物","CAPACITY":0,"CID":"89ddc07a-5d22-4c69-860d-9539bacd88d5","DISCOUNT":0,"ICOURL":"/Image/ico/499d9d93-9be7-42d5-838f-04b2d253b0a8.jpg","NUM":6,"PRICE":500,"RAN":1,"TRADENAME":"三星刀具 全能菜刀&小型菜刀 套装"},{"DetailUrl":"Items.svc//item//985c5a66-6f67-4f12-b0fb-2efd6da00408","msg":0,"BARGAIN":0,"BONAME":"三星刃物","CAPACITY":0,"CID":"985c5a66-6f67-4f12-b0fb-2efd6da00408","DISCOUNT":0,"ICOURL":"/Image/ico/155efcda-e1a5-4105-8238-1d429d86308e.jpg","NUM":5,"PRICE":235,"RAN":2,"TRADENAME":"三星刀具 小型菜刀155mm"},{"DetailUrl":"Items.svc//item//83e9448b-f317-42be-a4e0-01cfba002ff7","msg":0,"BARGAIN":0,"BONAME":"Fuwarie","CAPACITY":0,"CID":"83e9448b-f317-42be-a4e0-01cfba002ff7","DISCOUNT":0,"ICOURL":"/Image/ico/5650d193-1c3b-4715-bde0-b74a86117fce.jpg","NUM":4,"PRICE":130,"RAN":3,"TRADENAME":"Kracie Fuwarie 卷发造型喷雾 "},{"DetailUrl":"Items.svc//item//9c01706f-089f-4ca0-a920-672b8c056083","msg":0,"BARGAIN":0,"BONAME":"minon","CAPACITY":0,"CID":"9c01706f-089f-4ca0-a920-672b8c056083","DISCOUNT":0,"ICOURL":"/Image/ico/49acedf4-b629-4a01-99e9-13f6e80fa0e8.jpg","NUM":3,"PRICE":150,"RAN":4,"TRADENAME":"minon 全身用滋润型洗发液浴液二合一"},{"DetailUrl":"Items.svc//item//294c1f6c-93de-4cae-88da-44bfff932c0f","msg":0,"BARGAIN":0,"BONAME":"Fuwarie","CAPACITY":0,"CID":"294c1f6c-93de-4cae-88da-44bfff932c0f","DISCOUNT":0,"ICOURL":"/Image/ico/28f0fd0e-4889-40ea-89f8-8d997793fada.jpg","NUM":2,"PRICE":150,"RAN":5,"TRADENAME":"Kracie Fuwarie 卷翘造型发雾"},{"DetailUrl":"Items.svc//item//cf1bdf84-8a29-4979-9fc0-f61648f135a6","msg":0,"BARGAIN":0,"BONAME":"AQUA SAVON","CAPACITY":0,"CID":"cf1bdf84-8a29-4979-9fc0-f61648f135a6","DISCOUNT":0,"ICOURL":"/Image/ico/8c126683-b9a6-43fe-92ba-a142d77a7bbb.jpg","NUM":1,"PRICE":222,"RAN":6,"TRADENAME":"水润防晒凝露 清新香皂味 SPF40 PA+++"}]
     */

    private String DetailUrl;
    private int msg;
    private int PAYCOUNT;
    /**
     * DetailUrl : null
     * msg : 0
     * IMGINDEX : 0
     * IMGURL : /Image/banner//76f0b9bf-eaf5-43eb-b61e-c5a7c188b560.jpg
     */

    private List<TOPBANNERBean> TOPBANNER;
    /**
     * DetailUrl : Items.svc//item//89ddc07a-5d22-4c69-860d-9539bacd88d5
     * msg : 0
     * BARGAIN : 0
     * BONAME : 三星刃物
     * CAPACITY : 0
     * CID : 89ddc07a-5d22-4c69-860d-9539bacd88d5
     * DISCOUNT : 0.0
     * ICOURL : /Image/ico/499d9d93-9be7-42d5-838f-04b2d253b0a8.jpg
     * NUM : 6
     * PRICE : 500.0
     * RAN : 1
     * TRADENAME : 三星刀具 全能菜刀&小型菜刀 套装
     */

    private List<TOPITEMSBean> TOPITEMS;

    public  String getDetailUrl() {
        return DetailUrl;
    }

    public void setDetailUrl( String DetailUrl) {
        this.DetailUrl = DetailUrl;
    }

    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }

    public int getPAYCOUNT() {
        return PAYCOUNT;
    }

    public void setPAYCOUNT(int PAYCOUNT) {
        this.PAYCOUNT = PAYCOUNT;
    }

    public List<TOPBANNERBean> getTOPBANNER() {
        return TOPBANNER;
    }

    public void setTOPBANNER(List<TOPBANNERBean> TOPBANNER) {
        this.TOPBANNER = TOPBANNER;
    }

    public List<TOPITEMSBean> getTOPITEMS() {
        return TOPITEMS;
    }

    public void setTOPITEMS(List<TOPITEMSBean> TOPITEMS) {
        this.TOPITEMS = TOPITEMS;
    }

    public static class TOPBANNERBean {
        private String DetailUrl;
        private int msg;
        private int IMGINDEX;
        private String IMGURL;

        public  String  getDetailUrl() {
            return DetailUrl;
        }

        public void setDetailUrl( String  DetailUrl) {
            this.DetailUrl = DetailUrl;
        }

        public int getMsg() {
            return msg;
        }

        public void setMsg(int msg) {
            this.msg = msg;
        }

        public int getIMGINDEX() {
            return IMGINDEX;
        }

        public void setIMGINDEX(int IMGINDEX) {
            this.IMGINDEX = IMGINDEX;
        }

        public String getIMGURL() {
            return IMGURL;
        }

        public void setIMGURL(String IMGURL) {
            this.IMGURL = IMGURL;
        }
    }

    public static class TOPITEMSBean {
        private String DetailUrl;
        private int msg;
        private double BARGAIN;
        private String BONAME;
        private int CAPACITY;
        private String CID;
        private double DISCOUNT;
        private String ICOURL;
        private int NUM;
        private double PRICE;
        private int RAN;
        private String TRADENAME;

        public String getSTANDARD() {
            return STANDARD;
        }

        public void setSTANDARD(String STANDARD) {
            this.STANDARD = STANDARD;
        }

        private String STANDARD;

        public int getISOUTSTOCK() {
            return ISOUTSTOCK;
        }

        public void setISOUTSTOCK(int ISOUTSTOCK) {
            this.ISOUTSTOCK = ISOUTSTOCK;
        }

        private int ISOUTSTOCK;

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

        public double getBARGAIN() {
            return BARGAIN;
        }

        public void setBARGAIN(double BARGAIN) {
            this.BARGAIN = BARGAIN;
        }

        public String getBONAME() {
            return BONAME;
        }

        public void setBONAME(String BONAME) {
            this.BONAME = BONAME;
        }

        public int getCAPACITY() {
            return CAPACITY;
        }

        public void setCAPACITY(int CAPACITY) {
            this.CAPACITY = CAPACITY;
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

        public int getNUM() {
            return NUM;
        }

        public void setNUM(int NUM) {
            this.NUM = NUM;
        }

        public double getPRICE() {
            return PRICE;
        }

        public void setPRICE(double PRICE) {
            this.PRICE = PRICE;
        }

        public int getRAN() {
            return RAN;
        }

        public void setRAN(int RAN) {
            this.RAN = RAN;
        }

        public String getTRADENAME() {
            return TRADENAME;
        }

        public void setTRADENAME(String TRADENAME) {
            this.TRADENAME = TRADENAME;
        }
    }
}
