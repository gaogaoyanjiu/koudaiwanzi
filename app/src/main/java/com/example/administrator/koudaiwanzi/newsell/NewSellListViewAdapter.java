package com.example.administrator.koudaiwanzi.newsell;

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

import org.xutils.x;

/**
 * 特辑的listview适配器
 * Created by Administrator on 2016/08/29.
 */
public class NewSellListViewAdapter extends BaseAdapter {
    //定义context，实体类
    private Context context;
    private NewSellEntity entity;
    private Handler handler;

    //构造方法
    public NewSellListViewAdapter(Context context, NewSellEntity entity, Handler handler) {
        this.context = context;
        this.entity = entity;
        this.handler = handler;
    }

    @Override
    public int getCount() {
        return entity.getSPEITEMS().size();
    }

    @Override
    public Object getItem(int position) {
        return entity.getSPEITEMS().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_new_sell,null);
            viewHolder = new ViewHolder();
            viewHolder.imageView_new_sell_left = (SimpleDraweeView) convertView.findViewById(R.id.new_sell_imageView);
            viewHolder.textView_new_sell_textView_brand = (TextView) convertView.findViewById(R.id.new_sell_textView_brand);
            viewHolder.textView_capacity = (TextView) convertView.findViewById(R.id.new_sell_volume_weight);
            viewHolder.textView_new_sell_price = (TextView) convertView.findViewById(R.id.new_sell_price);
            viewHolder.textView_new_sell_content = (TextView) convertView.findViewById(R.id.new_sell_content);
            viewHolder.textView_new_sell_old_price= (TextView) convertView.findViewById(R.id.new_sell_old_price);
            viewHolder.iv_shopCar = (ImageView) convertView.findViewById(R.id.new_sell_buy_iv);
            viewHolder.textView_new_sell_buy_iv = (ImageView) convertView.findViewById(R.id.new_sell_buy_iv);
            viewHolder.tv_ISOUTSTOCK = (TextView) convertView.findViewById(R.id.tv_ISOUTSTOCK);
            viewHolder.textView_new_sell_buy_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "买了买啦", Toast.LENGTH_SHORT).show();

                }
            });
            convertView.setTag(viewHolder);
        }else {
         viewHolder = (ViewHolder) convertView.getTag();
        }

        //绑定航布局图片的网络数据
//        x.image().bind(viewHolder.imageView_new_sell_left, MyUrl.url + entity.getSPEITEMS().get(position).getICOURL());
        //FRESCO缓存图片
        Uri imageUri = Uri.parse(MyUrl.url + entity.getSPEITEMS().get(position).getICOURL());
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(imageUri)
                .setResizeOptions(new ResizeOptions(200, 200))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(viewHolder.imageView_new_sell_left.getController())
                .setImageRequest(request)
                .build();
        viewHolder.imageView_new_sell_left.setController(controller);


        //绑定航布局中品牌的网络数据
        viewHolder.textView_new_sell_textView_brand.setText(entity.getSPEITEMS().get(position).getBONAME());
        //绑定航布局中容量的网络数据
        viewHolder.textView_capacity.setText(entity.getSPEITEMS().get(position).getCAPACITY() + "g");

        //价格的判断是否有打折
        if (entity.getSPEITEMS().get(position).getDISCOUNT() != 0){
            viewHolder.textView_new_sell_old_price.setVisibility(View.VISIBLE);
            viewHolder.textView_new_sell_old_price.setText("¥" + entity.getSPEITEMS().get(position).getPRICE());
            viewHolder.textView_new_sell_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            viewHolder.textView_new_sell_price.setText("¥" +entity.getSPEITEMS().get(position).getBARGAIN());
        }else {
            viewHolder.textView_new_sell_old_price.setVisibility(View.INVISIBLE);
            viewHolder.textView_new_sell_price.setText("¥" +entity.getSPEITEMS().get(position).getPRICE());
        }

        if(entity.getSPEITEMS().get(position).getISOUTSTOCK()==0){
            viewHolder.tv_ISOUTSTOCK.setVisibility(View.GONE);
        }else {
            viewHolder.tv_ISOUTSTOCK.setVisibility(View.VISIBLE);
        }

        viewHolder.textView_new_sell_content.setText(entity.getSPEITEMS().get(position).getTRADENAME());
        viewHolder.iv_shopCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendMessage(handler.obtainMessage(30, entity.getSPEITEMS().get(position).getDetailUrl()));
            }
        });

        return convertView;
    }
    //缓存类
    class ViewHolder {
        private SimpleDraweeView imageView_new_sell_left;
        private TextView textView_new_sell_textView_brand;
        private TextView textView_capacity;
        private TextView textView_new_sell_price , tv_ISOUTSTOCK;
        private TextView textView_new_sell_content;
        private ImageView textView_new_sell_buy_iv;
        private TextView textView_new_sell_old_price;
        private ImageView iv_shopCar;
    }


}
