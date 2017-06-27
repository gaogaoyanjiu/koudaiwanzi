package com.example.administrator.koudaiwanzi.person.address;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;

import java.io.IOException;
import java.util.List;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2016/6/3.
 */
public class AddressAdapter extends BaseAdapter{
    private Context context;
    private List<AddressBean> data;
    private Handler mHandler;
    private String url1 = MyUrl.url;
    public AddressAdapter(Context context, Handler mHandler) {
        this.context = context;
        this.mHandler = mHandler;
    }



    public void addData(List<AddressBean> data){
        this.data = data;
        notifyDataSetChanged();
    }



    @Override
    public int getCount() {
        return data.size() != 0 && data != null ? data.size() : 0;
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        MyViewHolder myViewHolder = null;
        if (convertView == null){
            myViewHolder = new MyViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_manage_address,null);
            myViewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_manage_name);
            myViewHolder.tv_number = (TextView) convertView.findViewById(R.id.tv_manage_phone);
            myViewHolder.tv_province = (TextView) convertView.findViewById(R.id.tv_manage_province);
            myViewHolder.tv_city = (TextView) convertView.findViewById(R.id.tv_manage_city);
            myViewHolder.tv_area = (TextView) convertView.findViewById(R.id.tv_manage_area);
            myViewHolder.tv_street = (TextView) convertView.findViewById(R.id.tv_manage_street);
            myViewHolder.ck = (CheckBox) convertView.findViewById(R.id.ck_manage);
            myViewHolder.iv_delete = (ImageView) convertView.findViewById(R.id.iv_manage_delete);
            myViewHolder.iv_change = (ImageView) convertView.findViewById(R.id.iv_manage_write);
            myViewHolder.tv_change = (TextView) convertView.findViewById(R.id.tv_manage_title2);
            myViewHolder.tv_delete = (TextView) convertView.findViewById(R.id.tv_manage_title1);
            convertView.setTag(myViewHolder);
        }else {


            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        myViewHolder.tv_name.setText(data.get(position).getCONSIGNEE());
        myViewHolder.tv_number.setText(data.get(position).getTEL());
        if (data.get(position).getPROVINCE() == null){
            myViewHolder.tv_province.setText("");
        }else {
            myViewHolder.tv_province.setText(data.get(position).getPROVINCE());
        }
        if (data.get(position).getCITY() == null){
            myViewHolder.tv_city.setText("");
        }else {
            myViewHolder.tv_city.setText(data.get(position).getCITY());
        }
       if (data.get(position).getAREA() == null){
           myViewHolder.tv_area.setText("");
       }else {
           myViewHolder.tv_area.setText(data.get(position).getAREA());
       }
        myViewHolder.tv_street.setText(data.get(position).getFULLADRESS());
        if(data.get(position).getISDEF()== 1){
             myViewHolder.ck.setChecked(true);
         }
        else {
             myViewHolder.ck.setChecked(false);
         }
        myViewHolder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setMessage("确认删除吗?");

                builder.setTitle("提示");

                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        OkHttpClient ok = new OkHttpClient();
                        String del = url1 + data.get(position).getDELETEADDRESS();
                        final Request request2 = new Request.Builder().url(del).build();
                        Call call = ok.newCall(request2);
                        call.enqueue(new okhttp3.Callback() {

                            public void onFailure(Call call, IOException e) {


                            }

                            public void onResponse(Call call, okhttp3.Response response) throws IOException {

                            }
                        });
                        data.remove(position);
                        notifyDataSetChanged();
                        dialog.dismiss();



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
       myViewHolder.tv_delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               AlertDialog.Builder builder = new AlertDialog.Builder(context);

               builder.setMessage("确认删除吗?");

               builder.setTitle("提示");

               builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       OkHttpClient ok = new OkHttpClient();
                       String del = url1 + data.get(position).getDELETEADDRESS();
                       final Request request2 = new Request.Builder().url(del).build();
                       Call call = ok.newCall(request2);
                       call.enqueue(new okhttp3.Callback() {

                           public void onFailure(Call call, IOException e) {


                           }

                           public void onResponse(Call call, okhttp3.Response response) throws IOException {

                           }
                       });
                       data.remove(position);
                       notifyDataSetChanged();
                       dialog.dismiss();



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
        myViewHolder.iv_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m = data.get(position).getMODIFYADDRESS();
                SharedPreferences share = context.getSharedPreferences("ChangeAddress", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = share.edit();
                editor.putString("changeAddress", m);
                editor.apply();
                Intent intent = new Intent(context,ChangeAddressActivity.class);
                context.startActivity(intent);

            }
        });
      myViewHolder.tv_change.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String m = data.get(position).getMODIFYADDRESS();
              SharedPreferences share = context.getSharedPreferences("ChangeAddress", Context.MODE_PRIVATE);
              SharedPreferences.Editor editor = share.edit();
              editor.putString("changeAddress", m);
              editor.apply();
              Intent intent = new Intent(context,ChangeAddressActivity.class);
              context.startActivity(intent);

          }
      });
        myViewHolder.ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String set = data.get(position).getSETADDRESS();
                Bundle bundle = new Bundle();
                bundle.putString("setUrl",set);
                mHandler.sendMessage(mHandler.obtainMessage(10, bundle));
            }
        });

        return convertView;
    }

    class MyViewHolder{
        private TextView tv_name;
        private TextView tv_number;
        private TextView tv_province;
        private TextView tv_city;
        private TextView tv_area;
        private TextView tv_street;
        private CheckBox ck;
        private ImageView iv_delete;
        private ImageView iv_change;
        private TextView tv_change,tv_delete;
    }


}

