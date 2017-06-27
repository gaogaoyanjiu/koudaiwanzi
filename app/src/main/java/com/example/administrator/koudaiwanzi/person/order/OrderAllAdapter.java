package com.example.administrator.koudaiwanzi.person.order;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.PayActivity;
import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.ems.EMSActivity;
import com.example.administrator.koudaiwanzi.shopcar.OrderActivity;
import com.example.administrator.koudaiwanzi.wxapi.WXEntryActivity;


import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2016/6/7.
 */
public class OrderAllAdapter extends BaseAdapter {
    private List<OrderBean> data;
    private Context context;
    private LinearLayout ll_one, ll_two, ll_three, ll_four, ll_five;
    String url = MyUrl.url;
    private Handler handler;

    final int TYPE_1 = 0;
    final int TYPE_2 = 1;
    final int TYPE_3 = 2;
    final int TYPE_4 = 3;
    final int TYPE_5 = 4;

    public OrderAllAdapter(Context context) {
        this.context = context;
//        init();
    }

    public OrderAllAdapter(Context context,Handler handler){
        this.context = context;
        this.handler = handler;
    }

    public void addData(List<OrderBean> data) {
        this.data = data;
        notifyDataSetChanged();

    }

//    private void init() {
//        data = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            data.add("");
//        }
//    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).getSTATUSTYPE() == 0) {
            //待付款
            return TYPE_1;
        } else if (data.get(position).getSTATUSTYPE() == 1) {
            //待发货
            return TYPE_2;
        } else if (data.get(position).getSTATUSTYPE() == 2) {
            //待收货
            return TYPE_3;
        } else if (data.get(position).getSTATUSTYPE() == 3) {
            //待评价
            return TYPE_4;
        } else {
            return TYPE_5;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 5;
    }

    @Override
    public int getCount() {
        return data.size() > 0 && data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return data.size() != 0 && data != null ? data.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = null;
        MyViewHolder2 myViewHolder2 = null;
        MyViewHolder3 myViewHolder3 = null;
        MyViewHolder4 myViewHolder4 = null;
        MyViewHolder5 myViewHolder5 = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case TYPE_1:
                    myViewHolder = new MyViewHolder();
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_two, null);
                    ll_one = (LinearLayout) convertView.findViewById(R.id.ll_order_item_one);
                    myViewHolder.tv_boNum = (TextView) convertView.findViewById(R.id.tv_myOrder_item2_number);
                    myViewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_myOrder_item2_price);
                    myViewHolder.tv_post = (TextView) convertView.findViewById(R.id.tv_myOrder_item2_post);
                    myViewHolder.tv_quantity = (TextView) convertView.findViewById(R.id.tv_myOrder_item2_quantity);
                    myViewHolder.rl_pay = (RelativeLayout) convertView.findViewById(R.id.rl_myOrder_item2_pay);
                    myViewHolder.rl_delete = (RelativeLayout) convertView.findViewById(R.id.rl_delete_order);
                    myViewHolder.rl_call = (RelativeLayout) convertView.findViewById(R.id.rl_delete_call);
                    // 设置加载图片的参数
                    final ImageOptions options = new ImageOptions.Builder()
                            //图片大小
                            .setSize(DensityUtil.dip2px(150), DensityUtil.dip2px(100))
                            // 是否忽略GIF格式的图片
                            .setIgnoreGif(false)
                            // 图片缩放模式
                            .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                            // 得到ImageOptions对象
                            .build();
                    for (int i = 0; i < data.get(position).getORDERITEMS().size(); i++) {
                        View view = LayoutInflater.from(context).inflate(R.layout.item_one, null);
                        //跳转到订单详情界面
                        RelativeLayout rl_ems = (RelativeLayout) view.findViewById(R.id.rl_ems_detail);
                        rl_ems.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(context, OrderDetailActivity.class);
                                intent.putExtra("orderDetail", data.get(position).getORDERDETAILURL()+data.get(position).getNID());
                                context.startActivity(intent);
                            }
                        });


                        TextView tv_price = (TextView) view.findViewById(R.id.tv_myOrder_item1_price);
                        TextView tv_Oldprice = (TextView) view.findViewById(R.id.tv_myOrder_item1_Oldprice);
                        ImageView image = (ImageView) view.findViewById(R.id.iv_myOrder_item1);
                        TextView tv_name = (TextView) view.findViewById(R.id.tv_myOrder_item1_name);
                        TextView tv_standard = (TextView) view.findViewById(R.id.tv_myOrder_item1_type);
                        TextView tv_quantity = (TextView) view.findViewById(R.id.tv_myOrder_item1_quantity);

                        //给textView中间加横线
                        tv_Oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

                        if (data.get(position).getORDERITEMS().get(i).getDISCOUNT() == 0) {
                            tv_price.setText(data.get(position).getORDERITEMS().get(i).getPRICE() + "");
                            tv_Oldprice.setVisibility(View.GONE);
                        } else {
                            tv_price.setText(data.get(position).getORDERITEMS().get(i).getBARGAIN() + "");
                            tv_Oldprice.setText(data.get(position).getORDERITEMS().get(i).getPRICE() + "");
                        }


                        x.image().bind(image, url + data.get(position).getORDERITEMS().get(i).getICOURL(), options);
                        tv_name.setText(data.get(position).getORDERITEMS().get(i).getTRADENAME());
                        tv_standard.setText(data.get(position).getORDERITEMS().get(i).getSTANDARD());
                        tv_quantity.setText("X" + data.get(position).getORDERITEMS().get(i).getQUANTITY());
                        ll_one.addView(view);
                    }
                    convertView.setTag(myViewHolder);
                    break;
                case TYPE_2:
                    myViewHolder2 = new MyViewHolder2();
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_three, null);
                    //商品数量
                    myViewHolder2.tv_quantity = (TextView) convertView.findViewById(R.id.tv_myOrder_item3_quantity);
                    //总价
                    myViewHolder2.tv_price = (TextView) convertView.findViewById(R.id.tv_myOrder_item3_price);
                    //运费
                    myViewHolder2.tv_post = (TextView) convertView.findViewById(R.id.tv_myOrder_item3_post);
                    //运单号
                    myViewHolder2.tv_boNum = (TextView) convertView.findViewById(R.id.tv_myOrder_item3_number);
                    //提醒发货
                    myViewHolder2.tv_remind = (RelativeLayout)convertView.findViewById(R.id.tv_myOrder_item3_pay);

                    ll_two = (LinearLayout) convertView.findViewById(R.id.ll_order_item_two);
                    for (int i = 0; i < data.get(position).getORDERITEMS().size(); i++) {
                        View view = LayoutInflater.from(context).inflate(R.layout.item_one, null);

                        //跳转到订单详情界面
                        RelativeLayout rl_ems = (RelativeLayout) view.findViewById(R.id.rl_ems_detail);
                        rl_ems.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(context, OrderDetailActivity.class);
                                intent.putExtra("orderDetail", data.get(position).getORDERDETAILURL()+data.get(position).getNID());
                                context.startActivity(intent);
                            }
                        });

                        TextView tv_price = (TextView) view.findViewById(R.id.tv_myOrder_item1_price);
                        ImageView image = (ImageView) view.findViewById(R.id.iv_myOrder_item1);
                        TextView tv_name = (TextView) view.findViewById(R.id.tv_myOrder_item1_name);
                        TextView tv_standard = (TextView) view.findViewById(R.id.tv_myOrder_item1_type);
                        TextView tv_quantity = (TextView) view.findViewById(R.id.tv_myOrder_item1_quantity);
                        TextView tv_price_old = (TextView) view.findViewById(R.id.tv_myOrder_item1_Oldprice);
                        //给textView中间加横线
                        tv_price_old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

                        if (data.get(position).getORDERITEMS().get(i).getDISCOUNT() == 0) {
                            tv_price.setText(data.get(position).getORDERITEMS().get(i).getPRICE() + "");
                            tv_price_old.setVisibility(View.GONE);
                        } else {
                            tv_price.setText(data.get(position).getORDERITEMS().get(i).getBARGAIN() + "");
                            tv_price_old.setText(data.get(position).getORDERITEMS().get(i).getPRICE() + "");
                        }

                        x.image().bind(image, url + data.get(position).getORDERITEMS().get(i).getICOURL());
                        tv_name.setText(data.get(position).getORDERITEMS().get(i).getTRADENAME());
                        tv_standard.setText(data.get(position).getORDERITEMS().get(i).getSTANDARD());
                        tv_quantity.setText("X" + data.get(position).getORDERITEMS().get(i).getQUANTITY());
                        ll_two.addView(view);
                    }
                    convertView.setTag(myViewHolder2);
                    break;


                case TYPE_3:
                    myViewHolder3 = new MyViewHolder3();
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_four, null);
                    //商品数量
                    myViewHolder3.tv_quantity = (TextView) convertView.findViewById(R.id.tv_myOrder_item4_quantity);
                    //总价
                    myViewHolder3.tv_price = (TextView) convertView.findViewById(R.id.tv_myOrder_item4_price);
                    //运费
                    myViewHolder3.tv_post = (TextView) convertView.findViewById(R.id.tv_myOrder_item4_post);
                    //运单号
                    myViewHolder3.tv_boNum = (TextView) convertView.findViewById(R.id.tv_myOrder_item4_number);
                    myViewHolder3.rl_wuliu = (RelativeLayout) convertView.findViewById(R.id.tv_myOrder_item4_check);
                    myViewHolder3.rl_queren = (RelativeLayout) convertView.findViewById(R.id.tv_myOrder_item4_pay);

                    ll_three = (LinearLayout) convertView.findViewById(R.id.ll_order_item_three);

                    for (int i = 0; i < data.get(position).getORDERITEMS().size(); i++) {
                        View view = LayoutInflater.from(context).inflate(R.layout.item_one, null);

                        //跳转到订单详情界面
                        RelativeLayout rl_ems = (RelativeLayout) view.findViewById(R.id.rl_ems_detail);
                        rl_ems.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(context, OrderDetailActivity.class);
                                intent.putExtra("orderDetail", data.get(position).getORDERDETAILURL()+data.get(position).getNID());
                                context.startActivity(intent);
                            }
                        });


                        ImageView image = (ImageView) view.findViewById(R.id.iv_myOrder_item1);
                        TextView tv_name = (TextView) view.findViewById(R.id.tv_myOrder_item1_name);
                        TextView tv_standard = (TextView) view.findViewById(R.id.tv_myOrder_item1_type);
                        TextView tv_quantity = (TextView) view.findViewById(R.id.tv_myOrder_item1_quantity);
                        TextView tv_price = (TextView) view.findViewById(R.id.tv_myOrder_item1_price);
                        TextView tv_price_old3 = (TextView) view.findViewById(R.id.tv_myOrder_item1_Oldprice);
                        //给textView中间加横线
                        tv_price_old3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                        if (data.get(position).getORDERITEMS().get(i).getDISCOUNT() == 0) {
                            tv_price.setText(data.get(position).getORDERITEMS().get(i).getPRICE() + "");
                            tv_price_old3.setVisibility(View.GONE);
                        } else {
                            tv_price.setText(data.get(position).getORDERITEMS().get(i).getBARGAIN() + "");
                            tv_price_old3.setText(data.get(position).getORDERITEMS().get(i).getPRICE() + "");
                        }
                        x.image().bind(image, url + data.get(position).getORDERITEMS().get(i).getICOURL());
                        tv_name.setText(data.get(position).getORDERITEMS().get(i).getTRADENAME());
                        tv_standard.setText(data.get(position).getORDERITEMS().get(i).getSTANDARD());
                        tv_quantity.setText("X" + data.get(position).getORDERITEMS().get(i).getQUANTITY());
                        ll_three.addView(view);
                    }
                    convertView.setTag(myViewHolder3);
                    break;


                case TYPE_4:
                    myViewHolder4 = new MyViewHolder4();
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_five, null);
                    //商品数量
                    myViewHolder4.tv_quantity = (TextView) convertView.findViewById(R.id.tv_myOrder_item5_quantity);
                    //总价
                    myViewHolder4.tv_price = (TextView) convertView.findViewById(R.id.tv_myOrder_item5_price);
                    //运费
                    myViewHolder4.tv_post = (TextView) convertView.findViewById(R.id.tv_myOrder_item5_post);
                    //运单号
                    myViewHolder4.tv_boNum = (TextView) convertView.findViewById(R.id.tv_myOrder_item5_number);
                    //查看物流
                    myViewHolder4.rl_wu = (RelativeLayout) convertView.findViewById(R.id.rl_item_five_wu);
                    //评价
                    myViewHolder4.rl_ping = (RelativeLayout) convertView.findViewById(R.id.tv_myOrder_item2_pay);
                    myViewHolder4.rl_ping.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, EvaluateActivity.class);
                            intent.putExtra("pingjia", data.get(position).getCOMMENTURL());
                            context.startActivity(intent);
                        }
                    });

                    ll_four = (LinearLayout) convertView.findViewById(R.id.ll_order_item_five);

                    for (int i = 0; i < data.get(position).getORDERITEMS().size(); i++) {
                        View view = LayoutInflater.from(context).inflate(R.layout.item_one, null);

                        //跳转到订单详情界面
                        RelativeLayout rl_ems = (RelativeLayout) view.findViewById(R.id.rl_ems_detail);
                        rl_ems.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(context, OrderDetailActivity.class);
                                intent.putExtra("orderDetail", data.get(position).getORDERDETAILURL()+data.get(position).getNID());
                                context.startActivity(intent);
                            }
                        });

                        ImageView image = (ImageView) view.findViewById(R.id.iv_myOrder_item1);
                        TextView tv_name = (TextView) view.findViewById(R.id.tv_myOrder_item1_name);
                        TextView tv_standard = (TextView) view.findViewById(R.id.tv_myOrder_item1_type);
                        TextView tv_quantity = (TextView) view.findViewById(R.id.tv_myOrder_item1_quantity);
                        TextView tv_price = (TextView) view.findViewById(R.id.tv_myOrder_item1_price);
                        TextView tv_price_old4 = (TextView) view.findViewById(R.id.tv_myOrder_item1_Oldprice);
                        //给textView中间加横线
                        tv_price_old4.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                        if (data.get(position).getORDERITEMS().get(i).getDISCOUNT() == 0) {
                            tv_price.setText(data.get(position).getORDERITEMS().get(i).getPRICE() + "");
                            tv_price_old4.setVisibility(View.GONE);
                        } else {
                            tv_price.setText(data.get(position).getORDERITEMS().get(i).getBARGAIN() + "");
                            tv_price_old4.setText(data.get(position).getORDERITEMS().get(i).getPRICE() + "");
                        }
                        x.image().bind(image, url + data.get(position).getORDERITEMS().get(i).getICOURL());
                        tv_name.setText(data.get(position).getORDERITEMS().get(i).getTRADENAME());
                        tv_standard.setText(data.get(position).getORDERITEMS().get(i).getSTANDARD());
                        tv_quantity.setText("X" + data.get(position).getORDERITEMS().get(i).getQUANTITY());
                        ll_four.addView(view);
                    }
                    convertView.setTag(myViewHolder4);
                    break;


                case TYPE_5:
                    myViewHolder5 = new MyViewHolder5();
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_six, null);
                    ll_five = (LinearLayout) convertView.findViewById(R.id.ll_order_item_five);

                    for (int i = 0; i < data.get(position).getORDERITEMS().size(); i++) {
                        View view = LayoutInflater.from(context).inflate(R.layout.item_one, null);

                        //跳转到订单详情界面
                        RelativeLayout rl_ems = (RelativeLayout) view.findViewById(R.id.rl_ems_detail);
                        rl_ems.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(context, OrderDetailActivity.class);

                                intent.putExtra("orderDetail", data.get(position).getORDERDETAILURL()+data.get(position).getNID());
                                context.startActivity(intent);
                            }
                        });

                        ImageView image = (ImageView) view.findViewById(R.id.iv_myOrder_item1);
                        TextView tv_name = (TextView) view.findViewById(R.id.tv_myOrder_item1_name);
                        TextView tv_standard = (TextView) view.findViewById(R.id.tv_myOrder_item1_type);
                        TextView tv_quantity = (TextView) view.findViewById(R.id.tv_myOrder_item1_quantity);
                        TextView tv_price_old5 = (TextView) view.findViewById(R.id.tv_myOrder_item1_Oldprice);
                        TextView tv_price = (TextView) view.findViewById(R.id.tv_myOrder_item1_price);
                        //给textView中间加横线
                        tv_price_old5.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                        if (data.get(position).getORDERITEMS().get(i).getDISCOUNT() == 0) {
                            tv_price.setText(data.get(position).getORDERITEMS().get(i).getPRICE() + "");
                            tv_price_old5.setVisibility(View.GONE);
                        } else {
                            tv_price.setText(data.get(position).getORDERITEMS().get(i).getBARGAIN() + "");
                            tv_price_old5.setText(data.get(position).getORDERITEMS().get(i).getPRICE() + "");
                        }


                        x.image().bind(image, url + data.get(position).getORDERITEMS().get(i).getICOURL());
                        tv_name.setText(data.get(position).getORDERITEMS().get(i).getTRADENAME());

                        tv_standard.setText(data.get(position).getORDERITEMS().get(i).getSTANDARD());
                        tv_quantity.setText("X" + data.get(position).getORDERITEMS().get(i).getQUANTITY());
                        ll_five.addView(view);
                    }
                    convertView.setTag(myViewHolder5);
                    break;

            }

        } else {
            switch (type) {
                case TYPE_1:
                    myViewHolder = (MyViewHolder) convertView.getTag();
                    break;
                case TYPE_2:
                    myViewHolder2 = (MyViewHolder2) convertView.getTag();
                    break;
                case TYPE_3:
                    myViewHolder3 = (MyViewHolder3) convertView.getTag();
                    break;
                case TYPE_4:
                    myViewHolder4 = (MyViewHolder4) convertView.getTag();
                    break;
                case TYPE_5:
                    myViewHolder5 = (MyViewHolder5) convertView.getTag();
                    break;

            }
        }


        //加入资源
        switch (type) {
            case TYPE_1:
                myViewHolder.tv_boNum.setText(data.get(position).getBONUM() + "");
                myViewHolder.tv_post.setText("(含运费¥" + data.get(position).getPOSTAGE() + ")");

                myViewHolder.tv_price.setText("¥" + data.get(position).getPRICE());

                myViewHolder.tv_quantity.setText("共" + data.get(position).getQUANTITY() + "件商品");

                myViewHolder.rl_pay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        String a = data.get(position).getPAYORDER();
//                        Intent intent = new Intent(context, WXEntryActivity.class);
//                        intent.putExtra("payOrder", a);
                        Intent intent = new Intent(context, WXEntryActivity.class);
                        intent.putExtra("detailUrl", data.get(position).getPAYORDER() + "");
                        intent.putExtra("price", data.get(position).getPRICE()+"");
                        context.startActivity(intent);
                    }
                });

                myViewHolder.rl_call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("确认要拨打客服电话吗?");
                        builder.setTitle("提示");
                        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent1 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:10086"));
                                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    // TODO: Consider calling
                                    //    ActivityCompat#requestPermissions
                                    // here to request the missing permissions, and then overriding
                                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                    //                                          int[] grantResults)
                                    // to handle the case where the user grants the permission. See the documentation
                                    // for ActivityCompat#requestPermissions for more details.
                                    return;
                                }
                                context.startActivity(intent1);

                            }

                        });

                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override

                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();

                            }

                        });

                        builder.create().show();

                    }
                });

                myViewHolder.rl_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("确认取消订单吗?");
                        builder.setTitle("提示");
                        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                RequestParams params = new RequestParams(url + data.get(position).getDELEORDER());
                                x.http().get(params, new Callback.CommonCallback<String>() {
                                    @Override
                                    public void onSuccess(String s) {
                                        handler.sendMessage(handler.obtainMessage(0));
//                                        Intent intent = new Intent(context, OrderAllActivity.class);
//                                        context.startActivity(intent);
//                                        ((OrderAllActivity) context).finish();
//                                        ((OrderAllActivity) context).overridePendingTransition(0, 0);
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

                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override

                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();

                            }

                        });

                        builder.create().show();
                    }
                });
                break;

            case TYPE_2:
                myViewHolder2.tv_boNum.setText(data.get(position).getBONUM() + "");
                myViewHolder2.tv_post.setText("(含运费¥" + data.get(position).getPOSTAGE() + ")");

                myViewHolder2.tv_price.setText("¥" + data.get(position).getPRICE());

                myViewHolder2.tv_quantity.setText("共" + data.get(position).getQUANTITY() + "件商品");

                myViewHolder2.tv_remind.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url1 = url+"Users.svc/reminderdelivery/"+data.get(position).getNID();
                        RequestParams params = new RequestParams(url1);
                        x.http().get(params, new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String s) {

                                Toast.makeText(context, "已提醒卖家发货", Toast.LENGTH_SHORT).show();

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

                break;
            case TYPE_3:
                myViewHolder3.tv_boNum.setText(data.get(position).getBONUM() + "");
                myViewHolder3.tv_post.setText("(含运费¥" + data.get(position).getPOSTAGE() + ")");
                myViewHolder3.tv_price.setText("¥" + data.get(position).getPRICE());
                myViewHolder3.tv_quantity.setText("共" + data.get(position).getQUANTITY() + "件商品");
               //跳转到物流页面

                myViewHolder3.rl_wuliu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, EMSActivity.class);
                        intent.putExtra("ems", data.get(position).getINFORMATION());
                        context.startActivity(intent);

                    }
                });
                myViewHolder3.rl_queren.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RequestParams params = new RequestParams(url +  data.get(position).getRECIPIENTSUCCESSURL());
                        x.http().get(params, new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String s) {

                                Log.d("wobuxiangtanlianai", s);

                                handler.sendEmptyMessage(10);
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


                        Log.d("dsadsdawxcz", String.valueOf(data.get(position)));
                    }
                });


                break;
            case TYPE_4:
                myViewHolder4.tv_boNum.setText(data.get(position).getBONUM() + "");
                myViewHolder4.tv_post.setText("(含运费¥" + data.get(position).getPOSTAGE() + ")");
                myViewHolder4.tv_price.setText("¥" + data.get(position).getPRICE());
                myViewHolder4.tv_quantity.setText("共" + data.get(position).getQUANTITY() + "件商品");

                myViewHolder4.rl_wu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, EMSActivity.class);

                        context.startActivity(intent);
                    }
                });

                break;
            case TYPE_5:

                break;
        }

        return convertView;
    }

    class MyViewHolder {
        private TextView tv_boNum, tv_price, tv_post, tv_quantity;
        private RelativeLayout rl_pay, rl_delete, rl_call;

    }

    class MyViewHolder2 {
        private TextView tv_boNum, tv_price, tv_post, tv_quantity;
        private RelativeLayout tv_remind;
    }

    class MyViewHolder3 {
        private TextView tv_boNum, tv_price, tv_post, tv_quantity;
        private RelativeLayout rl_queren, rl_wuliu;
    }

    class MyViewHolder4 {
        private TextView tv_boNum, tv_price, tv_post, tv_quantity;
        private RelativeLayout rl_wu, rl_ping;
    }

    class MyViewHolder5 {
        private TextView tv_boNum, tv_price, tv_post, tv_quantity;

    }

}
