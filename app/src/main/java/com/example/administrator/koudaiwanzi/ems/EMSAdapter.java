package com.example.administrator.koudaiwanzi.ems;

import android.content.Context;
import android.util.Log;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.CommonAdapter;
import com.example.administrator.koudaiwanzi.base.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 */
public class EMSAdapter extends CommonAdapter<EMSBean.DataBean>{

    public EMSAdapter(Context context, List<EMSBean.DataBean> data, int layoutId) {
        super(context, data, layoutId);
        Log.e("qwez",  data.size()+"");
    }


    @Override
    public void convert(ViewHolder holder, EMSBean.DataBean emsBean) {

        holder.setText(R.id.tv_ems_add, emsBean.getContext());
        holder.setText(R.id.tv_ems_time, emsBean.getFtime());

    }
}
