package com.microsoft.appcenter.utils.storage;

import android.database.sqlite.SQLiteQueryBuilder;

public class SQLiteUtils {
    public static SQLiteQueryBuilder newSQLiteQueryBuilder() {
        return new SQLiteQueryBuilder();
    }
}
