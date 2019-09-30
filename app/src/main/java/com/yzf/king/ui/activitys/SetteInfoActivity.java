package com.yzf.king.ui.activitys;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetMerchInfoResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;

import static com.yzf.king.ui.activitys.UploadBankCardActivity.IDNO;
import static com.yzf.king.ui.activitys.UploadBankCardActivity.NAME;

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
 * ClassName：SetteInfoActivity
 * Description:
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/3/21 20:47
 * Modified By：
 * Fixtime：2019/3/21 20:47
 * FixDescription：
 */
public class SetteInfoActivity extends XActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bank_iv)
    ImageView bankIv;
    @BindView(R.id.setteinfo_change_tv)
    TextView setteinfoChangeTv;
    @BindView(R.id.setteinfo_bankname_tv)
    TextView setteinfoBanknameTv;
    @BindView(R.id.setteinfo_banktype_tv)
    TextView setteinfoBanktypeTv;
    @BindView(R.id.setteinfo_acctno_tv)
    TextView setteinfoAcctnoTv;
    @BindView(R.id.bank_bg)
    LinearLayout bankBg;
    GetMerchInfoResult.DataBean dataBean;

    @Override
    public void initData(Bundle savedInstanceState) {
        dataBean = AppUser.getInstance().getMerchInfoBean();
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sette_info;
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
        if (dataBean!=null) {
            setteinfoAcctnoTv.setText(setAcctno(dataBean.getSettleCardNo()));
            setteinfoBanknameTv.setText(dataBean.getSettleBankName());
            setteinfoBanktypeTv.setText("储蓄卡");
            if (!AppTools.isEmpty(dataBean.getSettleBankName())) {
                if (dataBean.getSettleBankName().contains("工商") || dataBean.getSettleBankName().contains("中国银行")
                        || dataBean.getSettleBankName().contains("招商") || dataBean.getSettleBankName().contains("中信")) {
                    bankBg.setBackground(ContextCompat.getDrawable(context, R.drawable.bankcard_red));
                } else if (dataBean.getSettleBankName().contains("农业") || dataBean.getSettleBankName().contains("邮政")) {
                    bankBg.setBackground(ContextCompat.getDrawable(context, R.drawable.bankcard_green));
                } else if (dataBean.getSettleBankName().contains("交通") || dataBean.getSettleBankName().contains("浦") || dataBean.getSettleBankName().contains("民生")
                        || dataBean.getSettleBankName().contains("兴业") || dataBean.getSettleBankName().contains("建设")) {
                    bankBg.setBackground(ContextCompat.getDrawable(context, R.drawable.bankcard_blue));
                } else if (dataBean.getSettleBankName().contains("平安") || dataBean.getSettleBankName().contains("光大") || dataBean.getSettleBankName().contains("农村")) {
                    bankBg.setBackground(ContextCompat.getDrawable(context, R.drawable.bankcard_yellow));
                } else {
                    bankBg.setBackground(ContextCompat.getDrawable(context, R.drawable.bankcard_red));
                }
            }
            int id = getImgId("b" + dataBean.getSettleBankCode());
            if (id > 0) {
                ILFactory.getLoader().loadCircleImage(id,bankIv);
            }else {
                ILFactory.getLoader().loadCircleImage(getImgId("bank"),bankIv);
            }
        }
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
        title.setText("变更储蓄卡");
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
    public void JumpActivity(Class<?> activity, boolean isfinish, String idNo, String name) {
        Router.newIntent(context)
                .to(activity)
                .putString(IDNO, idNo)
                .putString(NAME, name)
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

    /**
     * 显示错误信息
     *
     * @param error
     */
    public void showError(NetError error) {
        getvDelegate().showError(error);
    }

    @OnClick(R.id.setteinfo_change_tv)
    public void onViewClicked() {
        JumpActivity(UploadBankCardDiyActivity.class, false, dataBean.getIdNo(), dataBean.getIdName());
    }


    private int getImgId(String imgName) {
        int id = -1;
        try {
            id = context.getResources().getIdentifier(imgName, "mipmap", context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    private String setAcctno(String acctNo) {
        if (!AppTools.isEmpty(acctNo)) {
            acctNo = acctNo.substring(0, 4) + "     ****     ****     " + acctNo.substring(acctNo.length() - 4, acctNo.length());

        }
        return acctNo;
    }


}
