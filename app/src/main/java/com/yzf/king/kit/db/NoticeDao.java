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

public class NoticeDao {

    /**
     * 插入或者更新数据
     *
     * @param tNotice
     */
    public void saveOrUpdate(TNotice tNotice) {
        try {
            TNoticeDao tnoticedao = DBManager.getInstance().getDaoSession().getTNoticeDao();
            tnoticedao.insertOrReplace(tNotice);
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
    public void saveOrUpdate(List<TNotice> list) {
        try {
            TNoticeDao tNoticeDao = DBManager.getInstance().getDaoSession().getTNoticeDao();
            tNoticeDao.insertOrReplaceInTx(list);
        } catch (Exception e) {
            Logger.e(e.toString());
            e.printStackTrace();
        }
    }

    public List<TNotice> getAll() {
        List<TNotice> list = new ArrayList<>();
        try {
            TNoticeDao tNoticeDao = DBManager.getInstance().getDaoSession().getTNoticeDao();
            list = tNoticeDao.queryBuilder().where(TNoticeDao.Properties.MerchId.eq(AppUser.getInstance().getMerchId())).orderDesc(TNoticeDao.Properties.Id).list();
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
    public List<TNotice> getByMaxId(long id) {
        List<TNotice> list = new ArrayList<>();
        try {
            TNoticeDao tNoticeDao = DBManager.getInstance().getDaoSession().getTNoticeDao();
            if (id > 0) {
                list = tNoticeDao.queryBuilder().where(TNoticeDao.Properties.Id.lt(id), TNoticeDao.Properties.MerchId.eq(AppUser.getInstance().getMerchId())).limit(10).orderDesc(TNoticeDao.Properties.Id).list();
            } else {
                list = tNoticeDao.queryBuilder().where(TNoticeDao.Properties.MerchId.eq(AppUser.getInstance().getMerchId())).limit(10).orderDesc(TNoticeDao.Properties.Id).list();
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
    public List<TNotice> getByMaxId(long id, int limit) {
        List<TNotice> list = new ArrayList<>();
        try {
            TNoticeDao tNoticeDao = DBManager.getInstance().getDaoSession().getTNoticeDao();
            if (id > 0) {
                list = tNoticeDao.queryBuilder().where(TNoticeDao.Properties.Id.lt(id), TNoticeDao.Properties.MerchId.eq(AppUser.getInstance().getMerchId())).limit(limit).orderDesc(TNoticeDao.Properties.Id).list();
            } else {
                list = tNoticeDao.queryBuilder().where(TNoticeDao.Properties.MerchId.eq(AppUser.getInstance().getMerchId())).limit(limit).orderDesc(TNoticeDao.Properties.Id).list();
            }
        } catch (Exception e) {
            Logger.e(e.toString());
            e.printStackTrace();
        }
        return list;
    }

    public TNotice get(TNotice tNotice) {
        TNoticeDao tNoticeDao = DBManager.getInstance().getDaoSession().getTNoticeDao();
        TNotice notice = tNoticeDao.queryBuilder().where(TNoticeDao.Properties.MerchId.eq(AppUser.getInstance().getMerchId()), TNoticeDao.Properties.NoticeId.eq(Integer.valueOf(tNotice.getNoticeId()))).unique();
        return notice;
    }


}
