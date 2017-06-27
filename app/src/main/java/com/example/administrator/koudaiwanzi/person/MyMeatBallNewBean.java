package com.example.administrator.koudaiwanzi.person;

import java.util.List;

/**
 * Created by Administrator on 2016/09/14.
 */
public class MyMeatBallNewBean {


    /**
     * DetailUrl : Items.svc/insertbloystory/6b435d62-e40c-4a6b-bb24-dc599779bfe6
     * msg : 0
     * DELCONCEMURL : Items.svc/delconcem/6b435d62-e40c-4a6b-bb24-dc599779bfe6/6b435d62-e40c-4a6b-bb24-dc599779bfe6/0
     * HEADURL : /Image/UserHeadImg/4d35f1f1-6d81-4d8b-a80f-5efbe80a45ad.jpg
     * USERNAME : WZ_2807923417
     * story : []
     */

    private String DetailUrl;
    private int msg;
    private String DELCONCEMURL;
    private String HEADURL;
    private String USERNAME;
    private List<Object> story;

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

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public List<Object> getStory() {
        return story;
    }

    public void setStory(List<Object> story) {
        this.story = story;
    }
}
