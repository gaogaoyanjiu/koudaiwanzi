package com.example.administrator.koudaiwanzi.shopcar;

import java.util.List;

/**
 * Created by Administrator on 2016/8/23.
 */
public class SelectAddressBean {
    /**
     * DetailUrl : Users.svc/deliveryaddress/90039e93-3a25-42d2-9b60-97a42fb93ada
     * msg : 0
     * ADDRESS : [{"DetailUrl":"Users.svc/adselected/05eff1e4-1cbe-4ad5-a982-77990250001b/90039e93-3a25-42d2-9b60-97a42fb93ada","msg":0,"AID":"05eff1e4-1cbe-4ad5-a982-77990250001b","AREA":"沙河口区","CITY":"大连市","CONSIGNEE":"硕","DELETEADDRESS":null,"FULLADRESS":"沙河扣去","ISDEF":0,"MODIFYADDRESS":null,"PROVINCE":"辽宁省","SETADDRESS":null,"TEL":"15524575712","ZIPCODE":"116000"},{"DetailUrl":"Users.svc/adselected/2d982ee3-87fc-4d6e-9d8d-c60bd0da52c9/90039e93-3a25-42d2-9b60-97a42fb93ada","msg":0,"AID":"2d982ee3-87fc-4d6e-9d8d-c60bd0da52c9","AREA":"枞阳县","CITY":"阿里地区","CONSIGNEE":"hshsh","DELETEADDRESS":null,"FULLADRESS":"sjsjsjsh","ISDEF":0,"MODIFYADDRESS":null,"PROVINCE":"西藏自治区","SETADDRESS":null,"TEL":"15652826375","ZIPCODE":"2"},{"DetailUrl":"Users.svc/adselected/7005feeb-a8b3-4680-bd1b-978d9b761828/90039e93-3a25-42d2-9b60-97a42fb93ada","msg":0,"AID":"7005feeb-a8b3-4680-bd1b-978d9b761828","AREA":"居巢区","CITY":"巢湖市","CONSIGNEE":"凤凰韩国","DELETEADDRESS":null,"FULLADRESS":"银行股改革","ISDEF":1,"MODIFYADDRESS":null,"PROVINCE":"安徽省","SETADDRESS":null,"TEL":"4568558888","ZIPCODE":"238000"},{"DetailUrl":"Users.svc/adselected/bd2095aa-25ae-48a0-94b8-b8627b944dac/90039e93-3a25-42d2-9b60-97a42fb93ada","msg":0,"AID":"bd2095aa-25ae-48a0-94b8-b8627b944dac","AREA":"昌平区","CITY":"南平市","CONSIGNEE":"洛阳","DELETEADDRESS":null,"FULLADRESS":"咖喱鸡块","ISDEF":0,"MODIFYADDRESS":null,"PROVINCE":"福建省","SETADDRESS":null,"TEL":"15652826375","ZIPCODE":"100000"}]
     * MANGURL : null
     */

    private String DetailUrl;
    private int msg;
    private Object MANGURL;
    /**
     * DetailUrl : Users.svc/adselected/05eff1e4-1cbe-4ad5-a982-77990250001b/90039e93-3a25-42d2-9b60-97a42fb93ada
     * msg : 0
     * AID : 05eff1e4-1cbe-4ad5-a982-77990250001b
     * AREA : 沙河口区
     * CITY : 大连市
     * CONSIGNEE : 硕
     * DELETEADDRESS : null
     * FULLADRESS : 沙河扣去
     * ISDEF : 0
     * MODIFYADDRESS : null
     * PROVINCE : 辽宁省
     * SETADDRESS : null
     * TEL : 15524575712
     * ZIPCODE : 116000
     */

    private List<ADDRESSBean> ADDRESS;

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

    public Object getMANGURL() {
        return MANGURL;
    }

    public void setMANGURL(Object MANGURL) {
        this.MANGURL = MANGURL;
    }

    public List<ADDRESSBean> getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(List<ADDRESSBean> ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public static class ADDRESSBean {
        private String DetailUrl;
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
}
