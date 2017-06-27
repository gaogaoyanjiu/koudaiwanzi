package com.example.administrator.koudaiwanzi.classification.brand;

import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */

public class BrandGridAdapter extends BaseAdapter {
    private Context context;
    private List<BrandDetailBean> data;
    private Handler handler;


    public BrandGridAdapter(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    public void addData(List<BrandDetailBean> data){
        this.data = data;
    }



    public int getCount() {
        return data.size()!= 0&& data!= null ? data.size() : 0;
    }


    public Object getItem(int position) {
        return data.get(position);
    }


    public long getItemId(int position) {
        return position;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = null;
        final int mPosition = position;
        if (convertView == null){
            myViewHolder = new MyViewHolder(position);
            convertView = LayoutInflater.from(context).inflate(R.layout.add_view1,null);
            myViewHolder.iv = (ImageView) convertView.findViewById(R.id.view_item_iv1);
            myViewHolder.tv = (TextView) convertView.findViewById(R.id.show_item_tv1);
            myViewHolder.iv_shop = (ImageView) convertView.findViewById(R.id.iv_product_shopCar);
            myViewHolder.tv1 = (TextView) convertView.findViewById(R.id.show_item_newprice1);
            myViewHolder.tv2 = (TextView) convertView.findViewById(R.id.show_item_oldprice1);
            convertView.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        myViewHolder.tv.setText(data.get(position).getTRADENAME()+"");
        if (data.get(position).getDISCOUNT() == 0){
            myViewHolder.tv1.setText("¥" + data.get(position).getPRICE());
            myViewHolder.tv2.setVisibility(View.INVISIBLE);
        }else {
            myViewHolder.tv1.setVisibility(View.VISIBLE);
            myViewHolder.tv2.setText("¥" + data.get(position).getBARGAIN());
            myViewHolder.tv1.setText("¥" + data.get(position).getPRICE());
            //给textView中间加横线
            myViewHolder.tv2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }

          Log.d("321sdoaxewq", data.get(position).getTRADENAME()+"");
         //设置加载图片的参数
                        ImageOptions options = new ImageOptions.Builder()
                                //图片大小
                                .setSize(DensityUtil.dip2px(200), DensityUtil.dip2px(250))
                                // 是否忽略GIF格式的图片
                                .setIgnoreGif(false)
                                // 图片缩放模式
                                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                                // 得到ImageOptions对象
                                .build();
                        x.image().bind(myViewHolder.iv, MyUrl.url + data.get(position).getICOURL(), options);

        myViewHolder.iv_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendMessage(handler.obtainMessage(30, data.get(position).getDetailUrl()));
            }
        });
        return convertView;
    }
    class MyViewHolder{
        private int pos;
        private ImageView iv,iv_shop;
        private TextView tv,tv1,tv2,tv3,tv4;
        public MyViewHolder(int pos) {
            this.pos = pos;
        }

    }
}
