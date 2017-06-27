package com.example.administrator.koudaiwanzi.person.address;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.example.administrator.koudaiwanzi.dialog.CustomProgressDialog;
import com.example.administrator.koudaiwanzi.person.PersonBean;
import com.example.administrator.koudaiwanzi.person.address.chooseAddress.activity.ChooseAddressActivity;
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
 * Created by Administrator on 2016/6/28.
 */
@ContentView(R.layout.my_set_buyaddress)
public class NewAddressActivity extends AppCompatActivity {
    private String url2 = MyUrl.url;
    private String url3 = "Users.svc/newaddress/";

    private String url1;
    private String UID;
    private RequestQueue queue;

    @ViewInject(R.id.et_address_place)
    private TextView et_place;

    @ViewInject(R.id.et_address_name)
    private EditText et_name;

    @ViewInject(R.id.et_address_number)
    private EditText et_number;

    @ViewInject(R.id.et_address_code)
    private EditText et_code;

    @ViewInject(R.id.et_address_street)
    private EditText et_street;


    @ViewInject(R.id.rl_choose_address)
    private RelativeLayout rl_choose;
    //保存收货地址
    @ViewInject(R.id.btn_address_post)
    private Button btn_post;

    private Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        url1 = url2 + url3;
        SharedPreferences share = this.getSharedPreferences("logIn", Context.MODE_PRIVATE);
        UID = share.getString("login", "");
        SharedPreferences share1 = this.getSharedPreferences("Address", Context.MODE_PRIVATE);
        String name1 = share1.getString("AddressName", "");
        String num1 = share1.getString("AddressNum", "");
        String place1 = share1.getString("AddressStreet", "");
        Intent intent = getIntent();
        String province = intent.getStringExtra("province");
        String city = intent.getStringExtra("city");
        String area = intent.getStringExtra("area");
        String code = intent.getStringExtra("code");
        String all = province + city + area + "";
        if (province == null) {
            et_place.setText("");
        } else {
            et_place.setText(all);
        }
        if (name1 == null) {
            et_name.setText("");
        } else {
            et_name.setText(name1);
        }
        if (num1 == null) {
            et_number.setText("");
        } else {
            et_number.setText(num1);
        }
        if (place1 == null) {
            et_street.setText("");
        } else {
            et_street.setText(place1);
        }
        et_code.setText(code);


        //绑定跳转到选择地址页面的ID
        rl_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String num = et_number.getText().toString();
                String place = et_street.getText().toString();
                SharedPreferences share = NewAddressActivity.this.getSharedPreferences("Address", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = share.edit();
                editor.putString("AddressName", name);
                editor.putString("AddressNum", num);
                editor.putString("AddressStreet", place);
                editor.apply();
                Intent intent = new Intent(NewAddressActivity.this, ChooseAddressActivity.class);
                startActivity(intent);
                finish();

            }
        });

        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CustomProgressDialog dialog = new CustomProgressDialog(NewAddressActivity.this, R.style.CommProgressDialog, "加载中...", R.anim.frame2);
                dialog.setProgressStyle(R.style.CommProgressDialog);
                dialog.show();
                String name = et_name.getText().toString();
                String number = et_number.getText().toString();
                String street = et_street.getText().toString();

                Intent intent = getIntent();
                String province = intent.getStringExtra("province");
                String city = intent.getStringExtra("city");
                String area = intent.getStringExtra("area");
                String code = intent.getStringExtra("code");
                String url = url1 + UID + "/" + name + "/" + number + "/" + province + "/" + city + "/" + area + "/" + street + "/" + code;


                OkHttpClient ok = new OkHttpClient();
                final Request request2 = new Request.Builder().url(url).build();
                Call call = ok.newCall(request2);
                call.enqueue(new okhttp3.Callback() {
                    public void onFailure(Call call, IOException e) {

                    }
                    public void onResponse(Call call, okhttp3.Response response) throws IOException {
                        String url1 = url2 + "Users.svc/useritem/" + UID;
                        queue = Volley.newRequestQueue(NewAddressActivity.this);
                        Log.e("dxghgr", url1);
                        StringRequest request = new StringRequest(url1, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response){
                                dialog.cancel();
                                Gson gson = new Gson();
                                final PersonBean p = gson.fromJson(response, PersonBean.class);
                                Intent intent = new Intent(NewAddressActivity.this, AddressActivity.class);
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


    @Event(R.id.iv_addAddress_back)
    private void backClick(View view) {
        Intent intent = new Intent(NewAddressActivity.this, AddressActivity.class);
        startActivity(intent);
        finish();

    }


}

