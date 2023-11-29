package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SimpleObject
@DesignerComponent(category = ComponentCategory.SOCIAL, description = "A button that, when clicked on, displays a list of the contacts to choose among. After the user has made a selection, the following properties will be set to information about the chosen contact: <ul>\n<li> <code>ContactName</code>: the contact's name </li>\n <li> <code>EmailAddress</code>: the contact's primary email address </li>\n <li> <code>ContactUri</code>: the contact's URI on the device </li>\n<li> <code>EmailAddressList</code>: a list of the contact's email addresses </li>\n <li> <code>PhoneNumber</code>: the contact's primary phone number (on Later Android Verisons)</li>\n <li> <code>PhoneNumberList</code>: a list of the contact's phone numbers (on Later Android Versions)</li>\n <li> <code>Picture</code>: the name of the file containing the contact's image, which can be used as a <code>Picture</code> property value for the <code>Image</code> or <code>ImageSprite</code> component.</li></ul>\n</p><p>Other properties affect the appearance of the button (<code>TextAlignment</code>, <code>BackgroundColor</code>, etc.) and whether it can be clicked on (<code>Enabled</code>).\n</p><p>The ContactPicker component might not work on all phones. For example, on Android systems before system 3.0, it cannot pick phone numbers, and the list of email addresses will contain only one email.", version = 12)
@UsesPermissions({"android.permission.READ_CONTACTS"})
public class ContactPicker extends Picker implements ActivityResultListener {
    private static String[] B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private static String[] hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T  reason: collision with other field name */
    private final Uri f74B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    protected final Activity activityContext;
    protected String contactName;
    protected String contactPictureUri;
    protected String contactUri;
    protected String emailAddress;
    protected List emailAddressList;
    /* access modifiers changed from: private */
    public boolean havePermission;
    protected String phoneNumber;
    protected List phoneNumberList;

    /* access modifiers changed from: protected */
    public String ensureNotNull(String str) {
        return str == null ? "" : str;
    }

    static {
        new String[]{CommonProperties.NAME, "primary_email"};
    }

    public ContactPicker(ComponentContainer componentContainer) {
        this(componentContainer, Contacts.People.CONTENT_URI);
    }

    protected ContactPicker(ComponentContainer componentContainer, Uri uri) {
        super(componentContainer);
        this.havePermission = false;
        this.activityContext = componentContainer.$context();
        if (uri.equals(Contacts.People.CONTENT_URI)) {
            this.f74B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = ContactsContract.Contacts.CONTENT_URI;
        } else if (uri.equals(Contacts.Phones.CONTENT_URI)) {
            this.f74B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        } else {
            this.f74B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = uri;
        }
    }

