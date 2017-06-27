package com.example.administrator.koudaiwanzi.person.register;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.meatball.BackBean;
import com.example.administrator.koudaiwanzi.person.login.ReviseActivity;
import com.example.administrator.koudaiwanzi.person.login.ReviseBean;
import com.example.administrator.koudaiwanzi.person.login.country.CharacterParserUtil;
import com.example.administrator.koudaiwanzi.person.login.country.CountryActivity;
import com.example.administrator.koudaiwanzi.person.login.country.CountrySortModel;
import com.example.administrator.koudaiwanzi.person.login.country.GetCountryNameSort;
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

import static com.example.administrator.koudaiwanzi.R.drawable.shape_regster_gray;

@ContentView(R.layout.activity_register_new)
public class RegisterActivity extends AppCompatActivity {
    private Handler mHandler;
    private TimeCount time;
    private RelativeLayout relative_choseCountry;

    private EditText editText_countryNum;

    private TextView tv_countryName;

    private GetCountryNameSort countryChangeUtil;

    private CharacterParserUtil characterParserUtil;

    private List<CountrySortModel> mAllCountryList;

    String beforText = null;


    //绑定获取注册密码的EditText
    @ViewInject(R.id.register_edit_pwd)
    private EditText password;

    //确认密码的id
    @ViewInject(R.id.secondPassword)
    private EditText secondPassword;


    @ViewInject(R.id.register_btn_register)
    private Button register;

    //手机号
    @ViewInject(R.id.register_edit_number)
    private EditText ed_num;

    //验证码
    @ViewInject(R.id.register_edit_yzm)
    private EditText ed_yzm;


    // 创建等待框
    private ProgressDialog dialog;
    // 返回的数据
    private String info;

    // 返回主线程更新数据
    private static Handler handler = new Handler();

    //注册协议
    @ViewInject(R.id.register_agreement_tv)
    private TextView register_agreement_tv;

    //是否选中注册协议
    @ViewInject(R.id.register_checkbox)
    private CheckBox register_checkbox;

    //标识符 用于判断是否选择了注册协议
    private Boolean isCheck = false;

    @ViewInject(R.id.register_btn_code)
    private Button btn_register;

    //直接登录
//    @ViewInject(R.id.register_login_tv)
//    private TextView register_login_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //xutiles3初始化
        x.view().inject(this);
        initView();
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

