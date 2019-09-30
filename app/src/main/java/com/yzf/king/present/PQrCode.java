package com.yzf.king.present;

import com.yzf.king.ui.activitys.QrCodeActivity;

import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * ClaseName：PQrCode
 * Description：二维码逻辑
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/3/20 10:34
 * Modified By：
 * Fixtime：2017/3/20 10:34
 * FixDescription：
 */

public class PQrCode extends XPresent<QrCodeActivity> {

    /**
     * 获取订单信息
     *
     * @param serviceId
     */
    public void GetOrderList(final int page, int pageNum, String beginTime, String endTime, String merchId, String serviceId, String result, String orderId, String notServiceId) {
        {
           /* String version = Version.QUERYTRANS.version();
            Api.getAPPService().queryTrans(page, pageNum, beginTime, endTime, merchId, serviceId, result, orderId, notServiceId, version)
                    .compose(XApi.<GetOrderListResult>getApiTransformer())
                    .compose(XApi.<GetOrderListResult>getScheduler())
                    .compose(getV().<GetOrderListResult>bindToLifecycle())
                    .subscribe(new ApiSubcriber<GetOrderListResult>() {
                        @Override
                        public void onNext(GetOrderListResult getOrderListResult) {
                            if (getOrderListResult.getCode() == ResultCode.SUCCESS.code()) {
                                GetOrderListResult.DataBean.ListBean dataBean = getOrderListResult.getData().getList().get(0);
                                if (!dataBean.getProcStatus().equals("00")) {
                                    if (dataBean.getProcStatus().equals("01")) {
                                        IBus.IEvent iEvent = new IEvent();
                                        iEvent.setId("refresh_money");
                                        BusProvider.getBus().post(iEvent);
                                        getV().JumpActivity(TransDetailActivity.class, true, dataBean);
                                    } else {
                                        getV().showErrorDialog(dataBean.getRespDesc());
                                    }

                                } else {
                                    getV().showToast(dataBean.getRespDesc());
                                }
                            } else {
                                getV().showErrorDialog(getOrderListResult.getMessage());
                            }
                        }

                        @Override
                        protected void onFail(NetError error) {
                            getV().showError(error);
                        }

                    });*/
        }
    }


    /**
     *
     */
    public void noCardPay(String BUS_CODE) {
       /* final String ORDER_AMT = AppTools.formatAmt(AppUser.getInstance().getAMT());
        String ORDER_ID = AppTools.craeateOrderId();
        String version = Version.NOCARDPAY.version();
        Api.getAPPService().noCardPay(ORDER_ID, ORDER_AMT, AppUser.getInstance().getUserId(), BUS_CODE, ORDER_ID, ORDER_ID, "1", "", version)
                .compose(XApi.<BaseResults>getApiTransformer())
                .compose(XApi.<BaseResults>getScheduler())
                .compose(getV().<BaseResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<BaseResults>() {
                    @Override
                    public void onNext(BaseResults baseResults) {
                        if (baseResults.getCode() == ResultCode.SUCCESS.code()) {
                            if (baseResults.getData() != null) {
                                getV().setQrCode(baseResults.getData().toString());
                            } else {
                                getV().showToast("获取数据失败");
                            }

                        } else {
                            getV().showToast(baseResults.getMessage());
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                });*/
    }

}
