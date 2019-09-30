package com.yzf.king.ui.activitys;

import android.Manifest;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.xw.repo.XEditText;
import com.yzf.king.R;
import com.yzf.king.kit.AppTools;
import com.yzf.king.kit.AppUser;
import com.yzf.king.kit.utils.GetImagePath;
import com.yzf.king.kit.utils.PermissionPageUtils;
import com.yzf.king.kit.utils.fileUtill;
import com.yzf.king.model.ChooseItem;
import com.yzf.king.model.servicesmodels.GetAddApplyInfoResults;
import com.yzf.king.model.servicesmodels.GetShopApplyResults;
import com.yzf.king.model.servicesmodels.GetSunMerchInfoListResult;
import com.yzf.king.model.servicesmodels.GetUploadFileIdResult;
import com.yzf.king.model.servicesmodels.GetUploadFileResult;
import com.yzf.king.present.PMerchApplyPermit;
import com.yzf.king.widget.BottomDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.imageloader.ILFactory;
import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.router.Router;
import rx.functions.Action1;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class MerchApplyPermitActivity extends XActivity<PMerchApplyPermit> implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.merch_apply_type)
    TextView merchApplyType;
    @BindView(R.id.merch_apply_type_tv)
    TextView merchApplyTypeTv;
    @BindView(R.id.merch_apply_type_iv)
    ImageView merchApplyTypeIv;
    @BindView(R.id.merch_apply_type_rl)
    RelativeLayout merchApplyTypeRl;
    @BindView(R.id.merch_apply_permit_add_iv)
    ImageView merchApplyPermitAddIv;
    @BindView(R.id.merch_apply_permit_add_tv)
    TextView merchApplyPermitAddTv;
    @BindView(R.id.merch_apply_permit_shopname)
    TextView merchApplyPermitShopname;
    @BindView(R.id.merch_apply_permit_shopname_tv)
    XEditText merchApplyPermitShopnameTv;
    @BindView(R.id.merch_apply_permit_number)
    TextView merchApplyPermitNumber;
    @BindView(R.id.merch_apply_permit_number_et)
    XEditText merchApplyPermitNumberEt;
    @BindView(R.id.merch_apply_permit_legal_person)
    TextView merchApplyPermitLegalPerson;
    @BindView(R.id.merch_apply_permit_legal_person_tv)
    XEditText merchApplyPermitLegalPersonTv;
    @BindView(R.id.merch_apply_permit_data)
    TextView merchApplyPermitData;
    @BindView(R.id.merch_apply_permit_data_tv)
    TextView merchApplyPermitDataTv;
    @BindView(R.id.merch_apply_legal_person_phone)
    TextView merchApplyLegalPersonPhone;
    @BindView(R.id.merch_apply_legal_person_phone_tv)
    XEditText merchApplyLegalPersonPhoneTv;
    @BindView(R.id.merch_apply_legal_person_email)
    TextView merchApplyLegalPersonEmail;
    @BindView(R.id.merch_apply_legal_person_email_tv)
    XEditText merchApplyLegalPersonEmailTv;
    @BindView(R.id.merch_apply_address)
    TextView merchApplyAddress;
    @BindView(R.id.merch_apply_address_tv)
    TextView merchApplyAddressTv;
    @BindView(R.id.merch_apply_address_iv)
    ImageView merchApplyAddressIv;
    @BindView(R.id.merch_apply_address_rl)
    RelativeLayout merchApplyAddressRl;
    @BindView(R.id.merch_apply_define_bt)
    Button merchApplyDefineBt;
    @BindView(R.id.merch_apply_category)
    TextView merchApplyCategory;
    @BindView(R.id.merch_apply_category_tv)
    TextView merchApplyCategoryTv;
    @BindView(R.id.merch_apply_category_iv)
    ImageView merchApplyCategoryIv;
    @BindView(R.id.merch_apply_category_rl)
    RelativeLayout merchApplyCategoryRl;
    @BindView(R.id.merch_apply_device_number)
    XEditText merchApplyDeviceNumber;
    @BindView(R.id.merch_apply_address_dtl_tv)
    XEditText merchApplyAddressDtl;
    @BindView(R.id.merch_apply_permit_iv)
    ImageView merchApplyPermitIv;
    @BindView(R.id.merch_apply_permit_prompt)
    TextView merchApplyPermitPrompt;
    @BindView(R.id.merch_apply_permit_zm_iv)
    ImageView merchApplyPermitZmIv;
    @BindView(R.id.merch_apply_permit_fm_iv)
    ImageView merchApplyPermitFmIv;
    @BindView(R.id.merch_apply_permit_tv)
    TextView merchApplyPermitTv;
    @BindView(R.id.merch_apply_permit_ll)
    LinearLayout merchApplyPermitLl;
    @BindView(R.id.merch_apply_permit_prompts)
    TextView merchApplyPermitPrompts;
    @BindView(R.id.merch_apply_permit_name)
    TextView merchApplyPermitName;
    @BindView(R.id.merch_apply_permit_name_tv)
    TextView merchApplyPermitNameTv;
    @BindView(R.id.merch_apply_permit_name_rl)
    RelativeLayout merchApplyPermitNameRl;
    @BindView(R.id.merch_apply_permit_idnumber)
    TextView merchApplyPermitIdnumber;
    @BindView(R.id.merch_apply_permit_idnumber_tv)
    TextView merchApplyPermitIdNumberTv;
    @BindView(R.id.merch_apply_permit_number_rl)
    RelativeLayout merchApplyPermitNumberRl;
    @BindView(R.id.merch_apply_permit_validity)
    TextView merchApplyPermitValidity;
    @BindView(R.id.merch_apply_permit_begin_tv)
    TextView merchApplyPermitBeginTv;
    @BindView(R.id.merch_apply_permit_end_tv)
    TextView merchApplyPermitEndTv;
    @BindView(R.id.merch_apply_permit_validity_rl)
    RelativeLayout merchApplyPermitValidityRl;

    private String type;
    private int mCount;
    private String mMobile;
    private String mFilePath;
    private String rootPath;
    private String mFilename;
    private String[] imgPath = new String[]{null};
    private String[] imgFlag = new String[]{null};
    private String[] itemList = new String[]{null};
    private static final int IDCARDBACK = 0;
    private static final int IDCARDFRONTZM = 1;
    private static final int IDCARDFRONTFM = 2;
    private String date;
    private String ranges;
    private String provinceCode;
    private String cityCode;
    private String districtCode;
    private String provinceName;
    private String cityName;
    private String districtName;
    private String applyType;
    private String bindType;
    private String idvalidity;
    private GetSunMerchInfoListResult.DataBean.SubListBean subListBean;
    private GetUploadFileResult.DataBean dataBean = new GetUploadFileResult.DataBean();
    private GetShopApplyResults.DataBean shopApplyDataBean = new GetShopApplyResults.DataBean();
    private GetAddApplyInfoResults.DataBean addApplyDataBean = new GetAddApplyInfoResults.DataBean();
    private GetUploadFileIdResult.DataBean dataBeanZM = new GetUploadFileIdResult.DataBean();
    private GetUploadFileIdResult.DataBean dataBeanFM = new GetUploadFileIdResult.DataBean();
    private GetUploadFileIdResult.DataBean.IdBeanBean idBeanBean = new GetUploadFileIdResult.DataBean.IdBeanBean();

    @Override
    public void initData(Bundle savedInstanceState) {
        mMobile = AppUser.getInstance().getPhone();
        applyType = getIntent().getStringExtra("applyType");
        bindType = getIntent().getStringExtra("bindType");
        subListBean = (GetSunMerchInfoListResult.DataBean.SubListBean) getIntent().getSerializableExtra("subListBean");
        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_merch_apply_permit;
    }

    @Override
    public PMerchApplyPermit newP() {
        return new PMerchApplyPermit();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        initToolbar();
        initEditTextView();
        //applyType 1：支付宝    applyType 2：微信
        if ("1".equals(applyType)) {
            merchApplyPermitIv.setImageDrawable(getResources().getDrawable(R.mipmap.merch_apply_permit_zfb_progressbar_iv));
            title.setText("支付宝商家申请");
        } else if ("2".equals(applyType)) {
            merchApplyPermitIv.setImageDrawable(getResources().getDrawable(R.mipmap.merch_apply_permit_wx_progressbar_iv));
            title.setText("微信商家申请");
        } else {
            merchApplyPermitIv.setImageDrawable(getResources().getDrawable(R.mipmap.merch_apply_permit_zfb_progressbar_iv));
            title.setText("支付宝商家申请");
        }



        itemList = new String[]{"交通出行-旅馆/酒店/度假区",
                "交通出行-铁路客运",
                "交通出行-加油",
                "休闲娱乐-娱乐票务",
                "休闲娱乐-运动健身场馆",
                "休闲娱乐-俱乐部/休闲会所",
                "休闲娱乐-游艺厅/KTV/网吧",
                "休闲娱乐-美发/美容/美甲店",
                "休闲娱乐-酒吧",
                "居民生活-咨询/法律咨询/金融咨询等",
                "居民生活-婚庆/摄影",
                "居民生活-家政/维修服务",
                "居民生活-装饰/设计",
                "居民生活-搬家/回收",
                "居民生活-宠物医院",
                "线下零售-数码电器/电脑办公",
                "线下零售-家具建材/家居厨具",
                "线下零售-服饰箱包",
                "线下零售-运动户外",
                "线下零售-美妆个护",
                "线下零售-母婴用品/儿童玩具",
                "线下零售-批发业",
                "线下零售-钟表眼镜",
                "线下零售-便利店",
                "线下零售-食品生鲜",
                "餐饮-小吃/熟食",
                "餐饮-甜品饮品",
                "餐饮-烘焙糕点",
                "餐饮-西餐",
                "餐饮-餐饮",
                "其他-游戏",
                "其他-软件/建站/技术开发",
                "其他-网络推广/网络广告"};
    }

    private void initEditTextView() {
        merchApplyPermitLegalPersonTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                merchApplyPermitPrompt.setText("请上传"+s+"身份证信息");
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        rootPath = context.getExternalFilesDir("") + File.separator + "Pictures/";
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

    @OnClick({R.id.merch_apply_type_rl, R.id.merch_apply_permit_add_iv, R.id.merch_apply_define_bt,
            R.id.merch_apply_permit_data_tv, R.id.merch_apply_category_rl, R.id.merch_apply_address_rl,
            R.id.merch_apply_permit_zm_iv, R.id.merch_apply_permit_fm_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.merch_apply_address_rl:
                JDCityPicker cityPicker = new JDCityPicker();
                JDCityConfig jdCityConfig = new JDCityConfig.Builder().build();

                jdCityConfig.setShowType(JDCityConfig.ShowType.PRO_CITY_DIS);
                cityPicker.init(this);
                cityPicker.setConfig(jdCityConfig);
                cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        merchApplyAddressTv.setText(province.getName() + city.getName() + district.getName());
                        //省市区名称
                        provinceName = province.getName();
                        if (city.getName().contains("省直辖县级行政单位")) {
                            cityName = city.getName().replace("省直辖县级行政单位",province.getName());
                        }else {
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
            case R.id.merch_apply_category_rl:
                new XPopup.Builder(context)
//                        .enableDrag(false)
                        .asBottomList("请选择", itemList,
                                new OnSelectListener() {
                                    @Override
                                    public void onSelect(int position, String text) {
                                        merchApplyCategoryTv.setText(text);
                                        ranges = text;
                                    }
                                })
                        .show();
                break;
            case R.id.merch_apply_type_rl:
                new XPopup.Builder(context)
//                        .enableDrag(false)
                        .asBottomList("请选择", new String[]{"三证合一", "未三证合一"},
                                new OnSelectListener() {
                                    @Override
                                    public void onSelect(int position, String text) {
                                        switch (position) {
                                            case 0:
                                                type = "0";
                                                break;
                                            case 1:
                                                type = "1";
                                                break;
                                            default:
                                                break;
                                        }
                                        merchApplyTypeTv.setText(text);
                                    }
                                })
                        .show();
                break;
            case R.id.merch_apply_permit_add_iv:
                mCount = IDCARDBACK;
                choosePicture(mMobile + "_IDCARDBACK");
                break;
            case R.id.merch_apply_permit_zm_iv:
                mCount = IDCARDFRONTZM;
                choosePicture(mMobile + "_IDCARDFRONTZM");
                break;
            case R.id.merch_apply_permit_fm_iv:
                mCount = IDCARDFRONTFM;
                choosePicture(mMobile + "_IDCARDFRONTFM");
                break;
            case R.id.merch_apply_define_bt:
                boolean flag = false;
                for (int i = 0; i < imgFlag.length; i++) {
                    String temp = imgFlag[i];
                    if (AppTools.isEmpty(temp) || !temp.equals("00")) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    showToast("请提交上传的图片");
                } else {
                    if (AppTools.isEmpty(merchApplyTypeTv.getText().toString())) {
                        showToast("请选择营业执照类型");
                        return;
                    }

                    if (AppTools.isEmpty(merchApplyPermitShopnameTv.getTextEx())) {
                        showToast("请输入店铺名称");
                        return;
                    }
                    if (!AppTools.isEmpty(merchApplyPermitShopnameTv.getTextEx()) && merchApplyPermitShopnameTv.getTextEx().length() < 2) {
                        showToast("店铺名称格式不正确");
                        return;
                    }

                    if (AppTools.isEmpty(merchApplyPermitNumberEt.getText().toString())) {
                        showToast("请输入营业执照号");
                        return;
                    }
                    if (!AppTools.isEmpty(merchApplyPermitNumberEt.getText().toString()) && merchApplyPermitNumberEt.getText().toString().length() < 10) {
                        showToast("营业执照号格式不正确");
                        return;
                    }

                    if (AppTools.isEmpty(merchApplyPermitLegalPersonTv.getTextEx())) {
                        showToast("请输入法人姓名");
                        return;
                    }
                    if (!AppTools.isEmpty(merchApplyPermitLegalPersonTv.getTextEx()) && merchApplyPermitLegalPersonTv.getTextEx().length() < 2) {
                        showToast("法人姓名格式不正确");
                        return;
                    }

                    if (AppTools.isEmpty(merchApplyPermitDataTv.getText().toString())) {
                        showToast("请选择营业执照注册时间");
                        return;
                    }
                    if (AppTools.isEmpty(merchApplyCategoryTv.getText().toString())) {
                        showToast("请选择经营类目");
                        return;
                    }
                    if (AppTools.isEmpty(merchApplyLegalPersonPhoneTv.getTextEx())) {
                        showToast("请填写法人电话");
                        return;
                    }
                    if (AppTools.isEmpty(merchApplyLegalPersonEmailTv.getTextEx())) {
                        showToast("请填写法人邮箱");
                        return;
                    }
                    if (AppTools.isEmpty(merchApplyAddressTv.getText().toString())) {
                        showToast("请选择地区");
                        return;
                    }
                    if (AppTools.isEmpty(merchApplyAddressDtl.getTextEx())) {
                        showToast("请填写详细地址");
                        return;
                    }
                    if (!AppTools.isMobile(merchApplyLegalPersonPhoneTv.getTextEx())) {
                        showToast("法人电话格式不正确，请重新填写");
                        return;
                    }
                    if (!AppTools.isEmail(merchApplyLegalPersonEmailTv.getTextEx())) {
                        showToast("法人邮箱格式不正确，请重新填写");
                        return;
                    }
                    if (AppTools.isEmpty(merchApplyPermitNameTv.getText().toString())) {
                        showToast("请上传身份证姓名");
                        return;
                    }
                    if (AppTools.isEmpty(merchApplyPermitIdNumberTv.getText().toString())) {
                        showToast("请上传身份证号");
                        return;
                    }
                    if (AppTools.isEmpty(merchApplyPermitBeginTv.getText().toString())) {
                        showToast("请上传身份证有效期");
                        return;
                    }
                    if (!merchApplyPermitLegalPersonTv.getTextEx().equals(merchApplyPermitNameTv.getText().toString())) {
                        showToast("请确保法人姓名与身份证姓名一致");
                        return;
                    }
                    //bindType为1时材料驳回
                    if ("1".equals(bindType)) {
                        //applyType 1：支付宝    applyType 2：微信
                        if ("1".equals(applyType)) {
                            getP().addApplyInfo(AppUser.getInstance().getMerchId(), subListBean.getShopId(), "0", "5",
                                    dataBeanZM.getImgPath(), dataBeanFM.getImgPath(), merchApplyPermitIdNumberTv.getText().toString(),merchApplyPermitName.getText().toString(),
                                    null, null, null, null, null, null, null,
                                    null, null, null, null, null, null,
                                    null, bindType, type, ranges, dataBean.getImgPath(), merchApplyPermitNumberEt.getText().toString(),
                                    merchApplyPermitShopnameTv.getTextEx(), merchApplyPermitDataTv.getText().toString(), provinceName, cityName, provinceCode, cityCode,
                                    districtName, merchApplyAddressDtl.getTextEx(), merchApplyLegalPersonEmailTv.getTextEx(),
                                    merchApplyLegalPersonPhoneTv.getTextEx(),districtCode, merchApplyPermitLegalPersonTv.getTextEx());
                        } else if ("2".equals(applyType)) {
                            getP().addApplyInfo(AppUser.getInstance().getMerchId(), subListBean.getShopId(), "1", "5",
                                    dataBeanZM.getImgPath(), dataBeanFM.getImgPath(), merchApplyPermitIdNumberTv.getText().toString(),merchApplyPermitName.getText().toString(),
                                    null, null, null, null, null, null, null,
                                    null, null, null, null, null, null, null,
                                    bindType, type, ranges, dataBean.getImgPath(), merchApplyPermitNumberEt.getText().toString(), merchApplyPermitShopnameTv.getTextEx(),
                                    merchApplyPermitDataTv.getText().toString(), provinceName, cityName, provinceCode, cityCode,
                                    districtName, merchApplyAddressDtl.getTextEx(), merchApplyLegalPersonEmailTv.getTextEx(),
                                    merchApplyLegalPersonPhoneTv.getTextEx(),districtCode, merchApplyPermitLegalPersonTv.getTextEx());
                        } else {
                            getP().addApplyInfo(AppUser.getInstance().getMerchId(), subListBean.getShopId(), "3", "5",
                                    dataBeanZM.getImgPath(), dataBeanFM.getImgPath(), merchApplyPermitIdNumberTv.getText().toString(),merchApplyPermitName.getText().toString(),
                                    null, null, null, null, null, null, null,
                                    null, null, null, null, null, null,
                                    null, bindType, type, ranges, dataBean.getImgPath(), merchApplyPermitNumberEt.getText().toString(),
                                    merchApplyPermitShopnameTv.getTextEx(), merchApplyPermitDataTv.getText().toString(), provinceName, cityName, provinceCode, cityCode,
                                    districtName, merchApplyAddressDtl.getTextEx(), merchApplyLegalPersonEmailTv.getTextEx(),
                                    merchApplyLegalPersonPhoneTv.getTextEx(),districtCode, merchApplyPermitLegalPersonTv.getTextEx());
                        }
                    } else {
                        if (!AppTools.isEmpty(applyType)) {
                            //applyType 1：支付宝    applyType 2：微信
                            if ("1".equals(applyType)) {
                                getP().shopApply(AppUser.getInstance().getMerchId(), "0", type, ranges, dataBean.getImgPath(),
                                        merchApplyPermitNumberEt.getText().toString(), merchApplyPermitShopnameTv.getTextEx(),
                                        merchApplyPermitDataTv.getText().toString(), provinceName, cityName, provinceCode, cityCode,
                                        districtName, merchApplyAddressDtl.getTextEx(), merchApplyLegalPersonEmailTv.getTextEx(),
                                        merchApplyLegalPersonPhoneTv.getTextEx(), merchApplyPermitLegalPersonTv.getTextEx(), districtCode,
                                        dataBeanZM.getImgPath(), dataBeanFM.getImgPath(), merchApplyPermitIdNumberTv.getText().toString(),
                                        merchApplyPermitNameTv.getText().toString(), idvalidity);
                            } else if ("2".equals(applyType)) {
                                getP().shopApply(AppUser.getInstance().getMerchId(), "1", type, ranges, dataBean.getImgPath(),
                                        merchApplyPermitNumberEt.getText().toString(), merchApplyPermitShopnameTv.getTextEx(),
                                        merchApplyPermitDataTv.getText().toString(), provinceName, cityName, provinceCode, cityCode,
                                        districtName, merchApplyAddressDtl.getTextEx(), merchApplyLegalPersonEmailTv.getTextEx(),
                                        merchApplyLegalPersonPhoneTv.getTextEx(), merchApplyPermitLegalPersonTv.getTextEx(), districtCode,
                                        dataBeanZM.getImgPath(), dataBeanFM.getImgPath(), merchApplyPermitIdNumberTv.getText().toString(),
                                        merchApplyPermitNameTv.getText().toString(), idvalidity);
                            } else {
                                getP().shopApply(AppUser.getInstance().getMerchId(), "3", type, ranges, dataBean.getImgPath(),
                                        merchApplyPermitNumberEt.getText().toString(), merchApplyPermitShopnameTv.getTextEx(),
                                        merchApplyPermitDataTv.getText().toString(), provinceName, cityName, provinceCode, cityCode,
                                        districtName, merchApplyAddressDtl.getTextEx(), merchApplyLegalPersonEmailTv.getTextEx(),
                                        merchApplyLegalPersonPhoneTv.getTextEx(), merchApplyPermitLegalPersonTv.getTextEx(), districtCode,
                                        dataBeanZM.getImgPath(), dataBeanFM.getImgPath(), merchApplyPermitIdNumberTv.getText().toString(),
                                        merchApplyPermitNameTv.getText().toString(), idvalidity);
                            }
                        }
                    }
                }
                break;
            case R.id.merch_apply_permit_data_tv:
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
                break;
        }
    }

    /**
     * 获取身份证正面
     *
     * @param idBean
     */
    public void setIdCardInfoZM(GetUploadFileIdResult.DataBean idBean) {
        merchApplyPermitNameTv.setText(idBean.getIdBean().getName().trim().replace(" ", ""));
        merchApplyPermitIdNumberTv.setText(idBean.getIdBean().getIdNo().trim().replace(" ", ""));

        dataBeanZM.setImgPath(idBean.getImgPath());

    }

    /**
     * 获取身份证反面
     *
     * @param idBean
     */
    public void setIdCardInfoFM(GetUploadFileIdResult.DataBean idBean) {
        dataBeanFM.setImgPath(idBean.getImgPath());

        merchApplyPermitBeginTv.setText(Kits.Date.formatTime(idBean.getIdBean().getEffectDate(), "yyyymmdd", "yyyy-mm-dd"));
        if (!"长期".equals(idBean.getIdBean().getInvalidDate())) {
            merchApplyPermitEndTv.setText("—" + Kits.Date.formatTime(idBean.getIdBean().getInvalidDate(), "yyyymmdd", "yyyy-mm-dd"));
        } else {
            merchApplyPermitEndTv.setVisibility(View.GONE);
        }

        idBeanBean.setEffectDate(idBean.getIdBean().getEffectDate());
        idBeanBean.setInvalidDate(idBean.getIdBean().getInvalidDate());


        idvalidity = Kits.Date.formatTime(idBean.getIdBean().getEffectDate(), "yyyymmdd", "yyyy-mm-dd") + ","
                + Kits.Date.formatTime(idBean.getIdBean().getInvalidDate(), "yyyymmdd", "yyyy-mm-dd");
    }

    /**
     * 跳转页面
     */
    public void toMerchApplyIdcard() {
        Router.newIntent(context)
                .to(MerchApplyIdcardActivity.class)
                .putString("applyType", applyType)
                .putString("shopId", shopApplyDataBean.getShopId())
                .putString("addShopId", addApplyDataBean.getShopId())
                .putString("bindType", bindType)
                .putString("legalName", merchApplyPermitLegalPersonTv.getTextEx())
                .putString("shopName", merchApplyPermitShopnameTv.getTextEx())
                .launch();
        Router.pop(context);
    }

    /**
     * 时间选择器
     *
     * @param view
     * @param year
     * @param monthOfYear
     * @param dayOfMonth
     */
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
        date = year + "-" + month + "-" + day;
        merchApplyPermitDataTv.setText(date);
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
                            File file = new File(rootPath);
                            if (!file.exists()) {
                                file.mkdirs();
                            }
                            switch (mCount) {
                                case IDCARDBACK:
                                    CameraActivity.toCameraActivity(context, CameraActivity.TYPE_BANKCARD);
                                    break;
                                case IDCARDFRONTZM:
                                    CameraActivity.toCameraActivity(context, CameraActivity.TYPE_IDCARD_FRONT);
                                    break;
                                case IDCARDFRONTFM:
                                    CameraActivity.toCameraActivity(context, CameraActivity.TYPE_IDCARD_FRONT);
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
                            intent.setAction(Intent.ACTION_PICK);//Pick an item from the data
                            intent.setType("image/*");//从所有图片中进行选择
                            startActivityForResult(intent, 2);

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

    /**
     * 获取上传图片的数据
     *
     * @param licenseBeanBean
     */
    public void setIdInfo(GetUploadFileResult.DataBean licenseBeanBean) {
        merchApplyPermitShopnameTv.setText(licenseBeanBean.getLicenseBean().getShopName());
        merchApplyPermitLegalPersonTv.setText(licenseBeanBean.getLicenseBean().getName());
        merchApplyPermitNumberEt.setText(licenseBeanBean.getLicenseBean().getLicenseNo().trim().replace(" ", ""));

        dataBean.setImgPath(licenseBeanBean.getImgPath());
    }

    /**
     * 获取上传注册资料的数据
     */
    public void setInfo(GetShopApplyResults.DataBean data) {
        shopApplyDataBean.setShopId(data.getShopId());
    }

    /**
     * 获取上传驳回资料的数据
     */
    public void setInfo(GetAddApplyInfoResults.DataBean data) {
        addApplyDataBean.setShopId(data.getShopId());
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
            case "04":
                if ("success".equals(success)) {
                    ILFactory.getLoader().loadImageSizekipMemoryCache(imgPath[0], merchApplyPermitAddIv);
                    imgFlag[0] = "00";
                    merchApplyPermitAddTv.setVisibility(View.GONE);
                } else if ("fail".equals(success)) {
                    ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, merchApplyPermitAddIv);
                    imgFlag[0] = "01";
                    merchApplyPermitAddTv.setVisibility(View.VISIBLE);
                    merchApplyPermitAddTv.setText(message);
                } else if ("error".equals(success)) {
                    ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, merchApplyPermitAddIv);
                    imgFlag[0] = "01";
                    merchApplyPermitAddTv.setVisibility(View.VISIBLE);
                    merchApplyPermitAddTv.setText(message);
                }
                break;
            case "21":
                if (success.equals("success")) {
                    ILFactory.getLoader().loadImageSizekipMemoryCache(imgPath[0], merchApplyPermitZmIv);
                    imgFlag[0] = "00";
                    merchApplyPermitTv.setVisibility(View.GONE);
                } else if (success.equals("fail")) {
                    ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, merchApplyPermitZmIv);
                    imgFlag[0] = "01";
                    merchApplyPermitTv.setVisibility(View.VISIBLE);
                    merchApplyPermitTv.setText(message);
                } else if (success.equals("error")) {
                    ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, merchApplyPermitZmIv);
                    imgFlag[0] = "01";
                    merchApplyPermitTv.setVisibility(View.VISIBLE);
                    merchApplyPermitTv.setText(message);
                }
                break;
            case "22":
                if (success.equals("success")) {
                    ILFactory.getLoader().loadImageSizekipMemoryCache(imgPath[0], merchApplyPermitFmIv);
                    imgFlag[0] = "00";
                    merchApplyPermitTv.setVisibility(View.GONE);
                } else if (success.equals("fail")) {
                    ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, merchApplyPermitFmIv);
                    imgFlag[0] = "01";
                    merchApplyPermitTv.setVisibility(View.VISIBLE);
                    merchApplyPermitTv.setText(message);
                } else if (success.equals("error")) {
                    ILFactory.getLoader().loadImage(R.mipmap.loading_fail_img, merchApplyPermitFmIv);
                    imgFlag[0] = "01";
                    merchApplyPermitTv.setVisibility(View.VISIBLE);
                    merchApplyPermitTv.setText(message);
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
                        .ignoreBy(1024)                                  // 忽略不压缩图片的大小
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
                                    case IDCARDBACK:
                                        if (!context.isFinishing()) {
                                            ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", merchApplyPermitAddIv);
                                        }
                                        imgPath[0] = file.getAbsolutePath();
//                                        getvDelegate().showLoading();
                                        getP().uploadFile(AppUser.getInstance().getMerchId(), null, "04", null,
                                                null, null, null, file);
                                        break;
                                    case IDCARDFRONTZM:
                                        if (!context.isFinishing()) {
                                            ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", merchApplyPermitZmIv);
                                        }
                                        imgPath[0] = file.getAbsolutePath();
                                        if ("1".equals(bindType)) {
                                            getP().uploadFileId(AppUser.getInstance().getMerchId(), addApplyDataBean.getShopId(), "21", null,
                                                    null, null, null, file);
                                        } else {
                                            getP().uploadFileId(AppUser.getInstance().getMerchId(), shopApplyDataBean.getShopId(), "21", null,
                                                    null, null, null, file);
                                        }
                                        break;
                                    case IDCARDFRONTFM:
                                        if (!context.isFinishing()) {
                                            ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", merchApplyPermitFmIv);
                                        }
                                        imgPath[0] = file.getAbsolutePath();
                                        if ("1".equals(bindType)) {
                                            getP().uploadFileId(AppUser.getInstance().getMerchId(), addApplyDataBean.getShopId(), "22", null,
                                                    null, null, null, file);
                                        } else {
                                            getP().uploadFileId(AppUser.getInstance().getMerchId(), shopApplyDataBean.getShopId(), "22", null,
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
        } else if (requestCode == 2 && resultCode != 0) {
            try {
                String picturePath = GetImagePath.getPath(context, data.getData());  //获取照片路径
                if (fileUtill.hasSdcard()) {
                    File file = new File(rootPath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    mFilePath = rootPath + mFilename;
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
                                        case IDCARDBACK:
                                            if (!context.isFinishing()) {
                                                ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", merchApplyPermitAddIv);
                                            }
                                            imgPath[0] = file.getAbsolutePath();
                                            getP().uploadFile(AppUser.getInstance().getMerchId(), null, "04", null,
                                                    null, null, null, file);
                                            break;
                                        case IDCARDFRONTZM:
                                            if (!context.isFinishing()) {
                                                ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", merchApplyPermitZmIv);
                                            }
                                            imgPath[0] = file.getAbsolutePath();
                                            if ("1".equals(bindType)) {
                                                getP().uploadFileId(AppUser.getInstance().getMerchId(), addApplyDataBean.getShopId(), "21", null,
                                                        null, null, null, file);
                                            } else {
                                                getP().uploadFileId(AppUser.getInstance().getMerchId(), shopApplyDataBean.getShopId(), "21", null,
                                                        null, null, null, file);
                                            }
                                            break;
                                        case IDCARDFRONTFM:
                                            if (!context.isFinishing()) {
                                                ILFactory.getLoader().loadGif("file:///android_asset/loading.gif", merchApplyPermitFmIv);
                                            }
                                            imgPath[0] = file.getAbsolutePath();
                                            if ("1".equals(bindType)) {
                                                getP().uploadFileId(AppUser.getInstance().getMerchId(), addApplyDataBean.getShopId(), "22", null,
                                                        null, null, null, file);
                                            } else {
                                                getP().uploadFileId(AppUser.getInstance().getMerchId(), shopApplyDataBean.getShopId(), "22", null,
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

}
