package com.example.administrator.koudaiwanzi.classify;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.classification.ClassificationFragmentAdapter;
import com.example.administrator.koudaiwanzi.classification.brand.BrandFragment;
import com.example.administrator.koudaiwanzi.classification.type.TypeFragment;
import com.example.administrator.koudaiwanzi.home.search.SearchActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/18.
 */

@ContentView(R.layout.activity_classify)
public class ClassifyActivity extends AppCompatActivity{
    private ClassificationFragmentAdapter adapter;
    private List<Fragment> data;
    private String t ="6";

    @ViewInject(R.id.tabLayout_classification)
    private TabLayout tabLayout;

    @ViewInject(R.id.viewPager_classification)
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        data = new ArrayList<>();
        data.add(new TypeFragment());
        data.add(new BrandFragment());
        adapter = new ClassificationFragmentAdapter(getSupportFragmentManager(), data, ClassifyActivity.this);
        viewPager.setAdapter(adapter);
        tabLayout.setTabTextColors(Color.BLACK, Color.argb(0xff, 0xf1, 0x94, 0x83));
        tabLayout.setupWithViewPager(viewPager);

        Intent intent = getIntent();
        String a = intent.getStringExtra("fenlei");
        if (t.equals(a)){
            viewPager.setCurrentItem(1);

        }
    }

    //监听搜索按键
    @Event(R.id.ll_classification)
    private void etClick(View view){
        Intent intent = new Intent(ClassifyActivity.this, SearchActivity.class);
        startActivity(intent);
    }

    @Event(R.id.iv_back_classify)
    private void ivClick(View view){
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setContentView(R.layout.view_null);
    }
}
