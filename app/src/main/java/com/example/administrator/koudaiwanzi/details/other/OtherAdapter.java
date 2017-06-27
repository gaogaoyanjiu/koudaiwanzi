package com.example.administrator.koudaiwanzi.details.other;

import android.content.Context;
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
 * Created by Administrator on 2016/6/21.
 *
 */
public class OtherAdapter extends BaseAdapter {
    private Context context;
    private List<OtherBean> data;
    String a = MyUrl.url;

    public OtherAdapter(Context context){
        this.context = context ;

    }
    public  void addData(List<OtherBean> data){
        this.data = data;
        notifyDataSetChanged();

    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return  data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = null;
        if(convertView == null){
            myViewHolder = new MyViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.mycollection_recyclerview_item,null);
            myViewHolder.tv_name1 = (TextView) convertView.findViewById(R.id.mycollection_item_tv1);
            myViewHolder.iv_p1 = (ImageView) convertView.findViewById(R.id.mycollection_item_iv1);
            myViewHolder.tv_price1 = (TextView) convertView.findViewById(R.id.mycollection_item_newprice1);
            myViewHolder.tv_priceOld1 = (TextView) convertView.findViewById(R.id.mycollection_item_oldprice1);
            myViewHolder.tv_ISOUTSTOCK = (TextView) convertView.findViewById(R.id.tv_ISOUTSTOCK);
            convertView.setTag(myViewHolder);
        }

        else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        if (data.get(position).getDISCOUNT() == 0){
            myViewHolder.tv_price1.setText(data.get(position).getPRICE()+"");
            myViewHolder.tv_priceOld1.setVisibility(View.INVISIBLE);
        }else {
            myViewHolder.tv_priceOld1.setText(data.get(position).getPRICE()+"");
            myViewHolder.tv_price1.setText(data.get(position).getBARGAIN()+"");
        }
        if (data.get(position).getISOUTSTOCK() == 0){
            myViewHolder.tv_ISOUTSTOCK.setVisibility(View.GONE);
        }else {
            myViewHolder.tv_ISOUTSTOCK.setVisibility(View.VISIBLE);
        }


        myViewHolder.tv_name1.setText(data.get(position).getTRADENAME());
        x.image().bind(myViewHolder.iv_p1, a + data.get(position).getICOURL());

        return convertView;
    }

    class MyViewHolder{
        private TextView tv_name1,tv_price1,tv_priceOld1,tv_ISOUTSTOCK;
        private ImageView iv_p1;
    }
}
