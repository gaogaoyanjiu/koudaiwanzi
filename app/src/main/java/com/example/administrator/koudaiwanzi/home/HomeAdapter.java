package com.example.administrator.koudaiwanzi.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.details.DetailsActivity;
import com.example.administrator.koudaiwanzi.product.ShowProductActivity;

import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 */
public class HomeAdapter extends BaseAdapter{
    private List<HomeBean.ItemsBean> data;
    private Context context;
    final int TYPE_1 = 0;
    final int TYPE_2 = 1;
    final int TYPE_3 = 2;

    public HomeAdapter(Context context) {
        this.context = context;
    }

    public void addData(List<HomeBean.ItemsBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        int a = position;
        if (a < 4) {
            return TYPE_1;
        } else if (a == 4) {
            return TYPE_1;
        } else {
            return TYPE_1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getCount() {
        return data.size() > 0 && data != null ? data.size() : 0;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = null;
        MyViewHolder2 myViewHolder2 = null;
        MyViewHolder3 myViewHolder3 = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {

                //一个大图6个小图
                case TYPE_1:
                    myViewHolder = new MyViewHolder();
                    convertView = LayoutInflater.from(context).inflate(R.layout.home_listview_item, null);
//                    myViewHolder.tv_name = (TextView) convertView.findViewById(R.id.listview_bottom1_tv);
//                    myViewHolder.tv_name_two = (TextView) convertView.findViewById(R.id.listview_bottom2_tv);
//                    myViewHolder.iv_big = (ImageView) convertView.findViewById(R.id.home_listview_top);
//                    myViewHolder.iv_one = (ImageView) convertView.findViewById(R.id.listview_bottom1);
//                    myViewHolder.iv_two = (ImageView) convertView.findViewById(R.id.listview_bottom2);

                    //小图的监听跳转到商品详情页
                    myViewHolder.iv_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, DetailsActivity.class);
                            context.startActivity(intent);
                        }
                    });

                    //大图的监听跳转到商品筛选页
                    myViewHolder.iv_big.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, ShowProductActivity.class);
                            intent.putExtra("where","3");
                            context.startActivity(intent);
                        }
                    });
                    convertView.setTag(myViewHolder);
                    break;

                //品牌
                case TYPE_2:
                    myViewHolder2 = new MyViewHolder2();
                    convertView = LayoutInflater.from(context).inflate(R.layout.home_listview_item2, parent, false);
                    myViewHolder2.gridLayout = (GridLayout) convertView.findViewById(R.id.gl_home);

                    for (int i = 0; i < 24; i++) {
                        View view = LayoutInflater.from(context).inflate(R.layout.item_gridlayout, null);
                        myViewHolder2.gridLayout.addView(view);
                    }
                    convertView.setTag(myViewHolder2);
                    break;

                //丸子说
                case TYPE_3:
                    myViewHolder3 = new MyViewHolder3();
                    convertView = LayoutInflater.from(context).inflate(R.layout.home_listview_item3, null);
                    convertView.setTag(myViewHolder3);
                    break;
            }
        } else {
            switch (type) {
                case TYPE_1:
                    myViewHolder = (MyViewHolder) convertView.getTag();
                    break;
                case TYPE_2:
                    myViewHolder2 = (MyViewHolder2) convertView.getTag();
                    break;
                case TYPE_3:
                    myViewHolder3 = (MyViewHolder3) convertView.getTag();
                    break;
            }
        }

        //加入资源
        switch (type) {

            case TYPE_1:
                String a = MyUrl.url;
                String b = data.get(position).getURL();
                String c = a + b;
                String q = "data.get(position).getCateItems()";


                if (position == 0) {
                    String b1 = data.get(position).getCateItems().get(position).getICOURL();
                    String c1 = a + b1;
                    x.image().bind(myViewHolder.iv_big, c);
                    x.image().bind(myViewHolder.iv_one, c1);

                    myViewHolder.iv_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context,DetailsActivity.class);
                            intent.putExtra("key", data.get(position).getCateItems().get(position).getDetailUrl());
                            context.startActivity(intent);
                        }
                    });

                    myViewHolder.tv_name.setText(data.get(position).getCateItems().get(position).getTRADENAME());
                } else if (position == 1) {
                    String b1 = data.get(position).getCateItems().get(position - 1).getICOURL();
                    String b2 = data.get(position).getCateItems().get(position).getICOURL();
                    String c1 = a + b1;
                    String c2 = a + b2;
                    x.image().bind(myViewHolder.iv_big, c);
                    x.image().bind(myViewHolder.iv_one, c1);
                    x.image().bind(myViewHolder.iv_two, c2);
                    myViewHolder.tv_name.setText(data.get(position).getCateItems().get(position - 1).getTRADENAME());
                    myViewHolder.tv_name_two.setText(data.get(position).getCateItems().get(position).getTRADENAME());

                    myViewHolder.iv_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context,DetailsActivity.class);
                            intent.putExtra("key", data.get(position).getCateItems().get(position-1).getDetailUrl());
                            context.startActivity(intent);
                        }
                    });


                    myViewHolder.iv_two.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context,DetailsActivity.class);
                            intent.putExtra("key", data.get(position).getCateItems().get(position).getDetailUrl());
                            context.startActivity(intent);
                        }
                    });


                }

                break;

            case TYPE_2:

                break;

            case TYPE_3:

                break;
        }
        return convertView;
    }



    class MyViewHolder {
        private TextView tv_name, tv_name_two;
        private ImageView iv_big;
        private ImageView iv_one;
        private ImageView iv_two;
    }

    class MyViewHolder2 {
        private GridLayout gridLayout;
    }

    class MyViewHolder3 {

    }
}
