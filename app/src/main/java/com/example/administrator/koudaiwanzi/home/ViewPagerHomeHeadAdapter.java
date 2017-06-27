package com.example.administrator.koudaiwanzi.home;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/6/21.
 * ViewPager 的适配器
 */
public class ViewPagerHomeHeadAdapter extends PagerAdapter {
    private List<View> data;
    private Context context;

    public ViewPagerHomeHeadAdapter(Context context) {
        this.context = context;

    }
    public void addData(List<View> data){
        this.data = data;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        if (view == object) {
            return true;
        } else {
            return false;
        }
    }


    //是从ViewGroup中移出当前View
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//       container.removeView(data.get(position));
        container.removeView((View) object);
        object = null;
    }

    //返回一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.removeView(data.get(position % data.size()));

        container.addView(data.get(position));

        return data.get(position);

    }
}
