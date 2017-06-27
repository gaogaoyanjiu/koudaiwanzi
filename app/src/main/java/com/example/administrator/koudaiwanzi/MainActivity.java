package com.example.administrator.koudaiwanzi;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.classification.ClassificationFragment;
import com.example.administrator.koudaiwanzi.home.HomeFragment;
import com.example.administrator.koudaiwanzi.meatball.MeatBallFragment;
import com.example.administrator.koudaiwanzi.person.PersonFragment;
import com.example.administrator.koudaiwanzi.person.login.LoginActivity;
import com.example.administrator.koudaiwanzi.shopcar.ShopCarFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(value = R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    private FragmentManager fm;
    private String UID = "1";
    private String t4 = "4";
    private String t5 = "2";
    private String t3 = "3";
    private String t2 = "5";
    private String t1 = "6";
    private LocalBroadcastManager broadcastManager2;
    private IntentFilter intentFilter2;
    private BroadcastReceiver mReceiver2;

    @ViewInject(R.id.split_ll)
    private LinearLayout liLayout;

    private DisplayMetrics metrics;
    int halfWidth;
    int height;
    int screen_w;
    int screen_h;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    //tabHost绑定ID
    @ViewInject(android.R.id.tabhost)
    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //xutiles3初始化
        x.view().inject(this);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        screen_w = getWindowManager().getDefaultDisplay().getWidth();
//        screen_h = getWindowManager().getDefaultDisplay().getHeight();
//
//        Log.e("ffc", "screen_w  ===" + screen_w + "  screen_h== " + screen_h);
//
//
//        metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//
//        Log.e("byf", "------------metrics.widthPixels =" + metrics.widthPixels);
//        Log.e("byf", "------------metrics.heightPixels =" + metrics.heightPixels);
//
//        halfWidth = metrics.widthPixels / 2;
//        height = metrics.heightPixels;
//        Log.e("byf", "------------halfWidth =" + halfWidth);
//
//        Animation animation = new TranslateAnimation(0, -(halfWidth), 0, 0);// 向左移动的动画效果
//        animation.setDuration(1200);// 设置动画过程时间
//        animation.setStartOffset(1000);// 设置动画延时时间//一秒后开
//        animation.setRepeatCount(0);
//
//
//        Animation animation1 = new TranslateAnimation(0, (float) (halfWidth), 0, 0);// 向右移动的动画效果
//        animation1.setDuration(1200);
//        animation1.setStartOffset(1000);
//        animation1.setRepeatCount(0);
//
//        final ImageView tt = new ImageView(this);
//        tt.setLayoutParams(new ViewGroup.LayoutParams(halfWidth, (metrics.heightPixels)));// 设置改ImageView
//        // 的大小，宽为屏幕的一半，高为屏幕的高度
//        // 这样做的好处可以对不同屏幕分辨率进行适配
//        Bitmap leftBmp = getBitmap(0);
//        tt.setImageBitmap(leftBmp);
//        tt.setScaleType(ImageView.ScaleType.FIT_XY);
//
//        final ImageView tt1 = new ImageView(this);
//        tt1.setLayoutParams(new ViewGroup.LayoutParams(halfWidth, (metrics.heightPixels)));
//        Bitmap rightBmp = getBitmap(1);
//        tt1.setImageBitmap(rightBmp);
//        tt1.setScaleType(ImageView.ScaleType.FIT_XY);
//
//
////        sharedPreferences = getSharedPreferences("Shop", Context.MODE_PRIVATE);
////        if (sharedPreferences.getBoolean("first", true)) {
////            editor = sharedPreferences.edit();
////            editor.putBoolean("first", false);
////            editor.commit();
//            tt.setAnimation(animation);// 设置动画效果
//            tt1.setAnimation(animation1);// 设置动画效果me
//
//
//            animation.setAnimationListener(new Animation.AnimationListener() {// 设置动画监听
//                @Override
//                public void onAnimationStart(Animation animation) {// 动画状态开始
//                    //Toast.makeText(context, "I'm begin", 0).show();// 日志提示开始了
//                    // 如果想开们效果中出现锯齿效果，在动画开始时，在本位置，将ImageView 的背景置换一下就可以了
//
//                }
//
//                @Override
//                public void onAnimationRepeat(Animation animation) {
//
//                }
//
//                @Override
//                public void onAnimationEnd(Animation animation) {// 动画状态结束
//                    tt.setVisibility(View.GONE);// 设置改控件隐藏，当然你也可以在结束这个位置放置其他处理。
//
//                }
//            });
//
//            /**
//             * 与上一个监听一样
//             */
//            animation1.setAnimationListener(new Animation.AnimationListener() {
//
//                @Override
//                public void onAnimationStart(Animation animation) {
//
//                }
//
//                @Override
//                public void onAnimationRepeat(Animation animation) {
//
//                }
//
//                @Override
//                public void onAnimationEnd(Animation animation) {
//                    tt1.setVisibility(View.GONE);
//                }
//            });
//
//            liLayout.addView(tt);
//            liLayout.addView(tt1);

//        } else {
//
//        }


        Intent intent = getIntent();
        String tiao = intent.getStringExtra("tiaozhuan");
        String wanzishuo = intent.getStringExtra("wanzishuo");
        String car = intent.getStringExtra("Merge");
        String type = intent.getStringExtra("class");
        String clas = intent.getStringExtra("fenlei");
        tabHost.setup();

        View viewHome = LayoutInflater.from(this).inflate(R.layout.tabhost_home, null);
        tabHost.addTab(tabHost.newTabSpec("news").setIndicator(viewHome).setContent(R.id.tb_homePage));

        View viewClass = LayoutInflater.from(this).inflate(R.layout.tabhost_classification, null);
        tabHost.addTab(tabHost.newTabSpec("book").setIndicator(viewClass).setContent(R.id.tb_classification));

        View viewJapanese = LayoutInflater.from(this).inflate(R.layout.tabhost_japanese, null);
        tabHost.addTab(tabHost.newTabSpec("comic").setIndicator(viewJapanese).setContent(R.id.tb_japanese));

        View viewShopCar = LayoutInflater.from(this).inflate(R.layout.tabhost_shopcar, null);
        tabHost.addTab(tabHost.newTabSpec("funny").setIndicator(viewShopCar).setContent(R.id.tb_shopCar));

        View viewPerson = LayoutInflater.from(this).inflate(R.layout.tabhost_person, null);
        tabHost.addTab(tabHost.newTabSpec("person").setIndicator(viewPerson).setContent(R.id.tb_person));


        SharedPreferences share = getSharedPreferences("logIn", Context.MODE_PRIVATE);
        UID = share.getString("login", "");

        //tabHost购物车ID绑定
        LinearLayout iv_main_shop = (LinearLayout) viewShopCar.findViewById(R.id.ll_home_shopCar);
        LinearLayout iv_main_japan = (LinearLayout) viewJapanese.findViewById(R.id.ll_home_japan);

        LinearLayout iv_my = (LinearLayout) viewPerson.findViewById(R.id.ll_home_my);
        iv_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences share = getSharedPreferences("logIn", Context.MODE_PRIVATE);
                UID = share.getString("login", "");
                if (UID == "") {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.putExtra("me", 2);
                    startActivityForResult(intent, 5);
                } else {
                    tabHost.setCurrentTab(4);
                    fm = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction1 = fm.beginTransaction();
                    fragmentTransaction1.replace(R.id.tb_person, new PersonFragment());
                    fragmentTransaction1.commit();

                }
            }
        });

        fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.tb_homePage, new HomeFragment());
        fragmentTransaction.replace(R.id.tb_classification, new ClassificationFragment());
        fragmentTransaction.replace(R.id.tb_japanese, new MeatBallFragment());
        fragmentTransaction.replace(R.id.tb_shopCar, new ShopCarFragment());
        fragmentTransaction.replace(R.id.tb_person, new PersonFragment());

        fragmentTransaction.commit();

        if (t1.equals(clas)) {
            tabHost.setCurrentTab(1);
            FragmentTransaction fragmentTransaction1 = fm.beginTransaction();
            fragmentTransaction1.replace(R.id.tb_classification, new ClassificationFragment());
            fragmentTransaction1.commit();
        }

        if (t2.equals(type)) {
            tabHost.setCurrentTab(1);
            FragmentTransaction fragmentTransaction1 = fm.beginTransaction();
            fragmentTransaction1.replace(R.id.tb_classification, new ClassificationFragment());
            fragmentTransaction1.commit();
        }

