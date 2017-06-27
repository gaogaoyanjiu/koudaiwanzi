package com.example.administrator.koudaiwanzi.person.concern;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/8/2.
 * 已关注的个人的详情界面（由关注界面跳转进来）
 */

@ContentView(R.layout.activity_already_concern_person)
public class ConcernPersonActivity extends AppCompatActivity {

    private List<ConcernPersonBean.StoryBean> data;
    private String url1 = MyUrl.url;
    private ConcernPAdapter adapter;

    @ViewInject(R.id.already_concern_person_listview)
    private RecyclerView recyclerView;

    @ViewInject(R.id.wanzishuo_count)
    private TextView tv_wan;

    @ViewInject(R.id.user_name)
    private TextView tv_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        Intent intent = getIntent();
        String url2 = intent.getStringExtra("Concern");
        String a = intent.getStringExtra("ConcernBLOYNUM");
        String b = intent.getStringExtra("ConcernCONUM");
        String name = intent.getStringExtra("ConcernName");
        String c = a+"个丸子说，"+b+"个粉丝";
        tv_name.setText(name);
        tv_wan.setText(c);
        adapter = new ConcernPAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RequestParams params = new RequestParams(url1+url2);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                ConcernPersonBean bean = gson.fromJson(s,ConcernPersonBean.class);
                data = bean.getStory();
                if (data == null) {

                } else {
                    adapter.addData(data);
                    recyclerView.setAdapter(adapter);
//                    recyclerView.setOnScrollListener(new AbsListView.OnScrollListener() {
//                        @Override
//                        public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//                        }
//
//                        @Override
//                        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//
//                            //利用广播真的是很好用啊  连适配器中都能接收广播
//                            if (visibleItemCount == 3) {
//                                Intent intent = new Intent("com.meatball.broadcast");
//                                //MainActivity.this.sendBroadcast(intent);
//                                LocalBroadcastManager.getInstance(ConcernPersonActivity.this).sendBroadcast(intent);
//                            }
//
//                        }
//                    });

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
