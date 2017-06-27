package com.example.administrator.koudaiwanzi.details.detail.size;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;

import java.util.List;

/**
 * Created by Administrator on 2016/6/13.
 */
public class SkuAdapter extends BaseAdapter {
    private Context context;
    private List<Bean> list;
    private onItemClickListener itemClickListener; //接口回调

    public void setItemClickListener(onItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public SkuAdapter(Context context, List<Bean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.gridview_item,parent,false);
            holder.title = (TextView) convertView.findViewById(R.id.ItemText);
            holder.layout = (LinearLayout) convertView.findViewById(R.id.layout);
            convertView.setTag(holder);// 绑定ViewHolder对象
        }else {
            holder = (ViewHolder) convertView.getTag();// 取出ViewHolder对象
        }

        final Bean bean = list.get(position);

        switch (bean.getStates()){
            //选中
            case "0":
                holder.layout.setBackgroundResource(R.drawable.shape2);
                holder.title.setTextColor(Color.WHITE);
                break;
            //未选中
            case "1":
                holder.layout.setBackgroundResource(R.drawable.shape1);
                holder.title.setTextColor(Color.BLACK);
                break;

            // 不可选
            case "2":
                holder.layout.setBackgroundResource(R.drawable.shape1);
                holder.title.setTextColor(Color.parseColor("#999999"));
                break;
            default:
                break;

        }

        /** 设置TextView显示的内容，即我们存放在动态数组中的数据 */
        holder.title.setText(bean.getName());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null){
                    if (!bean.getStates().equals("2")){
                        itemClickListener.onItemClick(bean,position);
                    }
                }
            }
        });

        return convertView;
    }

    public final class ViewHolder {
        public TextView title;
        public LinearLayout layout;
    }

    public interface onItemClickListener {
        public void onItemClick(Bean bean, int position);
    }

}