//        if (t4.equals(tiao)) {
//            tabHost.setCurrentTab(4);
//            FragmentTransaction fragmentTransaction1 = fm.beginTransaction();
//            fragmentTransaction1.replace(R.id.tb_person, new PersonFragment());
//            fragmentTransaction1.commit();
//        }

        if (t5.equals(wanzishuo)) {
            tabHost.setCurrentTab(2);
            FragmentTransaction fragmentTransaction1 = fm.beginTransaction();
            fragmentTransaction.replace(R.id.tb_japanese, new MeatBallFragment());
            fragmentTransaction1.commit();
        }


        if (t3.equals(car)) {
            tabHost.setCurrentTab(3);
            FragmentTransaction fragmentTransaction1 = fm.beginTransaction();
            fragmentTransaction.replace(R.id.tb_shopCar, new ShopCarFragment());
            fragmentTransaction1.commit();
        }


        broadcastManager2 = LocalBroadcastManager.getInstance(MainActivity.this);
        intentFilter2 = new IntentFilter();
        intentFilter2.addAction("com.example.administrator.koudaiwanzi.shopca.broadcast");
        mReceiver2 = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                tabHost.setCurrentTab(3);
                fm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction1 = fm.beginTransaction();
                fragmentTransaction1.replace(R.id.tb_shopCar, new ShopCarFragment());
                fragmentTransaction1.commitAllowingStateLoss();
            }
        };
        broadcastManager2.registerReceiver(mReceiver2, intentFilter2);


        //点击首页标签 发送一条广播 首页接收到广播后停止播放视频
        ImageView homeIV = (ImageView) viewHome.findViewById(R.id.homeIV);
        homeIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabHost.setCurrentTab(0);
                sendBroadcast();

            }
        });


        ImageView classfIV = (ImageView) viewClass.findViewById(R.id.classfIV);
        classfIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabHost.setCurrentTab(1);
                sendBroadcast();

            }
        });

