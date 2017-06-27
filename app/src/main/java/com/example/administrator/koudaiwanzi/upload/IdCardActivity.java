package com.example.administrator.koudaiwanzi.upload;

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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.koudaiwanzi.R;
import com.example.administrator.koudaiwanzi.base.MyUrl;
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
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016/9/5.
 */
@ContentView(R.layout.activity_idcard)
public class IdCardActivity extends AppCompatActivity {
    private int REQUEST_CODE_PICK_IMAGE = 2;
    private int REQUEST_CODE_CAPTURE_CAMEIA = 1;
    private Uri imageUri;
    private Uri imageCropUri;
    private String picPath = null;
    private String newName = "image.jpg";
    private static final int RESULT_CAMERA_CROP_PATH_RESULT = 301;
    private String url;
    private String upFace;
    private String upBack;
    private String myUrl = MyUrl.url;
    private URL url_up;
    private String str;

    //确定上传信息
    @ViewInject(R.id.btn_idCard_right)
    private Button btn;

    //身份证号码
    @ViewInject(R.id.et_idCard_id)
    private EditText et_id;

    //姓名
    @ViewInject(R.id.et_idCard_name)
    private EditText et_name;

    //身份证正面图片
    @ViewInject(R.id.iv_card_face_idCard)
    private ImageView imageView_face;

    //身份证反面图片
    @ViewInject(R.id.iv_card_opposite_idCard)
    private ImageView imageView_oppsite;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
//        SharedPreferences share = this.getSharedPreferences("logIn", Context.MODE_PRIVATE);
//        final String UID = share.getString("login", "");

        url = myUrl + getIntent().getStringExtra("idCard");
        String path = getSDCardPath();
        File file = new File(path + "/temp2.jpg");
        imageUri = Uri.fromFile(file);
        File cropFile = new File(getSDCardPath() + "/temp_crop2.jpg");
        imageCropUri = Uri.fromFile(cropFile);
        picPath = getRealFilePath(IdCardActivity.this, imageCropUri);

