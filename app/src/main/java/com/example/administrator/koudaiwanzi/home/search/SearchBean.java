package com.example.administrator.koudaiwanzi.home.search;

/**
 * Created by Administrator on 2016/6/30.
 */
public class SearchBean {
    /**
     * DetailUrl : Items.svc//searchitems//B
     * msg : 0
     * TRADENAME : BB霜
     */

    private String DetailUrl;
    private int msg;
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

    public String getTRADENAME() {
        return TRADENAME;
    }

    public void setTRADENAME(String TRADENAME) {
        this.TRADENAME = TRADENAME;
    }
}
