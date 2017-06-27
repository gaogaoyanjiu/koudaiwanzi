package com.example.administrator.koudaiwanzi.wxapi;

/**
 * Created by Administrator on 2016/9/22.
 */
public class WXBean {


    /**
     * CHINAPAYURL : http://218.24.166.153:8080/ChinaPay/pay.html?UID=b2fd42d6-a6f0-4f26-8483-cc6e3f07ecee&NID=c259a4a7-a848-4d6e-80f5-9ab9e84c2495
     * WXPAYURL : Pay.svc/wxpay/c259a4a7-a848-4d6e-80f5-9ab9e84c2495/b2fd42d6-a6f0-4f26-8483-cc6e3f07ecee/
     */

    private String CHINAPAYURL;
    private String WXPAYURL;

    public String getZFBPAYURL() {
        return ZFBPAYURL;
    }

    public void setZFBPAYURL(String ZFBPAYURL) {
        this.ZFBPAYURL = ZFBPAYURL;
    }

    private String ZFBPAYURL;

    public String getCHINAPAYURL() {
        return CHINAPAYURL;
    }

    public void setCHINAPAYURL(String CHINAPAYURL) {
        this.CHINAPAYURL = CHINAPAYURL;
    }

    public String getWXPAYURL() {
        return WXPAYURL;
    }

    public void setWXPAYURL(String WXPAYURL) {
        this.WXPAYURL = WXPAYURL;
    }
}
