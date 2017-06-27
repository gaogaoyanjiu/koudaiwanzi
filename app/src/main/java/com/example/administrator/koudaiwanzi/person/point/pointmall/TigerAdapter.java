package com.example.administrator.koudaiwanzi.person.point.pointmall;

import android.content.Context;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.CommonAdapter;
import com.example.administrator.koudaiwanzi.base.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/9/22.
 */
public class TigerAdapter extends CommonAdapter<TigerMachineBean.HISDRAWBean>{
    public TigerAdapter(Context context, List<TigerMachineBean.HISDRAWBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, TigerMachineBean.HISDRAWBean hisdrawBean) {
        holder.setText(R.id.tv_tiger_name, hisdrawBean.getNICKNAME());
        holder.setText(R.id.tv_tiger_product, hisdrawBean.getZID());
    }
}
