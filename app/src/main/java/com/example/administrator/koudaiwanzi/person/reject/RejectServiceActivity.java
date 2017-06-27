package com.example.administrator.koudaiwanzi.person.reject;

import android.app.AlertDialog;
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
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
import com.example.administrator.koudaiwanzi.person.mymeatball.NetUtil;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/2.
 * 退款成功界面
 */

@ContentView(R.layout.fragment_service_apply)
public class RejectServiceActivity extends AppCompatActivity{
    private String myUrl = MyUrl.url;
    private String url2,url3;
    private int REQUEST_CODE_PICK_IMAGE = 662;
    private int REQUEST_CODE_CAPTURE_CAMEIA = 62;
    private Uri imageUri;
    private Uri imageCropUri;
    private String picPath = null;
    private String PicPath3 = null;
    private String newName = "image.jpg";
    private static final int RESULT_CAMERA_CROP_PATH_RESULT = 601;
    private URL url_up;
    private Runnable uploadImageRunnable3;
    private String str,meat_zi;
    private String resultStr = "";
    private String imageName,myMeatBall_DetailUrl,myAddurl,UpUrl,OneUrl;
    private  Uri uri;//当前选择图片的uri
    @ViewInject(R.id.order_pic)
    private RelativeLayout order_pic;
    private Bitmap bitmap;
    @ViewInject(R.id.service_pic)
    private ImageView service_pic;

    @ViewInject(R.id.order_frame)
    private FrameLayout order_frame;

    @ViewInject(R.id.sure)
    private TextView sure;
    private int a;

    @ViewInject(R.id.service_ET)
    private EditText service_ET;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        String path = getSDCardPath();
        File file = new File(path + "/temp.jpg");
        imageUri = Uri.fromFile(file);
        //选择照片的uri
        File cropFile = new File(getSDCardPath() + "/temp_crop.jpg");
        imageCropUri = Uri.fromFile(cropFile);

        SharedPreferences share = getSharedPreferences("ttt", Context.MODE_PRIVATE);
        PicPath3 = share.getString("ttt", "");