//        ImageView tabhost_japanese = (ImageView) viewJapanese.findViewById(R.id.tabhost_japanese);
//        tabhost_japanese.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tabHost.setCurrentTab(2);
//                //sendBroadcast();
//                Intent intent1 = new Intent("com.example.administrator.myapplication.main.main.home.broadcast");
//                LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(intent1);
//
//            }
//        });

        //点击购物车图片
        iv_main_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast();
                SharedPreferences share = getSharedPreferences("logIn", Context.MODE_PRIVATE);
                UID = share.getString("login", "");
                if (UID == "") {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.putExtra("me", 2);
                    startActivityForResult(intent, 5);
                } else {
                    tabHost.setCurrentTab(3);
                    fm = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction1 = fm.beginTransaction();
                    fragmentTransaction1.replace(R.id.tb_shopCar, new ShopCarFragment());
                    fragmentTransaction1.commit();
                }
            }
        });

        iv_main_japan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "敬请期待", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //回调方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int value = data.getIntExtra("our", 2);
//        int value = 2;
        switch (requestCode) {
            case 5:
                if (value == 6) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    tabHost.setCurrentTab(value);
                    FragmentTransaction fragmentTransaction1 = fm.beginTransaction();
                    fragmentTransaction1.replace(R.id.tb_person, new PersonFragment());
                    fragmentTransaction1.commit();
                }
                break;
        }
    }

    //发送广播的方法
    public void sendBroadcast() {
        Intent intent1 = new Intent("com.example.administrator.myapplication.main.main.home.broadcast");
        LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(intent1);
        Intent intent2 = new Intent("com.meatball.broadcast");
        LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(intent2);

    }


//    private Bitmap getBitmap(int type) {
//        Bitmap bmp = null;
//        bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.banner1);
//        if (type == 0) {
//            bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth() / 2, bmp.getHeight());
//        } else if (type == 1) {
//            bmp = Bitmap.createBitmap(bmp, bmp.getWidth() / 2, 0, bmp.getWidth() / 2, bmp.getHeight());
//        }
//        return bmp;
//    }


    /**
     * 02.     * 返回监听
     * 03.
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 监听返回键，相当于点击home键
        PackageManager pm = getPackageManager();
        ResolveInfo homeInfo = pm.resolveActivity(
                new Intent(Intent.ACTION_MAIN)
                        .addCategory(Intent.CATEGORY_HOME), 0);
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ActivityInfo ai = homeInfo.activityInfo;
            Intent startIntent = new Intent(Intent.ACTION_MAIN);
            startIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            startIntent
                    .setComponent(new ComponentName(ai.packageName, ai.name));
            startActivitySafely(startIntent);
            return true;
        } else
            return super.onKeyDown(keyCode, event);
    }


    private void startActivitySafely(Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "ActivityNotFoundExceptionnull",
                    Toast.LENGTH_SHORT).show();
        } catch (SecurityException e) {
            Toast.makeText(this, "SecurityExceptionnull", Toast.LENGTH_SHORT)
                    .show();
        }
    }


}
