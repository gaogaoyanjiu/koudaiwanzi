package com.example.administrator.koudaiwanzi.meatball;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
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
import android.widget.VideoView;

import com.example.administrator.koudaiwanzi.MainActivity;
import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.classification.type.TypeBean;
import com.example.administrator.koudaiwanzi.details.DetailsActivity;
import com.example.administrator.koudaiwanzi.dialog.CustomProgressDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/7/11.
 */
@ContentView(R.layout.fragment_ball_details_two)
public class DetailsVideoActivity extends AppCompatActivity implements View.OnClickListener{
    private String url1 = MyUrl.url;
    private View view;
    private Handler handler;

    @ViewInject(R.id.playVideo_meat)
    private ImageView playVideo;
    @ViewInject(R.id.playVideoCenter_meat)
    private ImageView playVideoCenter;
    @ViewInject(R.id.playVideo1_meat)
    private ImageView playVideo1;
    @ViewInject(R.id.videoView_meat)
    private VideoView videoView;
    @ViewInject(R.id.playVideoCenter_meat)
    private ImageView iv_preview;
    @ViewInject(R.id.ball_details_attention)
    private TextView iv_attention;
    @ViewInject(R.id.ball_details_notAttention)
    private TextView iv_notAttention;
    @ViewInject(R.id.details_two_name)
    private TextView tv_name;
    @ViewInject(R.id.details_two_note)
    private TextView tv_note;
    @ViewInject(R.id.mycollection_item_iv1)
    private ImageView iv_p1;
    @ViewInject(R.id.mycollection_item_iv2)
    private ImageView iv_p2;
    @ViewInject(R.id.mycollection_item_iv3)
    private ImageView iv_p3;
    @ViewInject(R.id.mycollection_item_tv1)
    private TextView tv_name1;
    @ViewInject(R.id.mycollection_item_tv2)
    private TextView tv_name2;
    @ViewInject(R.id.mycollection_item_tv3)
    private TextView tv_name3;
    @ViewInject(R.id.zan_count)
    private TextView tv_like;
    @ViewInject(R.id.ll_ball_comment)
    private LinearLayout ll_comment;
    @ViewInject(R.id.details_two_head)
    private ImageView iv_head;
//    @ViewInject(R.id.ll_head_v)
//    private LinearLayout ll_p1;
    @ViewInject(R.id.tv_ball_comment)
    private TextView tv_comment;
    @ViewInject(R.id.ball_details_et)
    private EditText et_comment;
    private String strGBK;
    private String VideoUrl;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        final CustomProgressDialog dialog = new CustomProgressDialog(this, R.style.CommProgressDialog, "加载中...", R.anim.frame2);
        dialog.show();
        handler = new Handler() {
            public void handleMessage(Message msg) {
                dialog.cancel();
            }
        };

        playVideo1.setOnClickListener(this);
        playVideo.setOnClickListener(this);
        playVideoCenter.setOnClickListener(this);

