package com.example.administrator.koudaiwanzi.person.point.pointmall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.person.mymeatball.MyMeatBallActivity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/09/29.
 */
public class MyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<YaoBean.HISLODRAWBean> data;
    private static String reward;

    public MyAdapter(Context context, ArrayList<YaoBean.HISLODRAWBean> data) {
        this.context = context;
        this.data = data;
    }

    public MyAdapter() {

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       MyViewHolder holder;
        if(view == null){
           view = LayoutInflater.from(context).inflate(R.layout.activity_adapter,null);
           holder = new MyViewHolder();
            holder.texxx = (TextView) view.findViewById(R.id.texxx);
            holder.text_count = (TextView) view.findViewById(R.id.text_count);

            view.setTag(holder);


       }else {
            holder = (MyViewHolder) view.getTag();
        }
        //获取用户姓名
       holder.texxx.setText(data.get(i).getNICKNAME());
        //获奖时间  后面的方法是把服务器的 字符串时间进行转码
        holder.text_count.setText(MyMeatBallActivity.getStringTime(data.get(i).getCREATTIME() + ""));

        if(data.size()>=2 &&data.get(i+1).getZID() != null){
            reward =data.get(i+1).getZID();
        }else {
            reward =null;
        }

      // reward =data.get(i).getZID();
        return view;
    }

    public static String getReward(){
        if(reward != null){
            return reward;
        }else {
            return "没有中奖请再接再厉";
        }

    }

    public class MyViewHolder{

        private TextView texxx,text_count;

    }

}
