package com.yzf.king.model.servicesmodels;


import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

import java.util.List;

/**
 * ClaseName：MposMerchPickHeaderBean
 * Description：
 * Author：chocozhao
 * QQ: 1027313530
 * Createtime：2019/6/4 16:31
 * Modified By：
 * Fixtime：2019/6/4 16:31
 * FixDescription：
 **/

public class MposMerchPickHeaderBean extends BaseIndexPinyinBean {

    private List<String> cityList;
    //悬停ItemDecoration显示的Tag
    private String suspensionTag;

    public MposMerchPickHeaderBean() {
    }

    public MposMerchPickHeaderBean(List<String> cityList, String suspensionTag, String indexBarTag) {
        this.cityList = cityList;
        this.suspensionTag = suspensionTag;
        this.setBaseIndexTag(indexBarTag);
    }

    public List<String> getCityList() {
        return cityList;
    }

    public MposMerchPickHeaderBean setCityList(List<String> cityList) {
        this.cityList = cityList;
        return this;
    }

    public MposMerchPickHeaderBean setSuspensionTag(String suspensionTag) {
        this.suspensionTag = suspensionTag;
        return this;
    }

    @Override
    public String getTarget() {
        return null;
    }

    @Override
    public boolean isNeedToPinyin() {
        return false;
    }

    @Override
    public String getSuspensionTag() {
        return suspensionTag;
    }
}