    public void click() {
        if (!this.havePermission) {
            this.container.$form().askPermission("android.permission.READ_CONTACTS", new PermissionResultHandler() {
                public final void HandlePermissionResponse(String str, boolean z) {
                    if (z) {
                        boolean unused = ContactPicker.this.havePermission = true;
                        ContactPicker.this.click();
                        return;
                    }
                    ContactPicker.this.container.$form().dispatchPermissionDeniedEvent((Component) ContactPicker.this, "Click", "android.permission.READ_CONTACTS");
                }
            });
        } else {
            super.click();
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String Picture() {
        return ensureNotNull(this.contactPictureUri);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String ContactName() {
        return ensureNotNull(this.contactName);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String EmailAddress() {
        return ensureNotNull(this.emailAddress);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "URI that specifies the location of the contact on the device.")
    public String ContactUri() {
        return ensureNotNull(this.contactUri);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public List EmailAddressList() {
        return ensureNotNull(this.emailAddressList);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String PhoneNumber() {
        return ensureNotNull(this.phoneNumber);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public List PhoneNumberList() {
        return ensureNotNull(this.phoneNumberList);
    }

    @SimpleFunction(description = "view a contact via its URI")
    public void ViewContact(String str) {
        if (this.contactUri != null) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            if (intent.resolveActivity(this.activityContext.getPackageManager()) != null) {
                this.activityContext.startActivity(intent);
            }
        }
    }

    /* access modifiers changed from: protected */
    public Intent getIntent() {
        return new Intent("android.intent.action.PICK", this.f74B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00c3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void resultReturned(int r8, int r9, android.content.Intent r10) {
        /*
            r7 = this;
            int r0 = r7.requestCode
            if (r8 != r0) goto L_0x00cc
            r8 = -1
            if (r9 != r8) goto L_0x00cc
            java.lang.String r8 = java.lang.String.valueOf(r10)
            java.lang.String r9 = "received intent is "
            java.lang.String r8 = r9.concat(r8)
            java.lang.String r9 = "ContactPicker"
            android.util.Log.i(r9, r8)
            android.net.Uri r8 = r10.getData()
            java.lang.String r10 = "//com.android.contacts/contact"
            boolean r10 = r7.checkContactUri(r8, r10)
            if (r10 == 0) goto L_0x00c7
            r10 = 0
            java.lang.String[] r0 = com.google.appinventor.components.runtime.util.HoneycombMR1Util.getContactProjection()     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r0     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            android.app.Activity r0 = r7.activityContext     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            java.lang.String[] r2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            r3 = 0
            r4 = 0
            r5 = 0
            r1 = r8
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x00a2, all -> 0x009f }
            java.lang.String r1 = r7.postHoneycombGetContactNameAndPicture(r0)     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            java.lang.String[] r2 = com.google.appinventor.components.runtime.util.HoneycombMR1Util.getDataProjection()     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r2     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            android.app.Activity r3 = r7.activityContext     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            android.database.Cursor r10 = com.google.appinventor.components.runtime.util.HoneycombMR1Util.getDataCursor(r1, r3, r2)     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            r7.postHoneycombGetContactEmailAndPhone(r10)     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            r7.contactUri = r8     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            java.lang.String r1 = "Contact name = "
            r8.<init>(r1)     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            java.lang.String r1 = r7.contactName     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            r8.append(r1)     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            java.lang.String r1 = ", email address = "
            r8.append(r1)     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            java.lang.String r1 = r7.emailAddress     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            r8.append(r1)     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            java.lang.String r1 = ",contact Uri = "
            r8.append(r1)     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            java.lang.String r1 = r7.contactUri     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            r8.append(r1)     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            java.lang.String r1 = ", phone number = "
            r8.append(r1)     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            java.lang.String r1 = r7.phoneNumber     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            r8.append(r1)     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            java.lang.String r1 = ", contactPhotoUri = "
            r8.append(r1)     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            java.lang.String r1 = r7.contactPictureUri     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            r8.append(r1)     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            android.util.Log.i(r9, r8)     // Catch:{ Exception -> 0x009c, all -> 0x0098 }
            if (r0 == 0) goto L_0x0092
            r0.close()
        L_0x0092:
            if (r10 == 0) goto L_0x00c7
            r10.close()
            goto L_0x00c7
        L_0x0098:
            r8 = move-exception
            r9 = r10
            r10 = r0
            goto L_0x00bc
        L_0x009c:
            r8 = r10
            r10 = r0
            goto L_0x00a3
        L_0x009f:
            r8 = move-exception
            r9 = r10
            goto L_0x00bc
        L_0x00a2:
            r8 = r10
        L_0x00a3:
            java.lang.String r0 = "checkContactUri failed: D"
            android.util.Log.i(r9, r0)     // Catch:{ all -> 0x00b8 }
            r9 = 1107(0x453, float:1.551E-42)
            r7.puntContactSelection(r9)     // Catch:{ all -> 0x00b8 }
            if (r10 == 0) goto L_0x00b2
            r10.close()
        L_0x00b2:
            if (r8 == 0) goto L_0x00c7
            r8.close()
            goto L_0x00c7
        L_0x00b8:
            r9 = move-exception
            r6 = r9
            r9 = r8
            r8 = r6
        L_0x00bc:
            if (r10 == 0) goto L_0x00c1
            r10.close()
        L_0x00c1:
            if (r9 == 0) goto L_0x00c6
            r9.close()
        L_0x00c6:
            throw r8
        L_0x00c7:
            java.lang.String r8 = "The ContactPicker component have no 'After Picking' return value."
            r7.AfterPicking(r8)
        L_0x00cc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.ContactPicker.resultReturned(int, int, android.content.Intent):void");
    }

    public void preHoneycombGetContactInfo(Cursor cursor, Uri uri) {
        List list;
        if (cursor.moveToFirst()) {
            this.contactName = guardCursorGetString(cursor, 0);
            this.emailAddress = getEmailAddress(guardCursorGetString(cursor, 1));
            this.contactUri = uri.toString();
            this.contactPictureUri = uri.toString();
            if (this.emailAddress.equals("")) {
                list = new ArrayList();
            } else {
                list = Arrays.asList(new String[]{this.emailAddress});
            }
            this.emailAddressList = list;
        }
    }

    public String postHoneycombGetContactNameAndPicture(Cursor cursor) {
        if (!cursor.moveToFirst()) {
            return "";
        }
        int columnIndex = cursor.getColumnIndex("_id");
        int columnIndex2 = cursor.getColumnIndex("display_name");
        int columnIndex3 = cursor.getColumnIndex("photo_thumb_uri");
        int columnIndex4 = cursor.getColumnIndex("photo_uri");
        String guardCursorGetString = guardCursorGetString(cursor, columnIndex);
        this.contactName = guardCursorGetString(cursor, columnIndex2);
        this.contactPictureUri = guardCursorGetString(cursor, columnIndex3);
        Log.i("ContactPicker", "photo_uri=" + guardCursorGetString(cursor, columnIndex4));
        return guardCursorGetString;
    }

    public void postHoneycombGetContactEmailAndPhone(Cursor cursor) {
        this.phoneNumber = "";
        this.emailAddress = "";
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
        }
        if (!arrayList.isEmpty()) {
            this.phoneNumber = (String) arrayList.get(0);
        }
        if (!arrayList2.isEmpty()) {
            this.emailAddress = (String) arrayList2.get(0);
        }
        this.phoneNumberList = arrayList;
        this.emailAddressList = arrayList2;
    }

    /* access modifiers changed from: protected */
    public boolean checkContactUri(Uri uri, String str) {
        Log.i("ContactPicker", "contactUri is ".concat(String.valueOf(uri)));
        if (uri == null || !"content".equals(uri.getScheme())) {
            Log.i("ContactPicker", "checkContactUri failed: A");
            puntContactSelection(ErrorMessages.ERROR_PHONE_UNSUPPORTED_CONTACT_PICKER);
            return false;
        } else if (uri.getSchemeSpecificPart().startsWith(str)) {
            return true;
        } else {
            Log.i("ContactPicker", "checkContactUri failed: C");
            Log.i("ContactPicker", uri.getPath());
            puntContactSelection(ErrorMessages.ERROR_PHONE_UNSUPPORTED_CONTACT_PICKER);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void puntContactSelection(int i) {
        this.contactName = "";
        this.emailAddress = "";
        this.contactPictureUri = "";
        this.container.$form().dispatchErrorOccurredEvent(this, "", i, new Object[0]);
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public String getEmailAddress(String str) {
        String str2 = "";
        try {
            String concat = "contact_methods._id = ".concat(String.valueOf(Integer.parseInt(str)));
            Cursor query = this.activityContext.getContentResolver().query(Contacts.ContactMethods.CONTENT_EMAIL_URI, new String[]{"data"}, concat, (String[]) null, (String) null);
            try {
                if (query.moveToFirst()) {
                    str2 = guardCursorGetString(query, 0);
                }
                query.close();
                return ensureNotNull(str2);
            } catch (Throwable th) {
                query.close();
                throw th;
            }
        } catch (NumberFormatException unused) {
            return str2;
        }
    }

    /* access modifiers changed from: protected */
    public String guardCursorGetString(Cursor cursor, int i) {
        String str;
        try {
            str = cursor.getString(i);
        } catch (Exception unused) {
            str = "";
        }
        return ensureNotNull(str);
    }

    /* access modifiers changed from: protected */
    public List ensureNotNull(List list) {
        return list == null ? new ArrayList() : list;
    }
}
