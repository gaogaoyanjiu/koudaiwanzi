package com.example.administrator.koudaiwanzi.details.detail.size;

import java.util.List;

/**
 * Created by Administrator on 2016/6/24.
 */
public class SizeBean {


    /*
    * *
     * DetailUrl : null
     * msg : 0
     * ADDFAV : null
     * BARGAIN : 70.2
     * CID : 3b7e436c-0c12-4f16-8362-da477b71632a
     * DCTFLG : 0
     * DELFAV : null
     * DELIVERY : 国内直邮
     * DISCOUNT : 9.0
     * ICOURL : /Image/ico/ebd63921-a256-4eca-afb2-7e09b30a8d74.jpg
     * IMGS : [{"IMGURL":"/Image/img/27aff476-0939-48b5-9ade-7a185f58ea2a.jpg"},{"IMGURL":"/Image/img/8a917212-ac2c-4951-ad79-c5d94414a934.jpg"},{"IMGURL":"/Image/img/954e3856-1eac-420c-b425-feffb6264e6f.jpg"},{"IMGURL":"/Image/img/c6cdc17b-1c84-4397-a1e6-d508094198b1.jpg"},{"IMGURL":"/Image/img/eeda31d0-c684-451e-904f-3852aee7e084.jpg"}]
     * NOTES : 无退货
     * OTHER : [{"DetailUrl":"Items.svc//item//4e5672ac-522d-4066-bcb2-475e58327781","msg":0,"BARGAIN":160.3,"CID":"4e5672ac-522d-4066-bcb2-475e58327781","DISCOUNT":7,"ICOURL":"/Image/ico/abbef866-dbcc-42b8-94ea-ccc1d830ace5.jpg","PRICE":229,"TRADENAME":"眼线"},{"DetailUrl":"Items.svc//item//a2420bc9-2fce-4c93-a15c-9be137a59c00","msg":0,"BARGAIN":215.9,"CID":"a2420bc9-2fce-4c93-a15c-9be137a59c00","DISCOUNT":8.5,"ICOURL":"/Image/ico/59fd4032-dc51-4de8-96c3-11133c9bf5ee.jpg","PRICE":254,"TRADENAME":"散粉"}]
     * PICS : [{"IMGURL":"/Image/icotable/d8b9522b-5750-4113-861c-a8928ff2e6e9.jpg","NUM":0},{"IMGURL":"/Image/icotable/0c1012a0-3851-4b51-9dd6-43bca8d4814f.jpg","NUM":0},{"IMGURL":"/Image/icotable/47c1d65b-19e9-49d9-b457-74d316313bd7.jpg","NUM":0},{"IMGURL":"/Image/icotable/1e497f8d-4994-465c-a1f7-92d4f2cce427.jpg","NUM":0},{"IMGURL":"/Image/icotable/bc7bcd9e-ddf1-4a6a-b333-2649984febf6.jpg","NUM":0},{"IMGURL":"/Image/icotable/adc3a281-b4f4-481d-9fd3-62648512c439.jpg","NUM":0},{"IMGURL":"/Image/icotable/a4dfd01a-f1cd-4d42-9989-ba43b9e53418.jpg","NUM":0},{"IMGURL":"/Image/icotable/533980a8-54b7-4f08-aeed-d8cc60c60bde.jpg","NUM":0},{"IMGURL":"/Image/icotable/75a0fd4c-8125-46aa-955e-59ed956b9ade.jpg","NUM":0},{"IMGURL":"/Image/icotable/ddeadc31-3370-423d-9221-1fb77e6a3f8d.jpg","NUM":0},{"IMGURL":"/Image/icotable/80f2c555-e056-40a6-82cb-c5eea13198bf.jpg","NUM":0},{"IMGURL":"/Image/icotable/0e9015e3-e60a-47fe-aca7-d5012f25b932.jpg","NUM":0}]
     * POSTAGE : 12.0
     * PRICE : 78.0
     * STANDARDS : [{"SD_TB_ID":"0ebb3fec-fa80-43c0-a2b5-21ecb18eb3a7","STANDARD":"大号防晒"},{"SD_TB_ID":"1781ace2-f861-45b2-9f35-d9d734449dd0","STANDARD":"小号防晒"}]
     * TARFFFLG : 0
     * TRADENAME : BB霜
     */

    private Object DetailUrl;
    private int msg;
    private String ADDFAV;
    private double BARGAIN;
    private String CID;
    private int DCTFLG;
    private String DELFAV;
    private String DELIVERY;
    private double DISCOUNT;
    private String ICOURL;
    private String NOTES;
    private double POSTAGE;
    private double PRICE;
    private int TARFFFLG;
    private String TRADENAME;
    /**
     * IMGURL : /Image/img/27aff476-0939-48b5-9ade-7a185f58ea2a.jpg
     */

