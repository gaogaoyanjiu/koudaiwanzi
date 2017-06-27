package com.example.administrator.koudaiwanzi.ems;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/09/07.
 */

@ContentView(R.layout.activity_ems)
public class EMSActivity extends AppCompatActivity {
    private JSONObject param, param1;
    private String paramJsonString, ppp, pp;
    private String sign;
    private EMSAdapter adapter;
    private EMSOtherAdapter adapter1;
    private String url;
    private String myUrl = MyUrl.url;

    //物流编号
    @ViewInject(R.id.tv_num_order)
    private TextView tv_order_num;

    //物流编号
    @ViewInject(R.id.tv_wuLiu_detail)
    private TextView tv_detail;

    @ViewInject(R.id.lv_ems)
    private ListView listView;

    @ViewInject(R.id.sl_ems)
    private ScrollView sl;
    @ViewInject(R.id.rl_ems)
    private RelativeLayout rl_ems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        String url_ems = getIntent().getStringExtra("ems");
        url = myUrl + url_ems;
        Log.d("paramxasqwe",  url);
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                EMSBean bean = gson.fromJson(s, EMSBean.class);

                if (bean.getDELINUM() == null || bean.getDELINUM().equals("")){
                    sl.setVisibility(View.GONE);
                    rl_ems.setVisibility(View.VISIBLE);
                }else {
                    if (bean.getTYPE() ==3)
                    {
                        sl.setVisibility(View.VISIBLE);
                        rl_ems.setVisibility(View.GONE);
                        tv_detail.setText(bean.getResponse().getExpressRoute().getExpressName());

                        tv_order_num.setText(bean.getResponse().getExpressRoute().getExpressNo());
                        Log.e("jjdjdj", s);
                        Log.e("dzcv",  bean.getResponse().getExpressRoute().getExpressRouteItemList().getExpressRouteItems().size()+"12314");
                        adapter1 = new EMSOtherAdapter(EMSActivity.this, bean.getResponse().getExpressRoute().getExpressRouteItemList().getExpressRouteItems(), R.layout.item_ems);
                        listView.setAdapter(adapter1);
                    }else {
                        sl.setVisibility(View.VISIBLE);
                        rl_ems.setVisibility(View.GONE);
                        tv_detail.setText(bean.getCom());

                        tv_order_num.setText(bean.getDELINUM());
                        Log.e("jjdjdj", s);
                        Log.e("dzcv", bean.getData().size()+"");
                        adapter = new EMSAdapter(EMSActivity.this, bean.getData(), R.layout.item_ems);
                        listView.setAdapter(adapter);
                    }


                }



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
    @Event(R.id.iv_back_wu)
    private void ivClick(View view){
        finish();
    }


}
