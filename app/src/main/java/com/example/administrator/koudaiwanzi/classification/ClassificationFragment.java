package com.example.administrator.koudaiwanzi.classification;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.BaseFragment;
import com.example.administrator.koudaiwanzi.classification.brand.BrandFragment;
import com.example.administrator.koudaiwanzi.classification.type.TypeFragment;
import com.example.administrator.koudaiwanzi.home.search.SearchActivity;


import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 */

@ContentView(R.layout.fragment_classificatin)
public class ClassificationFragment extends BaseFragment {
    private boolean isVisible = false;//当前Fragment是否可见
    private boolean isFirstLoad = true;//是否是第一次加载数据
    private boolean isInitView = false;//是否与View建立起映射关系

    private ClassificationFragmentAdapter adapter;
    private List<Fragment> data;
    private String t ="6";

    @ViewInject(R.id.tabLayout_classification)
    private TabLayout tabLayout;

    @ViewInject(R.id.viewPager_classification)
    private ViewPager viewPager;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        isInitView = true;
        lazyLoadData();

    }

    //监听搜索按键
    @Event(R.id.ll_classification)
    private void etClick(View view){
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            isVisible = true;
            lazyLoadData();

        } else {
            isVisible = false;
        }
        super.setUserVisibleHint(isVisibleToUser);
    }


    private void lazyLoadData() {
        initData();
        isFirstLoad = false;
    }

    private void initData(){
        data = new ArrayList<>();
        data.add(new TypeFragment());
        data.add(new BrandFragment());
        adapter = new ClassificationFragmentAdapter(getFragmentManager(),data,getActivity());
        viewPager.setAdapter(adapter);
        tabLayout.setTabTextColors(Color.BLACK, Color.argb(0xff, 0xf1, 0x94, 0x83));
        tabLayout.setupWithViewPager(viewPager);

        Intent intent = getActivity().getIntent();
        String a = intent.getStringExtra("fenlei");
        if (t.equals(a)){
            viewPager.setCurrentItem(1);
        }
    }

}
