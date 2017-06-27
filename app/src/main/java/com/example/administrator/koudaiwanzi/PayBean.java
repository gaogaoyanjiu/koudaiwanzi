package com.example.administrator.koudaiwanzi;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/8/1.
 */
public class PayBean {



 /**
     * SUCCESSURL : null
     * appid : wxe24ef99553c9f77b
     * nonceStr : c203d8a151612acf12457e4d67635a95
     * package : Sign=WXPay
     * partnerId : 1366179502
     * prepayId : wx20160811153952c80c7cce960876859284
     * sign : 91838733E549006A9057E6F2370C4004
     * timeStamp : 1470901196
     * total : 99.9
     */

    private String SUCCESSURL;
    private String appid;
    private String nonceStr;
    @SerializedName("package")
    private String packageX;
    private String partnerId;
    private String prepayId;
    private String sign;
    private String timeStamp;
    private double total;

    public String getSUCCESSURL() {
        return SUCCESSURL;
    }

    public void setSUCCESSURL(String SUCCESSURL) {
        this.SUCCESSURL = SUCCESSURL;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
