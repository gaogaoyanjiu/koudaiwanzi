package com.example.administrator.koudaiwanzi.person.order;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/6/7.
 */
public class OrderAdapter extends FragmentPagerAdapter {

    private List<Fragment> data;
    private Context context;

    String title1 = "全部";
    String title2 = "待付款";
    String title3 = "待发货";
    String title4 = "待收货";
    String title5 = "待评价";

    String[] titles = {title1, title2, title3, title4, title5};

    public OrderAdapter(FragmentManager fm, List<Fragment> data, Context context) {
        super(fm);
        this.data = data;
        this.context = context;
    }


    public Fragment getItem(int position) {
        return data.get(position);
    }


    public int getCount() {
        return data.size();
    }


    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
