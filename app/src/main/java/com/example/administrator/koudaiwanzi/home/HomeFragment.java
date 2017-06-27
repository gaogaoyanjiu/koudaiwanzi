package com.example.administrator.koudaiwanzi.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.BaseFragment;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.classify.ClassifyActivity;
import com.example.administrator.koudaiwanzi.details.DetailsActivity;
import com.example.administrator.koudaiwanzi.home.lun.Advertisements;
import com.example.administrator.koudaiwanzi.home.lun.RequestManager;
import com.example.administrator.koudaiwanzi.home.search.SearchActivity;
import com.example.administrator.koudaiwanzi.meatball.DetailBallActivity;
import com.example.administrator.koudaiwanzi.meatball.DetailsVideoActivity;
import com.example.administrator.koudaiwanzi.newproduct.NewProductActivity;
import com.example.administrator.koudaiwanzi.newsell.NewSellActivity;
import com.example.administrator.koudaiwanzi.person.point.pointmall.RewardActivity;
import com.example.administrator.koudaiwanzi.person.point.pointmall.scoremall.ScoreMallActivity;
import com.example.administrator.koudaiwanzi.person.set.AccountActivity;
import com.example.administrator.koudaiwanzi.product.ShowProductActivity;
import com.example.administrator.koudaiwanzi.rank.RankTwoActivity;
import com.example.administrator.koudaiwanzi.refresh.xlistview.XListView;
import com.example.administrator.koudaiwanzi.selling.SellActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 */

@ContentView(R.layout.fragment_home)
public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout llAdvertiseBoard;
    private LayoutInflater inflater;
    private int errorTime = 1;
    private boolean isRunning = false;
    private HomeAdapterTwo homeAdapter;
    private RelativeLayout rank;
    private long rest;
    private long restOver;
    private TextView tv_sale;
    private String imaurl = MyUrl.url;
    private String url2 = MyUrl.url + "Items.svc/home/";
    private String url;
    private String UID;
    private ViewPagerHomeHeadAdapter viewPagerHomeHeadAdapter;
    private ViewPager viewPager;
    private View viewHead;
    private RelativeLayout rl;
    private List<View> data;
    private Handler mHandler;
    private TextView tv_nine2, tv_twelve2, tv_eighteen2;
    private GridLayout gridLayout;
    private boolean cor = false;
    private RecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    private RelativeLayout rl_special;


    //相关推荐三个图
    private ImageView iv_p1;
    private ImageView iv_p2;
    private ImageView iv_p3;

    //相关推荐3个名称
    private TextView tv_name1;
    private TextView tv_name2;
    private TextView tv_name3;

    //相关推荐三个价格
    private TextView tv_prive1;
    private TextView tv_prive2;
    private TextView tv_prive3;

    private TextView tv_priveOld1;
    private TextView tv_priveOld2;
    private TextView tv_priveOld3;

    private ImageView iv_like ;
    private ImageView iv_notLike;
    private TextView  tv_zan;
    //搜索栏
//    @ViewInject(R.id.rl_home_search)
    private RelativeLayout rl_home_search;

    //listView绑定ID
    @ViewInject(R.id.home_listview)
    private XListView listView;
    //头像
//    @ViewInject(R.id.home_title_left)
    private ImageView iv_home_head;
    private LinearLayout search_ll;

    private RelativeLayout rl_sell;
    private RelativeLayout iv_new;
    private ImageView iv_nine;
    private ImageView iv_eighteen;
    private ImageView iv_twenty_one;
    private ImageView iv_nine1;
    private ImageView iv_eighteen1;
    private ImageView iv_twenty_one1;


    //首页轮播图↓
    private ViewPager TopviewPager;
    private LinearLayout pointGroup;
    private TextView iamgeDesc;
    //    // 图片资源ID
    private final int[] imageIds = {R.mipmap.banner1, R.mipmap.banner2, R.mipmap.banner3,
            R.mipmap.banner4};
    // 图片标题集合
//    private final String[] imageDescriptions = {"！拉布拉多猎犬",
//            "！边境牧羊犬", "！西伯利亚雪橇犬", "！德国柴犬", "！日本秋田犬"};
    private ArrayList<ImageView> imageList;

    //上一个页面的位置
    protected int lastPosition;

    //视频
    private VideoView videoview;
    private ImageView playVideo, playVideo1, playVideoCenter;
    private String VideoUrl;
    private ImageView home_selected, home_unselected;
    private TextView count;

    //定义广播管理器，广播接收器 用来接收光不后停止视频播放
    private LocalBroadcastManager broadcastManager;
    private IntentFilter intentFilter;
    private BroadcastReceiver mReceiver;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences share = getActivity().getSharedPreferences("logIn", Context.MODE_PRIVATE);
        UID = share.getString("login", "");
        if (UID == "" || UID.equals("")) {
            url = url2 + 1;
        } else {
            url = url2 + UID;
        }
        //handler初始化
        mHandler = new Handler();
        data = new ArrayList<View>();

        homeAdapter = new HomeAdapterTwo(getActivity().getApplication());
        viewPagerHomeHeadAdapter = new ViewPagerHomeHeadAdapter(getActivity().getApplication());

        //添加头布局
        view = LayoutInflater.from(getActivity()).inflate(R.layout.home_head, null);
        RequestManager.init(getActivity());
        inflater = LayoutInflater.from(getActivity());
        llAdvertiseBoard = (LinearLayout) view.findViewById(R.id.llAdvertiseBoard);
//        initViews();
        rl_home_search = (RelativeLayout) view.findViewById(R.id.rl_home_search);
//        iv_home_head = (ImageView) view.findViewById(R.id.home_title_left);
        search_ll = (LinearLayout) view.findViewById(R.id.home_ll);
        search_ll.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.activity_in, R.anim.activity_out);// 淡出淡入动画效果
            }
        });


//        iv_p1 = (ImageView) view.findViewById(R.id.home_recommend_iv1);
//        iv_p2 = (ImageView) view.findViewById(R.id.home_recommend_iv2);
//        iv_p3 = (ImageView) view.findViewById(R.id.home_recommend_iv3);
//        tv_name1 = (TextView) view.findViewById(R.id.home_recommend_tv1);
//        tv_name2 = (TextView) view.findViewById(R.id.home_recommend_tv2);
//        tv_name3 = (TextView) view.findViewById(R.id.home_recommend_tv3);
//        tv_prive1 = (TextView) view.findViewById(R.id.home_recommend_newprice1);
//        tv_prive2 = (TextView) view.findViewById(R.id.home_recommend_newprice2);
//        tv_prive3 = (TextView) view.findViewById(R.id.home_recommend_newprice3);
//        tv_priveOld1 = (TextView) view.findViewById(R.id.tv_view_one);
//        tv_priveOld2 = (TextView) view.findViewById(R.id.tv_view_two);
//        tv_priveOld3 = (TextView) view.findViewById(R.id.tv_view_three);
//        iv_like = (ImageView) view.findViewById(R.id.home_selected);
//        iv_notLike = (ImageView) view.findViewById(R.id.home_unselected);
//        tv_zan = (TextView) view.findViewById(R.id.home_count);
        //跳转丸子说界面
