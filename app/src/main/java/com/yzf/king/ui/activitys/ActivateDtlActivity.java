package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.model.servicesmodels.GetMyTermResult;
import com.yzf.king.model.servicesmodels.GetTeamTermInfoResult;
import com.yzf.king.model.servicesmodels.GetTransDevicesResult;
import com.yzf.king.model.servicesmodels.GetTransTermInfoResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;

public class ActivateDtlActivity extends XActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activate_dtl_hear_iv)
    ImageView activateDtlHearIv;
    @BindView(R.id.activate_dtl_title_tv)
    TextView activateDtlTitleTv;
    @BindView(R.id.activate_dtl_income_rl)
    RelativeLayout activateDtlIncomeRl;
    @BindView(R.id.activate_dtl_service_bt)
    Button activateDtlServiceBt;
    @BindView(R.id.activate_dtl_package_tv)
    TextView activateDtlPackageTv;
    @BindView(R.id.activate_dtl_date_tv)
    TextView activateDtlDateTv;
    @BindView(R.id.activate_dtl_address_tv)
    TextView activateDtlAddressTv;
    @BindView(R.id.activate_dtl_modelname_tv)
    TextView activateDtlModelnameTv;
    @BindView(R.id.activate_dtl_sn_tv)
    TextView activateDtlSnTv;
    @BindView(R.id.activate_dtl_activate_tv)
    TextView activateDtlActivateTv;
    @BindView(R.id.activate_dtl_term_tv)
    TextView activateDtlTermTv;
    @BindView(R.id.activate_dtl_shopname_tv)
    TextView activateDtlShopnameTv;
    @BindView(R.id.activate_dtl_shopaddress_tv)
    TextView activateDtlShopaddressTv;
    @BindView(R.id.activate_dtl_devicestutas_tv)
    TextView activateDtlDevicestutasTv;
    @BindView(R.id.activate_dtl_income_tv)
    TextView activateDtlIncomeTv;
    @BindView(R.id.activate_dtl_massage_ll)
    LinearLayout activateDtlMassageLl;
    @BindView(R.id.activate_dtl_device_ll)
    LinearLayout activateDtlDeviceLl;
    @BindView(R.id.activate_dtl_income_ll)
    LinearLayout activateDtlIncomeLl;
    @BindView(R.id.activate_dtl_date_ll)
    LinearLayout activateDtlDateLl;
    @BindView(R.id.activate_dtl_devicemessage_tv)
    TextView activateDtlDevicemessageTv;
    @BindView(R.id.activate_dtl_model_tv)
    TextView activateDtlModelTv;
    @BindView(R.id.activate_dtl_sncode_tv)
    TextView activateDtlSncodeTv;
    @BindView(R.id.activate_dtl_activatecode_tv)
    TextView activateDtlActivatecodeTv;
    @BindView(R.id.activate_dtl_termcode_tv)
    TextView activateDtlTermcodeTv;

    GetMyTermResult.DataBean.DataListBean dataListBean;
    GetTeamTermInfoResult.DataBean.DataListBean teamListBean;
    GetTransTermInfoResult.DataBean transTermDataBean;
    GetTransDevicesResult.DataBean transDevicesdataBean;
    String unact;
    String teamunact;
    @Override
    public void initData(Bundle savedInstanceState) {
        dataListBean = (GetMyTermResult.DataBean.DataListBean) getIntent().getSerializableExtra("dataListBean");
        teamListBean = (GetTeamTermInfoResult.DataBean.DataListBean) getIntent().getSerializableExtra("teamListBean");
        transTermDataBean = (GetTransTermInfoResult.DataBean) getIntent().getSerializableExtra("transTermDataBean");
        transDevicesdataBean = (GetTransDevicesResult.DataBean) getIntent().getSerializableExtra("transDevicesdataBean");
        unact = getIntent().getStringExtra("unact");
        teamunact = getIntent().getStringExtra("teamunact");
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_activate_dtl;
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
        //团队设备详情
        if (teamListBean != null) {
            if (AppTools.isEmpty(teamListBean.getLogoUrl())) {
                ILFactory.getLoader().loadImage(teamListBean.getLogoUrl(), activateDtlHearIv);
            } else {
                ILFactory.getLoader().loadImage(R.mipmap.logo, activateDtlHearIv);
            }
            activateDtlPackageTv.setText("套餐："+teamListBean.getMealId());
            activateDtlDateTv.setText(Kits.Date.getYmdhms(teamListBean.getInsertTime()));
            activateDtlAddressTv.setText(teamListBean.getShopAddress());
            activateDtlModelnameTv.setText(teamListBean.getModelName());
            activateDtlSnTv.setText(teamListBean.getTermSn());
            activateDtlActivateTv.setText(teamListBean.getActivCode());
            activateDtlTermTv.setText(teamListBean.getTermId());
            activateDtlTitleTv.setText(teamListBean.getGoodsName());
            title.setText(teamListBean.getGoodsName());
            activateDtlShopnameTv.setText(teamListBean.getShopName());
            activateDtlShopaddressTv.setText(teamListBean.getShopAddress());
            switch (teamListBean.getStatus()) {
                case 2:
                    activateDtlDevicestutasTv.setText("正常");
                    break;
                case 3:
                    activateDtlDevicestutasTv.setText("异常");
                    break;
                case 4:
                    activateDtlDevicestutasTv.setText("激活中");
                    break;
                default:
            }
        }
        if (!AppTools.isEmpty(teamunact)) {
            activateDtlMassageLl.setVisibility(View.GONE);
            activateDtlDeviceLl.setVisibility(View.GONE);
            activateDtlIncomeLl.setVisibility(View.GONE);
        }

        //我的设备详情
        if (dataListBean != null) {
            ILFactory.getLoader().loadImage(dataListBean.getLogoUrl(), activateDtlHearIv);
            activateDtlPackageTv.setText("套餐："+dataListBean.getMealId());
            activateDtlDateTv.setText(Kits.Date.getYmdhms(dataListBean.getUpdateTime()));
            activateDtlAddressTv.setText(dataListBean.getShopAddress());
            activateDtlModelnameTv.setText(dataListBean.getModelName());
            activateDtlSnTv.setText(dataListBean.getTermSn());
            activateDtlActivateTv.setText(dataListBean.getActivCode());
            activateDtlTermTv.setText(dataListBean.getTermId());
            activateDtlTitleTv.setText(dataListBean.getGoodsName());
            title.setText(dataListBean.getGoodsName());
            activateDtlShopnameTv.setText(dataListBean.getShopName());
            activateDtlShopaddressTv.setText(dataListBean.getShopAddress());
            switch (dataListBean.getStatus()) {
                case 2:
                    activateDtlDevicestutasTv.setText("正常");
                    break;
                case 3:
                    activateDtlDevicestutasTv.setText("异常");
                    break;
                case 4:
                    activateDtlDevicestutasTv.setText("激活中");
                    break;
                default:
            }
//            activateDtlIncomeTv.setText(AppTools.formatF2Y(dataListBean.getMealAmt()));
        }
        if (!AppTools.isEmpty(unact)) {
            activateDtlMassageLl.setVisibility(View.GONE);
            activateDtlDeviceLl.setVisibility(View.GONE);
            activateDtlIncomeLl.setVisibility(View.GONE);
        }

        //发货设备信息
        if (transTermDataBean != null) {
            activateDtlDevicemessageTv.setText("物品信息");
            if (transDevicesdataBean.getGoodsId() == 3 || transDevicesdataBean.getGoodsId() == 4) {
                if (!AppTools.isEmpty(transDevicesdataBean.getLogoUrl())) {
                    ILFactory.getLoader().loadImage(transDevicesdataBean.getLogoUrl(), activateDtlHearIv);
                } else {
                    ILFactory.getLoader().loadImage(R.mipmap.logo, activateDtlHearIv);
                }
                activateDtlPackageTv.setText("套餐" + transTermDataBean.getMealId());
                activateDtlDateTv.setText(Kits.Date.getYmdhms(transDevicesdataBean.getInsertTime()));
                activateDtlAddressTv.setText(transDevicesdataBean.getAddress());
                activateDtlModelTv.setText("商品名称：");
                activateDtlSncodeTv.setText("总数：");
                activateDtlActivatecodeTv.setText("未发货：");
                activateDtlModelnameTv.setText(transTermDataBean.getModelName());
                activateDtlSnTv.setText(transTermDataBean.getAdd1());
                activateDtlActivateTv.setText(transTermDataBean.getAdd2());
                title.setText(transTermDataBean.getModelName());
                activateDtlTitleTv.setText(transTermDataBean.getModelName());

                activateDtlTermcodeTv.setVisibility(View.GONE);
                activateDtlMassageLl.setVisibility(View.GONE);
                activateDtlDeviceLl.setVisibility(View.GONE);
                activateDtlIncomeLl.setVisibility(View.GONE);
                activateDtlDateLl.setVisibility(View.GONE);
            } else {
                if (!AppTools.isEmpty(transTermDataBean.getLogoUrl())) {
                    ILFactory.getLoader().loadImage(transTermDataBean.getLogoUrl(), activateDtlHearIv);
                } else {
                    ILFactory.getLoader().loadImage(R.mipmap.logo, activateDtlHearIv);
                }
                activateDtlPackageTv.setText("套餐" + transTermDataBean.getMealId());
                activateDtlDateTv.setText(Kits.Date.getYmdhms(transTermDataBean.getInsertTime()));
                activateDtlAddressTv.setText(transDevicesdataBean.getAddress());
                activateDtlModelnameTv.setText(transTermDataBean.getModelName());
                activateDtlSnTv.setText(transTermDataBean.getTermSn());
                activateDtlActivateTv.setText(transTermDataBean.getActivCode());
                activateDtlTermTv.setText(transTermDataBean.getTermId());
                activateDtlTitleTv.setText(transTermDataBean.getModelName());
                title.setText(transTermDataBean.getModelName());

                activateDtlMassageLl.setVisibility(View.GONE);
                activateDtlDeviceLl.setVisibility(View.GONE);
                activateDtlIncomeLl.setVisibility(View.GONE);
                activateDtlDateLl.setVisibility(View.GONE);
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


    @OnClick({R.id.activate_dtl_income_rl, R.id.activate_dtl_service_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activate_dtl_income_rl:
//                JumpActivity(TransActivity.class);//跳转交易明细
                break;
            case R.id.activate_dtl_service_bt:

                break;
            default:
        }
    }


}
