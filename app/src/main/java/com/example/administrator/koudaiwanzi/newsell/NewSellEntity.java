package com.example.administrator.koudaiwanzi.newsell;

import java.util.List;

/**
 * Created by Administrator on 2016/08/29.
 */
public class NewSellEntity {


    /**
     * DetailUrl : null
     * msg : 0
     * SPEBANNER : [{"DetailUrl":null,"msg":0,"IMGINDEX":0,"IMGURL":"/Image/banner//76f0b9bf-eaf5-43eb-b61e-c5a7c188b560.jpg"},{"DetailUrl":null,"msg":0,"IMGINDEX":0,"IMGURL":"/Image/banner//57f9fda2-a446-4dda-aa3b-d633b18cd117.jpg"},{"DetailUrl":null,"msg":0,"IMGINDEX":0,"IMGURL":"/Image/banner//f4f95fed-3c0d-42ae-bdfb-6cd207d68080.jpg"},{"DetailUrl":null,"msg":0,"IMGINDEX":0,"IMGURL":"/Image/banner//b13ca5ba-bf07-4533-9f26-492cd87c9c9d.jpg"},{"DetailUrl":null,"msg":0,"IMGINDEX":0,"IMGURL":"/Image/banner//7823f03f-db95-4945-a5a4-193f20c2cc10.jpg"}]
     * SPEITEMS : [{"DetailUrl":"Items.svc//item//0163cb05-5182-4fb0-99a8-cc8d10ce2b3c","msg":0,"BARGAIN":0,"BONAME":"minon","CAPACITY":0,"CID":"0163cb05-5182-4fb0-99a8-cc8d10ce2b3c","DISCOUNT":0,"ICOURL":"/Image/ico/67730d8a-1262-4c73-a561-6e676ae52c7c.jpg","NUM":0,"PRICE":35,"RAN":0,"TRADENAME":"狮王 KIREI 水果混合香型泡沫洁净洗手液"},{"DetailUrl":"Items.svc//item//03b19b2d-d5d8-438c-bd8e-5279015e5149","msg":0,"BARGAIN":0,"BONAME":"minon","CAPACITY":0,"CID":"03b19b2d-d5d8-438c-bd8e-5279015e5149","DISCOUNT":0,"ICOURL":"/Image/ico/838eec54-82c8-4be2-b4d8-8f41eca05afa.jpg","NUM":0,"PRICE":200,"RAN":0,"TRADENAME":"skin peace 防干燥全食物成分润唇膏"}]
     */

    private Object DetailUrl;
    private int msg;
    /**
     * DetailUrl : null
     * msg : 0
     * IMGINDEX : 0
     * IMGURL : /Image/banner//76f0b9bf-eaf5-43eb-b61e-c5a7c188b560.jpg
     */

    private List<SPEBANNERBean> SPEBANNER;
    /**
     * DetailUrl : Items.svc//item//0163cb05-5182-4fb0-99a8-cc8d10ce2b3c
     * msg : 0
     * BARGAIN : 0
     * BONAME : minon
     * CAPACITY : 0
     * CID : 0163cb05-5182-4fb0-99a8-cc8d10ce2b3c
     * DISCOUNT : 0.0
     * ICOURL : /Image/ico/67730d8a-1262-4c73-a561-6e676ae52c7c.jpg
     * NUM : 0
     * PRICE : 35.0
     * RAN : 0
     * TRADENAME : 狮王 KIREI 水果混合香型泡沫洁净洗手液
     */

    private List<SPEITEMSBean> SPEITEMS;

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

    public List<SPEBANNERBean> getSPEBANNER() {
        return SPEBANNER;
    }

    public void setSPEBANNER(List<SPEBANNERBean> SPEBANNER) {
        this.SPEBANNER = SPEBANNER;
    }

    public List<SPEITEMSBean> getSPEITEMS() {
        return SPEITEMS;
    }

    public void setSPEITEMS(List<SPEITEMSBean> SPEITEMS) {
        this.SPEITEMS = SPEITEMS;
    }

    public static class SPEBANNERBean {
        private Object DetailUrl;
        private int msg;
        private int IMGINDEX;
        private String IMGURL;

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

    public static class SPEITEMSBean {
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