//        ImageView tv_all = (ImageView) view.findViewById(R.id.tv_home_all);
//        tv_all.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), MainActivity.class);
//                intent.putExtra("wanzishuo", "2");
//                startActivity(intent);
//            }
//        });


        //跳转到丸子说视频页面
      //  final TextView tv_video = (TextView) view.findViewById(R.id.video_tv);

        //销量排行的横滑
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_home_head_recyc);

        //初始化头布局中视频的各个组件
        playVideo = (ImageView) view.findViewById(R.id.playVideo);
        playVideo1 = (ImageView) view.findViewById(R.id.playVideo1);
        playVideoCenter = (ImageView) view.findViewById(R.id.playVideoCenter);
        playVideo1.setOnClickListener(this);
        playVideo.setOnClickListener(this);
        playVideoCenter.setOnClickListener(this);
        videoview = (VideoView) view.findViewById(R.id.videoView222);


        videoview.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                if (errorTime < 40) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // TODO Auto-generated method stub

                        }
                    }, 2000);
                    errorTime++;
                    return true;
                }
                return false;
            }
        });


        count = (TextView) view.findViewById(R.id.count);
        //点赞
//        home_selected = (ImageView) view.findViewById(R.id.home_selected);
//        home_unselected = (ImageView) view.findViewById(R.id.home_unselected);
        //如此简单的接收广播停止播放 注册广播接收器 此种方法不用再manifest中写任何东东
        broadcastManager = LocalBroadcastManager.getInstance(getActivity().getApplication());
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.administrator.myapplication.main.main.home.broadcast");
        // intentFilter.addAction("com.example.administrator.myapplication.main.main.home.broadcast");
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                videoview.pause();
                playVideo1.setVisibility(View.VISIBLE);
            }
        };
        broadcastManager.registerReceiver(mReceiver, intentFilter);


        //参加抽奖
        final RelativeLayout iv_tigerMachine = (RelativeLayout) view.findViewById(R.id.rl_home_4_1);
        RelativeLayout iv_scoreMall = (RelativeLayout) view.findViewById(R.id.rl_home_5_1);
        iv_scoreMall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(getActivity(), ScoreMallActivity.class);
                    startActivity(intent);


            }
        });


        //相关推荐绑定ID和监听跳转
//        final RelativeLayout rl_home_one = (RelativeLayout) view.findViewById(R.id.rl_home_one);
//        final RelativeLayout rl_home_two = (RelativeLayout) view.findViewById(R.id.rl_home_two);
//        final RelativeLayout rl_home_three = (RelativeLayout) view.findViewById(R.id.rl_home_three);


        //自动轮播的轮播图**
//        TopviewPager = (ViewPager) view.findViewById(R.id.top_viewpager);
//        pointGroup = (LinearLayout) view.findViewById(R.id.point_group);
//        imageList = new ArrayList<ImageView>();

        //商品特卖
        rl_sell = (RelativeLayout) view.findViewById(R.id.rl_sell);

        //新品上架
        iv_new = (RelativeLayout) view.findViewById(R.id.rl_home_3_1);


        //首页品牌logo
        gridLayout = (GridLayout) view.findViewById(R.id.gl_home);

        //大图小图
        final LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll_home_head);


        //Viewpager绑定id
//        viewPager = (ViewPager) view.findViewById(R.id.vp_home_head);


        //倒计时绑定ID
//        tv_sale = (TextView) view.findViewById(R.id.home_left_time);


//        TextView tv_old_price2 = (TextView) view.findViewById(R.id.tv_view_one);
//        //给textView中间加横线
//        tv_old_price2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//
//        TextView tv_old_price3 = (TextView) view.findViewById(R.id.tv_view_two);
//        //给textView中间加横线
//        tv_old_price3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//
//        TextView tv_old_price4 = (TextView) view.findViewById(R.id.tv_view_three);
//        //给textView中间加横线
//        tv_old_price4.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        listView.addHeaderView(view);
        //listview的滑动监听，如果视频滑出视线范围停止播放
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (visibleItemCount > 1) {
                    rl_home_search.setVisibility(View.VISIBLE);

                } else {
                    rl_home_search.setVisibility(View.GONE);
                }


                //如果visibleItemCount等于2 则视频停止播放
                if (visibleItemCount == 2) {
                    videoview.pause();
                    playVideo1.setVisibility(View.VISIBLE);
                }

            }
        });

        //绑定top ID并监听
        rank = (RelativeLayout) view.findViewById(R.id.rl_home2_1);
        rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RankTwoActivity.class);
                startActivity(intent);
            }
        });


//        tv_nine2 = (TextView) view.findViewById(R.id.tv_nine);
//        tv_twelve2 = (TextView) view.findViewById(R.id.tv_twelve);
//        tv_eighteen2 = (TextView) view.findViewById(R.id.tv_eighteen);
//        iv_nine = (ImageView) view.findViewById(R.id.nine);
//        iv_nine1 = (ImageView) view.findViewById(R.id.nine1);
//        iv_eighteen = (ImageView) view.findViewById(R.id.eighteen);
//        iv_eighteen1 = (ImageView) view.findViewById(R.id.eighteen1);
//        iv_twenty_one = (ImageView) view.findViewById(R.id.twenty_one);
//        iv_twenty_one1 = (ImageView) view.findViewById(R.id.twenty_one1);
//        rl_special = (RelativeLayout) view.findViewById(R.id.rl_home_special);
        Log.e("fzljkdajl", url);
        final RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String o) {
                    //抢购的商品
                    Gson gson = new Gson();
                    final HomeBean bean = gson.fromJson(o, HomeBean.class);

//                    if (bean.getSpecialTime().size() > 0){
//                        rl_special.setVisibility(View.VISIBLE);
//                    }else {
//                        rl_special.setVisibility(View.GONE);
//                    }

                /**
                 * 轮播图添加图片，跳转是在他的适配器中
                 */

                    if (bean.getBanner().size() > 0){
                        int lun_nums = bean.getBanner().size();
                        // 添加图片的Url地址
                        JSONArray advertiseArray = new JSONArray();
                        try {

                            for (int i = 0; i < lun_nums; i++) {
                                JSONObject head_img0 = new JSONObject();
                                head_img0.put("head_img", imaurl + bean.getBanner().get(i).getIMGURL());
                                advertiseArray.put(head_img0);
                            }

                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        llAdvertiseBoard.addView(new Advertisements(getActivity(), true, inflater, 3000).initView(advertiseArray));

                    }

                    //跳转到参加抽奖
                    iv_tigerMachine.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                                Intent intent = new Intent(getActivity(), RewardActivity.class);
                                intent.putExtra("tiger", bean.getDRAWURL());
                                startActivity(intent);



                        }
                    });



                    //加载头像
