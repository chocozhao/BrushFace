package com.yzf.king.present;

import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.BaseResults;
import com.yzf.king.model.servicesmodels.GetProcessPlanResults;
import com.yzf.king.net.Api;
import com.yzf.king.ui.fragments.CardStatusFragment;
import com.yzf.king.ui.fragments.ProcessPlanFragment;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import cn.droidlover.xdroidmvp.net.ApiSubcriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

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
 * ClassName：POrderBilling
 * Description: 收款账单逻辑
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/3/20 9:18
 * Modified By：
 * Fixtime：2017/3/20 9:18
 * FixDescription：
 */
public class PCardStatusFragment extends XPresent<CardStatusFragment> {


    /**
     * 获取订单信息
     *
     * @param
     */
    public void GetProcessPlan(final int page, int pageNum, String beginTime, String endTime, String merchId, String type, String status, String acctNo) {
        {
            String version = Version.GETPROCESSPLAN.version();
            Api.getAPPService().getProcessPlan(merchId, type, status, acctNo, page, pageNum, beginTime, endTime, version)
                    .compose(XApi.<GetProcessPlanResults>getApiTransformer())
                    .compose(XApi.<GetProcessPlanResults>getScheduler())
                    .compose(getV().<GetProcessPlanResults>bindToLifecycle())
                    .subscribe(new ApiSubcriber<GetProcessPlanResults>() {
                        @Override
                        public void onNext(GetProcessPlanResults results) {
                            if (results.getCode() == ResultCode.SUCCESS.code()) {
                                getV().setAdapter(results, page);
                            } else {
                                if ("暂无数据".equals(results.getMessage())) {
                                    getV().showEmptyView(results.getMessage());
                                }else {
                                    getV().showErrorView(results.getMessage());
                                }
                            }

                        }

                        @Override
                        protected void onFail(NetError error) {
                            getV().showErrorView(error);
                        }

                    });
        }
    }

    /**
     * 获取注册协议
     */
    public void planOperator(String merchId, String orderId, String confirmType, String type) {
        String version = Version.PLANOPERATOR.version();
        Api.getAPPService().planOperator(merchId, orderId, type, confirmType, version)
                .compose(XApi.<BaseResults>getApiTransformer())
                .compose(XApi.<BaseResults>getScheduler())
                .compose(getV().<BaseResults>bindToLifecycle())
                .subscribe(new ApiSubcriber<BaseResults>() {
                    @Override
                    public void onNext(BaseResults baseResults) {
                        getV().showToast(baseResults.getMessage());
                        if (baseResults.getCode() == ResultCode.SUCCESS.code()) {
                            getV().refresh();
                        }
                    }

                    @Override
                    protected void onFail(NetError error) {
                        getV().showError(error);
                    }

                });
    }


}
