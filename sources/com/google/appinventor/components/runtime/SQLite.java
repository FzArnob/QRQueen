package com.google.appinventor.components.runtime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;
import java.util.List;

@SimpleObject
@DesignerComponent(category = ComponentCategory.STORAGE, description = "Tool developed by Carlos Pedroza to access the application's SQlite database.", helpUrl = "https://docs.kodular.io/components/storage/sqlite/", iconName = "images/SQLite.png", nonVisible = true, version = 2)
public class SQLite extends AndroidNonvisibleComponent implements Component {
    /* access modifiers changed from: private */
    public static String IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2 = "DATABASE.sqlite";
    private String BeAOotgA7zBP9Op6r2FqJlUCXvxuSHPx6BwhNdpgtXlIG2LNe5NWKzZhiJoW0gYE;
    private int HD0pINq0k1DLtf3yXVbK8xQhU4iGPT4iryXVeTceizjENHUUCtIIAr11wB5gmj8l = 1;
    private boolean I1fbWPe6RJ2coGGe88dnbV3SwCWOYXWySlRHSiEJVMw7CeEp0YdmKizbxY7zqrk2;
    private String[] KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;
    private boolean Mn7MCs8byCcphc6u6vZkI1pXuzw5IvVJJPq0YTQ0xCW64cXQ1HYdJPP7QsOPqGr1;
    private boolean TcZoKXUijRhvOFm1gZ0ppEfXWxECqlUNNvncSPIfT3ZrTDcozo05OAb21mkMXciT;
    private ComponentContainer container;
    private Context context;
    /* access modifiers changed from: private */
    public int dNRA8zk5IiUh4Pp3hPTF1cOQ5zFA2APl8kyLVDxMBtQN7HXMvFLEdGqNCHj4PKNw = 0;
    private SQLiteDatabase hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private String[] l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j;

    class b extends AsyncTask<Void, Void, Void> {
        private boolean pixa37in9tdgjNQb2jpfjxTaBGtatW1GnEvNM302mGQljyvLBwDosTGoRPcRGYok;

        private b() {
        }

