package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.lxj.xpopup.XPopup;
import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetMerchInfoResult;
import com.yzf.king.widget.MerchInfoPopup;
import com.yzf.king.widget.MultipleStatusView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
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
 * ClassName：MerchInfoActivity
 * Description:我的——>个人资料
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/2/20 19:52
 * Modified By：
 * Fixtime：2019/2/20 19:52
 * FixDescription：
 */
public class MerchInfoActivity extends XActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.merchinfo_father_tv)
    TextView merchinfoFatherTv;
    @BindView(R.id.merchinfo_phone_tv)
    TextView merchinfoPhoneTv;
    @BindView(R.id.merchinfo_status_tv)
    TextView merchinfoStatusTv;
    @BindView(R.id.merchinfo_head_iv)
    ImageView merchinfoHeadIv;
    @BindView(R.id.merchinfo_status_ll)
    LinearLayout merchinfoStatusLl;
    @BindView(R.id.multiplestatusview)
    MultipleStatusView multiplestatusview;


    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_merch_info;
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
        GetMerchInfoResult.DataBean dataBean = AppUser.getInstance().getMerchInfoBean();
//        merchinfoFatherTv.setText(AppTools.isEmpty(dataBean.getFatherName()) ? dataBean.getFatherId() : AppTools.formatName(dataBean.getFatherName()));
        merchinfoPhoneTv.setText(AppTools.formatPhone(dataBean.getPhone()));
        merchinfoStatusTv.setText("00".equals(dataBean.getStatus()) ? "点击查看" : AppTools.formatStatus(dataBean.getStatus()));
        /*switch (dataBean.getMerchLevel()) {
            case "0":
                merchinfoHeadIv.setImageResource(R.mipmap.level_2);
                break;
            case "1":
                merchinfoHeadIv.setImageResource(R.mipmap.level_3);
                break;
            case "10":
                merchinfoHeadIv.setImageResource(R.mipmap.level_10);
                break;
            case "11":
                merchinfoHeadIv.setImageResource(R.mipmap.level_11);
                break;
            case "12":
                merchinfoHeadIv.setImageResource(R.mipmap.level_12);
                break;
            case "13":
                merchinfoHeadIv.setImageResource(R.mipmap.level_13);
                break;
            case "14":
                merchinfoHeadIv.setImageResource(R.mipmap.level_14);
                break;
            default:
                merchinfoHeadIv.setImageResource(R.mipmap.level_2);
                break;
        }*/
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
        title.setText("个人资料");
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
        getvDelegate().dismissLoading();
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
     * 显示双按钮对话框
     *
     * @param msg
     * @param callback
     */
    public void showNoticeDialog(String msg, MaterialDialog.SingleButtonCallback callback) {
        getvDelegate().showNoticeDialog(msg, callback);
    }

    /**
     * 显示错误信息
     *
     * @param error
     */
    public void showError(NetError error) {
        getvDelegate().showError(error);
    }

    public void showEmptyView(String msg) {
        multiplestatusview.showEmpty(msg);
    }

    public void showErrorView(String msg) {
        multiplestatusview.showError(msg);
    }

    public void showErrorView(NetError error) {
        multiplestatusview.showError(getvDelegate().getErrorType(error));
    }

    /**
     * 显示双按钮对话框
     *
     * @param msg
     * @param
     */
    public void showDialog(String title, String msg, String positiveText, String negativeText, MaterialDialog.SingleButtonCallback singleButtonCallback, Boolean cancle) {
        getvDelegate().showDialog(title, msg, positiveText, negativeText, singleButtonCallback, cancle);
    }

    @OnClick(R.id.merchinfo_status_ll)
    public void onViewClicked() {
        verifyStatus(AppUser.getInstance().getStatus());
    }

    /**
     * 根据state判断用户状态
     *
     * @param state 用户状态
     * @return 已实名认证后返回true
     */
    public boolean verifyStatus(String state) {
        if (AppTools.isEmpty(state)) {
            state = AppUser.getInstance().getStatus();
        }
        if (state.equals("00")) {//通过实名认证
            new XPopup.Builder(context)
                    .asCustom(new MerchInfoPopup(context))
                    .show();

            return true;
        }
        if (state.equals("10")) {//
            showErrorDialog("收款账户已冻结");
            return false;
        }
        switch (state) {
            case "30": //
                return true;
            case "03":
                showDialog("请进行个人实名认证", getString(R.string.Verified_context), "立即认证", "取消", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.POSITIVE) {
                            JumpActivity(UploadIdCardZmActivity.class);
                        }
                    }
                }, true);
                return false;
            case "02": //{"message":"商户未上传照片","status":100,"state":"3","storeId":null}

                showDialog("请上传实名认证照片", getString(R.string.Verified_context), "立即上传", "取消", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.POSITIVE) {
                            JumpActivity(UploadFaceActivity.class);
                        }
                    }
                }, true);
                return false;
            case "01": //资料未通过

                showDialog("请进行个人实名认证", getString(R.string.Verified_context), "立即认证", "取消", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.POSITIVE) {
                            JumpActivity(UploadIdCardZmActivity.class);
                        }
                    }
                }, true);
                return false;
            default: //资料未通过

                showDialog("请进行个人实名认证", getString(R.string.Verified_context), "立即认证", "取消", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.POSITIVE) {
                            JumpActivity(UploadIdCardZmActivity.class);
                        }
                    }
                }, true);
                return false;
        }
    }
}
