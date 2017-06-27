package com.example.administrator.koudaiwanzi.person.set;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.MainActivity;
import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.dialog.CustomProgressDialog;
import com.example.administrator.koudaiwanzi.person.login.LoginActivity;
import com.example.administrator.koudaiwanzi.person.login.ReviseActivity;
import com.example.administrator.koudaiwanzi.person.point.pointmall.TigerMachineFragment;
import com.example.administrator.koudaiwanzi.upload.IdCardActivity;
import com.google.gson.Gson;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2016/6/3.
 */
@ContentView(R.layout.activity_account)
public class AccountActivity extends AppCompatActivity {
    private String url1 = MyUrl.url;
    private Button button;
    private String url;
    private String url3;
    private String picPath = null;
    private String newName = "image.jpg";
    @ViewInject(R.id.tv_account_name)
    private TextView tv_username;
    private String actionUrl = MyUrl.url + "FileStream.svc/portrait/";
    private String urlll;
    private int REQUEST_CODE_CAPTURE_CAMEIA = 1;
    private int REQUEST_CODE_PICK_IMAGE = 2;
    private static final int RESULT_CAMERA_CROP_PATH_RESULT = 301;
    private Uri imageUri;
    private Uri imageCropUri;
    private String b;//头像网址
    //昵称
    @ViewInject(R.id.et_name)
    private EditText et_name;

    //性别
    @ViewInject(R.id.et_sex)
    private TextView et_sex;

    //生日
    @ViewInject(R.id.et_birthday)
    private TextView et_birthday;

    @ViewInject(R.id.rl_account_date)
    private RelativeLayout rl_account_date;

    //头像
    @ViewInject(R.id.iv_person_picture)
    private ImageView iv_picture;

    @ViewInject(R.id.iv_person_picture)
    private ImageView imageView;

