package com.example.administrator.koudaiwanzi.product;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.CommonAdapter;
import com.example.administrator.koudaiwanzi.base.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/7/21.
 */
public class ShowAdapter extends CommonAdapter<FilterBean.ItemsBean> {

    public ShowAdapter(Context context, List<FilterBean.ItemsBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, FilterBean.ItemsBean itemsBean) {
        holder.setText(R.id.tv_show, itemsBean.getCODENAME());
        final RelativeLayout layout = holder.getView(R.id.rl_show);
        // 点击改变选中listItem的背景色
        if (itemsBean.getNameIsSelect()) {
            layout.setBackgroundResource(R.drawable.shape_all_read2);
        } else {
            layout.setBackgroundResource(R.drawable.all_red);
        }


    }

}
