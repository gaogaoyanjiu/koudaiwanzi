package com.example.administrator.koudaiwanzi.person.address;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.person.PersonBean;
import com.example.administrator.koudaiwanzi.person.address.chooseAddress.activity.ChooseAddress1Activity;
import com.google.gson.Gson;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * Created by Administrator on 2016/6/29.
 */
@ContentView(R.layout.activity_change_address)
public class ChangeAddressActivity extends AppCompatActivity {
    private String url1 = MyUrl.url;
    private RequestQueue queue,queue1;

    @ViewInject(R.id.tv_change_province)
    private TextView tv_province;

    @ViewInject(R.id.tv_change_city)
    private TextView tv_city;

    @ViewInject(R.id.tv_change_area)
    private TextView tv_area;


    @ViewInject(R.id.et_change_name)
    private EditText et_name;

    @ViewInject(R.id.et_change_number)
    private EditText et_number;

    @ViewInject(R.id.et_change_code)
    private EditText et_code;

    @ViewInject(R.id.et_change_street)
    private EditText et_street;


    @ViewInject(R.id.rl_choose_change)
    private RelativeLayout rl_choose;


    @ViewInject(R.id.btn_change_address)
    private Button btn_post;

    @ViewInject(R.id.tv_change_place)
    private TextView tv_place;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        SharedPreferences share = this.getSharedPreferences("logIn", Context.MODE_PRIVATE);
        final String UID = share.getString("login", "");

        SharedPreferences share1 = this.getSharedPreferences("ChangeAddress", Context.MODE_PRIVATE);
        final String changeUrl = share1.getString("changeAddress", "");

        btn_post = (Button) findViewById(R.id.btn_change_address);
        final Intent intent = getIntent();

        queue1 = Volley.newRequestQueue(ChangeAddressActivity.this);
          String C = "!";
          StringRequest request1 = new StringRequest(C, new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {

              }
          }, new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {

              }
          });
        queue1.add(request1);






        String url = url1 + changeUrl;
        queue = Volley.newRequestQueue(ChangeAddressActivity.this);
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                final ChangeAddressBean p = gson.fromJson(response, ChangeAddressBean.class);
                et_name.setText(p.getCONSIGNEE() + "");
                et_number.setText(p.getTEL() + "");

                String province = intent.getStringExtra("province");
                String city = intent.getStringExtra("city");
                String area = intent.getStringExtra("area");

                if(province == null || city == null|| area == null )
                {
                    tv_province.setText(p.getPROVINCE());
                    tv_city.setText(p.getCITY());
                    tv_area.setText(p.getAREA());
                }
                else {
                    tv_province.setText(province);
                    tv_city.setText(city);
                    tv_area.setText(area);
                }




                et_code.setText(p.getZIPCODE() + "");
                et_street.setText(p.getFULLADRESS() + "");
                final String toPost = url1 + p.getDetailUrl();

                rl_choose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ChangeAddressActivity.this, ChooseAddress1Activity.class);
                        startActivity(intent);

                    }
                });

                btn_post.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = et_name.getText().toString();
                        String num = et_number.getText().toString();
                        String code = et_code.getText().toString();
                        String street = et_street.getText().toString();
                        String province = tv_province.getText().toString();
                        String city = tv_city.getText().toString();
                        String area = tv_area.getText().toString();
                        String post = toPost + name + "/" + num + "/" + province + "/" + city + "/" + area + "/" + street + "/" + code;
                        OkHttpClient ok = new OkHttpClient();
                        final Request request2 = new Request.Builder().url(post).build();
                        Call call = ok.newCall(request2);
                        call.enqueue(new okhttp3.Callback() {
                            public void onFailure(Call call, IOException e) {
                            }
                            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                                String  url = url1+"Users.svc/useritem/"+ UID;
                                queue = Volley.newRequestQueue(ChangeAddressActivity.this);
                                StringRequest request = new StringRequest(url, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Gson gson = new Gson();
                                        final PersonBean p = gson.fromJson(response, PersonBean.class);
                                        Intent intent = new Intent(ChangeAddressActivity.this,AddressActivity.class);
                                        intent.putExtra("address", p.getADRESSMANAGEURL());
                                        startActivity(intent);
                                        finish();

                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                });
                                queue.add(request);

                            }
                        });
                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }
    @Event(R.id.iv_changeAddress_back)
    private void backClick(View view){

        Intent intent = new Intent(ChangeAddressActivity.this,AddressActivity.class);
        startActivity(intent);
        finish();

    }
}