    private List<IMGSBean> IMGS;
    /**
     * DetailUrl : Items.svc//item//4e5672ac-522d-4066-bcb2-475e58327781
     * msg : 0
     * BARGAIN : 160.3
     * CID : 4e5672ac-522d-4066-bcb2-475e58327781
     * DISCOUNT : 7.0
     * ICOURL : /Image/ico/abbef866-dbcc-42b8-94ea-ccc1d830ace5.jpg
     * PRICE : 229.0
     * TRADENAME : 眼线
     */

    private List<OTHERBean> OTHER;
    /**
     * IMGURL : /Image/icotable/d8b9522b-5750-4113-861c-a8928ff2e6e9.jpg
     * NUM : 0
     */

    private List<PICSBean> PICS;
    /**
     * SD_TB_ID : 0ebb3fec-fa80-43c0-a2b5-21ecb18eb3a7
     * STANDARD : 大号防晒
     */

    private List<STANDARDSBean> STANDARDS;

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

    public String getADDFAV() {
        return ADDFAV;
    }

    public void setADDFAV(String ADDFAV) {
        this.ADDFAV = ADDFAV;
    }

    public double getBARGAIN() {
        return BARGAIN;
    }

    public void setBARGAIN(double BARGAIN) {
        this.BARGAIN = BARGAIN;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public int getDCTFLG() {
        return DCTFLG;
    }

    public void setDCTFLG(int DCTFLG) {
        this.DCTFLG = DCTFLG;
    }

    public  String getDELFAV() {
        return DELFAV;
    }

    public void setDELFAV( String DELFAV) {
        this.DELFAV = DELFAV;
    }

    public String getDELIVERY() {
        return DELIVERY;
    }

    public void setDELIVERY(String DELIVERY) {
        this.DELIVERY = DELIVERY;
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

    public String getNOTES() {
        return NOTES;
    }

    public void setNOTES(String NOTES) {
        this.NOTES = NOTES;
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

    public int getTARFFFLG() {
        return TARFFFLG;
    }

    public void setTARFFFLG(int TARFFFLG) {
        this.TARFFFLG = TARFFFLG;
    }

    public String getTRADENAME() {
        return TRADENAME;
    }

    public void setTRADENAME(String TRADENAME) {
        this.TRADENAME = TRADENAME;
    }

    public List<IMGSBean> getIMGS() {
        return IMGS;
    }

    public void setIMGS(List<IMGSBean> IMGS) {
        this.IMGS = IMGS;
    }

    public List<OTHERBean> getOTHER() {
        return OTHER;
    }

    public void setOTHER(List<OTHERBean> OTHER) {
        this.OTHER = OTHER;
    }

    public List<PICSBean> getPICS() {
        return PICS;
    }

    public void setPICS(List<PICSBean> PICS) {
        this.PICS = PICS;
    }

    public List<STANDARDSBean> getSTANDARDS() {
        return STANDARDS;
    }

    public void setSTANDARDS(List<STANDARDSBean> STANDARDS) {
        this.STANDARDS = STANDARDS;
    }

    public static class IMGSBean {
        private String IMGURL;

        public String getIMGURL() {
            return IMGURL;
        }

        public void setIMGURL(String IMGURL) {
            this.IMGURL = IMGURL;
        }
    }

    public static class OTHERBean {
        private String DetailUrl;
        private int msg;
        private double BARGAIN;
        private String CID;
        private double DISCOUNT;
        private String ICOURL;
        private double PRICE;
        private String TRADENAME;

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

        public String getTRADENAME() {
            return TRADENAME;
        }

        public void setTRADENAME(String TRADENAME) {
            this.TRADENAME = TRADENAME;
        }
    }

    public static class PICSBean {
        private String IMGURL;
        private int NUM;

        public String getIMGURL() {
            return IMGURL;
        }

        public void setIMGURL(String IMGURL) {
            this.IMGURL = IMGURL;
        }

        public int getNUM() {
            return NUM;
        }

        public void setNUM(int NUM) {
            this.NUM = NUM;
        }
    }

    public static class STANDARDSBean {
        private String SD_TB_ID;
        private String STANDARD;
        private boolean nameIsSelect;//商品属性是否选中

        public boolean getNameIsSelect() {
            return nameIsSelect;
        }

        public void setNameIsSelect(boolean nameIsSelect) {
            this.nameIsSelect = nameIsSelect;
        }

        public String getSD_TB_ID() {
            return SD_TB_ID;
        }

        public void setSD_TB_ID(String SD_TB_ID) {
            this.SD_TB_ID = SD_TB_ID;
        }

        public String getSTANDARD() {
            return STANDARD;
        }

        public void setSTANDARD(String STANDARD) {
            this.STANDARD = STANDARD;
        }
    }
}
