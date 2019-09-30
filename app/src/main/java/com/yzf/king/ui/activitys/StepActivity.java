package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetMposIncomeResult;
import com.yzf.king.present.PStep;
import com.yzf.king.widget.MultipleStatusView;
import com.yzf.king.widget.StateButton;
import com.yzf.king.widget.stepView.StepItemData;
import com.yzf.king.widget.stepView.StepView;

import java.util.ArrayList;
import java.util.List;

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
 * ClassName：StepActivity
 * Description: 进件状态
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2018/12/14 11:52
 * Modified By：
 * Fixtime：2018/12/14 11:52
 * FixDescription：
 */
public class StepActivity extends XActivity<PStep> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.step_view)
    StepView stepView;
    @BindView(R.id.multiplestatusview)
    MultipleStatusView multiplestatusview;

    List<StepItemData> datas = new ArrayList<>();
    @BindView(R.id.step_bt)
    StateButton stepBt;

    int status;
    String amt = null;
    GetMposIncomeResult.DataBean.MposApplyRecordBean dataBean;

    @Override
    public void initData(Bundle savedInstanceState) {
        dataBean = AppUser.getInstance().getMposApplyBean();
        initView();
        if (savedInstanceState == null) {
            multiplestatusview.showLoading();
        }
        getP().getMposFee(AppUser.getInstance().getMerchId());
//        getP().GetMposApplyRecord(AppUser.getInstance().getMerchId());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_step;
    }

    @Override
    public PStep newP() {
        return new PStep();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
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
        title.setText("进件状态");
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
    public void JumpActivity(Class<?> activity, boolean isfinish, GetMposIncomeResult.DataBean.MposApplyRecordBean dataBean) {
        getvDelegate().dismissLoading();
        Router.newIntent(context)
                .putSerializable("dataBean", dataBean)
                .putString("amt", amt)
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


    public void setStep() {
        if (dataBean != null) {
            int begin = 0;
            if (dataBean.getVerifyStatus() == 0 || dataBean.getVerifyStatus() == 3)//审核中
            {
                StepItemData data = new StepItemData();
                data.setMsg("MPOS进件已提交，请等待审核");
                data.setDate("预计一个工作日内可完成审核，请耐心等待");
                datas.add(data);
                data = new StepItemData();
                data.setMsg("等待审核通过");
                datas.add(data);
                data = new StepItemData();
                data.setMsg("购买移动POS机");
                datas.add(data);/*
                data = new StepItemData();
                data.setMsg("移动POS机发货");
                datas.add(data);
                data = new StepItemData();
                data.setMsg("到货签收");
                datas.add(data);*/
                begin = 0;
                status = 0;
            } else if (dataBean.getVerifyStatus() == 1) {//审核通过
                StepItemData data = new StepItemData();
                data.setMsg("MPOS进件已提交，请等待审核");
                data.setDate("预计一个工作日内可完成审核，请耐心等待");
                datas.add(data);
                if (dataBean.getPayStatus() == 0 || dataBean.getPayStatus() == 2) {//未购买
                    data = new StepItemData();
                    data.setMsg("恭喜您进件审核成功");
                    data.setDate("您已成功进件，请点击下方按钮购买POS机");
                    datas.add(data);
                    data = new StepItemData();
                    data.setMsg("购买移动POS机");
                    data.setDate("订单金额：￥" + amt + " \n订单状态：未付款");
                    datas.add(data);
                    /*data = new StepItemData();
                    data.setMsg("移动POS机发货");
                    datas.add(data);
                    data = new StepItemData();
                    data.setMsg("到货签收");
                    datas.add(data);*/
                    begin = 1;
                    status = 1;
                    stepBt.setVisibility(View.VISIBLE);
                    stepBt.setText("购买POS机");
                } else if (dataBean.getPayStatus() == 3) {//支付处理中
                    data = new StepItemData();
                    data.setMsg("恭喜您进件审核成功");
                    data.setDate("您已成功进件，请点击方按钮购买POS机");
                    datas.add(data);
                    data = new StepItemData();
                    data.setMsg("购买移动POS机");
                    data.setDate("订单金额：￥" + amt + " \n订单状态：支付处理中");
                    datas.add(data);
                   /* data = new StepItemData();
                    data.setMsg("移动POS机发货");
                    datas.add(data);
                    data = new StepItemData();
                    data.setMsg("到货签收");
                    datas.add(data);*/
                    begin = 2;
                    /*status = 1;
                    stepBt.setVisibility(View.VISIBLE);
                    stepBt.setText("购买POS机");*/

                } else if (dataBean.getPayStatus() == 1) {//已购买
                    data = new StepItemData();
                    data.setMsg("恭喜您进件审核成功");
                    data.setDate("您已成功进件");
                    datas.add(data);
                    data = new StepItemData();
                    data.setMsg("购买移动POS机");
                    data.setDate("订单金额：￥" + amt + " \n订单状态：已付款");
                    datas.add(data);
                    begin = 2;
                    status = 5;
                    stepBt.setVisibility(View.VISIBLE);
                    stepBt.setText("完成");
//                    JumpActivity(PosPayActivity.class, true);
                    /*if (dataBean.getShipStatus() == 0) {//未发货
                        data = new StepItemData();
                        data.setMsg("您的POS机正在发货中");
                        data.setDate("快递状态：待发货 ");
                        datas.add(data);
                        data = new StepItemData();
                        data.setMsg("到货签收");
                        datas.add(data);
                        begin = 2;
                        *//*if (AppTools.isEmpty(dataBean.getAddressDtl())) {
                            stepBt.setVisibility(View.VISIBLE);
                            stepBt.setText("新增收货地址");
                            status = 2;
                        } else {
                            stepBt.setVisibility(View.VISIBLE);
                            stepBt.setText("修改收货地址");
                            status = 3;
                        }*//*

                    } else {//已发货
                        data = new StepItemData();
                        data.setMsg("您的POS机正在发货中");
                        data.setDate("快递状态：已发货 \n快递单号:" + dataBean.getShipOrderId());
                        datas.add(data);
                        if (dataBean.getReceiptStatus() == 0) {//未收货
                            data = new StepItemData();
                            data.setMsg("到货签收");
                            data.setDate("如果您已收货，请点击确认收货");
                            datas.add(data);
                            begin = 3;
                            status = 4;
                            stepBt.setVisibility(View.VISIBLE);
                            stepBt.setText("确认收货");
                        } else if (dataBean.getReceiptStatus() == 1) {//已收货
                            data = new StepItemData();
                            data.setMsg("到货签收");
                            data.setDate("已签收");
                            datas.add(data);
                            begin = 4;
                            status = 5;
                            stepBt.setVisibility(View.VISIBLE);
                            stepBt.setText("完成");
                            JumpActivity(PosPayActivity.class, true);
                        }
                    }*/
                }
            }
            multiplestatusview.showContent();
            stepView.init(begin);
            stepView.setDatas(datas);
            stepView.setBindViewListener(new StepView.BindViewListener() {
                @Override
                public void onBindView(TextView itemMsg, TextView itemDate, Object data) {
                    StepItemData sid = (StepItemData) data;
                    itemMsg.setText(sid.getMsg());
                    itemDate.setText(sid.getDate());
                }
            });
        } else {
            multiplestatusview.showError("返回数据有误");
        }

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


    @OnClick(R.id.step_bt)
    public void onViewClicked() {
        if (status == 1)//购买pos
        {
//            JumpActivity(BuyPosActivity.class, false, dataBean);
        }
        if (status == 2)//新增收货地址
        {
//            JumpActivity(AddressActivity.class, false);

        }
        if (status == 3)//修改收货地址
        {
//            JumpActivity(AddressActivity.class, false);
        }
        if (status == 4)//确认收货
        {
            showNoticeDialog("是否确认收货？", new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    if (which == DialogAction.POSITIVE) {
                        getvDelegate().showLoading();
                        getP().receipt(AppUser.getInstance().getMerchId());
                    }
                }
            });
        }
        if (status == 5)//已完成

        {
//            JumpActivity(PosPayActivity.class, true);
        }
    }

    public void toPay() {
        getvDelegate().dismissLoading();
//        JumpActivity(PosPayActivity.class, true);
    }

    public void setFee(String externFee) {
        amt = AppTools.formatAmt(String.valueOf(externFee));
    }
}
