package com.microsoft.appcenter.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import com.microsoft.appcenter.Constants;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.utils.AppCenterLog;
import com.microsoft.appcenter.utils.crypto.CryptoUtils;
import com.microsoft.appcenter.utils.storage.DatabaseManager;
import com.microsoft.appcenter.utils.storage.FileManager;
import com.microsoft.appcenter.utils.storage.SQLiteUtils;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.json.JSONException;

public class DatabasePersistence extends Persistence {
    private static final String COLUMN_DATA_TYPE = "type";
    static final String COLUMN_GROUP = "persistence_group";
    static final String COLUMN_LOG = "log";
    static final String COLUMN_PRIORITY = "priority";
    static final String COLUMN_TARGET_KEY = "target_key";
    static final String COLUMN_TARGET_TOKEN = "target_token";
    static final String CREATE_LOGS_SQL = "CREATE TABLE IF NOT EXISTS `logs`(`oid` INTEGER PRIMARY KEY AUTOINCREMENT,`target_token` TEXT,`type` TEXT,`priority` INTEGER,`log` TEXT,`persistence_group` TEXT,`target_key` TEXT);";
    private static final String CREATE_PRIORITY_INDEX_LOGS = "CREATE INDEX `ix_logs_priority` ON logs (`priority`)";
    static final String DATABASE = "com.microsoft.appcenter.persistence";
    private static final String DROP_LOGS_SQL = "DROP TABLE `logs`";
    private static final String GET_SORT_ORDER = "priority DESC, oid";
    private static final String PAYLOAD_FILE_EXTENSION = ".json";
    private static final String PAYLOAD_LARGE_DIRECTORY = "/appcenter/database_large_payloads";
    static final int PAYLOAD_MAX_SIZE = 1992294;
    static final ContentValues SCHEMA = getContentValues("", "", "", "", "", 0);
    static final String TABLE = "logs";
    private static final int VERSION = 6;
    static final int VERSION_TIMESTAMP_COLUMN = 5;
    private final Context mContext;
    final DatabaseManager mDatabaseManager;
    private final File mLargePayloadDirectory;
    private long mLargePayloadsSize;
    final Set<Long> mPendingDbIdentifiers;
    final Map<String, List<Long>> mPendingDbIdentifiersGroups;

    public DatabasePersistence(Context context) {
        this(context, 6, SCHEMA);
    }