        order_frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose_picture();
                a = 1;
            }
        });

        String url_apply = getIntent().getStringExtra("sec");
        url2 = myUrl + url_apply;

        final RequestParams params = new RequestParams(url2);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                final SecBean bean = gson.fromJson(s,SecBean.class);
                //山川图片的网址
                  url3 = myUrl + bean.getINSERTCERTPICURL();
                Log.d("url2",url3);


                sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new Thread(uploadImageRunnable3).start();
                        Toast.makeText(RejectServiceActivity.this, "上传了", Toast.LENGTH_SHORT).show();

                        //上传文字
                        try {
                            str = URLEncoder.encode(service_ET.getText().toString(), "GBK");
                            Log.d("try", str);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        //网址+你说输入的内容
                        meat_zi=  bean.getINSERTORDERNUMURL()+"/"+str;

                        RequestParams para = new RequestParams(myUrl +meat_zi);
                        x.http().get(para, new CommonCallback<String>() {
                            @Override
                            public void onSuccess(String s) {

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



        //上传图片的线程
        uploadImageRunnable3 = new Runnable() {
            @Override
            public void run() {

                if(TextUtils.isEmpty(url3)){
                    //	Toast.makeText(mContext, "还没有设置上传服务器的路径！", Toast.LENGTH_SHORT).show();
                    return;
                }

                Map<String, String> textParams = new HashMap<String, String>();
                Map<String, File> fileparams = new HashMap<String, File>();

                try {
                    Log.d("ttttt",url3);
                    // 创建一个URL对象
                    URL url = new URL(url3);
                    textParams = new HashMap<String, String>();
                    fileparams = new HashMap<String, File>();
                    // 要上传的图片文件
                    File file = new File(picPath);
                    Log.d("pith",picPath);
                    fileparams.put("image", file);
                    // 利用HttpURLConnection对象从网络中获取网页数据
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    // 设置连接超时（记得设置连接超时,如果网络不好,Android系统在超过默认时间会收回资源中断操作）
                    conn.setConnectTimeout(5000);
                    // 设置允许输出（发送POST请求必须设置允许输出）
                    conn.setDoOutput(true);
                    // 设置使用POST的方式发送
                    conn.setRequestMethod("POST");
                    // 设置不使用缓存（容易出现问题）
                    conn.setUseCaches(false);
                    // 在开始用HttpURLConnection对象的setRequestProperty()设置,就是生成HTML文件头
                    conn.setRequestProperty("ser-Agent", "Fiddler");
                    // 设置contentType
                    conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + NetUtil.BOUNDARY);
                    OutputStream os = conn.getOutputStream();
                    DataOutputStream ds = new DataOutputStream(os);
                    NetUtil.writeStringParams(textParams, ds);
                    NetUtil.writeFileParams(fileparams, ds);
                    NetUtil.paramsEnd(ds);
                    // 对文件流操作完,要记得及时关闭
                    os.close();
                    // 服务器返回的响应吗
                    int code = conn.getResponseCode(); // 从Internet获取网页,发送请求,将网页以流的形式读回来
                    // 对响应码进行判断
                    if (code == 200) {// 返回的响应码200,是成功
                        // 得到网络返回的输入流
                        InputStream is = conn.getInputStream();
                        resultStr = NetUtil.readString(is);
                        Log.d("resultStr",resultStr);
                    } else {
                        Toast.makeText(RejectServiceActivity.this, "请求URL失败！", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        };

        //开启一下线程 防止三星手机 照相Bug
       // new Thread(uploadImageRunnable3).start();

    }


    //获取sd卡路径
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
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA );
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }




    //选择拍照还是相册
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

    //结果的回调
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //选择照片
        if (requestCode == REQUEST_CODE_PICK_IMAGE) {
            try {
                uri = data.getData();
                cropImg(uri);
                //获取照片的路径
                picPath = getRealFilePath(RejectServiceActivity.this, uri);
            } catch (Exception e) {
            }

        } else if (requestCode == REQUEST_CODE_CAPTURE_CAMEIA) {

            cropImg(imageUri);
            //获取拍照的路径
            picPath = getRealFilePath(RejectServiceActivity.this, imageUri);
        } else if (requestCode == RESULT_CAMERA_CROP_PATH_RESULT) {
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageCropUri));

                SharedPreferences share = getSharedPreferences("ttt", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = share.edit();
                editor.putString("ttt", picPath);
                editor.apply();

                if(a ==1){
                    order_pic.setVisibility(View.INVISIBLE);
                    service_pic.setVisibility(View.VISIBLE);
                    service_pic.setImageBitmap(bitmap);
                }



//                switch (chooses){
//                    case 1:
//                        SharedPreferences share = getSharedPreferences("tupian", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = share.edit();
//                        editor.putString("tupian", picPath);
//                        editor.apply();
//
//
//                        Log.d("pitch",picPath);
//                        meat_ball_11.setImageBitmap(bitmap);
//                        //如果没有设置图片 吐司  设置图片了那么下一张显示
//                        if(meat_ball_11.getDrawable() == null){
//                            Toast.makeText(AddMeatBallPActivity.this, "也没有图片啊", Toast.LENGTH_SHORT).show();
//                        }else {
//                            meat_ball_12.setVisibility(View.VISIBLE);
//
//                        }
//                        break;


                //}
                //开启线程上传图片
                 //new Thread(uploadImageRunnable3).start();



            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
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


    //调用相机照相
    private void takeCameraOnly() {
        Intent intent = null;
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//action is capture
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, REQUEST_CODE_CAPTURE_CAMEIA);
    }





//    @Event(R.id.iv_service_apply_back)
//    private void backClick(View view){
////        Intent intent = new Intent(RejectServiceActivity.this,RejectActivity.class);
////        startActivity(intent);
//        finish();
//    }
}
