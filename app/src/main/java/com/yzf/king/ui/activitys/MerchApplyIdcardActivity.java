package com.yzf.king.ui.activitys;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.style.cityjd.JDCityConfig;
import com.lljjcoder.style.cityjd.JDCityPicker;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.xw.repo.XEditText;
import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.utils.GetImagePath;
import com.yzf.king.kit.utils.PermissionPageUtils;
import com.yzf.king.kit.utils.fileUtill;
import com.yzf.king.model.ChooseItem;
import com.yzf.king.model.servicesmodels.GetUploadFileIdResult;
import com.yzf.king.present.PMerchApplyIdcard;
import com.yzf.king.widget.BottomDialog;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.log.Logger;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import rx.functions.Action1;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class MerchApplyIdcardActivity extends XActivity<PMerchApplyIdcard> {


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.merch_apply_bankcard_number)
    TextView merchApplyBankcardNumber;
    @BindView(R.id.merch_apply_bankcard_number_tv)
    XEditText merchApplyBankcardNumberTv;
    @BindView(R.id.merch_apply_bankcard_name)
    TextView merchApplyBankcardName;
    @BindView(R.id.merch_apply_bankcard_name_et)
    XEditText merchApplyBankcardNameEt;
    @BindView(R.id.merch_apply_bankcard_row)
    TextView merchApplyBankcardRow;
    @BindView(R.id.merch_apply_bankcard_row_tv)
    XEditText merchApplyBankcardRowTv;
    @BindView(R.id.merch_apply_idcard_zm_iv)
    ImageView merchApplyIdcardZmIv;
    @BindView(R.id.merch_apply_idcard_fm_iv)
    ImageView merchApplyIdcardFmIv;
    @BindView(R.id.merch_apply_idcard_tv)
    TextView merchApplyIdcardTv;
    @BindView(R.id.merch_apply_idcard_name)
    TextView merchApplyIdcardName;
    @BindView(R.id.merch_apply_idcard_name_tv)
    TextView merchApplyIdcardNameTv;
    @BindView(R.id.merch_apply_idcard_number)
    TextView merchApplyIdcardNumber;
    @BindView(R.id.merch_apply_idcard_number_tv)
    TextView merchApplyIdcardNumberTv;
    @BindView(R.id.merch_apply_idcard_validity)
    TextView merchApplyIdcardValidity;
    @BindView(R.id.merch_apply_idcard_wx_define_bt)
    Button merchApplyIdcardWxDefineBt;
    @BindView(R.id.merch_apply_idcard_begin_tv)
    TextView merchApplyIdcardBeginTv;
    @BindView(R.id.merch_apply_idcard_end_tv)
    TextView merchApplyIdcardEndTv;
    @BindView(R.id.merch_apply_zfb_account_tv)
    XEditText merchApplyZfbAccountTv;
    @BindView(R.id.merch_apply_idcard_progressbar_iv)
    ImageView merchApplyIdcardProgressbarIv;
    @BindView(R.id.merch_apply_idcard_wx_ns)
    NestedScrollView merchApplyIdcardWxNs;
    @BindView(R.id.merch_apply_bankcard_phone_tv)
    XEditText merchApplyBankcardPhoneTv;
    @BindView(R.id.merch_apply_bankcard_phone)
    TextView merchApplyBankcardPhone;
    @BindView(R.id.merch_apply_zfb_account_rl)
    RelativeLayout merchApplyZfbAccountRl;
    @BindView(R.id.merch_apply_idcard_zfb_auth_iv)
    ImageView merchApplyIdcardZfbAuthIv;
    @BindView(R.id.merch_apply_idcard_ll)
    LinearLayout merchApplyIdcardLl;
    @BindView(R.id.merch_apply_idcard_name_rl)
    RelativeLayout merchApplyIdcardNameRl;
    @BindView(R.id.merch_apply_idcard_number_rl)
    RelativeLayout merchApplyIdcardNumberRl;
    @BindView(R.id.merch_apply_idcard_validity_rl)
    RelativeLayout merchApplyIdcardValidityRl;
    @BindView(R.id.merch_apply_idcard_prompt)
    TextView merchApplyIdcardPrompt;
    @BindView(R.id.merch_apply_idcard_prompts)
    TextView merchApplyIdcardPrompts;
    @BindView(R.id.merch_apply_bank_message_tv)
    TextView merchApplyBankMessageTv;
    @BindView(R.id.merch_apply_bankcard_region_tv)
    TextView merchApplyBankcardRegionTv;
    @BindView(R.id.merch_apply_bankcard_region_rl)
    RelativeLayout merchApplyBankcardRegionRl;
    @BindView(R.id.merch_apply_bankcard_bank_tv)
    TextView merchApplyBankcardBankTv;
    @BindView(R.id.merch_apply_bankcard_bank_rl)
    RelativeLayout merchApplyBankcardBankRl;
    @BindView(R.id.merch_apply_idcard_verified_ll)
    LinearLayout merchApplyIdcardVerifiedLl;


    private String[] imgPath = new String[]{null};
    private String[] imgFlag = new String[]{null};

    private int mCount;
    private String mMobile;
    private String mFilePath;
    private String rootPath;
    private String mFilename;
    private static final int IDCARDFRONTZM = 0;
    private static final int IDCARDFRONTFM = 1;
    private static final int IDCARDFRONTAUTH = 2;
    private GetUploadFileIdResult.DataBean dataBeanZM = new GetUploadFileIdResult.DataBean();
    private GetUploadFileIdResult.DataBean dataBeanFM = new GetUploadFileIdResult.DataBean();
    private GetUploadFileIdResult.DataBean dataBeanAuth = new GetUploadFileIdResult.DataBean();
    private GetUploadFileIdResult.DataBean.IdBeanBean idBeanBean = new GetUploadFileIdResult.DataBean.IdBeanBean();
    private String idvalidity;
    private String applyType;
    private String shopId;
    private String addShopId;
    private String bindType;
    private String legalName;
    private String provinceName;
    private String cityName;
    private String districtName;
    private String provinceCode;
    private String cityCode;
    private String districtCode;
    private String BankcardBank;
    private String shopName;
    private String[] itemList = new String[]{null};

    @Override
    public void initData(Bundle savedInstanceState) {
        applyType = getIntent().getStringExtra("applyType");
        shopId = getIntent().getStringExtra("shopId");
        addShopId = getIntent().getStringExtra("addShopId");
        bindType = getIntent().getStringExtra("bindType");
        legalName = getIntent().getStringExtra("legalName");
        shopName = getIntent().getStringExtra("shopName");
        mMobile = AppUser.getInstance().getPhone();
        initView();
        initListData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_merch_apply_idcard;
    }

    @Override
    public PMerchApplyIdcard newP() {
        return new PMerchApplyIdcard();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        initEditText();
        //applyType 1：支付宝    applyType 2：微信
        if ("1".equals(applyType)) {
            merchApplyIdcardProgressbarIv.setImageDrawable(getResources().getDrawable(R.mipmap.merch_apply_idcard_zfb_progressbar_iv));
            title.setText("支付宝商家申请");
            merchApplyBankMessageTv.setText("（支付宝商家作结算使用）");
            merchApplyIdcardProgressbarIv.setVisibility(View.VISIBLE);

            merchApplyZfbAccountRl.setVisibility(View.VISIBLE);
        } else if ("2".equals(applyType)) {
            merchApplyIdcardProgressbarIv.setImageDrawable(getResources().getDrawable(R.mipmap.merch_apply_idcard_wx_progressbar_iv));
            title.setText("微信商家申请");
            merchApplyBankMessageTv.setText("（微信商家作结算使用）");
            merchApplyIdcardProgressbarIv.setVisibility(View.VISIBLE);
            merchApplyIdcardPrompt.setVisibility(View.VISIBLE);
            merchApplyIdcardLl.setVisibility(View.VISIBLE);

        } else {
            merchApplyIdcardProgressbarIv.setImageDrawable(getResources().getDrawable(R.mipmap.merch_apply_idcard_zfb_progressbar_iv));
            title.setText("支付宝商家申请");
            merchApplyIdcardProgressbarIv.setVisibility(View.VISIBLE);
            merchApplyIdcardPrompt.setVisibility(View.VISIBLE);
            merchApplyIdcardLl.setVisibility(View.VISIBLE);

        }


    }

    private void initEditText() {
        //监听改变方式
        merchApplyBankcardNameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



            }

            @Override
            public void afterTextChanged(Editable s) {
//                if ("1".equals(applyType)) {
                    if (legalName.equals(s.toString()) || shopName.equals(s.toString())) {
                        merchApplyIdcardPrompt.setVisibility(View.GONE);
                        merchApplyIdcardPrompts.setVisibility(View.GONE);
                        merchApplyIdcardVerifiedLl.setVisibility(View.GONE);
                        merchApplyIdcardLl.setVisibility(View.GONE);
                    } else {
                        merchApplyIdcardPrompt.setVisibility(View.VISIBLE);
                        merchApplyIdcardPrompts.setVisibility(View.VISIBLE);
                        merchApplyIdcardVerifiedLl.setVisibility(View.VISIBLE);
                        merchApplyIdcardLl.setVisibility(View.VISIBLE);
                    }
                    merchApplyIdcardPrompt.setText("请上传"+s+"的身份证和授权信息");
//                }
            }
        });
    }

    /**
     * 初始化Toolbar
     */
    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_black_24dp);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void initListData() {
        itemList = new String[]{
                "工商银行",
                "交通银行",
                "招商银行",
                "民生银行",
                "中信银行",
                "浦发银行",
                "兴业银行",
                "光大银行",
                "广发银行",
                "平安银行",
                "北京银行",
                "华夏银行",
                "农业银行",
                "建设银行",
                "邮政储蓄银行",
                "中国银行",
                "宁波银行"};
    }

    @Override
    protected void onResume() {
        super.onResume();
        rootPath = context.getExternalFilesDir("") + File.separator + "Pictures/";
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

    /**
     * 显示双按钮对话框
     *
     * @param msg
     * @param callback
     */
    public void showNoticeDialog(String msg, MaterialDialog.SingleButtonCallback callback) {
        getvDelegate().showNoticeDialog(msg, callback);
    }

    @OnClick({R.id.merch_apply_idcard_zm_iv, R.id.merch_apply_idcard_fm_iv, R.id.merch_apply_idcard_zfb_auth_iv,
            R.id.merch_apply_idcard_wx_define_bt, R.id.merch_apply_bankcard_region_rl, R.id.merch_apply_bankcard_bank_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.merch_apply_idcard_zm_iv:
                mCount = IDCARDFRONTZM;
                choosePicture(mMobile + "_IDCARDFRONTZM");
                break;
            case R.id.merch_apply_idcard_fm_iv:
                mCount = IDCARDFRONTFM;
                choosePicture(mMobile + "_IDCARDFRONTFM");
                break;
            case R.id.merch_apply_idcard_zfb_auth_iv:
                mCount = IDCARDFRONTAUTH;
                choosePicture(mMobile + "_IDCARDFRONTAUTH");
                break;
            case R.id.merch_apply_bankcard_region_rl:
                JDCityPicker cityPicker = new JDCityPicker();
                JDCityConfig jdCityConfig = new JDCityConfig.Builder().build();

                jdCityConfig.setShowType(JDCityConfig.ShowType.PRO_CITY_DIS);
                cityPicker.init(this);
                cityPicker.setConfig(jdCityConfig);
                cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        merchApplyBankcardRegionTv.setText(province.getName() + city.getName() + district.getName());
                        //省市区名称
                        provinceName = province.getName();
                        if (city.getName().contains("省直辖县级行政单位")) {
                            cityName = city.getName().replace("省直辖县级行政单位", province.getName());
                        } else {
                            cityName = city.getName();
                        }
                        districtName = district.getName();
                        //省市区号码
                        provinceCode = province.getId();
                        cityCode = city.getId();
                        districtCode = district.getId();
                    }

                    @Override
                    public void onCancel() {
                    }
                });
                cityPicker.showCityPicker();
                break;
            case R.id.merch_apply_bankcard_bank_rl:
                new XPopup.Builder(context)