    // 子线程接收数据，主线程修改数据
    private class MyThread implements Runnable {
        @Override
        public void run() {
            SharedPreferences share = RegisterActivity.this.getSharedPreferences("logIn", Context.MODE_PRIVATE);
//            info = RegisterService.executeHttpGet(ed_num.getText().toString(), password.getText().toString());

            String url = MyUrl.url + "Users.svc/register/" + ed_num.getText().toString() + "/" + password.getText().toString();
            RequestParams params = new RequestParams(url);
            Log.e("ghbvn", url);
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String s) {
                    Gson gson = new Gson();

                    ReviseBean bean = gson.fromJson(s, ReviseBean.class);

                    if (bean.getMsg() == 1){
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                            }
                        });
                        finish();

                    }else if (bean.getMsg() == 0){
                        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                            }
                        });


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
    }

    // 检测网络
    private boolean checkNetwork() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }

    @Event(R.id.register_btn_register)
    private void registerClick(View view) {
        //判断用户是否点击了注册协议
        if (isCheck == true) {

            boolean i = isUserNameAndPwdValid();
            SharedPreferences share = RegisterActivity.this.getSharedPreferences("Yzm", Context.MODE_PRIVATE);
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
        } else {
            Toast.makeText(RegisterActivity.this, "请选择同意注册协议", Toast.LENGTH_SHORT).show();
        }

    }

    //获取验证码
    @Event(R.id.register_btn_code)
    private void codeClick(View view) {
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象


        if (isPhone(ed_num.getText().toString()) == false){
            Toast.makeText(RegisterActivity.this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
        }else {

            if (!ed_num.getText().toString().equals("") && !password.getText().toString().equals("") && !secondPassword.getText().toString().equals("")) {
                Toast.makeText(RegisterActivity.this, "正在获取验证码请稍后", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(RegisterActivity.this, "正在获取验证码请稍后", Toast.LENGTH_SHORT).show();
            int yzm = (int) (Math.random() * (9999 - 1000 + 1)) + 1000;
            String number = ed_num.getText().toString();
            time.start();

            /**
             * 验证码新
             */


             String   yzmUrl1 = MyUrl.url+"Users.svc/identifycode/"+number+"/0";

            RequestParams params = new RequestParams(yzmUrl1);
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String s) {
                    Gson gson = new Gson();
                    BackBean bean = gson.fromJson(s,BackBean.class);
                    if (bean.getMsg() == 1){
                        Toast.makeText(RegisterActivity.this, "验证码已发送请查收", Toast.LENGTH_SHORT).show();
                        SharedPreferences share = RegisterActivity.this.getSharedPreferences("Yzm", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = share.edit();
                        editor.putString("yzm", bean.getDetailUrl());
                        editor.apply();
                    }else {
                        Toast.makeText(RegisterActivity.this, "请求验证码失败", Toast.LENGTH_SHORT).show();
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

//            String yzmUrl = "http://222.73.117.138:7891/mt?un=N4974948&pw=c5e8d960&da=" + number + "&sm=【口袋丸子】您的注册验证码是:" + yzm + ".请完成注册&dc=15&rd=1&tf=3&rf=2";
//            SharedPreferences share = RegisterActivity.this.getSharedPreferences("Yzm", Context.MODE_PRIVATE);
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


    private void initView() {
        relative_choseCountry = (RelativeLayout) findViewById(R.id.rala_chose_country);
        editText_countryNum = (EditText) findViewById(R.id.edt_chosed_country_num);
        tv_countryName = (TextView) findViewById(R.id.tv_chosed_country);

        mAllCountryList = new ArrayList<CountrySortModel>();
        countryChangeUtil = new GetCountryNameSort();
        characterParserUtil = new CharacterParserUtil();

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
                intent.setClass(RegisterActivity.this, CountryActivity.class);
                startActivityForResult(intent, 12);
            }
        });

//        editText_countryNum.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                beforText = s.toString();
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                String contentString = editText_countryNum.getText().toString();
//
//                CharSequence contentSeq = editText_countryNum.getText();
//
//                if (contentString.length() > 1) {
//                    // �����������ݽ���ƥ��
//                    List<CountrySortModel> fileterList = (ArrayList<CountrySortModel>) countryChangeUtil
//                            .search(contentString, mAllCountryList);
//
//                    if (fileterList.size() == 1) {
//                        tv_countryName.setText(fileterList.get(0).countryName);
//                    } else {
//                        tv_countryName.setText("输入区号有误");
//                    }
//
//                } else {
//                    if (contentString.length() == 0) {
//                        editText_countryNum.setText(beforText);
//                        tv_countryName.setText("请输入区号");
//                    } else if (contentString.length() == 1 && contentString.equals("+")) {
//                        tv_countryName.setText("输入区号有误");
//                    }
//
//                }
//
//                if (contentSeq instanceof Spannable) {
//                    Spannable spannable = (Spannable) contentSeq;
//                    Selection.setSelection(spannable, contentSeq.length());
//                }
//            }
//        });

        //点击注册协议 跳转到协议页面
        register_agreement_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, RegisterAgreementActivity.class);
                startActivity(intent);
            }
        });

        //checkBox的监听
        register_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    isCheck = true;
                } else {
                    isCheck = false;
                }

            }
        });

        //直接登录的监听
//        register_login_tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

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

                }
                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    //返回键监听
    @Event(R.id.iv_back_register)
    private void ivClick(View veiw){
        finish();
    }

    public static boolean isPhone(String inputText) {
        Pattern p = Pattern.compile("^((14[0-9])|(13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(inputText);
        return m.matches();
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


