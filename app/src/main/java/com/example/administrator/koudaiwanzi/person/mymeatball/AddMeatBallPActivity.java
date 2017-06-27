package com.example.administrator.koudaiwanzi.person.mymeatball;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
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
 * Created by Administrator on 2016/09/20.
 */
@ContentView(R.layout.activity_add_meat_ball_p)
public class AddMeatBallPActivity extends AppCompatActivity implements View.OnClickListener {
    private int REQUEST_CODE_PICK_IMAGE = 222;
    private int REQUEST_CODE_CAPTURE_CAMEIA = 122;
    private Uri imageUri;
    private Uri imageCropUri;
    private String picPath = null;
    private String picPath22 = null;
    private String PicPath2 = null;
    private String newName = "image.jpg";
    private static final int RESULT_CAMERA_CROP_PATH_RESULT = 61;
    private String myUrl = MyUrl.url;
    private Runnable uploadImageRunnable,uploadImageRunnable2;
    private String str;
    private String resultStr = "";
    private String imageName,myMeatBall_DetailUrl,myAddurl,UpUrl,OneUrl;
    private String addurl = MyUrl.url;

    //加号的图片 一共有5个
    @ViewInject(R.id.meat_ball_11)
    private ImageView meat_ball_11;
    @ViewInject(R.id.meat_ball_12)
    private ImageView meat_ball_12;
    @ViewInject(R.id.meat_ball_13)
    private ImageView meat_ball_13;
    @ViewInject(R.id.meat_ball_14)
    private ImageView meat_ball_14;
    @ViewInject(R.id.meat_ball_15)
    private ImageView meat_ball_15;
    @ViewInject(R.id.meat_ball_return)
    private ImageView meat_ball_return;
    //定义一个标识符 控制加号图片的显示与隐藏
    private int chooses ;
    //要输入的内容editext
    @ViewInject(R.id.my_meat_ball_editext)
    private EditText editText;
    //设置一个string 用来接editext
    private String meat_zi;
    //发布按钮
    @ViewInject(R.id.tv_add_meatBall22)
    private TextView tv_add_meatBall;

    private static Uri uri;//当前选择图片的uri
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initViews();
       // url = myUrl + getIntent().getStringExtra("idCard");
        //拍照的uri
        String path = getSDCardPath();
        File file = new File(path + "/temp.jpg");
        imageUri = Uri.fromFile(file);
        //选择照片的uri
        //这里的imageCropUri 是上一张图片的uri  把cropImg 里换成imageCropUri 你就能看出来了
        File cropFile = new File(getSDCardPath() + "/temp_crop.jpg");
        imageCropUri = Uri.fromFile(cropFile);

