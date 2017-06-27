package com.example.administrator.koudaiwanzi.person;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.administrator.koudaiwanzi.MainActivity;
import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.BaseFragment;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.dialog.CustomProgressDialog;
import com.example.administrator.koudaiwanzi.person.address.AddressActivity;
import com.example.administrator.koudaiwanzi.person.collection.CollectionActivity;
import com.example.administrator.koudaiwanzi.person.concern.ConcernActivity;
import com.example.administrator.koudaiwanzi.person.coupon.CouponActivity;
import com.example.administrator.koudaiwanzi.person.inviter.InviterActivity;
import com.example.administrator.koudaiwanzi.person.mymeatball.MyMeatBallActivity;
import com.example.administrator.koudaiwanzi.person.order.OrderAllActivity;
import com.example.administrator.koudaiwanzi.person.point.MyPointActivity;
import com.example.administrator.koudaiwanzi.person.question.QuestionActivity;
import com.example.administrator.koudaiwanzi.person.reject.MyRejectActivity;
import com.example.administrator.koudaiwanzi.person.reject.RejectActivity;
import com.example.administrator.koudaiwanzi.person.set.AccountActivity;
import com.example.administrator.koudaiwanzi.person.set.AccountBean;
import com.example.administrator.koudaiwanzi.shopcar.ShopCarFragment;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Administrator on 2016/5/30.
 */


@ContentView(R.layout.fragment_person)
public class PersonFragment extends BaseFragment{
    private RequestQueue queue;
    private String url1 = MyUrl.url;
    private String UID = "1";
    private String url;
    public static final String ARGUMENT = "argument";
    private  CustomProgressDialog dialog;
    private  Dialog dialog2;
    private View inflate;
    private LocalBroadcastManager broadcastManager2;
    private IntentFilter intentFilter2;
    private BroadcastReceiver mReceiver2;


    @ViewInject(R.id.rl_person_address)
    private RelativeLayout rl_address;


    @ViewInject(R.id.rl_person_inviter)
    private RelativeLayout iv_inviter;

    @ViewInject(R.id.person_price_rel)
    private RelativeLayout rl_concern;

    //绑定我的收藏的数量
    @ViewInject(R.id.tv_person_collect)
    private TextView tv_collect;

    //绑定收藏的相对布局
    @ViewInject(R.id.person_collect_rel)
    private RelativeLayout rl_collect;

    //绑定账户金额
    @ViewInject(R.id.tv_person_price)
    private TextView tv_price;

    //绑定积分数量
    @ViewInject(R.id.tv_person_score)
    private TextView tv_score;

    @ViewInject(R.id.person_score_rel)
    private RelativeLayout rl_score;

    //绑定优惠券数量
    @ViewInject(R.id.tv_person_coupon)
    private TextView tv_coupon;

    //绑定用户信息设置页面
    @ViewInject(R.id.iv_person_head)
    private CircleImageView iv_person_head;

    //绑定跳转收货地址的ID
    @ViewInject(R.id.manage_address)
    private ImageView iv_address;

    //绑定我的页面的用户头像
    @ViewInject(R.id.iv_person_head)
    private ImageView iv_head;

    //绑定我的页面的用户名称
    @ViewInject(R.id.tv_person_name)
    private TextView tv_name;

    //绑定优惠券ID
    @ViewInject(R.id.person_coupon_rel)
    private RelativeLayout rl_coupon;

    @ViewInject(R.id.my_meatBall_re)
    private RelativeLayout my_meatBall_re;

    //跳转到退货/退款界面
    @ViewInject(R.id.rl_reject)
    private RelativeLayout rl_reject;
    private LocalBroadcastManager broadcastManager;
    private IntentFilter intentFilter;
    private BroadcastReceiver mReceiver;
    private String meatballstory;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dialog = new CustomProgressDialog(getActivity(), R.style.CommProgressDialog, "加载中...", R.anim.frame2);
        dialog.setProgressStyle(R.style.CommProgressDialog);
        dialog.show();

        SharedPreferences share = getActivity().getSharedPreferences("logIn", Context.MODE_PRIVATE);
        UID = share.getString("login", "");

        if (UID.equals("")||UID == null || UID == ""){
            url = url1 + "Users.svc/useritem/" + "1";
            dialog.cancel();
        }else {
            url = url1 + "Users.svc/useritem/" + UID;
        }