//                    if (bean.getHEADURL() == ""){
//                        iv_home_head.setImageResource(R.mipmap.home_title_left);
//                    }else {
//                        Picasso.with(getActivity()).load(imaurl + bean.getHEADURL()).into(iv_home_head);
//                    }

                    //添加数据
                    homeAdapter.addData(bean.getSTORY());
                    //listview的设置
               //     listView.setPullLoadEnable(true);
                    //添加监听
                    listView.setXListViewListener(new MyListener());
                    //listView添加适配器
                    listView.setAdapter(homeAdapter);


                    ImageOptions options2 = new ImageOptions.Builder()
                            //图片大小
                            .setSize(DensityUtil.dip2px(200), DensityUtil.dip2px(100))
                            // 是否忽略GIF格式的图片
                            .setIgnoreGif(false)
                            // 图片缩放模式
                            .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                            // 得到ImageOptions对象
                            .build();

                    //品牌logo添加图片
                    int size7 = bean.getLOGO().size();
                    if (size7 != 0) {
                        for (int i = 0; i < size7; i++) {
                            View view = LayoutInflater.from(getActivity().getApplication()).inflate(R.layout.item_gridlayout, null);
                            ImageView ima = (ImageView) view.findViewById(R.id.iv_head_pinpai);
                            x.image().bind(ima, imaurl + bean.getLOGO().get(i).getLOGOURL(), options2);
                            ima.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getActivity(), ClassifyActivity.class);
    //                                    intent.putExtra("fenlei", "6");
                                    Log.e("sserty", "fdgdg");
                                    startActivity(intent);
    //                                    getActivity().finish();

                                }
                            });
                            gridLayout.addView(view);
                        }
                    }


                    ImageOptions options3 = new ImageOptions.Builder()
                            //图片大小
                            .setSize(250, 200)
                            // 是否忽略GIF格式的图片
                            .setIgnoreGif(false)
                            // 图片缩放模式
                            .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                            // 得到ImageOptions对象
                            .build();

                    // 设置加载图片的参数
                    final ImageOptions options = new ImageOptions.Builder()
                            //图片大小
                            .setSize(350, 300)
                            // 是否忽略GIF格式的图片
                            .setIgnoreGif(false)
                            // 图片缩放模式
                            .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                            // 得到ImageOptions对象
                            .build();

                    //大图小图
                    int size5 = bean.getItems().size();
                    if (size5 != 0) {
                        for (int i = 0; i < size5; i++) {
                            Log.e("sdczvb", bean.getItems().size() + "");
                            View v = LayoutInflater.from(getActivity().getApplication()).inflate(R.layout.home_listview_item, null);
                            SimpleDraweeView ima1 = (SimpleDraweeView) v.findViewById(R.id.home_listview_top);
                            x.image().bind(ima1, imaurl + bean.getItems().get(i).getURL(), options);

                            //FRESCO缓存图片
                            Uri imageUri = Uri.parse(imaurl + bean.getItems().get(i).getURL());
                            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(imageUri)
                                    .setResizeOptions(new ResizeOptions(280, 200))
                                    .build();
                            DraweeController controller = Fresco.newDraweeControllerBuilder()
                                    .setOldController(ima1.getController())
                                    .setImageRequest(request)
                                    .build();
                            ima1.setController(controller);

                            //大图的监听跳转到商品筛选页
                            final int finalI1 = i;
                            ima1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getActivity(), ShowProductActivity.class);
                                    intent.putExtra("showKey", bean.getItems().get(finalI1).getDetailUrl());
                                    intent.putExtra("TID", bean.getItems().get(finalI1).getTID());
                                    intent.putExtra("where", "3");
                                    getActivity().startActivity(intent);
                                }
                            });
                            LinearLayout ll_small = (LinearLayout) v.findViewById(R.id.ll_home_product);
                            int size6 = bean.getItems().get(i).getCateItems().size();
                            for (int j = 0; j < size6; j++) {
                                View view = LayoutInflater.from(getActivity().getApplication()).inflate(R.layout.item_home_product, null);
                                SimpleDraweeView ima = (SimpleDraweeView) view.findViewById(R.id.home_recommend_iv);
                                TextView tv_ISOUTSTOCK = (TextView) view.findViewById(R.id.tv_ISOUTSTOCK);
                                TextView tv = (TextView) view.findViewById(R.id.home_recommend_tv);
                                TextView tv_price = (TextView) view.findViewById(R.id.home_recommend_newprice1);
                                TextView tv_price_old = (TextView) view.findViewById(R.id.tv_home_head_item_old_price);
                                x.image().bind(ima, imaurl + bean.getItems().get(i).getCateItems().get(j).getICOURL(), options3);
                                tv.setText(bean.getItems().get(i).getCateItems().get(j).getTRADENAME());
                                //FRESCO缓存图片
                                Uri imageUri2 = Uri.parse(imaurl + bean.getItems().get(i).getCateItems().get(j).getICOURL());
                                ImageRequest request2 = ImageRequestBuilder.newBuilderWithSource(imageUri2)
                                        .setResizeOptions(new ResizeOptions(150, 100))
                                        .build();
                                DraweeController controller2 = Fresco.newDraweeControllerBuilder()
                                        .setOldController(ima.getController())
                                        .setImageRequest(request2)
                                        .build();
                                ima.setController(controller2);

                                if (bean.getItems().get(i).getCateItems().get(j).getDISCOUNT() == 0){
                                    tv_price_old.setVisibility(View.GONE);
                                    tv_price.setText("¥" + bean.getItems().get(i).getCateItems().get(j).getPRICE() + "");
                                }else {
                                    tv_price.setText("¥" + bean.getItems().get(i).getCateItems().get(j).getBARGAIN() + "");
                                    tv_price_old.setText("¥" + bean.getItems().get(i).getCateItems().get(j).getPRICE() + "");
                                    //给textView中间加横线
                                    tv_price_old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                                }
                                if (bean.getItems().get(i).getCateItems().get(j).getISOUTSTOCK() == 0){
                                    tv_ISOUTSTOCK.setVisibility(View.GONE);
                                }else {
                                    tv_ISOUTSTOCK.setVisibility(View.VISIBLE);
                                }

                                ll_small.addView(view);
                                final int finalI = i;
                                final int finalJ = j;
                                ima.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(getActivity(), DetailsActivity.class);
                                        intent.putExtra("key", bean.getItems().get(finalI).getCateItems().get(finalJ).getDetailUrl());
                                        startActivity(intent);
                                    }
                                });
                            }

                            ll.addView(v);
                        }
                    }

                    //跳转到品牌特卖
                    rl_sell.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), SellActivity.class);
                            intent.putExtra("sell", bean.getSALEURL());
                            startActivity(intent);
                        }
                    });

                    //跳转到新品上架
                    iv_new.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), NewProductActivity.class);
                            intent.putExtra("newproduct", bean.getNEWITEMURL());
                            startActivity(intent);
                        }
                    });

                    //点击头像跳转到个人信息界面
//                    iv_home_head.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            SharedPreferences share = getActivity().getSharedPreferences("logIn", Context.MODE_PRIVATE);
//                            SharedPreferences.Editor editor = share.edit();
//                            editor.putString("account", "Users.svc//accmanagement//" + UID);
//                            editor.apply();
//                            Intent intent = new Intent(getActivity(), AccountActivity.class);
//                            startActivity(intent);
//                        }
//                    });


    //        iamgeDesc = (TextView) view.findViewById(R.id.image_desc);
    //        iamgeDesc.setText(imageDescriptions[0]);
//                    final int pos = bean.getBanner().size();
    //                for (int q = 0; q < pos; q++) {
    //                    // 添加指示点
    //                    ImageView point = new ImageView(getActivity());
    //                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
    //                            LinearLayout.LayoutParams.WRAP_CONTENT,
    //                            LinearLayout.LayoutParams.WRAP_CONTENT);
    //
    //                    params.rightMargin = 20;
//                        point.setLayoutParams(params);
    //                    //获取原点的图标
    //                    point.setBackgroundResource(R.drawable.selector_home_point);
    //                    if (q == 0) {
    //                        point.setEnabled(true);
    //                    } else {
    //                        point.setEnabled(false);
    //                    }
//                        pointGroup.addView(point);
    //                }

    //        TopviewPager.setAdapter(new MyPagerAdapter());

    //                TopviewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
    //
    //                    @Override
    //                    /**
    //                     * 页面切换后调用
    //                     * position  新的页面位置
    //                     */
    //                    public void onPageSelected(int position) {
    //                        if (position == 0) {
    //                            position = 0;
    //                        } else {
    //                            position = position % pos;
    //                        }
    //                        // 设置文字描述内容
    ////                iamgeDesc.setText(imageDescriptions[position]);
    //
    //                        // 改变指示点的状态
    //                        // 把当前点enbale 为true
    //                        pointGroup.getChildAt(position).setEnabled(true);
    //                        // 把上一个点设为false
