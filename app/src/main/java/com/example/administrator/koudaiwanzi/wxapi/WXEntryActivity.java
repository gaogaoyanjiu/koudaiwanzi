package com.example.administrator.koudaiwanzi.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.administrator.koudaiwanzi.Constants;
import com.example.administrator.koudaiwanzi.PayActivity;
import com.example.administrator.koudaiwanzi.PayBean;
import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.person.set.AccountActivity;
import com.example.administrator.koudaiwanzi.unionpay.Unionpay;
import com.example.administrator.koudaiwanzi.zfb.PayDemoActivity;
import com.example.administrator.koudaiwanzi.zfb.PayResult;
import com.example.administrator.koudaiwanzi.zfb.ZfbBean;
import com.google.gson.Gson;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


public class WXEntryActivity extends Activity {
    private RelativeLayout payBtn, pay_yinlian,pay_zfb;
    private ImageView iv_back;
    private String url;
    private String myUrl = MyUrl.url;
    private String detailUrl;
    private TextView tv_price;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_CHECK_FLAG = 2;
    private String paysuccessurl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_pay);

        //接受订单信息
        detailUrl = getIntent().getStringExtra("detailUrl");

        url = myUrl + detailUrl;

        String price = getIntent().getStringExtra("price");

        tv_price = (TextView) findViewById(R.id.total_order_price);
        tv_price.setText("¥" + price);


        //返回键的监听
        iv_back = (ImageView) findViewById(R.id.iv_pay);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("zenmehuishia", "onReceive: ");
                sendBroadcast();
                finish();
            }
        });

        //微信支付
        payBtn = (RelativeLayout) findViewById(R.id.rl_pay_weixin);
        //点击微信把订单信息传给微信支付界面
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestParams params = new RequestParams(url);
                Log.e("adzcv", url);
                x.http().get(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Gson gson = new Gson();
                        final WXBean bean = gson.fromJson(s, WXBean.class);
                        Intent intent = new Intent(WXEntryActivity.this, PayActivity.class);
                        intent.putExtra("WX", bean.getWXPAYURL());
                        startActivity(intent);

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

            }
        });

        //银联支付
        pay_yinlian = (RelativeLayout) findViewById(R.id.rl_pay_yinlian);
        //银联支付
        pay_yinlian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestParams params = new RequestParams(url);
                Log.e("adzcv", url);
                x.http().get(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Gson gson = new Gson();
                        final WXBean bean = gson.fromJson(s, WXBean.class);
                        Intent intent = new Intent(WXEntryActivity.this, Unionpay.class);
                        intent.putExtra("Unionpay", bean.getCHINAPAYURL());
                        startActivity(intent);

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

            }
        });


        //支付宝支付
        pay_zfb = (RelativeLayout) findViewById(R.id.rl_pay_zfb);
        //银联支付
        pay_zfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestParams params = new RequestParams(url);
                Log.e("adzcv", url);
                x.http().get(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Gson gson = new Gson();
                        final WXBean bean = gson.fromJson(s, WXBean.class);
//                        Intent intent = new Intent(WXEntryActivity.this, PayDemoActivity.class);
//                        intent.putExtra("ZFBpay", );
//                        startActivity(intent);
                        String url = myUrl +bean.getZFBPAYURL();
                        RequestParams params = new RequestParams(url);
                        Log.d("128wasdwad",url);
                        x.http().get(params, new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String s) {
                                Gson gson = new Gson();
                                final ZfbBean bean = gson.fromJson(s, ZfbBean.class);
                                final String payInfoGet1;
                                payInfoGet1 = bean.getPaystring();
                                paysuccessurl = bean.getSuccessurl();
                                Log.d("128wasdwad", payInfoGet1);
                                Runnable payRunnable = new Runnable() {

                                    @Override
                                    public void run() {
                                        // 构造PayTask 对象
                                        PayTask alipay = new PayTask(WXEntryActivity.this);
                                        // 调用支付接口，获取支付结果
                                        String result = alipay.pay(payInfoGet1);

                                        Message msg = new Message();
                                        msg.what = SDK_PAY_FLAG;
                                        msg.obj = result;
                                        mHandler.sendMessage(msg);
                                    }
                                };

                                // 必须异步调用
                                Thread payThread = new Thread(payRunnable);
                                payThread.start();
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

            }
        });
    }
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);

                    // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                    String resultInfo = payResult.getResult();
                    Log.e("fdsaf", resultInfo);
                    String resultStatus = payResult.getResultStatus();
                    Log.e("fdsaf", resultStatus);
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        String url = myUrl+paysuccessurl;
                        RequestParams params = new RequestParams(url);
                        x.http().get(params, new org.xutils.common.Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String s) {
                                Toast.makeText(WXEntryActivity.this, "支付成功",
                                        Toast.LENGTH_SHORT).show();
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

                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(WXEntryActivity.this, "支付结果确认中",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(WXEntryActivity.this, "支付失败",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                }
                case SDK_CHECK_FLAG: {
                    Toast.makeText(WXEntryActivity.this, "检查结果为：" + msg.obj,
                            Toast.LENGTH_SHORT).show();
                    break;
                }
                default:
                    break;
            }
        };
    };
    //发送广播的方法
    public void sendBroadcast() {
        Log.d("zenmehuishia", "onReceive: 123");
        Intent intent1 = new Intent("com.example.administrator.koudaiwanzi.shopca.broadcast");
        LocalBroadcastManager.getInstance(WXEntryActivity.this).sendBroadcast(intent1);
    }
}