        //我 标签下的总URL uid是传过来的 需要登录后才有uid

        Log.e("person", url);
        broadcastManager = LocalBroadcastManager.getInstance(getActivity().getApplication());
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.administrator.myapplication.main.main.person.broadcast");
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                RequestParams params = new RequestParams(url);
                x.http().get(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        dialog.cancel();
                        Gson gson = new Gson();
                        final PersonBean p = gson.fromJson(s, PersonBean.class);
                        tv_collect.setText(p.getCOLNUM() + "");
                        tv_name.setText(p.getUSERNAME()+"");
                        String b = url1 + p.getHEADIMG();
                        Picasso.with(getActivity()).load(b).resize(200, 200).into(iv_head);

                        RequestParams params_my = new RequestParams(url1 + p.getMINEBLOYSTORY());
                        Log.d("p.gget1", url1 + p.getMINEBLOYSTORY());
                        x.http().get(params_my, new CommonCallback<String>() {
                            @Override
                            public void onSuccess(String s) {
                                Gson gson = new Gson();
                                final MyMeatBallNewBean myMeatBallBean = gson.fromJson(s, MyMeatBallNewBean.class);
                                SharedPreferences share_meatball = getActivity().getSharedPreferences("meatballstory", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor_meatball = share_meatball.edit();
                                editor_meatball.putString("meatballstory", url1 + p.getMINEBLOYSTORY());
                                Log.d("443233", myMeatBallBean.getDetailUrl());
                                Log.d("556566", url1 + p.getMINEBLOYSTORY());
                                editor_meatball.apply();

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
                        SharedPreferences share2 = getActivity().getSharedPreferences("invite", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = share2.edit();
                        editor.putString("invite", p.getINVITECODE());
                        editor.apply();

                        //跳转到退款界面

                        rl_reject.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), MyRejectActivity.class);
                                intent.putExtra("reject", p.getRETURNITEMURL());
                                startActivity(intent);
                            }
                        });

                        //监听跳转到优惠券界面
                        rl_coupon.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), CouponActivity.class);
                                intent.putExtra("coupon", p.getUSERCOUPONURL());
                                startActivity(intent);
                            }
                        });

                        //监听跳转的收藏页面
                        rl_collect.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), CollectionActivity.class);
                                intent.putExtra("collect", p.getCOLLECTIONURL());
                                startActivity(intent);
                            }
                        });

                        //跳转账户管理页面的监听
                        iv_person_head.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                SharedPreferences share = getActivity().getSharedPreferences("logIn", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = share.edit();
                                editor.putString("account", p.getACCMANAGEMENT());
                                editor.apply();
                                Intent intent = new Intent(getActivity(), AccountActivity.class);
                                startActivity(intent);

                            }
                        });

                        //监听跳转到邀请绑定人界面
                        iv_inviter.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), InviterActivity.class);
                                intent.putExtra("invite", p.getINVITEURL());
                                startActivity(intent);
                            }
                        });


                        //监听跳转到地址管理页面
                        rl_address.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                SharedPreferences share2 = getActivity().getSharedPreferences("address_order", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor2 = share2.edit();
                                editor2.putString("address_order", p.getADRESSMANAGEURL());
                                editor2.apply();
                                SharedPreferences share = getActivity().getSharedPreferences("AddressBack", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = share.edit();
                                editor.putString("AddressBack", "2");
                                editor.apply();
                                Intent intent = new Intent(getActivity(), AddressActivity.class);
                                intent.putExtra("address", p.getADRESSMANAGEURL());
                                startActivity(intent);
                            }
                        });

                        tv_coupon.setText(p.getCOUNUM() + "");
                        tv_score.setText(p.getPOINT() + "");

                        //跳转到我的积分界面
                        rl_score.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), MyPointActivity.class);
                                intent.putExtra("score", p.getPOINT() + "");
                                startActivity(intent);
                            }
                        });

                        tv_price.setText(p.getCONUM() + "");

                        //跳转到我的关注界面
                        rl_concern.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(), "敬请期待", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(getActivity(), ConcernActivity.class);
