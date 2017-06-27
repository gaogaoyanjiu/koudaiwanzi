package com.example.administrator.koudaiwanzi.shopcar;


import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;

import org.xutils.x;
import java.util.List;

/**
 * Created by Administrator on 2016/6/2.
 */
public class ShopCarAdapter extends BaseAdapter {
    private List<ShopCarBean.SHOPITEMBean> data;
    private Handler mHandler;
    private Context context;            //上下文对象


    public ShopCarAdapter(Context context, List<ShopCarBean.SHOPITEMBean> data
            , Handler mHandler) {
        this.data = data;
        this.context = context;
        this.mHandler = mHandler;
        Log.e("qwasd", data.size() + "qq");
        notifyDataSetChanged();
    }

    public void del(int position) {
        data.remove(position);
        notifyDataSetChanged();
    }

    public void delALL(){
        data.removeAll(data);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return data.size() != 0 && data != null ? data.size() : 0;

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

            convertView = LayoutInflater.from(context).inflate(R.layout.shopping_car_recyclerview_item, null);
            myViewHolder.tv_name = (TextView) convertView.findViewById(R.id.shopping_car_recyclerview_item_title);
            myViewHolder.tv_num = (TextView) convertView.findViewById(R.id.number_btn);
            myViewHolder.tv_price = (TextView) convertView.findViewById(R.id.shopping_car_recyclerview_item_newprice);
            myViewHolder.tv_favourable = (TextView) convertView.findViewById(R.id.tv_old_price);
            myViewHolder.tv_favourable.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            myViewHolder.shop_check = (CheckBox) convertView.findViewById(R.id.shopping_car_checkbox_item);
            myViewHolder.btn_add = (Button) convertView.findViewById(R.id.add_btn);
            myViewHolder.btn_reduce = (Button) convertView.findViewById(R.id.reduce_btn);
            myViewHolder.imageView = (ImageView) convertView.findViewById(R.id.shopping_car_recyclerview_item_iv);
            myViewHolder.tv_note = (TextView) convertView.findViewById(R.id.note);
            myViewHolder.tv_value = (TextView) convertView.findViewById(R.id.shopping_car_recyclerview_item_little_title);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
//      直接添加减少数量的方法
        init(myViewHolder, position);

        x.image().bind(myViewHolder.imageView, MyUrl.url + data.get(position).getICOURL());
        if(data.get(position).getDISCOUNT() == 0){
            myViewHolder.tv_price.setText(data.get(position).getPRICE() + "");
            myViewHolder.tv_favourable.setVisibility(View.GONE);
        }else {
            myViewHolder.tv_price.setText(data.get(position).getBARGAIN() + "");
            myViewHolder.tv_favourable.setText(data.get(position).getPRICE() + "");
        }
        myViewHolder.tv_name.setText(data.get(position).getTRADENAME());
        myViewHolder.tv_note.setText("比其他商城共计"+data.get(position).getPOSTAGE()+"元（活动，满减，优惠信息）");

        myViewHolder.tv_num.setTag(position);
        myViewHolder.tv_num.setText(data.get(position).getQUANTITY() + "");

        myViewHolder.tv_value.setText("尺码/净含量：" + data.get(position).getSTANDARD());

        myViewHolder.shop_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chose = data.get(position).getCHOSEURL();
                Bundle bundle = new Bundle();
                bundle.putString("choseUrl", chose);
                mHandler.sendMessage(mHandler.obtainMessage(12, bundle));
            }
        });
        //标记位置
        myViewHolder.shop_check.setTag(position);
//        int a = data.size();
//        for (int i = 0; i < a; i++) {
//            if (data.get(i).getISCHOSE() == 1){
//                mHandler.sendEmptyMessage(5);
//            }else {
//
//            }
//        }


        if (data.get(position).getISCHOSE() == 1) {
            myViewHolder.shop_check.setChecked(true);

        } else {
            myViewHolder.shop_check.setChecked(false);
        }
        return convertView;
    }


    class MyViewHolder {
        private TextView tv_name, tv_num, tv_price, tv_favourable,tv_note,tv_value;
        private Button btn_add, btn_reduce;
        private CheckBox shop_check;
        private ImageView imageView;
    }

    private void init(final MyViewHolder itemView, final int position) {

        //获取商品数量
        final String numString = itemView.tv_num.getText().toString();

        //商品点击增加的操作监听
        itemView.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String add = data.get(position).getADDURL();
                Bundle bundle = new Bundle();
                bundle.putString("addUrl", add);
                mHandler.sendMessage(mHandler.obtainMessage(10, bundle));
            }
        });

        //商品点击减少的操作监听
        itemView.btn_reduce.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int a = Integer.valueOf(itemView.tv_num.getText().toString());
                if (a > 1){
                    String minus = data.get(position).getMINUSURL();
                    Bundle bundle = new Bundle();
                    bundle.putString("minusUrl", minus);
                    mHandler.sendMessage(mHandler.obtainMessage(11, bundle));
                }

            }
        });
    }
}
