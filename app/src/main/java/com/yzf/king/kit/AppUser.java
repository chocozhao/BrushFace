package com.yzf.king.kit;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yzf.king.kit.db.ParamDao;
import com.yzf.king.model.FilterSource;
import com.yzf.king.model.servicesmodels.GetAcctInfoResult;
import com.yzf.king.model.servicesmodels.GetCardInfoResult;
import com.yzf.king.model.servicesmodels.GetChannelMerchResult;
import com.yzf.king.model.servicesmodels.GetGoodsInfoResults;
import com.yzf.king.model.servicesmodels.GetMachinApplyInfoResults;
import com.yzf.king.model.servicesmodels.GetPosChanlFeeResult;
import com.yzf.king.model.servicesmodels.GetMposIncomeResult;
import com.yzf.king.model.servicesmodels.GetMerchInfoResult;
import com.yzf.king.model.servicesmodels.GetUrlResult;

import java.util.List;
//信息缓存
public class AppUser {
    private String merchId;
    private String merchName;
    private String phone;
    private String status;
    private String token;
    private String merchLevel;
    private String maxAmt;
    private String minAmt;
    private String orderAmt;
    private String orderId;
    private ParamDao paramDao;
    private String merchInfo;
    private String cardInfo;
    private String acctInfo;
    private GetMerchInfoResult.DataBean merchInfoBean;
    private GetCardInfoResult.DataBean cardInfoBean;
    private GetAcctInfoResult.DataBeanX acctInfoBean;
    private GetMposIncomeResult.DataBean.MposApplyRecordBean mposApplyBean;
    private String mposApplyInfo;
    private String cardList;
    private List<GetCardInfoResult.DataBean> cardListBean;

    private String mposFeeInfo;
    private GetPosChanlFeeResult.DataBean mposFeeBean;

    private String mposMerchInfo;
    private GetChannelMerchResult.DataBean mposMerchBean;

    private String urlInfo;
    private List<GetUrlResult.DataBean> urlBean;
    private String usedFlag;
    private String planMinAmt;
    private String isAgent;
    private String filtersource;
    private FilterSource filterSource;
    private String leveName;
    private String agentLevel;
    private String agentFlag;
    private String shopFlag;
    private String deviceNum;
    private String termId;
    private String city;
    private String latitude;
    private String longitude;
    private String servicePhone;
    private String merchlevelname;
    private String merchlevelname2;
    private String branchlevelname;
    private String shoplevelname;
    private String agentlevelname;

    private static class SingletonHolder {
        private static AppUser instance = new AppUser();

        private SingletonHolder() {
        }
    }

    public String getKey() {
        return "0123456789ABCDEF0123456789ABCDEF";
    }

    private AppUser() {
    }

    public static AppUser getInstance() {
        return SingletonHolder.instance;
    }

    public ParamDao getParamDao() {
        if (paramDao == null) {
            paramDao = new ParamDao();
        }
        return paramDao;
    }

    public String getStatus() {
        if (status == null) {
            getParamDao().getByName(paramDao.STATUS);
        }
        return status;
    }

    public void setStatus(String status) {
        getParamDao().saveOrUpdate(paramDao.STATUS, status);
        this.status = status;
    }


    public String getMinAmt() {
        if (minAmt == null) {
            getParamDao().getByName(paramDao.MINAMT);
        }
        return minAmt;
    }

    public void setMinAmt(String minAmt) {
        getParamDao().saveOrUpdate(paramDao.MINAMT, minAmt);
        this.minAmt = minAmt;
    }

    public String getMaxAmt() {
        if (maxAmt == null) {
            getParamDao().getByName(paramDao.MAXAMT);
        }
        return maxAmt;
    }

    public void setMaxAmt(String maxAmt) {
        paramDao.saveOrUpdate(paramDao.MAXAMT, maxAmt);
        this.maxAmt = maxAmt;
    }

    public String getOrderId() {
        if (orderId == null) {
            getParamDao().getByName(paramDao.ORDERID);
        }
        return orderId;
    }

