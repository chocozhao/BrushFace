package com.yzf.king.ui.activitys;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.GetMachinApplyInfoResults;
import com.yzf.king.present.PShopDtl;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;

public class ShopDtlActivity extends XActivity<PShopDtl> implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.shop_dtl_select_tv)
    TextView shopDtlSelectTv;
    @BindView(R.id.shop_dtl_select_rl)
    RelativeLayout shopDtlSelectRl;
    @BindView(R.id.shop_dtl_devicemessage_ll)
    LinearLayout shopDtlDevicemessageLl;
    @BindView(R.id.shop_dtl_define_bt)
    Button shopDtlDefineBt;
    @BindView(R.id.shop_dtl_date_tv)
    TextView shopDtlDateTv;
    @BindView(R.id.shop_dtl_date_rl)
    RelativeLayout shopDtlDateRl;

    public final static int REQUEST_CODE_ACTIVITY = 0X13;

    GetMachinApplyInfoResults.DataBean dataBean;
    @BindView(R.id.shop_dtl_count_tv)
    TextView shopDtlCountTv;
    @BindView(R.id.shop_dtl_shopname_tv)
    TextView shopDtlShopnameTv;
    @BindView(R.id.shop_dtl_applydate_tv)
    TextView shopDtlApplydateTv;
    @BindView(R.id.shop_dtl_address_tv)
    TextView shopDtlAddressTv;
    String date;

    @Override
    public void initData(Bundle savedInstanceState) {
        dataBean = (GetMachinApplyInfoResults.DataBean) getIntent().getSerializableExtra("dataBean");
        initView();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_dtl;
    }

    @Override
    public PShopDtl newP() {
        return new PShopDtl();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        if (dataBean != null) {
            shopDtlApplydateTv.setText(Kits.Date.getYmdhms(dataBean.getInsertTime()));
            shopDtlCountTv.setText(dataBean.getMachinNum() + "台");
            shopDtlShopnameTv.setText(dataBean.getShopName());
            shopDtlAddressTv.setText(dataBean.getAddress());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        shopDtlSelectTv.setText(AppUser.getInstance().getDeviceNum());
        shopDtlSelectTv.setTextColor(getResources().getColor(R.color.text_main));
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
        title.setText("立即铺货");

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


    @OnClick({R.id.shop_dtl_select_rl, R.id.shop_dtl_define_bt, R.id.shop_dtl_date_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shop_dtl_select_rl:
                Router.newIntent(context)
                        .to(AvailableActivity.class)
                        .putString("shopmanage", "shopmanage")
                        .putInt("number", dataBean.getMachinNum())
                        .launch();
//                Intent intent = new Intent(context, AvailableActivity.class);
//                context.startActivityForResult(intent, REQUEST_CODE_ACTIVITY);

                break;
            case R.id.shop_dtl_define_bt:
                String date = shopDtlDateTv.getText().toString();
                String device = shopDtlSelectTv.getText().toString();
                if (AppTools.isEmpty(date)) {
                    showToast("请选择装机时间");
                    return;
                }
                if (AppTools.isEmpty(device)) {
                    showToast("请选择设备");
                    return;
                }
                Router.newIntent(context)
                        .to(ShopFinishActivity.class)
                        .putString("date", date)
                        .putSerializable("dataBean", dataBean)
                        .launch();
                break;
            case R.id.shop_dtl_date_rl:
                Calendar calendarStart = Calendar.getInstance();
                DatePickerDialog datePickerDialogStart = DatePickerDialog.newInstance(
                        this,
                        calendarStart.get(Calendar.YEAR),
                        calendarStart.get(Calendar.MONTH),
                        calendarStart.get(Calendar.DAY_OF_MONTH)
                );
                FragmentManager fragmentManager = context.getFragmentManager();
                datePickerDialogStart.show(fragmentManager, "Datepickerdialog");
                break;
            default:
        }
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        monthOfYear = monthOfYear + 1;
        String month;
        if (monthOfYear < 10) {
            month = "0" + monthOfYear;
        } else {
            month = monthOfYear + "";
        }
        String day;
        if (dayOfMonth < 10) {
            day = "0" + dayOfMonth;
        } else {
            day = dayOfMonth + "";
        }
        date = year + month + day;
        shopDtlDateTv.setText(date);
        shopDtlDateTv.setTextColor(getResources().getColor(R.color.text_main));
        shopDtlDateTv.getText();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        //获取activity传递的数据并显示出来
                        //dosomething;
                        Bundle MarsBuddle = data.getExtras();
                        String title = MarsBuddle.getString("homeTests");
                        shopDtlSelectTv.setText(title);
                        shopDtlSelectTv.setTextColor(getResources().getColor(R.color.text_main));
                    }
                }
                break;
            default:
        }
    }

}
