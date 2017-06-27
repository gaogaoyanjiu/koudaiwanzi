package com.example.administrator.koudaiwanzi.meatball;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.meatball.texttureview.TextureVideoView;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/8/16.
 */
public class MeatBallAdapterTwo extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //广播在这里好用吗
    private LocalBroadcastManager broadcastManager;
    private IntentFilter intentFilter;
    private BroadcastReceiver mReceiver;
    private Context context;
    private List<VideoBean> data;
    private MyViewHolder lastPlayVideo = null;
    private String url = MyUrl.url;


    public static enum ITEM_TYPE {
        ITEM_TYPE_IMAGE, ITEM_TYPE_TEXT
    }


    public MeatBallAdapterTwo(Context context) {
        this.context = context;

    }

    public void addData(List<VideoBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        int a = data.get(position).getTHEMETYPE();
        if (a != 0) {
            return ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal();
        } else {
            return ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal()){
            View view = LayoutInflater.from(context).inflate(R.layout.item_japan_video, null);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;

        }else {
            View view2 = LayoutInflater.from(context).inflate(R.layout.item_japan_picture,null);
            MyViewHolder2 myViewHolder2 = new MyViewHolder2(view2);
            return myViewHolder2;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder){
            ((MyViewHolder) holder).tv_name.setText(data.get(position).getTHEMENAME());
            ((MyViewHolder) holder).rl_all_video_meatBall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailsVideoActivity.class);
                    intent.putExtra("videoBall", data.get(position).getDetailUrl());
                    context.startActivity(intent);
                }
            });

            x.image().bind(((MyViewHolder) holder).insideVideoView, url + data.get(position).getPREVIEW());
            x.image().bind(((MyViewHolder) holder).iv_p1, url + data.get(position).getITEMS().get(0).getICOURL());
            ((MyViewHolder) holder).tv_name1.setText(data.get(position).getITEMS().get(0).getTRADENAME());
            if (data.get(position).getITEMS().get(0).getDISCOUNT() == 0){
                ((MyViewHolder) holder).tv_one1.setText(data.get(position).getITEMS().get(0).getPRICE() + "");
                ((MyViewHolder) holder).tv_one.setVisibility(View.GONE);
            }else{
                ((MyViewHolder) holder).tv_one.setText(data.get(position).getITEMS().get(0).getPRICE() + "");
                ((MyViewHolder) holder).tv_one1.setText(data.get(position).getITEMS().get(0).getBARGAIN() + "");
            }
            x.image().bind(((MyViewHolder) holder).iv_p2, url + data.get(position).getITEMS().get(1).getICOURL());
            ((MyViewHolder) holder).tv_name2.setText(data.get(position).getITEMS().get(1).getTRADENAME());
            if (data.get(position).getITEMS().get(1).getDISCOUNT() == 0){
                ((MyViewHolder) holder).tv_two1.setText(data.get(position).getITEMS().get(1).getPRICE() + "");
                ((MyViewHolder) holder).tv_two.setVisibility(View.GONE);
            }else{
                ((MyViewHolder) holder).tv_two.setText(data.get(position).getITEMS().get(1).getPRICE() + "");
                ((MyViewHolder) holder).tv_two1.setText(data.get(position).getITEMS().get(1).getBARGAIN() + "");
            }
            x.image().bind(((MyViewHolder) holder).iv_p3, url + data.get(position).getITEMS().get(2).getICOURL());
            ((MyViewHolder) holder).tv_name3.setText(data.get(position).getITEMS().get(2).getTRADENAME());
            if (data.get(position).getITEMS().get(2).getDISCOUNT() == 0){
                ((MyViewHolder) holder).tv_three1.setText(data.get(position).getITEMS().get(2).getPRICE() + "");
                ((MyViewHolder) holder).tv_three.setVisibility(View.GONE);

            }else{
                ((MyViewHolder) holder).tv_three.setText(data.get(position).getITEMS().get(2).getPRICE() + "");
                ((MyViewHolder) holder).tv_three1.setText(data.get(position).getITEMS().get(2).getBARGAIN() + "");
            }



            ((MyViewHolder) holder).tv_count.setText(data.get(position).getLIKES() + "");
            //处理点赞的逻辑
            ((MyViewHolder) holder).thumbs_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //处理点赞按钮点击之后的逻辑
                    ((MyViewHolder) holder).tv_count.setText(data.get(position).getLIKES() + 1 + "");
                    ((MyViewHolder) holder).thumbs_up_selected.setVisibility(View.VISIBLE);
                    ((MyViewHolder) holder).thumbs_up.setVisibility(View.INVISIBLE);
                }
            });


            ((MyViewHolder) holder).thumbs_up_selected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MyViewHolder) holder).tv_count.setText(data.get(position).getLIKES() + "");
                    ((MyViewHolder) holder).thumbs_up_selected.setVisibility(View.INVISIBLE);
                    ((MyViewHolder) holder).thumbs_up.setVisibility(View.VISIBLE);
                }
            });

