package com.yzf.king.ui.activitys;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxj.xpopup.XPopup;
import com.yzf.king.R;
import com.yzf.king.kit.AppKit;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.utils.ImageUtil;
import com.yzf.king.ui.fragments.ShareFragment;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;

import static com.yzf.king.App.getContext;

public class WeChatPublicActivity extends XActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.wechat_message_logo_iv)
    ImageView wechatMessageLogoIv;
    @BindView(R.id.wechat_message_name_tv)
    TextView wechatMessageNameTv;
    @BindView(R.id.wechat_message_way_one_tv)
    TextView wechatMessageWayOneTv;
    @BindView(R.id.wechat_message_way_one)
    TextView wechatMessageWayOne;
    @BindView(R.id.wechat_message_way_two_tv)
    TextView wechatMessageWayTwoTv;
    @BindView(R.id.wechat_message_way_two)
    TextView wechatMessageWayTwo;
    @BindView(R.id.wechat_message_way_two_iv)
    ImageView wechatMessageWayTwoIv;
    @BindView(R.id.wechat_public_save_tv)
    TextView wechatPublicSaveTv;


    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_we_chat_public;
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
        wechatMessageNameTv.setText("乐收官方公众号");
        wechatMessageWayOne.setText(Html.fromHtml("打开微信，搜索<font color='#008AFF'>“乐收刷脸支付”</font>，点击关注。"));
        wechatMessageWayTwo.setText("扫一扫下面的二维码图案，关注公众号");
    }

    /**
     * 初始化Toolbar
     */
    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_arrow_left_black_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title.setText("微信公众号");

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
     * activity跳转
     */
    public void JumpActivity(Class<?> activity) {
        Router.newIntent(context)
                .to(activity)
                .launch();
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

    /**
     * 显示错误信息
     *
     * @param error
     */
    public void showError(NetError error) {
        getvDelegate().showError(error);
    }


    @OnClick(R.id.wechat_public_save_tv)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wechat_public_save_tv:
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.wechat_public_code_iv);
                boolean isSaveSuccess = ImageUtil.saveImageToGallery(context, bitmap);
                if (isSaveSuccess) {
                    showToast("保存图片成功");
                } else {
                    showToast("保存图片失败，请稍后重试");
                }
                break;
            default:
                break;
        }
    }
}
