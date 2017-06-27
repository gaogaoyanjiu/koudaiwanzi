package com.example.administrator.koudaiwanzi.person.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.meatball.BackBean;
import com.example.administrator.koudaiwanzi.person.login.country.CharacterParserUtil;
import com.example.administrator.koudaiwanzi.person.login.country.CountryActivity;
import com.example.administrator.koudaiwanzi.person.login.country.CountrySortModel;
import com.example.administrator.koudaiwanzi.person.login.country.GetCountryNameSort;
import com.example.administrator.koudaiwanzi.person.register.RegisterService;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2016/9/23.
 * 修改密码界面
 */

@ContentView(R.layout.activity_revise)
public class ReviseActivity extends AppCompatActivity {
    //倒计时的显示
    @ViewInject(R.id.btn_s)
    private Button btn_s;
    //获取验证码的button
    @ViewInject(R.id.register_btn_code)
    private Button button;
    //手机号码的id
    @ViewInject(R.id.register_edit_number)
    private EditText ed_num;
    //输入密码的id
    @ViewInject(R.id.register_edit_pwd)
    private EditText password;
    //去人密码的id
    @ViewInject(R.id.secondPassword)
    private EditText secondPassword;
    //选择国家的id
    @ViewInject(R.id.rala_chose_country)
    private RelativeLayout relative_choseCountry;
    //显示国家的textView的id
    @ViewInject(R.id.tv_chosed_country)
    private TextView tv_countryName;
    //验证码
    @ViewInject(R.id.register_edit_yzm)
    private EditText ed_yzm;
    //倒计时的秒数
    private int recLen;
    private String country = "中国";
    // 创建等待框
    private ProgressDialog dialog;
    // 返回主线程更新数据
    private static Handler handler = new Handler();

    @ViewInject(R.id.register_btn_code)
    private Button btn_register;

    //选择城市
    private GetCountryNameSort countryChangeUtil;
    private CharacterParserUtil characterParserUtil;
    private List<CountrySortModel> mAllCountryList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        //选择城市初始化
        mAllCountryList = new ArrayList<CountrySortModel>();
        countryChangeUtil = new GetCountryNameSort();
        characterParserUtil = new CharacterParserUtil();

