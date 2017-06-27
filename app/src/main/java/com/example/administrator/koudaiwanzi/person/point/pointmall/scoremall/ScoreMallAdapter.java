package com.example.administrator.koudaiwanzi.person.point.pointmall.scoremall;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class ScoreMallAdapter extends FragmentPagerAdapter {
    private List<Fragment> data;
    private Context context;
    private TextView title;
//    String title1 = "积分好礼";
//    String title2 = "积分服务";
    private List<String> datas;


    public ScoreMallAdapter(List<Fragment> data, Context context, FragmentManager fm) {
        super(fm);
        this.data = data;
        this.context = context;
        datas = new ArrayList<>();
        datas.add("积分好礼");
        datas.add("积分服务");
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.tablayout_score, null);
        title = (TextView) view.findViewById(R.id.tv_tablayout);
        title.setText(datas.get(position));

        return view;
    }
}
