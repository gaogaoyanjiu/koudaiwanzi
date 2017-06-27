package com.example.administrator.koudaiwanzi.newproduct;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.CommonAdapter;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.base.ViewHolder;
import com.example.administrator.koudaiwanzi.details.DetailsActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.squareup.picasso.Picasso;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/9/26.
 */
public class NewGridViewAdapter extends CommonAdapter<NEWITEMBean>{
    private Context context;
    private Handler handler;
    private String myUrl = MyUrl.url;

    public NewGridViewAdapter(Context context, List<NEWITEMBean> data, int layoutId, Handler handler) {
        super(context, data, layoutId);
        this.context = context;
        this.handler = handler;
    }

    @Override
    public void convert(ViewHolder holder, final NEWITEMBean newitemBean) {
        holder.setText(R.id.tv_name_two_two,newitemBean.getBONAME());
        holder.setText(R.id.tv_name_group_addView_two, newitemBean.getTRADENAME());
        holder.setText(R.id.tv_size_add_two, newitemBean.getSTANDARD());
        SimpleDraweeView imageView = holder.getView(R.id.iv_group_addView_two);
        TextView tv = holder.getView(R.id.tv_add_view3);
        if(newitemBean.getISOUTSTOCK() == 0){
            tv.setVisibility(View.GONE);
        }else
        {
            tv.setVisibility(View.VISIBLE);
        }
        //FRESCO缓存图片
        Uri imageUri = Uri.parse(myUrl + newitemBean.getICOURL());
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(imageUri)
                .setResizeOptions(new ResizeOptions(200, 200))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(imageView.getController())
                .setImageRequest(request)
                .build();
        imageView.setController(controller);
        //监听跳转到商品详情页
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("key", newitemBean.getDetailUrl());
                context.startActivity(intent);
            }
        });

        holder.setOnClickListener(R.id.iv_shopCar_new, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(newitemBean.getISOUTSTOCK() == 0){
                    handler.sendMessage(handler.obtainMessage(30, newitemBean.getDetailUrl()));
                }else
                {
                    Toast.makeText(context, "商品缺货", Toast.LENGTH_SHORT).show();
                }


            }
        });
        //判断价格是否打折
        if (newitemBean.getDISCOUNT() == 0){
            holder.setText(R.id.tv_price_new_group_two, "¥" + newitemBean.getPRICE());
        }else {
            holder.setText(R.id.tv_price_new_group_two, "¥" + newitemBean.getBARGAIN());
            holder.setText(R.id.tv_old_price_group_two, "¥" + newitemBean.getPRICE());
        }
        //原价划线
        TextView textView = holder.getView(R.id.tv_old_price_group_two);
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }
}
