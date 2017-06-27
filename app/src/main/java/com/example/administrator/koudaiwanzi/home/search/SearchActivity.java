package com.example.administrator.koudaiwanzi.home.search;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.home.entity.greendao.DaoMaster;
import com.example.administrator.koudaiwanzi.home.entity.greendao.DaoSession;
import com.example.administrator.koudaiwanzi.home.entity.greendao.HistoryEntity;
import com.example.administrator.koudaiwanzi.home.entity.greendao.HistoryEntityDao;
import com.example.administrator.koudaiwanzi.product.ShowProductActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/5/30.
 * 搜索界面
 */

@ContentView(value = R.layout.activity_seach)
public class SearchActivity extends AppCompatActivity {
    private String myUrl = MyUrl.url;
    private List<Bean> dbData;
    private List<BeanTwo> datas;
    private List<HotSearchBean> data;
    //热门搜索
    @ViewInject(R.id.ll_class)
    private LinearLayout linearLayout;

    //历史搜索listView绑定ID
    @ViewInject(R.id.main_lv_search_results)
    private ListView historyListview;

    //搜索listView绑定ID
    @ViewInject(R.id.search_lv_tips)
    private ListView searchListview;

    //定义listView的适配器
    private ListViewAdapter adapter;

    //历史搜索绑定ID
    @ViewInject(R.id.tv_history)
    private TextView tv_history;


    //历史搜索绑定ID
    @ViewInject(R.id.search_iv_delete)
    private ImageView iv_delete;


    /**
     * 省略一些控件的声明
     */
    // 数据库
    private SQLiteDatabase db;
    // 管理者
    private DaoMaster mDaoMaster;
    // 会话
    private DaoSession mDaoSession;
    // 对应的表,由java代码生成的,对数据库内相应的表操作使用此对象
    private HistoryEntityDao historyEntityDao;
    //定义数据库的数组
    private List<HistoryEntity> history;

    private List<HistoryEntity> queryList;
    private int q = 0;
    //历史搜索适配器

    private HistoryListViewAdapter historyAdapter;

    //历史搜索假数据集合
    private List<String> karidata;

    //编辑框绑定ID
    @ViewInject(R.id.editText_home)
    private EditText editText_home;

    //测试绑定的数据
    @ViewInject(R.id.home_text)
    private TextView home_text;

    @ViewInject(R.id.search_btn_back)
    private TextView home_btn;

    //实体类的集合
    private List<SearchBean> typeData;

    //中文转换成GBK编码的变量
    private String strGBK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        //清空搜索的editText
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_home.setText("");
            }
        });

        // 初始化
        // 此DevOpenHelper类继承自SQLiteOpenHelper,
        // 第一个参数Context,第二个参数数据库名字,第三个参数CursorFactory
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "daodemo", null);
        //以下代码主要为了获得操作数据库的historyEntityDao
        db = helper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
        historyEntityDao = mDaoSession.getHistoryEntityDao();