//                                intent.putExtra("concern", p.getMINECONCEMURL());
//                                startActivity(intent);
                            }
                        });

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
        };
        broadcastManager.registerReceiver(mReceiver, intentFilter);


        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                final PersonBean p = gson.fromJson(s, PersonBean.class);
                tv_collect.setText(p.getCOLNUM() + "");

                tv_name.setText(p.getUSERNAME()+"");
                String b = url1 + p.getHEADIMG();
                Picasso.with(getActivity()).load(b).resize(200, 200).into(iv_head);
                RequestParams params_my = new RequestParams(url1 + p.getMINEBLOYSTORY());
                Log.d("p.gget", url1 + p.getMINEBLOYSTORY());
                x.http().get(params_my, new CommonCallback<String>() {

                    @Override
                    public void onSuccess(String s) {
                        dialog.cancel();
                        Gson gson = new Gson();
                        final MyMeatBallNewBean myMeatBallBean = gson.fromJson(s, MyMeatBallNewBean.class);
                        SharedPreferences share_meatball = getActivity().getSharedPreferences("meatballstory", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor_meatball = share_meatball.edit();
                        editor_meatball.putString("meatballstory", url1 + p.getMINEBLOYSTORY());
                        Log.d("443233", myMeatBallBean.getDetailUrl());
                        Log.d("556566", url1 + p.getMINEBLOYSTORY());
                        editor_meatball.apply();

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
                SharedPreferences share2 = getActivity().getSharedPreferences("invite", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = share2.edit();
                editor.putString("invite", p.getINVITECODE());
                editor.apply();

                //跳转到退款界面

                rl_reject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), MyRejectActivity.class);
                        intent.putExtra("reject", p.getRETURNITEMURL());
                        startActivity(intent);
                    }
                });

                //监听跳转到优惠券界面
                rl_coupon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), CouponActivity.class);
                        intent.putExtra("coupon", p.getUSERCOUPONURL());
                        startActivity(intent);
                    }
                });

                //监听跳转的收藏页面
                rl_collect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), CollectionActivity.class);
                        intent.putExtra("collect", p.getCOLLECTIONURL());
                        startActivity(intent);
                    }
                });

                //跳转账户管理页面的监听
                iv_person_head.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences share = getActivity().getSharedPreferences("logIn", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = share.edit();
                        editor.putString("account", p.getACCMANAGEMENT());
                        editor.apply();
                        Intent intent = new Intent(getActivity(), AccountActivity.class);
                        startActivity(intent);

                    }
                });

                //监听跳转到邀请绑定人界面
                iv_inviter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), InviterActivity.class);
                        intent.putExtra("invite", p.getINVITEURL());
                        startActivity(intent);
                    }
                });


                //监听跳转到地址管理页面
                rl_address.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences share2 = getActivity().getSharedPreferences("address_order", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor2 = share2.edit();
                        editor2.putString("address_order", p.getADRESSMANAGEURL());
                        editor2.apply();
                        SharedPreferences share = getActivity().getSharedPreferences("AddressBack", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = share.edit();
                        editor.putString("AddressBack", "2");
                        editor.apply();
                        Intent intent = new Intent(getActivity(), AddressActivity.class);
                        intent.putExtra("address", p.getADRESSMANAGEURL());
                        startActivity(intent);
                    }
                });

                tv_coupon.setText(p.getCOUNUM() + "");
                tv_score.setText(p.getPOINT() + "");

                //跳转到我的积分界面
                rl_score.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), MyPointActivity.class);
                        intent.putExtra("score", p.getPOINT() + "");
                        startActivity(intent);
                    }
                });

                tv_price.setText(p.getCONUM() + "");

                //跳转到我的关注界面
                rl_concern.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(getActivity(), "敬请期待", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getActivity(), ConcernActivity.class);
//                        intent.putExtra("concern", p.getMINECONCEMURL());
//                        startActivity(intent);
                    }
                });

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

    //监听跳转到订单管理页面
    @Event(R.id.rl_person_order)
    private void rlClick(View view) {
        Intent intent = new Intent(getActivity(), OrderAllActivity.class);
        startActivity(intent);

    }

    //跳转待付款
    @Event(R.id.rl_waitPay)
    private void waitPayClick(View view) {
        Intent intent = new Intent(getActivity(), OrderAllActivity.class);
        intent.putExtra("order", 1);
        startActivity(intent);
    }

    //跳转待发货
    @Event(R.id.rl_waitSend)
    private void waitSendClick(View view) {
        Intent intent = new Intent(getActivity(), OrderAllActivity.class);
        intent.putExtra("order", 2);
        startActivity(intent);
    }

    //跳转待收货
    @Event(R.id.rl_waitCollect)
    private void waitReceiveClick(View view) {
        Intent intent = new Intent(getActivity(), OrderAllActivity.class);
        intent.putExtra("order", 3);
        startActivity(intent);
    }

    //跳转待评价
    @Event(R.id.rl_waitEvaluate)
    private void waitEvaluateClick(View view) {
        Intent intent = new Intent(getActivity(), OrderAllActivity.class);
        intent.putExtra("order", 4);
        startActivity(intent);
    }