        Intent intent = getIntent();
        final String url2 = intent.getStringExtra("videoBall");
        String url = url1 + url2;
        Log.d("kanyikan", url);
        final RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String o) {

                Gson gson = new Gson();
                final DetailVideoBean bean = gson.fromJson(o, DetailVideoBean.class);

                x.image().bind(iv_preview, url1+bean.getPREVIEW());

                //videoview的视频的播放
                VideoUrl = url1 + bean.getVIDEOURL();
                Log.e("tybnk", VideoUrl);
                videoView.setVideoURI(Uri.parse(VideoUrl));
                videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        playVideoCenter.setVisibility(View.VISIBLE);
                        playVideo1.setVisibility(View.VISIBLE);
                    }
                });


                x.image().bind(iv_p1, url1 + bean.getITEMS().get(0).getICOURL());
                x.image().bind(iv_p2, url1 + bean.getITEMS().get(1).getICOURL());
                x.image().bind(iv_p3, url1 + bean.getITEMS().get(2).getICOURL());
                tv_name1.setText(bean.getITEMS().get(0).getTRADENAME());
                tv_name2.setText(bean.getITEMS().get(1).getTRADENAME());
                tv_name3.setText(bean.getITEMS().get(2).getTRADENAME());

                tv_name.setText(bean.getUSERNAME());
                tv_note.setText(bean.getNOTES() + "");
                tv_like.setText(bean.getLIKES() + "");
                int a = bean.getCONCEMSTATE();

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
                                    iv_attention.setVisibility(View.INVISIBLE);
                                    iv_notAttention.setVisibility(View.VISIBLE);
                                } else {


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

                                    iv_attention.setVisibility(View.VISIBLE);
                                    iv_notAttention.setVisibility(View.INVISIBLE);
                                } else {


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


                Picasso.with(DetailsVideoActivity.this).load(url1 + bean.getHEADURL()).into(iv_head);

                int size = bean.getCOMMENT().size();
                for (int i = 0; i < size; i++) {
                    view = LayoutInflater.from(DetailsVideoActivity.this).inflate(R.layout.ball_details_listview_item, null);
                    TextView tv1 = (TextView) view.findViewById(R.id.author);
                    tv1.setText(bean.getCOMMENT().get(i).getNICKNAME());
                    TextView tv2 = (TextView) view.findViewById(R.id.commentary);
                    tv2.setText(bean.getCOMMENT().get(i).getCOMMENTARY());
                    TextView tv3 = (TextView) view.findViewById(R.id.time);
                    ImageView iv = (ImageView) view.findViewById(R.id.details_listview_item_head);
                    String result = formatData("yyyy-MM-dd", bean.getCOMMENT().get(i).getCREATETIME());
                    tv3.setText(result + "");
                    Picasso.with(DetailsVideoActivity.this).load(url1 + bean.getCOMMENT().get(i).getHEADURL()).into(iv);

                    //监听item跳转
                    ll_comment.addView(view);
            }

                tv_comment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String comment = et_comment.getText().toString();
                        if (comment == "") {
                            Toast.makeText(DetailsVideoActivity.this, "输入的评论不能为空", Toast.LENGTH_SHORT).show();
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
                                        Toast.makeText(DetailsVideoActivity.this, "提交评论成功", Toast.LENGTH_SHORT).show();
                                        Intent intent1 = new Intent(DetailsVideoActivity.this, DetailsVideoActivity.class);
                                        intent1.putExtra("videoBall", url2);
                                        startActivity(intent1);
                                        DetailsVideoActivity.this.overridePendingTransition(0, 0);
                                        finish();

                                    } else {
                                        Toast.makeText(DetailsVideoActivity.this, "提交评论失败", Toast.LENGTH_SHORT).show();

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
//                    int size2 = bean.getHEADS().size();
//                    for (int i = 0; i < size2; i++) {
//                        view = LayoutInflater.from(DetailsVideoActivity.this).inflate(R.layout.item_all_p2, null);
//                        ImageView iv2 = (ImageView) view.findViewById(R.id.details_item_head);
//                        Picasso.with(DetailsVideoActivity.this).load(url1+bean.getHEADS().get(i).getHEADURL()).into(iv2);
//
//                        //监听item跳转
//                        ll_p1.addView(view);
//                    }
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                Toast.makeText(DetailsVideoActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
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

    public static String formatData(String dataFormat, long timeStamp) {
        if (timeStamp == 0) {
            return "";
        }
        timeStamp = timeStamp * 1000;
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        result = format.format(new Date(timeStamp));
        return result;
    }


    //监听手机自带的返回键
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }


    //控制视频播放与否的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playVideo_meat:
                videoView.pause();
                playVideo1.setVisibility(View.VISIBLE);

                break;
            case R.id.playVideo1_meat:
                videoView.start();
                playVideo1.setVisibility(View.INVISIBLE);
                playVideoCenter.setVisibility(View.GONE);
                break;
        }


    }

}
