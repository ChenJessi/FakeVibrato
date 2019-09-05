package com.chen.fakevibrato.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static android.content.Intent.ACTION_MEDIA_SCANNER_SCAN_FILE;

public class CyCamera implements Camera.PreviewCallback {
    private Camera camera;
    private SurfaceTexture surfaceTexture;
    private SurfaceHolder surfaceHolder;

    private int width;
    private int height;
    private Context context;
    public CyCamera(Context context) {
        this.context = context;
        this.width = DisplayUtils.getScreenWidth(context);
        this.height = DisplayUtils.getScreenHeight(context);
    }

//    public void initCamera(SurfaceTexture surfaceTexture, int cameraId) {
//        this.surfaceTexture = surfaceTexture;
//        setCameraParm(cameraId);
//    }

    public void initCamera(SurfaceHolder surfaceHolder, int cameraId) {
        this.surfaceHolder = surfaceHolder;
        setCameraParm(cameraId);
    }


    public void setCameraParm(int cameraId) {

        camera = Camera.open(cameraId);
        camera.setDisplayOrientation(90);

        final Camera.Parameters parameters = camera.getParameters();

        parameters.setFlashMode("off");
        parameters.setPreviewFormat(ImageFormat.NV21);

        Camera.Size size = getFitSize(parameters.getSupportedPictureSizes());
        parameters.setPictureSize(size.width, size.height);
        parameters.setPreviewFrameRate(30);
        size = getFitSize(parameters.getSupportedPreviewSizes());
        parameters.setPreviewSize(size.width, size.height);

        camera.setPreviewCallback(this);


        List<String> focusModes = parameters.getSupportedFocusModes();
        if (focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)) {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        }
        camera.setParameters(parameters);

    }


    public void startPreview(SurfaceHolder surfaceHolder) {
        if (camera != null) {
            try {
                camera.setPreviewDisplay(surfaceHolder);
                camera.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void stopPreview() {
        if (camera != null) {
            camera.stopPreview();
            camera.setPreviewCallback(null);
            camera.release();
            camera = null;
        }
    }

    public void changeCamera(int cameraId) {
        if (camera != null) {

            stopPreview();
        }
        setCameraParm(cameraId);
    }

    private Camera.Size getFitSize(List<Camera.Size> sizes) {
        if (width < height) {
            int t = height;
            height = width;
            width = t;
        }

        for (Camera.Size size : sizes) {
            if (1.0f * size.width / size.height == 1.0f * width / height) {
                return size;
            }
        }
        return sizes.get(0);
    }
    private int i = 0;
    @Override
    public void onPreviewFrame(byte[] bytes, Camera camera) {
        i ++;
        if (i%30 != 0){
            return;
        }
        YuvImage yuvimage = new YuvImage(
                bytes,
                ImageFormat.NV21,
                width ,
                height ,
                null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        yuvimage.compressToJpeg(new Rect(0, 0, width , height ), 70, baos);// 80--JPG图片的质量[0-100],100最高
        byte[] rawImage= baos.toByteArray();
        //将rawImage转换成bitmap
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeByteArray(rawImage, 0, rawImage.length, options);

        saveImageToGallery(bitmap);
        bitmap.recycle();
        bitmap = null;

    }

    private int saveImageToGallery(Bitmap bitmap){
        //生成路径
        String root = Environment.getExternalStorageDirectory().getAbsolutePath();
        String dirName = "chencc";
        File appDir = new File(root, dirName);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        //文件名为时间
        String fileName =   "chencc"+i/30+".jpg";
        //获取文件
        File file = new File(appDir, fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            //通知系统相册刷新
            context.sendBroadcast(
                    new Intent(
                            ACTION_MEDIA_SCANNER_SCAN_FILE,
                            Uri.fromFile(new File(file.getPath()))
                    )
            );
                    Toast.makeText(context, "保存成功！", Toast.LENGTH_SHORT).show();
            return 2;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }
}