//                            pointGroup.getChildAt(lastPosition).setEnabled(false);
    //                        lastPosition = position;
    //                        //给图片设置监听
    //                        final int finalPosition = position;
    //                        imageList.get(position).setOnClickListener(new View.OnClickListener() {
    //                            @Override
    //                            public void onClick(View view) {
    //                                //点击每个图片所进行的逻辑操作在这里写
    ////                                Intent intent = new Intent(getActivity(),DetailsActivity.class);
    ////                                startActivity(intent);
    ////                                Toast.makeText(getContext(), "这是第" + lastPosition + "个", Toast.LENGTH_SHORT).show();
    //
    //                                if (bean.getBanner().get(finalPosition).getMsg() == 0){
    //                                    Intent intent = new Intent(getActivity(), NewSellActivity.class);
    //                                    intent.putExtra("teji", bean.getBanner().get(finalPosition).getDetailUrl());
    ////                                Log.e("sserty","fdgdg");
    //                                    startActivity(intent);
    //                                }else {
    //                                    Intent intent = new Intent(getActivity(), SellActivity.class);
    //                                    intent.putExtra("sell", bean.getBanner().get(finalPosition).getDetailUrl());
    ////                                Log.e("sserty","fdgdg");
    //                                    startActivity(intent);
    //                                }
    //
    //
    ////                                getActivity().finish();
    //                            }
    //                        });
    //                    }
    //
    //                    @Override
    //                    /**
    //                     * 页面正在滑动的时候，回调
    //                     */
    //                    public void onPageScrolled(int position, float positionOffset,
    //                                               int positionOffsetPixels) {
    //                    }
    //
    //                    @Override
    //                    /**
    //                     * 当页面状态发生变化的时候，回调
    //                     */
    //                    public void onPageScrollStateChanged(int state) {
    //
    //                    }
    //                });

            /*
             * 自动循环： 1、定时器：Timer 2、开子线程 while true 循环 3、ColckManager 4、 用handler
             * 发送延时信息，实现循环
             */
    //                isRunning = true;
                    // 设置图片的自动滑动
    //                topHandler.sendEmptyMessageDelayed(0, 5000);

                    //轮播图↑***

                    //轮播图的图片解析
    //                String path;
    //                int banner = bean.getBanner().size();

    //                for (int i = 0; i < pos; i++) {
    //                    ImageView image = new ImageView(getActivity());
    //                    path = imaurl + bean.getBanner().get(i).getIMGURL();
    //                    Log.e("qaqaq", path);
    //                    x.image().bind(image, path, options);
    ////                    image.setBackgroundResource(imageIds[i]);
    //                    imageList.add(image);
    //                }
    //                TopviewPager.setAdapter(new ViewPagerTopAdapter(imageList, getActivity()));


                    //丸子说图片文字点击跳转到丸子说详情界面
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getActivity(), DetailBallActivity.class);
                            intent.putExtra("pictureBall", bean.getSTORY().get((int) parent.getItemIdAtPosition(position)).getDetailUrl());
                            Log.e("fghghafa", (int) parent.getItemIdAtPosition(position) + "");
                            startActivity(intent);
                        }
                    });
                    if (bean.getHOT().getDetailUrl() == null || bean.getHOT().getDetailUrl() == "") {

                    } else {
                        //跳转到丸子说视频页面
//                        tv_video.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Intent intent = new Intent(getActivity(), DetailsVideoActivity.class);
//                                intent.putExtra("videoBall", bean.getHOT().getDetailUrl());
//                                startActivity(intent);
//                            }
//                        });

                    }


                    if (bean.getTop().size() != 0) {
                        recyclerAdapter = new RecyclerAdapter(getActivity(), R.layout.item_home_head_recyc, bean.getTop());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                        recyclerView.setAdapter(recyclerAdapter);
                    } else {
                        recyclerView.setVisibility(View.GONE);
                    }
                   Log.e("fzljkdajl", "zoule2");
//                    if (bean.getSpecialTime().size() != 0) {
//                        long time = bean.getSpecialTime().get(0).getSPETIME();
//                        long time2 = bean.getSpecialTime().get(1).getSPETIME();
//                        long time3 = bean.getSpecialTime().get(2).getSPETIME();
//                        Log.e("fzljkdajl", "zoule3");
//
//                        if ((time2 - time2 / 3600 * 3600) / 60 == 0) {
//                            tv_nine2.setText(time2 / 3600 + ":" + 0 + 0);
//                        } else {
//                            tv_nine2.setText(time2 / 3600 + ":" + (time2 - time2 / 3600 * 3600) / 60);
//                        }
//
//                        if ((time - time / 3600 * 3600) / 60 == 0) {
//                            tv_twelve2.setText(time / 3600 + ":" + 0 + 0);
//                        } else {
//                            tv_twelve2.setText(time / 3600 + ":" + (time - time / 3600 * 3600) / 60);
//                        }
//
//                        if ((time3 - time3 / 3600 * 3600) / 60 == 0) {
//                            tv_eighteen2.setText(time3 / 3600 + ":" + 0 + 0);
//                        } else {
//                            tv_eighteen2.setText(time3 / 3600 + ":" + (time3 - time3 / 3600 * 3600) / 60);
//                        }
//                    }
/**
 * 丸子说点赞逻辑
 */

