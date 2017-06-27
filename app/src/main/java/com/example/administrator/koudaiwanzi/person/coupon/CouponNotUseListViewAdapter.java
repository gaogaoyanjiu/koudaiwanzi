package com.example.administrator.koudaiwanzi.person.coupon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;

import java.util.List;

/**
 * Created by Administrator on 2016/6/24.
 */
public class CouponNotUseListViewAdapter extends BaseAdapter{
    private Context context;
    private List<CouponNotUsedBean.CPBean> data;

    public CouponNotUseListViewAdapter(Context context) {
        this.context = context;
    }

    public void addData(List<CouponNotUsedBean.CPBean> data){
        this.data = data;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = null;
        if (convertView == null){
            myViewHolder = new MyViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.coupon_not_use_listview_item,null);
           myViewHolder.coupon_count = (TextView) convertView.findViewById(R.id.coupon_count);
            myViewHolder.all_things_tv = (TextView) convertView.findViewById(R.id.all_things_tv);
            myViewHolder.enough_money_can_use = (TextView) convertView.findViewById(R.id.enough_money_can_use);
            myViewHolder.coupon_date = (TextView) convertView.findViewById(R.id.coupon_date);
            convertView.setTag(myViewHolder);

        }else {
            myViewHolder = (MyViewHolder) convertView.getTag();

        }
        myViewHolder.coupon_count.setText(data.get(position).getCNPRICE()+"");
        myViewHolder.all_things_tv.setText(data.get(position).getCNNAME()+"");
        myViewHolder.coupon_date.setText(data.get(position).getPEROFVAL()+"");
        return convertView;
    }

    class MyViewHolder {
        private TextView coupon_count;
        private TextView all_things_tv;
        private TextView enough_money_can_use;
        private TextView coupon_date;
    }
}
