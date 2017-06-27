package com.example.administrator.koudaiwanzi.home.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.administrator.koudaiwanzi.R;

import java.util.List;

/**
 * Created by Administrator on 2016/07/top1.
 */
public class ListViewAdapter extends BaseAdapter {

    private Context context;
    private List<SearchBean> datas;
    private List<String> karidatas;

    public ListViewAdapter(Context context, List<SearchBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    public ListViewAdapter() {
        super();
    }


    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_search_listview,null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.search_lv_tv);
            viewHolder.textView.setText(datas.get(position).getTRADENAME());
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
       // viewHolder.textView.setText(datas.get(position).getTRADENAME());
        return convertView;
    }

    public  class ViewHolder{
        private TextView textView;


    }
}
