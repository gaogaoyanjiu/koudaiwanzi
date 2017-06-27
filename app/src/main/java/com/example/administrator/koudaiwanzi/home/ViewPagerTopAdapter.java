package com.example.administrator.koudaiwanzi.home;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/07/11.
 */
public class ViewPagerTopAdapter extends PagerAdapter {

    private ArrayList<ImageView> imageList;
    private Context context;

    public ViewPagerTopAdapter(ArrayList<ImageView> imageList, Context context) {
        this.imageList = imageList;
        this.context = context;
    }

    @Override
    /**
     * 获得页面的总数
     */
    public int getCount() {
        return Integer.MAX_VALUE; // 使得图片可以循环
    }


    @Override
    /**
     * 获得相应位置上的view
     * container  view的容器，其实就是viewpager自身
     * position     相应的位置
     */
    public Object instantiateItem(ViewGroup container, int position) {
        // 给 container 添加一个view
        //container.removeView(imageList.get(position % imageList.size()));
        container.removeView(imageList.get(position % imageList.size()));


        container.addView(imageList.get(position % imageList.size()));
        // 返回一个和该view相对的object
        return imageList.get(position % imageList.size());
    }

    @Override
    /**
     * 判断 view和object的对应关系
     */
    public boolean isViewFromObject(View view, Object object) {
        if (view == object) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    /**
     * 销毁对应位置上的object
     */
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        object = null;
    }
}
