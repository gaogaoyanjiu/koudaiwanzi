package com.example.administrator.koudaiwanzi.person.reject;

/**
 * Created by Administrator on 2016/09/30.
 */
public class SecBean {

    /**
     * INSERTCERTPICURL : Users.svc/insertcertnum/934f67b9-6eca-4367-9f9a-80fe39bb3f0a/4292d536-5f0a-4f4e-97d9-6bf7286c6a73
     * INSERTORDERNUMURL : Users.svc/insertordernum/934f67b9-6eca-4367-9f9a-80fe39bb3f0a/4292d536-5f0a-4f4e-97d9-6bf7286c6a73/
     */

    private String INSERTCERTPICURL;
    private String INSERTORDERNUMURL;

    public String getINSERTCERTPICURL() {
        return INSERTCERTPICURL;
    }

    public void setINSERTCERTPICURL(String INSERTCERTPICURL) {
        this.INSERTCERTPICURL = INSERTCERTPICURL;
    }

    public String getINSERTORDERNUMURL() {
        return INSERTORDERNUMURL;
    }

    public void setINSERTORDERNUMURL(String INSERTORDERNUMURL) {
        this.INSERTORDERNUMURL = INSERTORDERNUMURL;
    }
}
