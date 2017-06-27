package com.example.administrator.koudaiwanzi.person.point.exchangticket;

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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/6/3.
 */
public class ExchangeAdapter extends BaseAdapter{
    private Context context;
    private List<ExchangeBean> data;

    public ExchangeAdapter(Context context) {
        this.context = context;
    }

    public void addData(List<ExchangeBean> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size() != 0 && data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return data.size() != 0 && data != null ? data.size() : 0;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.exchange_ticket_item,null);
            myViewHolder.tv_1 = (TextView) convertView.findViewById(R.id.exchange_ticket_listview_title);
            myViewHolder.tv_2 = (TextView) convertView.findViewById(R.id.exchange_ticket_listview_item_point);
            myViewHolder.tv_3 = (TextView) convertView.findViewById(R.id.exchange_count_number);
            myViewHolder.tv_4 = (TextView) convertView.findViewById(R.id.exchange_time_date);
            myViewHolder.iv = (ImageView) convertView.findViewById(R.id.exchange_ticket_listview_item_iv);


            convertView.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        String result = formatData("yyyy/MM/dd", data.get(position).getCREATETIME());
        myViewHolder.tv_4.setText(result);
        myViewHolder.tv_1.setText(data.get(position).getCANAME());
        myViewHolder.tv_2.setText(data.get(position).getPOINTS()+"");
        myViewHolder.tv_3.setText(data.get(position).getQUANTITY()+"");
        x.image().bind(myViewHolder.iv, MyUrl.url+data.get(position).getICOURL());
        return convertView;
    }

    class MyViewHolder{
       TextView tv_1,tv_2,tv_3,tv_4;
        ImageView iv;
    }

    public static String formatData(String dataFormat, long timeStamp) {
        if (timeStamp == 0) {
            return "";
        }
        timeStamp = timeStamp * 1000;
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        result = format.format(new Date(timeStamp));
        return result;
    }

}