    public void setOrderId(String orderId) {
        getParamDao().saveOrUpdate(paramDao.ORDERID, orderId);
        this.orderId = orderId;
    }


    public String getMerchName() {
        if (merchName == null) {
            merchName = getParamDao().getByName(paramDao.MERCHNAME);
        }
        return merchName;
    }

    public void setMerchName(String merchName) {
        getParamDao().saveOrUpdate(paramDao.MERCHNAME, merchName);
        this.merchName = merchName;
    }


    public String getMerchId() {
        if (merchId == null) {
            merchId = getParamDao().getByName(paramDao.MERCHID);
        }
        return merchId;
    }

    public void setMerchId(String merchId) {
        getParamDao().saveOrUpdate(paramDao.MERCHID, merchId);
        this.merchId = merchId;
    }

    public String getOrderAmt() {
        if (orderAmt == null) {
            orderAmt = getParamDao().getByName(paramDao.ORDERAMT);
        }
        return orderAmt;
    }

    public void setOrderAmt(String orderAmt) {
        getParamDao().saveOrUpdate(paramDao.ORDERAMT, orderAmt);
        this.orderAmt = orderAmt;
    }


    public String getToken() {
        if (token == null) {
            token = getParamDao().getByName(paramDao.TOKEN);
        }
        return token;
    }

    public void setToken(String token) {
        getParamDao().saveOrUpdate(paramDao.TOKEN, token);
        this.token = token;
    }


    public String getMerchInfo() {
        if (merchInfo == null) {
            merchInfo = getParamDao().getByName(paramDao.MERCHINFO);
        }
        return merchInfo;
    }

    public void setMerchInfo(String merchInfo) {
        getParamDao().saveOrUpdate(paramDao.MERCHINFO, merchInfo);
        this.merchInfo = merchInfo;
    }

    public String getPhone() {
        if (phone == null) {
            phone = getParamDao().getByName(paramDao.MERCHINFO);
        }
        return phone;
    }

    public void setPhone(String phone) {
        getParamDao().saveOrUpdate(paramDao.PHONE, phone);
        this.phone = phone;
    }

    public String getMerchLevel() {
        if (merchLevel == null) {
            merchLevel = getParamDao().getByName(paramDao.MERCHLEVEL);
        }
        return merchLevel;
    }

    public void setMerchLevel(String merchLevel) {
        getParamDao().saveOrUpdate(paramDao.MERCHLEVEL, merchLevel);
        this.merchLevel = merchLevel;
    }

    public void setParamDao(ParamDao paramDao) {
        this.paramDao = paramDao;
    }

    public String getCardInfo() {
        if (cardInfo == null) {
            cardInfo = getParamDao().getByName(paramDao.CARDINFO);
        }
        return cardInfo;
    }

    public void setCardInfo(String cardInfo) {
        getParamDao().saveOrUpdate(paramDao.CARDINFO, cardInfo);
        this.cardInfo = cardInfo;
    }

    public GetMerchInfoResult.DataBean getMerchInfoBean() {
        merchInfoBean = (GetMerchInfoResult.DataBean) new Gson().fromJson(getMerchInfo(), new TypeToken<GetMerchInfoResult.DataBean>() {
        }.getType());
        return merchInfoBean;
    }

    public void setMerchInfoBean(GetMerchInfoResult.DataBean merchInfoBean) {
        this.merchInfoBean = merchInfoBean;
    }

    public GetCardInfoResult.DataBean getCardInfoBean() {
        cardInfoBean = (GetCardInfoResult.DataBean) new Gson().fromJson(getCardInfo(), new TypeToken<GetCardInfoResult.DataBean>() {
        }.getType());
        return cardInfoBean;
    }

    public void setCardInfoBean(GetCardInfoResult.DataBean cardInfoBean) {
        this.cardInfoBean = cardInfoBean;
    }

    public String getAcctInfo() {
        if (acctInfo == null) {
            acctInfo = getParamDao().getByName(paramDao.ACCTINFO);
        }
        return acctInfo;
    }

