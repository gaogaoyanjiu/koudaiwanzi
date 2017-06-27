package com.example.administrator.koudaiwanzi.zfb;

/**
 * Created by Administrator on 2016/12/1.
 */

public class ZfbBean {


    public String getSuccessurl() {
        return successurl;
    }

    public void setSuccessurl(String successurl) {
        this.successurl = successurl;
    }

    /**
     * paystring : partner="2088421708298584"&seller_id="wxkfpt@victorgroup-beijing.com"&out_trade_no="kdwz340314455095047"&subject="我是测试数据"&body="口袋丸子"&total_fee="1"&currency="JPY"&forex_biz="FP"&notify_url="http://218.24.166.153:7878/Pay.svc/zfbcheckpay"&service="mobile.securitypay.pay"&payment_type="1"&_input_charset="utf-8"&it_b_pay="30m"&show_url="m.alipay.com"&sign="mnr3l4OZLeYsg66bCIMz4Qx1QrDxu8SYIYSdmnADtBgP6SXfhGsii30VArVra4Joty7yVo9lpvvAj3qERiN3h1TXVvWbjkvAcxRNrDxZJT3vL%2bS0GgoKofMt941noJ4PXBpksJiAf0AprKtvyYL5YzUhmzTmyu2wyJzNfjglPa4%3d"&sign_type="RSA"
     */
    private String successurl;
    private String paystring;

    public String getPaystring() {
        return paystring;
    }

    public void setPaystring(String paystring) {
        this.paystring = paystring;
    }
}
