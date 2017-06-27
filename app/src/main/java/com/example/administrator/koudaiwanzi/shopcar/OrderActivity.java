package com.example.administrator.koudaiwanzi.shopcar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.PayActivity;
import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.base.Post;
import com.example.administrator.koudaiwanzi.dialog.CustomProgressDialog;
import com.example.administrator.koudaiwanzi.person.address.AddressActivity;
import com.example.administrator.koudaiwanzi.person.coupon.CouponNewActivity;
import com.example.administrator.koudaiwanzi.person.set.AccountActivity;
import com.example.administrator.koudaiwanzi.wxapi.WXEntryActivity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016/5/31.
 * 提交订单界面
 */
@ContentView(R.layout.activity_firm_order)
public class OrderActivity extends AppCompatActivity {
    private String goUrl;
    private String url1 = MyUrl.url;
    private String url;
    private int getPoint;
    private String price;
    private String message;
    private String sr;
    //凑单页面
    @ViewInject(R.id.goto_together)
    private TextView tv_together;
    //    private void togetherClick(View view) {
//        Intent intent = new Intent(OrderActivity.this, MergeActivity.class);
//        intent.putExtra("merge", String.valueOf(10));
//        startActivity(intent);
//        finish();
//    }
    //包邮
    @ViewInject(R.id.tv_baoYou)
    private TextView tv_baoYou;
    //商品数量
    @ViewInject(R.id.firm_order_count_price)
    private TextView tv_product_nums;
    //优惠券
    @ViewInject(R.id.rl_order_quan)
    private RelativeLayout rl_ticket;

    //绑定买家姓名id
    @ViewInject(R.id.firm_order_name)
    private TextView tv_name;
    //绑定买家电话
    @ViewInject(R.id.firm_order_number)
    private TextView tv_num;
    //收货地址
    @ViewInject(R.id.firm_order_place)
    private TextView tv_place;

    @ViewInject(R.id.firm_order_total_price)
    private TextView tv_price;

    @ViewInject(R.id.ll_order_product)
    private LinearLayout ll_order;
    //实付款
    @ViewInject(R.id.pay_price)
    private TextView tv_pay;

    @ViewInject(R.id.tv_order_coupon)
    private TextView tv_coupon;

    @ViewInject(R.id.tv_order_score)
    private TextView tv_score;

    @ViewInject(R.id.firm_order_recyclerview_count)
    private TextView tv_count;

    @ViewInject(R.id.tv_detail_discount)
    private TextView tv_discount;

    @ViewInject(R.id.tv_order_post)
    private TextView tv_post;
//
//    @ViewInject(R.id.tv_order_money)
//    private TextView tv_money;

    //提交订单
    @ViewInject(R.id.commit_order)
    private TextView tv_commit;

    @ViewInject(R.id.et_firm_message)
    private EditText et_message;

    @ViewInject(R.id.firm_order_checkbox)
    private CheckBox ck_order_point;

    @ViewInject(R.id.tv_order_none)
    private TextView tv_none;

    private String strGBK;
    private String province;
    private String area;
    private String address;
    private String city;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        final CustomProgressDialog dialog = new CustomProgressDialog(OrderActivity.this, R.style.CommProgressDialog, "加载中...", R.anim.frame2);
        dialog.setProgressStyle(R.style.CommProgressDialog);
        dialog.show();
//        SharedPreferences share = OrderActivity.this.getSharedPreferences("Order", Context.MODE_PRIVATE);
//        String order = share.getString("order", "");
        SharedPreferences share = this.getSharedPreferences("logIn", Context.MODE_PRIVATE);
        String UID = share.getString("login", "");
        url = url1 + "Users.svc//accounts//" + UID;
        Log.e("gsad", url);
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                final OrderBean bean = gson.fromJson(s, OrderBean.class);
                dialog.cancel();


