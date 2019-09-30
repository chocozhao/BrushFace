package com.yzf.king.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxj.xpopup.animator.PopupAnimator;
import com.lxj.xpopup.core.CenterPopupView;
import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetMerchInfoResult;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * ClaseName：CustomPopup
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2019/3/20 13:48
 * Modified By：
 * Fixtime：2019/3/20 13:48
 * FixDescription：
 */
public class MerchInfoPopup extends CenterPopupView {
    ImageView popMerchInfoClose;
    TextView popMerchInfoName;
    TextView popMerchInfoIdno;
    TextView popMerchInfoBankname;
    TextView popMerchInfoBankno;

    //自定义弹窗本质是一个自定义View，但是只需重写这个构造，其他的不用重写
    public MerchInfoPopup(@NonNull Context context) {
        super(context);
    }

    // 返回自定义弹窗的布局
    @Override
    protected int getImplLayoutId() {
        return R.layout.pop_merch_info;
    }

    // 执行初始化操作，比如：findView，设置点击，或者任何你弹窗内的业务逻辑
    @Override
    protected void onCreate() {
        super.onCreate();
        GetMerchInfoResult.DataBean dataBean = AppUser.getInstance().getMerchInfoBean();
        if (dataBean != null) {
            popMerchInfoName=findViewById(R.id.pop_merch_info_name);
            popMerchInfoIdno=findViewById(R.id.pop_merch_info_idno);
            popMerchInfoBankname=findViewById(R.id.pop_merch_info_bankname);
            popMerchInfoBankno=findViewById(R.id.pop_merch_info_bankno);
            popMerchInfoName.setText(AppTools.formatName(dataBean.getIdName()));
            popMerchInfoIdno.setText(AppTools.formatCard(dataBean.getIdNo()));
            popMerchInfoBankname.setText(dataBean.getSettleBankName());
            popMerchInfoBankno.setText(AppTools.formatCard(dataBean.getSettleCardNo()));
            findViewById(R.id.pop_merch_info_close).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss(); // 关闭弹窗
                }
            });
        }
    }

    // 设置最大宽度，看需要而定
    @Override
    protected int getMaxWidth() {
        return super.getMaxWidth();
    }

    // 设置最大高度，看需要而定
    @Override
    protected int getMaxHeight() {
        return super.getMaxHeight();
    }

    // 设置自定义动画器，看需要而定
    @Override
    protected PopupAnimator getPopupAnimator() {
        return super.getPopupAnimator();
    }

}
