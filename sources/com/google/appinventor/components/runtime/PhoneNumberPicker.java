package com.google.appinventor.components.runtime;

import android.content.ContentUris;
import android.database.Cursor;
import android.provider.Contacts;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.util.ArrayList;

@SimpleObject
@DesignerComponent(category = ComponentCategory.SOCIAL, description = "A button that, when clicked on, displays a list of the contacts' phone numbers to choose among. After the user has made a selection, the following properties will be set to information about the chosen contact: <ul>\n<li> <code>ContactName</code>: the contact's name </li>\n <li> <code>PhoneNumber</code>: the contact's phone number </li>\n <li> <code>EmailAddress</code>: the contact's email address </li> <li> <code>Picture</code>: the name of the file containing the contact's image, which can be used as a <code>Picture</code> property value for the <code>Image</code> or <code>ImageSprite</code> component.</li></ul>\n</p><p>Other properties affect the appearance of the button (<code>TextAlignment</code>, <code>BackgroundColor</code>, etc.) and whether it can be clicked on (<code>Enabled</code>).</p>\n<p>The PhoneNumberPicker component may not work on all Android devices. For example, on Android systems before system 3.0, the returned lists of phone numbers and email addresses will be empty.\n", version = 10)
@UsesPermissions({"android.permission.READ_CONTACTS"})
public class PhoneNumberPicker extends ContactPicker {
    private static String[] B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private static String[] mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT;

    static {
        new String[]{CommonProperties.NAME, "number", "person", "primary_email"};
    }

