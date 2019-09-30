package com.yzf.king.ui.activitys;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.xw.repo.XEditText;
import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetCardInfoResult;
import com.yzf.king.present.PBankCardDetail;
import com.yzf.king.widget.StateButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
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
 * ClassName：BankCardDetailActivity
 * Description: 银行卡解绑和修改
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/4/3 14:08
 * Modified By：
 * Fixtime：2019/4/3 14:08
 * FixDescription：
 */
public class BankCardDetailActivity extends XActivity<PBankCardDetail> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bankcarddetail_iv)
    ImageView bankcarddetailIv;
    @BindView(R.id.bankcarddetail_bankname_tv)
    TextView bankcarddetailBanknameTv;
    @BindView(R.id.bankcarddetail_banktype_tv)
    TextView bankcarddetailBanktypeTv;
    @BindView(R.id.bankcarddetail_acctno_tv)
    TextView bankcarddetailAcctnoTv;
    @BindView(R.id.bankcarddetail_payday_tv)
    TextView bankcarddetailPaydayTv;
    @BindView(R.id.bankcarddetail_repayday_tv)
    TextView bankcarddetailRepaydayTv;
    @BindView(R.id.bankcarddetail_limit_tv)
    TextView bankcarddetailLimitTv;
    @BindView(R.id.bank_bg)
    LinearLayout bankBg;
    @BindView(R.id.bankcarddetail_cardno_tv)
    XEditText bankcarddetailCardnoTv;
    @BindView(R.id.bankcarddetail_cvn2_tv)
    XEditText bankcarddetailCvn2Tv;
    @BindView(R.id.bankcarddetail_expdate_tv)
    XEditText bankcarddetailExpdateTv;
    @BindView(R.id.bankcarddetail_statementday_tv)
    XEditText bankcarddetailStatementdayTv;
    @BindView(R.id.bankcarddetail_repaymentday_tv)
    XEditText bankcarddetailRepaymentdayTv;
    @BindView(R.id.bankcarddetail_phoneno_tv)
    XEditText bankcarddetailPhonenoTv;
    @BindView(R.id.bankcarddetail_name_tv)
    XEditText bankcarddetailNameTv;
    @BindView(R.id.bankcarddetail_unbind_bt)
    StateButton bankcarddetailUnbindBt;
    @BindView(R.id.bankcarddetail_modify_bt)
    StateButton bankcarddetailConfirmBt;
    GetCardInfoResult.DataBean cardBean = new GetCardInfoResult.DataBean();

    @Override
    public void initData(Bundle savedInstanceState) {
        cardBean = (GetCardInfoResult.DataBean) getIntent().getSerializableExtra("dataBean");
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bank_card_detail;
    }

    @Override
    public PBankCardDetail newP() {
        return new PBankCardDetail();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        bankcarddetailAcctnoTv.setText(setAcctno(cardBean.getCardId()));
        bankcarddetailBanknameTv.setText(cardBean.getBankName());
        bankcarddetailBanktypeTv.setText(setCardType(cardBean.getCardType()));
        if (cardBean.getBankName().contains("工商") || cardBean.getBankName().contains("中国银行")
                || cardBean.getBankName().contains("招商") || cardBean.getBankName().contains("中信")) {
            bankBg.setBackground(ContextCompat.getDrawable(context, R.drawable.bankcard_red));
        } else if (cardBean.getBankName().contains("农业") || cardBean.getBankName().contains("邮政")) {
            bankBg.setBackground(ContextCompat.getDrawable(context, R.drawable.bankcard_green));
        } else if (cardBean.getBankName().contains("交通") || cardBean.getBankName().contains("浦") || cardBean.getBankName().contains("民生")
                || cardBean.getBankName().contains("兴业") || cardBean.getBankName().contains("建设")) {
            bankBg.setBackground(ContextCompat.getDrawable(context, R.drawable.bankcard_blue));
        } else if (cardBean.getBankName().contains("平安") || cardBean.getBankName().contains("光大") || cardBean.getBankName().contains("农村")) {
            bankBg.setBackground(ContextCompat.getDrawable(context, R.drawable.bankcard_yellow));
        } else {
            bankBg.setBackground(ContextCompat.getDrawable(context, R.drawable.bankcard_red));
        }
        int id = getImgId("b" + cardBean.getBankCode());
        if (id > 0) {
            ILFactory.getLoader().loadCircleImage(id,bankcarddetailIv);
        }else {
            ILFactory.getLoader().loadCircleImage(getImgId("bank"),bankcarddetailIv);
        }

        bankcarddetailNameTv.setTextEx(cardBean.getName());
        bankcarddetailCardnoTv.setTextEx(cardBean.getCardId());
        bankcarddetailExpdateTv.setTextEx(cardBean.getValidDate());
        bankcarddetailCvn2Tv.setTextEx(cardBean.getCvv());
        bankcarddetailStatementdayTv.setTextEx(cardBean.getBillDate());
        bankcarddetailRepaymentdayTv.setTextEx(cardBean.getRepaymentDate());
        bankcarddetailPhonenoTv.setTextEx(cardBean.getPhone());
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
        title.setText(cardBean.getBankName());
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

    public void finish(String msg) {
        getvDelegate().toastShort(msg);
        finish();
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

    @OnClick({R.id.bankcarddetail_unbind_bt, R.id.bankcarddetail_modify_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bankcarddetail_unbind_bt:
                showNoticeDialog("是否删除该银行卡", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.POSITIVE) {
                            getvDelegate().showLoading();
                            getP().deleteBankCard(AppUser.getInstance().getMerchId(), cardBean.getCardId(), "4");
                        }
                    }
                });
                break;
            case R.id.bankcarddetail_modify_bt:
//                Router.newIntent(context)
//                        .to(ModifiedCreditCardctivity.class)
//                        .putSerializable("dataBean", cardBean)
//                        .launch();
                break;
        }
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

    private String setCardType(String cartType) {
        String type = "储蓄卡";
        if (!AppTools.isEmpty(cartType)) {
            switch (cartType) {
                case "00":
                    type = "信用卡";
                    break;
                case "01":
                    type = "储蓄卡";
                    break;
                case "02":
                    type = "准贷记卡";
                    break;
            }
        }
        return type;
    }
}
