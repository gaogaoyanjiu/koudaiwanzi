package com.example.administrator.koudaiwanzi.person.mymeatball;

/**
 * Created by Administrator on 2016/09/19.
 */
public class SendMeatBallBean {

    /**
     * DetailUrl : Items.svc/insertbloynote/6881cb5c-e50b-4fed-aa3b-3c2d2c58262e/a3b2814d-c8e5-402e-8a50-b4ba3cfc0f1f
     * msg : 0
     * IMGURL : FileStream.svc/insertimg/6881cb5c-e50b-4fed-aa3b-3c2d2c58262e/a3b2814d-c8e5-402e-8a50-b4ba3cfc0f1f
     * PREVIEWURL : FileStream.svc/insertpreview/6881cb5c-e50b-4fed-aa3b-3c2d2c58262e/a3b2814d-c8e5-402e-8a50-b4ba3cfc0f1f
     */

    private String DetailUrl;
    private int msg;
    private String IMGURL;
    private String PREVIEWURL;

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

    public String getIMGURL() {
        return IMGURL;
    }

    public void setIMGURL(String IMGURL) {
        this.IMGURL = IMGURL;
    }

    public String getPREVIEWURL() {
        return PREVIEWURL;
    }

    public void setPREVIEWURL(String PREVIEWURL) {
        this.PREVIEWURL = PREVIEWURL;
    }
}
