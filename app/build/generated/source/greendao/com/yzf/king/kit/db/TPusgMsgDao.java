package com.yzf.king.kit.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TPUSG_MSG".
*/
public class TPusgMsgDao extends AbstractDao<TPusgMsg, Long> {

    public static final String TABLENAME = "TPUSG_MSG";

    /**
     * Properties of entity TPusgMsg.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property MerchId = new Property(1, String.class, "merchId", false, "MERCH_ID");
        public final static Property MsgTitle = new Property(2, String.class, "msgTitle", false, "MSG_TITLE");
        public final static Property MsgContent = new Property(3, String.class, "msgContent", false, "MSG_CONTENT");
        public final static Property MsgType = new Property(4, String.class, "msgType", false, "MSG_TYPE");
        public final static Property MsgTime = new Property(5, String.class, "msgTime", false, "MSG_TIME");
        public final static Property MsgStatus = new Property(6, String.class, "msgStatus", false, "MSG_STATUS");
        public final static Property OrderId = new Property(7, String.class, "orderId", false, "ORDER_ID");
        public final static Property OrderAmt = new Property(8, String.class, "orderAmt", false, "ORDER_AMT");
        public final static Property OrderType = new Property(9, String.class, "orderType", false, "ORDER_TYPE");
        public final static Property OrderTime = new Property(10, String.class, "orderTime", false, "ORDER_TIME");
        public final static Property OrderStatus = new Property(11, String.class, "orderStatus", false, "ORDER_STATUS");
        public final static Property OrderDesc = new Property(12, String.class, "orderDesc", false, "ORDER_DESC");
    }


    public TPusgMsgDao(DaoConfig config) {
        super(config);
    }
    
    public TPusgMsgDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TPUSG_MSG\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"MERCH_ID\" TEXT," + // 1: merchId
                "\"MSG_TITLE\" TEXT," + // 2: msgTitle
                "\"MSG_CONTENT\" TEXT," + // 3: msgContent
                "\"MSG_TYPE\" TEXT," + // 4: msgType
                "\"MSG_TIME\" TEXT," + // 5: msgTime
                "\"MSG_STATUS\" TEXT," + // 6: msgStatus
                "\"ORDER_ID\" TEXT," + // 7: orderId
                "\"ORDER_AMT\" TEXT," + // 8: orderAmt
                "\"ORDER_TYPE\" TEXT," + // 9: orderType
                "\"ORDER_TIME\" TEXT," + // 10: orderTime
                "\"ORDER_STATUS\" TEXT," + // 11: orderStatus
                "\"ORDER_DESC\" TEXT);"); // 12: orderDesc
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TPUSG_MSG\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, TPusgMsg entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String merchId = entity.getMerchId();
        if (merchId != null) {
            stmt.bindString(2, merchId);
        }
 
        String msgTitle = entity.getMsgTitle();
        if (msgTitle != null) {
            stmt.bindString(3, msgTitle);
        }
 
        String msgContent = entity.getMsgContent();
        if (msgContent != null) {
            stmt.bindString(4, msgContent);
        }
 
        String msgType = entity.getMsgType();
        if (msgType != null) {
            stmt.bindString(5, msgType);
        }
 
        String msgTime = entity.getMsgTime();
        if (msgTime != null) {
            stmt.bindString(6, msgTime);
        }
 
        String msgStatus = entity.getMsgStatus();
        if (msgStatus != null) {
            stmt.bindString(7, msgStatus);
        }
 
        String orderId = entity.getOrderId();
        if (orderId != null) {
            stmt.bindString(8, orderId);
        }
 
        String orderAmt = entity.getOrderAmt();
        if (orderAmt != null) {
            stmt.bindString(9, orderAmt);
        }
 
        String orderType = entity.getOrderType();
        if (orderType != null) {
            stmt.bindString(10, orderType);
        }
 
        String orderTime = entity.getOrderTime();
        if (orderTime != null) {
            stmt.bindString(11, orderTime);
        }
 
        String orderStatus = entity.getOrderStatus();
        if (orderStatus != null) {
            stmt.bindString(12, orderStatus);
        }
 
        String orderDesc = entity.getOrderDesc();
        if (orderDesc != null) {
            stmt.bindString(13, orderDesc);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, TPusgMsg entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String merchId = entity.getMerchId();
        if (merchId != null) {
            stmt.bindString(2, merchId);
        }
 
        String msgTitle = entity.getMsgTitle();
        if (msgTitle != null) {
            stmt.bindString(3, msgTitle);
        }
 
        String msgContent = entity.getMsgContent();
        if (msgContent != null) {
            stmt.bindString(4, msgContent);
        }
 
        String msgType = entity.getMsgType();
        if (msgType != null) {
            stmt.bindString(5, msgType);
        }
 
        String msgTime = entity.getMsgTime();
        if (msgTime != null) {
            stmt.bindString(6, msgTime);
        }
 
        String msgStatus = entity.getMsgStatus();
        if (msgStatus != null) {
            stmt.bindString(7, msgStatus);
        }
 
        String orderId = entity.getOrderId();
        if (orderId != null) {
            stmt.bindString(8, orderId);
        }
 
        String orderAmt = entity.getOrderAmt();
        if (orderAmt != null) {
            stmt.bindString(9, orderAmt);
        }
 
        String orderType = entity.getOrderType();
        if (orderType != null) {
            stmt.bindString(10, orderType);
        }
 
        String orderTime = entity.getOrderTime();
        if (orderTime != null) {
            stmt.bindString(11, orderTime);
        }
 
        String orderStatus = entity.getOrderStatus();
        if (orderStatus != null) {
            stmt.bindString(12, orderStatus);
        }
 
        String orderDesc = entity.getOrderDesc();
        if (orderDesc != null) {
            stmt.bindString(13, orderDesc);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public TPusgMsg readEntity(Cursor cursor, int offset) {
        TPusgMsg entity = new TPusgMsg( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // merchId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // msgTitle
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // msgContent
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // msgType
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // msgTime
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // msgStatus
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // orderId
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // orderAmt
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // orderType
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // orderTime
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // orderStatus
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12) // orderDesc
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, TPusgMsg entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMerchId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setMsgTitle(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setMsgContent(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setMsgType(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setMsgTime(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setMsgStatus(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setOrderId(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setOrderAmt(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setOrderType(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setOrderTime(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setOrderStatus(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setOrderDesc(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(TPusgMsg entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(TPusgMsg entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(TPusgMsg entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
