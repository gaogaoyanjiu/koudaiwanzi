package com.example.administrator.koudaiwanzi.classification.type;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.classification.type.screen.ClassActivity;

import org.xutils.x;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 自定义Adapter
 *
 * @author zihao
 */
public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private List<TypeBean> datas;
	private List<List<String>>childs;

    LayoutInflater mInflater;
    private Context context;

    public ExpandableListViewAdapter(Context context) {
        // TODO Auto-generated constructor stub
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }
    public void addData(List<TypeBean> datas, List<List<String>>childs){
		this.datas = datas;
		this.childs = childs;
		notifyDataSetChanged();
	}

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childs != null && childs.size() > 0 ? childs.get(groupPosition).get(childPosition) : null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            mViewChild = new ViewChild();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.type_expandablelistview_item, null);
            mViewChild.gridView = (GridView) convertView
                    .findViewById(R.id.channel_item_child_gridView);
            convertView.setTag(mViewChild);
        } else {
            mViewChild = (ViewChild) convertView.getTag();
        }
        SimpleAdapter mSimpleAdapter = new SimpleAdapter(context,
                setGridViewData(childs.get(groupPosition)),
                R.layout.type_gridview_item,
                new String[]{"channel_gridview_item"},
                new int[]{R.id.channel_gridview_item});
        mViewChild.gridView.setAdapter(mSimpleAdapter);
//        setGridViewListener(mViewChild.gridView);
        mViewChild.gridView.setSelector(new ColorDrawable(Color.WHITE));

        mViewChild.gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // TODO Auto-generated method stub
                    if (view instanceof TextView) {
                        // 如果想要获取到哪一行，则自定义gridview的adapter，item设置2个textview一个隐藏设置id，显示哪一行
                        TextView tv = (TextView) view;
                        String item = datas.get(groupPosition).getSECLEVEL().get(position).getDetailUrl();
//                        SharedPreferences share = context.getSharedPreferences("Class", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = share.edit();
//                        editor.putString("class", item);
//                        editor.apply();
                        Log.d("kankancuo", item);
                        Intent intent = new Intent(context, ClassActivity.class);
                        intent.putExtra("classKey", item);
                        context.startActivity(intent);

                    }
                }
            });

        return convertView;
    }

    private ArrayList<HashMap<String, Object>> setGridViewData(List<String> childs) {
        ArrayList<HashMap<String, Object>> gridItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < childs.size(); i++) {
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("channel_gridview_item", childs.get(i));
            gridItem.add(hashMap);
        }
        return gridItem;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // TODO Auto-generated method stub
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        // TODO Auto-generated method stub
        return datas.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        return datas.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            mViewChild = new ViewChild();
            convertView = mInflater.inflate(R.layout.type_expandablelistview, null);
            mViewChild.imageView = (ImageView) convertView.findViewById(R.id.channel_imageview_orientation);
            convertView.setTag(mViewChild);
        } else {
            mViewChild = (ViewChild) convertView.getTag();
        }
        String a = MyUrl.url;
        String b = datas.get(groupPosition).getICOURL();
        String c = a+b;
        x.image().bind(mViewChild.imageView, c);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return true;
    }

    ViewChild mViewChild;

    class ViewChild {
        ImageView imageView;
        GridView gridView;

    }
}