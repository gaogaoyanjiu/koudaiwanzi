package com.example.administrator.koudaiwanzi.guidepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import com.example.administrator.koudaiwanzi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/25.
 */
public class GuidePageActivity extends AppCompatActivity{
    private ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewPager = (ViewPager) findViewById(R.id.vp_guide);
        GuideAdapter guideAdapter = new GuideAdapter(getSupportFragmentManager());
        viewPager.setPageTransformer(true,new TranslatePageTransformer());
        viewPager.setAdapter(guideAdapter);
    }

    class GuideAdapter extends FragmentStatePagerAdapter{
        private List<Fragment> datas;

        //构造方法
        public GuideAdapter(FragmentManager fm) {
            super(fm);
            datas = new ArrayList<>();
            datas.add(new GuideFragmentOne());
            datas.add(new GuideFragmentTwo());
            datas.add(new GuideFragmentThree());
        }

        @Override
        public Fragment getItem(int position) {
            return datas.get(position);
        }

        @Override
        public int getCount() {
            return datas == null ? 0 : datas.size();
        }
    }

}