        RequestParams params = new RequestParams(url);
        Log.e("jdjdjdj", url);
        x.http().get(params, new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String s) {
                                Gson gson = new Gson();
                                final IdCardBean bean = gson.fromJson(s, IdCardBean.class);
                                if (bean.getPERSON().getREALNAME() != null) {
                                    et_name.setText(bean.getPERSON().getREALNAME());
                                }

                                if (bean.getPERSON().getPICNUMBER() != null) {
                                    et_id.setText(bean.getPERSON().getPICNUMBER());
                                }

                                SharedPreferences share = getSharedPreferences("uri", Context.MODE_PRIVATE);
                                final String nums = share.getString("uri", "");

                                if (nums.equals("5")){
                                    if (bean.getPERSON().getPICBACK() != null) {
                                        x.image().bind(imageView_oppsite, myUrl + bean.getPERSON().getPICBACK());
                                    }
                                }else if (nums.equals("6")){
                                    if (bean.getPERSON().getPICFACE() != null) {
                                        x.image().bind(imageView_face, myUrl + bean.getPERSON().getPICFACE());
                                    }
                                }else {
                                    if (bean.getPERSON().getPICFACE() != null) {
                                        x.image().bind(imageView_face, myUrl + bean.getPERSON().getPICFACE());
                                    }
                                    if (bean.getPERSON().getPICBACK() != null) {
                                        x.image().bind(imageView_oppsite, myUrl + bean.getPERSON().getPICBACK());
                                    }
                                }




                                upFace = myUrl + bean.getPICFACEURL();
                                upBack = myUrl + bean.getPICBACKURL();
                                Log.e("wwwsss", upFace);

                                new Thread(new MyThread1()).start();

                                btn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //获取姓名和身份证号码
                                        final String name = et_name.getText().toString();
                                        final String id = et_id.getText().toString();

                                        try {
                                            str = URLEncoder.encode(name, "GBK");
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }


                                        Log.e("gzfad", name + id);
                                        RequestParams rp = new RequestParams(myUrl + bean.getDetailUrl() + str + "/" + id);
                                        Log.e("werty", myUrl + bean.getDetailUrl() + name + "/" + id);
                                        x.http().get(rp, new CommonCallback<String>() {
                                            @Override
                                            public void onSuccess(String s) {
                                                Log.e("adfz", "aaaaaa");
                                                if (personIdValidation(id)) {
                                                    Log.e("adfz", "wwwwww");
                                                    Gson gson = new Gson();
                                                    BackUpBean bean = gson.fromJson(s, BackUpBean.class);
                                                    int a = bean.getMsg();
                                                    if (a > 0) {
                                                        Toast.makeText(IdCardActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        Toast.makeText(IdCardActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Log.e("adfz", "eeeeee");
                                                    Toast.makeText(IdCardActivity.this, "身份证格式不正确", Toast.LENGTH_SHORT).show();
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
                        }

                );


//        OkHttpClient ok = new OkHttpClient();
//        final Request request2 = new Request.Builder().url_up(toPost).build();
//        Call call = ok.newCall(request2);
//        call.enqueue(new okhttp3.Callback() {
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            public void onResponse(Call call, okhttp3.Response response) throws IOException {
//                Looper.prepare();
//                Toast.makeText(IdCardActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
//                Looper.loop();
//            }
//        });


    }

    //正面照片
    @Event(R.id.iv_card_face_idCard)
    private void upFaceClick(View view) {
        SharedPreferences share = IdCardActivity.this.getSharedPreferences("id", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putString("id", "1");
        editor.apply();
        choose_picture();
    }

    //反面照片
    @Event(R.id.iv_card_opposite_idCard)
    private void upOppsiteClick(View view) {
        SharedPreferences share = IdCardActivity.this.getSharedPreferences("id", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putString("id", "2");
        editor.apply();
        choose_picture();

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

    private class MyThread1 implements Runnable {
        @Override
        public void run() {
            uploadFile2();
        }

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

                SharedPreferences share = this.getSharedPreferences("id", Context.MODE_PRIVATE);
                final String numss = share.getString("id", "");
                if (numss.equals("1")) {

                    imageView_face.setImageBitmap(bitmap);
                    Log.e("ggggg", "wwwww");
                    new Thread(new MyThread1()).start();
                } else {
                    imageView_oppsite.setImageBitmap(bitmap);
                    new Thread(new MyThread1()).start();
                }

                Log.d("iopujnv", "aaaaa");
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


    /* 上传文件至Server的方法 */
    private void uploadFile2() {
        String end = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        SharedPreferences share = this.getSharedPreferences("id", Context.MODE_PRIVATE);
        final String nums = share.getString("id", "");
        try {
            if (nums.equals("1")) {
                url_up = new URL(upFace);

                SharedPreferences shares = IdCardActivity.this.getSharedPreferences("uri", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shares.edit();
                editor.putString("uri", "5");
                editor.apply();

            } else {
                url_up = new URL(upBack);
                SharedPreferences shares = IdCardActivity.this.getSharedPreferences("uri", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shares.edit();
                editor.putString("uri", "6");
                editor.apply();
            }

            HttpURLConnection con = (HttpURLConnection) url_up.openConnection();
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

    private void takeCameraOnly() {
        Intent intent = null;
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//action is capture
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, REQUEST_CODE_CAPTURE_CAMEIA);
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

    @Event(R.id.iv_back_idCard)
    private void imaBack(View view) {
        finish();
    }

    /**
     * 验证身份证号是否符合规则
     *
     * @param text 身份证号
     * @return
     */
    public boolean personIdValidation(String text) {
        String regx = "[0-9]{17}x";
        String reg1 = "[0-9]{15}";
        String regex = "[0-9]{18}";
        return text.matches(regx) || text.matches(reg1) || text.matches(regex);
    }
}
