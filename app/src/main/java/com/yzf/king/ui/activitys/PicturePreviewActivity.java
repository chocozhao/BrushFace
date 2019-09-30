package com.yzf.king.ui.activitys;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.yzf.king.R;

import java.lang.ref.WeakReference;


public class PicturePreviewActivity extends Activity {

    private static WeakReference<Bitmap> bitmap;

    public static void setBitmap(@Nullable Bitmap b) {
        bitmap = b != null ? new WeakReference<>(b) : null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_preview);
        ImageView imageView = findViewById(R.id.image);
        ImageView ok = findViewById(R.id.result_ok);
        ImageView cancle = findViewById(R.id.result_cancel);
        if (bitmap == null || bitmap.get() == null) {
            Toast.makeText(this, "文件处理失败，请重试", Toast.LENGTH_SHORT);
            finish();
            return;
        }
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(CameraActivity.RESULT_CODE, intent);
                finish();
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(0, intent);
                finish();
            }
        });
        imageView.setImageBitmap(bitmap.get());
//        ILFactory.getLoader().loadFile(imageView, file, new ILoader.Options(-1, R.mipmap.loading_fail_img).scaleType(ImageView.ScaleType.CENTER_CROP));


    }


}
