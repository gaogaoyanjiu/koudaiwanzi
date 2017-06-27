package com.example.administrator.koudaiwanzi.details.detail;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.android.volley.toolbox.NetworkImageView;
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
import com.squareup.picasso.Picasso;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/8/3.
 */
public class DetailPictureAdapter extends CommonAdapter<DetailBean.PICSBean> {
    private String url = MyUrl.url;

    public DetailPictureAdapter(Context context, List<DetailBean.PICSBean> data, int layoutId) {
        super(context, data, layoutId);
    }


    @Override
    public void convert(ViewHolder holder, DetailBean.PICSBean picsBean) {
        SimpleDraweeView imageView = holder.getView(R.id.iv_detail_drawble);

        //FRESCO缓存图片
        Uri imageUri = Uri.parse(url + picsBean.getIMGURL());
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(imageUri)
                .setResizeOptions(new ResizeOptions(400, 300))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(imageView.getController())
                .setImageRequest(request)
                .build();
        imageView.setController(controller);
    }
}
