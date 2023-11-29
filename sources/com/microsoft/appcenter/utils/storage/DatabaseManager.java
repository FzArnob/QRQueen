package com.microsoft.appcenter.utils.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteFullException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import com.microsoft.appcenter.utils.AppCenterLog;
import java.io.Closeable;
import java.util.Arrays;
import java.util.Set;

public class DatabaseManager implements Closeable {
    public static final long OPERATION_FAILED_FLAG = -1;
    public static final String PRIMARY_KEY = "oid";
    public static final String[] SELECT_PRIMARY_KEY = {PRIMARY_KEY};
    private final Context mContext;
    private final String mDatabase;
    private final String mDefaultTable;
    /* access modifiers changed from: private */
    public final Listener mListener;
    private SQLiteOpenHelper mSQLiteOpenHelper;
    private final ContentValues mSchema;

    public interface Listener {
        void onCreate(SQLiteDatabase sQLiteDatabase);

        void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2);
    }

    public DatabaseManager(Context context, String str, String str2, int i, ContentValues contentValues, String str3, Listener listener) {
        this.mContext = context;
        this.mDatabase = str;
        this.mDefaultTable = str2;
        this.mSchema = contentValues;
        this.mListener = listener;
        final String str4 = str3;
        this.mSQLiteOpenHelper = new SQLiteOpenHelper(context, str, (SQLiteDatabase.CursorFactory) null, i) {
            public void onCreate(SQLiteDatabase sQLiteDatabase) {
                sQLiteDatabase.execSQL(str4);
                DatabaseManager.this.mListener.onCreate(sQLiteDatabase);
            }

            public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
                DatabaseManager.this.mListener.onUpgrade(sQLiteDatabase, i, i2);
            }
        };
    }

    private static ContentValues buildValues(Cursor cursor, ContentValues contentValues) {
        ContentValues contentValues2 = new ContentValues();
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            if (!cursor.isNull(i)) {
                String columnName = cursor.getColumnName(i);
                if (columnName.equals(PRIMARY_KEY)) {
                    contentValues2.put(columnName, Long.valueOf(cursor.getLong(i)));
                } else {
                    Object obj = contentValues.get(columnName);
                    if (obj instanceof byte[]) {
                        contentValues2.put(columnName, cursor.getBlob(i));
                    } else if (obj instanceof Double) {
                        contentValues2.put(columnName, Double.valueOf(cursor.getDouble(i)));
                    } else if (obj instanceof Float) {
                        contentValues2.put(columnName, Float.valueOf(cursor.getFloat(i)));
                    } else if (obj instanceof Integer) {
                        contentValues2.put(columnName, Integer.valueOf(cursor.getInt(i)));
                    } else if (obj instanceof Long) {
                        contentValues2.put(columnName, Long.valueOf(cursor.getLong(i)));
                    } else if (obj instanceof Short) {
                        contentValues2.put(columnName, Short.valueOf(cursor.getShort(i)));
                    } else if (obj instanceof Boolean) {
                        boolean z = true;
                        if (cursor.getInt(i) != 1) {
                            z = false;
                        }
                        contentValues2.put(columnName, Boolean.valueOf(z));
                    } else {
                        contentValues2.put(columnName, cursor.getString(i));
                    }
                }
            }
        }
        return contentValues2;
    }

    public ContentValues buildValues(Cursor cursor) {
        return buildValues(cursor, this.mSchema);
    }

    public ContentValues nextValues(Cursor cursor) {
        try {
            if (cursor.moveToNext()) {
                return buildValues(cursor);
            }
            return null;
        } catch (RuntimeException e) {
            AppCenterLog.error("AppCenter", "Failed to get next cursor value: ", e);
            return null;
        }
    }

    public long put(ContentValues contentValues) throws SQLiteFullException {
        try {
            return getDatabase().insertOrThrow(this.mDefaultTable, (String) null, contentValues);
        } catch (SQLiteFullException e) {
            throw e;
        } catch (RuntimeException e2) {
            AppCenterLog.error("AppCenter", String.format("Failed to insert values (%s) to database %s.", new Object[]{contentValues.toString(), this.mDatabase}), e2);
            return -1;
        }
    }

    public void delete(long j) {
        delete(this.mDefaultTable, PRIMARY_KEY, Long.valueOf(j));
    }

    public ContentValues deleteTheOldestRecord(Set<String> set, String str, int i) {
        SQLiteQueryBuilder newSQLiteQueryBuilder = SQLiteUtils.newSQLiteQueryBuilder();
        newSQLiteQueryBuilder.appendWhere(str + " <= ?");
        set.add(PRIMARY_KEY);
        String[] strArr = {String.valueOf(i)};
        ContentValues nextValues = nextValues(getCursor(newSQLiteQueryBuilder, (String[]) set.toArray(new String[0]), strArr, str + " , " + PRIMARY_KEY));
        if (nextValues != null) {
            long longValue = nextValues.getAsLong(PRIMARY_KEY).longValue();
            delete(longValue);
            AppCenterLog.debug("AppCenter", "Deleted log id=" + longValue);
            return nextValues;
        }
        AppCenterLog.error("AppCenter", String.format("Failed to delete the oldest log from database %s.", new Object[]{this.mDatabase}));
        return null;
    }

    public int delete(String str, Object obj) {
        return delete(this.mDefaultTable, str, obj);
    }

    private int delete(String str, String str2, Object obj) {
        String[] strArr = {String.valueOf(obj)};
        try {
            SQLiteDatabase database = getDatabase();
            return database.delete(str, str2 + " = ?", strArr);
        } catch (RuntimeException e) {
            AppCenterLog.error("AppCenter", String.format("Failed to delete values that match condition=\"%s\" and values=\"%s\" from database %s.", new Object[]{str2 + " = ?", Arrays.toString(strArr), this.mDatabase}), e);
            return 0;
        }
    }

    public void clear() {
        try {
            getDatabase().delete(this.mDefaultTable, (String) null, (String[]) null);
        } catch (RuntimeException e) {
            AppCenterLog.error("AppCenter", "Failed to clear the table.", e);
        }
    }

    public void close() {
        try {
            this.mSQLiteOpenHelper.close();
        } catch (RuntimeException e) {
            AppCenterLog.error("AppCenter", "Failed to close the database.", e);
        }
    }

    public final long getRowCount() {
        try {
            return DatabaseUtils.queryNumEntries(getDatabase(), this.mDefaultTable);
        } catch (RuntimeException e) {
            AppCenterLog.error("AppCenter", "Failed to get row count of database.", e);
            return -1;
        }
    }

    public Cursor getCursor(SQLiteQueryBuilder sQLiteQueryBuilder, String[] strArr, String[] strArr2, String str) throws RuntimeException {
        return getCursor(this.mDefaultTable, sQLiteQueryBuilder, strArr, strArr2, str);
    }

    /* access modifiers changed from: package-private */
    public Cursor getCursor(String str, SQLiteQueryBuilder sQLiteQueryBuilder, String[] strArr, String[] strArr2, String str2) throws RuntimeException {
        if (sQLiteQueryBuilder == null) {
            sQLiteQueryBuilder = SQLiteUtils.newSQLiteQueryBuilder();
        }
        SQLiteQueryBuilder sQLiteQueryBuilder2 = sQLiteQueryBuilder;
        sQLiteQueryBuilder2.setTables(str);
        return sQLiteQueryBuilder2.query(getDatabase(), strArr, (String) null, strArr2, (String) null, (String) null, str2);
    }

    /* access modifiers changed from: package-private */
    public SQLiteDatabase getDatabase() {
        try {
            return this.mSQLiteOpenHelper.getWritableDatabase();
        } catch (RuntimeException e) {
            AppCenterLog.warn("AppCenter", "Failed to open database. Trying to delete database (may be corrupted).", e);
            if (this.mContext.deleteDatabase(this.mDatabase)) {
                AppCenterLog.info("AppCenter", "The database was successfully deleted.");
            } else {
                AppCenterLog.warn("AppCenter", "Failed to delete database.");
            }
            return this.mSQLiteOpenHelper.getWritableDatabase();
        }
    }

    /* access modifiers changed from: package-private */
    public void setSQLiteOpenHelper(SQLiteOpenHelper sQLiteOpenHelper) {
        this.mSQLiteOpenHelper.close();
        this.mSQLiteOpenHelper = sQLiteOpenHelper;
    }

    public boolean setMaxSize(long j) {
        try {
            SQLiteDatabase database = getDatabase();
            long maximumSize = database.setMaximumSize(j);
            long pageSize = database.getPageSize();
            long j2 = j / pageSize;
            if (j % pageSize != 0) {
                j2++;
            }
            if (maximumSize != j2 * pageSize) {
                AppCenterLog.error("AppCenter", "Could not change maximum database size to " + j + " bytes, current maximum size is " + maximumSize + " bytes.");
                return false;
            } else if (j == maximumSize) {
                AppCenterLog.info("AppCenter", "Changed maximum database size to " + maximumSize + " bytes.");
                return true;
            } else {
                AppCenterLog.info("AppCenter", "Changed maximum database size to " + maximumSize + " bytes (next multiple of page size).");
                return true;
            }
        } catch (RuntimeException e) {
            AppCenterLog.error("AppCenter", "Could not change maximum database size.", e);
            return false;
        }
    }

    public long getMaxSize() {
        try {
            return getDatabase().getMaximumSize();
        } catch (RuntimeException e) {
            AppCenterLog.error("AppCenter", "Could not get maximum database size.", e);
            return -1;
        }
    }

    public long getCurrentSize() {
        return this.mContext.getDatabasePath(this.mDatabase).length();
    }
}
