package com.example.administrator.koudaiwanzi.person.concern;

import android.content.Context;
import android.content.Intent;
import android.opengl.Visibility;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.CommonAdapter;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.base.ViewHolder;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/8/2.
 */
public class ConcernAdapter extends CommonAdapter<ConcernBean>{
 private  Context context;

    public ConcernAdapter(Context context, List<ConcernBean> data, int layoutId) {
        super(context, data, layoutId);
        this.context = context;
    }

    @Override
    public void convert(ViewHolder holder, final ConcernBean concernBean) {

        holder.setText(R.id.user_name, concernBean.getUSERNAME());
        int a = concernBean.getBLOYNUM();
        int b = concernBean.getCONUM();
        String c = a+"个丸子说，"+b+"个粉丝";
        holder.setText(R.id.fans_count,c);
        ImageView imageView = holder.getView(R.id.my_concern_head);
        x.image().bind(imageView, MyUrl.url+concernBean.getHEADURL());
        holder.setOnClickListener(R.id.concern_notAttention, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams  params = new RequestParams(MyUrl.url+concernBean.getDELCONCEMURL());
                x.http().get(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Intent intent = new Intent(context,ConcernActivity.class);
                        context.startActivity(intent);
                        ((ConcernActivity)context).finish();
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
        });

    }
}
