package com.yzf.king.kit.db;

import cn.droidlover.xdroidmvp.log.Logger;

public class ParamDao {
    public final String MERCHID = "merchId";
    public final String MERCHINFO = "merchInfo";
    public final String MERCHNAME = "merchName";
    public final String PASSWORD = "password";
    public final String PHONE = "phone";
    public final String STATUS = "status";
    public final String MERCHLEVEL = "merchLevel";
    public final String TOKEN = "token";
    public final String MINAMT = "minAmt";
    public final String MAXAMT = "maxAmt";
    public final String ORDERAMT = "orderAmt";
    public final String ORDERID = "orderId";
    public final String ZMSCORE = "zmScore";
    public final String CARDINFO = "cardInfo";
    public final String ACCTINFO = "acctInfo";
    public final String MPOSAPPLYINFO = "mposApplyInfo";
    public final String CARDLIST = "cardList";
    public final String MPOSFEEINFO = "mposFeeInfo";
    public final String MPOSMERCHINFO = "mposMerchInfo";
    public final String URLINFO = "urlInfo";
    public final String AGENTFLAG = "agentFlag";
    public final String PLANMINAMT = "planMinAmt";
    public final String ISAGENT = "isAgent";
    public final String LEVENAME = "leveName";
    public final String AGENTLEVEL = "agentLevel";
    public final String USEDFLAG = "usedFlag";
    public final String SHOPFLAG = "shopFlag";
    public final String DEVICENUM = "deviceNum";
    public final String TERMID = "TermId";
    public final String CITY = "city";
    public final String LATITUDE = "latitude";
    public final String LONGITUDE = "longitude";
    public final String SERVICEPHONE = "servicePhone";
    public final String MERCHLEVELNAME = "merchlevelname";
    public final String MERCHLEVELNAME2 = "merchlevelname2";
    public final String BRANCHLEVELNAME = "branchlevelname";
    public final String SHOPLEVELNAME = "shoplevelname";
    public final String AGENTLEVELNAME = "agentlevelname";

    /**
     * 插入或者更新数据
     *
     * @param paramName
     * @param paramValue
     */
    public void saveOrUpdate(String paramName, String paramValue) {
        try {
            Tparam tparam = new Tparam();
            tparam.setKey(paramName);
            tparam.setValue(paramValue);
            TparamDao tparamDao = DBManager.getInstance().getDaoSession().getTparamDao();
            tparamDao.insertOrReplace(tparam);
        } catch (Exception e) {
            Logger.e(e.toString());
            e.printStackTrace();
        }
    }

    /**
     * 检查数据是否存在
     *
     * @param paramName
     * @return
     */
    public boolean exist(String paramName) {
        try {
            TparamDao tparamDao = DBManager.getInstance().getDaoSession().getTparamDao();
            Tparam tparam = tparamDao.queryBuilder().where(TparamDao.Properties.Key.eq(paramName)).unique();
            if (tparam != null) {
                return true;
            }
        } catch (Exception e) {
            Logger.e(e.toString());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据名称获取数据
     *
     * @param paramName
     * @return
     */
    public String getByName(String paramName) {
        try {
            TparamDao tparamDao = DBManager.getInstance().getDaoSession().getTparamDao();
            Tparam tparam = tparamDao.queryBuilder().where(TparamDao.Properties.Key.eq(paramName)).unique();
            if (tparam != null) {
                return tparam.getValue();
            }
        } catch (Exception e) {
            Logger.e(e.toString());
            e.printStackTrace();
        }
        return null;
    }

    public void initDbBeforeLogin() {
        if (!exist(MERCHID)) {
            saveOrUpdate(MERCHID, "");
        }
        if (!exist(PHONE)) {
            saveOrUpdate("phone", "");
        }
        saveOrUpdate(ORDERID, "");
        saveOrUpdate(ORDERAMT, "");
        saveOrUpdate(TOKEN, "");
        saveOrUpdate(MINAMT, "");
        saveOrUpdate(MAXAMT, "");
        saveOrUpdate(MERCHNAME, "");
        saveOrUpdate(ORDERAMT, "");
        saveOrUpdate(ZMSCORE, "");
        saveOrUpdate(MERCHINFO, "");
        saveOrUpdate(MERCHLEVEL, "");
        saveOrUpdate(STATUS, "");
        saveOrUpdate(CARDINFO, "");
        saveOrUpdate(ACCTINFO, "");
        saveOrUpdate(MPOSAPPLYINFO, "");
        saveOrUpdate(CARDLIST, "");
        saveOrUpdate(MPOSFEEINFO, "");
        saveOrUpdate(MPOSMERCHINFO, "");
        saveOrUpdate(URLINFO, "");
        saveOrUpdate(AGENTFLAG, "");
        saveOrUpdate(PLANMINAMT, "");
        saveOrUpdate(ISAGENT, "");
        saveOrUpdate(LEVENAME, "");
        saveOrUpdate(AGENTLEVEL, "");
        saveOrUpdate(USEDFLAG, "");
        saveOrUpdate(SHOPFLAG, "");
        saveOrUpdate(DEVICENUM, "");
        saveOrUpdate(TERMID, "");
        saveOrUpdate(CITY, "");
        saveOrUpdate(LATITUDE, "");
        saveOrUpdate(LONGITUDE, "");
        saveOrUpdate(SERVICEPHONE, "");
        saveOrUpdate(MERCHLEVELNAME, "");
        saveOrUpdate(MERCHLEVELNAME2, "");
        saveOrUpdate(BRANCHLEVELNAME, "");
        saveOrUpdate(SHOPLEVELNAME, "");
        saveOrUpdate(AGENTLEVELNAME, "");
    }

    public void resetDbTradeInfo() {
        saveOrUpdate(ORDERID, "");
        saveOrUpdate(ORDERAMT, "");
        saveOrUpdate(MPOSAPPLYINFO, "");
        saveOrUpdate(MPOSFEEINFO, "");
        saveOrUpdate(MPOSMERCHINFO, "");
    }
}