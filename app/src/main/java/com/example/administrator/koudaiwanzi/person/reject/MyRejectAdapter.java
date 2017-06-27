package com.example.administrator.koudaiwanzi.person.reject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.CommonAdapter;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.base.ViewHolder;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 */
public class MyRejectAdapter extends CommonAdapter<MyRejectBean>{
    private Context context;

    public MyRejectAdapter(Context context, List<MyRejectBean> data, int layoutId) {
        super(context, data, layoutId);
        this.context = context;
    }

    @Override
    public void convert(ViewHolder holder, final MyRejectBean rejectBean) {
        //商品名称
        holder.setText(R.id.tv_myReject_name, rejectBean.getTRADENAME());

        if (rejectBean.getDISCOUNT() == 0){
            //交易金额
            holder.setText(R.id.tv_myReject_price, "¥" + rejectBean.getPRICE());
            //退款金额
            holder.setText(R.id.tv_myReject_price_two, "¥" + rejectBean.getPRICE());
        }else {
            //交易金额
            holder.setText(R.id.tv_myReject_price, "¥" + rejectBean.getBARGAIN());
            //退款金额
            holder.setText(R.id.tv_myReject_price_two, "¥" + rejectBean.getBARGAIN());
        }
        //品牌 名称
        holder.setText(R.id.tv_myReject_type, "品牌" + rejectBean.getBONAME());
        //加载图片
        SimpleDraweeView simpleDraweeView = holder.getView(R.id.iv_myreject);
        Uri imageUri = Uri.parse(MyUrl.url + rejectBean.getICOURL());
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(imageUri)
                .setResizeOptions(new ResizeOptions(200, 200))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(simpleDraweeView.getController())
                .setImageRequest(request)
                .build();
        simpleDraweeView.setController(controller);

        //退款中 或 退款成功
        if (rejectBean.getISRETURN() == 1){
            holder.setText(R.id.tv_reject, "退款/成功");
        }

        //点击售后的监听
        holder.setOnClickListener(R.id.tv_myReject_reject, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rejectBean.getISRETURN() == 0){
                    //没有退款成功
                    Intent intent = new Intent(context, RejectActivity.class);
                    intent.putExtra("myReject", rejectBean.getDetailUrl());
                    context.startActivity(intent);
                }else {
                    //退款成功
                    Intent intent = new Intent(context, RejectServiceActivity.class);
                    intent.putExtra("sec", rejectBean.getDetailUrl());
                    context.startActivity(intent);
                }

            }
        });

    }
}
