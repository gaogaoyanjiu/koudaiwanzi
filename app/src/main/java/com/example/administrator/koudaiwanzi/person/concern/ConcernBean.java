package com.example.administrator.koudaiwanzi.person.concern;

/**
 * Created by Administrator on 2016/8/16.
 */
public class ConcernBean {
    /**
     * DetailUrl : Users.svc/minecondetail/3dbab2c5-6e8e-4f38-baa4-c399c3601a06/1e4b15ba-a3ca-48d3-bcc0-0a4b3d4a7ec8
     * msg : 0
     * ADDCONCEMURL : null
     * BLOYNUM : 4
     * CONCEMSTATE : 0
     * CONUM : 1
     * DELCONCEMURL : Items.svc/delconcem/1e4b15ba-a3ca-48d3-bcc0-0a4b3d4a7ec8/3dbab2c5-6e8e-4f38-baa4-c399c3601a06
     * HEADURL : null
     * MID : 1e4b15ba-a3ca-48d3-bcc0-0a4b3d4a7ec8
     * USERNAME : 管理员
     */

    private String DetailUrl;
    private int msg;
    private String ADDCONCEMURL;
    private int BLOYNUM;
    private int CONCEMSTATE;
    private int CONUM;
    private String DELCONCEMURL;
    private String HEADURL;
    private String MID;
    private String USERNAME;

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

    public String getADDCONCEMURL() {
        return ADDCONCEMURL;
    }

    public void setADDCONCEMURL(String ADDCONCEMURL) {
        this.ADDCONCEMURL = ADDCONCEMURL;
    }

    public int getBLOYNUM() {
        return BLOYNUM;
    }

    public void setBLOYNUM(int BLOYNUM) {
        this.BLOYNUM = BLOYNUM;
    }

    public int getCONCEMSTATE() {
        return CONCEMSTATE;
    }

    public void setCONCEMSTATE(int CONCEMSTATE) {
        this.CONCEMSTATE = CONCEMSTATE;
    }

    public int getCONUM() {
        return CONUM;
    }

    public void setCONUM(int CONUM) {
        this.CONUM = CONUM;
    }

    public String getDELCONCEMURL() {
        return DELCONCEMURL;
    }

    public void setDELCONCEMURL(String DELCONCEMURL) {
        this.DELCONCEMURL = DELCONCEMURL;
    }

    public String getHEADURL() {
        return HEADURL;
    }

    public void setHEADURL(String HEADURL) {
        this.HEADURL = HEADURL;
    }

    public String getMID() {
        return MID;
    }

    public void setMID(String MID) {
        this.MID = MID;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }
}
