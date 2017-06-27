package com.example.administrator.koudaiwanzi.shopcar;

import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;

import org.xutils.x;
import java.util.List;

/**
 * Created by Administrator on 2016/5/31.
 */
public class MergerAdapter extends BaseAdapter{
    private Context context;
    private List<MergeBean.DITEMSBean> data;
    private Handler handler;
    private String url = MyUrl.url;

    public MergerAdapter(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    public void addData(List<MergeBean.DITEMSBean> data){
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
        if (convertView == null){
            myViewHolder = new MyViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_merger,null);
            myViewHolder.tv_name1 = (TextView) convertView.findViewById(R.id.mycollection_item_tv1);
            myViewHolder.iv_p1 = (ImageView) convertView.findViewById(R.id.mycollection_item_iv1);
            myViewHolder.tv_price1 = (TextView) convertView.findViewById(R.id.mycollection_item_newprice1);
            myViewHolder.tv_priceOld1 = (TextView) convertView.findViewById(R.id.mycollection_item_oldprice1);
            myViewHolder.img_shopCar = (ImageView) convertView.findViewById(R.id.merge_shopping_car);

            convertView.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        if (data.get(position).getDISCOUNT() == 0){
            myViewHolder.tv_price1.setText("¥" + data.get(position).getPRICE()+"");
            myViewHolder.tv_priceOld1.setVisibility(View.GONE);
        }else {
            myViewHolder.tv_price1.setText("¥" + data.get(position).getBARGAIN()+"");
            myViewHolder.tv_priceOld1.setText("¥" + data.get(position).getPRICE()+"");
        }
        myViewHolder.tv_name1.setText(data.get(position).getTRADENAME());

        myViewHolder.tv_priceOld1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        myViewHolder.img_shopCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendMessage(handler.obtainMessage(18, url + data.get(position).getDetailUrl()));
            }
        });

        String b = data.get(position).getICOURL();
        String c = url + b;
        x.image().bind(myViewHolder.iv_p1, c);
        return convertView;
    }

    class MyViewHolder{
        private TextView tv_name1,tv_price1,tv_priceOld1;
        private ImageView iv_p1, img_shopCar;
    }

}
