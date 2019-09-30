package com.yzf.king.ui.activitys;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetMachinApplyInfoDtlResult;
import com.yzf.king.model.servicesmodels.GetMachinApplyInfoResults;
import com.yzf.king.model.servicesmodels.GetMyShopInfoResult;
import com.yzf.king.model.servicesmodels.GetSunMerchInfoListResult;
import com.yzf.king.present.POrderManagePromotionDtl;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;

public class OrderManagePromotionDtlActivity extends XActivity<POrderManagePromotionDtl> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.promotion_dtl_hear_iv)
    ImageView promotionDtlHearIv;
    @BindView(R.id.promotion_dtl_title_tv)
    TextView promotionDtlTitleTv;
    @BindView(R.id.promotion_dtl_stauts_tv)
    TextView promotionDtlStautsTv;
    @BindView(R.id.promotion_dtl_iv)
    ImageView promotionDtlIv;
    @BindView(R.id.promotion_dtl_receipt_tv)
    TextView promotionDtlReceiptTv;
    @BindView(R.id.promotion_dtl_receipt_ll)
    LinearLayout promotionDtlReceiptLl;
    @BindView(R.id.promotion_dtl_mail_tv)
    TextView promotionDtlMailTv;
    @BindView(R.id.promotion_dtl_mail_ll)
    LinearLayout promotionDtlMailLl;
    @BindView(R.id.promotion_dtl_receiptname_tv)
    TextView promotionDtlReceiptnameTv;
    @BindView(R.id.promotion_dtl_acc_tv)
    TextView promotionDtlAccTv;
    @BindView(R.id.promotion_dtl_remarks_tv)
    TextView promotionDtlRemarksTv;
    private GetMachinApplyInfoResults.DataBean applyDataBean;
    private GetSunMerchInfoListResult.DataBean.SubListBean subListBean;
    private GetMyShopInfoResult.DataBean.DataListBean dataListBean;
    private String type;
    private String signtype;

    @Override
    public void initData(Bundle savedInstanceState) {
        applyDataBean = (GetMachinApplyInfoResults.DataBean) getIntent().getSerializableExtra("applyDataBean");
        subListBean = (GetSunMerchInfoListResult.DataBean.SubListBean) getIntent().getSerializableExtra("subListBean");
        dataListBean = (GetMyShopInfoResult.DataBean.DataListBean) getIntent().getSerializableExtra("dataListBean");
        type = getIntent().getStringExtra("type");
        signtype = getIntent().getStringExtra("signtype");
        initView();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_promotion_dtl;
    }

    @Override
    public POrderManagePromotionDtl newP() {
        return new POrderManagePromotionDtl();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        if (applyDataBean != null) {
            promotionDtlTitleTv.setText(applyDataBean.getShopName());
            ILFactory.getLoader().loadCircleImage(applyDataBean.getLogoUrl(), promotionDtlHearIv);
            getP().getMachinApplyInfoDtl(AppUser.getInstance().getMerchId(), applyDataBean.getOrderId(), applyDataBean.getShopId());
        }

        if (!AppTools.isEmpty(signtype)) {
            if (subListBean != null) {
                promotionDtlTitleTv.setText(subListBean.getShopName());
                ILFactory.getLoader().loadCircleImage(dataListBean.getLogoUrl(), promotionDtlHearIv);
                promotionDtlStautsTv.setText(Kits.Date.getYmdhms(dataListBean.getInsertTime()));
                promotionDtlStautsTv.setTextColor(getResources().getColor(R.color.text_tip));
                promotionDtlReceiptnameTv.setText("姓名：");
                if ("ALIPAY".equals(subListBean.getChannelCode())) {
                    promotionDtlAccTv.setText("支付宝账号：");
                    promotionDtlRemarksTv.setText("请使用申请的支付宝账户扫描确认授权");
                    //生成二维码图片
                    if (!AppTools.isEmpty(subListBean.getAuthUrl())) {
                        Bitmap mBitmap = null;
                        mBitmap = CodeUtils.createImage(subListBean.getAuthUrl(), 400, 400, null);
                        promotionDtlIv.setImageBitmap(mBitmap);
                    }
                } else if ("WXPAY".equals(subListBean.getChannelCode())) {
                    promotionDtlAccTv.setText("银行卡账号：");
                    promotionDtlRemarksTv.setText("请使用申请的微信账户扫描确认授权");
                    if (!AppTools.isEmpty(subListBean.getAuthUrl()) && subListBean.getAuthUrl().endsWith(".jpg")) {
                        ILFactory.getLoader().loadImage(subListBean.getAuthUrl().replace(".jpg", ""), promotionDtlIv);
                    } else {
                        //生成二维码图片
                        if (!AppTools.isEmpty(subListBean.getAuthUrl())) {
                            Bitmap mBitmap = null;
                            mBitmap = CodeUtils.createImage(subListBean.getAuthUrl(), 400, 400, null);
                            promotionDtlIv.setImageBitmap(mBitmap);
                        }
                    }
                } else if ("UNIONPAY".equals(subListBean.getChannelCode())) {
                    promotionDtlAccTv.setText("银行卡账号：");
                    promotionDtlRemarksTv.setText("请使用申请的银联账户扫描确认授权");
                    //生成二维码图片
                    if (!AppTools.isEmpty(subListBean.getAuthUrl())) {
                        Bitmap mBitmap = null;
                        mBitmap = CodeUtils.createImage(subListBean.getAuthUrl(), 400, 400, null);
                        promotionDtlIv.setImageBitmap(mBitmap);
                    }
                } else {
                    promotionDtlAccTv.setText("账号：");
                }
                promotionDtlReceiptTv.setText(subListBean.getSettName());
                promotionDtlMailTv.setText(subListBean.getSettBankName());

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
        if (!AppTools.isEmpty(signtype)) {
            title.setText("店铺签约管理");
        } else {
            title.setText(applyDataBean.getShopName());
        }
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

    public void setAdapter(GetMachinApplyInfoDtlResult.DataBean data) {
        if (data != null) {
            switch (data.getStatus()) {
                case 0:
                    promotionDtlStautsTv.setText("审核中");
                    break;
                case 1:
                    promotionDtlStautsTv.setText("待授权");
                    if ("2".equals(type) || "3".equals(type)) {
                        promotionDtlReceiptTv.setText(setAcctno(data.getAliPayId()));
                        promotionDtlMailTv.setText(setAcctno(data.getMailId()));
                    } else if ("1".equals(type)) {
                        promotionDtlReceiptTv.setText(data.getAliPayId());
                        promotionDtlMailTv.setText(data.getMailId());
                    } else {
                        promotionDtlReceiptLl.setVisibility(View.GONE);
                        promotionDtlMailLl.setVisibility(View.GONE);
                    }
                    break;
                case 2:
                    promotionDtlStautsTv.setText("待铺货");
                    break;
                case 3:
                    promotionDtlStautsTv.setText("装机中");
                    break;
                case 4:
                    promotionDtlStautsTv.setText("已完成");
                    break;
                case 5:
                    promotionDtlStautsTv.setText("审核失败");
                    break;
                case 6:
                    promotionDtlStautsTv.setText("装机失败");
                    break;
                default:
            }

            //生成二维码图片
            if (!AppTools.isEmpty(data.getPayAuthRul())) {
                Bitmap mBitmap = null;
                mBitmap = CodeUtils.createImage(data.getPayAuthRul(), 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.login_logo));
                promotionDtlIv.setImageBitmap(mBitmap);
            }
        }

    }

    private String setAcctno(String acctNo) {
        if (!AppTools.isEmpty(acctNo)) {
            acctNo = acctNo.substring(0, 3) + "****" + acctNo.substring(acctNo.length() - 4, acctNo.length());

        }
        return acctNo;
    }


}
