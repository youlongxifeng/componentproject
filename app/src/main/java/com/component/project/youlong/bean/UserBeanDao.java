package com.component.project.youlong.bean;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.component.project.youlong.UserBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "UserBean".
*/
public class UserBeanDao extends AbstractDao<UserBean, Long> {

    public static final String TABLENAME = "UserBean";

    /**
     * Properties of entity UserBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_id");
        public final static Property Userid = new Property(1, String.class, "userid", false, "userid");
        public final static Property AccessToken = new Property(2, String.class, "accessToken", false, "token");
    }


    public UserBeanDao(DaoConfig config) {
        super(config);
    }
    
    public UserBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"UserBean\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: _id
                "\"userid\" TEXT NOT NULL ," + // 1: userid
                "\"token\" TEXT NOT NULL );"); // 2: accessToken
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_UserBean__id_DESC ON \"UserBean\"" +
                " (\"_id\" DESC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"UserBean\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserBean entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
        stmt.bindString(2, entity.getUserid());
        stmt.bindString(3, entity.getAccessToken());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserBean entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
        stmt.bindString(2, entity.getUserid());
        stmt.bindString(3, entity.getAccessToken());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public UserBean readEntity(Cursor cursor, int offset) {
        UserBean entity = new UserBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
            cursor.getString(offset + 1), // userid
            cursor.getString(offset + 2) // accessToken
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserBean entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserid(cursor.getString(offset + 1));
        entity.setAccessToken(cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UserBean entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UserBean entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserBean entity) {
        return entity.get_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
