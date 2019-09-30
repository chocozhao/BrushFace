package com.yzf.king.ui.activitys;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraLogger;
import com.otaliastudios.cameraview.CameraOptions;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.Facing;
import com.otaliastudios.cameraview.SessionType;
import com.otaliastudios.cameraview.Size;
import com.yzf.king.R;
import com.yzf.king.kit.utils.BitmapUtil;
import com.yzf.king.widget.cameraView.Control;
import com.yzf.king.widget.cameraView.ControlView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.droidlover.xdroidmvp.log.Logger;


public class CameraActivity extends AppCompatActivity implements View.OnClickListener, ControlView.Callback {

    /**
     * 拍摄类型-身份证正面
     */
    public final static int TYPE_IDCARD_FRONT = 1;
    /**
     * 拍摄类型-身份证反面
     */
    public final static int TYPE_IDCARD_BACK = 2;
    /**
     * 拍摄类型-人面照
     */
    public final static int TYPE_PERSEN = 3;
    /**
     * 拍摄类型-银行卡正面
     */
    public final static int TYPE_BANKCARD = 4;
    private int type;
    private CameraView camera;
    private ViewGroup controlPanel;

    private boolean mCapturingPicture;
    private boolean mCapturingVideo;

    // To show stuff in the callback
    private Size mCaptureNativeSize;
    private long mCaptureTime;
    ImageView cropView;
    public final static int REQUEST_CODE = 0X13;
    public final static int RESULT_CODE = 0X14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        type = getIntent().getIntExtra("type", 0);
        /*if (type == TYPE_PERSEN) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }*/
        setContentView(R.layout.activity_cameraview);
        CameraLogger.setLogLevel(CameraLogger.LEVEL_VERBOSE);

        camera = findViewById(R.id.camera);
        camera.addCameraListener(new CameraListener() {
            public void onCameraOpened(CameraOptions options) {
                onOpened();
            }

            public void onPictureTaken(byte[] jpeg) {
                onPicture(jpeg);
            }

            @Override
            public void onVideoTaken(File video) {
                super.onVideoTaken(video);
                onVideo(video);
            }
        });


        findViewById(R.id.edit).setOnClickListener(this);
        findViewById(R.id.capturePhoto).setOnClickListener(this);
        findViewById(R.id.captureVideo).setOnClickListener(this);
        findViewById(R.id.toggleCamera).setOnClickListener(this);

        controlPanel = findViewById(R.id.controls);
        ViewGroup group = (ViewGroup) controlPanel.getChildAt(0);
        Control[] controls = Control.values();
        for (Control control : controls) {
            ControlView view = new ControlView(this, control, this);
            group.addView(view, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        controlPanel.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                BottomSheetBehavior b = BottomSheetBehavior.from(controlPanel);
                b.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });
        //获取屏幕最小边，设置为cameraPreview较窄的一边
        float screenMinSize = Math.min(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
        //根据screenMinSize，计算出cameraPreview的较宽的一边，长宽比为标准的16:9
        float maxSize = screenMinSize / 9.0f * 16.0f;
        RelativeLayout.LayoutParams layoutParams;
        if (type == TYPE_PERSEN) {
            camera.setFacing(Facing.FRONT);
//            layoutParams = new RelativeLayout.LayoutParams((int) screenMinSize, (int) maxSize);
        } else {
            camera.setFacing(Facing.BACK);
//            layoutParams = new RelativeLayout.LayoutParams((int) maxSize, (int) screenMinSize);
        }
        layoutParams = new RelativeLayout.LayoutParams((int) screenMinSize, (int) maxSize);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        FrameLayout contentView = (FrameLayout) getWindow().getDecorView().findViewById(R.id.camera);
        cropView = new ImageView(this);
        switch (type) {
            case TYPE_IDCARD_FRONT:
                cropView.setBackgroundResource(R.mipmap.camera_idcard_front);
                break;
            case TYPE_IDCARD_BACK:
                cropView.setBackgroundResource(R.mipmap.camera_idcard_back);
                break;
            case TYPE_PERSEN:
                cropView.setBackgroundResource(R.mipmap.camera_person);
                break;
            case TYPE_BANKCARD:
                cropView.setBackgroundResource(R.mipmap.camera_bankcard);
                break;
            default:
                cropView.setImageResource(R.mipmap.camera_bankcard);
                break;
        }
        cropView.setScaleType(ImageView.ScaleType.FIT_XY);
        if (type == TYPE_PERSEN) {
            float width = (int) (screenMinSize * 0.8);
            float height = (int) (width * 48.0f / 33.0f);
            FrameLayout.LayoutParams cropParams = new FrameLayout.LayoutParams((int) width, (int) height);
            cropParams.gravity = Gravity.CENTER;
            contentView.addView(cropView, cropParams);
        } else {
            float width = (int) (screenMinSize * 0.76);
            float height = (int) (width * 85.60f / 53.98f);
            /*float height = (int) (screenMinSize * 0.75);
            float width = (int) (height * 85.60f / 53.98f);*/
            FrameLayout.LayoutParams cropParams = new FrameLayout.LayoutParams((int) width, (int) height);
            cropParams.gravity = Gravity.CENTER;
            contentView.addView(cropView, cropParams);
        }


    }

