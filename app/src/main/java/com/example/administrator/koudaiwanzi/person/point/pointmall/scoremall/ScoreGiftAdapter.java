package com.example.administrator.koudaiwanzi.person.point.pointmall.scoremall;

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
 * Created by Administrator on 2016/7/6.
 */
public class ScoreGiftAdapter extends BaseAdapter {
    private Context context;
    private List<ScoreBean.ITEMSBean> data;

    public ScoreGiftAdapter(Context context) {
        this.context = context;
//        init();
    }
//
//    private void init() {
//        data = new ArrayList<>();
//        for (int i = 0; i <10 ; i++) {
//            data.add("");
//
//        }
//    }
    public void addData(List<ScoreBean.ITEMSBean> data){
        this.data = data;
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
        if(myViewHolder == null){
            myViewHolder = new MyViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_score_mall_one,null);
            myViewHolder.tv1 = (TextView) convertView.findViewById(R.id.tv_mall_t3);
            myViewHolder.tv2 = (TextView) convertView.findViewById(R.id.tv_mall_price3);
            myViewHolder.tv3 = (TextView) convertView.findViewById(R.id.tv_mall_post3);
            myViewHolder.iv = (ImageView) convertView.findViewById(R.id.iv_mall_p3);
            convertView.setTag(myViewHolder);
        }
        else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        myViewHolder.tv1.setText(data.get(position).getCANAME());
        myViewHolder.tv2.setText(data.get(position).getPOINTS()+"");
        myViewHolder.tv3.setText("兑换后支付"+data.get(position).getPOSTAGE()+"元邮费");
        x.image().bind(myViewHolder.iv, MyUrl.url + data.get(position).getICOURL());
        return convertView;


    }
    class MyViewHolder{
         TextView tv1,tv2,tv3;
        ImageView iv;
    }
}