        //我的丸子说跳过来传来的网址
        myMeatBall_DetailUrl = getIntent().getStringExtra("Mymeat_url");
        myAddurl =addurl +myMeatBall_DetailUrl;
        Log.d("myAddurl",myAddurl);
        //解析网址获得上传图片和文字的网址
        RequestParams params = new RequestParams(myAddurl);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                final SendMeatBallBean mymeat = gson.fromJson(s, SendMeatBallBean.class);
                UpUrl = addurl +mymeat.getIMGURL();
                //第一张图片的上唇网址
                OneUrl = addurl + mymeat.getPREVIEWURL();
                Log.d("OneUrl",OneUrl);
                //发布按钮的监听 点击发布上传文字
                tv_add_meatBall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            str = URLEncoder.encode(editText.getText().toString(), "GBK");
                            Log.d("try", str);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        //网址+你说输入的内容
                        meat_zi=  mymeat.getDetailUrl()+"/"+str;
                        //上传文字
                        RequestParams para = new RequestParams(myUrl +meat_zi);
                        x.http().get(para, new CommonCallback<String>() {
                            @Override
                            public void onSuccess(String s) {
                                Toast.makeText(AddMeatBallPActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AddMeatBallPActivity.this,MyMeatBallActivity.class);
                                startActivity(intent);
                                finish();
                                new Thread(uploadImageRunnable2).start();
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


                //上传图片的线程
                uploadImageRunnable = new Runnable() {
                    @Override
                    public void run() {

                        if(TextUtils.isEmpty(UpUrl)){
                            //	Toast.makeText(mContext, "还没有设置上传服务器的路径！", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Map<String, String> textParams = new HashMap<String, String>();
                        Map<String, File> fileparams = new HashMap<String, File>();

                        try {
                            Log.d("ttttt",UpUrl);
                            // 创建一个URL对象
                            URL url = new URL(UpUrl);
                            textParams = new HashMap<String, String>();
                            fileparams = new HashMap<String, File>();
                            // 要上传的图片文件
                            File file = new File(picPath);
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
                                Toast.makeText(AddMeatBallPActivity.this, "请求URL失败！", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                };

                //开启一下线程 防止三星手机 照相Bug
                new Thread(uploadImageRunnable).start();

                //共需要两个线程 这个线程是第一张图片上传时候调用的
                uploadImageRunnable2 = new Runnable() {
                    @Override
                    public void run() {
                        SharedPreferences share = getSharedPreferences("photos", Context.MODE_PRIVATE);
                        String PicPath21 = share.getString("photos", "");
                        Log.d("PicPath21",PicPath21);
                        if(TextUtils.isEmpty(OneUrl)){
                            //	Toast.makeText(mContext, "还没有设置上传服务器的路径！", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Map<String, String> textParams = new HashMap<String, String>();
                        Map<String, File> fileparams = new HashMap<String, File>();

                        try {
                            Log.d("ttttt",OneUrl);
                            // 创建一个URL对象
                            URL url = new URL(OneUrl);
                            textParams = new HashMap<String, String>();
                            fileparams = new HashMap<String, File>();
                            // 要上传的图片文件
                            File file = new File(PicPath21);
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
                                Toast.makeText(AddMeatBallPActivity.this, "请求URL失败！", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                };


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

    private void initViews() {
        meat_ball_11.setOnClickListener(this);
        meat_ball_12.setOnClickListener(this);
        meat_ball_13.setOnClickListener(this);
        meat_ball_14.setOnClickListener(this);
        meat_ball_15.setOnClickListener(this);
        meat_ball_return.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.meat_ball_return:
                finish();
                break;
            case R.id.meat_ball_11:
                choose_picture();//选择图片
                chooses =1;//标示位 来控制其他 “+” 图片的隐藏与现实
                break;

            case R.id.meat_ball_12:
                choose_picture();
                chooses =2;
                break;
            case R.id.meat_ball_13:
                choose_picture();
                chooses =3;
                break;
            case R.id.meat_ball_14:
                choose_picture();
                chooses =4;
                break;
            case R.id.meat_ball_15:
                choose_picture();
                chooses =5;
                break;

            default:

                break;
        }
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
                //这个是当前你选择的照片的uri
                 uri = data.getData();
                cropImg(uri);
                //获取照片的路径
                picPath = getRealFilePath(AddMeatBallPActivity.this, uri);

            } catch (Exception e) {
            }

        } else if (requestCode == REQUEST_CODE_CAPTURE_CAMEIA) {

            cropImg(imageUri);
            //获取拍照的路径
            picPath = getRealFilePath(AddMeatBallPActivity.this, imageUri);
        } else if (requestCode == RESULT_CAMERA_CROP_PATH_RESULT) {
            try {
                //获取显示缩略图的bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));

                //当选择第一个按钮的时候 表示符变成1
                switch (chooses){
                    case 1:
                        //重新获取一下路径 其实是多余的好像 直接用picPath也行
                        picPath22 = getRealFilePath(AddMeatBallPActivity.this, uri);
                        //因为第一张图片选择的时候不上传 那么 我们要把他的路径先保存下来
                        //在上面开启线程的时候在拿出来
                        SharedPreferences share = getSharedPreferences("photos", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = share.edit();
                        editor.putString("photos", picPath22);
                        editor.apply();

                        //我没有选择剪裁的话 就直接设置了  最后一次选择的 （上一张图片）
                        //下面两句话是 使显示的缩略图片大小一致
                        Bitmap bitmap1 = zoomBitmap(bitmap,meat_ball_11.getWidth(), meat_ball_11.getHeight());
                        meat_ball_11.setImageBitmap(bitmap1);
                        //meat_ball_11.setImageBitmap(bitmap);

                        //如果没有设置图片 吐司  设置图片了那么下一张显示
                        if(meat_ball_11.getDrawable() == null){
                            Toast.makeText(AddMeatBallPActivity.this, "也没有图片啊", Toast.LENGTH_SHORT).show();
                        }else {
                            meat_ball_12.setVisibility(View.VISIBLE);

                        }
                        break;

                    case 2:
                        Log.d("pitch",picPath);
                        Bitmap bitmap2 = zoomBitmap(bitmap,meat_ball_12.getWidth(), meat_ball_12.getHeight());
                        meat_ball_12.setImageBitmap(bitmap2);

                        //meat_ball_12.setImageBitmap(bitmap);
                        if(meat_ball_12.getDrawable() == null){
                            Toast.makeText(AddMeatBallPActivity.this, "也没有图片啊", Toast.LENGTH_SHORT).show();
                        }else {
                            meat_ball_13.setVisibility(View.VISIBLE);
                            new Thread(uploadImageRunnable).start();
                        }
                        break;

                    case 3:
                        Bitmap bitmap3 = zoomBitmap(bitmap,meat_ball_13.getWidth(), meat_ball_13.getHeight());
                        meat_ball_13.setImageBitmap(bitmap3);

                        //meat_ball_13.setImageBitmap(bitmap);
                        if(meat_ball_13.getDrawable() == null){
                            Toast.makeText(AddMeatBallPActivity.this, "也没有图片啊", Toast.LENGTH_SHORT).show();
                        }else {
                            meat_ball_14.setVisibility(View.VISIBLE);
                            new Thread(uploadImageRunnable).start();
                        }
                        break;

                    case 4:

                        Bitmap bitmap4 = zoomBitmap(bitmap,meat_ball_14.getWidth(), meat_ball_14.getHeight());
                        meat_ball_14.setImageBitmap(bitmap4);
                        //meat_ball_14.setImageBitmap(bitmap);
                        if(meat_ball_14.getDrawable() == null){
                            Toast.makeText(AddMeatBallPActivity.this, "也没有图片啊", Toast.LENGTH_SHORT).show();
                        }else {
                            meat_ball_15.setVisibility(View.VISIBLE);
                            new Thread(uploadImageRunnable).start();
                        }
                        break;

                    case 5:
                        Bitmap bitmap5 = zoomBitmap(bitmap,meat_ball_15.getWidth(), meat_ball_15.getHeight());
                        meat_ball_15.setImageBitmap(bitmap5);
                        //meat_ball_15.setImageBitmap(bitmap);
                        new Thread(uploadImageRunnable).start();
                        break;
                }
                //开启线程上传图片
              //  new Thread(uploadImageRunnable).start();



            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //对图片进行剪裁
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

    //调整bitmap大小的方法
    public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newBmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
        return newBmp;
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

}
