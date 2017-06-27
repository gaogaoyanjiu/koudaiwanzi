package com.example.administrator.koudaiwanzi.person.order;

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

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/7.
 */

@ContentView(R.layout.acitivity_order)
public class OrderAllActivity extends AppCompatActivity {
    private OrderAdapter adapter;
    private List<Fragment> data;

    //tabLayout绑定ID
    @ViewInject(R.id.tabLayout_order)
    private TabLayout tabLayout;

    //viewPager绑定ID
    @ViewInject(R.id.viewPager_order)
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        data = new ArrayList<>();
        data.add(new AllFragment());
        data.add(new PayFragment());
        data.add(new SendFragment());
        data.add(new TakeFragment());
        data.add(new OkFragment());
        adapter = new OrderAdapter(getSupportFragmentManager(),data,OrderAllActivity.this);
        viewPager.setAdapter(adapter);

        Intent intent = getIntent();
        int a = intent.getIntExtra("order", 0);
        if (a == 1){
            viewPager.setCurrentItem(1);
        }else if (a == 2){
            viewPager.setCurrentItem(2);
        }else if (a == 3){
            viewPager.setCurrentItem(3);
        }else if (a == 4){
            viewPager.setCurrentItem(4);
        }
        tabLayout.setTabTextColors(Color.BLACK, Color.argb(0xff, 0xf1, 0x94, 0x83));
        tabLayout.setupWithViewPager(viewPager);

    }

    //返回按键
    @Event(R.id.iv_order_back)
    private void imgClick(View view){
        finish();
    }
}
