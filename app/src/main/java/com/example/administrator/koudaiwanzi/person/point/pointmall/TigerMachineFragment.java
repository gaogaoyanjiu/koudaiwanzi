package com.example.administrator.koudaiwanzi.person.point.pointmall;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.BaseFragmentAndroid;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.person.point.pointmall.widget.OnWheelChangedListener;
import com.example.administrator.koudaiwanzi.person.point.pointmall.widget.OnWheelScrollListener;
import com.example.administrator.koudaiwanzi.person.point.pointmall.widget.WheelView;
import com.example.administrator.koudaiwanzi.person.point.pointmall.widget.adapters.AbstractWheelAdapter;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 老虎机抽奖界面
 */

@ContentView(R.layout.activity_tiger_machine)
public class TigerMachineFragment extends BaseFragmentAndroid {
    private TextView resultTipstv;
    private String url1 = MyUrl.url;
    private String url;
    private String TAG = "NUM";
    private String UID = "";
    private String url2;

    @ViewInject(R.id.lv_tiger)
    private ListView lv_tiger;

    private TigerAdapter adapter;

    CallBackValue callBackValue;
//	@ViewInject(R.id.tv_mall_score)
//	private TextView tv_point;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callBackValue = (CallBackValue) getActivity();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences share = getActivity().getSharedPreferences("logIn", Context.MODE_PRIVATE);
        UID = share.getString("login", "");

        String url_tiger = getActivity().getIntent().getStringExtra("tiger");

        Log.e("zfrew", url_tiger);

        url2 = url1 + "Integral.svc/draw/" + UID;

        Log.e("dfxv", url2);

        RequestParams params = new RequestParams(url2);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                TigerMachineBean bean = gson.fromJson(s, TigerMachineBean.class);
//				tv_point.setText(bean.getPOINT()+"");


