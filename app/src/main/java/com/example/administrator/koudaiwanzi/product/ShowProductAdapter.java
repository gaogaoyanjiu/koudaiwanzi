package com.example.administrator.koudaiwanzi.product;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
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
import com.example.administrator.koudaiwanzi.person.login.LoginActivity;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;
import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 */
public class ShowProductAdapter extends BaseAdapter {
    private List<ShowProductBean.DITEMSBean> data;
    private Context context;
    private Handler handler;


    public ShowProductAdapter(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    public void addData(List<ShowProductBean.DITEMSBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.show_item, null);
            myViewHolder.tv_name = (TextView) convertView.findViewById(R.id.show_item_tv1);
            myViewHolder.iv_topPicture = (ImageView) convertView.findViewById(R.id.show_item_iv1);
            myViewHolder.tv_price = (TextView) convertView.findViewById(R.id.show_item_newprice1);
            myViewHolder.tv_price2 = (TextView) convertView.findViewById(R.id.show_item_oldprice1);
            myViewHolder.iv_shop = (ImageView) convertView.findViewById(R.id.iv_product_shopCar);
            myViewHolder.tv_stock = (TextView) convertView.findViewById(R.id.tv_show_ISOUTSTOCK);
            convertView.setTag(myViewHolder);

        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        if (data.get(position).getDISCOUNT() == 0){
            myViewHolder.tv_price.setText("¥" + data.get(position).getPRICE());
            myViewHolder.tv_price2.setVisibility(View.GONE);

        }else {
            myViewHolder.tv_price2.setVisibility(View.VISIBLE);
            myViewHolder.tv_price.setText("¥" + data.get(position).getBARGAIN());
            myViewHolder.tv_price2.setText("¥" + data.get(position).getPRICE());
            //给textView中间加横线
            myViewHolder.tv_price2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
        myViewHolder.tv_name.setText(data.get(position).getTRADENAME());
        if (data.get(position).getISOUTSTOCK() == 0){
            myViewHolder.tv_stock.setVisibility(View.GONE);
        }else {
            myViewHolder.tv_stock.setVisibility(View.VISIBLE);
        }

        ImageOptions options3 = new ImageOptions.Builder()
                //图片大小
                .setSize(250, 150)
                // 是否忽略GIF格式的图片
                .setIgnoreGif(false)
                // 图片缩放模式
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                // 得到ImageOptions对象
                .build();

        x.image().bind(myViewHolder.iv_topPicture, MyUrl.url+ data.get(position).getICOURL(),options3);
        myViewHolder.iv_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (data.get(position).getISOUTSTOCK() == 0){
                    handler.sendMessage(handler.obtainMessage(30, data.get(position).getDetailUrl()));
                }else {
                    Toast.makeText(context, "商品缺货", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return convertView;
    }

    class MyViewHolder {
        private TextView tv_name,tv_price,tv_price2 ,tv_stock;
        private ImageView iv_topPicture, iv_shop;
    }

}