//
//                if (bean.getMsg() == 0) {
//                    iv_like.setVisibility(View.VISIBLE);
//                    iv_notLike.setVisibility(View.INVISIBLE);
//
//                    iv_like.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            RequestParams params1 = new RequestParams(imaurl + bean.getHOT().getLIKESURL());
//                            x.http().get(params1, new CommonCallback<String>() {
//                                @Override
//                                public void onSuccess(String s) {
//                                    iv_like.setVisibility(View.INVISIBLE);
//                                    iv_notLike.setVisibility(View.VISIBLE);
//                                    Log.d("dsadsdsads", String.valueOf(bean.getHOT().getLIKES()));
//                                    Log.d("dsadsdsads1", String.valueOf(bean.getHOT().getLIKES() + 1+""));
//                                    tv_zan.setText(bean.getHOT().getLIKES() + 1+"");
//                                }
//
//                                @Override
//                                public void onError(Throwable throwable, boolean b) {
//
//                                }
//
//                                @Override
//                                public void onCancelled(CancelledException e) {
//
//                                }
//
//                                @Override
//                                public void onFinished() {
//
//                                }
//
//                            });
//                        }
//                    });
//                    iv_notLike.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            RequestParams params1 = new RequestParams(imaurl + bean.getHOT().getLIKESURL());
//                            x.http().get(params1, new CommonCallback<String>() {
//                                @Override
//                                public void onSuccess(String s) {
//                                    iv_like.setVisibility(View.VISIBLE);
//                                    iv_notLike.setVisibility(View.INVISIBLE);
//                                    tv_zan.setText(bean.getHOT().getLIKES()+ "");
//                                }
//
//                                @Override
//                                public void onError(Throwable throwable, boolean b) {
//
//                                }
//
//                                @Override
//                                public void onCancelled(CancelledException e) {
//
//                                }
//
//                                @Override
//                                public void onFinished() {
//
//                                }
//
//                            });
//                        }
//                    });
//
//
//                } else {
//                    iv_like.setVisibility(View.INVISIBLE);
//                    iv_notLike.setVisibility(View.VISIBLE);
//
//
//                    iv_like.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            RequestParams params1 = new RequestParams(imaurl + bean.getHOT().getLIKESURL());
//                            x.http().get(params1, new CommonCallback<String>() {
//                                @Override
//                                public void onSuccess(String s) {
//                                    iv_like.setVisibility(View.INVISIBLE);
//                                    iv_notLike.setVisibility(View.VISIBLE);
//                                    Log.d("dsadsdsads", String.valueOf(bean.getHOT().getLIKES()));
//                                    Log.d("dsadsdsads1", String.valueOf(bean.getHOT().getLIKES() + 1+""));
//                                    tv_zan.setText(bean.getHOT().getLIKES()+"");
//                                }
//
//                                @Override
//                                public void onError(Throwable throwable, boolean b) {
//
//                                }
//
//                                @Override
//                                public void onCancelled(CancelledException e) {
//
//                                }
//
//                                @Override
//                                public void onFinished() {
//
//                                }
//
//                            });
//                        }
//                    });
//                    iv_notLike.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            RequestParams params1 = new RequestParams(imaurl + bean.getHOT().getLIKESURL());
//                            x.http().get(params1, new CommonCallback<String>() {
//                                @Override
//                                public void onSuccess(String s) {
//                                    iv_like.setVisibility(View.VISIBLE);
//                                    iv_notLike.setVisibility(View.INVISIBLE);
//                                    tv_zan.setText(bean.getHOT().getLIKES() - 1 + "");
//                                }
//
//                                @Override
//                                public void onError(Throwable throwable, boolean b) {
//
//                                }
//
//                                @Override
//                                public void onCancelled(CancelledException e) {
//
//                                }
//
//                                @Override
//                                public void onFinished() {
//
//                                }
//
//                            });
//                        }
//                    });
//                }
                /**
                 * videoview 绑定播放前图片
                 */
                    Log.e("zfgbcc", "qweasd");
                    if (bean.getHOT().getDetailUrl() == null || bean.getHOT().getDetailUrl() != "") {
                        x.image().bind(playVideoCenter, imaurl + bean.getHOT().getPREVIEW());
                        //videoview的视频的播放
                        VideoUrl = imaurl + bean.getHOT().getVIDEOURL();
                        Log.e("tybnk", VideoUrl);
                        videoview.setVideoURI(Uri.parse(VideoUrl));
                        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                playVideoCenter.setVisibility(View.VISIBLE);
                                playVideo1.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                Log.e("fzljkdajl", "zoule5");
                /**
                 * 相关推荐
                 */

//                if (bean.getHOT().getITEMS().size() == 0){
//
//                }else if(bean.getHOT().getITEMS().size() == 1){
//                    x.image().bind(iv_p1, imaurl + bean.getHOT().getITEMS().get(0).getICOURL(), options3);
//                    tv_name1.setText(bean.getHOT().getITEMS().get(0).getTRADENAME());
//                    if (bean.getHOT().getITEMS().get(0).getDISCOUNT() == 0) {
//                        tv_prive1.setText(bean.getHOT().getITEMS().get(0).getPRICE() + "");
//                        tv_priveOld1.setVisibility(View.GONE);
//                    } else {
//                        tv_prive1.setText(bean.getHOT().getITEMS().get(0).getBARGAIN() + "");
//                        tv_priveOld1.setText(bean.getHOT().getITEMS().get(0).getPRICE() + "");
//                    }
//                }
//                else if(bean.getHOT().getITEMS().size() == 2){
//                    x.image().bind(iv_p1, imaurl + bean.getHOT().getITEMS().get(0).getICOURL(), options3);
//                    tv_name1.setText(bean.getHOT().getITEMS().get(0).getTRADENAME());
//                    x.image().bind(iv_p2, imaurl + bean.getHOT().getITEMS().get(1).getICOURL(), options3);
//                    tv_name2.setText(bean.getHOT().getITEMS().get(1).getTRADENAME());
//                    if (bean.getHOT().getITEMS().get(0).getDISCOUNT() == 0) {
//                        tv_prive1.setText(bean.getHOT().getITEMS().get(0).getPRICE() + "");
//                        tv_priveOld1.setVisibility(View.GONE);
//                    } else {
//                        tv_prive1.setText(bean.getHOT().getITEMS().get(0).getBARGAIN() + "");
//                        tv_priveOld1.setText(bean.getHOT().getITEMS().get(0).getPRICE() + "");
//                    }
//                    if (bean.getHOT().getITEMS().get(1).getDISCOUNT() == 0) {
//                        tv_prive2.setText(bean.getHOT().getITEMS().get(1).getPRICE() + "");
//                        tv_priveOld2.setVisibility(View.GONE);
//                    } else {
//                        tv_prive2.setText(bean.getHOT().getITEMS().get(1).getBARGAIN() + "");
//                        tv_priveOld2.setText(bean.getHOT().getITEMS().get(1).getPRICE() + "");
//                    }
//                }else if (bean.getHOT().getITEMS().size() == 3){
//                    x.image().bind(iv_p1, imaurl + bean.getHOT().getITEMS().get(0).getICOURL(), options3);
//                    tv_name1.setText(bean.getHOT().getITEMS().get(0).getTRADENAME());
//                    x.image().bind(iv_p2, imaurl + bean.getHOT().getITEMS().get(1).getICOURL(), options3);
//                    tv_name2.setText(bean.getHOT().getITEMS().get(1).getTRADENAME());
//                    x.image().bind(iv_p3, imaurl + bean.getHOT().getITEMS().get(2).getICOURL(), options3);
//                    tv_name3.setText(bean.getHOT().getITEMS().get(2).getTRADENAME());
//                    if (bean.getHOT().getITEMS().get(0).getDISCOUNT() == 0) {
//                        tv_prive1.setText(bean.getHOT().getITEMS().get(0).getPRICE() + "");
//                        tv_priveOld1.setVisibility(View.GONE);
//                    } else {
//                        tv_prive1.setText(bean.getHOT().getITEMS().get(0).getBARGAIN() + "");
//                        tv_priveOld1.setText(bean.getHOT().getITEMS().get(0).getPRICE() + "");
//                    }
//                    if (bean.getHOT().getITEMS().get(1).getDISCOUNT() == 0) {
//                        tv_prive2.setText(bean.getHOT().getITEMS().get(1).getPRICE() + "");
//                        tv_priveOld2.setVisibility(View.GONE);
//                    } else {
//                        tv_prive2.setText(bean.getHOT().getITEMS().get(1).getBARGAIN() + "");
//                        tv_priveOld2.setText(bean.getHOT().getITEMS().get(1).getPRICE() + "");
//                    }
//                    if (bean.getHOT().getITEMS().get(2).getDISCOUNT() == 0) {
//                        tv_prive3.setText(bean.getHOT().getITEMS().get(2).getPRICE() + "");
//                        tv_priveOld3.setVisibility(View.GONE);
//                    } else {
//                        tv_prive3.setText(bean.getHOT().getITEMS().get(2).getBARGAIN() + "");
//                        tv_priveOld3.setText(bean.getHOT().getITEMS().get(2).getPRICE() + "");
//                    }
//                }


//                //下记代码是点赞的逻辑  选中点赞按钮
//                count.setText(bean.getSTORY().get(0).getLIKES() + "");
//                home_selected.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        count.setText(bean.getSTORY().get(0).getLIKES() + "");
//                        home_unselected.setVisibility(View.VISIBLE);
//                        home_selected.setVisibility(View.GONE);
//                    }
//                });
//
//                //点赞按钮没有选中
//                home_unselected.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        count.setText(bean.getSTORY().get(0).getLIKES() + 1 + "");
//                        home_unselected.setVisibility(View.GONE);
//                        home_selected.setVisibility(View.VISIBLE);
//                    }
//                });
                //相关推荐添加数据



//                    rl_home_one.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent in = new Intent(getActivity(), DetailsActivity.class);
//                            in.putExtra("key", bean.getHOT().getITEMS().get(0).getDetailUrl());
//                            getActivity().startActivity(in);
//                        }
//                    });

//                    rl_home_two.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent in = new Intent(getActivity(), DetailsActivity.class);
//                            in.putExtra("key", bean.getHOT().getITEMS().get(1).getDetailUrl());
//                            getActivity().startActivity(in);
//                        }
//                    });
//
//                    rl_home_three.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent in = new Intent(getActivity(), DetailsActivity.class);
//                            in.putExtra("key", bean.getHOT().getITEMS().get(2).getDetailUrl());
//                            getActivity().startActivity(in);
//                        }
//                    });


    //                tv_prive1.setText(bean.getHOT().getITEMS().get(0).getPRICE()+"");
    //                tv_prive2.setText(bean.getHOT().getITEMS().get(1).getPRICE()+"");
    //                tv_prive3.setText(bean.getHOT().getITEMS().get(2).getPRICE()+"");

/**
 *     抢购的逻辑
 */

    //                if (bean.getSpecialTime().size() != 0) {
//                    int Qnum = bean.getSpecialTime().size();
//                    Log.e("ghjkmb", Qnum+"");
//                    //9dian
//                    //9点的抢购
//                    if (Qnum > 0) {
//                        iv_nine.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                final RequestParams params = new RequestParams(url);
//                                x.http().get(params, new CommonCallback<String>() {
//                                    @Override
//                                    public void onSuccess(String s) {
//                                        //抢购的商品
//                                        Gson gson = new Gson();
//                                        final HomeBean bean = gson.fromJson(s, HomeBean.class);
//
//                                        iv_nine.setVisibility(View.INVISIBLE);
//                                        iv_nine1.setVisibility(View.VISIBLE);
//                                        iv_eighteen.setVisibility(View.VISIBLE);
//                                        iv_eighteen1.setVisibility(View.INVISIBLE);
//                                        iv_twenty_one.setVisibility(View.VISIBLE);
//                                        iv_twenty_one1.setVisibility(View.INVISIBLE);
//                                        rest = bean.getSpecialTime().get(0).getREST();
//                                        restOver = bean.getSpecialTime().get(0).getRESTOVER();
//
//                                        if (cor == false) {
//                                            tv_nine2.setTextColor(Color.parseColor("#f19483"));
//                                            tv_twelve2.setTextColor(Color.parseColor("#8a8a8a"));
//                                            tv_eighteen2.setTextColor(Color.parseColor("#8a8a8a"));
//                                        }
//
//                                        //抢购的商品
////                                        int k = data.size();
////                                        Log.e("gdg", k + "");
////                                        if (k > 0) {
////                                            for (int i = k - 1; i >= 0; i--) {
////                                                data.remove(i);
////
////                                            }
////                                        }
//
////                                        int size = bean.getSpecialTime().get(0).getSpecialItem().size();
////                                        for (int i = 0; i < size; i++) {
////                                            viewHead = LayoutInflater.from(getActivity().getApplication()).inflate(R.layout.item_home_recyclerview, null);
////                                            rl = (RelativeLayout) viewHead.findViewById(R.id.rl_home_head);
////                                            TextView tv_jdt = (TextView) viewHead.findViewById(R.id.tv_jdt);
////                                            TextView tv_price = (TextView) viewHead.findViewById(R.id.home_time_newprice);
////                                            TextView tv_price_old = (TextView) viewHead.findViewById(R.id.home_time_oldprice);
////                                            tv_price_old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
////                                            TextView tv_title = (TextView) viewHead.findViewById(R.id.home_time_small_title);
////                                            ImageView imageView = (ImageView) viewHead.findViewById(R.id.iv_home);
////                                            ProgressBar pb = (ProgressBar) viewHead.findViewById(R.id.jindutiao);
////                                            x.image().bind(imageView, imaurl + bean.getSpecialTime().get(0).getSpecialItem().get(i).getICOURL()
////                                                    , options);
////                                            tv_price_old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
////                                            tv_price.setText("¥" + bean.getSpecialTime().get(0).getSpecialItem().get(i).getBARGAIN());
////                                            tv_price_old.setText("¥" + bean.getSpecialTime().get(0).getSpecialItem().get(i).getPRICE());
////                                            tv_title.setText("¥" + bean.getSpecialTime().get(0).getSpecialItem().get(i).getTRADENAME());
////                                            int a = (int) (bean.getSpecialTime().get(0).getSpecialItem().get(i).getSURITEMNUM() * 100);
////                                            pb.setProgress(a);
////                                            tv_jdt.setText("还剩" + a + "%");
////                                            //监听item跳转
////                                            final int finalI = i;
////                                            rl.setOnClickListener(new View.OnClickListener() {
////                                                @Override
////                                                public void onClick(View v) {
////                                                    if (rest == 0 && restOver == 0) {
////                                                        Toast.makeText(getActivity(), "活动已结束", Toast.LENGTH_SHORT).show();
////                                                    } else {
////                                                        Intent in = new Intent(getActivity(), DetailsActivity.class);
////                                                        in.putExtra("key", bean.getSpecialTime().get(0).getSpecialItem().get(finalI).getDetailUrl());
////                                                        getActivity().startActivity(in);
////                                                    }
////
////                                                }
////                                            });
////                                            data.add(viewHead);
////                                        }
//
//                                        viewPagerHomeHeadAdapter.addData(data);
//                                        viewPager.setAdapter(viewPagerHomeHeadAdapter);
//                                    }
//
//
//                                    @Override
//                                    public void onError(Throwable throwable, boolean b) {
//
//                                    }
//
//                                    @Override
//                                    public void onCancelled(CancelledException e) {
//
//                                    }
//
//                                    @Override
//                                    public void onFinished() {
//
//                                    }
//                                });
//
//
//                            }
//
//                        });

//                    }

//                    if (Qnum > 1) {
//                        //12dian
//                        iv_eighteen.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                iv_nine.setVisibility(View.VISIBLE);
//                                iv_nine1.setVisibility(View.INVISIBLE);
//                                iv_eighteen.setVisibility(View.INVISIBLE);
//                                iv_eighteen1.setVisibility(View.VISIBLE);
//                                iv_twenty_one.setVisibility(View.VISIBLE);
//                                iv_twenty_one1.setVisibility(View.INVISIBLE);
//                                rest = bean.getSpecialTime().get(1).getREST();
//                                restOver = bean.getSpecialTime().get(1).getRESTOVER();
//
//                                if (cor == false) {
//                                    tv_nine2.setTextColor(Color.parseColor("#8a8a8a"));
//                                    tv_twelve2.setTextColor(Color.parseColor("#f19483"));
//                                    tv_eighteen2.setTextColor(Color.parseColor("#8a8a8a"));
//                                }
//    //                            ArrayList<HomeBean.SpecialTimeBean.SpecialItemBean> list = new ArrayList<HomeBean.SpecialTimeBean.SpecialItemBean>();
//    //                            for (HomeBean.SpecialTimeBean.SpecialItemBean sb : bean.getSpecialTime().get(0).getSpecialItem()) {
//    //                                data.add(sb);
//    //                            }
//                                //抢购的商品
//                                int l = data.size();
//                                Log.e("gdg", l + "");
//                                if (l > 0) {
//                                    for (int i = l - 1; i >= 0; i--) {
//                                        data.remove(i);
//                                    }
//                                }
//                                int size2 = bean.getSpecialTime().get(1).getSpecialItem().size();
//                                if (size2 != 0) {
//                                    for (int i = 0; i < size2; i++) {
//                                        viewHead = LayoutInflater.from(getActivity().getApplication()).inflate(R.layout.item_home_recyclerview, null);
//                                        rl = (RelativeLayout) viewHead.findViewById(R.id.rl_home_head);
//                                        TextView tv_jdt = (TextView) viewHead.findViewById(R.id.tv_jdt);
//                                        TextView tv_price = (TextView) viewHead.findViewById(R.id.home_time_newprice);
//                                        TextView tv_price_old = (TextView) viewHead.findViewById(R.id.home_time_oldprice);
//                                        tv_price_old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//                                        ProgressBar pb = (ProgressBar) viewHead.findViewById(R.id.jindutiao);
//                                        TextView tv_title = (TextView) viewHead.findViewById(R.id.home_time_small_title);
//                                        ImageView imageView = (ImageView) viewHead.findViewById(R.id.iv_home);
//                                        x.image().bind(imageView, imaurl + bean.getSpecialTime().get(1).getSpecialItem().get(i).getICOURL()
//                                                , options);
//
//
//                                        tv_price_old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//                                        tv_price.setText("¥" + bean.getSpecialTime().get(1).getSpecialItem().get(i).getBARGAIN());
//                                        tv_price_old.setText("¥" + bean.getSpecialTime().get(1).getSpecialItem().get(i).getPRICE());
//                                        tv_title.setText("¥" + bean.getSpecialTime().get(1).getSpecialItem().get(i).getTRADENAME());
//                                        int a = (int) (bean.getSpecialTime().get(1).getSpecialItem().get(i).getSURITEMNUM() * 100);
//                                        tv_jdt.setText("还剩" + a + "%");
//                                        pb.setProgress(a);
//                                        //监听item跳转
//                                        final int finalI = i;
//                                        rl.setOnClickListener(new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View v) {
//                                                if (rest == 0 && restOver == 0) {
//                                                    Toast.makeText(getActivity(), "活动已结束", Toast.LENGTH_SHORT).show();
//                                                } else {
//                                                    Intent in = new Intent(getActivity(), DetailsActivity.class);
//                                                    in.putExtra("key", bean.getSpecialTime().get(1).getSpecialItem().get(finalI).getDetailUrl());
//                                                    getActivity().startActivity(in);
//                                                }
//                                            }
//                                        });
//                                        data.add(viewHead);
//                                    }
//                                }
//                                viewPagerHomeHeadAdapter.addData(data);
//                                viewPager.setAdapter(viewPagerHomeHeadAdapter);
//                            }
//                        });
//                    }


//                    if (Qnum > 2) {
//                        //18dian
//                        iv_twenty_one.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                final RequestParams params = new RequestParams(url);
//                                x.http().get(params, new CommonCallback<String>() {
//
//                                    @Override
//                                    public void onSuccess(String o) {
//                                        //抢购的商品
//                                        Gson gson = new Gson();
//                                        final HomeBean bean = gson.fromJson(o, HomeBean.class);
//                                        cor = false;
//                                        iv_nine.setVisibility(View.VISIBLE);
//                                        iv_nine1.setVisibility(View.INVISIBLE);
//                                        iv_eighteen.setVisibility(View.VISIBLE);
//                                        iv_eighteen1.setVisibility(View.INVISIBLE);
//                                        iv_twenty_one.setVisibility(View.INVISIBLE);
//                                        iv_twenty_one1.setVisibility(View.VISIBLE);
//                                        rest = bean.getSpecialTime().get(2).getREST();
//                                        restOver = bean.getSpecialTime().get(2).getRESTOVER();
//
//                                        if (cor == false) {
//                                            tv_nine2.setTextColor(Color.parseColor("#8a8a8a"));
//                                            tv_twelve2.setTextColor(Color.parseColor("#8a8a8a"));
//                                            tv_eighteen2.setTextColor(Color.parseColor("#f19483"));
//                                        }
//                                        //抢购的商品
//                                        int p = data.size();
//                                        if (p > 0) {
//                                            for (int i = p - 1; i >= 0; i--) {
//                                                data.remove(i);
//
//                                            }
//                                        }
//                                        int size3 = bean.getSpecialTime().get(2).getSpecialItem().size();
//                                        if (size3 != 0) {
//                                            for (int i = 0; i < size3; i++) {
//
//                                                viewHead = LayoutInflater.from(getActivity().getApplication()).inflate(R.layout.item_home_recyclerview, null);
//                                                ProgressBar bar = (ProgressBar) viewHead.findViewById(R.id.jindutiao);
//                                                rl = (RelativeLayout) viewHead.findViewById(R.id.rl_home_head);
//                                                TextView tv_price = (TextView) viewHead.findViewById(R.id.home_time_newprice);
//                                                TextView tv_price_old = (TextView) viewHead.findViewById(R.id.home_time_oldprice);
//                                                TextView tv_jdt = (TextView) viewHead.findViewById(R.id.tv_jdt);
//                                                tv_price_old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//                                                TextView tv_title = (TextView) viewHead.findViewById(R.id.home_time_small_title);
//                                                ImageView imageView = (ImageView) viewHead.findViewById(R.id.iv_home);
//
//                                                x.image().bind(imageView, imaurl + bean.getSpecialTime().get(1).getSpecialItem().get(i).getICOURL()
//                                                        , options);
//                                                int a = (int) (bean.getSpecialTime().get(2).getSpecialItem().get(i).getSURITEMNUM() * 100);
//                                                bar.setProgress(a);
//                                                tv_jdt.setText("还剩" + a + "%");
//                                                tv_price_old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//                                                tv_price.setText("¥" + bean.getSpecialTime().get(2).getSpecialItem().get(i).getBARGAIN());
//                                                tv_price_old.setText("¥" + bean.getSpecialTime().get(2).getSpecialItem().get(i).getPRICE());
//                                                tv_title.setText("¥" + bean.getSpecialTime().get(2).getSpecialItem().get(i).getTRADENAME());
//                                                //TODO
//    //                            bar.setProgress(progressStatus);
//                                                //监听item跳转
//                                                final int finalI = i;
//                                                rl.setOnClickListener(new View.OnClickListener() {
//                                                    @Override
//                                                    public void onClick(View v) {
//                                                        Intent in = new Intent(getActivity(), DetailsActivity.class);
//                                                        in.putExtra("key", bean.getSpecialTime().get(2).getSpecialItem().get(finalI).getDetailUrl());
//                                                        getActivity().startActivity(in);
//                                                    }
//                                                });
//                                                data.add(viewHead);
//                                            }
//                                        }
//                                        viewPagerHomeHeadAdapter.addData(data);
//                                        viewPager.setAdapter(viewPagerHomeHeadAdapter);
//                                    }
//
//                                    @Override
//                                    public void onError(Throwable throwable, boolean b) {
//
//                                    }
//
//                                    @Override
//                                    public void onCancelled(CancelledException e) {
//
//                                    }
//
//                                    @Override
//                                    public void onFinished() {
//
//                                    }
//                                });
//
//
//                            }
//                        });
//                    }


    //                } else {
    //                    rl_special.setVisibility(View.GONE);
    //                }

//                    Log.e("fzljkdajl", "zoule5");
//                    //倒计时和listview添加数据
//
//                    if (bean.getSpecialTime().size() > 0) {
//                        rest = bean.getSpecialTime().get(0).getREST();
//                        restOver = bean.getSpecialTime().get(0).getRESTOVER();
//    //                        restOver = bean.getSpecialTime().get(0).getRESTOVER();
//                        //开启倒计时的线程
//                        runnable.run();
//
//                        //刚进入界面默认加载抢购是9点的数据
//                        int size4 = bean.getSpecialTime().get(0).getSpecialItem().size();
//                        if (size4 != 0) {
//                            for (int i = 0; i < size4; i++) {
//                                viewHead = LayoutInflater.from(getActivity().getApplication()).inflate(R.layout.item_home_recyclerview, null);
//
//                                rl = (RelativeLayout) viewHead.findViewById(R.id.rl_home_head);
//                                TextView tv_price = (TextView) viewHead.findViewById(R.id.home_time_newprice);
//                                TextView tv_price_old = (TextView) viewHead.findViewById(R.id.home_time_oldprice);
//                                TextView tv_title = (TextView) viewHead.findViewById(R.id.home_time_small_title);
//                                ImageView imageView = (ImageView) viewHead.findViewById(R.id.iv_home);
//                                x.image().bind(imageView, imaurl + bean.getSpecialTime().get(0).getSpecialItem().get(i).getICOURL()
//                                        , options);
//                                tv_price_old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//                                tv_price.setText("¥" + bean.getSpecialTime().get(0).getSpecialItem().get(i).getBARGAIN());
//                                tv_price_old.setText("¥" + bean.getSpecialTime().get(0).getSpecialItem().get(i).getPRICE());
//                                tv_title.setText("¥" + bean.getSpecialTime().get(0).getSpecialItem().get(i).getTRADENAME());
//                                //监听item跳转
//                                final int finalI = i;
//                                rl.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        if (rest == 0 && restOver == 0) {
//                                            Toast.makeText(getActivity(), "活动已结束", Toast.LENGTH_SHORT).show();
//                                        } else {
//                                            Intent in = new Intent(getActivity(), DetailsActivity.class);
//                                            in.putExtra("key", bean.getSpecialTime().get(0).getSpecialItem().get(finalI).getDetailUrl());
//                                            getActivity().startActivity(in);
//                                        }
//                                    }
//                                });
//                                data.add(viewHead);
//                                viewPagerHomeHeadAdapter.addData(data);
//                                //  recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
//                                viewPager.setAdapter(viewPagerHomeHeadAdapter);
//
//                            }
//                        }
//
//                    }


//                    int h = bean.getSpecialTime().size();
//                    if (h != 0) {
//                        //抢购的时间
//                        tv_nine2.setText(bean.getSpecialTime().get(0).getSPETIME() / 3600);
//                        tv_twelve2.setText(bean.getSpecialTime().get(1).getSPETIME() / 3600);
//                        tv_eighteen2.setText(bean.getSpecialTime().get(2).getSPETIME() / 3600);
//                    }


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

    }


