package com.example.administrator.koudaiwanzi.person.set;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.R;

import java.util.Calendar;

public class DateActivity extends Activity {

    private DatePicker mDatePicker;
    private Button btn_date;
    private TextView mEditText;

    // 定义5个记录当前时间的变量
    private int year;
    private int month;
    private int day;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        initView();

    }

    private void initView() {
        mDatePicker = (DatePicker) findViewById(R.id.dpPicker);
        mEditText = (TextView) findViewById(R.id.tv_date);
        btn_date = (Button) findViewById(R.id.btn_date);
        // 获取当前的年、月、日、小时、分钟


        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                int month1 = month + 1;
                day = calendar.get(Calendar.DAY_OF_MONTH);
                Log.d("wangjidashabi", year + "/" + mDatePicker.getYear());
                if (year < mDatePicker.getYear()) {

                    Toast.makeText(DateActivity.this, "日期选择不正确！请选择" + year + "年" + month1 + "月" + day + "日之前的日期", Toast.LENGTH_SHORT).show();
                } else {
                    if (month1 < mDatePicker.getMonth()) {
                        Toast.makeText(DateActivity.this, "日期选择不正确！请选择" + year + "年" + month1 + "月" + day + "日之前的日期", Toast.LENGTH_SHORT).show();
                    } else {
                        if (day < mDatePicker.getDayOfMonth()) {
                            Toast.makeText(DateActivity.this, "日期选择不正确！请选择" + year + "年" + month1 + "月" + day + "日之前的日期", Toast.LENGTH_SHORT).show();
                        } else {
                            int a = mDatePicker.getMonth();
                            int b = a + 1;
                            mEditText.setText("选择的时间为" + mDatePicker.getYear() + "-" + b + "-" + mDatePicker.getDayOfMonth());
                            String s = mDatePicker.getYear() + "-" + b + "-" + mDatePicker.getDayOfMonth();
                            Intent intent = new Intent(DateActivity.this, AccountActivity.class);
                            intent.putExtra("date", s);
                            startActivity(intent);
                            finish();
                        }
                    }

                }

            }
        });


    }


}