    private void message(String content, boolean important) {
        int length = important ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
        Toast.makeText(this, content, length).show();
    }

    private void onOpened() {
        ViewGroup group = (ViewGroup) controlPanel.getChildAt(0);
        for (int i = 0; i < group.getChildCount(); i++) {
            ControlView view = (ControlView) group.getChildAt(i);
            view.onCameraOpened(camera);
        }
    }

    private void onPicture(final byte[] jpeg) {
        camera.stop();
        final long callbackTime = System.currentTimeMillis();
        if (mCapturingVideo) {
            message("Captured while taking video. Size=" + mCaptureNativeSize, false);
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    File originalFile = getOriginalFile();
                    FileOutputStream originalFileOutputStream = new FileOutputStream(originalFile);
                    originalFileOutputStream.write(jpeg);
                    originalFileOutputStream.close();

//                    Bitmap bitmap = BitmapFactory.decodeFile(originalFile.getPath());
                    Bitmap bitmap = BitmapUtil.getImage(originalFile.getPath());
                    //计算裁剪位置
                    float left, top, right, bottom;
                    left = (float) cropView.getLeft() / (float) camera.getWidth();
                    top = ((float) cropView.getTop()) / (float) camera.getHeight();
                    right = (float) cropView.getRight() / (float) camera.getWidth();
                    bottom = (float) cropView.getBottom() / (float) camera.getHeight();

                    Logger.d("left:" + left + "top:" + top + "right:" + right + "bottom:" + bottom);
                    Logger.d("left:" + cropView.getLeft() + "top:" + cropView.getTop() + "right:" + cropView.getRight() + "bottom:" + cropView.getBottom());
                    Logger.d("x:" + left * (float) bitmap.getWidth() + "y:" + top * (float) bitmap.getHeight() + "width:" + (right - left) * (float) bitmap.getWidth() + "height:" + (bottom - top) * (float) bitmap.getHeight());


                    int degrees = 0;
                    if (type == TYPE_PERSEN) {
                        degrees = 0;
                    }
                    Matrix matrix = new Matrix();
                    matrix.reset();
                    matrix.setRotate(degrees);

                    //裁剪及保存到文件
                    Bitmap cropBitmap = Bitmap.createBitmap(bitmap,
                            (int) (left * (float) bitmap.getWidth()),
                            (int) (top * (float) bitmap.getHeight()),
                            (int) ((right - left) * (float) bitmap.getWidth()),
                            (int) ((bottom - top) * (float) bitmap.getHeight()), matrix, true);


                    final File cropFile = getCropFile();
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(cropFile));
                    cropBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                    PicturePreviewActivity.setBitmap(cropBitmap);
                    bos.flush();
                    bos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // This can happen if picture was taken with a gesture.
                        Intent intent = new Intent(CameraActivity.this, PicturePreviewActivity.class);
                        intent.putExtra("delay", callbackTime - mCaptureTime);
                        intent.putExtra("nativeWidth", mCaptureNativeSize.getWidth());
                        intent.putExtra("nativeHeight", mCaptureNativeSize.getHeight());
                        startActivityForResult(intent, REQUEST_CODE);
                        return;
                    }
                });
            }
        }).start();
    }

    private void onVideo(File video) {
        mCapturingVideo = false;
        Intent intent = new Intent(CameraActivity.this, VideoPreviewActivity.class);
        intent.putExtra("video", Uri.fromFile(video));
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit:
                edit();
                break;
            case R.id.capturePhoto:
                capturePhoto();
                break;
            case R.id.captureVideo:
                captureVideo();
                break;
            case R.id.toggleCamera:
                toggleCamera();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        BottomSheetBehavior b = BottomSheetBehavior.from(controlPanel);
        if (b.getState() != BottomSheetBehavior.STATE_HIDDEN) {
            b.setState(BottomSheetBehavior.STATE_HIDDEN);
            return;
        }
        super.onBackPressed();
    }

    private void edit() {
        BottomSheetBehavior b = BottomSheetBehavior.from(controlPanel);
        b.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private void capturePhoto() {
        if (mCapturingPicture) return;
        mCapturingPicture = true;
        mCaptureTime = System.currentTimeMillis();
        mCaptureNativeSize = camera.getPictureSize();
//        message("Capturing picture...", false);
        camera.capturePicture();
    }

    private void captureVideo() {
        if (camera.getSessionType() != SessionType.VIDEO) {
            message("Can't record video while session type is 'picture'.", false);
            return;
        }
        if (mCapturingPicture || mCapturingVideo) return;
        mCapturingVideo = true;
        message("Recording for 8 seconds...", true);
        camera.startCapturingVideo(null, 8000);
    }

    private void toggleCamera() {
        if (mCapturingPicture) return;
        switch (camera.toggleFacing()) {
            case BACK:
//                message("Switched to back camera!", false);
                break;

            case FRONT:
//                message("Switched to front camera!", false);
                break;
        }
    }

    @Override
    public boolean onValueChanged(Control control, Object value, String name) {
        if (!camera.isHardwareAccelerated() && (control == Control.WIDTH || control == Control.HEIGHT)) {
            if ((Integer) value > 0) {
                message("This device does not support hardware acceleration. " +
                        "In this case you can not change width or height. " +
                        "The view will act as WRAP_CONTENT by default.", true);
                return false;
            }
        }
        control.applyValue(camera, value);
        BottomSheetBehavior b = BottomSheetBehavior.from(controlPanel);
        b.setState(BottomSheetBehavior.STATE_HIDDEN);
//        message("Changed " + control.getName() + " to " + name, false);
        return true;
    }

    //region Boilerplate

    @Override
    protected void onResume() {
        super.onResume();
        camera.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        camera.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        camera.destroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean valid = true;
        for (int grantResult : grantResults) {
            valid = valid && grantResult == PackageManager.PERMISSION_GRANTED;
        }
        if (valid && !camera.isStarted()) {
            camera.start();
        }
    }

    /**
     * @return 拍摄图片原始文件
     */
    private File getOriginalFile() {
        switch (type) {
            case TYPE_IDCARD_FRONT:
                return new File(getExternalCacheDir(), "idCardFront.jpg");
            case TYPE_IDCARD_BACK:
                return new File(getExternalCacheDir(), "idCardBack.jpg");
            case TYPE_PERSEN:
                return new File(getExternalCacheDir(), "person.jpg");
            case TYPE_BANKCARD:
                return new File(getExternalCacheDir(), "bankCard.jpg");
        }
        return new File(getExternalCacheDir(), "picture.jpg");
    }

    /**
     * @return 拍摄图片裁剪文件
     */
    private File getCropFile() {
        switch (type) {
            case TYPE_IDCARD_FRONT:
                return new File(getExternalCacheDir(), "idCardFrontCrop.jpg");
            case TYPE_IDCARD_BACK:
                return new File(getExternalCacheDir(), "idCardBackCrop.jpg");
            case TYPE_PERSEN:
                return new File(getExternalCacheDir(), "personCrop.jpg");
            case TYPE_BANKCARD:
                return new File(getExternalCacheDir(), "bankCardCrop.jpg");
        }
        return new File(getExternalCacheDir(), "pictureCrop.jpg");
    }

    /**
     * @param type {@link #TYPE_IDCARD_FRONT}
     *             {@link #TYPE_IDCARD_BACK}
     *             {@link #TYPE_PERSEN}
     *             {@link #TYPE_BANKCARD
     */
    public static void toCameraActivity(Activity activity, int type) {
        Intent intent = new Intent(activity, CameraActivity.class);
        intent.putExtra("type", type);
        activity.startActivityForResult(intent, REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_CODE) {
            Intent intent = new Intent();
            intent.putExtra("path", getCropFile().getPath());
            setResult(RESULT_CODE, intent);
            finish();
        } else {
            mCapturingVideo = false;
            mCapturingPicture = false;
        }
    }


    //endregion
}