//                        .enableDrag(false)
                        .asBottomList("请选择", itemList,
                                new OnSelectListener() {
                                    @Override
                                    public void onSelect(int position, String text) {
                                        merchApplyBankcardBankTv.setText(text);
                                        BankcardBank = text;
                                    }
                                })
                        .show();
                break;
            case R.id.merch_apply_idcard_wx_define_bt:
                boolean flag = false;
                if (legalName.equals(merchApplyBankcardNameEt.getText().toString()) || shopName.equals(merchApplyBankcardNameEt.getText().toString())) {}else {
                    for (int i = 0; i < imgFlag.length; i++) {
                        String temp = imgFlag[i];
                        if (AppTools.isEmpty(temp) || !temp.equals("00")) {
                            flag = true;
                            break;
                        }
                    }
                }
                if (flag) {
                    showToast("请提交上传的图片");
                } else {
                    if (AppTools.isEmpty(merchApplyZfbAccountTv.getText().toString()) && "1".equals(applyType)) {
                        showToast("请输入支付宝账号");
                        return;
                    }
                    if (legalName.equals(merchApplyBankcardNameEt.getTextEx()) || shopName.equals(merchApplyBankcardNameEt.getTextEx())) {}else {
                        if (AppTools.isEmpty(dataBeanAuth.getImgPath())) {
                            showToast("请上传授权书");
                            return;
                        }
                        if (AppTools.isEmpty(merchApplyIdcardNameTv.getText().toString())) {
                            showToast("请上传身份证姓名");
                            return;
                        }
                        if (AppTools.isEmpty(merchApplyIdcardNumberTv.getText().toString())) {
                            showToast("请上传身份证号");
                            return;
                        }
                        if (AppTools.isEmpty(merchApplyIdcardBeginTv.getText().toString())) {
                            showToast("请上传身份证有效期");
                            return;
                        }
                        if (!merchApplyIdcardNameTv.getText().toString().equals(merchApplyBankcardNameEt.getTextEx())) {
                            showToast("请保证开户名和身份证姓名一致");
                            return;
                        }
                    }
                    if (AppTools.isEmpty(merchApplyBankcardNumberTv.getTextEx())) {
                        showToast("请输入银行卡号");
                        return;
                    }
                    if (AppTools.isEmpty(merchApplyBankcardNameEt.getText().toString())) {
                        showToast("请输入开户名");
                        return;
                    }
                    if (AppTools.isEmpty(merchApplyBankcardRowTv.getTextEx())) {
                        showToast("请输入开户支行");
                        return;
                    }
                    if (AppTools.isEmpty(merchApplyBankcardRegionTv.getText().toString())) {
                        showToast("请输入开户地区");
                        return;
                    }
                    if (AppTools.isEmpty(merchApplyBankcardBankTv.getText().toString())) {
                        showToast("请输入开户银行");
                        return;
                    }
                    if (AppTools.isEmpty(merchApplyBankcardPhoneTv.getTextEx())) {
                        showToast("请输入预留手机号");
                        return;
                    }
                    if (!AppTools.isMobile(merchApplyBankcardPhoneTv.getTextEx())) {
                        showToast("输入预留手机号格式不正确");
                        return;
                    }
                    if (merchApplyBankcardNumberTv.getTextEx().length() < 12) {
                        showToast("银行卡号格式不正确");
                        return;
                    }


                    String settBankName = BankcardBank + merchApplyBankcardRowTv.getTextEx();
                    //bindType为1时材料驳回
                    if ("1".equals(bindType)) {
                        //applyType 1：支付宝    applyType 2：微信
                        if ("1".equals(applyType)) {
                            getP().addApplyInfo(AppUser.getInstance().getMerchId(), addShopId, "0", "2",
                                    dataBeanZM.getImgPath(), dataBeanFM.getImgPath(), merchApplyIdcardNumberTv.getText().toString(), merchApplyIdcardNameTv.getText().toString(),
                                    idvalidity, settBankName, null, merchApplyBankcardNameEt.getText().toString(), merchApplyBankcardNumberTv.getTextEx(),
                                    merchApplyBankcardPhoneTv.getTextEx(), merchApplyZfbAccountTv.getTextEx(), null, null, null, null,
                                    null, dataBeanAuth.getImgPath(), null, bindType, null, null, null, null, null,
                                    null, provinceName, cityName, provinceCode, cityCode, districtName,
                                    null, null, null, districtCode, null);
                        } else if ("2".equals(applyType)) {
                            getP().addApplyInfo(AppUser.getInstance().getMerchId(), addShopId, "1", "0",
                                    dataBeanZM.getImgPath(), dataBeanFM.getImgPath(), merchApplyIdcardNumberTv.getText().toString(),
                                    merchApplyIdcardNameTv.getText().toString(), idvalidity, settBankName,
                                    null, merchApplyBankcardNameEt.getText().toString(), merchApplyBankcardNumberTv.getTextEx(),
                                    merchApplyBankcardPhoneTv.getTextEx(), null, null, null, null,
                                    null, null, null, null, bindType, null, null, null,
                                    null, null, null, provinceName, cityName, provinceCode, cityCode, districtName,
                                    null, null, null, districtCode, null);
                        } else {
                            getP().addApplyInfo(AppUser.getInstance().getMerchId(), addShopId, "3", "0",
                                    dataBeanZM.getImgPath(), dataBeanFM.getImgPath(), merchApplyIdcardNumberTv.getText().toString(),
                                    merchApplyIdcardNameTv.getText().toString(), idvalidity, settBankName,
                                    null, merchApplyBankcardNameEt.getText().toString(), merchApplyBankcardNumberTv.getTextEx(),
                                    merchApplyBankcardPhoneTv.getTextEx(), null, null, null, null,
                                    null, null, null, null, bindType, null, null, null,
                                    null, null, null, provinceName, cityName, provinceCode, cityCode, districtName,
                                    null, null, null, districtCode, null);
                        }
                    } else {
                        if ("1".equals(applyType)) {
                            getP().addApplyInfo(AppUser.getInstance().getMerchId(), shopId, "0", "2",
                                    dataBeanZM.getImgPath(), dataBeanFM.getImgPath(), merchApplyIdcardNumberTv.getText().toString(), merchApplyIdcardNameTv.getText().toString(),
                                    idvalidity, settBankName, null, merchApplyBankcardNameEt.getText().toString(), merchApplyBankcardNumberTv.getTextEx(),
                                    merchApplyBankcardPhoneTv.getTextEx(), merchApplyZfbAccountTv.getTextEx(), null, null, null, null,
                                    null, dataBeanAuth.getImgPath(), null, "0", null, null, null, null,
                                    null, null, provinceName, cityName, provinceCode, cityCode, districtName,
                                    null, null, null, districtCode, null);
                        } else if ("2".equals(applyType)) {
                            getP().addApplyInfo(AppUser.getInstance().getMerchId(), shopId, "1", "0",
                                    dataBeanZM.getImgPath(), dataBeanFM.getImgPath(), merchApplyIdcardNumberTv.getText().toString(),
                                    merchApplyIdcardNameTv.getText().toString(), idvalidity, settBankName,
                                    null, merchApplyBankcardNameEt.getText().toString(), merchApplyBankcardNumberTv.getTextEx(),
                                    merchApplyBankcardPhoneTv.getTextEx(), null, null, null, null,
                                    null, null, null, null, "0", null, null, null,
                                    null, null, null, provinceName, cityName, provinceCode, cityCode, districtName,
                                    null, null, null, districtCode, null);
                        } else {
                            getP().addApplyInfo(AppUser.getInstance().getMerchId(), shopId, "3", "0",
                                    dataBeanZM.getImgPath(), dataBeanFM.getImgPath(), merchApplyIdcardNumberTv.getText().toString(),
                                    merchApplyIdcardNameTv.getText().toString(), idvalidity, settBankName,
                                    null, merchApplyBankcardNameEt.getText().toString(), merchApplyBankcardNumberTv.getTextEx(),
                                    merchApplyBankcardPhoneTv.getTextEx(), null, null, null, null,
                                    null, null, null, null, "0", null, null, null,
                                    null, null, null, provinceName, cityName, provinceCode, cityCode, districtName,
                                    null, null, null, districtCode, null);
                        }
                    }

                }
                break;
            default:
                break;
        }
    }

    /**
     * 获取身份证正面
     *
     * @param idBean
     */
    public void setIdCardInfoZM(GetUploadFileIdResult.DataBean idBean) {
        merchApplyIdcardNameTv.setText(idBean.getIdBean().getName().trim().replace(" ", ""));
        merchApplyIdcardNumberTv.setText(idBean.getIdBean().getIdNo().trim().replace(" ", ""));

        dataBeanZM.setImgPath(idBean.getImgPath());

    }

    /**
     * 获取身份证反面
     *
     * @param idBean
     */
    public void setIdCardInfoFM(GetUploadFileIdResult.DataBean idBean) {
        dataBeanFM.setImgPath(idBean.getImgPath());

        merchApplyIdcardBeginTv.setText(Kits.Date.formatTime(idBean.getIdBean().getEffectDate(), "yyyymmdd", "yyyy-mm-dd"));
        if (!"长期".equals(idBean.getIdBean().getInvalidDate())) {
            merchApplyIdcardEndTv.setText("—" + Kits.Date.formatTime(idBean.getIdBean().getInvalidDate(), "yyyymmdd", "yyyy-mm-dd"));
        } else {
            merchApplyIdcardEndTv.setVisibility(View.GONE);
        }

        idBeanBean.setEffectDate(idBean.getIdBean().getEffectDate());
        idBeanBean.setInvalidDate(idBean.getIdBean().getInvalidDate());


        idvalidity = Kits.Date.formatTime(idBean.getIdBean().getEffectDate(), "yyyymmdd", "yyyy-mm-dd") + ","
                + Kits.Date.formatTime(idBean.getIdBean().getInvalidDate(), "yyyymmdd", "yyyy-mm-dd");
    }

    /**
     * 获取授权书
     *
     * @param idBean
     */
    public void setIdCardInfoAuth(GetUploadFileIdResult.DataBean idBean) {
        dataBeanAuth.setImgPath(idBean.getImgPath());
    }

    /**
     * 跳转
     */
    public void toMerchApplyPhoto() {
        Router.newIntent(context)
                .to(MerchApplyPhotoActivity.class)
                .putString("applyType", applyType)
                .putString("shopId", shopId)
                .putString("addShopId", addShopId)
                .putString("bindType", bindType)
                .launch();
        Router.pop(context);

    }

    /**
     * 根据filename，成功或者失败，在相应的imageview显示对应的图片
     *
     * @param file_type 图片名
     * @param success   识别成功/识别失败/上传失败
     */
    public void showphotos(String file_type, String success, String message) {
        getvDelegate().dismissLoading();
        switch (file_type) {
            case "25":
                if (success.equals("success")) {
                    ILFactory.getLoader().loadImageSizekipMemoryCache(imgPath[0], merchApplyIdcardZmIv);
                    imgFlag[0] = "00";
                    merchApplyIdcardTv.setVisibility(View.GONE);
                } else if (success.equals("fail")) {
                    ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, merchApplyIdcardZmIv);
                    imgFlag[0] = "01";
                    merchApplyIdcardTv.setVisibility(View.VISIBLE);
                    merchApplyIdcardTv.setText(message);
                } else if (success.equals("error")) {
                    ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, merchApplyIdcardZmIv);
                    imgFlag[0] = "01";
                    merchApplyIdcardTv.setVisibility(View.VISIBLE);
                    merchApplyIdcardTv.setText(message);
                }
                break;
            case "26":
                if (success.equals("success")) {
                    ILFactory.getLoader().loadImageSizekipMemoryCache(imgPath[0], merchApplyIdcardFmIv);
                    imgFlag[0] = "00";
                    merchApplyIdcardTv.setVisibility(View.GONE);
                } else if (success.equals("fail")) {
                    ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, merchApplyIdcardFmIv);
                    imgFlag[0] = "01";
                    merchApplyIdcardTv.setVisibility(View.VISIBLE);
                    merchApplyIdcardTv.setText(message);
                } else if (success.equals("error")) {
                    ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, merchApplyIdcardFmIv);
                    imgFlag[0] = "01";
                    merchApplyIdcardTv.setVisibility(View.VISIBLE);
                    merchApplyIdcardTv.setText(message);
                }
                break;
            case "23":
                if (success.equals("success")) {
                    ILFactory.getLoader().loadImageSizekipMemoryCache(imgPath[0], merchApplyIdcardZfbAuthIv);
                    imgFlag[0] = "00";
                    merchApplyIdcardTv.setVisibility(View.GONE);
                } else if (success.equals("fail")) {
                    ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, merchApplyIdcardZfbAuthIv);
                    imgFlag[0] = "01";
                    merchApplyIdcardTv.setVisibility(View.VISIBLE);
                    merchApplyIdcardTv.setText(message);
                } else if (success.equals("error")) {
                    ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, merchApplyIdcardZfbAuthIv);
                    imgFlag[0] = "01";
                    merchApplyIdcardTv.setVisibility(View.VISIBLE);
                    merchApplyIdcardTv.setText(message);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CameraActivity.REQUEST_CODE && resultCode == CameraActivity.RESULT_CODE) {
            if (fileUtill.hasSdcard()) {
//                mFilePath = rootPath + mFilename;
                File file = new File(rootPath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                mFilePath = data.getExtras().getString("path");
                Luban.with(this)
                        .load(mFilePath)                                   // 传人要压缩的图片列表
                        .ignoreBy(100)                                  // 忽略不压缩图片的大小
                        .setTargetDir(rootPath)                        // 设置压缩后文件存储位置
                        .setCompressListener(new OnCompressListener() { //设置回调
                            @Override
                            public void onStart() {
                                // TODO 压缩开始前调用，可以在方法内启动 loading UI
                            }

                            @Override
                            public void onSuccess(File file) {
                                // TODO 压缩成功后调用，返回压缩后的图片文件
                                File delPath = new File(mFilePath);
                                fileUtill.delDir(delPath);
                                switch (mCount) {
                                    case IDCARDFRONTZM:
                                        if (!context.isFinishing()) {
                                            ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", merchApplyIdcardZmIv);
                                        }
                                        imgPath[0] = file.getAbsolutePath();
                                        if ("1".equals(bindType)) {
                                            getP().uploadFileId(AppUser.getInstance().getMerchId(), addShopId, "25", null,
                                                    null, null, null, file);
                                        } else {
                                            getP().uploadFileId(AppUser.getInstance().getMerchId(), shopId, "25", null,
                                                    null, null, null, file);
                                        }
                                        break;
                                    case IDCARDFRONTFM:
                                        if (!context.isFinishing()) {
                                            ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", merchApplyIdcardFmIv);
                                        }
                                        imgPath[0] = file.getAbsolutePath();
                                        if ("1".equals(bindType)) {
                                            getP().uploadFileId(AppUser.getInstance().getMerchId(), addShopId, "26", null,
                                                    null, null, null, file);
                                        } else {
                                            getP().uploadFileId(AppUser.getInstance().getMerchId(), shopId, "26", null,
                                                    null, null, null, file);
                                        }
                                        break;
                                    case IDCARDFRONTAUTH:
                                        if (!context.isFinishing()) {
                                            ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", merchApplyIdcardZfbAuthIv);
                                        }
                                        imgPath[0] = file.getAbsolutePath();
                                        if ("1".equals(bindType)) {
                                            getP().uploadFileId(AppUser.getInstance().getMerchId(), addShopId, "23", null,
                                                    null, null, null, file);
                                        } else {
                                            getP().uploadFileId(AppUser.getInstance().getMerchId(), shopId, "23", null,
                                                    null, null, null, file);
                                        }
                                        break;
                                    default:
                                        break;
                                }
                                file.deleteOnExit();
                            }

                            @Override
                            public void onError(Throwable e) {
                                // TODO 当压缩过程出现问题时调用
                                showToast("图片处理出错，请重试");
                            }
                        }).launch();    //启动压缩

            } else {
                showToast("请检查是否有外置存储卡");
            }
        } else if (requestCode >= 2 && requestCode <= 4 && resultCode != 0) {
            try {
                String picturePath = GetImagePath.getPath(context, data.getData());  //获取照片路径
                if (fileUtill.hasSdcard()) {
                    mFilePath = rootPath + mFilename;
                    File file = new File(rootPath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    Luban.with(this)
                            .load(picturePath)                                   // 传人要压缩的图片列表
                            .ignoreBy(100)                                  // 忽略不压缩图片的大小
                            .setTargetDir(rootPath)                        // 设置压缩后文件存储位置
                            .setCompressListener(new OnCompressListener() { //设置回调
                                @Override
                                public void onStart() {
                                    // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                }

                                @Override
                                public void onSuccess(File file) {
                                    // TODO 压缩成功后调用，返回压缩后的图片文件
                                    File delPath = new File(mFilePath);
                                    fileUtill.delDir(delPath);
                                    switch (mCount) {
                                        case IDCARDFRONTZM:
                                            if (!context.isFinishing()) {
                                                ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", merchApplyIdcardZmIv);
                                            }
                                            imgPath[0] = file.getAbsolutePath();
                                            getP().uploadFileId(AppUser.getInstance().getMerchId(), shopId, "25", null,
                                                    null, null, null, file);
                                            break;
                                        case IDCARDFRONTFM:
                                            if (!context.isFinishing()) {
                                                ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", merchApplyIdcardFmIv);
                                            }
                                            imgPath[0] = file.getAbsolutePath();
                                            getP().uploadFileId(AppUser.getInstance().getMerchId(), shopId, "26", null,
                                                    null, null, null, file);
                                            break;
                                        case IDCARDFRONTAUTH:
                                            if (!context.isFinishing()) {
                                                ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", merchApplyIdcardZfbAuthIv);
                                            }
                                            imgPath[0] = file.getAbsolutePath();
                                            if ("1".equals(bindType)) {
                                                getP().uploadFileId(AppUser.getInstance().getMerchId(), addShopId, "23", null,
                                                        null, null, null, file);
                                            } else {
                                                getP().uploadFileId(AppUser.getInstance().getMerchId(), shopId, "23", null,
                                                        null, null, null, file);
                                            }
                                            break;
                                        default:
                                            break;
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    // TODO 当压缩过程出现问题时调用
                                    showToast("图片处理出错，请重试");
                                }
                            }).launch();    //启动压缩
                }
            } catch (Exception e) {
                // TODO Auto-generatedcatch block
                e.printStackTrace();
            }
        }

    }

    /**
     * 底部对话框
     *
     * @param fileName 图片名称
     */
    private void choosePicture(final String fileName) {
        BottomDialog bottomDailog = new BottomDialog(this);
        ArrayList<ChooseItem> imgArray = new ArrayList<>();
        imgArray.add(new ChooseItem("拍照", null));
        imgArray.add(new ChooseItem("相册", null));
        bottomDailog.showAlert(null, imgArray, new BottomDialog.OnAlertSelectId() {

            @Override
            public void onClick(int whichButton) {
                if (whichButton == 0) {
                    takePhoto(fileName);
                } else if (whichButton == 1) {
                    gallery(fileName);
                }
            }
        });
    }

    /**
     * 拍照选取图片
     *
     * @param fileName
     */
    private void takePhoto(final String fileName) {
        getRxPermissions()
                .request(Manifest.permission.CAMERA)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (granted) {
                            //TODO 许可
                            String path = rootPath;
                            File dir = new File(path);
                            if (!dir.exists()) {
                                dir.mkdirs();
                            }
                            switch (mCount) {
                                case IDCARDFRONTZM:
                                    CameraActivity.toCameraActivity(context, CameraActivity.TYPE_IDCARD_FRONT);
                                    break;
                                case IDCARDFRONTFM:
                                    CameraActivity.toCameraActivity(context, CameraActivity.TYPE_IDCARD_FRONT);
                                    break;
                                case IDCARDFRONTAUTH:
                                    CameraActivity.toCameraActivity(context, CameraActivity.TYPE_BANKCARD);
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            //TODO 未许可
//                            showToast("权限未获取");
                            showNoticeDialog("尚未获取权限，是否去开启权限?", new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    PermissionPageUtils permissionPageUtils = new PermissionPageUtils(context);
                                    permissionPageUtils.jumpPermissionPage();
                                }

                            });
                        }
                    }
                });

    }

    /**
     * 从相册选取图片
     *
     * @param fileName 图片名称
     */
    private void gallery(final String fileName) {
        getRxPermissions()
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if (granted) {
                            //TODO 许可
                            Intent intent = new Intent();
                            mFilename = fileName + ".jpg";
                            String path = rootPath;
                            File dir = new File(path);
                            if (!dir.exists()) {
                                dir.mkdirs();
                            }
                            File file = new File(rootPath,
                                    mFilename);
                            if (file.exists()) {
                                file.delete();
                            }
                            switch (mCount) {
                                case IDCARDFRONTZM:
                                    intent.setAction(Intent.ACTION_PICK);//Pick an item from the data
                                    intent.setType("image/*");//从所有图片中进行选择
                                    startActivityForResult(intent, 2);
                                    break;
                                case IDCARDFRONTFM:
                                    intent.setAction(Intent.ACTION_PICK);//Pick an item from the data
                                    intent.setType("image/*");//从所有图片中进行选择
                                    startActivityForResult(intent, 3);
                                    break;
                                case IDCARDFRONTAUTH:
                                    intent.setAction(Intent.ACTION_PICK);//Pick an item from the data
                                    intent.setType("image/*");//从所有图片中进行选择
                                    startActivityForResult(intent, 4);
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            //TODO 未许可
//                            showToast("权限未获取");
                            showNoticeDialog("尚未获取权限，是否去开启权限?", new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    if (which == DialogAction.POSITIVE) {
                                        PermissionPageUtils permissionPageUtils = new PermissionPageUtils(context);
                                        permissionPageUtils.jumpPermissionPage();
                                    }
                                }
                            });
                        }
                    }
                });
    }

}