    public void setAcctInfo(String acctInfo) {
        getParamDao().saveOrUpdate(paramDao.ACCTINFO, acctInfo);
        this.acctInfo = acctInfo;
    }

    public GetAcctInfoResult.DataBeanX getAcctInfoBean() {
        acctInfoBean = (GetAcctInfoResult.DataBeanX) new Gson().fromJson(getAcctInfo(), new TypeToken<GetAcctInfoResult.DataBeanX>() {
        }.getType());
        return acctInfoBean;
    }

    public void setAcctInfoBean(GetAcctInfoResult.DataBeanX acctInfoBean) {
        this.acctInfoBean = acctInfoBean;
    }

    public GetMposIncomeResult.DataBean.MposApplyRecordBean getMposApplyBean() {
        Gson gson = new Gson();
        mposApplyBean = gson.fromJson(getMposApplyInfo(),
                new TypeToken<GetMposIncomeResult.DataBean.MposApplyRecordBean>() {
                }.getType());
        return mposApplyBean;
    }

    public void setMposApplyBean(GetMposIncomeResult.DataBean.MposApplyRecordBean mposApplyBean) {
        this.mposApplyBean = mposApplyBean;
    }

    public String getMposApplyInfo() {
        if (mposApplyInfo == null) {
            mposApplyInfo = getParamDao().getByName(paramDao.MPOSAPPLYINFO);
        }
        return mposApplyInfo;
    }

    public void setMposApplyInfo(String mposApplyInfo) {
        getParamDao().saveOrUpdate(paramDao.MPOSAPPLYINFO, mposApplyInfo);
        this.mposApplyInfo = mposApplyInfo;
    }

    public String getCardList() {
        if (cardList == null) {
            cardList = getParamDao().getByName(paramDao.CARDLIST);
        }
        return cardList;
    }

    public void setCardList(String cardList) {
        getParamDao().saveOrUpdate(paramDao.CARDLIST, cardList);
        this.cardList = cardList;
    }

    public List<GetCardInfoResult.DataBean> getCardListBean() {
        Gson gson = new Gson();
        cardListBean = gson.fromJson(getCardList(),
                new TypeToken<List<GetCardInfoResult.DataBean>>() {
                }.getType());
        return cardListBean;
    }

    public void setCardListBean(List<GetCardInfoResult.DataBean> cardListBean) {
        this.cardListBean = cardListBean;
    }

    public String getMposFeeInfo() {
        if (mposFeeInfo == null) {
            mposFeeInfo = getParamDao().getByName(paramDao.MPOSFEEINFO);
        }
        return mposFeeInfo;
    }

    public void setMposFeeInfo(String mposFeeInfo) {
        getParamDao().saveOrUpdate(paramDao.MPOSFEEINFO, mposFeeInfo);
        this.mposFeeInfo = mposFeeInfo;
    }

    public GetPosChanlFeeResult.DataBean getMposFeeBean() {
        Gson gson = new Gson();
        mposFeeBean = gson.fromJson(getMposFeeInfo(),
                new TypeToken<GetPosChanlFeeResult.DataBean>() {
                }.getType());
        return mposFeeBean;
    }

    public void setMposFeeBean(GetPosChanlFeeResult.DataBean mposFeeBean) {
        this.mposFeeBean = mposFeeBean;
    }

    public String getMposMerchInfo() {
        if (mposMerchInfo == null) {
            mposMerchInfo = getParamDao().getByName(paramDao.MPOSMERCHINFO);
        }
        return mposMerchInfo;
    }

    public void setMposMerchInfo(String mposMerchInfo) {
        getParamDao().saveOrUpdate(paramDao.MPOSMERCHINFO, mposMerchInfo);
        this.mposMerchInfo = mposMerchInfo;
    }

