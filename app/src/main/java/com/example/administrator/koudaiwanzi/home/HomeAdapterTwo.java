package com.example.administrator.koudaiwanzi.home;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
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
 * Created by Administrator on 2016/7/2.
 */
public class HomeAdapterTwo extends BaseAdapter {
    private List<HomeBean.STORYBean> data;
    private Context context;

    public HomeAdapterTwo(Context context) {
        this.context = context;
    }

    public void addData(List<HomeBean.STORYBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return data.size() > 0 && data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.home_listview_item3, null);
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        myViewHolder.tv_title.setText(data.get(position).getTHEMENAME());
//        ImageOptions options3 = new ImageOptions.Builder()
//                //图片大小
//                .setSize(300, 250)
//                // 是否忽略GIF格式的图片
//                .setIgnoreGif(false)
//                // 图片缩放模式
//                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
//                // 得到ImageOptions对象
//                .build();
//
//        x.image().bind(myViewHolder.image, MyUrl.url + data.get(position).getPREVIEW(), options3);

        Uri imageUri = Uri.parse(MyUrl.url + data.get(position).getPREVIEW());
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(imageUri)
                .setResizeOptions(new ResizeOptions(280, 250))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(myViewHolder.image.getController())
                .setImageRequest(request)
                .build();
        myViewHolder.image.setController(controller);



        return convertView;
    }

    class MyViewHolder {
        private TextView tv_title;
        private ImageView imageView_head;
        private SimpleDraweeView image;

        public MyViewHolder(View view) {
            tv_title = (TextView) view.findViewById(R.id.tv_title_head);
            image = (SimpleDraweeView) view.findViewById(R.id.home_tail_iv);
            imageView_head = (ImageView) view.findViewById(R.id.home_tail_left_iv);
        }

    }
}
