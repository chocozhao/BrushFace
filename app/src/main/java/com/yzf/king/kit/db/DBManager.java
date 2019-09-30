package com.yzf.king.kit.db;

import android.content.Context;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * ClaseName：DBManager
 * Description：数据库管理类
 * Author：JensenWei
 * QQ: 2188307188
 * Createtime：2017/6/27 15:07
 * Modified By：
 * Fixtime：2017/6/27 15:07
 * FixDescription：
 */

public class DBManager {
    private final static String dbName = "AppDb";
    private static DBManager mInstance;
    private MySQLiteOpenHelper openHelper;
    private Context context;
    private DaoSession daoSession;
    private DaoMaster daoMaster;

    public static void initialize(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
    }

    public DBManager(Context context) {
        this.context = context;
        openHelper = new MySQLiteOpenHelper(context, dbName, null);
        daoMaster = new DaoMaster(openHelper.getWritableDb());
        daoSession = daoMaster.newSession();
        //打印日志
        QueryBuilder.LOG_SQL = false;
        QueryBuilder.LOG_VALUES = false;
    }

    /**
     * 获取单例引用
     *
     * @param
     * @return
     */
    public static DBManager getInstance() {
        return mInstance;
    }

    /**
     * 获取session对象
     *
     * @return
     */
    public DaoSession getDaoSession() {
        return daoSession;
    }
}