    DatabasePersistence(Context context, int i, ContentValues contentValues) {
        this.mContext = context;
        this.mPendingDbIdentifiersGroups = new HashMap();
        this.mPendingDbIdentifiers = new HashSet();
        this.mDatabaseManager = new DatabaseManager(context, DATABASE, TABLE, i, contentValues, CREATE_LOGS_SQL, new DatabaseManager.Listener() {
            public void onCreate(SQLiteDatabase sQLiteDatabase) {
                sQLiteDatabase.execSQL(DatabasePersistence.CREATE_PRIORITY_INDEX_LOGS);
            }

            public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
                sQLiteDatabase.execSQL(DatabasePersistence.DROP_LOGS_SQL);
                sQLiteDatabase.execSQL(DatabasePersistence.CREATE_LOGS_SQL);
                sQLiteDatabase.execSQL(DatabasePersistence.CREATE_PRIORITY_INDEX_LOGS);
            }
        });
        File file = new File(Constants.FILES_PATH + PAYLOAD_LARGE_DIRECTORY);
        this.mLargePayloadDirectory = file;
        file.mkdirs();
        this.mLargePayloadsSize = checkLargePayloadFilesAndCollectTheirSize();
    }

    private static ContentValues getContentValues(String str, String str2, String str3, String str4, String str5, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_GROUP, str);
        contentValues.put(COLUMN_LOG, str2);
        contentValues.put(COLUMN_TARGET_TOKEN, str3);
        contentValues.put("type", str4);
        contentValues.put(COLUMN_TARGET_KEY, str5);
        contentValues.put(COLUMN_PRIORITY, Integer.valueOf(i));
        return contentValues;
    }

    public boolean setMaxStorageSize(long j) {
        boolean maxSize = this.mDatabaseManager.setMaxSize(j);
        deleteLogsThatNotFitMaxSize();
        return maxSize;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:42|43|(2:45|83)(1:82)) */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        com.microsoft.appcenter.utils.AppCenterLog.debug("AppCenter", "Storage is full, trying to delete the oldest log that has the lowest priority which is lower or equal priority than the new log.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00d4, code lost:
        if (deleteTheOldestLog(r2) == -1) goto L_0x00d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00d6, code lost:
        r9 = -1L;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01ca, code lost:
        r0 = e;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:42:0x00cb */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x01ca A[ExcHandler: JSONException (e org.json.JSONException), Splitter:B:1:0x0008] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long putLog(com.microsoft.appcenter.ingestion.models.Log r19, java.lang.String r20, int r21) throws com.microsoft.appcenter.persistence.Persistence.PersistenceException {
        /*
            r18 = this;
            r1 = r18
            r0 = r19
            r2 = r21
            java.lang.String r3 = "AppCenter"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            r4.<init>()     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.String r5 = "Storing a log to the Persistence database for log type "
            r4.append(r5)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.String r5 = r19.getType()     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            r4.append(r5)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.String r5 = " with flags="
            r4.append(r5)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            r4.append(r2)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.String r4 = r4.toString()     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            com.microsoft.appcenter.utils.AppCenterLog.debug(r3, r4)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            com.microsoft.appcenter.ingestion.models.json.LogSerializer r4 = r18.getLogSerializer()     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.String r4 = r4.serializeLog(r0)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.String r5 = "UTF-8"
            byte[] r5 = r4.getBytes(r5)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            int r5 = r5.length     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            r6 = 1992294(0x1e6666, float:2.791799E-39)
            if (r5 < r6) goto L_0x003e
            r6 = 1
            goto L_0x003f
        L_0x003e:
            r6 = 0
        L_0x003f:
            boolean r8 = r0 instanceof com.microsoft.appcenter.ingestion.models.one.CommonSchemaLog     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            r9 = 0
            if (r8 == 0) goto L_0x006d
            if (r6 != 0) goto L_0x0065
            java.util.Set r8 = r19.getTransmissionTargetTokens()     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.Object r8 = r8.next()     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.String r10 = com.microsoft.appcenter.ingestion.models.one.PartAUtils.getTargetKey(r8)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            android.content.Context r11 = r1.mContext     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            com.microsoft.appcenter.utils.crypto.CryptoUtils r11 = com.microsoft.appcenter.utils.crypto.CryptoUtils.getInstance(r11)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.String r8 = r11.encrypt(r8)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            r12 = r8
            r14 = r10
            goto L_0x006f
        L_0x0065:
            com.microsoft.appcenter.persistence.Persistence$PersistenceException r0 = new com.microsoft.appcenter.persistence.Persistence$PersistenceException     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.String r2 = "Log is larger than 1992294 bytes, cannot send to OneCollector."
            r0.<init>(r2)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            throw r0     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
        L_0x006d:
            r12 = r9
            r14 = r12
        L_0x006f:
            com.microsoft.appcenter.utils.storage.DatabaseManager r8 = r1.mDatabaseManager     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            long r10 = r8.getMaxSize()     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            r16 = -1
            int r8 = (r10 > r16 ? 1 : (r10 == r16 ? 0 : -1))
            if (r8 == 0) goto L_0x01b9
            long r7 = (long) r5     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            int r15 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r15 <= 0) goto L_0x0195
            r13 = 0
            int r2 = com.microsoft.appcenter.Flags.getPersistenceFlag(r2, r13)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            if (r6 == 0) goto L_0x0089
            r5 = r9
            goto L_0x008a
        L_0x0089:
            r5 = r4
        L_0x008a:
            java.lang.String r13 = r19.getType()     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            r0 = r10
            r10 = r20
            r11 = r5
            r15 = r2
            android.content.ContentValues r5 = getContentValues(r10, r11, r12, r13, r14, r15)     // Catch:{ JSONException -> 0x0191, IOException -> 0x018d }
        L_0x0097:
            java.lang.String r10 = "Storage is full, trying to delete the oldest log that has the lowest priority which is lower or equal priority than the new log."
            if (r6 == 0) goto L_0x00bc
            long r11 = r18.getStoredDataSize()     // Catch:{ JSONException -> 0x0191, IOException -> 0x018d }
            long r11 = r11 + r7
            int r13 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r13 <= 0) goto L_0x00bc
            com.microsoft.appcenter.utils.AppCenterLog.debug(r3, r10)     // Catch:{ JSONException -> 0x0191, IOException -> 0x018d }
            r10 = r0
            r1 = r18
            long r12 = r1.deleteTheOldestLog(r2)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            int r0 = (r12 > r16 ? 1 : (r12 == r16 ? 0 : -1))
            if (r0 == 0) goto L_0x00b4
            r0 = r10
            goto L_0x0097
        L_0x00b4:
            com.microsoft.appcenter.persistence.Persistence$PersistenceException r0 = new com.microsoft.appcenter.persistence.Persistence$PersistenceException     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.String r2 = "Failed to clear space for new log record."
            r0.<init>(r2)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            throw r0     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
        L_0x00bc:
            r1 = r18
        L_0x00be:
            if (r9 != 0) goto L_0x00dc
            com.microsoft.appcenter.utils.storage.DatabaseManager r0 = r1.mDatabaseManager     // Catch:{ SQLiteFullException -> 0x00cb }
            long r7 = r0.put(r5)     // Catch:{ SQLiteFullException -> 0x00cb }
            java.lang.Long r9 = java.lang.Long.valueOf(r7)     // Catch:{ SQLiteFullException -> 0x00cb }
            goto L_0x00be
        L_0x00cb:
            com.microsoft.appcenter.utils.AppCenterLog.debug(r3, r10)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            long r7 = r1.deleteTheOldestLog(r2)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            int r0 = (r7 > r16 ? 1 : (r7 == r16 ? 0 : -1))
            if (r0 != 0) goto L_0x00be
            java.lang.Long r0 = java.lang.Long.valueOf(r16)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            r9 = r0
            goto L_0x00be
        L_0x00dc:
            long r7 = r9.longValue()     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            int r0 = (r7 > r16 ? 1 : (r7 == r16 ? 0 : -1))
            if (r0 == 0) goto L_0x016d
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            r0.<init>()     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.String r2 = "Stored a log to the Persistence database for log type "
            r0.append(r2)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.String r2 = r19.getType()     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            r0.append(r2)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.String r2 = " with databaseId="
            r0.append(r2)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            r0.append(r9)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.String r0 = r0.toString()     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            com.microsoft.appcenter.utils.AppCenterLog.debug(r3, r0)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            if (r6 == 0) goto L_0x0165
            java.lang.String r0 = "Payload is larger than what SQLite supports, storing payload in a separate file."
            com.microsoft.appcenter.utils.AppCenterLog.debug(r3, r0)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            r0 = r20
            java.io.File r0 = r1.getLargePayloadGroupDirectory(r0)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            r0.mkdir()     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            long r5 = r9.longValue()     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.io.File r0 = r1.getLargePayloadFile(r0, r5)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            com.microsoft.appcenter.utils.storage.FileManager.write((java.io.File) r0, (java.lang.String) r4)     // Catch:{ IOException -> 0x015a, JSONException -> 0x01ca }
            long r4 = r1.mLargePayloadsSize     // Catch:{ IOException -> 0x015a, JSONException -> 0x01ca }
            long r6 = r0.length()     // Catch:{ IOException -> 0x015a, JSONException -> 0x01ca }
            long r4 = r4 + r6
            r1.mLargePayloadsSize = r4     // Catch:{ IOException -> 0x015a, JSONException -> 0x01ca }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x015a, JSONException -> 0x01ca }
            r2.<init>()     // Catch:{ IOException -> 0x015a, JSONException -> 0x01ca }
            java.lang.String r4 = "Store extra "
            r2.append(r4)     // Catch:{ IOException -> 0x015a, JSONException -> 0x01ca }
            long r4 = r0.length()     // Catch:{ IOException -> 0x015a, JSONException -> 0x01ca }
            r2.append(r4)     // Catch:{ IOException -> 0x015a, JSONException -> 0x01ca }
            java.lang.String r4 = " KB as a separated payload file."
            r2.append(r4)     // Catch:{ IOException -> 0x015a, JSONException -> 0x01ca }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x015a, JSONException -> 0x01ca }
            com.microsoft.appcenter.utils.AppCenterLog.verbose(r3, r2)     // Catch:{ IOException -> 0x015a, JSONException -> 0x01ca }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            r2.<init>()     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.String r4 = "Payload written to "
            r2.append(r4)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            r2.append(r0)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.String r0 = r2.toString()     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            com.microsoft.appcenter.utils.AppCenterLog.debug(r3, r0)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            goto L_0x0165
        L_0x015a:
            r0 = move-exception
            com.microsoft.appcenter.utils.storage.DatabaseManager r2 = r1.mDatabaseManager     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            long r3 = r9.longValue()     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            r2.delete(r3)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            throw r0     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
        L_0x0165:
            r18.deleteLogsThatNotFitMaxSize()     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            long r2 = r9.longValue()     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            return r2
        L_0x016d:
            com.microsoft.appcenter.persistence.Persistence$PersistenceException r0 = new com.microsoft.appcenter.persistence.Persistence$PersistenceException     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            r2.<init>()     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.String r3 = "Failed to store a log to the Persistence database for log type "
            r2.append(r3)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.String r3 = r19.getType()     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            r2.append(r3)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.String r3 = "."
            r2.append(r3)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            r0.<init>(r2)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            throw r0     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
        L_0x018d:
            r0 = move-exception
            r1 = r18
            goto L_0x01c2
        L_0x0191:
            r0 = move-exception
            r1 = r18
            goto L_0x01cb
        L_0x0195:
            com.microsoft.appcenter.persistence.Persistence$PersistenceException r0 = new com.microsoft.appcenter.persistence.Persistence$PersistenceException     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            r2.<init>()     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.String r3 = "Log is too large ("
            r2.append(r3)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            r2.append(r5)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.String r3 = " bytes) to store in database. Current maximum database size is "
            r2.append(r3)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            r2.append(r10)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.String r3 = " bytes."
            r2.append(r3)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.String r2 = r2.toString()     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            r0.<init>(r2)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            throw r0     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
        L_0x01b9:
            com.microsoft.appcenter.persistence.Persistence$PersistenceException r0 = new com.microsoft.appcenter.persistence.Persistence$PersistenceException     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            java.lang.String r2 = "Failed to store a log to the Persistence database."
            r0.<init>(r2)     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
            throw r0     // Catch:{ JSONException -> 0x01ca, IOException -> 0x01c1 }
        L_0x01c1:
            r0 = move-exception
        L_0x01c2:
            com.microsoft.appcenter.persistence.Persistence$PersistenceException r2 = new com.microsoft.appcenter.persistence.Persistence$PersistenceException
            java.lang.String r3 = "Cannot save large payload in a file."
            r2.<init>(r3, r0)
            throw r2
        L_0x01ca:
            r0 = move-exception
        L_0x01cb:
            com.microsoft.appcenter.persistence.Persistence$PersistenceException r2 = new com.microsoft.appcenter.persistence.Persistence$PersistenceException
            java.lang.String r3 = "Cannot convert to JSON string."
            r2.<init>(r3, r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.appcenter.persistence.DatabasePersistence.putLog(com.microsoft.appcenter.ingestion.models.Log, java.lang.String, int):long");
    }

    /* access modifiers changed from: package-private */
    public File getLargePayloadGroupDirectory(String str) {
        return new File(this.mLargePayloadDirectory, str);
    }

    /* access modifiers changed from: package-private */
    public File getLargePayloadFile(File file, long j) {
        return new File(file, j + ".json");
    }

    private void deleteLog(File file, long j) {
        getLargePayloadFile(file, j).delete();
        this.mDatabaseManager.delete(j);
    }

    public void deleteLogs(String str, String str2) {
        AppCenterLog.debug("AppCenter", "Deleting logs from the Persistence database for " + str + " with " + str2);
        AppCenterLog.debug("AppCenter", "The IDs for deleting log(s) is/are:");
        Map<String, List<Long>> map = this.mPendingDbIdentifiersGroups;
        List<Long> remove = map.remove(str + str2);
        File largePayloadGroupDirectory = getLargePayloadGroupDirectory(str);
        if (remove != null) {
            for (Long l : remove) {
                AppCenterLog.debug("AppCenter", "\t" + l);
                deleteLog(largePayloadGroupDirectory, l.longValue());
                this.mPendingDbIdentifiers.remove(l);
            }
        }
    }

    public void deleteLogs(String str) {
        AppCenterLog.debug("AppCenter", "Deleting all logs from the Persistence database for " + str);
        File largePayloadGroupDirectory = getLargePayloadGroupDirectory(str);
        File[] listFiles = largePayloadGroupDirectory.listFiles();
        if (listFiles != null) {
            for (File delete : listFiles) {
                delete.delete();
            }
        }
        largePayloadGroupDirectory.delete();
        AppCenterLog.debug("AppCenter", "Deleted " + this.mDatabaseManager.delete(COLUMN_GROUP, str) + " logs.");
        Iterator<String> it = this.mPendingDbIdentifiersGroups.keySet().iterator();
        while (it.hasNext()) {
            if (it.next().startsWith(str)) {
                it.remove();
            }
        }
    }

    public int countLogs(String str) {
        Cursor cursor;
        SQLiteQueryBuilder newSQLiteQueryBuilder = SQLiteUtils.newSQLiteQueryBuilder();
        newSQLiteQueryBuilder.appendWhere("persistence_group = ?");
        int i = 0;
        try {
            cursor = this.mDatabaseManager.getCursor(newSQLiteQueryBuilder, new String[]{"COUNT(*)"}, new String[]{str}, (String) null);
            cursor.moveToNext();
            i = cursor.getInt(0);
            cursor.close();
        } catch (RuntimeException e) {
            AppCenterLog.error("AppCenter", "Failed to get logs count: ", e);
        } catch (Throwable th) {
            cursor.close();
            throw th;
        }
        return i;
    }

    public String getLogs(String str, Collection<String> collection, int i, List<Log> list) {
        Cursor cursor;
        String str2 = str;
        int i2 = i;
        AppCenterLog.debug("AppCenter", "Trying to get " + i2 + " logs from the Persistence database for " + str2);
        SQLiteQueryBuilder newSQLiteQueryBuilder = SQLiteUtils.newSQLiteQueryBuilder();
        newSQLiteQueryBuilder.appendWhere("persistence_group = ?");
        ArrayList arrayList = new ArrayList();
        arrayList.add(str2);
        if (!collection.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (int i3 = 0; i3 < collection.size(); i3++) {
                sb.append("?,");
            }
            sb.deleteCharAt(sb.length() - 1);
            newSQLiteQueryBuilder.appendWhere(" AND ");
            newSQLiteQueryBuilder.appendWhere("target_key NOT IN (" + sb.toString() + ")");
            arrayList.addAll(collection);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ArrayList<Long> arrayList2 = new ArrayList<>();
        File largePayloadGroupDirectory = getLargePayloadGroupDirectory(str);
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        try {
            cursor = this.mDatabaseManager.getCursor(newSQLiteQueryBuilder, (String[]) null, strArr, GET_SORT_ORDER);
        } catch (RuntimeException e) {
            AppCenterLog.error("AppCenter", "Failed to get logs: ", e);
            cursor = null;
        }
        int i4 = 0;
        while (cursor != null) {
            ContentValues nextValues = this.mDatabaseManager.nextValues(cursor);
            if (nextValues == null || i4 >= i2) {
                break;
            }
            Long asLong = nextValues.getAsLong(DatabaseManager.PRIMARY_KEY);
            if (asLong == null) {
                AppCenterLog.error("AppCenter", "Empty database record, probably content was larger than 2MB, need to delete as it's now corrupted.");
                Iterator<Long> it = getLogsIds(newSQLiteQueryBuilder, strArr).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Long next = it.next();
                    if (!this.mPendingDbIdentifiers.contains(next) && !linkedHashMap.containsKey(next)) {
                        deleteLog(largePayloadGroupDirectory, next.longValue());
                        AppCenterLog.error("AppCenter", "Empty database corrupted empty record deleted, id=" + next);
                        break;
                    }
                }
            } else if (!this.mPendingDbIdentifiers.contains(asLong)) {
                try {
                    String asString = nextValues.getAsString(COLUMN_LOG);
                    if (asString == null) {
                        File largePayloadFile = getLargePayloadFile(largePayloadGroupDirectory, asLong.longValue());
                        AppCenterLog.debug("AppCenter", "Read payload file " + largePayloadFile);
                        asString = FileManager.read(largePayloadFile);
                        if (asString == null) {
                            throw new JSONException("Log payload is null and not stored as a file.");
                        }
                    }
                    Log deserializeLog = getLogSerializer().deserializeLog(asString, nextValues.getAsString("type"));
                    String asString2 = nextValues.getAsString(COLUMN_TARGET_TOKEN);
                    if (asString2 != null) {
                        deserializeLog.addTransmissionTarget(CryptoUtils.getInstance(this.mContext).decrypt(asString2).getDecryptedData());
                    }
                    linkedHashMap.put(asLong, deserializeLog);
                    i4++;
                } catch (JSONException e2) {
                    AppCenterLog.error("AppCenter", "Cannot deserialize a log in the database", e2);
                    arrayList2.add(asLong);
                }
            }
        }
        if (cursor != null) {
            try {
                cursor.close();
            } catch (RuntimeException unused) {
            }
        }
        if (arrayList2.size() > 0) {
            for (Long longValue : arrayList2) {
                deleteLog(largePayloadGroupDirectory, longValue.longValue());
            }
            AppCenterLog.warn("AppCenter", "Deleted logs that cannot be deserialized");
        }
        if (linkedHashMap.size() <= 0) {
            AppCenterLog.debug("AppCenter", "No logs found in the Persistence database at the moment");
            return null;
        }
        String uuid = UUID.randomUUID().toString();
        AppCenterLog.debug("AppCenter", "Returning " + linkedHashMap.size() + " log(s) with an ID, " + uuid);
        AppCenterLog.debug("AppCenter", "The SID/ID pairs for returning log(s) is/are:");
        ArrayList arrayList3 = new ArrayList();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            Long l = (Long) entry.getKey();
            this.mPendingDbIdentifiers.add(l);
            arrayList3.add(l);
            list.add((Log) entry.getValue());
            AppCenterLog.debug("AppCenter", "\t" + ((Log) entry.getValue()).getSid() + " / " + l);
        }
        this.mPendingDbIdentifiersGroups.put(str2 + uuid, arrayList3);
        return uuid;
    }

    public void clearPendingLogState() {
        this.mPendingDbIdentifiers.clear();
        this.mPendingDbIdentifiersGroups.clear();
        AppCenterLog.debug("AppCenter", "Cleared pending log states");
    }

    public void close() {
        this.mDatabaseManager.close();
    }

    /* JADX WARNING: Removed duplicated region for block: B:1:0x0006 A[LOOP:0: B:1:0x0006->B:4:0x001c, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void deleteLogsThatNotFitMaxSize() {
        /*
            r6 = this;
            r0 = 1
            r1 = 0
            int r0 = com.microsoft.appcenter.Flags.getPersistenceFlag(r0, r1)
        L_0x0006:
            long r1 = r6.getStoredDataSize()
            com.microsoft.appcenter.utils.storage.DatabaseManager r3 = r6.mDatabaseManager
            long r3 = r3.getMaxSize()
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 < 0) goto L_0x001e
            long r1 = r6.deleteTheOldestLog(r0)
            r3 = -1
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 != 0) goto L_0x0006
        L_0x001e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.appcenter.persistence.DatabasePersistence.deleteLogsThatNotFitMaxSize():void");
    }

    private long getStoredDataSize() {
        return this.mDatabaseManager.getCurrentSize() + this.mLargePayloadsSize;
    }

    private long deleteTheOldestLog(int i) {
        HashSet hashSet = new HashSet();
        hashSet.add(DatabaseManager.PRIMARY_KEY);
        hashSet.add(COLUMN_GROUP);
        ContentValues deleteTheOldestRecord = this.mDatabaseManager.deleteTheOldestRecord(hashSet, COLUMN_PRIORITY, i);
        if (deleteTheOldestRecord == null) {
            return -1;
        }
        long longValue = deleteTheOldestRecord.getAsLong(DatabaseManager.PRIMARY_KEY).longValue();
        File largePayloadFile = getLargePayloadFile(getLargePayloadGroupDirectory(deleteTheOldestRecord.getAsString(COLUMN_GROUP)), longValue);
        if (!largePayloadFile.exists()) {
            return longValue;
        }
        long length = largePayloadFile.length();
        if (largePayloadFile.delete()) {
            this.mLargePayloadsSize -= length;
            AppCenterLog.verbose("AppCenter", "Large payload file with id " + longValue + " has been deleted. " + length + " KB of memory has been freed.");
        } else {
            AppCenterLog.warn("AppCenter", "Cannot delete large payload file with id " + longValue);
        }
        return longValue;
    }

    private long checkLargePayloadFilesAndCollectTheirSize() {
        AnonymousClass2 r2 = new FilenameFilter() {
            public boolean accept(File file, String str) {
                return str.endsWith(".json");
            }
        };
        Set<Long> logsIds = getLogsIds(SQLiteUtils.newSQLiteQueryBuilder(), new String[0]);
        File[] listFiles = this.mLargePayloadDirectory.listFiles();
        long j = 0;
        if (listFiles == null) {
            return 0;
        }
        for (File listFiles2 : listFiles) {
            File[] listFiles3 = listFiles2.listFiles(r2);
            if (listFiles3 != null) {
                for (File file : listFiles3) {
                    try {
                        long parseInt = (long) Integer.parseInt(FileManager.getNameWithoutExtension(file));
                        if (logsIds.contains(Long.valueOf(parseInt))) {
                            j += file.length();
                        } else if (!file.delete()) {
                            AppCenterLog.warn("AppCenter", "Cannot delete redundant large payload file with id " + parseInt);
                        } else {
                            AppCenterLog.debug("AppCenter", "Lasted large payload file with name " + file.getName() + " has been deleted.");
                        }
                    } catch (NumberFormatException unused) {
                        AppCenterLog.warn("AppCenter", "A file was found whose name does not match the pattern of naming log files: " + file.getName());
                    }
                }
            }
        }
        return j;
    }

    private Set<Long> getLogsIds(SQLiteQueryBuilder sQLiteQueryBuilder, String... strArr) {
        Cursor cursor;
        HashSet hashSet = new HashSet();
        try {
            cursor = this.mDatabaseManager.getCursor(sQLiteQueryBuilder, DatabaseManager.SELECT_PRIMARY_KEY, strArr, (String) null);
            while (cursor.moveToNext()) {
                hashSet.add(this.mDatabaseManager.buildValues(cursor).getAsLong(DatabaseManager.PRIMARY_KEY));
            }
            cursor.close();
        } catch (RuntimeException e) {
            AppCenterLog.error("AppCenter", "Failed to get corrupted ids: ", e);
        } catch (Throwable th) {
            cursor.close();
            throw th;
        }
        return hashSet;
    }
}