    public GetChannelMerchResult.DataBean getMposMerchBean() {
        Gson gson = new Gson();
        mposMerchBean = gson.fromJson(getMposMerchInfo(),
                new TypeToken<GetChannelMerchResult.DataBean>() {
                }.getType());
        return mposMerchBean;
    }

    public void setMposMerchBean(GetChannelMerchResult.DataBean mposMerchBean) {
        this.mposMerchBean = mposMerchBean;
    }

    public String getUrlInfo() {
        if (urlInfo == null) {
            urlInfo = getParamDao().getByName(paramDao.URLINFO);
        }
        return urlInfo;
    }

    public void setUrlInfo(String urlInfo) {
        getParamDao().saveOrUpdate(paramDao.URLINFO, urlInfo);
        this.urlInfo = urlInfo;
    }

    public List<GetUrlResult.DataBean> getUrlBean() {
        Gson gson = new Gson();
        urlBean = gson.fromJson(getUrlInfo(),
                new TypeToken<List<GetUrlResult.DataBean>>() {
                }.getType());
        return urlBean;
    }

    public void setUrlBean(List<GetUrlResult.DataBean> urlBean) {
        this.urlBean = urlBean;
    }

    public String getAgentFlag() {
        if (agentFlag == null) {
            agentFlag = getParamDao().getByName(paramDao.AGENTFLAG);
        }
        return agentFlag;
    }

    public void setAgentFlag(String agentFlag) {
        getParamDao().saveOrUpdate(paramDao.AGENTFLAG, agentFlag);
        this.agentFlag = agentFlag;
    }

    public String getPlanMinAmt() {
        if (planMinAmt == null) {
            planMinAmt = getParamDao().getByName(paramDao.PLANMINAMT);
        }
        return planMinAmt;
    }

    public void setPlanMinAmt(String planMinAmt) {
        getParamDao().saveOrUpdate(paramDao.PLANMINAMT, planMinAmt);
        this.planMinAmt = planMinAmt;
    }

    public String getIsAgent() {
        if (isAgent == null) {
            isAgent = getParamDao().getByName(paramDao.ISAGENT);
        }
        return isAgent;
    }

    public void setIsAgent(String isAgent) {
        getParamDao().saveOrUpdate(paramDao.ISAGENT, isAgent);
        this.isAgent = isAgent;
    }
    public String getFiltersource() {
        return filtersource;
    }

    public void setFiltersource(String filtersource) {
        this.filtersource = filtersource;
    }

    public FilterSource getFilterSource() {
        filterSource = (FilterSource) new Gson().fromJson(getFiltersource(), new TypeToken<FilterSource>(){}.getType());
        return filterSource;
    }

    public void setFilterSource(FilterSource filterSource) {
        this.filterSource = filterSource;
    }

    public String getLeveName() {
        if (leveName == null) {
            leveName = getParamDao().getByName(paramDao.LEVENAME);
        }
        return leveName;
    }

    public void setLeveName(String leveName) {
        getParamDao().saveOrUpdate(paramDao.LEVENAME, leveName);
        this.leveName = leveName;
    }

    public String getAgentLevel() {
        if (agentLevel == null) {
            agentLevel = getParamDao().getByName(paramDao.AGENTLEVEL);
        }
        return agentLevel;
    }

    public void setAgentLevel(String agentLevel) {
        getParamDao().saveOrUpdate(paramDao.AGENTLEVEL, agentLevel);
        this.agentLevel = agentLevel;
    }

    public String getUsedFlag() {
        if (usedFlag == null) {
            usedFlag = getParamDao().getByName(paramDao.USEDFLAG);
        }
        return usedFlag;
    }

    public void setUsedFlag(String usedFlag) {
        getParamDao().saveOrUpdate(paramDao.USEDFLAG, usedFlag);
        this.usedFlag = usedFlag;
    }

    public String getShopFlag() {
        if (shopFlag == null) {
            shopFlag = getParamDao().getByName(paramDao.SHOPFLAG);
        }
        return shopFlag;
    }

    public void setShopFlag(String shopFlag) {
        getParamDao().saveOrUpdate(paramDao.SHOPFLAG, shopFlag);
        this.shopFlag = shopFlag;
    }

