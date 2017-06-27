package com.example.administrator.koudaiwanzi.details.evaluate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/6/21.
 *
 */
public class EvaluateAdapter extends BaseAdapter {
    private Context context;
    private List<CommentBean.COMITEMSBean> data;

    public EvaluateAdapter (Context context){
        this.context = context ;

    }
    public  void addData(List<CommentBean.COMITEMSBean> data){
        this.data = data;
        notifyDataSetChanged();

    }
    @Override
    public int getCount() {
        return data.size() > 0 && data != null?data.size():0 ;
    }

    @Override
    public Object getItem(int position) {
        return  data.size() > 0 && data != null?data.size():0 ;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_evaluate,null);
            myViewHolder.tv = (TextView) convertView.findViewById(R.id.tv_evaluate_name);
            myViewHolder.iv = (ImageView) convertView.findViewById(R.id.iv_evaluate_head);
            myViewHolder.tv1 = (TextView) convertView.findViewById(R.id.tv_item_evaluate);
            myViewHolder.tv2 = (TextView) convertView.findViewById(R.id.tv_evaluate_time);
            convertView.setTag(myViewHolder);
        }

        else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        myViewHolder.tv.setText(data.get(position).getNICKNAME()+"");
//        x.image().bind(myViewHolder.iv,"http://192.168.1.203"+data.get(position).getHEADURL());
        myViewHolder.tv1.setText(data.get(position).getCOMMENTNOTE()+"");
        String result = formatData("yyyy.MM.dd", data.get(position).getCREATETIME());

        myViewHolder.tv2.setText(result+"");
        return convertView;
    }

    class MyViewHolder{
        private TextView tv,tv1,tv2;
        private ImageView iv;
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