//        historyEntityDao.deleteAll();//删除所有数据的方法

        //遍历数据库
        queryList = historyEntityDao.queryBuilder().list();
        history = new ArrayList<>();
        int a = queryList.size() - 1;
        for (int i = a; i >= 0; i--) {
            history.add(queryList.get(i));
        }

        //点击搜索按钮的时候添加搜索历史到数据库
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestParams params = new RequestParams(myUrl + "/Items.svc/seach/" + strGBK);
                Log.e("fdsgc", "http://218.24.166.153:3397//Items.svc/seach/" + strGBK);
                x.http().get(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Log.e("fdsgc", "wwwww");
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<BeanTwo>>() {
                        }.getType();
                        Log.e("fdsgc", "qqqqq");
                        datas = gson.fromJson(s, type);
                        Log.e("fdsgc", datas.get(0).getDetailUrl());

                        queryList = new ArrayList<>();
                        HistoryEntity his = new HistoryEntity();
                        his.setContent(editText_home.getText().toString());
                        his.setUrl(datas.get(0).getDetailUrl());
                        historyEntityDao.insert(his);
                        queryList.add(his);

                        Intent i = new Intent(SearchActivity.this, ShowProductActivity.class);
                        Log.d("dsadsdawsa", datas.get(0).getDetailUrl());
                        i.putExtra("showKey", datas.get(0).getDetailUrl());
                        i.putExtra("where", "4");
                        startActivity(i);
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

        //要解析的url
        //x.http()方法
        //Edittext中内容变化的监听回调接口
        editText_home.addTextChangedListener(watcher);


        String url = myUrl + "Items.svc/hotseach";
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<HotSearchBean>>() {
                }.getType();
                data = gson.fromJson(s, type);


                for (int i = 0; i < data.size(); i++) {
                    View view = LayoutInflater.from(SearchActivity.this).inflate(R.layout.item_hot, null);
                    TextView hot = (TextView) view.findViewById(R.id.tv_hot);
                    hot.setText(data.get(i).getSEANAME());
                    linearLayout.addView(view);
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


        //设置适配器
        //historyAdapter = new HistoryListViewAdapter();
        historyAdapter = new HistoryListViewAdapter(SearchActivity.this, history);

        historyListview.setAdapter(historyAdapter);

        //历史的listview进行item的监听
        historyListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchActivity.this, ShowProductActivity.class);
                intent.putExtra("showKey", history.get(position).getUrl());
                intent.putExtra("where", "4");
//                Log.e("gfdhgfh",history.get(position).getDetailUrl());
                startActivity(intent);
            }
        });


    }

    //edittext内容变化的监听
    public TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        //Edittext中内容变化后的监听
        @Override
        public void afterTextChanged(final Editable editable) {

            //定义handler 用于接收消息 接收到消息后就隐藏检索ListView
            final Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if (msg.what == 1) {
                        searchListview.setVisibility(View.GONE);
                    }
                }
            };
            //开启线程 发送消息 用来刷新UI
            final Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                }

            });
            //判断Edittext中如果没有内容 就让检索listview隐藏
            if ("".equals(editText_home.getText().toString().trim())) {
                searchListview.setVisibility(View.GONE);
                //如果edittext中内容为空 则开启线程刷新UI
                thread.start();
            }


            final String aaa = String.valueOf(editable);

            try {
                strGBK = URLEncoder.encode(aaa, "GBK");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            String url = MyUrl.url + "Items.svc/seach/" + strGBK;
            //上传我们在Edittext中输入的数据
            OkHttpClient ok = new OkHttpClient();
            final Request request2 = new Request.Builder().url(url).build();
            Call call = ok.newCall(request2);
            call.enqueue(new okhttp3.Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                //成功的回调
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    //把中文转化成 GBK编码的方法 如果不转换格式 在Edittext中输入中文 得不到服务器中的返回数据
                    try {
                        strGBK = URLEncoder.encode(aaa, "GBK");
                        Log.e("gdfg", strGBK);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    //得到服务器返回的数据 然后进行解析http://218.24.166.153:3397/Items.svc/seach/
                    RequestParams params = new RequestParams(MyUrl.url + "Items.svc/seach/" + strGBK);
                    x.http().get(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String s) {
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<SearchBean>>() {
                            }.getType();
                            typeData = gson.fromJson(s, type);

                            //处理的逻辑
                            if (typeData.size() > 0) {
                                searchListview.setVisibility(View.VISIBLE);
                                adapter = new ListViewAdapter(SearchActivity.this, typeData);
                                searchListview.setAdapter(adapter);


                                searchListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                                        RequestParams params = new RequestParams(MyUrl.url + "Items.svc/seach/" + strGBK);
                                        Log.d("ooo", "http://218.24.166.153:3397/Items.svc/seach/" + strGBK);

                                        x.http().get(params, new CommonCallback<String>() {

                                            @Override
                                            public void onSuccess(String s) {
                                                Gson gson = new Gson();
                                                Type type1 = new TypeToken<List<SearchBean>>() {
                                                }.getType();
                                                typeData = gson.fromJson(s, type1);
                                                // final SearchBean1 beannn = gson.fromJson(s, SearchBean1.class);
                                                Intent intent = new Intent(SearchActivity.this, ShowProductActivity.class);
                                                // Log.d("iii",beannn.getDetailUrl());
                                                intent.putExtra("showKey", typeData.get(position).getDetailUrl());
                                                intent.putExtra("where", "4");
                                                // Log.d("haeaaa",beannn.getDITEMS().get(position).getDetailUrl());
                                                startActivity(intent);
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

                            } else {
                                Toast.makeText(SearchActivity.this, "找不到你要的商品", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Throwable throwable, boolean b) {
                            editText_home.setHint("输入搜索内容");
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


        }
    };


    //点击返回到主页
    @Event(R.id.home_title_left)
    private void imgClick(View view) {
        finish();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);// 淡出淡入动画效果
    }

    //listView的item的监听
//    @Event(value = R.id.main_lv_search_results, type = AdapterView.OnItemClickListener.class)
//    private void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//        Toast.makeText(SearchActivity.this, position + "", Toast.LENGTH_SHORT).show();
//    }


}