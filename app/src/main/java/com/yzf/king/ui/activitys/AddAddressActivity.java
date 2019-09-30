package com.yzf.king.ui.activitys;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.xw.repo.XEditText;
import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.model.servicesmodels.AddressResult;
import com.yzf.king.present.PAddAddress;

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
 * ClassName：AddAddressActivity
 * Description:
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2018/12/7 14:18
 * Modified By：
 * Fixtime：2018/12/7 14:18
 * FixDescription：
 */
public class AddAddressActivity extends XActivity<PAddAddress> {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.addaddress_name_tv)
    XEditText nameTv;
    @BindView(R.id.addaddress_pnone_tv)
    XEditText pnoneTv;
    @BindView(R.id.addaddress_address_tv)
    TextView addressTv;
    @BindView(R.id.addaddress_daaressdtl_tv)
    XEditText addressdtlTv;

    CityPickerView mPicker;
    AddressResult.DataBean dataBean;
    String type = "0";


    @Override
    public void initData(Bundle savedInstanceState) {
        dataBean = (AddressResult.DataBean) getIntent().getSerializableExtra("dataBean");
        String tempType = getIntent().getStringExtra("type");
        if (!AppTools.isEmpty(tempType)) {
            type = tempType;
        }
        initView();
        mPicker = new CityPickerView();
        mPicker.init(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_address;
    }

    @Override
    public PAddAddress newP() {
        return new PAddAddress();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        if (dataBean != null) {
            nameTv.setText(dataBean.getName());
            pnoneTv.setText(dataBean.getPhone());
            addressTv.setText(dataBean.getAddress());
            addressdtlTv.setText(dataBean.getAddressDtl());
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
        if ("0".equals(type)) {
            title.setText("添加收货地址");
        } else if ("1".equals(type)) {
            title.setText("修改收货地址");
        }
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
            case R.id.menu_confirm:
                if (AppTools.isEmpty(nameTv.getTextEx())) {
                    showToast("请输入姓名");
                    break;
                }
                if (AppTools.isEmpty(pnoneTv.getTextEx())) {
                    showToast("请输入手机号码");
                    break;
                }
                if (AppTools.isEmpty(addressTv.getText().toString())) {
                    showToast("请选择地区");
                    break;
                }
                if (AppTools.isEmpty(addressdtlTv.getText().toString())) {
                    showToast("请输入详细地址");
                    break;
                }
                getvDelegate().showLoading();
                String id = null;
                if (dataBean != null) {
                    id = String.valueOf(dataBean.getId());
                }
                getP().addressOpera(AppUser.getInstance().getMerchId(), type, "0", nameTv.getTextEx(), pnoneTv.getTextEx(), addressTv.getText().toString(), addressdtlTv.getTextEx(), null, id);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getOptionsMenuId() {
        return R.menu.menu_confirm;
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

    public void finish(String msg) {
        showToast(msg);
        finish();
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


    @OnClick(R.id.addaddress_address_tv)
    public void onViewClicked() {
//设置自定义的属性配置
        CityConfig cityConfig = new CityConfig.Builder().build();
        cityConfig.setDefaultProvinceName("广东省");
        cityConfig.setDefaultCityName("广州市");
        cityConfig.setDefaultDistrict("番禺区");
        mPicker.setConfig(cityConfig);

//监听选择点击事件及返回结果
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                addressTv.setText(province.getName() + city.getName() + district.getName());

                //省份province
                //城市city
                //地区district
            }

            @Override
            public void onCancel() {
//                showToast( "已取消");
            }
        });

        //显示
        mPicker.showCityPicker();

    }
}
