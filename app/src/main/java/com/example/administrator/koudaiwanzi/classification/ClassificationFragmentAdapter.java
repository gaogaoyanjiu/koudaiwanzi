package com.example.administrator.koudaiwanzi.classification;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.koudaiwanzi.classify.ClassifyActivity;

import java.util.List;

/**
 * Created by liu on 2016/5/11.
 */
public class ClassificationFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> data;
    private Context context;

    String title1 = "分类";
    String title2 = "品牌";


    String[] titles = {title1,title2};

    public ClassificationFragmentAdapter(FragmentManager fm, List<Fragment> data, Context context) {
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
