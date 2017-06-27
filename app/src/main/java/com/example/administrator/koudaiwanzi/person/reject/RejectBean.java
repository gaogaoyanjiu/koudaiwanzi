package com.example.administrator.koudaiwanzi.person.reject;

/**
 * Created by Administrator on 2016/9/29.
 */
public class RejectBean {

    /**
     * DetailUrl : null
     * msg : 0
     * INSERTPICTURYURL : Users.svc/insertrqpicture/b2fd42d6-a6f0-4f26-8483-cc6e3f07ecee/01a0dc87-e598-4cb7-b176-14d142761b4e/2d26a2b9-8ced-4c5f-9ac9-a72ff51dc53e/5d1cd6f9-f7a0-4065-821c-0168d1dae513
     * INSERTQUESSIONURL : Users.svc/insertquession/b2fd42d6-a6f0-4f26-8483-cc6e3f07ecee/01a0dc87-e598-4cb7-b176-14d142761b4e/2d26a2b9-8ced-4c5f-9ac9-a72ff51dc53e/5d1cd6f9-f7a0-4065-821c-0168d1dae513
     * REQUESTITME : {"BARGAIN":0,"BONAME":"AROMA RESORT","CID":"01a0dc87-e598-4cb7-b176-14d142761b4e","DISCOUNT":0,"ICOURL":"/Image/ico/2b12530e-bcea-456d-81dd-c445d3b3db7d.jpg","PRICE":26,"QUANTITY":1,"TRADENAME":"lululun面膜 加强保湿型 7片装"}
     */

    private Object DetailUrl;
    private int msg;
    private String INSERTPICTURYURL;
    private String INSERTQUESSIONURL;
    /**
     * BARGAIN : 0
     * BONAME : AROMA RESORT
     * CID : 01a0dc87-e598-4cb7-b176-14d142761b4e
     * DISCOUNT : 0.0
     * ICOURL : /Image/ico/2b12530e-bcea-456d-81dd-c445d3b3db7d.jpg
     * PRICE : 26.0
     * QUANTITY : 1
     * TRADENAME : lululun面膜 加强保湿型 7片装
     */

    private REQUESTITMEBean REQUESTITME;

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

    public String getINSERTPICTURYURL() {
        return INSERTPICTURYURL;
    }

    public void setINSERTPICTURYURL(String INSERTPICTURYURL) {
        this.INSERTPICTURYURL = INSERTPICTURYURL;
    }

    public String getINSERTQUESSIONURL() {
        return INSERTQUESSIONURL;
    }

    public void setINSERTQUESSIONURL(String INSERTQUESSIONURL) {
        this.INSERTQUESSIONURL = INSERTQUESSIONURL;
    }

    public REQUESTITMEBean getREQUESTITME() {
        return REQUESTITME;
    }

    public void setREQUESTITME(REQUESTITMEBean REQUESTITME) {
        this.REQUESTITME = REQUESTITME;
    }

    public static class REQUESTITMEBean {
        private int BARGAIN;
        private String BONAME;
        private String CID;
        private double DISCOUNT;
        private String ICOURL;
        private double PRICE;
        private int QUANTITY;
        private String TRADENAME;

        public int getBARGAIN() {
            return BARGAIN;
        }

        public void setBARGAIN(int BARGAIN) {
            this.BARGAIN = BARGAIN;
        }

        public String getBONAME() {
            return BONAME;
        }

        public void setBONAME(String BONAME) {
            this.BONAME = BONAME;
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

        public String getTRADENAME() {
            return TRADENAME;
        }

        public void setTRADENAME(String TRADENAME) {
            this.TRADENAME = TRADENAME;
        }
    }
}
