package com.yzf.king.ui.activitys;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.github.chrisbanes.photoview.PhotoView;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.utils.ImageUtil;
import com.yzf.king.model.CardItem;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * 　　┏┓　　　┏┓+ +
 * 　┏┛┻━━━┛┻┓ + +
 * 　┃　　　　　　　┃
 * 　┃　　　━　　　┃ ++ + + +
 * ████━████ ┃+
 * 　┃　　　　　　　┃ +
 * 　┃　　　┻　　　┃
 * 　┃　　　　　　　┃ + +
 * 　┗━┓　　　┏━┛
 * 　　　┃　　　┃
 * 　　　┃　　　┃ + + + +
 * 　　　┃　　　┃
 * 　　　┃　　　┃ +  神兽镇楼
 * 　　　┃　　　┃    代码无bug
 * 　　　┃　　　┃　　+
 * 　　　┃　 　　┗━━━┓ + +
 * 　　　┃ 　　　　　　　┣┓
 * 　　　┃ 　　　　　　　┏┛
 * 　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　┃┫┫　┃┫┫
 * 　　　　┗┻┛　┗┻┛+ + + +
 * <p>
 * ClassName：PhotoActivity
 * Description:
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/2/20 14:33
 * Modified By：
 * Fixtime：2019/2/20 14:33
 * FixDescription：
 */
public class PhotoActivity extends XActivity {
    /*@BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;*/
    @BindView(R.id.photo_iv)
    PhotoView photoIv;
    public static final String RESOURCE_URL = "url";
    public static final String RESOURCE_ID = "resourceId";
    public static final String FILEPATH = "filePath";
    String url;
    int resourceId;
    String filePath;

    @Override
    public void initData(Bundle savedInstanceState) {
        url = getIntent().getStringExtra(RESOURCE_URL);
        resourceId = getIntent().getIntExtra(RESOURCE_ID, 0);
        filePath = getIntent().getStringExtra(FILEPATH);
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_photo;
    }

    @Override
    public Object newP() {
        return null;
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        if (resourceId > 0) {
            ILFactory.getLoader().loadImageSizekipMemoryCache(resourceId, photoIv);
        } else if (!AppTools.isEmpty(url)) {
            ILFactory.getLoader().loadImageSizekipMemoryCache(url, photoIv);
        } else if (!AppTools.isEmpty(filePath)) {
            ILFactory.getLoader().loadImageSizekipMemoryCache(filePath, photoIv);
        }
    }

    public static void launch(Activity activity, String url, int resourceId, String filePath) {
        Router.newIntent(activity)
                .to(PhotoActivity.class)
                .putString(RESOURCE_URL, url)
                .putInt(RESOURCE_ID, resourceId)
                .putString(FILEPATH, filePath)
                .launch();
    }

    /**
     * 初始化Toolbar
     */
    private void initToolbar() {
       /* setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_arrow_left_black_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title.setText("");*/
    }

    /**
     * 标题栏监听
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * activity跳转
     */
    public void JumpActivity(Class<?> activity, boolean isfinish) {
        getvDelegate().dismissLoading();
        Router.newIntent(context)
                .to(activity)
                .launch();
        if (isfinish) {
            Router.pop(context);
        }
    }

    /**
     * 显示Toast
     *
     * @param msg
     */
    public void showToast(String msg) {
        getvDelegate().toastShort(msg);
    }

    /**
     * 显示单按钮对话框
     *
     * @param msg
     */
    public void showErrorDialog(String msg) {
        getvDelegate().showErrorDialog(msg);
    }


    @OnClick(R.id.photo_iv)
    public void onViewClicked() {
        finish();
    }
}
