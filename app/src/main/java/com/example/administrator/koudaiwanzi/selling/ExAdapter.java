package com.example.administrator.koudaiwanzi.selling;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.CommonAdapter;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.base.ViewHolder;
import com.example.administrator.koudaiwanzi.details.DetailsActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/8/29.
 */
public class ExAdapter extends BaseExpandableListAdapter {
    private List<SellBean.NEWSALEITEMSBean> datas;
    private List<List<NEWITEMSBean>> childs;
    private Context context;
    private Handler handler;

    public ExAdapter(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    public void addData(List<SellBean.NEWSALEITEMSBean> datas, List<List<NEWITEMSBean>> childs) {
        this.datas = datas;
        this.childs = childs;
        notifyDataSetChanged();
    }


    @Override
    public int getGroupCount() {
        Log.e("poinbv", datas.size() + "");
        return datas.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return datas.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childs != null && childs.size() > 0 ? childs.get(groupPosition).get(childPosition) : null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder = null;
        if (convertView == null) {
            groupViewHolder = new GroupViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.group_item, null);
            groupViewHolder.imageView = (SimpleDraweeView) convertView.findViewById(R.id.iv_group);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }


        //FRESCO缓存图片
        Uri imageUri = Uri.parse(MyUrl.url + datas.get(groupPosition).getICOURL());
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(imageUri)
                .setResizeOptions(new ResizeOptions(200, 200))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(groupViewHolder.imageView.getController())
                .setImageRequest(request)
                .build();
        groupViewHolder.imageView.setController(controller);


        Log.e("sdfzv", MyUrl.url + datas.get(groupPosition).getICOURL());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (convertView == null) {
            childViewHolder = new ChildViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.child_item, null);
            childViewHolder.gridView = (GridView) convertView.findViewById(R.id.gv_sell);
            convertView.setTag(childViewHolder);

        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }


        CommonAdapter<NEWITEMSBean> mAdapter;
        childViewHolder.gridView.setAdapter(mAdapter = new CommonAdapter<NEWITEMSBean>(context, childs.get(groupPosition), R.layout.add_view) {
            @Override
            public void convert(ViewHolder holder, final NEWITEMSBean newitemsBean) {
                holder.setText(R.id.tv_name_two, newitemsBean.getTRADENAME());

                SimpleDraweeView imageView = holder.getView(R.id.iv_group_addView);
                TextView tv = holder.getView(R.id.tv_add_view);

                if (newitemsBean.getISOUTSTOCK() == 0){
                    tv.setVisibility(View.GONE);
                }else
                {
                    tv.setVisibility(View.VISIBLE);
                }

                if (newitemsBean.getSTANDARD() != null){
                    holder.setText(R.id.tv_size_add, newitemsBean.getSTANDARD());
                }else {

                }

                Log.e("sdfzv", MyUrl.url + newitemsBean.getICOURL());

                //FRESCO缓存图片
                Uri imageUri = Uri.parse(MyUrl.url + newitemsBean.getICOURL());
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(imageUri)
                        .setResizeOptions(new ResizeOptions(200, 200))
                        .build();
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setOldController(imageView.getController())
                        .setImageRequest(request)
                        .build();
                imageView.setController(controller);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, DetailsActivity.class);
                        intent.putExtra("key", newitemsBean.getDetailUrl());
                        context.startActivity(intent);
                    }
                });



                holder.setOnClickListener(R.id.iv_shopCar_sell, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (newitemsBean.getISOUTSTOCK() == 0){
                            handler.sendMessage(handler.obtainMessage(30, newitemsBean.getDetailUrl()));
                        }else
                        {
                            Toast.makeText(mContext, "商品缺货", Toast.LENGTH_SHORT).show();
                        }


                    }
                });


                if (newitemsBean.getDISCOUNT() == 0){
                    holder.setText(R.id.tv_price_new_group, "¥" + newitemsBean.getPRICE());
                }else {
                    holder.setText(R.id.tv_price_new_group, "¥" + newitemsBean.getBARGAIN());
                    holder.setText(R.id.tv_old_price_group, "¥" + newitemsBean.getPRICE());
                    TextView textView = holder.getView(R.id.tv_old_price_group);
                    textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                }

            }
        });

        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupViewHolder {
        private SimpleDraweeView imageView;
    }

    class ChildViewHolder {
        private TextView tv_name;
        private LinearLayout linearLayout;
        private GridView gridView;
    }
}