                SharedPreferences share_url = getActivity().getSharedPreferences("share_url", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = share_url.edit();
                editor.putString("share_url", bean.getDetailUrl() +"");
                editor.apply();



                Log.e("djkkd", String.valueOf(bean.getPOINT()));
                //传入积分
                callBackValue.sendMessageValue(bean.getPOINT());
                Log.e("djkkd", String.valueOf(bean.getPOINT())+"11111111111");


                adapter = new TigerAdapter(getActivity(), bean.getHISDRAW(), R.layout.tiger_item);
                lv_tiger.setAdapter(adapter);

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
        initWheel(R.id.slot_1);
        initWheel(R.id.slot_2);
        initWheel(R.id.slot_3);

//		resultTipstv = (TextView) findViewById(R.id.pwd_status);
        Button mix = (Button) getActivity().findViewById(R.id.btn_mix);
        mix.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                initWheel(R.id.slot_1);
                initWheel(R.id.slot_2);
                initWheel(R.id.slot_3);
                url = url1 + "Integral.svc/GetNewPrize/" + UID;
                RequestParams params = new RequestParams(url);
                Log.e("zvcgd", url);
                x.http().get(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Gson gson = new Gson();
                        TigerBean bean = gson.fromJson(s, TigerBean.class);
                        int a = bean.getMsg();
                        if (a == 1) {
                            int bat = bean.getBATMAN();
                            int mario = bean.getMARIO();
                            int amon = bean.getDORAEMON();

                            Log.d(TAG, bat + "dasdsadsad" + mario + "dsadsdasdsa" + amon);
                            if (bat == 0) {
                                WheelView wheel = getWheel(R.id.slot_1);
                                wheel.scroll(-310, 4000);
                            } else if (bat == 1) {
                                WheelView wheel = getWheel(R.id.slot_1);
                                wheel.scroll(-320, 4000);
                            } else {
                                WheelView wheel = getWheel(R.id.slot_1);
                                wheel.scroll(-330, 4000);
                            }

                            if (mario == 0) {
                                WheelView whee2 = getWheel(R.id.slot_2);
                                whee2.scroll(-310, 4000);
                            } else if (mario == 1) {
                                WheelView whee2 = getWheel(R.id.slot_2);
                                whee2.scroll(-320, 4000);
                            } else {
                                WheelView whee2 = getWheel(R.id.slot_2);
                                whee2.scroll(-330, 4000);
                            }

                            if (amon == 0) {
                                WheelView whee3 = getWheel(R.id.slot_3);
                                whee3.scroll(-310, 4000);
                            } else if (amon == 1) {
                                WheelView whee3 = getWheel(R.id.slot_3);
                                whee3.scroll(-320, 4000);
                            } else {
                                WheelView whee3 = getWheel(R.id.slot_3);
                                whee3.scroll(-330, 4000);
                            }
                        } else if (a == 2) {
                            Toast.makeText(getActivity(), "积分不足无法抽奖", Toast.LENGTH_SHORT).show();
                        } else if (a == 3) {
                            Toast.makeText(getActivity(), "您今日抽奖次数已达上限", Toast.LENGTH_SHORT).show();
                        }

                        new Handler().postDelayed(new Runnable() {

                            public void run() {

                                RequestParams params = new RequestParams(url2);
                                x.http().get(params, new CommonCallback<String>() {
                                    @Override
                                    public void onSuccess(String s) {
                                        Gson gson = new Gson();
                                        TigerMachineBean bean = gson.fromJson(s, TigerMachineBean.class);
                                        //传入积分
                                        callBackValue.sendMessageValue(bean.getPOINT());

                                        adapter = new TigerAdapter(getActivity(), bean.getHISDRAW(), R.layout.tiger_item);
                                        lv_tiger.setAdapter(adapter);


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

                        }, 4000);   //4秒

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
        });
    }

    //传入积分的接口
    public interface CallBackValue {
        public void sendMessageValue(int value);
    }


    // 车轮滚动标志
    private boolean wheelScrolled = false;

    // 车轮滚动的监听器
    OnWheelScrollListener scrolledListener = new OnWheelScrollListener() {
        public void onScrollingStarted(WheelView wheel) {
            wheelScrolled = true;
        }

        public void onScrollingFinished(WheelView wheel) {
            wheelScrolled = false;
            System.out.println("轮子---->" + wheel.getCurrentItem());
            updateStatus();
        }
    };

    // 车轮item改变的监听器
    private OnWheelChangedListener changedListener = new OnWheelChangedListener() {
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            if (!wheelScrolled) {
                System.out.println("轮子item---->" + wheel.getCurrentItem());
                // updateStatus();

            }
        }
    };

    /**
     * 更新状态
     */
    private void updateStatus() {
        resultTipstv = (TextView) getActivity().findViewById(R.id.pwd_status);

        int value = getWheel(R.id.slot_1).getCurrentItem();
        int value1 = getWheel(R.id.slot_2).getCurrentItem();
        int value2 = getWheel(R.id.slot_3).getCurrentItem();
        resultTipstv.setText("运气不错哦！" + (value % 10) + ",,," + (value1 % 10) + ",,," + (value2 % 10));

    }

    /**
     * 初始化轮子
     *
     * @param id the wheel widget Id
     */
    private void initWheel(int id) {
        WheelView wheel = getWheel(id);
        wheel.setViewAdapter(new SlotMachineAdapter(getActivity()));
        wheel.setCurrentItem((int) (Math.random() * 10));
        wheel.setCurrentItem(0);
        wheel.addChangingListener(changedListener);
        wheel.addScrollingListener(scrolledListener);
        wheel.setCyclic(true);
        wheel.setEnabled(false);
    }

    /**
     * 根据id获取轮子
     *
     * @param id the wheel Id
     * @return the wheel with passed Id
     */
    private WheelView getWheel(int id) {
        return (WheelView) getActivity().findViewById(id);
    }

    /**
     * 测试轮子转动位置
     *
     * @return true
     */
    private boolean test() {
        int value = getWheel(R.id.slot_1).getCurrentItem();
        return testWheelValue(R.id.slot_2, value) && testWheelValue(R.id.slot_3, value);
    }

    /**
     * 根据轮子id获取当前item值
     *
     * @param id    the wheel Id
     * @param value the value to test
     * @return true if wheel value is equal to passed value
     */
    private boolean testWheelValue(int id, int value) {
        return getWheel(id).getCurrentItem() == value;
    }

    /**
     * 转动轮子
     *
     * @param id the wheel id
     */
    private void mixWheel(int id) {
        WheelView wheel = getWheel(id);
        wheel.scroll(-320, 3000);
        // wheel.scroll(round, time);
        // wheel.scroll((int)(Math.random() * 50)+round, time);
        int num1 = -350 + (int) (Math.random() * 50);
        Log.i(TAG, "num1===>>" + num1);
        wheel.scroll(num1, 3000);
    }

    private void mixWheel2(int id) {
        WheelView wheel = getWheel(id);

        // wheel.scroll(round, time);
        // wheel.scroll((int)(Math.random() * 50)+round, time);
        int num2 = -350 + (int) (Math.random() * 50);
        Log.i(TAG, "num2===>>" + num2);
        wheel.scroll(num2, 4000);
    }

    private void mixWheel3(int id) {
        WheelView wheel = getWheel(id);
        // wheel.scroll(round, time);
        // wheel.scroll((int)(Math.random() * 50)+round, time);
        int num3 = -350 + (int) (Math.random() * 50);
        Log.i(TAG, "num3===>>" + num3);
        wheel.scroll(num3, 6000);
    }

    private void mixWheel5(int id, int id1, int id2) {
        WheelView wheel = getWheel(id);
        WheelView whee2 = getWheel(id1);
        WheelView whee3 = getWheel(id2);
        int num1 = -350 + (int) (Math.random() * 50);
        int num2 = -350 + (int) (Math.random() * 50);
        int num3 = -350 + (int) (Math.random() * 50);
        Log.d(TAG, num1 + "?" + num2 + "?" + num3);
        wheel.scroll(-320, 3000);
        whee2.scroll(-310, 4000);
        whee3.scroll(-330, 6000);
    }

    private void mixWheel4(int id, int id1, int id2) {
        WheelView wheel = getWheel(id);
        WheelView whee2 = getWheel(id1);
        WheelView whee3 = getWheel(id2);
        int num1 = -350 + (int) (Math.random() * 50);
        wheel.scroll(num1, 3000);
        whee2.scroll(num1, 4000);
        whee3.scroll(num1, 6000);
    }

    /**
     * 老虎机适配器
     */
    public class SlotMachineAdapter extends AbstractWheelAdapter {
        // 图片的大小
        final int IMAGE_WIDTH = 120;
        final int IMAGE_HEIGHT = 140;

        // 图片的数组
        private final int items[] = new int[]{R.mipmap.num11, R.mipmap.num22, R.mipmap.num33,
        };

        // 对图片的缓存
        private List<SoftReference<Bitmap>> images;

        // 布局膨胀器
        private Context context;

        /**
         * 构造函数
         */
        public SlotMachineAdapter(Context context) {
            this.context = context;
            images = new ArrayList<SoftReference<Bitmap>>(items.length);
            for (int id : items) {
                images.add(new SoftReference<Bitmap>(loadImage(id)));
            }
        }

        /**
         * 从资源加载图片
         */
        private Bitmap loadImage(int id) {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), id);
            Bitmap scaled = Bitmap.createScaledBitmap(bitmap, IMAGE_WIDTH, IMAGE_HEIGHT, true);
            bitmap.recycle();
            return scaled;
        }

        @Override
        public int getItemsCount() {
            return items.length;
        }

        // 设置图片布局的参数
        final LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            ImageView img;
            if (cachedView != null) {
                img = (ImageView) cachedView;
            } else {
                img = new ImageView(context);
            }
            img.setLayoutParams(params);
            // img.setScaleType(ScaleType.FIT_XY);
            SoftReference<Bitmap> bitmapRef = images.get(index);
            Bitmap bitmap = bitmapRef.get();
            if (bitmap == null) {
                bitmap = loadImage(items[index]);
                images.set(index, new SoftReference<Bitmap>(bitmap));
            }
            img.setImageBitmap(bitmap);
            return img;
        }
    }

    //返回按键的监听

    @Event(R.id.iv_tiger)
    private void imgClick(View view) {
        getActivity().finish();
    }


}
