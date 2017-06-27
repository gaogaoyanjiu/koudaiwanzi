package com.example.administrator.koudaiwanzi.person.coupon;

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
import org.xutils.x;

/**
 * Created by Administrator on 2016/6/24.
 */

@ContentView(R.layout.fragment_coupon_not_use)
public class CouponNotUseFragment extends BaseFragment {
    private String url = MyUrl.url+"Users.svc//nousecoupon//db534290-4e37-4db1-ac51-b7caffec874d";

    private CouponNotUseListViewAdapter adapter;
    private ListView listView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = (ListView) getView().findViewById(R.id.coupon_not_use_listview);

        adapter = new CouponNotUseListViewAdapter(getActivity());

        final RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                CouponNotUsedBean bean = gson.fromJson(s, CouponNotUsedBean.class);
                adapter.addData(bean.getCP());
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
}