//    /**
//     * 判断是否自动滚动
//     */
//
//    private Handler topHandler = new Handler() {
//
//        public void handleMessage(android.os.Message msg) {
//
//            // 让viewPager 滑动到下一页
//            TopviewPager.setCurrentItem(TopviewPager.getCurrentItem() + 1);
//            if (isRunning) {
//                topHandler.sendEmptyMessageDelayed(0, 5000);
//            }
//        }
//
//    };
//
//    //当销毁的时候不滚动
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        isRunning = false;
//    }

    //控制视频播放与否的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playVideo:
                videoview.pause();
                playVideo1.setVisibility(View.VISIBLE);

                break;
            case R.id.playVideo1:
                videoview.start();
                playVideo1.setVisibility(View.INVISIBLE);
                playVideoCenter.setVisibility(View.GONE);
                break;
        }


    }


    //搜索的监听
//    @Event(R.id.home_ll)
//    private void ediClick(View view) {
//        Intent intent = new Intent(getActivity(), SearchActivity.class);
//        startActivity(intent);
//        getActivity().overridePendingTransition(R.anim.activity_in, R.anim.activity_out);// 淡出淡入动画效果
//    }


    public class MyListener implements XListView.IXListViewListener {

        @Override
        public void onRefresh() {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    RequestParams params = new RequestParams(url);
                    x.http().get(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String s) {
                            Gson gson = new Gson();
                            HomeBean bean = gson.fromJson(s, HomeBean.class);


                            rest = bean.getSpecialTime().get(0).getSPETIME();
                            homeAdapter.addData(bean.getSTORY());
//                            //listview的设置
//                            listView.setPullLoadEnable(true);
//                            //添加监听
//                            listView.setXListViewListener(new MyListener());
                            //listView添加适配器
                            listView.setAdapter(homeAdapter);

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
                    onLoad();
                }
            }, 2000);
        }

        @Override
        public void onLoadMore() {
            mHandler.postDelayed(new Runnable() {
                                     @Override
                                     public void run() {
                                         onLoad();
                                     }
                                 }

                    , 2000);
        }

    }

    //    public static void test(){
    final Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (rest == 0 && restOver == 0) {
                tv_sale.setText("活动结束");
            } else if (rest == 0 && restOver > 0) {
                restOver--;
                long hour = restOver / 3600;
                long minute = (restOver - hour * 3600) / 60;
                long sec = restOver - hour * 3600 - minute * 60;
                tv_sale.setText("结束还剩" + hour + "小时" + minute + "分钟" + sec + "秒");
            } else if (rest > 0) {
                rest--;
                long hour = rest / 3600;
                long minute = (rest - hour * 3600) / 60;
                long sec = rest - hour * 3600 - minute * 60;
                tv_sale.setText("还剩" + hour + "小时" + minute + "分钟" + sec + "秒");
            }
            handler.postDelayed(this, 1000);
        }
    };
