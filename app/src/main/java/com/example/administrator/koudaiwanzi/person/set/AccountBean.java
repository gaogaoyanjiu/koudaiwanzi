package com.example.administrator.koudaiwanzi.person.set;

/**
 * Created by Administrator on 2016/6/27.
 */
public class AccountBean {
    /**
     * DetailUrl : Users.svc//saveacmgt//d17ada19-b1f0-4003-b857-f1e671f6a0f0//
     * msg : 0
     * BIRTHDAY : 1111000
     * HEADURL : /Image/UserHeadImg/a99bc28b-8d86-4e58-885d-47be2b0b0c5c.jpg
     * NICKNAME : 昵称
     * SEX : 1
     * USERNAME : null
     */

    private String DetailUrl;
    private int msg;
    private int BIRTHDAY;
    private String HEADURL;
    private String NICKNAME;
    private int SEX;
    private String USERNAME;
    private String PICDATAURL;

    public String getPICDATAURL() {
        return PICDATAURL;
    }

    public void setPICDATAURL(String PICDATAURL) {
        this.PICDATAURL = PICDATAURL;
    }

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

    public int getBIRTHDAY() {
        return BIRTHDAY;
    }

    public void setBIRTHDAY(int BIRTHDAY) {
        this.BIRTHDAY = BIRTHDAY;
    }

    public String getHEADURL() {
        return HEADURL;
    }

    public void setHEADURL(String HEADURL) {
        this.HEADURL = HEADURL;
    }

    public String getNICKNAME() {
        return NICKNAME;
    }

    public void setNICKNAME(String NICKNAME) {
        this.NICKNAME = NICKNAME;
    }

    public int getSEX() {
        return SEX;
    }

    public void setSEX(int SEX) {
        this.SEX = SEX;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }
}
