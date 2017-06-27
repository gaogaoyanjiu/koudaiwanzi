package com.example.administrator.koudaiwanzi.person.mymeatball;

import android.content.Context;
import android.widget.ImageView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.CommonAdapter;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.base.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2016/8/2.
 * 我的丸子说适配器
 */
public class MyMeatBallAdapter extends CommonAdapter<MyNewMeatBallBean.StoryBean>{


    private Context con;
    public MyMeatBallAdapter(Context context, List<MyNewMeatBallBean.StoryBean> data, int layoutId) {
        super(context, data, layoutId);
        con = context;

    }


    @Override
    public void convert(ViewHolder holder, MyNewMeatBallBean.StoryBean storyBean) {

        ImageView imageView = holder.getView(R.id.wanzishuo_iv);
        //x.image().bind(imageView, MyUrl.url + storyBean.getPREVIEW());
        //设置显示的图片
        String c = MyUrl.url + storyBean.getPREVIEW();
        Picasso.with(con).load(c).fit().into(imageView);

        //设置用户名和 发布的内容
        holder.setText(R.id.wanzishuo_content,storyBean.getNOTES());
        holder.setText(R.id.UserName,MyMeatBallActivity.getName());
        //设置头像的圆形图片
        CircleImageView imageView2 = holder.getView(R.id.my_wanzishuo_head);
        String b = MyUrl.url + MyMeatBallActivity.getUrl();
        Picasso.with(con).load(b).into(imageView2);
        //设置时间
        String time =MyMeatBallActivity.getStringTime(storyBean.getCREATETIME() + "");
        holder.setText(R.id.list_item_time,time);

    }
}
