package com.example.administrator.koudaiwanzi;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.google.gson.Gson;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class PayActivity extends Activity {
    private Button appayBtn;
    private Button payBtn;
    private IWXAPI api;
    private String url1 = MyUrl.url;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay);
        api = WXAPIFactory.createWXAPI(this, "wxb4ba3c02aa476ea1");
        appayBtn = (Button) findViewById(R.id.appay_btn);
        payBtn = (Button) findViewById(R.id.appay_btn);
        Intent intent = getIntent();
        String url2 = intent.getStringExtra("WX");
        final String url = url1 + url2 + "192.168.1.43";

        Log.e("gddf",url);
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                final PayBean bean = gson.fromJson(s, PayBean.class);
//                appayBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
                        payBtn.setEnabled(false);
                        Toast.makeText(PayActivity.this, "获取订单中...", Toast.LENGTH_SHORT).show();
                        try {
                            if (url.length() > 0) {
                                if (url.length() > 0) {
                                    PayReq req = new PayReq();
                                    //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
                                    req.appId = bean.getAppid();
                                    req.partnerId = bean.getPartnerId();
                                    req.prepayId = bean.getPrepayId();
                                    req.nonceStr = bean.getNonceStr();
                                    req.timeStamp = bean.getTimeStamp() + "";
                                    req.packageValue = "Sign=WXPay";
                                    req.sign = bean.getSign();
                                    req.extData = "app data"; // optional
                                    Toast.makeText(PayActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
                                    // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                                    api.sendReq(req);

                                    //传给回调函数的网址
                                    SharedPreferences share = PayActivity.this.getSharedPreferences("huidiao", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = share.edit();
                                    editor.putString("huidiao",MyUrl.url + bean.getSUCCESSURL());
                                    Log.e("wwasxg", MyUrl.url + bean.getSUCCESSURL());
                                    editor.apply();
                                    finish();

                                } else {
                                    Toast.makeText(PayActivity.this, "返回错误", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Log.d("PAY_GET", "服务器请求错误");
                                Toast.makeText(PayActivity.this, "服务器请求错误", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Log.e("PAY_GET", "异常：" + e.getMessage());
                            Toast.makeText(PayActivity.this, "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                       // payBtn.setEnabled(true);
//                    }
//                });
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });


        Button checkPayBtn = (Button) findViewById(R.id.check_pay_btn);
        checkPayBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
                Toast.makeText(PayActivity.this, String.valueOf(isPaySupported), Toast.LENGTH_SHORT).show();
            }
        });

        imageView = (ImageView) findViewById(R.id.iv_pay_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



}
