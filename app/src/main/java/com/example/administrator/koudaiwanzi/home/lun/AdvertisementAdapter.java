package com.example.administrator.koudaiwanzi.home.lun;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.base.NoDoubleClickListener;
import com.example.administrator.koudaiwanzi.home.HomeBean;
import com.example.administrator.koudaiwanzi.newsell.NewSellActivity;
import com.example.administrator.koudaiwanzi.selling.SellActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


/**
 * 广告轮播adapter
 *
 * @author dong
 * @data 2015年3月8日下午3:46:35
 * @contance dong854163@163.com
 */
public class AdvertisementAdapter extends PagerAdapter {
    private String url2 = MyUrl.url + "Items.svc/home/";
    private String url;
    private String UID, detailUrl, detaiUrl2;
    private Context context;
    private List<Integer> num;
    private List<View> views;
    private List<String> data;
    JSONArray advertiseArray;

    public AdvertisementAdapter() {
        super();
        // TODO Auto-generated constructor stub
    }

    public AdvertisementAdapter(Context context, List<View> views, JSONArray advertiseArray) {
        this.context = context;
        this.views = views;
        this.advertiseArray = advertiseArray;
        data = new ArrayList<>();
        num = new ArrayList<>();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return (arg0 == arg1);
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(View container, final int position) {

        ((ViewPager) container).addView(views.get(position), 0);
        final int POSITION = position;
        View view = views.get(position);
        try {
            String head_img = advertiseArray.optJSONObject(position).optString("head_img");
            ImageView ivAdvertise = (ImageView) view.findViewById(R.id.ivAdvertise);
            // 加载网络图片
            ImageLoaderUtil.getImage(context, ivAdvertise, head_img, R.mipmap.jiazaizhanwei, R.mipmap.jiazaizhanwei, 350, 400);

            SharedPreferences share = context.getSharedPreferences("logIn", Context.MODE_PRIVATE);
            UID = share.getString("login", "");
            if (UID == "") {
                url = url2 + 1;
            } else {
                url = url2 + UID;
            }
            //拉取网络数据
            RequestParams params = new RequestParams(url);
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String s) {
                    Gson gson = new Gson();
                    final HomeBean bean = gson.fromJson(s, HomeBean.class);
//                            num = bean.getBanner().get(position).getMsg();
//                            detailUrl = bean.getBanner().get(position).getDetailUrl();

                    int a = bean.getBanner().size();
                    for (int i = 0; i < a; i++) {
                        num.add(bean.getBanner().get(i).getMsg());
                        data.add(bean.getBanner().get(i).getDetailUrl());
                    }

                }

                @Override
                public void onError(Throwable throwable, boolean b) {

                }

                @Override
                public void onCancelled(CancelledException e) {

                }

                @Override
                public void onFinished() {

                }
            });


            // item的点击监听
            ivAdvertise.setOnClickListener(new NoDoubleClickListener() {
                @SuppressLint("ShowToast")
                @Override
                public void onNoDoubleClick(View v) {
//					Toast.makeText(context, "第"+POSITION+"个广告图片被点击", Toast.LENGTH_SHORT).show();
                    if (num.get(position) == 0) {
                        Intent intent = new Intent(context, NewSellActivity.class);
                        intent.putExtra("teji", data.get(position));
                        Log.e("sserty", data.get(position));
                        context.startActivity(intent);
                    } else {
                        Intent intent = new Intent(context, SellActivity.class);
                        intent.putExtra("sell", data.get(position));
//                                Log.e("sserty","fdgdg");
                        context.startActivity(intent);
                    }


                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

}
