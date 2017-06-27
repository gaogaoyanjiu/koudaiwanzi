package com.example.administrator.koudaiwanzi.rank;

import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.CommonAdapter;
import com.example.administrator.koudaiwanzi.base.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */

public class RankAdapter extends CommonAdapter<RankBean.TOPITEMSBean>{
    private Integer[] imgs = {R.mipmap.top1, R.mipmap.top2, R.mipmap.top3, R.mipmap.top4, R.mipmap.top5,
            R.mipmap.top6,R.mipmap.top7,R.mipmap.top8,R.mipmap.top9,R.mipmap.top10};
    private Handler handler;


    public RankAdapter(Context context, List<RankBean.TOPITEMSBean> data, int layoutId, Handler handler) {
        super(context, data, layoutId);
        this.handler = handler;
    }

    @Override
    public void convert(ViewHolder holder, RankBean.TOPITEMSBean topitemsBean) {
        TextView textView = holder.getView(R.id.tv_top_original_cost);

        if (topitemsBean.getDISCOUNT() == 0){
            holder.setText(R.id.tv_top_price, "¥" + topitemsBean.getPRICE());
            textView.setVisibility(View.GONE);
        }else {
            holder.setText(R.id.tv_top_price,"¥" + topitemsBean.getBARGAIN());
            holder.setText(R.id.tv_top_original_cost,"¥" + topitemsBean.getPRICE());
            //给textView中间加横线
            textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}
