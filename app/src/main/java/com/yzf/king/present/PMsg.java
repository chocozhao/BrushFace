package com.yzf.king.present;

import com.yzf.king.kit.AppConfig;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.Version;
import com.yzf.king.kit.utils.ResultCode;
import com.yzf.king.model.servicesmodels.BaseResults;
import com.yzf.king.model.servicesmodels.GetPushMsgJGResults;
import com.yzf.king.net.Api;
import com.yzf.king.ui.activitys.MsgActivity;

import cn.droidlover.xdroidmvp.log.Logger;
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
 * ClassName：PMerchTrans
 * Description: 商户交易逻辑
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/3/20 9:18
 * Modified By：
 * Fixtime：2017/3/20 9:18
 * FixDescription：
 */
public class PMsg extends XPresent<MsgActivity> {

    /**
     * 获取推送消息
     *
     * @param
     */
    public void getPushMsg(final int page, int pageNum, String beginTime, String endTime, String merchId, String status,String type) {
        {
            String version = Version.GETPUSHMSG.version();
            String topBranchNo = AppConfig.TOPBRANCHNO;
            Api.getAPPService().getPushMsg(page, pageNum, beginTime, endTime, merchId, status, topBranchNo, version,type)
                    .compose(XApi.<GetPushMsgJGResults>getApiTransformer())
                    .compose(XApi.<GetPushMsgJGResults>getScheduler())
                    .compose(getV().<GetPushMsgJGResults>bindToLifecycle())
                    .subscribe(new ApiSubcriber<GetPushMsgJGResults>() {
                        @Override
                        public void onNext(GetPushMsgJGResults getPushMsgJGResults) {
                            if (getPushMsgJGResults.getCode() == ResultCode.SUCCESS.code()) {
                                getV().setAdapter(getPushMsgJGResults.getData(), page);
                            } else {
                                getV().showErrorView(getPushMsgJGResults.getMessage());
                            }

                        }

                        @Override
                        protected void onFail(NetError error) {
                            getV().showErrorView(error);
                        }

                    });
        }
    }

    public void updateMsg(GetPushMsgJGResults.DataBean item) {
        if (item.getMsgStatus() != null && item.getMsgStatus().equals("0")) {
            String merchId = AppUser.getInstance().getMerchId();
            String id = String.valueOf(item.getId());
            String version = Version.UPDATEPUSHMSG.version();
            Api.getAPPService().updatePushMsg(merchId, id, null,version)
                    .compose(XApi.<BaseResults>getApiTransformer())
                    .compose(XApi.<BaseResults>getScheduler())
                    .compose(getV().<BaseResults>bindToLifecycle())
                    .subscribe(new ApiSubcriber<BaseResults>() {
                        @Override
                        public void onNext(BaseResults baseResults) {
                            if (baseResults.getCode() == ResultCode.SUCCESS.code()) {
                                Logger.i("修改状态成功");
                            }
                        }

                        @Override
                        protected void onFail(NetError error) {
                            getV().showError(error);
                        }

                    });
        }

    }
}
