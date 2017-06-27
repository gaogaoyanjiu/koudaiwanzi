package com.example.administrator.koudaiwanzi.home.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.home.entity.greendao.HistoryEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/07/02.
 */
public class HistoryListViewAdapter extends BaseAdapter {

    private Context context;
    private List<HistoryEntity> historis;







    public HistoryListViewAdapter(Context context, List<HistoryEntity> historis) {
        this.context = context;
        this.historis = historis;
        notifyDataSetChanged();
    }

    public HistoryListViewAdapter() {
        super();
    }

    @Override
    public int getCount() {
        return historis.size();
    }
public void addData(List<HistoryEntity> historis){
    this.historis = historis;
    notifyDataSetChanged();
}
    @Override
    public Object getItem(int position) {
        return historis.get(position);
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
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // viewHolder.textView.setText(datas.get(position).getTRADENAME());
        viewHolder.textView.setText(historis.get(position).getContent());
        return convertView;
    }

    public  class ViewHolder{
        private TextView textView;
    }
}
