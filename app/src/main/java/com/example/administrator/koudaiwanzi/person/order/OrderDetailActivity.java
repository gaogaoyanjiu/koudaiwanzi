package com.example.administrator.koudaiwanzi.person.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.ems.EMSActivity;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/30.
 */

@ContentView(R.layout.activity_order_detail)
public class OrderDetailActivity extends AppCompatActivity{
    private String url;
    private String myUrl = MyUrl.url;
    private OrderDetailAdapter adapter;

    @ViewInject(R.id.myListView_order)
    private ListView listView;

    @ViewInject(R.id.tv_order_detail_num)
    private TextView holder1;

    @ViewInject(R.id.tv_order_detail_type)
    private TextView holder2;

    @ViewInject(R.id.tv_from)
    private TextView holder3;

    @ViewInject(R.id.tv_to)
    private TextView holder4;

    @ViewInject(R.id.tv_order_detail_post)
    private TextView holder5;

    @ViewInject(R.id.tv_order_detail_phone)
    private TextView holder6;

    @ViewInject(R.id.tv_order_detail_price)
    private TextView holder7;

    @ViewInject(R.id.tv_order_detail_time)
    private TextView holder8;

    @ViewInject(R.id.rl_ems_order)
    private RelativeLayout rl_ems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        String url_order = getIntent().getStringExtra("orderDetail");
        url = myUrl + url_order;
        Log.e("qwzcv", url);
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Log.d("lollol", "123");
                Gson gson = new Gson();
                final OrderDetailBean bean = gson.fromJson(s, OrderDetailBean.class);

                Log.d("lollol", String.valueOf(bean));
                //跳转到物流界面
                rl_ems.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(OrderDetailActivity.this, EMSActivity.class);
                        intent.putExtra("ems", bean.getINFORMATION());
                        startActivity(intent);
                    }
                });

                adapter = new OrderDetailAdapter(OrderDetailActivity.this, bean.getORDERITEMS(), R.layout.item_order_detail);
                listView.setAdapter(adapter);

                //订单编号
                holder1.setText("订单编号：" + bean.getBONUM()+"");
                //转态（待付款）
                holder2.setText(bean.getSTATUSNAME());
                //发货地址
                holder3.setText(bean.getCONSIGNEE());
                //收货地址
                holder4.setText(bean.getFULLADRESS());
                //运费
                holder5.setText(bean.getPOSTAGE()+"");
                //电话
                holder6.setText(bean.getTEL()+"");
                //实付款
                double a = bean.getPOSTAGE() + bean.getPRICE();
                holder7.setText(a + "");
                //下单时间
                String result = formatData("yyyy/MM/dd", bean.getCREATETIME());
                holder8.setText("下单时间：" + result);


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

    //返回键监听
    @Event(R.id.iv_back_item_order)
    private void ivClick(View view){
        finish();
    }

    public static String formatData(String dataFormat, long timeStamp) {
        if (timeStamp == 0) {
            return "";
        }
        timeStamp = timeStamp * 1000;
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        result = format.format(new Date(timeStamp));
        return result;
    }

}