                    rl_ticket.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(bean.getCOUPONNUM() == 0){
                                Toast.makeText(OrderActivity.this, "您无可用优惠券", Toast.LENGTH_SHORT).show();
                            }else {
                                Intent intent = new Intent(OrderActivity.this, CouponNewActivity.class);
                                intent.putExtra("COUPONURL", bean.getCOUPONURL());
                                startActivity(intent);
                                finish();
                            }

                        }
                    });



                tv_together.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(OrderActivity.this, MergeActivity.class);
                        intent.putExtra("merge", bean.getJoinSingle() + bean.getTOTAL());
                        intent.putExtra("type", "2");
                        startActivity(intent);
                        finish();
                    }
                });


                tv_product_nums.setText(bean.getQUANTITY() + "件商品价格");
                if(bean.getADDRESS().getCONSIGNEE()!= null){
                    tv_name.setText(bean.getADDRESS().getCONSIGNEE() + "");
                }else {
                    tv_name.setText("");
                }


                tv_num.setText(bean.getADDRESS().getTEL());
                if (bean.getADDRESS().getPROVINCE() != null) {
                    province = bean.getADDRESS().getPROVINCE();
                } else {
                    province = "";
                }
                if (bean.getADDRESS().getCITY() != null) {
                    city = bean.getADDRESS().getCITY();
                } else {
                    city = "";
                }
                if (bean.getADDRESS().getAREA() != null) {
                    area = bean.getADDRESS().getAREA();
                } else {
                    area = "";
                }
                if (bean.getADDRESS().getFULLADRESS() != null) {
                    address = bean.getADDRESS().getFULLADRESS();
                } else {

                    address = "";
                }

                final String place = province + city + area + address;
                tv_place.setText(place);
                tv_price.setText(bean.getTOTAL() + "");


                //是否包邮
                if (bean.getTOTAL() >= 298) {
                    tv_baoYou.setText("已包邮");
                } else {
                    double q = 298 - bean.getTOTAL();
                    tv_baoYou.setText("还差" + q + "元包邮");
                }


                int size = bean.getITEMS().size();
                for (int i = 0; i < size; i++) {
                    View view = LayoutInflater.from(OrderActivity.this).inflate(R.layout.item_order_product, null);
                    ImageView ima = (ImageView) view.findViewById(R.id.iv_order_product);
                    x.image().bind(ima, url1 + bean.getITEMS().get(i).getICOURL());
                    ll_order.addView(view);
                }
                int point = bean.getPOINT();
                if (point < 1000) {
                    tv_score.setVisibility(View.GONE);
                    ck_order_point.setVisibility(View.GONE);
                    tv_none.setVisibility(View.VISIBLE);
                } else {
                    tv_score.setVisibility(View.VISIBLE);
                    ck_order_point.setVisibility(View.VISIBLE);
                    tv_none.setVisibility(View.GONE);
                }

                price = bean.getPRICE() + "";
                tv_pay.setText(bean.getPRICE() + "");
                tv_coupon.setText(bean.getCOUPONNUM() + "张可用");
                tv_score.setText("可用" + bean.getPOINT() + "积分" + "抵¥" + bean.getPOINT() / 100);
                tv_count.setText("共" + bean.getQUANTITY() + "件");
                tv_discount.setText("已优惠¥" + bean.getDISCOUNTED());
                tv_post.setText("+ ¥" + bean.getPOSTAGE());
//                tv_money.setText("+ ¥" + bean.getPOINT());

                //提交订单
                tv_commit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (place.equals("")) {
                            Toast.makeText(OrderActivity.this, "请输入收货地址", Toast.LENGTH_SHORT).show();
                        } else {

                            message = et_message.getText().toString();
                            String url2 = bean.getDetailUrl();

                            if (ck_order_point.isChecked()) {
                                getPoint = 1;
                            } else {
                                getPoint = 0;
                            }

//                            try {
//                                strGBK = URLEncoder.encode(message, "GBK");
//                            } catch (UnsupportedEncodingException e) {
//                                e.printStackTrace();
//                            }


                            goUrl = url1 + url2 + "/" + getPoint;





                            Log.e("gsadaaa", goUrl);
                            new MyThread().start();


                        }

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

    //返回按键
    @Event(R.id.firm_order_back)
    private void imagClick(View view) {
        finish();
    }

    //更换地址
    @Event(R.id.rl_order_address)
    private void addressClick(View view) {
//        SharedPreferences share = OrderActivity.this.getSharedPreferences("AddressBack", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = share.edit();
//        editor.putString("AddressBack", "1");
//        editor.apply();
        Intent intent = new Intent(OrderActivity.this, SelectAddressActivity.class);
        startActivity(intent);
        finish();

    }
    public class MyThread extends Thread {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            JSONObject object = new JSONObject();
            try {
                object.put("text", message);
                //  object.put("Cmd", "SPMSIPCheck");
                //  object.put("Dev", "app");
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            sr = Post.sendPost(
                    goUrl,
                    object);

            Log.d("ewqew1",  sr);
            handler.sendMessage(handler.obtainMessage(0, ""));
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0){
                Gson gson = new Gson();
                final BackBean bean = gson.fromJson(sr, BackBean.class);
                if (bean.getMsg() == 2) {
                    Toast.makeText(OrderActivity.this, "请您上传身份证信息", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(OrderActivity.this, WXEntryActivity.class);
                    intent.putExtra("detailUrl", bean.getDetailUrl() + "");
                    intent.putExtra("price", price);
                    startActivity(intent);
                    finish();
                }
            }
            super.handleMessage(msg);
        }
    };

}