    public PhoneNumberPicker(ComponentContainer componentContainer) {
        super(componentContainer, Contacts.Phones.CONTENT_URI);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String PhoneNumber() {
        return ensureNotNull(this.phoneNumber);
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void resultReturned(int r8, int r9, android.content.Intent r10) {
        /*
            r7 = this;
            int r0 = r7.requestCode
            if (r8 != r0) goto L_0x00be
            r8 = -1
            if (r9 != r8) goto L_0x00be
            java.lang.String r8 = java.lang.String.valueOf(r10)
            java.lang.String r9 = "received intent is "
            java.lang.String r8 = r9.concat(r8)
            java.lang.String r9 = "PhoneNumberPicker"
            android.util.Log.i(r9, r8)
            android.net.Uri r1 = r10.getData()
            java.lang.String r8 = "//com.android.contacts/data"
            boolean r8 = r7.checkContactUri(r1, r8)
            if (r8 == 0) goto L_0x00b9
            r8 = 0
            java.lang.String[] r10 = com.google.appinventor.components.runtime.util.HoneycombMR1Util.getNameProjection()     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT = r10     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            android.app.Activity r10 = r7.activityContext     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            android.content.ContentResolver r0 = r10.getContentResolver()     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            java.lang.String[] r2 = mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            r3 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r10 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x0096, all -> 0x0093 }
            java.lang.String r0 = r7.postHoneycombGetContactNameAndPicture(r10)     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            java.lang.String[] r1 = com.google.appinventor.components.runtime.util.HoneycombMR1Util.getDataProjection()     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r1     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            android.app.Activity r1 = r7.activityContext     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            java.lang.String[] r2 = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            android.database.Cursor r8 = com.google.appinventor.components.runtime.util.HoneycombMR1Util.getDataCursor(r0, r1, r2)     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            r7.postHoneycombGetContactEmailsAndPhones(r8)     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            java.lang.String r1 = "Contact name = "
            r0.<init>(r1)     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            java.lang.String r1 = r7.contactName     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            r0.append(r1)     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            java.lang.String r1 = ", phone number = "
            r0.append(r1)     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            java.lang.String r1 = r7.phoneNumber     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            r0.append(r1)     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            java.lang.String r1 = ", emailAddress = "
            r0.append(r1)     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            java.lang.String r1 = r7.emailAddress     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            r0.append(r1)     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            java.lang.String r1 = ", contactPhotoUri = "
            r0.append(r1)     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            java.lang.String r1 = r7.contactPictureUri     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            r0.append(r1)     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            android.util.Log.i(r9, r0)     // Catch:{ Exception -> 0x008e, all -> 0x0089 }
            if (r10 == 0) goto L_0x0083
            r10.close()
        L_0x0083:
            if (r8 == 0) goto L_0x00b9
            r8.close()
            goto L_0x00b9
        L_0x0089:
            r9 = move-exception
            r6 = r10
            r10 = r8
            r8 = r6
            goto L_0x00ae
        L_0x008e:
            r0 = move-exception
            r6 = r10
            r10 = r8
            r8 = r6
            goto L_0x0098
        L_0x0093:
            r9 = move-exception
            r10 = r8
            goto L_0x00ae
        L_0x0096:
            r0 = move-exception
            r10 = r8
        L_0x0098:
            java.lang.String r1 = "Exception in resultReturned"
            android.util.Log.e(r9, r1, r0)     // Catch:{ all -> 0x00ad }
            r9 = 1107(0x453, float:1.551E-42)
            r7.puntContactSelection(r9)     // Catch:{ all -> 0x00ad }
            if (r8 == 0) goto L_0x00a7
            r8.close()
        L_0x00a7:
            if (r10 == 0) goto L_0x00b9
            r10.close()
            goto L_0x00b9
        L_0x00ad:
            r9 = move-exception
        L_0x00ae:
            if (r8 == 0) goto L_0x00b3
            r8.close()
        L_0x00b3:
            if (r10 == 0) goto L_0x00b8
            r10.close()
        L_0x00b8:
            throw r9
        L_0x00b9:
            java.lang.String r8 = "The PhoneNumberPicker component have no 'After Picking' return value."
            r7.AfterPicking(r8)
        L_0x00be:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.PhoneNumberPicker.resultReturned(int, int, android.content.Intent):void");
    }

    public void preHoneycombGetContactInfo(Cursor cursor) {
        if (cursor.moveToFirst()) {
            this.contactName = guardCursorGetString(cursor, 0);
            this.phoneNumber = guardCursorGetString(cursor, 1);
            this.contactPictureUri = ContentUris.withAppendedId(Contacts.People.CONTENT_URI, (long) cursor.getInt(2)).toString();
            this.emailAddress = getEmailAddress(guardCursorGetString(cursor, 3));
        }
    }

    public String postHoneycombGetContactNameAndPicture(Cursor cursor) {
        if (!cursor.moveToFirst()) {
            return "";
        }
        int columnIndex = cursor.getColumnIndex("contact_id");
        int columnIndex2 = cursor.getColumnIndex("display_name");
        int columnIndex3 = cursor.getColumnIndex("photo_thumb_uri");
        this.phoneNumber = guardCursorGetString(cursor, cursor.getColumnIndex("data1"));
        String guardCursorGetString = guardCursorGetString(cursor, columnIndex);
        this.contactName = guardCursorGetString(cursor, columnIndex2);
        this.contactPictureUri = guardCursorGetString(cursor, columnIndex3);
        return guardCursorGetString;
    }

    public void postHoneycombGetContactEmailsAndPhones(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("data1");
            int columnIndex2 = cursor.getColumnIndex("data1");
            int columnIndex3 = cursor.getColumnIndex("mimetype");
            while (!cursor.isAfterLast()) {
                String guardCursorGetString = guardCursorGetString(cursor, columnIndex3);
                if (guardCursorGetString.contains("vnd.android.cursor.item/phone_v2")) {
                    arrayList.add(guardCursorGetString(cursor, columnIndex));
                } else if (guardCursorGetString.contains("vnd.android.cursor.item/email_v2")) {
                    arrayList2.add(guardCursorGetString(cursor, columnIndex2));
                } else {
                    Log.i("ContactPicker", "Type mismatch: " + guardCursorGetString + " not " + "vnd.android.cursor.item/phone_v2" + " or " + "vnd.android.cursor.item/email_v2");
                }
                cursor.moveToNext();
            }
            this.phoneNumberList = arrayList;
            this.emailAddressList = arrayList2;
            if (!this.emailAddressList.isEmpty()) {
                this.emailAddress = (String) this.emailAddressList.get(0);
            } else {
                this.emailAddress = "";
            }
        }
    }
}