    public String getDeviceNum() {
        if (deviceNum == null) {
            deviceNum = getParamDao().getByName(paramDao.DEVICENUM);
        }
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        getParamDao().saveOrUpdate(paramDao.DEVICENUM, deviceNum);
        this.deviceNum = deviceNum;
    }

    public String getTermId() {
        if (termId == null) {
            termId = getParamDao().getByName(paramDao.TERMID);
        }
        return termId;
    }

    public void setTermId(String termId) {
        getParamDao().saveOrUpdate(paramDao.TERMID, termId);
        this.termId = termId;
    }

    public String getCity() {
        if (city == null) {
            city = getParamDao().getByName(paramDao.CITY);
        }
        return city;
    }

    public void setCity(String city) {
        getParamDao().saveOrUpdate(paramDao.CITY, city);
        this.city = city;
    }

    public String getLatitude() {
        if (latitude == null) {
            latitude = getParamDao().getByName(paramDao.LATITUDE);
        }
        return latitude;
    }

    public void setLatitude(String latitude) {
        getParamDao().saveOrUpdate(paramDao.LATITUDE, latitude);
        this.latitude = latitude;
    }

    public String getLongitude() {
        if (longitude == null) {
            longitude = getParamDao().getByName(paramDao.LONGITUDE);
        }
        return longitude;
    }

    public void setLongitude(String longitude) {
        getParamDao().saveOrUpdate(paramDao.LONGITUDE, longitude);
        this.longitude = longitude;
    }

    public String getServicePhone() {
        if (servicePhone == null) {
            servicePhone = getParamDao().getByName(paramDao.SERVICEPHONE);
        }
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        getParamDao().saveOrUpdate(paramDao.SERVICEPHONE,servicePhone);
        this.servicePhone = servicePhone;
    }

    public String getMerchlevelname() {
        if (merchlevelname == null) {
            merchlevelname = getParamDao().getByName(paramDao.MERCHLEVELNAME);
        }
        return merchlevelname;
    }

    public void setMerchlevelname(String merchlevelname) {
        getParamDao().saveOrUpdate(paramDao.MERCHLEVELNAME,merchlevelname);
        this.merchlevelname = merchlevelname;
    }

    public String getMerchlevelname2() {
        if (merchlevelname2 == null) {
            merchlevelname2 = getParamDao().getByName(paramDao.MERCHLEVELNAME2);
        }
        return merchlevelname2;
    }

    public void setMerchlevelname2(String merchlevelname2) {
        getParamDao().saveOrUpdate(paramDao.MERCHLEVELNAME2,merchlevelname2);
        this.merchlevelname2 = merchlevelname2;
    }

    public String getBranchlevelname() {
        if (branchlevelname == null) {
            branchlevelname = getParamDao().getByName(paramDao.BRANCHLEVELNAME);
        }
        return branchlevelname;
    }

    public void setBranchlevelname(String branchlevelname) {
        getParamDao().saveOrUpdate(paramDao.BRANCHLEVELNAME,branchlevelname);
        this.branchlevelname = branchlevelname;
    }

    public String getShoplevelname() {
        if (shoplevelname == null) {
            shoplevelname = getParamDao().getByName(paramDao.SHOPLEVELNAME);
        }
        return shoplevelname;
    }

    public void setShoplevelname(String shoplevelname) {
        getParamDao().saveOrUpdate(paramDao.SHOPLEVELNAME,shoplevelname);
        this.shoplevelname = shoplevelname;
    }

    public String getAgentlevelname() {
        if (agentlevelname == null) {
            agentlevelname = getParamDao().getByName(paramDao.AGENTLEVELNAME);
        }
        return agentlevelname;
    }

    public void setAgentlevelname(String agentlevelname) {
        getParamDao().saveOrUpdate(paramDao.AGENTLEVELNAME,agentlevelname);
        this.agentlevelname = agentlevelname;
    }
}