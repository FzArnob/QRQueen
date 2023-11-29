package com.google.appinventor.components.runtime;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.text.util.Rfc822Token;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import com.google.appinventor.components.runtime.util.HoneycombMR1Util;
import com.microsoft.appcenter.ingestion.models.CommonProperties;

public class EmailAddressAdapter extends ResourceCursorAdapter {
    private static String PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY = null;
    public static final int PRE_HONEYCOMB_DATA_INDEX = 2;
    public static final int PRE_HONEYCOMB_NAME_INDEX = 1;
    private static final String[] vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = HoneycombMR1Util.getEmailAdapterProjection();
    private Context context;
    private ContentResolver hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    static {
        new String[]{"_id", CommonProperties.NAME, "data"};
    }

    public EmailAddressAdapter(Context context2) {
        super(context2, 17367050, (Cursor) null);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = context2.getContentResolver();
        this.context = context2;
        PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY = "times_contacted DESC, display_name";
    }

    public final String convertToString(Cursor cursor) {
        return new Rfc822Token(cursor.getString(cursor.getColumnIndex("display_name")), cursor.getString(cursor.getColumnIndex("data1")), (String) null).toString();
    }

    public final void bindView(View view, Context context2, Cursor cursor) {
        boolean z;
        TextView textView = (TextView) view;
        int columnIndex = cursor.getColumnIndex("display_name");
        int columnIndex2 = cursor.getColumnIndex("data1");
        StringBuilder sb = new StringBuilder();
        String string = cursor.getString(columnIndex);
        String string2 = cursor.getString(columnIndex2);
        if (!TextUtils.isEmpty(string)) {
            sb.append(string);
            z = true;
        } else {
            z = false;
        }
        if (z) {
            sb.append(" <");
        }
        sb.append(string2);
        if (z) {
            sb.append(">");
        }
        textView.setText(sb.toString());
    }

    public Cursor runQueryOnBackgroundThread(CharSequence charSequence) {
        Uri uri;
        StringBuilder sb = new StringBuilder();
        if (charSequence != null) {
            String sqlEscapeString = DatabaseUtils.sqlEscapeString(charSequence.toString() + '%');
            uri = ContactsContract.Data.CONTENT_URI;
            sb.append("(mimetype='vnd.android.cursor.item/email_v2')");
            sb.append(" AND ");
            sb.append("(display_name LIKE ");
            sb.append(sqlEscapeString);
            sb.append(")");
        } else {
            uri = null;
        }
        String sb2 = sb.toString();
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.query(uri, vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq, sb2, (String[]) null, PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY);
    }
}