    //上传身份证信息页面
    @ViewInject(R.id.rl_account_idCard)
    private RelativeLayout idCard;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        final CustomProgressDialog dialog = new CustomProgressDialog(AccountActivity.this, R.style.CommProgressDialog, "加载中...", R.anim.frame2);
        dialog.setProgressStyle(R.style.CommProgressDialog);
        dialog.show();
        Intent intent = getIntent();
        final String date = intent.getStringExtra("date");
        SharedPreferences share = this.getSharedPreferences("logIn", Context.MODE_PRIVATE);
        final String UID = share.getString("login", "");
        String collect = share.getString("account", "");
        //上传数据绑定id
        button = (Button) findViewById(R.id.btn_post);
        String path = getSDCardPath();
        File file = new File(path + "/temp.jpg");
        imageUri = Uri.fromFile(file);
        File cropFile = new File(getSDCardPath() + "/temp_crop.jpg");
        imageCropUri = Uri.fromFile(cropFile);
        picPath = getRealFilePath(AccountActivity.this, imageCropUri);
        url = url1 + collect;
        urlll = actionUrl + UID;
        RequestParams params = new RequestParams(url);
        Log.e("veveve", url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String o) {
                dialog.cancel();
                Gson gson = new Gson();
                final AccountBean bean = gson.fromJson(o, AccountBean.class);
                idCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(AccountActivity.this, IdCardActivity.class);
                        intent.putExtra("idCard", bean.getPICDATAURL());
                        Log.e("wwwwsfafa",  bean.getPICDATAURL());
                        SharedPreferences shares = getSharedPreferences("uri", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = shares.edit();
                        editor.putString("uri", "7");
                        editor.apply();
                        startActivity(intent);
                    }
                });
                et_name.setText(bean.getNICKNAME());
                tv_username.setText(bean.getUSERNAME() + "");
                Log.d("332111", bean.getNICKNAME());
                if (bean.getSEX() == 0) {
                    et_sex.setText("女");
                } else {
                    et_sex.setText("男");
                }
                String result = formatData("yyyy-MM-dd", bean.getBIRTHDAY());

                if (date == null) {
                    et_birthday.setText(result);
                } else {
                    et_birthday.setText(date);
                }
                //绑定生日选择的监听
                rl_account_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(AccountActivity.this, DateActivity.class);
                        startActivity(intent1);
                    }
                });

                String a = bean.getHEADURL();

                b = url1 + a;

                x.image().bind(iv_picture, b);
                String url2 = bean.getDetailUrl();
                url3 = url1 + url2;

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String post = MyUrl.url + "Users.svc//saveacmgt//" + UID + "/";
                        String name = et_name.getText().toString();
                        String sex = (String) et_sex.getText();
                        int sexNum;
                        if (sex == "男") {
                            sexNum = 1;
                        } else {
                            sexNum = 0;
                        }
                        String age = et_birthday.getText().toString();
                        long re = getTime(age);
                        long re1 = re / 1000;

                        String toPost = post + "/" + name + "/" + sexNum + "/" + re1;

                        OkHttpClient ok = new OkHttpClient();
                        final Request request2 = new Request.Builder().url(toPost).build();
                        Call call = ok.newCall(request2);
                        call.enqueue(new okhttp3.Callback() {
                            public void onFailure(Call call, IOException e) {

                            }

                            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                                Looper.prepare();
                                Toast.makeText(AccountActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        });
                        new Thread(new MyThread()).start();

                    }
                });


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

    //上传头像
    @Event(R.id.rl_account_head)
    private void rlClick(View view) {
        choose_picture();
    }

    //性别选择
    @Event(R.id.rl_account_sex)
    private void sexClick(View view) {
        change_sex();
        et_sex.setText("");

    }


    public void change_sex() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //定义一个AlertDialog
        String[] strarr = {"男", "女"};
        builder.setItems(strarr, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                int sex = 1;
                // 自动生成的方法存根
                if (arg1 == 0) {//男
                    sex = 1;
                } else {//女
                    sex = 0;
                }
                // 自动生成的方法存根
                if (sex == 0) {//男
                    et_sex.setText("女");
                } else {//女
                    et_sex.setText("男");
                }
            }
        });
        builder.show();

    }

    public static String formatData(String dataFormat, long timeStamp) {
        if (timeStamp == 0) {
            return "";
        }
        timeStamp = timeStamp * 1000;
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        result = format.format(new Date(timeStamp));
        return result;
    }


    public static long getTime(String user_time) {
        long re_time = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            re_time = l;


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return re_time;
    }



    //退出登录
    @Event(R.id.rl_login_out)
    private void relClick(View view) {
        SharedPreferences share = AccountActivity.this.getSharedPreferences("logIn", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putString("login", "");
        editor.apply();
        Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    //返回键监听
    @Event(R.id.iv_account_back)
    private void imgBackClick(View view) {
//        Intent intent = new Intent(AccountActivity.this, MainActivity.class);
//        String tiao = "4";
//        intent.putExtra("tiaozhuan", tiao);
//        startActivity(intent);
        sendBroadcast();
        finish();
    }

    //修改密码
    @Event(R.id.rl_account_forget)
    private void forgetClick(View view) {
        Intent intent = new Intent(AccountActivity.this, ReviseActivity.class);
        startActivity(intent);
        finish();
    }

    //手机自带返回键监听
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        sendBroadcast();
//        Intent intent = new Intent(AccountActivity.this, MainActivity.class);
//        String tiao = "4";
//        intent.putExtra("tiaozhuan", tiao);
//        startActivity(intent);
        finish();
    }

    public void choose_picture() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //定义一个AlertDialog
        String[] strarr = {"拍照", "选择照片"};
        builder.setItems(strarr, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                // 自动生成的方法存根
                if (arg1 == 0) {
                    //拍照
                    takeCameraOnly();

                } else {
                    //选择照片
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");//相片类型
                    startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
                }
            }
        });
        builder.show();

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_PICK_IMAGE) {
//
//            //to do find the path of pic
            try {
                Uri uri = data.getData();

                cropImg(uri);

            } catch (Exception e) {
            }

        } else if (requestCode == REQUEST_CODE_CAPTURE_CAMEIA) {

            cropImg(imageUri);
        } else if (requestCode == RESULT_CAMERA_CROP_PATH_RESULT) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageCropUri));
                Log.d("iopujnv", "wwwwwwwwwww");
                imageView.setImageBitmap(bitmap);
                Log.d("iopujnv", "aaaaa");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /* 上传文件至Server的方法 */
    private void uploadFile() {
        String end = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        try {
            URL url = new URL(urlll);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
          /* 允许Input、Output，不使用Cache */
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
          /* 设置传送的method=POST */
            con.setRequestMethod("POST");
          /* setRequestProperty */
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");
            con.setRequestProperty("Content-Type",
                    "multipart/form-data;boundary=" + boundary);
          /* 设置DataOutputStream */
            DataOutputStream ds =
                    new DataOutputStream(con.getOutputStream());
            ds.writeBytes(twoHyphens + boundary + end);
            ds.writeBytes("Content-Disposition: form-data; " +
                    "name=\"file1\";filename=\"" +
                    newName + "\"" + end);
            ds.writeBytes(end);
          /* 取得文件的FileInputStream */
            //picPath 为选择的图片路径
            Log.d("wodsadsda", picPath);
            FileInputStream fStream = new FileInputStream(picPath);

          /* 设置每次写入1024bytes */
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = -1;
          /* 从文件读取数据至缓冲区 */
            while ((length = fStream.read(buffer)) != -1) {
            /* 将资料写入DataOutputStream中 */
                ds.write(buffer, 0, length);
            }
            ds.writeBytes(end);
            ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
          /* close streams */
//            Toast.makeText(PhotoUpload.this, "上传成功", Toast.LENGTH_SHORT).show();
            fStream.close();
            ds.flush();
          /* 取得Response内容 */
            InputStream is = con.getInputStream();
            int ch;
            StringBuffer b = new StringBuffer();
            while ((ch = is.read()) != -1) {
                b.append((char) ch);
            }
          /* 将Response显示于Dialog */


          /* 关闭DataOutputStream */
            ds.close();
        } catch (Exception e) {
//           dialog();
        }
    }

    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    private class MyThread implements Runnable {
        @Override
        public void run() {
            uploadFile();
        }

    }

    private void takeCameraOnly() {
        Intent intent = null;
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//action is capture
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, REQUEST_CODE_CAPTURE_CAMEIA);
    }

    public void cropImg(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 700);
        intent.putExtra("outputY", 700);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageCropUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, RESULT_CAMERA_CROP_PATH_RESULT);
    }

    public static String getSDCardPath() {
        String cmd = "cat /proc/mounts";
        Runtime run = Runtime.getRuntime();// 返回与当前 Java 应用程序相关的运行时对象
        try {
            Process p = run.exec(cmd);// 启动另一个进程来执行命令
            BufferedInputStream in = new BufferedInputStream(p.getInputStream());
            BufferedReader inBr = new BufferedReader(new InputStreamReader(in));

            String lineStr;
            while ((lineStr = inBr.readLine()) != null) {
                // 获得命令执行后在控制台的输出信息
                if (lineStr.contains("sdcard")
                        && lineStr.contains(".android_secure")) {
                    String[] strArray = lineStr.split(" ");
                    if (strArray != null && strArray.length >= 5) {
                        String result = strArray[1].replace("/.android_secure",
                                "");
                        return result;
                    }
                }
                // 检查命令是否执行失败。
                if (p.waitFor() != 0 && p.exitValue() == 1) {
                    // p.exitValue()==0表示正常结束，1：非正常结束
                }
            }
            inBr.close();
            in.close();
        } catch (Exception e) {

            return Environment.getExternalStorageDirectory().getPath();
        }

        return Environment.getExternalStorageDirectory().getPath();
    }
    //发送广播的方法
    public void sendBroadcast() {
        Intent intent1 = new Intent("com.example.administrator.myapplication.main.main.person.broadcast");
        LocalBroadcastManager.getInstance(AccountActivity.this).sendBroadcast(intent1);
    }
}