        /* synthetic */ b(SQLite sQLite, byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME();
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(2:11|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0040, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
            r4.pixa37in9tdgjNQb2jpfjxTaBGtatW1GnEvNM302mGQljyvLBwDosTGoRPcRGYok = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x004c, code lost:
            r0.endTransaction();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x004f, code lost:
            throw r1;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0042 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.lang.Void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME() {
            /*
                r4 = this;
                com.google.appinventor.components.runtime.SQLite$a r0 = new com.google.appinventor.components.runtime.SQLite$a
                com.google.appinventor.components.runtime.SQLite r1 = com.google.appinventor.components.runtime.SQLite.this
                r0.<init>()
                android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
                r0.beginTransaction()
                r1 = 0
                com.google.appinventor.components.runtime.SQLite r2 = com.google.appinventor.components.runtime.SQLite.this     // Catch:{ SQLException -> 0x0042 }
                boolean r2 = com.google.appinventor.components.runtime.SQLite.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.SQLite) r2)     // Catch:{ SQLException -> 0x0042 }
                if (r2 == 0) goto L_0x0021
                com.google.appinventor.components.runtime.SQLite r2 = com.google.appinventor.components.runtime.SQLite.this     // Catch:{ SQLException -> 0x0042 }
                java.lang.String r2 = com.google.appinventor.components.runtime.SQLite.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.SQLite) r2)     // Catch:{ SQLException -> 0x0042 }
                r0.execSQL(r2)     // Catch:{ SQLException -> 0x0042 }
                goto L_0x0039
            L_0x0021:
                r2 = 0
            L_0x0022:
                com.google.appinventor.components.runtime.SQLite r3 = com.google.appinventor.components.runtime.SQLite.this     // Catch:{ SQLException -> 0x0042 }
                java.lang.String[] r3 = com.google.appinventor.components.runtime.SQLite.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.SQLite) r3)     // Catch:{ SQLException -> 0x0042 }
                int r3 = r3.length     // Catch:{ SQLException -> 0x0042 }
                if (r2 >= r3) goto L_0x0039
                com.google.appinventor.components.runtime.SQLite r3 = com.google.appinventor.components.runtime.SQLite.this     // Catch:{ SQLException -> 0x0042 }
                java.lang.String[] r3 = com.google.appinventor.components.runtime.SQLite.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.SQLite) r3)     // Catch:{ SQLException -> 0x0042 }
                r3 = r3[r2]     // Catch:{ SQLException -> 0x0042 }
                r0.execSQL(r3)     // Catch:{ SQLException -> 0x0042 }
                int r2 = r2 + 1
                goto L_0x0022
            L_0x0039:
                r2 = 1
                r4.pixa37in9tdgjNQb2jpfjxTaBGtatW1GnEvNM302mGQljyvLBwDosTGoRPcRGYok = r2     // Catch:{ SQLException -> 0x0042 }
                r0.setTransactionSuccessful()     // Catch:{ SQLException -> 0x0042 }
                goto L_0x0044
            L_0x0040:
                r1 = move-exception
                goto L_0x004c
            L_0x0042:
                r4.pixa37in9tdgjNQb2jpfjxTaBGtatW1GnEvNM302mGQljyvLBwDosTGoRPcRGYok = r1     // Catch:{ all -> 0x0040 }
            L_0x0044:
                r0.endTransaction()
                r0.close()
                r0 = 0
                return r0
            L_0x004c:
                r0.endTransaction()
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.SQLite.b.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME():java.lang.Void");
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            if (!SQLite.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(SQLite.this)) {
                Toast.makeText(SQLite.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(SQLite.this), "Done", 0).show();
            }
            SQLite.this.AfterExecution(this.pixa37in9tdgjNQb2jpfjxTaBGtatW1GnEvNM302mGQljyvLBwDosTGoRPcRGYok);
            if (!this.pixa37in9tdgjNQb2jpfjxTaBGtatW1GnEvNM302mGQljyvLBwDosTGoRPcRGYok) {
                SQLite.this.ErrorOccurred("Invalid SQL Statement");
            }
        }
    }

    class c extends AsyncTask<Void, Void, Void> {
        private int R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe;
        private boolean pixa37in9tdgjNQb2jpfjxTaBGtatW1GnEvNM302mGQljyvLBwDosTGoRPcRGYok;
        private List zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5;

        private c() {
        }

        /* synthetic */ c(SQLite sQLite, byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME();
        }

        private Void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME() {
            SQLiteDatabase readableDatabase = new a().getReadableDatabase();
            readableDatabase.beginTransaction();
            try {
                List hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = SQLite.this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(readableDatabase.rawQuery(SQLite.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(SQLite.this), SQLite.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(SQLite.this)));
                this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2;
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.size();
                this.pixa37in9tdgjNQb2jpfjxTaBGtatW1GnEvNM302mGQljyvLBwDosTGoRPcRGYok = true;
                readableDatabase.setTransactionSuccessful();
            } catch (SQLException unused) {
                this.pixa37in9tdgjNQb2jpfjxTaBGtatW1GnEvNM302mGQljyvLBwDosTGoRPcRGYok = false;
            } catch (Throwable th) {
                readableDatabase.endTransaction();
                throw th;
            }
            readableDatabase.endTransaction();
            readableDatabase.close();
            return null;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            if (!SQLite.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(SQLite.this)) {
                Toast.makeText(SQLite.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(SQLite.this), "Done", 0).show();
            }
            if (this.pixa37in9tdgjNQb2jpfjxTaBGtatW1GnEvNM302mGQljyvLBwDosTGoRPcRGYok) {
                SQLite.this.AfterQuery(this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5, this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe);
            } else {
                SQLite.this.ErrorOccurred("Invalid SQL Statement");
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004d, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0050, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0071, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0072, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0075, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0076, code lost:
        r0 = r2;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:5:0x0013, B:13:0x002b, B:32:0x005f] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:13:0x002b=Splitter:B:13:0x002b, B:32:0x005f=Splitter:B:32:0x005f} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(android.database.Cursor r7) {
        /*
            r6 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String[] r1 = r7.getColumnNames()     // Catch:{ SQLException -> 0x0077 }
            int r2 = r1.length     // Catch:{ SQLException -> 0x0077 }
            r3 = 1
            r4 = 0
            if (r2 <= r3) goto L_0x0051
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ SQLException -> 0x0077 }
            r2.<init>()     // Catch:{ SQLException -> 0x0077 }
            boolean r0 = r6.Mn7MCs8byCcphc6u6vZkI1pXuzw5IvVJJPq0YTQ0xCW64cXQ1HYdJPP7QsOPqGr1     // Catch:{ SQLException -> 0x0076 }
            if (r0 == 0) goto L_0x002b
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ SQLException -> 0x0076 }
            r0.<init>()     // Catch:{ SQLException -> 0x0076 }
            r3 = 0
        L_0x001d:
            int r5 = r1.length     // Catch:{ SQLException -> 0x0076 }
            if (r3 >= r5) goto L_0x0028
            r5 = r1[r3]     // Catch:{ SQLException -> 0x0076 }
            r0.add(r5)     // Catch:{ SQLException -> 0x0076 }
            int r3 = r3 + 1
            goto L_0x001d
        L_0x0028:
            r2.add(r0)     // Catch:{ SQLException -> 0x0076 }
        L_0x002b:
            boolean r0 = r7.moveToNext()     // Catch:{ all -> 0x004c }
            if (r0 == 0) goto L_0x0048
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x004c }
            r0.<init>()     // Catch:{ all -> 0x004c }
            r3 = 0
        L_0x0037:
            int r5 = r1.length     // Catch:{ all -> 0x004c }
            if (r3 >= r5) goto L_0x0044
            java.lang.String r5 = r7.getString(r3)     // Catch:{ all -> 0x004c }
            r0.add(r5)     // Catch:{ all -> 0x004c }
            int r3 = r3 + 1
            goto L_0x0037
        L_0x0044:
            r2.add(r0)     // Catch:{ all -> 0x004c }
            goto L_0x002b
        L_0x0048:
            r7.close()     // Catch:{ SQLException -> 0x0076 }
            goto L_0x0070
        L_0x004c:
            r0 = move-exception
            r7.close()     // Catch:{ SQLException -> 0x0076 }
            throw r0     // Catch:{ SQLException -> 0x0076 }
        L_0x0051:
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ SQLException -> 0x0077 }
            r2.<init>()     // Catch:{ SQLException -> 0x0077 }
            boolean r0 = r6.Mn7MCs8byCcphc6u6vZkI1pXuzw5IvVJJPq0YTQ0xCW64cXQ1HYdJPP7QsOPqGr1     // Catch:{ SQLException -> 0x0076 }
            if (r0 == 0) goto L_0x005f
            r0 = r1[r4]     // Catch:{ SQLException -> 0x0076 }
            r2.add(r0)     // Catch:{ SQLException -> 0x0076 }
        L_0x005f:
            boolean r0 = r7.moveToNext()     // Catch:{ all -> 0x0071 }
            if (r0 == 0) goto L_0x006d
            java.lang.String r0 = r7.getString(r4)     // Catch:{ all -> 0x0071 }
            r2.add(r0)     // Catch:{ all -> 0x0071 }
            goto L_0x005f
        L_0x006d:
            r7.close()     // Catch:{ SQLException -> 0x0076 }
        L_0x0070:
            return r2
        L_0x0071:
            r0 = move-exception
            r7.close()     // Catch:{ SQLException -> 0x0076 }
            throw r0     // Catch:{ SQLException -> 0x0076 }
        L_0x0076:
            r0 = r2
        L_0x0077:
            java.lang.String r7 = "Error during managing the cursor"
            r6.ErrorOccurred(r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.SQLite.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(android.database.Cursor):java.util.List");
    }

    public SQLite(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        this.context = componentContainer.$context();
        Log.d("SQLite", "SQLite created");
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns whether the header row should be returned in the result of a Select statement.")
    public boolean ReturnHeader() {
        return this.Mn7MCs8byCcphc6u6vZkI1pXuzw5IvVJJPq0YTQ0xCW64cXQ1HYdJPP7QsOPqGr1;
    }

    @DesignerProperty(defaultValue = "false", editorType = "boolean")
    @SimpleProperty
    public void ReturnHeader(boolean z) {
        this.Mn7MCs8byCcphc6u6vZkI1pXuzw5IvVJJPq0YTQ0xCW64cXQ1HYdJPP7QsOPqGr1 = z;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns whether Success Toast should be suppressed.")
    public boolean SuppressToast() {
        return this.I1fbWPe6RJ2coGGe88dnbV3SwCWOYXWySlRHSiEJVMw7CeEp0YdmKizbxY7zqrk2;
    }

    @DesignerProperty(defaultValue = "false", editorType = "boolean")
    @SimpleProperty
    public void SuppressToast(boolean z) {
        this.I1fbWPe6RJ2coGGe88dnbV3SwCWOYXWySlRHSiEJVMw7CeEp0YdmKizbxY7zqrk2 = z;
    }

    @SimpleFunction(description = "Returns the path to the database")
    public String GetPath() {
        SQLiteDatabase readableDatabase = new a().getReadableDatabase();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = readableDatabase;
        return readableDatabase.getPath();
    }

    @SimpleFunction(description = "Clears the database to version 1. Use only while developing, this shouldn't be use on production.")
    public void ClearDatabase() {
        this.context.deleteDatabase(IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2);
        Toast.makeText(this.context, "Database cleared", 0).show();
    }

    @SimpleFunction(description = "Execute a Single SQL Statement asynchronously and returns whether the transaction was successful in the AfterExecution Event Handler. Use it when returned data isn't needed. Parameter: 1) String sql.")
    public void SingleSQL(String str) {
        try {
            this.BeAOotgA7zBP9Op6r2FqJlUCXvxuSHPx6BwhNdpgtXlIG2LNe5NWKzZhiJoW0gYE = str;
            this.TcZoKXUijRhvOFm1gZ0ppEfXWxECqlUNNvncSPIfT3ZrTDcozo05OAb21mkMXciT = true;
            new b(this, (byte) 0).execute(new Void[0]);
        } catch (Exception unused) {
            ErrorOccurred("Something went wrong SingleSQL");
        }
    }

    @SimpleFunction(description = "Execute Multiple SQL Statement asynchronously and returns whether the transaction was successful in the AfterExecution Event Handler. Use it when returned data isn't needed. Parameter: 1 ) List of SQL.")
    public void MultipleSQL(YailList yailList) {
        try {
            this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j = yailList.toStringArray();
            this.TcZoKXUijRhvOFm1gZ0ppEfXWxECqlUNNvncSPIfT3ZrTDcozo05OAb21mkMXciT = false;
            new b(this, (byte) 0).execute(new Void[0]);
        } catch (Exception unused) {
            ErrorOccurred("Something went wrong MultipleSQL");
        }
    }

    @SimpleEvent
    public void AfterExecution(boolean z) {
        EventDispatcher.dispatchEvent(this, "AfterExecution", Boolean.valueOf(z));
    }

    @SimpleFunction(description = "Executes the provided rawQuery Statement asynchronously. Returns a YailList with the selected data and number of records in the AfterQuery Event. Parameter: 1) String sql. 2) YailList selectionArgs: List with the arguments that will replace '?' in where clause in the query, to prevent SQL injections")
    public void RawQuery(String str, YailList yailList) {
        try {
            this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = yailList.toStringArray();
            this.BeAOotgA7zBP9Op6r2FqJlUCXvxuSHPx6BwhNdpgtXlIG2LNe5NWKzZhiJoW0gYE = str;
            new c(this, (byte) 0).execute(new Void[0]);
        } catch (Exception unused) {
            ErrorOccurred("Something went wrong RawQuery");
        }
    }

    @SimpleEvent
    public void AfterQuery(List list, int i) {
        EventDispatcher.dispatchEvent(this, "AfterQuery", list, Integer.valueOf(i));
    }

    @SimpleFunction(description = "Executes pre-compiled DELETE statement with specified parameters. Parameters: 1) String table - Name of the table. 2) String whereClause - Optional WHERE clause to apply when deleting (Example: 'ID = ?'), pasing an empty a string will delete all rows. 3) List whereArgs - List with arguments for the WHERE clause. These arguments will be replaced by '?' in the whereClause. Returns the number of rows affected if a whereClause is passed in, 0 otherwise.")
    public int Delete(String str, String str2, YailList yailList) {
        try {
            SQLiteDatabase writableDatabase = new a().getWritableDatabase();
            String[] stringArray = yailList.toStringArray();
            if (str2.equals("")) {
                str2 = null;
            }
            int delete = writableDatabase.delete(str, str2, stringArray);
            writableDatabase.close();
            return delete;
        } catch (SQLException unused) {
            ErrorOccurred("Something went wrong deleting");
            return -1;
        }
    }

    @SimpleFunction(description = "Executes pre-compiled INSERT statement with specified parameters. Parameters: 1) String table - Name of the table. 2) YailList columns - List with the columns that will contain the data to be inserted in the database. 3) YailList values - List with the data to be inserted in the database. Returns the row ID of the newly inserted row, or -1 if an error occurred.")
    public long Insert(String str, YailList yailList, YailList yailList2) {
        try {
            SQLiteDatabase writableDatabase = new a().getWritableDatabase();
            String[] stringArray = yailList.toStringArray();
            String[] stringArray2 = yailList2.toStringArray();
            ContentValues contentValues = new ContentValues();
            for (int i = 0; i < stringArray.length; i++) {
                contentValues.put(stringArray[i], stringArray2[i]);
            }
            long insert = writableDatabase.insert(str, (String) null, contentValues);
            writableDatabase.close();
            return insert;
        } catch (SQLException unused) {
            ErrorOccurred("Something went wrong inserting");
            return -1;
        }
    }

    @SimpleFunction(description = "Executes pre-compiled REPLACE OR INSERT INTO statement with specified parameters. Parameters: 1) String table - Name of the table. 2) YailList columns - List with the columns that will contain the data to be replaced in the database. 3) YailList values - List with the data to be replaced in the database. Returns the row ID of the newly replaced row, or -1 if an error occurred.")
    public long Replace(String str, YailList yailList, YailList yailList2) {
        try {
            SQLiteDatabase writableDatabase = new a().getWritableDatabase();
            String[] stringArray = yailList.toStringArray();
            String[] stringArray2 = yailList2.toStringArray();
            ContentValues contentValues = new ContentValues();
            for (int i = 0; i < stringArray.length; i++) {
                contentValues.put(stringArray[i], stringArray2[i]);
            }
            long replace = writableDatabase.replace(str, (String) null, contentValues);
            writableDatabase.close();
            return replace;
        } catch (SQLException unused) {
            ErrorOccurred("Something went wrong replacing");
            return -1;
        }
    }

    @SimpleFunction(description = "Executes pre-compiled UPDATE statement with specified parameters. Parameters: 1) String table - Name of the table. 2) YailList columns - List with the columns that will contain the data to be inserted in the database. 3) YailList values - List with the data to be inserted in the database. 4) String whereClause - optional WHERE clause to apply when updating, leave an empty string to update all rows. Include ?s, which will be updated by the values from whereArgs. 5) YailList whereArgs - List with the columns that will contain the data to be updated in the database. Returns the row ID of the newly inserted row, or -1 if an error occurred.")
    public int Update(String str, YailList yailList, YailList yailList2, String str2, YailList yailList3) {
        try {
            SQLiteDatabase writableDatabase = new a().getWritableDatabase();
            String[] stringArray = yailList.toStringArray();
            String[] stringArray2 = yailList2.toStringArray();
            ContentValues contentValues = new ContentValues();
            for (int i = 0; i < stringArray.length; i++) {
                contentValues.put(stringArray[i], stringArray2[i]);
            }
            String[] stringArray3 = yailList3.toStringArray();
            if (str2.equals("")) {
                str2 = null;
            }
            int update = writableDatabase.update(str, contentValues, str2, stringArray3);
            writableDatabase.close();
            return update;
        } catch (SQLException unused) {
            ErrorOccurred("Something went wrong updating");
            return -1;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0078, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0079, code lost:
        r11.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x007c, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0078 A[ExcHandler: all (r0v8 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:21:0x0064] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x009a  */
    @com.google.appinventor.components.annotations.SimpleFunction(description = "Executes pre-compiled QUERY statement with specified parameters. Parameters: 1) String table: Name of the table. 2) YailList columns: List of which columns to return, passing an empty list will return all columns. 3) String selection: Filter declaring which rows to return, formatted as an SQL WHERE clause, passing an empty string will return all rows. 4) YailList selectionArgs: List with the arguments that will replace onto '?' in the selection filter. 5) String groupBy: A filter declaring how to group rows, formatted as an SQL GROUP BY clause (excluding the GROUP BY itself), passing an empty string will cause the row to not be grouped. 6) String having: A filter declare which row groups to include if row grouping is being used, passing an empty string will cause all row groups to be included. 7) String orderBy: How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself), passing an empty string will use the default sort order (unordered). 8) String limit: Limits the number of rows returned by the query, formatted as LIMIT clause, passing an empty string denotes no LIMIT clause. The result query is available in the AfterQuery event handler")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void Query(java.lang.String r15, com.google.appinventor.components.runtime.util.YailList r16, java.lang.String r17, com.google.appinventor.components.runtime.util.YailList r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, java.lang.String r22) {
        /*
            r14 = this;
            r1 = r14
            com.google.appinventor.components.runtime.SQLite$a r0 = new com.google.appinventor.components.runtime.SQLite$a
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r11 = r0.getReadableDatabase()
            java.lang.String[] r4 = r16.toStringArray()
            java.lang.String[] r6 = r18.toStringArray()
            java.lang.String r0 = ""
            r2 = r17
            boolean r3 = r2.equals(r0)
            r5 = 0
            if (r3 == 0) goto L_0x0021
            r2 = r19
            r7 = r5
            goto L_0x0024
        L_0x0021:
            r7 = r2
            r2 = r19
        L_0x0024:
            boolean r3 = r2.equals(r0)
            if (r3 == 0) goto L_0x002e
            r2 = r20
            r8 = r5
            goto L_0x0031
        L_0x002e:
            r8 = r2
            r2 = r20
        L_0x0031:
            boolean r3 = r2.equals(r0)
            if (r3 == 0) goto L_0x003b
            r2 = r21
            r9 = r5
            goto L_0x003e
        L_0x003b:
            r9 = r2
            r2 = r21
        L_0x003e:
            boolean r3 = r2.equals(r0)
            if (r3 == 0) goto L_0x0048
            r2 = r22
            r10 = r5
            goto L_0x004b
        L_0x0048:
            r10 = r2
            r2 = r22
        L_0x004b:
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0053
            r0 = r5
            goto L_0x0054
        L_0x0053:
            r0 = r2
        L_0x0054:
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            r11.beginTransaction()
            r13 = 0
            r2 = r11
            r3 = r15
            r5 = r7
            r7 = r8
            r8 = r9
            r9 = r10
            r10 = r0
            android.database.Cursor r0 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLException -> 0x007d, all -> 0x0078 }
            java.util.List r12 = r14.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((android.database.Cursor) r0)     // Catch:{ SQLException -> 0x007d, all -> 0x0078 }
            int r0 = r12.size()     // Catch:{ SQLException -> 0x007d, all -> 0x0078 }
            r2 = 1
            r11.setTransactionSuccessful()     // Catch:{ SQLException -> 0x007e, all -> 0x0078 }
            r11.endTransaction()
            goto L_0x0082
        L_0x0078:
            r0 = move-exception
            r11.endTransaction()
            throw r0
        L_0x007d:
            r0 = 0
        L_0x007e:
            r11.endTransaction()
            r2 = 0
        L_0x0082:
            r11.close()
            boolean r3 = r1.I1fbWPe6RJ2coGGe88dnbV3SwCWOYXWySlRHSiEJVMw7CeEp0YdmKizbxY7zqrk2
            if (r3 != 0) goto L_0x0094
            android.content.Context r3 = r1.context
            java.lang.String r4 = "Done"
            android.widget.Toast r3 = android.widget.Toast.makeText(r3, r4, r13)
            r3.show()
        L_0x0094:
            if (r2 == 0) goto L_0x009a
            r14.AfterQuery(r12, r0)
            return
        L_0x009a:
            java.lang.String r0 = "Something went wrong querying"
            r14.ErrorOccurred(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.SQLite.Query(java.lang.String, com.google.appinventor.components.runtime.util.YailList, java.lang.String, com.google.appinventor.components.runtime.util.YailList, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    @SimpleEvent
    public void ErrorOccurred(String str) {
        EventDispatcher.dispatchEvent(this, "ErrorOccurred", str);
    }

    @SimpleFunction(description = "Used to run any valid SQLite query and return results in same block.", userVisible = true)
    public YailList RunQuery(String str) {
        a aVar = new a();
        String trim = str.trim();
        if (trim.toLowerCase().startsWith("select") || trim.toLowerCase().startsWith("explain")) {
            return aVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(trim);
        }
        return aVar.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(trim);
    }

    @SimpleFunction(description = "Used to drop / delete table from database. Please note that this event will DELETE any data you may have on the table and will then delete table from the database. After this operation is completed, it cannot be undone!", userVisible = true)
    public YailList DropTable(String str) {
        return RunQuery("drop table if exists ".concat(String.valueOf(str)));
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the number of rows affected after a raw SQL has been executed using SQL Query.")
    public int RowsAffected() {
        return this.dNRA8zk5IiUh4Pp3hPTF1cOQ5zFA2APl8kyLVDxMBtQN7HXMvFLEdGqNCHj4PKNw;
    }

    @SimpleFunction(description = "Used to retrieve list of existing tables from the database", userVisible = true)
    public YailList DisplayTables() {
        return new a().hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("SELECT tbl_name FROM sqlite_master WHERE type='table' and tbl_name != 'android_metadata'");
    }

    public static String toCsvRow(String[] strArr) {
        StringBuilder sb = new StringBuilder();
        for (String append : strArr) {
            sb.append(append);
            sb.append(",");
        }
        String sb2 = sb.toString();
        return sb2.endsWith(",") ? sb2.substring(0, sb2.length() - 1) : sb2;
    }

    class a extends SQLiteOpenHelper {
        public a() {
            super(SQLite.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((SQLite) SQLite.this), SQLite.IpCDSeDtSQ9aVIFHrTV0geVthcfgimpo1gHNFztT9EKnCqzMmr52GQEFh7mXSGc2, (SQLiteDatabase.CursorFactory) null, SQLite.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((SQLite) SQLite.this));
        }

        public final void onCreate(SQLiteDatabase sQLiteDatabase) {
            if (!SQLite.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(SQLite.this)) {
                Toast.makeText(SQLite.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(SQLite.this), "Database created", 0).show();
            }
        }

        public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (!SQLite.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(SQLite.this)) {
                Toast.makeText(SQLite.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(SQLite.this), "Database upgraded", 0).show();
            }
        }

        /* access modifiers changed from: private */
        public YailList hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str) {
            ArrayList arrayList = new ArrayList();
            SQLiteDatabase readableDatabase = getReadableDatabase();
            try {
                Cursor rawQuery = readableDatabase.rawQuery(str, (String[]) null);
                if (rawQuery != null) {
                    if (rawQuery.moveToFirst()) {
                        arrayList.add(SQLite.toCsvRow(rawQuery.getColumnNames()));
                        do {
                            StringBuilder sb = new StringBuilder("");
                            for (int i = 0; i < rawQuery.getColumnCount(); i++) {
                                sb.append(rawQuery.getString(i));
                                sb.append(",");
                            }
                            String sb2 = sb.toString();
                            if (sb2.endsWith(",")) {
                                sb2 = sb2.substring(0, sb2.length() - 1);
                            }
                            arrayList.add(sb2);
                        } while (rawQuery.moveToNext());
                    }
                    rawQuery.close();
                }
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(readableDatabase);
            } catch (Exception e) {
                Log.d("SQLite", "Error executing query. Error was: " + e.getMessage());
            }
            readableDatabase.close();
            SQLiteDatabase.releaseMemory();
            return YailList.makeList((List) arrayList);
        }

        private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(SQLiteDatabase sQLiteDatabase) {
            int unused = SQLite.this.dNRA8zk5IiUh4Pp3hPTF1cOQ5zFA2APl8kyLVDxMBtQN7HXMvFLEdGqNCHj4PKNw = 0;
            Cursor rawQuery = sQLiteDatabase.rawQuery("select total_changes()", (String[]) null);
            if (rawQuery != null) {
                if (rawQuery.moveToFirst()) {
                    int unused2 = SQLite.this.dNRA8zk5IiUh4Pp3hPTF1cOQ5zFA2APl8kyLVDxMBtQN7HXMvFLEdGqNCHj4PKNw = Integer.parseInt(rawQuery.getString(0));
                }
                rawQuery.close();
            }
        }

        /* access modifiers changed from: private */
        public YailList B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(String str) {
            ArrayList arrayList = new ArrayList();
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                writableDatabase.execSQL(str);
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(writableDatabase);
                writableDatabase.close();
                arrayList.add("true");
            } catch (SQLException e) {
                Log.d("SQLite", "Error executing query. Error is: " + e.getMessage());
                arrayList.add("false");
            }
            return YailList.makeList((List) arrayList);
        }
    }
}
