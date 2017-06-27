package com.example.administrator.koudaiwanzi.person.point;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.administrator.koudaiwanzi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/3.
 */
public class MyPointAdapter extends BaseAdapter{
    private List<String> data;
    private Context context;


    public MyPointAdapter(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("COSME DECORTE 黛珂洗颜乳");
        }
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
            convertView = LayoutInflater.from(context).inflate(R.layout.my_point_listview_item,null);
            convertView.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        return convertView;
    }

    class MyViewHolder{

    }

}
