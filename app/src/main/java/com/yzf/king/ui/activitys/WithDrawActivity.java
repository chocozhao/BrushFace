package com.yzf.king.ui.activitys;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.xw.repo.XEditText;
import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetAcctInfoResult;
import com.yzf.king.present.PWithDraw;
import com.yzf.king.widget.StateButton;
import com.yzf.king.widget.WeChatPswKeyboard.VirtualKeyboardView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

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
 * ClassName：WithDrawActivity
 * Description:提现页面
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/5/8 11:27
 * Modified By：
 * Fixtime：2017/5/8 11:27
 * FixDescription：
 */
public class WithDrawActivity extends XActivity<PWithDraw> {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.withdraw_settle_tv)
    TextView withdrawSettleTv;
    @BindView(R.id.withdraw_amt_et)
    XEditText withdrawAmtEt;
    @BindView(R.id.withdraw_avlamt_tv)
    TextView withdrawAvlamtTv;
    @BindView(R.id.withdraw_confirm_bt)
    StateButton withdrawConfirmBt;
    @BindView(R.id.virtualKeyboardView)
    VirtualKeyboardView virtualKeyboardView;
    @BindView(R.id.withdraw_fee_tv)
    TextView withdrawFeeTv;

    private Animation enterAnim;
    private Animation exitAnim;
    private GridView gridView;
    private ArrayList<Map<String, String>> valueList;
    GetAcctInfoResult.DataBeanX dataBean;
    GetAcctInfoResult.DataBeanX.DataBean data;
    public static final String TYPE = "type";
    String type;
    double amt = 0;

    @Override
    public void initData(Bundle savedInstanceState) {
        dataBean = AppUser.getInstance().getAcctInfoBean();
        type = getIntent().getStringExtra(TYPE);
        initAnim();
        initView();
        valueList = virtualKeyboardView.getValueList();
        if (dataBean != null) {
            setSettleInfo(dataBean);
            for (GetAcctInfoResult.DataBeanX.DataBean bean : dataBean.getData()) {
                if (type.equals(bean.getAcctType())) {
                    data = bean;
                }

            }
            if (data != null) {
                double tmp = Double.parseDouble(data.getAvlbAmt());
                double fee = Double.parseDouble(data.getExternFee());
                tmp = tmp - fee;
                amt = tmp;
                if (amt < 0) {
                    amt = 0.00;
                }
                if (tmp > 0) {
                    withdrawAvlamtTv.setText("当前可提现余额￥" + AppTools.formatLAmt(data.getAvlbAmt()) + "元");
                } else {
                    withdrawAvlamtTv.setText("当前可提现余额￥0");
                }
                withdrawFeeTv.setText("提现服务费" + AppTools.formatLAmt(fee) + "元");

            }
        }
        withdrawAmtEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String orderAmt = withdrawAmtEt.getTextEx();
                double tmp = 0;
                if (!AppTools.isEmpty(orderAmt)) {
                    tmp = Double.parseDouble(orderAmt);
                }
                double fee = tmp * Double.parseDouble(data.getTransFee()) / 10000 + Double.parseDouble(data.getExternFee());
                withdrawFeeTv.setText("提现服务费" + AppTools.formatLAmt(fee) + "元");
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_with_draw;
    }

//    @Override
//    public int getOptionsMenuId() {
//        return R.menu.menu_withdraw;
//    }

    @Override
    public PWithDraw newP() {
        return new PWithDraw();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        //版本判断
        if (Build.VERSION.SDK_INT <= 10) {
            withdrawAmtEt.setInputType(InputType.TYPE_NULL);
        } else {
            this.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus",
                        boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(withdrawAmtEt, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //调用键盘
        virtualKeyboardView.getLayoutBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                virtualKeyboardView.startAnimation(exitAnim);
                virtualKeyboardView.setVisibility(View.GONE);
            }
        });

        gridView = virtualKeyboardView.getGridView();
        gridView.setOnItemClickListener(onItemClickListener);

        withdrawAmtEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                virtualKeyboardView.setFocusable(true);
                virtualKeyboardView.setFocusableInTouchMode(true);
                virtualKeyboardView.startAnimation(enterAnim);
                virtualKeyboardView.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * 数字键盘显示动画
     */
    private void initAnim() {

        enterAnim = AnimationUtils.loadAnimation(this, R.anim.push_bottom_in);
        exitAnim = AnimationUtils.loadAnimation(this, R.anim.push_bottom_out);
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
        title.setText("提现");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 标题栏监听
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                finish();
                break;
            case R.id.menu_withdraw:
                JumpActivity(WithDrawDetailActivity.class, false);
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    public void finishActivity(String msg) {
        showToast(msg);
        finish();
    }

    /**
     * activity跳转
     */
    public void JumpActivity(Class<?> activity, boolean isfinish) {
        getvDelegate().dismissLoading();
        Router.newIntent(context)
                .to(activity)
                .putString(TYPE, type)
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

    @OnClick({R.id.withdraw_confirm_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.withdraw_confirm_bt:
                String orderAmt = withdrawAmtEt.getTextEx();//获取输入的金额，进行判断
                if (AppTools.isEmpty(orderAmt)) {
                    showToast("请输入金额");
                    return;
                }
                if (Double.parseDouble(orderAmt) < 10) {
                    showToast("最低提现金额为10元");
                    return;
                }
                if (Double.parseDouble(orderAmt) > amt) {
                    showToast("最多可提现" + AppTools.formatAmt(amt) + "元");
                    return;
                }
                getvDelegate().showLoading();
//                getP().withDraw(AppUser.getInstance().getMerchId(), orderAmt, type);
                getP().withDraw(AppUser.getInstance().getMerchId(), AppTools.formatY2F(orderAmt), null);
                break;
            default:
        }
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            if (position < 11 && position != 9) {    //点击0~9按钮

                String amount = withdrawAmtEt.getText().toString().trim();
                amount = amount + valueList.get(position).get("name");

                withdrawAmtEt.setText(amount);

                Editable ea = withdrawAmtEt.getText();
                withdrawAmtEt.setSelection(ea.length());
            } else {

                if (position == 9) {      //点击退格键
                    String amount = withdrawAmtEt.getText().toString().trim();
                    if (!amount.contains(".")) {
                        amount = amount + valueList.get(position).get("name");
                        withdrawAmtEt.setText(amount);

                        Editable ea = withdrawAmtEt.getText();
                        withdrawAmtEt.setSelection(ea.length());
                    }
                }

                if (position == 11) {      //点击退格键
                    String amount = withdrawAmtEt.getText().toString().trim();
                    if (amount.length() > 0) {
                        amount = amount.substring(0, amount.length() - 1);
                        withdrawAmtEt.setText(amount);

                        Editable ea = withdrawAmtEt.getText();
                        withdrawAmtEt.setSelection(ea.length());
                    }
                }
            }
        }
    };

    /**
     * 从P层获取到数据
     *
     * @param dataBean
     */
    public void setSettleInfo(GetAcctInfoResult.DataBeanX dataBean) {
        String acctNo = dataBean.getSettCardNo();
        if (acctNo != null && acctNo.length() > 4) {
            withdrawSettleTv.setText(dataBean.getSettBankName() + "  (" + acctNo.substring(acctNo.length() - 4, acctNo.length()) + ")");
        }
    }


}
