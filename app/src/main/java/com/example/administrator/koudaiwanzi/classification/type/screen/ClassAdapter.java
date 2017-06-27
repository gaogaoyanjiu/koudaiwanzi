package com.example.administrator.koudaiwanzi.classification.type.screen;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
 * Created by Administrator on 2016/6/25.
 */
public class ClassAdapter extends BaseAdapter{
    private String imgUrl = MyUrl.url;
//    private List<String> inits;
    private List<ProductBean.DITEMSBean> data;
    private Context context;
    private Handler handler;


    public ClassAdapter(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }



    public void addData(List<ProductBean.DITEMSBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return data.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = null;
        if (convertView == null) {
            myViewHolder = new MyViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.show_item_two, null);
            myViewHolder.tv_name = (TextView) convertView.findViewById(R.id.show2_item_tv1);
            myViewHolder.iv_topPicture = (SimpleDraweeView) convertView.findViewById(R.id.show2_item_iv1);
            myViewHolder.tv_nowPrice = (TextView) convertView.findViewById(R.id.show2_item_newprice1);
            myViewHolder.tv_oldPrice = (TextView) convertView.findViewById(R.id.tv_price_old);
            myViewHolder.iv_shop = (ImageView) convertView.findViewById(R.id.iv_shop_class);
            myViewHolder.tv_ISOUTSTOCK = (TextView) convertView.findViewById(R.id.tv_show_two_ISOUTSTOCK);
            convertView.setTag(myViewHolder);

        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        if (data.get(position).getDISCOUNT() == 0){
            myViewHolder.tv_oldPrice.setVisibility(View.INVISIBLE);
            myViewHolder.tv_nowPrice.setText("¥" + data.get(position).getPRICE()+"");
        }else {
            myViewHolder.tv_oldPrice.setVisibility(View.VISIBLE);
            myViewHolder.tv_nowPrice.setText("¥" + data.get(position).getBARGAIN()+"");
            myViewHolder.tv_oldPrice.setText("¥" + data.get(position).getPRICE()+"");
            //给textView中间加横线
            myViewHolder.tv_oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
        if (data.get(position).getISOUTSTOCK() == 0){
            myViewHolder.tv_ISOUTSTOCK.setVisibility(View.GONE);
        }else {
            myViewHolder.tv_ISOUTSTOCK.setVisibility(View.VISIBLE);
        }
        myViewHolder.tv_name.setText(data.get(position).getTRADENAME());


//        ImageOptions options = new ImageOptions.Builder()
//                //图片大小
//                .setSize(DensityUtil.dip2px(200), DensityUtil.dip2px(150))
//                // 是否忽略GIF格式的图片
//                .setIgnoreGif(false)
//                // 图片缩放模式
//                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
//                // 得到ImageOptions对象
//                .build();
//
//        x.image().bind(myViewHolder.iv_topPicture, MyUrl.url+data.get(position).getICOURL(), options);


        //FRESCO缓存图片
        Uri imageUri = Uri.parse(imgUrl + data.get(position).getICOURL());
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(imageUri)
                .setResizeOptions(new ResizeOptions(200, 200))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(myViewHolder.iv_topPicture.getController())
                .setImageRequest(request)
                .build();
        myViewHolder.iv_topPicture.setController(controller);





        myViewHolder.iv_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.get(position).getISOUTSTOCK() == 0){
                    handler.sendMessage(handler.obtainMessage(28, data.get(position).getDetailUrl()));
                }else {
                    Toast.makeText(context, "商品缺货", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return convertView;
    }

    class MyViewHolder {
        private TextView tv_name,tv_nowPrice,tv_oldPrice,tv_ISOUTSTOCK;
        private ImageView iv_shop;
        private SimpleDraweeView iv_topPicture;
    }


}
