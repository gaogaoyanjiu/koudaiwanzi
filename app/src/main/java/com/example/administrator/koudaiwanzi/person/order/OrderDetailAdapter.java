package com.example.administrator.koudaiwanzi.person.order;

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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/9/30.
 */
public class OrderDetailAdapter extends CommonAdapter<OrderDetailBean.ORDERITEMSBean>{

    public OrderDetailAdapter(Context context, List<OrderDetailBean.ORDERITEMSBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, OrderDetailBean.ORDERITEMSBean bean) {
        //名称
        holder.setText(R.id.tv_item_order_name, bean.getTRADENAME());
        //规格
        holder.setText(R.id.tv_item_order_type, "规格：" + bean.getSTANDARD());
        //价格
        if (bean.getDISCOUNT() == 0){
            holder.setText(R.id.tv_item_order_price, bean.getPRICE()+"");
        }else {
            holder.setText(R.id.tv_item_order_price, bean.getBARGAIN()+"");
        }
        //数量
        holder.setText(R.id.tv_item_order_num, "x" + bean.getQUANTITY());
        //加载图片
        SimpleDraweeView simpleDraweeView = holder.getView(R.id.iv_item_order_tag);
        Uri imageUri = Uri.parse(MyUrl.url + bean.getICOURL());
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(imageUri)
                .setResizeOptions(new ResizeOptions(200, 200))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(simpleDraweeView.getController())
                .setImageRequest(request)
                .build();
        simpleDraweeView.setController(controller);

    }
}
