package com.example.administrator.koudaiwanzi.person.address;

/**
 * Created by Administrator on 2016/6/29.
 */
public class ChangeAddressBean {
    /**
     * DetailUrl : Users.svc//modifyaddress//49a551d8-0522-45bc-831c-4d2d19a653f3//
     * msg : 0
     * AREA : 其他
     * CITY : 马鞍山市
     * CONSIGNEE : 李
     * DELETEADDRESS : null
     * FULLADRESS : 1111
     * ISDEF : 0
     * MODIFYADDRESS : null
     * PROVINCE : 安徽省
     * SETADDRESS : null
     * TEL : 15652826375
     * ZIPCODE : 404100
     */

    private String DetailUrl;
    private int msg;
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
    private int ZIPCODE;

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

    public int getZIPCODE() {
        return ZIPCODE;
    }

    public void setZIPCODE(int ZIPCODE) {
        this.ZIPCODE = ZIPCODE;
    }
}
