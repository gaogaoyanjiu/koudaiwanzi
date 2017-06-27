package com.example.administrator.koudaiwanzi.person.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.MainActivity;
import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.classification.type.screen.ClassActivity;
import com.example.administrator.koudaiwanzi.details.DetailsActivity;
import com.example.administrator.koudaiwanzi.person.login.country.CharacterParserUtil;
import com.example.administrator.koudaiwanzi.person.login.country.CountryActivity;
import com.example.administrator.koudaiwanzi.person.login.country.CountrySortModel;
import com.example.administrator.koudaiwanzi.person.login.country.GetCountryNameSort;
import com.example.administrator.koudaiwanzi.person.register.RegisterActivity;
import com.example.administrator.koudaiwanzi.person.set.AccountActivity;
import com.example.administrator.koudaiwanzi.product.ShowProductActivity;
import com.example.administrator.koudaiwanzi.selling.SellActivity;
import com.google.gson.Gson;

import org.w3c.dom.Text;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/15.
 * 登录界面
 */

@ContentView(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {
    //手机号
    @ViewInject(R.id.et_usertel)
    private EditText username;

    //输入密码
    @ViewInject(R.id.et_password)
    private EditText password;

    @ViewInject(R.id.tv_wenti)
    private TextView tv_question;

    private String info;
    private AlertDialog.Builder dialog;
    private static Handler handler = new Handler();
    private int value;
    private String url;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

    }

    //登录按钮
    @Event(R.id.login_btn_login)
    private void login(View view) {
        Log.e("bnzwggag", "登录");

        if (isNetworkAvailable(LoginActivity.this)) {
            boolean i = isUserNameAndPwdValid();
            if (i == true) {
                dialog = new AlertDialog.Builder(LoginActivity.this);
//                dialog.setTitle("提示");
//                dialog.setMessage("正在登录，请稍后...");
//                dialog.setCancelable(false);
                // 创建子线程，分别进行Get和Post传输
                new Thread(new MyThread()).start();
            } else {
                Toast.makeText(this, "登录失败",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "当前没有可用网络！", Toast.LENGTH_LONG).show();
        }


    }


    private class MyThread implements Runnable {
        @Override
        public void run() {
            info = LoginService.executeHttpGet(username.getText().toString(), password.getText().toString());
            Gson gson = new Gson();
            LoginBean bean = gson.fromJson(info, LoginBean.class);
            final String show = bean.getUID();
            final int login = bean.getMsg();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (login == 1) {
                        SharedPreferences share = LoginActivity.this.getSharedPreferences("logIn", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = share.edit();
                        editor.putString("login", show);
                        editor.apply();
                        value = getIntent().getIntExtra("me", 0);
                        if (value == 2) {
                            if (show != "") {
                                //从主页进来
                                Intent intent = new Intent();
                                intent.putExtra("our", 4);
                                setResult(5, intent);

                                finish();
                            } else {
                                Intent intent = new Intent();
                                intent.putExtra("our", 1);
                                setResult(5, intent);

                                finish();
                            }

                        } else{
                            //从收藏或加入购物车进来
                            finish();
                        }

                    } else {
                        dialog.setTitle("");
                        dialog.setMessage("用户名或密码错误");
                        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                //从主页进来
                                Intent intent = new Intent();
                                intent.putExtra("our", 6);
                                setResult(5, intent);
                                finish();
                            }
                        });
                        dialog.show();
                    }

                }
            });
        }
    }

    public boolean isUserNameAndPwdValid() {
        if (username.getText().toString().trim().equals("")) {
            Toast.makeText(this, "用户名不能为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.getText().toString().trim().equals("")) {
            Toast.makeText(this, "密码不能为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //跳转到忘记密码
    @Event(R.id.tv_wenti)
    private void textClick2(View view){
        Intent intent = new Intent(LoginActivity.this, ReviseActivity.class);
        startActivity(intent);

    }

    //跳转到注册界面
    @Event(R.id.tv_register_login)
    private void textClick(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }


//    //返回键的监听
    @Event(R.id.btn_back_login)
    private void backClick(View view) {
        value = getIntent().getIntExtra("me", 0);
        if (value == 2) {
            Intent intent = new Intent();
            intent.putExtra("our", 0);
            setResult(5, intent);
            finish();
        }
//        else if (value == 1) {
//            //从收藏或加入购物车进来
//            url = getIntent().getStringExtra("fanhui");
//            Intent intent = new Intent(LoginActivity.this, DetailsActivity.class);
//            intent.putExtra("key", url);
//            startActivity(intent);
//            finish();
//        }
        else {
//            Intent intent2 = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent2);
            finish();
        }

    }

//    //监听手机自带的返回键
    @Override
    public void onBackPressed() {
        value = getIntent().getIntExtra("me", 0);
        if (value == 2) {
            Intent intent = new Intent();
            intent.putExtra("our", 0);
            setResult(5, intent);
            finish();
        }

//        else if (value == 1) {
//            //从收藏或加入购物车进来
//            url = getIntent().getStringExtra("fanhui");
//            Intent intent = new Intent(LoginActivity.this, DetailsActivity.class);
//            intent.putExtra("key", url);
//            startActivity(intent);
//            finish();
//        }

        else {
//            Intent intent2 = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent2);
            finish();
        }
        super.onBackPressed();
    }


    /**
     * 检查当前网络是否可用
     *
     * @param
     * @return
     */

    public boolean isNetworkAvailable(Activity activity) {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    System.out.println(i + "===状态===" + networkInfo[i].getState());
                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void sendBroadcast() {
        Intent intent1 = new Intent("com.example.administrator.myapplication.main.main.person.broadcast");
        LocalBroadcastManager.getInstance(LoginActivity.this).sendBroadcast(intent1);
    }
}