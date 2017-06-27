package com.example.administrator.koudaiwanzi.zfb;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.administrator.koudaiwanzi.PayActivity;
import com.example.administrator.koudaiwanzi.PayBean;
import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.google.gson.Gson;
import com.tencent.mm.sdk.modelpay.PayReq;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


public class PayDemoActivity extends FragmentActivity {
	private String url1 = MyUrl.url;
	private String payInfoGet;
	// 商户PID
	public static final String PARTNER = "088421708298584";
	// 商户收款账号
	public static final String SELLER = "wxkfpt@victorgroup-beijing.com";
	// 商户私钥，pkcs8格式
//	public static final String RSA_PRIVATE ="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALys+oYaxqv4FYju\n" +
//			"8C1poM6qmHLjWPnXzqEJT0NxyFXgdaK/Qe9DcpcASod9mIAdlLIxJEyYNlWeonAJ\n" +
//			"VYW8pQ+pTVGwI9n0iaT71ldWQzcMN3Dvi/+zpgw3HxxO7HJtEIlR84pvILv1yceC\n" +
//			"ZCqqQ4O/4SemsG00oTiTyD3SM2ZvAgMBAAECgYBLToeX6ywNC7Icu7Hljll+45yB\n" +
//			"jri+0CJLKFoYw1uA21xYnxoEE9my54zX04uA502oafDhGYfmWLDhIvidrpP6oalu\n" +
//			"URb/gbV5Bdcm98gGGVgm6lpK+G5N/eawXDjP0ZjxXb114Y/Hn/oVFVM9OqcujFSV\n" +
//			"+Wg4JgJ4Mmtdr35gYQJBAPbhx030xPcep8/dL5QQMc7ddoOrfxXewKcpDmZJi2ey\n" +
//			"381X+DhuphQ5gSVBbbunRiDCEcuXFY+R7xrgnP+viWcCQQDDpN8DfqRRl+cUhc0z\n" +
//			"/TbnSPJkMT/IQoFeFOE7wMBcDIBoQePEDsr56mtc/trIUh/L6evP9bkjLzWJs/kb\n" +
//			"/i25AkEAtoOf1k/4NUEiipdYjzuRtv8emKT2ZPKytmGx1YjVWKpyrdo1FXMnsJf6\n" +
//			"k9JVD3/QZnNSuJJPTD506AfZyWS6TQJANdeF2Hxd1GatnaRFGO2y0mvs6U30c7R5\n" +
//			"zd6JLdyaE7sNC6Q2fppjmeu9qFYq975CKegykYTacqhnX4I8KEwHYQJAby60iHMA\n" +
//			"YfSUpu//f5LMMRFK2sVif9aqlYbepJcAzJ6zbiSG5E+0xg/MjEj/Blg9rNsqDG4R\n" +
//			"ECGJG2nPR72O8g==";

	private String RSA_PRIVATE = "MIICWwIBAAKBgQC23kxf5KRLbNw8yinF06wBvztnybprcdJmYPIcHOfQV5DwrN1Q\n" +
			"7+6ZWw4gHX63q0MAjpFuaMFO26ESS5TleytC5eYDQ8QVFYFhFfX78qO2w5X9Brzm\n" +
			"oKzXUaaMqET2tGdUc10Wj9Nmks4NxYeqTiFBjhgz/blXnarL0IVy3YrziQIDAQAB\n" +
			"AoGAXzPWISpAmn/fHMrG+pXbe8FaJQtxwRQtzFz2TsqBqQadj57/TW7MEgzEitWw\n" +
			"LeC8biLK9ecVlH9lGMg+iUYZW8UOEIreIn3TZdYjd5Q3R9PkSubc0ADsUsAqjs5Y\n" +
			"KnbQePk0Kke0OnPUYBl3miJ1DI48bmKZwLDiBJFBCSNI6o0CQQDw0qgXZY1BOQXQ\n" +
			"/zX6KGOYjCtKX8RXIO2Sr9UsOkdov5DsnVbG2oV0CZA4gd5X+GStGWMim1X3QG78\n" +
			"qPRemYH/AkEAwmSgM+t7aWxxE7fvwg/8BnWn9u9/t/Ac0t/x48m7PZ4OaQU/SX1m\n" +
			"GwhCaYcIsvmbnUWkE66z2Fnjys7Xy8R6dwJATFNHqRqreUd8W2/3iJByeG+8bLcL\n" +
			"gF3UThBibMEPztggM43uadnqQAuj5PTsJk94gRAxxk8bIOEQDA30XWMjuwJAa1wt\n" +
			"yl78qs3UacKFbRVs6JhkapH9ZLX9BU3fhyGsAqzfYoseV9Uk5jsairOkT0xh8Gs8\n" +
			"4g3zj8Kddy3jqstZowJAHZhX9kpttzS3blhbClVRj5b01MwrkzvFTwvWrTq7duwf\n" +
			"S3XVZaEt93XXlBUpjaa684AjNxyzjWx5TbuMXOyhng==";

