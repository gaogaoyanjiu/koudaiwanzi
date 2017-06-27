package com.example.administrator.koudaiwanzi.classification.brand;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.CommonAdapter;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.base.ViewHolder;
import com.example.administrator.koudaiwanzi.details.DetailsActivity;
import com.example.administrator.koudaiwanzi.newproduct.NEWITEMBean;
import com.example.administrator.koudaiwanzi.newproduct.NewProductBean;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/8/31.
 */
public class BrandAdapter extends BaseExpandableListAdapter{

    private List<BrandBean> datas;
    private List<List<SECLEVELBean>> childs;
    private Context context;


    public BrandAdapter(Context context) {
        this.context = context;
    }

    public void addData(List<BrandBean> datas, List<List<SECLEVELBean>> childs) {
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
            groupViewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_group);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        x.image().bind(groupViewHolder.imageView, MyUrl.url + datas.get(groupPosition).getICOURL());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (convertView == null) {
            childViewHolder = new ChildViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.child_item_brand, null);
            childViewHolder.gridView = (GridView) convertView.findViewById(R.id.gv_sell_brand);
            convertView.setTag(childViewHolder);

        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }


        CommonAdapter<SECLEVELBean> mAdapter;
        childViewHolder.gridView.setAdapter(mAdapter = new CommonAdapter<SECLEVELBean>(context, childs.get(groupPosition), R.layout.add_view2) {
            @Override
            public void convert(ViewHolder holder, final SECLEVELBean seclevelBean) {

                ImageView imageView = holder.getView(R.id.iv_brand_add);

                // 设置加载图片的参数
//                ImageOptions options = new ImageOptions.Builder()
//                        //图片大小
//                        .setSize(DensityUtil.dip2px(200), DensityUtil.dip2px(150))
//                        // 是否忽略GIF格式的图片
//                        .setIgnoreGif(false)
//                        // 图片缩放模式
////                        .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
//                        // 得到ImageOptions对象
//                        .build();

                x.image().bind(imageView, MyUrl.url + seclevelBean.getCODENAME());
                Log.e("zxccvz", MyUrl.url + seclevelBean.getCODENAME());
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, BrandActivity.class);
                        intent.putExtra("brandDetail", seclevelBean.getDetailUrl());
                        context.startActivity(intent);
                    }
                });
            }
        });

        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupViewHolder {
        private ImageView imageView;
    }

    class ChildViewHolder {
        private TextView tv_name;
        private LinearLayout linearLayout;
        private GridView gridView;
    }


}
