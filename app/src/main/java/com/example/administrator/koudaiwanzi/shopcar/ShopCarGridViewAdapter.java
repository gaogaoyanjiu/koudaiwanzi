package com.example.administrator.koudaiwanzi.shopcar;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

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
 * Created by Administrator on 2016/10/13.
 */
public class ShopCarGridViewAdapter extends CommonAdapter<ShopCarBean.LIKEITEMSBean>{

    public ShopCarGridViewAdapter(Context context, List<ShopCarBean.LIKEITEMSBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, ShopCarBean.LIKEITEMSBean likeitemsBean) {
        //商品名称
        holder.setText(R.id.tv_name_shop, likeitemsBean.getTRADENAME());

        TextView tv_old = holder.getView(R.id.tv_oldPrice_shop);
        //判断价格是否打折
        if (likeitemsBean.getDISCOUNT() == 0){
            tv_old.setVisibility(View.INVISIBLE);
            holder.setText(R.id.tv_price_shop, likeitemsBean.getPRICE()+"");
        }else {
            tv_old.setVisibility(View.VISIBLE);
            holder.setText(R.id.tv_price_shop, likeitemsBean.getBARGAIN()+"");
            holder.setText(R.id.tv_oldPrice_shop, likeitemsBean.getPRICE()+"");
            tv_old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
        TextView tv_ISOUTSTOCK = holder.getView(R.id.tv_ISOUTSTOCK);
        if (likeitemsBean.getISOUTSTOCK() == 0){
            tv_ISOUTSTOCK.setVisibility(View.GONE);
        }else {
            tv_ISOUTSTOCK.setVisibility(View.VISIBLE);
        }


        SimpleDraweeView imageView = holder.getView(R.id.sv_shop);
        //FRESCO缓存图片
        Uri imageUri = Uri.parse(MyUrl.url + likeitemsBean.getICOURL());
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(imageUri)
                .setResizeOptions(new ResizeOptions(200, 200))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(imageView.getController())
                .setImageRequest(request)
                .build();
        imageView.setController(controller);

    }
}
