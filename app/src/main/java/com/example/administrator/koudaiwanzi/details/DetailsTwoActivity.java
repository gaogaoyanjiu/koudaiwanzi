package com.example.administrator.koudaiwanzi.details;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.details.detail.DetailBean;
import com.example.administrator.koudaiwanzi.details.detail.DetailFragment;
import com.example.administrator.koudaiwanzi.details.detail.DetailFragmentTwo;
import com.example.administrator.koudaiwanzi.details.evaluate.EvaluateFragment;
import com.example.administrator.koudaiwanzi.details.evaluate.EvaluateFragmentTwo;
import com.example.administrator.koudaiwanzi.details.other.OtherFragment;
import com.example.administrator.koudaiwanzi.details.other.OtherFragmentTwo;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.key.OnekeyShare;
import cn.sharesdk.key.ShareContentCustomizeCallback;

/**
 * Created by Administrator on 2016/11/17.
 */

@ContentView(R.layout.activity_detail_three)
public class DetailsTwoActivity extends AppCompatActivity{
    private String info,url;
    private String url1 = MyUrl.url;
    private ArrayList<Fragment> data;
    private DetailsAdapter adapter;

    //绑定tabLayout的id
    @ViewInject(R.id.tab_detail)
    private TabLayout tabLayout;

    //绑定viewPager的id
    @ViewInject(R.id.vp_detail)
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        ShareSDK.initSDK(this);
        data = new ArrayList<>();
        data.add(new DetailFragmentTwo());
        data.add(new EvaluateFragmentTwo());
        data.add(new OtherFragmentTwo());

        adapter = new DetailsAdapter(getSupportFragmentManager(), data, this);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    //分享键监听
    @Event(R.id.iv_share)
    private void ima2Click(View view) {
        showShare();
    }

    //监听返回键
    @Event(R.id.iv_detail_back)
    private void imaClick(View view) {
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        setContentView(R.layout.view_null);
    }

    private void showShare() {

        SharedPreferences share = getSharedPreferences("logIn", MODE_PRIVATE);
        info = share.getString("login", "");
        Intent intent = DetailsTwoActivity.this.getIntent();
        final String getUrl = intent.getStringExtra("key");
        if (info.equals("")) {
            url = url1 + getUrl + "/" + 1;
        } else {
            url = url1 + getUrl + "/" + info;
        }
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            @Override
            public void onShare(Platform platform, cn.sharesdk.framework.Platform.ShareParams paramsToShare) {
                if ("QZone".equals(platform.getName())) {
                    paramsToShare.setTitle(null);
                    paramsToShare.setTitleUrl(null);
                }
                if ("SinaWeibo".equals(platform.getName())) {
                    paramsToShare.setUrl(null);
                    paramsToShare.setText("分享文本 http://www.baidu.com");
                }
                if ("Wechat".equals(platform.getName())) {
                    Bitmap imageData = BitmapFactory.decodeResource(getResources(), R.drawable.ssdk_logo);
                    paramsToShare.setImageData(imageData);
                }
                if ("WechatMoments".equals(platform.getName())) {
                    Bitmap imageData = BitmapFactory.decodeResource(getResources(), R.drawable.ssdk_logo);
                    paramsToShare.setImageData(imageData);
                }

            }
        });
        // 启动分享GUI
        oks.show(this);
        //分享成功加积分的
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                DetailBean bean = gson.fromJson(s, DetailBean.class);
                url = url1 + bean.getCID() + "/" + info;
                RequestParams params = new RequestParams(url);
                x.http().get(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String o) {

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
