package com.example.administrator.koudaiwanzi.person.inviter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.MainActivity;
import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.meatball.BackBean;
import com.example.administrator.koudaiwanzi.person.order.OrderAllActivity;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/2.
 * 绑定邀请人界面
 */

@ContentView(R.layout.activity_bundling_inviter)
public class InviterActivity extends AppCompatActivity {

    @ViewInject(R.id.tv_inviter_finish)
    private TextView tv_finish;

    @ViewInject(R.id.input_invite_code_et)
    private EditText et_input;

    private String url1 = MyUrl.url;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        final Intent intent = getIntent();
        final String invite = intent.getStringExtra("invite");

        tv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = et_input.getText().toString();
                RequestParams params = new RequestParams(url1 + invite + "/" + input);
                Log.e("iropwk", url1 + invite + "/" + input );
                x.http().get(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Gson gson = new Gson();
                        BackBean bean = gson.fromJson(s, BackBean.class);
                        if (bean.getMsg() == 0) {
                            Toast.makeText(InviterActivity.this, "邀请码错误", Toast.LENGTH_SHORT).show();
                        } else if (bean.getMsg() == 1){
                            Toast.makeText(InviterActivity.this, "绑定邀请人成功", Toast.LENGTH_SHORT).show();
                        }else if (bean.getMsg() == 2){
                            Toast.makeText(InviterActivity.this, "邀请码已被使用过", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(InviterActivity.this, "不能填写自己的邀请码", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Throwable throwable, boolean b) {
                        Toast.makeText(InviterActivity.this, "请输入正确的邀请码", Toast.LENGTH_SHORT).show();
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

    //后退按钮
    @Event(R.id.back)
    private void rlClick(View view) {
        finish();

    }
}
