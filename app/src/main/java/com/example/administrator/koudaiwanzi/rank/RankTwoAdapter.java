package com.example.administrator.koudaiwanzi.rank;

import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import org.xutils.x;
import java.util.List;
import static com.example.administrator.koudaiwanzi.R.id.tv_top_original_cost;

/**
 * Created by Administrator on 2016/8/29.
 */
public class RankTwoAdapter extends BaseAdapter {
    private Context context;
    private List<RankBean.TOPITEMSBean> data;
    private Handler handler;
    private Integer[] imgs = {R.mipmap.top1, R.mipmap.top2, R.mipmap.top3, R.mipmap.top4, R.mipmap.top5,
            R.mipmap.top6,R.mipmap.top7,R.mipmap.top8,R.mipmap.top9,R.mipmap.top10};

    public RankTwoAdapter(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

   public void addData(List<RankBean.TOPITEMSBean> data){
       this.data = data;
   }


    @Override
    public int getCount() {
        return data.size()!= 0&& data!= null ? data.size() : 0;
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
        final int mPosition = position;
        if (convertView == null){
            myViewHolder = new MyViewHolder(position);
            convertView = LayoutInflater.from(context).inflate(R.layout.item_top,null);
            myViewHolder.iv = (ImageView) convertView.findViewById(R.id.iv_top_product);
            myViewHolder.iv_shop = (ImageView) convertView.findViewById(R.id.iv_top_shop);
            myViewHolder.tv = (TextView) convertView.findViewById(tv_top_original_cost);
            myViewHolder.tv1 = (TextView) convertView.findViewById(R.id.tv_top_price);
            myViewHolder.tv2 = (TextView) convertView.findViewById(R.id.tv_top_brand);
            myViewHolder.tv3 = (TextView) convertView.findViewById(R.id.tv_top_name);
            myViewHolder.tv4 = (TextView) convertView.findViewById(R.id.tv_top_size);
            myViewHolder.tv5 = (TextView) convertView.findViewById(R.id.tv_top_ISOUTSTOCK);
            myViewHolder.iv_tag = (ImageView) convertView.findViewById(R.id.iv_top_tag);
            convertView.setTag(myViewHolder);
        }else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        if (data.get(position).getDISCOUNT() == 0){
               myViewHolder.tv1.setText("¥" + data.get(position).getPRICE());
               myViewHolder.tv.setVisibility(View.INVISIBLE);
        }else {
            myViewHolder.tv.setVisibility(View.VISIBLE);
            myViewHolder.tv1.setText("¥" + data.get(position).getBARGAIN());
            myViewHolder.tv.setText("¥" + data.get(position).getPRICE());
            //给textView中间加横线
            myViewHolder.tv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        }

       if (data.get(position).getISOUTSTOCK() == 0){
           myViewHolder.tv5.setVisibility(View.GONE);
       }else {
           myViewHolder.tv5.setVisibility(View.VISIBLE);
       }

        myViewHolder.tv3.setText(data.get(position).getTRADENAME());
        myViewHolder.tv2.setText(data.get(position).getBONAME());

        if (data.get(position).getSTANDARD() == null || data.get(position).getSTANDARD().equals(""))
        {
            myViewHolder.tv4.setVisibility(View.INVISIBLE);
        }else {
            myViewHolder.tv4.setText(data.get(position).getSTANDARD()+"");
        }

        x.image().bind(myViewHolder.iv, MyUrl.url+data.get(position).getICOURL());
        //加入top标签
        myViewHolder.iv_tag.setImageResource(imgs[position]);

        myViewHolder.iv_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.get(position).getISOUTSTOCK() == 0){
                    handler.sendMessage(handler.obtainMessage(30, data.get(position).getDetailUrl()));
                }else {
                    Toast.makeText(context, "商品缺货", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return convertView;
    }
    class MyViewHolder{
        private int pos;
        private ImageView iv,iv_shop, iv_tag;
        private TextView tv,tv1,tv2,tv3,tv4,tv5,tv6;
        public MyViewHolder(int pos) {
            this.pos = pos;
        }

    }
}
