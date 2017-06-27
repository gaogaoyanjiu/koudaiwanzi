package com.example.administrator.koudaiwanzi.details;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/21.
 */
public class DetailsAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> data;
    private Context context;


    String title1 = "商品详情";
    String title2 = "买家评价";
    String title3 = "品牌";
    String[] titles = {title1, title2, title3};

    public DetailsAdapter(FragmentManager fm, ArrayList<Fragment> data, Context context) {
        super(fm);
        this.data = data;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
