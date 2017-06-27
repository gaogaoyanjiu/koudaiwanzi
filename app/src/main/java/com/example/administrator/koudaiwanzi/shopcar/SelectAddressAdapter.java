package com.example.administrator.koudaiwanzi.shopcar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;

import java.util.List;

/**
 * Created by Administrator on 2016/8/23.
 */
public class SelectAddressAdapter extends BaseAdapter {
    private List<SelectAddressBean.ADDRESSBean> data;
    private Context context;

    public SelectAddressAdapter(Context context) {

        this.context = context;

    }
//        private void init() {
//        data = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            data.add("");
//        }
//    }
    public void addData(List<SelectAddressBean.ADDRESSBean> data){

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
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = null;
        if (convertView == null){
            myViewHolder = new MyViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_select_address,null);
            myViewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_manage_name);
            myViewHolder.tv_number = (TextView) convertView.findViewById(R.id.tv_manage_phone);
            myViewHolder.tv_province = (TextView) convertView.findViewById(R.id.tv_manage_province);
            myViewHolder.tv_city = (TextView) convertView.findViewById(R.id.tv_manage_city);
            myViewHolder.tv_area = (TextView) convertView.findViewById(R.id.tv_manage_area);
            myViewHolder.tv_street = (TextView) convertView.findViewById(R.id.tv_manage_street);

            convertView.setTag(myViewHolder);
        }


        else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        myViewHolder.tv_name.setText(data.get(position).getCONSIGNEE());
        myViewHolder.tv_number.setText(data.get(position).getTEL());
        if (data.get(position).getPROVINCE() == null){
            myViewHolder.tv_province.setText("");
        }else {
            myViewHolder.tv_province.setText(data.get(position).getPROVINCE());
        }
        if (data.get(position).getCITY() == null){
            myViewHolder.tv_city.setText("");
        }else {
            myViewHolder.tv_city.setText(data.get(position).getCITY());
        }
        if (data.get(position).getAREA() == null){
            myViewHolder.tv_area.setText("");
        }else {
            myViewHolder.tv_area.setText(data.get(position).getAREA());
        }
        myViewHolder.tv_street.setText(data.get(position).getFULLADRESS());
        return convertView;

    }

    class MyViewHolder{
        private TextView tv_name;
        private TextView tv_number;
        private TextView tv_province;
        private TextView tv_city;
        private TextView tv_area;
        private TextView tv_street;

    }
}
