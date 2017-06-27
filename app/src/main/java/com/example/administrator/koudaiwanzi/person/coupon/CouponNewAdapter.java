package com.example.administrator.koudaiwanzi.person.coupon;

import android.content.Context;
import android.widget.ImageView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.CommonAdapter;
import com.example.administrator.koudaiwanzi.base.ViewHolder;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/09/21.
 * 未使用优惠券的适配器
 */
public class CouponNewAdapter extends CommonAdapter<CouponNewBean> {

    private  ArrayList<CouponNewBean> data;

    public CouponNewAdapter(Context context, java.util.List data, int layoutId) {
        super(context, data, layoutId);

    }

    @Override
    public void convert(final ViewHolder holder, CouponNewBean couponNewBean) {
        //如果是价格的话一定要 加“”
        holder.setText(R.id.coupon_count_price, couponNewBean.getCNPRICE()+"");
        //添加图片的方法
        ImageView imageView = holder.getView(R.id.ball_details_view);
       // x.image()

    }
}