//                x.image().bind(myViewHolder.iv_head, url + data.get(position).getHEADURL());
//                Picasso.with(context).load(url + data.get(position).getHEADURL()).into(myViewHolder.iv_head);

            ((MyViewHolder) holder).textureVideoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (data.get(position).getVIDEOURL() == "") {
                        Toast.makeText(context, "视频地址不能为空，请重新拉取网络视频地址哦", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (lastPlayVideo == null) {
                        lastPlayVideo = ((MyViewHolder) holder);
                    } else {
                        if (!((MyViewHolder) holder).equals(lastPlayVideo)) {
                            lastPlayVideo.textureVideoView.stop();
                            lastPlayVideo.pbWaiting.setVisibility(View.GONE);
                            lastPlayVideo.playVideo.setVisibility(View.VISIBLE);
                            lastPlayVideo = ((MyViewHolder) holder);
                        }
                    }
                    //textureVideoView的播放
                    TextureVideoView textureView = (TextureVideoView) v;
                    if (textureView.getState() == TextureVideoView.MediaState.INIT || textureView.getState() == TextureVideoView.MediaState.RELEASE) {

                        textureView.play(url + data.get(position).getVIDEOURL());

                        ((MyViewHolder) holder).insideVideoView.setVisibility(View.GONE);
                        ((MyViewHolder) holder).pbWaiting.setVisibility(View.VISIBLE);
                        ((MyViewHolder) holder).playVideo.setVisibility(View.GONE);

                    } else if (textureView.getState() == TextureVideoView.MediaState.PAUSE) {
                        textureView.start();
                        ((MyViewHolder) holder).pbWaiting.setVisibility(View.GONE);
                        ((MyViewHolder) holder).playVideo.setVisibility(View.GONE);
                    } else if (textureView.getState() == TextureVideoView.MediaState.PLAYING) {
                        textureView.pause();
                        ((MyViewHolder) holder).pbWaiting.setVisibility(View.GONE);
                        ((MyViewHolder) holder).playVideo.setVisibility(View.VISIBLE);
                    } else if (textureView.getState() == TextureVideoView.MediaState.PREPARING) {
                        textureView.stop();
                        ((MyViewHolder) holder).pbWaiting.setVisibility(View.GONE);
                        ((MyViewHolder) holder).playVideo.setVisibility(View.VISIBLE);
                    }

                }
            });


            ((MyViewHolder) holder).textureVideoView.setOnStateChangeListener(new TextureVideoView.OnStateChangeListener() {
                @Override
                public void onSurfaceTextureDestroyed(SurfaceTexture surface) {

                    ((MyViewHolder) holder).textureVideoView.stop();
                    ((MyViewHolder) holder).pbWaiting.setVisibility(View.GONE);
                    ((MyViewHolder) holder).playVideo.setVisibility(View.VISIBLE);
                    ((MyViewHolder) holder).pbProgressBar.setMax(1);
                    ((MyViewHolder) holder).pbProgressBar.setProgress(0);
                    ((MyViewHolder) holder).insideVideoView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onPlaying() {
                    ((MyViewHolder) holder).pbWaiting.setVisibility(View.GONE);
                    ((MyViewHolder) holder).playVideo.setVisibility(View.GONE);
                }

                @Override
                public void onBuffering() {
                    ((MyViewHolder) holder).pbWaiting.setVisibility(View.VISIBLE);
                    ((MyViewHolder) holder).playVideo.setVisibility(View.GONE);
                }

                @Override
                public void onSeek(int max, int progress) {
                    ((MyViewHolder) holder).insideVideoView.setVisibility(View.GONE);
                    ((MyViewHolder) holder).pbProgressBar.setMax(max);
                    ((MyViewHolder) holder).pbProgressBar.setProgress(progress);
                }

                @Override
                public void onStop() {
                    ((MyViewHolder) holder).pbProgressBar.setMax(1);
                    ((MyViewHolder) holder).pbProgressBar.setProgress(0);
                    ((MyViewHolder) holder).pbWaiting.setVisibility(View.GONE);
                    ((MyViewHolder) holder).playVideo.setVisibility(View.VISIBLE);
                    ((MyViewHolder) holder).insideVideoView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onPause() {
                    ((MyViewHolder) holder).pbWaiting.setVisibility(View.GONE);
                    ((MyViewHolder) holder).playVideo.setVisibility(View.VISIBLE);
                }

                @Override
                public void onTextureViewAvaliable() {

                }

                @Override
                public void playFinish() {
                    ((MyViewHolder) holder).pbProgressBar.setMax(1);
                    ((MyViewHolder) holder).pbProgressBar.setProgress(0);
                    ((MyViewHolder) holder).playVideo.setVisibility(View.GONE);
                    ((MyViewHolder) holder).insideVideoView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onPrepare() {
                }
            });

            //如此简单的接收广播停止播放
            broadcastManager = LocalBroadcastManager.getInstance(context);
            intentFilter = new IntentFilter();
            intentFilter.addAction("com.meatball.broadcast");
            mReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    ((MyViewHolder) holder).textureVideoView.pause();
                    ((MyViewHolder) holder).playVideo.setVisibility(View.VISIBLE);
                }
            };
            broadcastManager.registerReceiver(mReceiver, intentFilter);


        }else if (holder instanceof MyViewHolder2){

            x.image().bind(((MyViewHolder2) holder).imageView, url + data.get(position).getPREVIEW());
            ((MyViewHolder2) holder).tv_name_two.setText(data.get(position).getTHEMENAME());
            //内容   跳转到图片的详情界面
            ((MyViewHolder2) holder).tv_title_two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailBallActivity.class);
                    intent.putExtra("pictureBall", data.get(position).getDetailUrl());
                    context.startActivity(intent);
                }
            });

            //图片  跳转到详情界面
            ((MyViewHolder2) holder).iv_japan_picture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailBallActivity.class);
                    intent.putExtra("pictureBallLike", data.get(position).getLIKES() == 0);
                    intent.putExtra("pictureBall", data.get(position).getDetailUrl());
                    context.startActivity(intent);
                }
            });

            //标题  跳转到图片的详情界面
            ((MyViewHolder2) holder).tv_name_two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailBallActivity.class);
                    intent.putExtra("pictureBall", data.get(position).getDetailUrl());
                    context.startActivity(intent);
                }
            });

            //详细 跳转到详情界面
            ((MyViewHolder2) holder).tv_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailBallActivity.class);
                    intent.putExtra("pictureBall", data.get(position).getDetailUrl());
                    context.startActivity(intent);
                }
            });

            if (data.get(position).getLIKES() == 0) {
                ((MyViewHolder2) holder).japan_thumbs_up_selected.setVisibility(View.INVISIBLE);
                ((MyViewHolder2) holder).japan_thumbs_up.setVisibility(View.VISIBLE);
                ((MyViewHolder2) holder).japan_thumbs_up.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RequestParams params = new RequestParams(url + data.get(position).getLIKESURL());
                        x.http().get(params, new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String s) {
                                Gson gson = new Gson();
                                BackBean bean = gson.fromJson(s, BackBean.class);
                                if (bean.getMsg() == 1) {


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

                        //点赞时候的按钮的状态
                        ((MyViewHolder2) holder).japan_thumbs_up_selected.setVisibility(View.VISIBLE);
                        ((MyViewHolder2) holder).japan_thumbs_up.setVisibility(View.INVISIBLE);
                        ((MyViewHolder2) holder).tv_count.setText(data.get(position).getLIKES() + 1 + "");

                    }
                });


                ((MyViewHolder2) holder).japan_thumbs_up_selected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RequestParams params = new RequestParams(url + data.get(position).getLIKESURL());
                        x.http().get(params, new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String s) {
                                Gson gson = new Gson();
                                BackBean bean = gson.fromJson(s, BackBean.class);
                                if (bean.getMsg() == 1) {


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
                        //选中点赞按钮的状态
                        ((MyViewHolder2) holder).japan_thumbs_up_selected.setVisibility(View.INVISIBLE);
                        ((MyViewHolder2) holder).japan_thumbs_up.setVisibility(View.VISIBLE);
                        //判断如果关注数是否为0 不然点赞数量会出现负数
                        if (data.get(position).getLIKES() != 0) {
                            ((MyViewHolder2) holder).tv_count.setText(data.get(position).getLIKES() + "");
                        } else if (data.get(position).getLIKES() == 0) {
                            ((MyViewHolder2) holder).tv_count.setText(data.get(position).getLIKES() + "");
                        }
                    }
                });

            } else {
                ((MyViewHolder2) holder).japan_thumbs_up_selected.setVisibility(View.VISIBLE);
                ((MyViewHolder2) holder).japan_thumbs_up.setVisibility(View.INVISIBLE);
                ((MyViewHolder2) holder).japan_thumbs_up.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RequestParams params = new RequestParams(url + data.get(position).getLIKESURL());
                        x.http().get(params, new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String s) {
                                Gson gson = new Gson();
                                BackBean bean = gson.fromJson(s, BackBean.class);
                                if (bean.getMsg() == 1) {


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

                        //点赞时候的按钮的状态
                        ((MyViewHolder2) holder).japan_thumbs_up_selected.setVisibility(View.VISIBLE);
                        ((MyViewHolder2) holder).japan_thumbs_up.setVisibility(View.INVISIBLE);
                        ((MyViewHolder2) holder).tv_count.setText(data.get(position).getLIKES()+"");

                    }
                });


                ((MyViewHolder2) holder).japan_thumbs_up_selected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RequestParams params = new RequestParams(url + data.get(position).getLIKESURL());
                        x.http().get(params, new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String s) {
                                Gson gson = new Gson();
                                BackBean bean = gson.fromJson(s, BackBean.class);
                                if (bean.getMsg() == 1) {


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
                        //选中点赞按钮的状态
                        ((MyViewHolder2) holder).japan_thumbs_up_selected.setVisibility(View.INVISIBLE);
                        ((MyViewHolder2) holder).japan_thumbs_up.setVisibility(View.VISIBLE);
                        //判断如果关注数是否为0 不然点赞数量会出现负数
                        if (data.get(position).getLIKES() != 0) {
                            ((MyViewHolder2) holder).tv_count.setText(data.get(position).getLIKES()-1 + "");
                        } else if (data.get(position).getLIKES() == 0) {
                            ((MyViewHolder2) holder).tv_count.setText(data.get(position).getLIKES() + "");
                        }
                    }
                });

            }


            ((MyViewHolder2) holder).tv_count.setText(data.get(position).getLIKES() + "");
            ((MyViewHolder2) holder).tv_content.setText(data.get(position).getNOTES());



            ((MyViewHolder2) holder).japan_thumbs_up_selected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //选中点赞按钮的状态
                    ((MyViewHolder2) holder).japan_thumbs_up_selected.setVisibility(View.INVISIBLE);
                    ((MyViewHolder2) holder).japan_thumbs_up.setVisibility(View.VISIBLE);
                    //判断如果关注数是否为0 不然点赞数量会出现负数
                    if (data.get(position).getLIKES() != 0) {
                        ((MyViewHolder2) holder).tv_count.setText(data.get(position).getLIKES() + "");
                    } else if (data.get(position).getLIKES() == 0) {
                        ((MyViewHolder2) holder).tv_count.setText(data.get(position).getLIKES() + "");
                    }
                }
            });



        }
    }

    @Override
    public int getItemCount() {
        return data != null && data.size() > 0 ? data.size() : 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextureVideoView textureVideoView;
        ProgressBar pbWaiting;
        ProgressBar pbProgressBar;
        private ImageView insideVideoView;
        private ImageView playVideo;
        //点赞的图标
        private ImageView thumbs_up, thumbs_up_selected;
        private TextView tv_one, tv_two, tv_three, tv_name, tv_one1, tv_two1, tv_three1;
        private ImageView iv_p1, iv_p2, iv_p3, iv_head;
        private TextView tv_name1, tv_name2, tv_name3, tv_count;
        private RelativeLayout rl_all_video_meatBall;

        public MyViewHolder(View itemView) {
            super(itemView);
            textureVideoView = (TextureVideoView) itemView.findViewById(R.id.textureview);
            insideVideoView = (ImageView) itemView.findViewById(R.id.insideVideoView);
            pbWaiting = (ProgressBar) itemView.findViewById(R.id.pb_waiting);
            pbProgressBar = (ProgressBar) itemView.findViewById(R.id.progress_progressbar);
            playVideo = (ImageView) itemView.findViewById(R.id.playVideo);
            tv_one = (TextView) itemView.findViewById(R.id.tv_meatBall_price_old);
            tv_two = (TextView) itemView.findViewById(R.id.tv_meatBall_price_old_two);
            tv_three = (TextView) itemView.findViewById(R.id.tv_meatBall_price_old_three);
            tv_one.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            tv_two.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            tv_three.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

            rl_all_video_meatBall = (RelativeLayout) itemView.findViewById(R.id.rl_all_video_meatBall);
            iv_p1 = (ImageView) itemView.findViewById(R.id.mycollection_item_iv1);
            iv_p2 = (ImageView) itemView.findViewById(R.id.mycollection_item_iv2);
            iv_p3 = (ImageView) itemView.findViewById(R.id.mycollection_item_iv3);
            tv_name = (TextView) itemView.findViewById(R.id.tv_japan_name_video);
            tv_name1 = (TextView) itemView.findViewById(R.id.mycollection_item_tv1);
            tv_name2 = (TextView) itemView.findViewById(R.id.mycollection_item_tv2);
            tv_name3 = (TextView) itemView.findViewById(R.id.mycollection_item_tv3);
            tv_one1 = (TextView) itemView.findViewById(R.id.mycollection_item_newprice1);
            tv_two1 = (TextView) itemView.findViewById(R.id.mycollection_item_newprice2);
            tv_three1 = (TextView) itemView.findViewById(R.id.mycollection_item_newprice3);
            tv_count = (TextView) itemView.findViewById(R.id.count);
            iv_head = (ImageView) itemView.findViewById(R.id.iv_japan_head);
            //绑定点赞图标的id
            thumbs_up = (ImageView) itemView.findViewById(R.id.thumbs_up);
            thumbs_up_selected = (ImageView) itemView.findViewById(R.id.thumbs_up_selected);
        }
    }



    public class MyViewHolder2 extends RecyclerView.ViewHolder {

        private TextView tv_name_two, tv_title_two, tv_count, tv_content, tv_all;
        private ImageView imageView, japan_thumbs_up, japan_thumbs_up_selected, iv_japan_picture;

        public MyViewHolder2(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_japan_picture);
            tv_title_two = (TextView) itemView.findViewById(R.id.tv_japan_content);
            tv_name_two = (TextView) itemView.findViewById(R.id.tv_japan_name);
            tv_count = (TextView) itemView.findViewById(R.id.tv_japan_num);
            tv_content = (TextView) itemView.findViewById(R.id.tv_japan_content);
            japan_thumbs_up = (ImageView) itemView.findViewById(R.id.japan_thumbs_up);
            japan_thumbs_up_selected = (ImageView) itemView.findViewById(R.id.japan_thumbs_up_selected);
            iv_japan_picture = (ImageView) itemView.findViewById(R.id.iv_japan_picture);
            tv_all = (TextView) itemView.findViewById(R.id.tv_wuyue);
        }
    }


}
