package com.example.administrator.koudaiwanzi.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.CommonAdapterRV;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.base.ViewHolderRV;
import com.example.administrator.koudaiwanzi.rank.RankTwoActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/7/13.
 */
public class RecyclerAdapter extends CommonAdapterRV<HomeBean.TopBean> {
    private String imgUrl = MyUrl.url;
    private Context context;

    public RecyclerAdapter(Context context, int layoutId, List<HomeBean.TopBean> data) {
        super(context, layoutId, data);
        this.context = context;
    }

    @Override
    public void convert(final ViewHolderRV holder, HomeBean.TopBean topBean) {
        super.convert(holder, topBean);


        //商品名称
        holder.setText(R.id.home_recommend_tv, topBean.getTRADENAME());
        //判断是否打折
        if (topBean.getDISCOUNT() != 0){
            holder.setText(R.id.home_recommend_newprice1, "¥" + topBean.getBARGAIN());
            holder.setText(R.id.tv_home_head_item_old_price, "¥" + topBean.getPRICE());
        }else {
            holder.setText(R.id.home_recommend_newprice1, "¥" + topBean.getPRICE());
        }
        //原价加划线
        TextView textView = holder.getView(R.id.tv_home_head_item_old_price);
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        TextView tv = holder.getView(R.id.tv_home_top_ISOUTSTOCK);
        if (topBean.getISOUTSTOCK() == 0){
            tv.setVisibility(View.GONE);
        }else {
            tv.setVisibility(View.VISIBLE);
        }

        SimpleDraweeView imageView = holder.getView(R.id.iv_item_home_head);

        //FRESCO缓存图片
        Uri imageUri = Uri.parse(imgUrl + topBean.getTOPURL());
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(imageUri)
                .setResizeOptions(new ResizeOptions(200, 200))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(imageView.getController())
                .setImageRequest(request)
                .build();
        imageView.setController(controller);

        holder.setOnClickListener(R.id.ll_home_head_recyc, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("qwaer", holder.getPos()+"");
                Intent intent = new Intent(context, RankTwoActivity.class);
                context.startActivity(intent);
            }
        });

    }
}
