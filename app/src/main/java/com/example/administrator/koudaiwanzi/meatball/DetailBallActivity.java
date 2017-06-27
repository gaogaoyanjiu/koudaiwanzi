package com.example.administrator.koudaiwanzi.meatball;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.dialog.CustomProgressDialog;
import com.example.administrator.koudaiwanzi.person.mymeatball.MyMeatBallActivity;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Administrator on 2016/7/8.
 * 丸子说图片详情界面
 */
@ContentView(R.layout.fragment_ball_details_one)
public class DetailBallActivity extends AppCompatActivity {
    private String url1 = MyUrl.url;
    private View view;
    private Handler handler;
    @ViewInject(R.id.ll_detail_p1)
    private LinearLayout ll_p;
    @ViewInject(R.id.ball_details_iv)
    private ImageView iv_ball;
    @ViewInject(R.id.ball_details_notes)
    private TextView tv_notes;
    @ViewInject(R.id.ball_details_title)
    private TextView tv_title;
    @ViewInject(R.id.ball_details_left_iv)
    private CircleImageView iv_head;
    @ViewInject(R.id.zan_count11)
    private TextView tv_zan;
    @ViewInject(R.id.ball_details_commentary)
    private TextView tv_commentary;
    @ViewInject(R.id.iv_detail_like)
    private ImageView iv_like;
    @ViewInject(R.id.iv_detail_notLike)
    private ImageView iv_notLike;
    //    @ViewInject(R.id.ll_head)
//    private LinearLayout ll_p1;
    @ViewInject(R.id.ll_ball_comment)
    private LinearLayout ll_comment;
    //绑定提交评论的editText
//    @ViewInject(R.id.rl_detail_like)
//    private RelativeLayout rl_like;
    @ViewInject(R.id.tv_ball_comment)
    private TextView tv_comment;
    @ViewInject(R.id.ball_details_et)
    private EditText et_comment;
    @ViewInject(R.id.ball_details_attention)
    private TextView iv_attention;
    @ViewInject(R.id.ball_details_notAttention)
    private TextView iv_notAttention;
    private String strGBK;

    @ViewInject(R.id.detail_one_text)
    private TextView detail_one_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        Intent intent = getIntent();
        final String url2 = intent.getStringExtra("pictureBall");
        String like = intent.getStringExtra("pictureBallLike");
        String url = url1 + url2;
        final CustomProgressDialog dialog = new CustomProgressDialog(this, R.style.CommProgressDialog, "加载中...", R.anim.frame2);
        dialog.show();
        handler = new Handler() {
            public void handleMessage(Message msg) {
                dialog.cancel();
            }
        };

