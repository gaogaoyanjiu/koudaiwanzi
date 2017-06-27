package com.example.administrator.koudaiwanzi.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/7/4.
 */
public class CommonAdapterRV<T> extends RecyclerView.Adapter<ViewHolderRV> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public CommonAdapterRV(Context context, int layoutId, List<T> data) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = data;
    }

    @Override
    public ViewHolderRV onCreateViewHolder(final ViewGroup parent, int viewType) {
        ViewHolderRV viewHolder = ViewHolderRV.createViewHolder(mContext, parent, mLayoutId);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderRV holder, int position) {
        convert(holder, mDatas.get(position));
        holder.setPos(position);
    }

    public void convert(ViewHolderRV holder, T t) {

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
