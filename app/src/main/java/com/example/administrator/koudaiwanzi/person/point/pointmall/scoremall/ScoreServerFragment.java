package com.example.administrator.koudaiwanzi.person.point.pointmall.scoremall;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.BaseFragment;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.google.gson.Gson;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Administrator on 2016/7/6.
 * 积分服务界面
 */
@ContentView(R.layout.fragment_score_mall_two)
public class ScoreServerFragment extends BaseFragment {
    private ScoreServerAdapter adapter;
    private String url1 = MyUrl.url;
    private String UID = "1";
    private String url;
    @ViewInject(R.id.score_mall_two_lv)
    private ListView lv;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new ScoreServerAdapter(getActivity());

        SharedPreferences share = getActivity().getSharedPreferences("logIn", Context.MODE_PRIVATE);
        UID = share.getString("login", "");
        url = url1+"Integral.svc/shopintegral/" + UID;
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                ScoreBean bean = gson.fromJson(s,ScoreBean.class);
                adapter.addData(bean.getSERIVCE());
                lv.setAdapter(adapter);
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