//    //跳转积分
//    @Event(R.id.person_score_rel)
//    private void pointClick(View view) {
//
//    }

    //跳转到我的丸子说
    @Event(R.id.my_meatBall_re)
    private void meatBallClick(View view) {
        //Toast.makeText(getActivity(), "敬请期待", Toast.LENGTH_SHORT).show();
//        SharedPreferences share_meatball = getActivity().getSharedPreferences("meatballstory", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor_meatball = share_meatball.edit();
//        editor_meatball.putString("meatballstory", p.getMINEBLOYSTORY());
//        editor_meatball.apply();
//
        Toast.makeText(getActivity(), "敬请期待", Toast.LENGTH_SHORT).show();
//        SharedPreferences share = getActivity().getSharedPreferences("meatballstory", Context.MODE_PRIVATE);
//        meatballstory = share.getString("meatballstory", "");
//
//        Intent intent = new Intent(getContext(), MyMeatBallActivity.class);
//        intent.putExtra("meatballstory", meatballstory);
//        startActivity(intent);


    }


//    @Event(R.id.iv_my_meatBall)


    //跳转到邀请好友界面
    @Event(R.id.rl_person_friend)
    private void FriendClick(View view) {
//        Intent intent = new Intent(getActivity(), InviteFriendActivity.class);
//        startActivity(intent);
        showShare();
    }


//    //跳转到我的关注界面
//    @Event(R.id.person_price_rel)
//    private void concernClick(View view) {
//
//    }

    //联系客服
    @Event(R.id.rl_mine_call)
    private void callClick(View view) {
//        Intent intent = new Intent(getActivity(), QuestionActivity.class);
//        startActivity(intent);
        show(inflate);
    }

    private void showShare() {
        ShareSDK.initSDK(getActivity());
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("品直邮，把“丸”美品质带回家");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://app.qq.com/#id=detail&appid=1105721948");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("口袋丸子致力于为消费者提供愉悦的在线购物体验，采取各种提供具有丰富品类及卓越品质的商品和服务，以快速可靠的方式送达给消费者");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://app.qq.com/#id=detail&appid=1105721948");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://app.qq.com/#id=detail&appid=1105721948");

        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            @Override
            public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
                if ("QZone".equals(platform.getName())) {
                    paramsToShare.setTitle(null);
                    paramsToShare.setTitleUrl(null);
                }
                if ("SinaWeibo".equals(platform.getName())) {
                    paramsToShare.setUrl(null);
                    paramsToShare.setText("口袋丸子 http://app.qq.com/#id=detail&appid=1105721948");
                }
                if ("Wechat".equals(platform.getName())) {
                    Bitmap imageData = BitmapFactory.decodeResource(getResources(), R.drawable.ssdk_logo);
                    paramsToShare.setImageData(imageData);
                }
                if ("WechatMoments".equals(platform.getName())) {
                    Bitmap imageData = BitmapFactory.decodeResource(getResources(), R.drawable.ssdk_logo);
                    paramsToShare.setImageData(imageData);
                }

            }
        });


        // 启动分享GUI
        oks.show(getActivity());
    }

    public void show(View view) {


        dialog2 = new Dialog(getActivity(), R.style.ActionSheetDialogStyle);
        inflate = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_layout, null);
//        Button choosePhoto = (Button) inflate.findViewById(R.id.choosePhoto);
//        Button  takePhoto = (Button) inflate.findViewById(R.id.takePhoto);
//        Button  cancel = (Button) inflate.findViewById(R.id.btn_cancel);
        TextView tv = (TextView) inflate.findViewById(R.id.tv_dialog_q);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuestionActivity.class);
                startActivity(intent);
            }
        });
        dialog2.setContentView(inflate);
        Window dialogWindow = dialog2.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 100;
        dialogWindow.setAttributes(lp);
        dialog2.show();
    }

}
