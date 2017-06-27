package com.example.administrator.koudaiwanzi.product;

import java.util.List;

/**
 * Created by Administrator on 2016/7/4.
 */
public class ShowProductBean {

    /**
     * DetailUrl : Items.svc//display//1e4b15ba-a3ca-48d3-baa0-97a3cd4a8C98//
     * msg : 0
     * CMENU : []
     * DEFAULTSTATUE : 1
     * DEFAULTURL : Items.svc//display//1e4b15ba-a3ca-48d3-baa0-97a3cd4a8C98
     * DITEMS : [{"DetailUrl":"Items.svc//item//3b7e436c-0c12-4f16-8362-da477b71632a","msg":0,"BARGAIN":70.2,"CID":"3b7e436c-0c12-4f16-8362-da477b71632a","DISCOUNT":9,"ICOURL":"/Image/ico/ebd63921-a256-4eca-afb2-7e09b30a8d74.jpg","PRICE":78,"SALESVOL":98,"TRADENAME":"BB霜"}]
     * FILITERSTATUE : 0
     * FILTER : Items.svc/filter/1e4b15ba-a3ca-48d3-baa0-97a3cd4a8C98
     * PEICESTATUE : 0
     * PEICEURL : Items.svc//displaypice//1e4b15ba-a3ca-48d3-baa0-97a3cd4a8C98//0
     * SALESSTATUE : 0
     * SALESVOLURL : Items.svc//displaysale//1e4b15ba-a3ca-48d3-baa0-97a3cd4a8C98
     * TITLE : null
     */

    private String DetailUrl;
    private int msg;
    private int DEFAULTSTATUE;
    private String DEFAULTURL;
    private int FILITERSTATUE;
    private String FILTER;
    private int PEICESTATUE;
    private String PEICEURL;
    private int SALESSTATUE;
    private String SALESVOLURL;
    private Object TITLE;
    private List<?> CMENU;
    /**
     * DetailUrl : Items.svc//item//3b7e436c-0c12-4f16-8362-da477b71632a
     * msg : 0
     * BARGAIN : 70.2
     * CID : 3b7e436c-0c12-4f16-8362-da477b71632a
     * DISCOUNT : 9.0
     * ICOURL : /Image/ico/ebd63921-a256-4eca-afb2-7e09b30a8d74.jpg
     * PRICE : 78.0
     * SALESVOL : 98
     * TRADENAME : BB霜
     */

    private List<DITEMSBean> DITEMS;

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

    public int getDEFAULTSTATUE() {
        return DEFAULTSTATUE;
    }

    public void setDEFAULTSTATUE(int DEFAULTSTATUE) {
        this.DEFAULTSTATUE = DEFAULTSTATUE;
    }

    public String getDEFAULTURL() {
        return DEFAULTURL;
    }

    public void setDEFAULTURL(String DEFAULTURL) {
        this.DEFAULTURL = DEFAULTURL;
    }

    public int getFILITERSTATUE() {
        return FILITERSTATUE;
    }

    public void setFILITERSTATUE(int FILITERSTATUE) {
        this.FILITERSTATUE = FILITERSTATUE;
    }

    public String getFILTER() {
        return FILTER;
    }

    public void setFILTER(String FILTER) {
        this.FILTER = FILTER;
    }

    public int getPEICESTATUE() {
        return PEICESTATUE;
    }

    public void setPEICESTATUE(int PEICESTATUE) {
        this.PEICESTATUE = PEICESTATUE;
    }

    public String getPEICEURL() {
        return PEICEURL;
    }

    public void setPEICEURL(String PEICEURL) {
        this.PEICEURL = PEICEURL;
    }

    public int getSALESSTATUE() {
        return SALESSTATUE;
    }

    public void setSALESSTATUE(int SALESSTATUE) {
        this.SALESSTATUE = SALESSTATUE;
    }

    public String getSALESVOLURL() {
        return SALESVOLURL;
    }

    public void setSALESVOLURL(String SALESVOLURL) {
        this.SALESVOLURL = SALESVOLURL;
    }

    public Object getTITLE() {
        return TITLE;
    }

    public void setTITLE(Object TITLE) {
        this.TITLE = TITLE;
    }

    public List<?> getCMENU() {
        return CMENU;
    }

    public void setCMENU(List<?> CMENU) {
        this.CMENU = CMENU;
    }

    public List<DITEMSBean> getDITEMS() {
        return DITEMS;
    }

    public void setDITEMS(List<DITEMSBean> DITEMS) {
        this.DITEMS = DITEMS;
    }

    public static class DITEMSBean {
        private String DetailUrl;
        private int msg;
        private double BARGAIN;
        private String CID;
        private double DISCOUNT;
        private String ICOURL;
        private double PRICE;
        private int SALESVOL;
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

        public int getSALESVOL() {
            return SALESVOL;
        }

        public void setSALESVOL(int SALESVOL) {
            this.SALESVOL = SALESVOL;
        }

        public String getTRADENAME() {
            return TRADENAME;
        }

        public void setTRADENAME(String TRADENAME) {
            this.TRADENAME = TRADENAME;
        }
    }
}