//    }


    private void onLoad() {
        listView.stopRefresh();
        listView.stopLoadMore();
//        listView.setRefreshTime("刚刚");
    }

//    private void initViews() {
//
//        // 添加图片的Url地址
//        JSONArray advertiseArray = new JSONArray();
//        try {
//            JSONObject head_img0 = new JSONObject();
//            head_img0.put("head_img","http://192.168.1.212:3971//Image/img/b33daef2-aa80-45ce-83e6-4bc23b7d84ef.jpg");
//            JSONObject head_img1 = new JSONObject();
//            head_img1.put("head_img","http://192.168.1.212:3971//Image/img/1089020c-4f57-4861-8883-847d73c5e95b.jpg");
////            JSONObject head_img2 = new JSONObject();
////            head_img2.put("head_img","http://pic1.ooopic.com/uploadfilepic/sheji/2009-09-12/OOOPIC_wenneng837_200909122b2c8368339dd52a.jpg");
////            JSONObject head_img3 = new JSONObject();
////            head_img3.put("head_img","http://img.xiaba.cvimage.cn/4cbc56c1a57e26873c140000.jpg");
//            advertiseArray.put(head_img0);
//            advertiseArray.put(head_img1);
////            advertiseArray.put(head_img2);
////            advertiseArray.put(head_img3);
//        } catch (JSONException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        llAdvertiseBoard.addView(new Advertisements(getActivity(), true, inflater, 3000).initView(advertiseArray));
//    }
}