        final RequestParams params = new RequestParams(url);
        Log.d("dsadsdads", url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                final DetailPictureBean bean = gson.fromJson(s, DetailPictureBean.class);
                int a = bean.getCONCEMSTATE();
                if (bean.getMsg() == 0) {
                    iv_like.setVisibility(View.VISIBLE);
                    iv_notLike.setVisibility(View.INVISIBLE);
                    iv_like.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RequestParams params1 = new RequestParams(url1 + bean.getLIKESURL());
                            x.http().get(params1, new CommonCallback<String>() {
                                @Override
                                public void onSuccess(String s) {
                                    iv_like.setVisibility(View.INVISIBLE);
                                    iv_notLike.setVisibility(View.VISIBLE);
                                    Log.d("dsadsdsads", String.valueOf(bean.getLIKES()));
                                    Log.d("dsadsdsads1", String.valueOf(bean.getLIKES() + 1+""));
                                    tv_zan.setText(bean.getLIKES() + 1+"");
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
                    iv_notLike.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RequestParams params1 = new RequestParams(url1 + bean.getLIKESURL());
                            x.http().get(params1, new CommonCallback<String>() {
                                @Override
                                public void onSuccess(String s) {
                                    iv_like.setVisibility(View.VISIBLE);
                                    iv_notLike.setVisibility(View.INVISIBLE);
                                    tv_zan.setText(bean.getLIKES() + "");
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


                } else {
                    iv_like.setVisibility(View.INVISIBLE);
                    iv_notLike.setVisibility(View.VISIBLE);


                    iv_like.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RequestParams params1 = new RequestParams(url1 + bean.getLIKESURL());
                            x.http().get(params1, new CommonCallback<String>() {
                                @Override
                                public void onSuccess(String s) {
                                    iv_like.setVisibility(View.INVISIBLE);
                                    iv_notLike.setVisibility(View.VISIBLE);
                                    Log.d("dsadsdsads", String.valueOf(bean.getLIKES()));
                                    Log.d("dsadsdsads1", String.valueOf(bean.getLIKES() + 1+""));
                                    tv_zan.setText(bean.getLIKES()+"");
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
                    iv_notLike.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RequestParams params1 = new RequestParams(url1 + bean.getLIKESURL());
                            x.http().get(params1, new CommonCallback<String>() {
                                @Override
                                public void onSuccess(String s) {
                                    iv_like.setVisibility(View.VISIBLE);
                                    iv_notLike.setVisibility(View.INVISIBLE);
                                    tv_zan.setText(bean.getLIKES() - 1 + "");
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



                if (a == 0) {
                    iv_attention.setVisibility(View.VISIBLE);
                    iv_notAttention.setVisibility(View.INVISIBLE);
                } else {
                    iv_attention.setVisibility(View.INVISIBLE);
                    iv_notAttention.setVisibility(View.VISIBLE);
                }
                iv_attention.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String urlADD = url1 + bean.getADDCONCEMURL();
                        RequestParams params = new RequestParams(urlADD);
                        x.http().get(params, new CommonCallback<String>() {
                            @Override
                            public void onSuccess(String s) {
                                Gson gson = new Gson();
                                BackBean bean = gson.fromJson(s, BackBean.class);
                                if (bean.getMsg() == 1) {
                                    Toast.makeText(DetailBallActivity.this, "关注成功", Toast.LENGTH_SHORT).show();
                                    iv_attention.setVisibility(View.INVISIBLE);
                                    iv_notAttention.setVisibility(View.VISIBLE);
                                } else {
                                    Toast.makeText(DetailBallActivity.this, "关注失败", Toast.LENGTH_SHORT).show();

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
                    }
                });
                iv_notAttention.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String urlADD = url1 + bean.getDELCONCEMURL();
                        RequestParams params = new RequestParams(urlADD);
                        x.http().get(params, new CommonCallback<String>() {
                            @Override
                            public void onSuccess(String s) {
                                Gson gson = new Gson();
                                BackBean bean = gson.fromJson(s, BackBean.class);
                                if (bean.getMsg() == 1) {
                                    Toast.makeText(DetailBallActivity.this, "取消关注成功", Toast.LENGTH_SHORT).show();
                                    iv_attention.setVisibility(View.VISIBLE);
                                    iv_notAttention.setVisibility(View.INVISIBLE);
                                } else {
                                    Toast.makeText(DetailBallActivity.this, "取消关注失败", Toast.LENGTH_SHORT).show();

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
                    }
                });

                tv_comment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String comment = et_comment.getText().toString();
                        if (comment == "") {
                            Toast.makeText(DetailBallActivity.this, "输入的评论不能为空", Toast.LENGTH_SHORT).show();
                        } else {

                            try {
                                strGBK = URLEncoder.encode(comment, "GBK");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }

                            String urlComment = url1 + bean.getCOMMENTURL() + strGBK;
                            Log.e("popiu", strGBK);

                            RequestParams params = new RequestParams(urlComment);
                            x.http().get(params, new CommonCallback<String>() {
                                @Override
                                public void onSuccess(String s) {
                                    Gson gson = new Gson();
                                    BackBean bean = gson.fromJson(s, BackBean.class);
                                    if (bean.getMsg() == 1) {
                                        Toast.makeText(DetailBallActivity.this, "提交评论成功", Toast.LENGTH_SHORT).show();
                                        Intent intent1 = new Intent(DetailBallActivity.this, DetailBallActivity.class);
                                        intent1.putExtra("pictureBall", url2);
                                        startActivity(intent1);
                                        DetailBallActivity.this.overridePendingTransition(0, 0);
                                        finish();

                                    } else {
                                        Toast.makeText(DetailBallActivity.this, "提交评论失败", Toast.LENGTH_SHORT).show();

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

                        }
                    }
                });


                x.image().bind(iv_ball, url1 + bean.getPREVIEW());

                tv_notes.setText(bean.getNOTES());
                tv_title.setText(getIntent().getStringExtra("name"));
                tv_zan.setText(bean.getLIKES() + "");

                Log.d("urlidid",url1 + bean.getUSERHEAD());
                Picasso.with(DetailBallActivity.this).load(url1 + bean.getUSERHEAD()).into(iv_head);





                String urllls = MyMeatBallActivity.getStringTime(getIntent().getStringExtra("time") + "");

                detail_one_text.setText(MyMeatBallActivity.getStringTime(getIntent().getStringExtra("time")));

                int size4 = bean.getBLOGIMG().size();
                for (int i = 0; i < size4; i++) {
                    view = LayoutInflater.from(DetailBallActivity.this).inflate(R.layout.item_all_p1, null);
                    ImageView iv = (ImageView) view.findViewById(R.id.iv_ball_p1);
                    x.image().bind(iv, url1 + bean.getBLOGIMG().get(i).getIMGURL());
                    //监听item跳转
                    ll_p.addView(view);
                }

                int size = bean.getCOMMENT().size();
                for (int i = 0; i < size; i++) {
                    view = LayoutInflater.from(DetailBallActivity.this).inflate(R.layout.ball_details_listview_item, null);
                    TextView tv1 = (TextView) view.findViewById(R.id.author);
                    tv1.setText(bean.getCOMMENT().get(i).getNICKNAME());
                    TextView tv2 = (TextView) view.findViewById(R.id.commentary);
                    tv2.setText(bean.getCOMMENT().get(i).getCOMMENTARY());
                    TextView tv3 = (TextView) view.findViewById(R.id.time);
                    ImageView iv = (ImageView) view.findViewById(R.id.details_listview_item_head);
                    String result = formatData("yyyy-MM-dd", bean.getCOMMENT().get(i).getCREATETIME());
                    tv3.setText(result + "");
                    Picasso.with(DetailBallActivity.this).load(MyUrl.url + bean.getCOMMENT().get(i).getHEADURL()).into(iv);
                    //监听item跳转
                    ll_comment.addView(view);
                }

//                int size2 = bean.getHEADS().size();
//                for (int i = 0; i < size2; i++) {
//                    view = LayoutInflater.from(DetailBallActivity.this).inflate(R.layout.item_all_p2, null);
//                    ImageView iv2 = (ImageView) view.findViewById(R.id.details_item_head);
//                    Picasso.with(DetailBallActivity.this).load(url1+bean.getHEADS().get(i).getHEADURL()).into(iv2);
//                    //监听item跳转
//                    ll_p1.addView(view);
//                }
                handler.sendEmptyMessage(0);

            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });


    }


    //返回按键
    @Event(R.id.iv_ball_details)
    private void ivclick(View view) {
        finish();
    }

    public String formatData(String dataFormat, long timeStamp) {
        if (timeStamp == 0) {
            return "";
        }
        timeStamp = timeStamp * 1000;
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        result = format.format(new Date(timeStamp));
        return result;
    }
}