        initCountryList();
        setListener();


    }

    public boolean isUserNameAndPwdValid() {
        if (ed_num.getText().toString().trim().equals("")) {
            Toast.makeText(this, "用户名不能为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.getText().toString().trim().equals("")) {
            Toast.makeText(this, "密码不能为空",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (!secondPassword.getText().toString().trim().equals(password.getText().toString().trim())) {
            Toast.makeText(this, "您两次输入的密码不同",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (secondPassword.getText().toString().trim().equals("")) {
            Toast.makeText(this, "请再次确认密码",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    private void initCountryList() {
        String[] countryList = getResources().getStringArray(R.array.country_code_list_ch);

        for (int i = 0, length = countryList.length; i < length; i++) {
            String[] country = countryList[i].split("\\*");

            String countryName = country[0];
            String countryNumber = country[1];
            String countrySortKey = characterParserUtil.getSelling(countryName);
            CountrySortModel countrySortModel = new CountrySortModel(countryName, countryNumber,
                    countrySortKey);
            String sortLetter = countryChangeUtil.getSortLetterBySortKey(countrySortKey);
            if (sortLetter == null) {
                sortLetter = countryChangeUtil.getSortLetterBySortKey(countryName);
            }

            countrySortModel.sortLetters = sortLetter;
            mAllCountryList.add(countrySortModel);
        }

    }


    private void setListener() {
        relative_choseCountry.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ReviseActivity.this, CountryActivity.class);
                startActivityForResult(intent, 12);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        switch (requestCode) {
            case 12:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    String countryName = bundle.getString("countryName");
//                    String countryNumber = bundle.getString("countryNumber");

                    //            editText_countryNum.setText(countryNumber);
                    tv_countryName.setText(countryName);
                    country = countryName;
                }
                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    //获取验证码
    @Event(R.id.register_btn_code)
    private void codeClick(View view) {

        if (isPhone(ed_num.getText().toString()) == false){
            Toast.makeText(ReviseActivity.this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
        }else {
            recLen = 60;
            button.setVisibility(View.GONE);
            btn_s.setVisibility(View.VISIBLE);
            handler2.postDelayed(runnable, 1000);

            if (!ed_num.getText().toString().equals("") && !password.getText().toString().equals("") && !secondPassword.getText().toString().equals("")) {

            }

            int yzm = (int) (Math.random() * (9999 - 1000 + 1)) + 1000;
            String number = ed_num.getText().toString();
            /**
             * 验证码新
             */
            Toast.makeText(ReviseActivity.this, "正在获取验证码请稍后", Toast.LENGTH_SHORT).show();
            String yzmUrl1;
            if(country.equals("中国")){
                yzmUrl1 = MyUrl.url+"Users.svc/identifycode/"+number+"/0";
            }else {
                yzmUrl1 = MyUrl.url+"Users.svc/identifycode/"+number+"/1";
            }
            RequestParams params = new RequestParams(yzmUrl1);
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String s) {
                         Gson gson = new Gson();
                         BackBean  bean = gson.fromJson(s,BackBean.class);
                         if (bean.getMsg() == 1){
                             Toast.makeText(ReviseActivity.this, "验证码已发送请查收", Toast.LENGTH_SHORT).show();
                             SharedPreferences share = ReviseActivity.this.getSharedPreferences("Yzm", Context.MODE_PRIVATE);
                             SharedPreferences.Editor editor = share.edit();
                             editor.putString("yzm", bean.getDetailUrl());
                             editor.apply();
                         }else {
                             Toast.makeText(ReviseActivity.this, "请求验证码失败", Toast.LENGTH_SHORT).show();
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
            /**
             * 验证码旧
             */
//            String yzmUrl = "http://222.73.117.138:7891/mt?un=N4974948&pw=c5e8d960&da=" + number + "&sm=【口袋丸子】您的注册验证码是:" + yzm + ".请完成注册&dc=15&rd=1&tf=3&rf=2";
//            SharedPreferences share = ReviseActivity.this.getSharedPreferences("Yzm", Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = share.edit();
//            editor.putString("yzm", String.valueOf(yzm));
//            editor.apply();
//            OkHttpClient ok = new OkHttpClient();
//            final Request request2 = new Request.Builder().url(yzmUrl).build();
//            Call call = ok.newCall(request2);
//            call.enqueue(new okhttp3.Callback() {
//
//                public void onFailure(Call call, IOException e) {
//
//                }
//
//
//                public void onResponse(Call call, okhttp3.Response response) throws IOException {
//
//
//                }
//            });
        }
    }

    //点击确定
    @Event(R.id.register_btn_register)
    private void registerClick(View view) {
        //判断用户是否点击了注册协议
        boolean i = isUserNameAndPwdValid();
        SharedPreferences share = ReviseActivity.this.getSharedPreferences("Yzm", Context.MODE_PRIVATE);
        String yzm = share.getString("yzm", "");
        //验证码getYzm
        String getYzm = ed_yzm.getText().toString();

        if (i == true) {
            if (yzm.equals(getYzm)) {
                dialog = new ProgressDialog(this);
                dialog.setTitle("提示");
                dialog.setMessage("正在注册，请稍后...");
                dialog.setCancelable(false);
                dialog.show();
                // 创建子线程，分别进行Get和Post传输
                new Thread(new MyThread()).start();
            } else {
                Toast.makeText(this, "验证码错误",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "账号密码不能为空",
                    Toast.LENGTH_SHORT).show();
        }
    }

    // 子线程接收数据，主线程修改数据
    private class MyThread implements Runnable {
        @Override
        public void run() {
//            SharedPreferences share = RegisterActivity.this.getSharedPreferences("logIn", Context.MODE_PRIVATE);
//            RegisterService.executeHttpGet(ed_num.getText().toString(), password.getText().toString());
            String url = MyUrl.url + "/Users.svc/amend/" + ed_num.getText().toString() + "/" + password.getText().toString();
            RequestParams params = new RequestParams(url);
            Log.e("ghbvn", url);
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String s) {
                    Gson gson = new Gson();

                    ReviseBean bean = gson.fromJson(s, ReviseBean.class);

                    if (bean.getMsg() == 1) {
                        Toast.makeText(ReviseActivity.this, "修改密码成功", Toast.LENGTH_SHORT).show();
                    } else if (bean.getMsg() == 2) {
                        Toast.makeText(ReviseActivity.this, "密码重复", Toast.LENGTH_SHORT).show();
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


            handler.post(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                }
            });
            finish();
        }
    }


    Handler handler2 = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            if (recLen > 0) {
                recLen--;
                btn_s.setText(recLen + "s");
                handler2.postDelayed(this, 1000);
            } else {
                btn_s.setVisibility(View.GONE);
                button.setVisibility(View.VISIBLE);
            }
        }
    };

    public static boolean isPhone(String inputText) {
        Pattern p = Pattern.compile("^((14[0-9])|(13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(inputText);
        return m.matches();
    }

    @Event(R.id.iv_back_revise)
    private void imaClick(View view){
        finish();
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }
        @Override
        public void onFinish() {//计时完毕时触发
            btn_register.setText("重新验证");
            btn_register.setClickable(true);
            btn_register.setBackgroundResource(R.drawable.shape_regster_red);
        }
        @Override
        public void onTick(long millisUntilFinished){//计时过程显示
            btn_register.setClickable(false);
            btn_register.setText(millisUntilFinished /1000+"秒");
            btn_register.setBackgroundResource(R.drawable.shape_regster_gray);
        }
    }
}
