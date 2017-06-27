package com.example.administrator.koudaiwanzi.person.point;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.example.administrator.koudaiwanzi.R;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

/**
 * Created by Administrator on 2016/6/30.
 */

@ContentView(R.layout.fragment_point_rule)
public class PointRuleActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    //返回键监听
    @Event(R.id.iv_back_rule)
    private void imgClick(View view) {
        finish();
    }

}
