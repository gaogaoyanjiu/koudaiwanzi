package com.example.administrator.koudaiwanzi.details.detail;

import java.util.List;

/**
 * Created by Administrator on 2016/6/23.
 */
public class DetailBean {

    /**
     * DetailUrl : null
     * msg : 1
     * ADDFAV : Items.svc//addfavorites//4e5672ac-522d-4066-bcb2-475e58327781//d17ada19-b1f0-4003-b857-f1e671f6a0f0
     * BARGAIN : 160.3
     * CID : 4e5672ac-522d-4066-bcb2-475e58327781
     * DCTFLG : 0
     * DELFAV : Items.svc//delfavorites//4e5672ac-522d-4066-bcb2-475e58327781//d17ada19-b1f0-4003-b857-f1e671f6a0f0
     * DELIVERY : 国内只有
     * DISCOUNT : 7.0
     * ICOURL : /Image/ico/abbef866-dbcc-42b8-94ea-ccc1d830ace5.jpg
     * IMGS : [{"IMGURL":"/Image/img/6e9f0a35-54e9-4153-b0ac-7abf617bb9da.jpg"},{"IMGURL":"/Image/img/c9c99813-a370-4950-84c4-549d9d3f019c.jpg"},{"IMGURL":"/Image/img/d6ced897-a98b-432d-be52-f0374412488e.jpg"}]
     * NOTES : 19天退货
     * OTHER : [{"DetailUrl":"Items.svc//item//3b7e436c-0c12-4f16-8362-da477b71632a","msg":0,"BARGAIN":70.2,"CID":"3b7e436c-0c12-4f16-8362-da477b71632a","DISCOUNT":9,"ICOURL":"/Image/ico/ebd63921-a256-4eca-afb2-7e09b30a8d74.jpg","PRICE":78,"TRADENAME":"BB霜"},{"DetailUrl":"Items.svc//item//a2420bc9-2fce-4c93-a15c-9be137a59c00","msg":0,"BARGAIN":215.9,"CID":"a2420bc9-2fce-4c93-a15c-9be137a59c00","DISCOUNT":8.5,"ICOURL":"/Image/ico/59fd4032-dc51-4de8-96c3-11133c9bf5ee.jpg","PRICE":254,"TRADENAME":"散粉"}]
     * PICS : [{"IMGURL":"/Image/icotable/6cfd6f82-0bd0-4749-8866-3b5e00b54ed4.jpg","NUM":0},{"IMGURL":"/Image/icotable/2517b06b-39e9-4e7a-a7d7-e393ee09da6b.jpg","NUM":0},{"IMGURL":"/Image/icotable/01b3a939-6498-4e89-aeea-1e21650e9ce8.jpg","NUM":0},{"IMGURL":"/Image/icotable/1cfd0e81-e839-4fad-a269-25eb479fe1a8.jpg","NUM":0},{"IMGURL":"/Image/icotable/1bca92fd-15b7-4df0-a054-e083ccab5483.jpg","NUM":0},{"IMGURL":"/Image/icotable/fb46bd29-2088-4888-8940-06e9a9cd1421.jpg","NUM":0},{"IMGURL":"/Image/icotable/1d23c44b-c2d5-4472-be6c-6aef2fd26eba.jpg","NUM":0},{"IMGURL":"/Image/icotable/8e5254eb-ff6c-4090-9be3-c901c2958ee9.jpg","NUM":0},{"IMGURL":"/Image/icotable/c89abddc-57ca-4e66-b4d7-c96a2b18e4e6.jpg","NUM":0},{"IMGURL":"/Image/icotable/bf0d928a-fe15-4dfc-8e9e-cddf60d6d79b.jpg","NUM":0},{"IMGURL":"/Image/icotable/17a1a6a0-f5d4-4315-9af9-208605d85c6b.jpg","NUM":0},{"IMGURL":"/Image/icotable/a8e33594-e681-4bb0-bc01-cea081151fd7.jpg","NUM":0}]
     * POSTAGE : 99.0
     * PRICE : 229.0
     * STANDARDS : [{"SD_TB_ID":"4e30fbc5-1cb5-4357-8b42-b9d0bf95f1e6","STANDARD":"黑色"},{"SD_TB_ID":"747487f1-8fda-45cc-b160-8970ee41c286","STANDARD":"粑粑黄"},{"SD_TB_ID":"92242b15-32ba-4cd7-81ce-b34e6a963797","STANDARD":"白色"}]
     * TARFFFLG : 0
     * TRADENAME : 眼线
     */
    private int PAYCOUNT;

    public int getPAYCOUNT() {
        return PAYCOUNT;
    }

    public void setPAYCOUNT(int PAYCOUNT) {
        this.PAYCOUNT = PAYCOUNT;
    }

    private Object DetailUrl;
    private int msg;
    private String ADDFAV;
    private double BARGAIN;
    private String CID;
    private String COMMENT;
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

    public int getISOUTSTOCK() {
        return ISOUTSTOCK;
    }

    public void setISOUTSTOCK(int ISOUTSTOCK) {
        this.ISOUTSTOCK = ISOUTSTOCK;
    }

    private int ISOUTSTOCK;

    public void setOTHERURL(String OTHERURL) {
        this.OTHERURL = OTHERURL;
    }

    public String getOTHERURL() {

        return OTHERURL;
    }

    private String OTHERURL;
    /**
     * IMGURL : /Image/img/6e9f0a35-54e9-4153-b0ac-7abf617bb9da.jpg
     */

    private List<IMGSBean> IMGS;
    /**
     * DetailUrl : Items.svc//item//3b7e436c-0c12-4f16-8362-da477b71632a
     * msg : 0
     * BARGAIN : 70.2
     * CID : 3b7e436c-0c12-4f16-8362-da477b71632a
     * DISCOUNT : 9.0
     * ICOURL : /Image/ico/ebd63921-a256-4eca-afb2-7e09b30a8d74.jpg
     * PRICE : 78.0
     * TRADENAME : BB霜
     */

    private List<OTHERBean> OTHER;
    /**
     * IMGURL : /Image/icotable/6cfd6f82-0bd0-4749-8866-3b5e00b54ed4.jpg
     * NUM : 0
     */

    private List<PICSBean> PICS;
    /**
     * SD_TB_ID : 4e30fbc5-1cb5-4357-8b42-b9d0bf95f1e6
     * STANDARD : 黑色
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

    public void setCOMMENT(String COMMENT) {
        this.COMMENT = COMMENT;
    }

    public String getCOMMENT() {

        return COMMENT;
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

    public String getDELFAV() {
        return DELFAV;
    }

    public void setDELFAV(String DELFAV) {
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
