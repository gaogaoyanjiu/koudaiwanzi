package com.example.administrator.koudaiwanzi.person.reject;

import android.content.Context;
import android.net.Uri;

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
 * Created by Administrator on 2016/8/2.
 */
public class RejectAdapter extends CommonAdapter<RejectBean.REQUESTITMEBean>{

    public RejectAdapter(Context context, List<RejectBean.REQUESTITMEBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, RejectBean.REQUESTITMEBean rejectBean) {
        //名称
        holder.setText(R.id.shopping_car_recyclerview_item_title, rejectBean.getTRADENAME());
        //品牌
        holder.setText(R.id.shopping_car_recyclerview_item_little_title, rejectBean.getBONAME());
        //价格
        if (rejectBean.getDISCOUNT() == 0){
            holder.setText(R.id.shopping_car_recyclerview_item_newprice, rejectBean.getPRICE()+"");
        }else {
            holder.setText(R.id.shopping_car_recyclerview_item_newprice, rejectBean.getBARGAIN()+"");
        }

        //加载图片
        Uri imageUri = Uri.parse(MyUrl.url + rejectBean.getICOURL());
        SimpleDraweeView simpleDraweeView = holder.getView(R.id.shopping_car_recyclerview_item_iv);
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(imageUri)
                .setResizeOptions(new ResizeOptions(150, 150))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(simpleDraweeView.getController())
                .setImageRequest(request)
                .build();
        simpleDraweeView.setController(controller);

    }
}
