package com.example.administrator.koudaiwanzi.newproduct;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Handler;
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
import com.example.administrator.koudaiwanzi.classification.type.CustomGridView;
import com.example.administrator.koudaiwanzi.classification.type.ExpandableListViewAdapter;
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

import java.util.List;

/**
 * Created by Administrator on 2016/8/29.
 */
public class NewProductAdapter extends BaseExpandableListAdapter {
    private Handler handler;
    private List<NewProductBean.CATEGORYBean> datas;
    private List<List<NEWITEMBean>> childs;
    private Context context;
    private NewGridViewAdapter newAdapter;

    public NewProductAdapter(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    public void addData(List<NewProductBean.CATEGORYBean> datas, List<List<NEWITEMBean>> childs) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.group_item_new, null);
            groupViewHolder.imageView = (SimpleDraweeView) convertView.findViewById(R.id.iv_group_new);
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

        Log.e("xbnmdf", MyUrl.url + datas.get(groupPosition).getICOURL());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (convertView == null) {
            childViewHolder = new ChildViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.child_item_new_two, null);
            childViewHolder.gridView = (GridView) convertView.findViewById(R.id.gv_sell_new);
            convertView.setTag(childViewHolder);

        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        newAdapter = new NewGridViewAdapter(context, childs.get(groupPosition), R.layout.add_view_three, handler);
        childViewHolder.gridView.setAdapter(newAdapter);

        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
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
