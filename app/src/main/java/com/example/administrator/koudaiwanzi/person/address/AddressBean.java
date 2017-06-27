package com.example.administrator.koudaiwanzi.person.address;

/**
 * Created by Administrator on 2016/6/8.
 */
public class AddressBean {

    /**
     * DetailUrl : null
     * msg : 0
     * AREA : 高新园区
     * CITY : 大连市
     * CONSIGNEE : 小明
     * DELETEADDRESS : Users.svc//deleteAddress//d92dda2b-d7c8-4306-84ca-aaa98b510fad//d17ada19-b1f0-4003-b857-f1e671f6a0f0
     * FULLADRESS : 一条街
     * ISDEF : 0
     * MODIFYADDRESS : Users.svc//theaddress//d92dda2b-d7c8-4306-84ca-aaa98b510fad
     * PROVINCE : 辽宁省
     * SETADDRESS : Users.svc//setaddress//d17ada19-b1f0-4003-b857-f1e671f6a0f0//d92dda2b-d7c8-4306-84ca-aaa98b510fad
     * TEL : 15652826371
     * ZIPCODE : 10000
     */

    private Object DetailUrl;
    private int msg;
    private String AREA;
    private String CITY;
    private String CONSIGNEE;
    private String DELETEADDRESS;
    private String FULLADRESS;
    private int ISDEF;
    private String MODIFYADDRESS;
    private String PROVINCE;
    private String SETADDRESS;
    private String TEL;
    private int ZIPCODE;

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

    public String getDELETEADDRESS() {
        return DELETEADDRESS;
    }

    public void setDELETEADDRESS(String DELETEADDRESS) {
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

    public String getMODIFYADDRESS() {
        return MODIFYADDRESS;
    }

    public void setMODIFYADDRESS(String MODIFYADDRESS) {
        this.MODIFYADDRESS = MODIFYADDRESS;
    }

    public String getPROVINCE() {
        return PROVINCE;
    }

    public void setPROVINCE(String PROVINCE) {
        this.PROVINCE = PROVINCE;
    }

    public String getSETADDRESS() {
        return SETADDRESS;
    }

    public void setSETADDRESS(String SETADDRESS) {
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
