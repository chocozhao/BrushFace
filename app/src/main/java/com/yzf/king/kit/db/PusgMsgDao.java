package com.yzf.king.kit.db;

import com.yzf.king.kit.AppUser;

import java.util.ArrayList;
import java.util.List;

import cn.droidlover.xdroidmvp.log.Logger;

/**
 * ClaseName：PusgMsgDao
 * Description：
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2018/5/19 14:24
 * Modified By：
 * Fixtime：2018/5/19 14:24
 * FixDescription：
 */

public class PusgMsgDao {

    /**
     * 插入或者更新数据
     *
     * @param tPusgMsg
     */
    public void saveOrUpdate(TPusgMsg tPusgMsg) {
        try {
            TPusgMsgDao tPusgMsgDao = DBManager.getInstance().getDaoSession().getTPusgMsgDao();
            tPusgMsgDao.insertOrReplace(tPusgMsg);
        } catch (Exception e) {
            Logger.e(e.toString());
            e.printStackTrace();
        }
    }

    /**
     * 插入或者更新数据
     *
     * @param list
     */
    public void saveOrUpdate(List<TPusgMsg> list) {
        try {
            TPusgMsgDao tPusgMsgDao = DBManager.getInstance().getDaoSession().getTPusgMsgDao();
            tPusgMsgDao.insertOrReplaceInTx(list);
        } catch (Exception e) {
            Logger.e(e.toString());
            e.printStackTrace();
        }
    }

    public List<TPusgMsg> getAll() {
        List<TPusgMsg> list = new ArrayList<>();
        try {
            TPusgMsgDao tPusgMsgDao = DBManager.getInstance().getDaoSession().getTPusgMsgDao();
            list = tPusgMsgDao.queryBuilder().where(TPusgMsgDao.Properties.MerchId.eq(AppUser.getInstance().getMerchId())).orderDesc(TPusgMsgDao.Properties.Id).list();
        } catch (Exception e) {
            Logger.e(e.toString());
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取小于给定id的十条数据
     *
     * @param id
     * @return
     */
    public List<TPusgMsg> getByMaxId(long id) {
        List<TPusgMsg> list = new ArrayList<>();
        try {
            TPusgMsgDao tPusgMsgDao = DBManager.getInstance().getDaoSession().getTPusgMsgDao();
            if (id > 0) {
                list = tPusgMsgDao.queryBuilder().where(TPusgMsgDao.Properties.Id.lt(id), TPusgMsgDao.Properties.MerchId.eq(AppUser.getInstance().getMerchId())).limit(10).orderDesc(TPusgMsgDao.Properties.Id).list();
            } else {
                list = tPusgMsgDao.queryBuilder().where(TPusgMsgDao.Properties.MerchId.eq(AppUser.getInstance().getMerchId())).limit(10).orderDesc(TPusgMsgDao.Properties.Id).list();
            }
        } catch (Exception e) {
            Logger.e(e.toString());
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取小于给定id的limit条数据
     *
     * @param id
     * @param limit
     * @return
     */
    public List<TPusgMsg> getByMaxId(long id, int limit) {
        List<TPusgMsg> list = new ArrayList<>();
        try {
            TPusgMsgDao tPusgMsgDao = DBManager.getInstance().getDaoSession().getTPusgMsgDao();
            if (id > 0) {
                list = tPusgMsgDao.queryBuilder().where(TPusgMsgDao.Properties.Id.lt(id), TPusgMsgDao.Properties.MerchId.eq(AppUser.getInstance().getMerchId())).limit(limit).orderDesc(TPusgMsgDao.Properties.Id).list();
            } else {
                list = tPusgMsgDao.queryBuilder().where(TPusgMsgDao.Properties.MerchId.eq(AppUser.getInstance().getMerchId())).limit(limit).orderDesc(TPusgMsgDao.Properties.Id).list();
            }
        } catch (Exception e) {
            Logger.e(e.toString());
            e.printStackTrace();
        }
        return list;
    }


}
