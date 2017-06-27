package com.example.administrator.koudaiwanzi.unionpay;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Administrator on 2016/9/21.
 */

@ContentView(R.layout.activity_unionpay)
public class Unionpay extends AppCompatActivity {
    @ViewInject(R.id.wv)
    private WebView webView;

    private String url2;
    private String myUrl = MyUrl.url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        Intent intent = getIntent();
        url2 = intent.getStringExtra("Unionpay");
        Log.e("asdfzxc", url2);


        webView.getSettings().setJavaScriptEnabled(true);

        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });


//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });


        //WebView加载web资源
        webView.loadUrl(url2);


    }

}
