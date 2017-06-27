package com.example.administrator.koudaiwanzi.newsell;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.details.detail.size.ShowPop;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Administrator on 2016/08/29.
 * 特辑界面
 */
@ContentView(R.layout.activity_new_sell)
public class NewSellActivity extends AppCompatActivity implements View.OnClickListener {

    //绑定listview
    @ViewInject(R.id.new_sell_listView)
    private ListView listView;
    //listview适配器
    private NewSellListViewAdapter adapter;
    private LinearLayout listViewHeader;
    //url 应该是MyUrl 暂时没有
    private String myUrl = MyUrl.url;
    private String url;
   //@ViewInject(R.id.image_new_sell)
    private SimpleDraweeView imag;

    private String shopUrl;

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

    @ViewInject(R.id.ll_new_sell)
    private LinearLayout ll_hide;

    @ViewInject(R.id.el_new_product)
    private ExpandableListView expandableListView;


    //返回按钮
    @ViewInject(R.id.new_sell_title_back)
    private ImageView newSellBtn;

    //点击购买
    @ViewInject(R.id.new_sell_buy_iv)
    private TextView new_sell_buy_iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_new_sell);
        x.view().inject(this);

        String url_sell = getIntent().getStringExtra("teji");
        url = myUrl + url_sell;

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



        newSellBtn.setOnClickListener(this);
        //行布局的监听
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(NewSellActivity.this, "您点击了第"+ position + "行", Toast.LENGTH_SHORT).show();
//            }
//        });

        //拉取网路数据
        RequestParams params = new RequestParams(url);
        Log.e("wsadz", url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                NewSellEntity entity = gson.fromJson(s,NewSellEntity.class);
                //头布局
                listViewHeader = (LinearLayout) getLayoutInflater().inflate(R.layout.item_newsell_listviewheader,listView,false);
                //头布局中的图片
                imag = (SimpleDraweeView) listViewHeader.findViewById(R.id.image_new_sell);
                //绑定网络图片
//                x.image().bind(imag, myUrl + entity.getSPEBANNER().get(0).getIMGURL());
                Log.e("imag",   myUrl + entity.getSPEBANNER().get(0).getIMGURL());
                Uri imageUri = Uri.parse(myUrl + entity.getSPEBANNER().get(0).getIMGURL());
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(imageUri)
                        .setResizeOptions(new ResizeOptions(400, 350))
                        .build();
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setOldController(imag.getController())
                        .setImageRequest(request)
                        .build();
                imag.setController(controller);
                //向listview中添加头布局
                listView.addHeaderView(listViewHeader);
                //初始化适配器
                adapter = new NewSellListViewAdapter(NewSellActivity.this,entity,handler);
                //绑定适配器
                listView.setAdapter(adapter);

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
                mShowPopup = new ShowPop(NewSellActivity.this, mLayoutInflater.inflate(
                        R.layout.view_pop, null), MyUrl.url + shopUrl);

                mShowPopup.setOnDismissListener(new OnPopupDismissListener());
                mShowPopup.showAtLocation(NewSellActivity.this.findViewById(R.id.ll_new_sell),
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




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //返回按钮的监听
            case R.id.new_sell_title_back:
                finish();
                break;

        }
    }
}
