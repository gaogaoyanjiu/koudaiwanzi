package com.example.administrator.koudaiwanzi.details.detail.size;

import android.content.Context;
import android.widget.LinearLayout;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.CommonAdapter;
import com.example.administrator.koudaiwanzi.base.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/6/24.
 */
public class SizeAdapter extends CommonAdapter<SizeBean.STANDARDSBean> {
    private List<SizeBean.STANDARDSBean> data;
    int a = 0;

    public SizeAdapter(Context context, List<SizeBean.STANDARDSBean> data, int layoutId) {
        super(context, data, layoutId);
        this.data = data;
    }

    @Override
    public void convert(ViewHolder holder, SizeBean.STANDARDSBean standardsBean) {
        holder.setText(R.id.tv_size, standardsBean.getSTANDARD());
        final LinearLayout layout = holder.getView(R.id.layout_size);

        if (a == 0) {
            data.get(0).setNameIsSelect(true);
        }
        a++;

        // 点击改变选中listItem的背景色
        if (standardsBean.getNameIsSelect()) {
            layout.setBackgroundResource(R.drawable.shape2);

        } else {
            layout.setBackgroundResource(R.drawable.shape1);
        }


    }

}
