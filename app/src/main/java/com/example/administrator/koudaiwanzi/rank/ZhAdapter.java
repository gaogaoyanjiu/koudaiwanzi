package com.example.administrator.koudaiwanzi.rank;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.example.administrator.koudaiwanzi.R;
import java.util.List;

/**
 * Created by Administrator on 2016/5/25.
 * 首页上面三个标题的适配器
 */
public class ZhAdapter extends FragmentPagerAdapter {

    private List<Fragment> data;
    private List<String> titles;
    private Context context;

    public ZhAdapter(FragmentManager fm, Context context, List<Fragment> data, List<String> titles) {
        super(fm);
        this.data = data;
        this.titles = titles;
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

    public View getTabView(int position){
        View view= LayoutInflater.from(context).inflate(R.layout.tab_home_title,null);
        TextView textView = (TextView) view.findViewById(R.id.tab_home_title_tv);
        textView.setText(titles.get(position));
        return view;
    }
}