	// 支付宝公钥
	public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	private static final int SDK_PAY_FLAG = 1;
	private static final int SDK_CHECK_FLAG = 2;

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
					Toast.makeText(PayDemoActivity.this, "支付成功",
							Toast.LENGTH_SHORT).show();
				} else {
					// 判断resultStatus 为非“9000”则代表可能支付失败
					// “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
					if (TextUtils.equals(resultStatus, "8000")) {
						Toast.makeText(PayDemoActivity.this, "支付结果确认中",
								Toast.LENGTH_SHORT).show();

					} else {
						// 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
						Toast.makeText(PayDemoActivity.this, "支付失败",
								Toast.LENGTH_SHORT).show();

					}
				}
				break;
			}
			case SDK_CHECK_FLAG: {
				Toast.makeText(PayDemoActivity.this, "检查结果为：" + msg.obj,
						Toast.LENGTH_SHORT).show();
				break;
			}
			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pay_main);
		Intent intent = getIntent();
		String url2 = intent.getStringExtra("ZFBpay");
		String url = url1 + url2;
		RequestParams params = new RequestParams(url);
		Log.d("128wasdwad",url);
		x.http().get(params, new Callback.CommonCallback<String>() {
			@Override
			public void onSuccess(String s) {
				Gson gson = new Gson();
				final ZfbBean bean = gson.fromJson(s, ZfbBean.class);
				final String payInfoGet1;
				payInfoGet1 = bean.getPaystring();
				Log.d("128wasdwad", payInfoGet1);
				Runnable payRunnable = new Runnable() {

					@Override
					public void run() {
						// 构造PayTask 对象
						PayTask alipay = new PayTask(PayDemoActivity.this);
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

	/**
	 * call alipay sdk pay. 调用SDK支付
	 * 
	 */
	public void pay(View v) {
		if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE)
				|| TextUtils.isEmpty(SELLER)) {
			new AlertDialog.Builder(this)
					.setTitle("警告")
					.setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								public void onClick(
										DialogInterface dialoginterface, int i) {
									//
									finish();
								}
							}).show();
			return;
		}
		// 订单
		String orderInfo = getOrderInfo("一个商品", "一个好商品", "100");

		// 对订单做RSA 签名
		String sign = sign(payInfoGet);
		try {
			// 仅需对sign 做URL编码
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 完整的符合支付宝参数规范的订单信息
		final String payInfo = payInfoGet;

//		final String payInfo = "partner=\"2088421708298584\"&seller_id=\"wxkfpt@victorgroup-beijing.com\"&out_trade_no=\"0819145412-6156\"&subject=\"《暗黑破坏神3:凯恩之书》\"&body=\"text\"&total_fee=\"1\"&currency=\"JPY\"&forex_biz=\"FP\"&notify_url=\"http://218.24.166.153:7878/Pay.svc/zfbcheckpay\"&service=\"mobile.securitypay.pay\"&payment_type=\"1\"&_input_charset=\"utf-8\"&it_b_pay=\"30m\"&show_url=\"m.alipay.com\"&sign=\"rA24UnZsCQc7Jjq79YqScLGWjg9vINW2ugZeSiz4cOy/m0gXPnFa+59Y/03JFXUQaCVDcUn1mU3+wS7wQrWD0WNV1DiMUk08uhSAZmhbl9CABKDaJF7r2d04wiLgJZTQlR1N6f4hmw1G1PkUUjOEratbqbirLcvJ+5dSmlbDdBY=\"&sign_type=\"RSA\"\n" +
//				"陌生人 16:37:45\n" +
//				"partner=\"2088421708298584\"&seller_id=\"wxkfpt@victorgroup-beijing.com\"&out_trade_no=\"456852951753284\"&subject=\"暗黑破坏恩之书\"&body=\"text\"&total_fee=\"1\"&currency=\"JPY\"&forex_biz=\"FP\"&notify_url=\"http://218.24.166.153:7878/Pay.svc/zfbcheckpay\"&service=\"mobile.securitypay.pay\"&payment_type=\"1\"&_input_charset=\"utf-8\"&it_b_pay=\"30m\"&show_url=\"m.alipay.com\"&sign=\"T2fJyfrvP4BJ/ww+3S7TZREip9OQL0Wo0WwD40eqg7rHWTK7u9smoRAj1I8J0Q+2pPxdbOAYQemXIwN5Gq+Fh3bUnEpvj2fn52qTMMvmh3lYoHnNnbo5EbYs36aMQIJEYzHs+/PxlYm+ImwepPZJr6h4aEd6psxR9jP44V1vT78=\"&sign_type=\"RSA\"";

		Log.e("dzxcx123123", payInfoGet);


	}

	/**
	 * check whether the device has authentication alipay account.
	 * 查询终端设备是否存在支付宝认证账户
	 * 
	 */
	public void check(View v) {
		Runnable checkRunnable = new Runnable() {

			@Override
			public void run() {
				// 构造PayTask 对象
				PayTask payTask = new PayTask(PayDemoActivity.this);
				// 调用查询接口，获取查询结果
				boolean isExist = payTask.checkAccountIfExist();

				Message msg = new Message();
				msg.what = SDK_CHECK_FLAG;
				msg.obj = isExist;
				mHandler.sendMessage(msg);
			}
		};

		Thread checkThread = new Thread(checkRunnable);
		checkThread.start();

	}

	/**
	 * get the sdk version. 获取SDK版本号
	 * 
	 */
	public void getSDKVersion() {
		PayTask payTask = new PayTask(this);
		String version = payTask.getVersion();
		Toast.makeText(this, version, Toast.LENGTH_SHORT).show();
	}

	/**
	 * create the order info. 创建订单信息
	 * 
	 */
	public String getOrderInfo(String subject, String body, String price) {

		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + PARTNER + "\"";

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

		// 商品名称
		orderInfo += "&subject=" + "\"" + subject + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + body + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + price + "\"";

		orderInfo += "&currency=" + "\"" + "JPY" + "\"";

		orderInfo += "&forex_biz=" + "\"" + "FP" + "\"";

		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";

		// 服务接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"30m\"";

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&show_url=\"m.alipay.com\"";

		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}

	/**
	 * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
	 * 
	 */
	public String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss",
				Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);

		Random r = new Random();
		key = key + r.nextInt();
		key = key.substring(0, 15);
		Log.e("dddd", key);
		return key;
	}

	/**
	 * sign the order info. 对订单信息进行签名
	 * 
	 * @param content
	 *            待签名订单信息
	 */
	public String sign(String content) {
		return SignUtils.sign(content, RSA_PRIVATE);
	}

	/**
	 * get the sign type we use. 获取签名方式
	 * 
	 */
	public String getSignType() {
		return "sign_type=\"RSA\"";
	}

}
