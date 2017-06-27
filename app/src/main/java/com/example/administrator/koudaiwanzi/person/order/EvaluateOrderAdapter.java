package com.example.administrator.koudaiwanzi.person.order;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.CommonAdapter;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.base.ViewHolder;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.List;

/**
 * Created by Administrator on 2016/9/30.
 */
public class EvaluateOrderAdapter extends CommonAdapter<EvaluateOrderBean.ORDERITEMBean> {
    private Handler handler;
    private EditText editText;

    public EvaluateOrderAdapter(Context context, List<EvaluateOrderBean.ORDERITEMBean> data, int layoutId, Handler handler) {
        super(context, data, layoutId);
        this.handler = handler;
    }

    @Override
    public void convert(final ViewHolder holder, EvaluateOrderBean.ORDERITEMBean bean) {
        //加载图片
        SimpleDraweeView simpleDraweeView = holder.getView(R.id.iv);
        Uri imageUri = Uri.parse(MyUrl.url + bean.getICOURL());
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(imageUri)
                .setResizeOptions(new ResizeOptions(200, 200))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(simpleDraweeView.getController())
                .setImageRequest(request)
                .build();
        simpleDraweeView.setController(controller);
        //好评
        RadioButton button_one = holder.getView(R.id.rb_hao);
        //中评
        RadioButton button_two = holder.getView(R.id.rb_zhong);
        //差评
        RadioButton button_three = holder.getView(R.id.rb_cha);
        //选中文字换颜色
        if (button_one.isChecked()) {
            button_one.setTextColor(Color.parseColor("#ff0033"));
        } else {
            button_one.setTextColor(Color.parseColor("#BFBFBF"));
        }

        if (button_two.isChecked()) {
            button_two.setTextColor(Color.parseColor("#ff0033"));
        } else {
            button_two.setTextColor(Color.parseColor("#BFBFBF"));
        }

        if (button_three.isChecked()) {
            button_three.setTextColor(Color.parseColor("#ff0033"));
        } else {
            button_three.setTextColor(Color.parseColor("#BFBFBF"));
        }

        //button点击传值
        button_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendMessage(Message.obtain(handler, 1, 0));
            }
        });

        //button点击传值
        button_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendMessage(Message.obtain(handler, 2, 1));
            }
        });

        //button点击传值
        button_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendMessage(Message.obtain(handler, 3, 2));
            }
        });

        //输入文字
        editText = holder.getView(R.id.et_shangchuan);

      editText.addTextChangedListener(watcher);

    }


    public TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String a = editText.getText().toString();
            handler.sendMessage(Message.obtain(handler, 4, a));
        }
    };

}
