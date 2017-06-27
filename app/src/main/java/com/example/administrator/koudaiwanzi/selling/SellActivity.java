package com.example.administrator.koudaiwanzi.selling;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.details.detail.size.ShowPop;
import com.example.administrator.koudaiwanzi.person.collection.CollectionBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/29.
 * 商品特卖
 */

@ContentView(R.layout.activity_sell)
public class SellActivity extends AppCompatActivity {
    private ExAdapter exAdapter;
    private List<List<NEWITEMSBean>> childs;
    private String url;
    private String shopUrl;
    private String url1;

    @ViewInject(R.id.el_sell)
    private ExpandableListView expandableListView;

    @ViewInject(R.id.ll_sell)
    private LinearLayout ll_hide;

    // 弹出的popupwindow
    private ShowPop mShowPopup;
    // Layoutinflater
    private LayoutInflater mLayoutInflater;
    // 主页缩放动画
    private Animation mScalInAnimation1;
    // 主页缩放完毕小幅回弹动画
    private Animation mScalInAnimation2;
    // 主页回弹正常状态动画
    private Animation mScalOutAnimation;
    // 标题恢复动画
    private Animation mTranInAnimation;
    // 标题消失动画
    private Animation mTranOutAnimation;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        mLayoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // 动画初始化
        mScalInAnimation1 = AnimationUtils.loadAnimation(this,
                R.anim.root_in);
        mScalInAnimation2 = AnimationUtils.loadAnimation(this,
                R.anim.root_in2);
        mScalOutAnimation = AnimationUtils.loadAnimation(this,
                R.anim.root_out);
        mTranInAnimation = AnimationUtils.loadAnimation(this,
                R.anim.title_in);
        mTranOutAnimation = AnimationUtils.loadAnimation(this,
                R.anim.title_out);
        mScalInAnimation1.setAnimationListener(new ScalInAnimation1());

        url1 = getIntent().getStringExtra("sell");
        url = MyUrl.url + url1;
        //去掉线
        expandableListView.setDivider(null);

        childs = new ArrayList<>();
        exAdapter = new ExAdapter(this, handler);

        RequestParams params = new RequestParams(url);
        Log.e( "oerett", url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
//                Type type = new TypeToken<List<SellBean>>() {
//                }.getType();
//                datas = gson.fromJson(s, type);

                SellBean bean = gson.fromJson(s, SellBean.class);
                Log.e("111", "dddddddd");
                Log.e("wwfafch", bean.getNEWSALEITEMS().size()+"");
                for (int i = 0; i < bean.getNEWSALEITEMS().size(); i++) {
                    List<NEWITEMSBean> child = new ArrayList<NEWITEMSBean>();
                    for (int j = 0; j < bean.getNEWSALEITEMS().get(i).getNEWITEMS().size(); j++) {
                        child.add( bean.getNEWSALEITEMS().get(i).getNEWITEMS().get(j));
                    }
                    childs.add(child);
                }
                Log.e("ttttssghjh","fgbmnm");
                exAdapter.addData(bean.getNEWSALEITEMS(), childs);
                expandableListView.setAdapter(exAdapter);
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

        //点击其他项 之前点开的child自动关闭
        //group的展开监听
//        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                for (int i = 0; i < datas.size(); i++) {
//                    if (groupPosition != i){
//                        expandableListView.collapseGroup(i);
//                    }
//                }
//            }
//        });

        //设置 属性 GroupIndicator 去掉默认向下的箭头
        expandableListView.setGroupIndicator(null);


    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 30) {
                shopUrl = (String) msg.obj;
                Log.e("ersfhg", MyUrl.url + shopUrl);

                // 标题和主页开始播放动画
                ll_hide.startAnimation(mScalInAnimation1);

                // 弹出sPopupWindow
                mShowPopup = new ShowPop(SellActivity.this, mLayoutInflater.inflate(
                        R.layout.view_pop, null), MyUrl.url + shopUrl);

                mShowPopup.setOnDismissListener(new OnPopupDismissListener());
                mShowPopup.showAtLocation(SellActivity.this.findViewById(R.id.ll_sell),
                        Gravity.CENTER, 0, 0); // ����layout��PopupWindow����ʾ��λ��
            }
        }
    };

    /**
     * popupwindow消失的回调
     */
    private class OnPopupDismissListener implements
            android.widget.PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // 标题和主页开始播放动画
            ll_hide.startAnimation(mScalOutAnimation);
        }
    }

    /**
     * 缩小动画的回调
     */
    public class ScalInAnimation1 implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            ll_hide.startAnimation(mScalInAnimation2);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }



    @Event(R.id.iv_sell_back)
    private void ivBack(View view){
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        setContentView(R.layout.view_null);
    }

}
