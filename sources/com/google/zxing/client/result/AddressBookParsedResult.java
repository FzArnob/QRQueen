package com.google.zxing.client.result;

public final class AddressBookParsedResult extends ParsedResult {
    private final String[] addressTypes;
    private final String[] addresses;
    private final String birthday;
    private final String[] emailTypes;
    private final String[] emails;
    private final String instantMessenger;
    private final String[] names;
    private final String note;

    /* renamed from: org  reason: collision with root package name */
    private final String f344org;
    private final String[] phoneNumbers;
    private final String[] phoneTypes;
    private final String pronunciation;
    private final String title;
    private final String url;

    public AddressBookParsedResult(String[] strArr, String str, String[] strArr2, String[] strArr3, String[] strArr4, String[] strArr5, String str2, String str3, String[] strArr6, String[] strArr7, String str4, String str5, String str6, String str7) {
        super(ParsedResultType.ADDRESSBOOK);
        this.names = strArr;
        this.pronunciation = str;
        this.phoneNumbers = strArr2;
        this.phoneTypes = strArr3;
        this.emails = strArr4;
        this.emailTypes = strArr5;
        this.instantMessenger = str2;
        this.note = str3;
        this.addresses = strArr6;
        this.addressTypes = strArr7;
        this.f344org = str4;
        this.birthday = str5;
        this.title = str6;
        this.url = str7;
    }

    public String[] getNames() {
        return this.names;
    }

    public String getPronunciation() {
        return this.pronunciation;
    }

    public String[] getPhoneNumbers() {
        return this.phoneNumbers;
    }

    public String[] getPhoneTypes() {
        return this.phoneTypes;
    }

    public String[] getEmails() {
        return this.emails;
    }

    public String[] getEmailTypes() {
        return this.emailTypes;
    }

    public String getInstantMessenger() {
        return this.instantMessenger;
    }

    public String getNote() {
        return this.note;
    }

    public String[] getAddresses() {
        return this.addresses;
    }

    public String[] getAddressTypes() {
        return this.addressTypes;
    }

    public String getTitle() {
        return this.title;
    }

    public String getOrg() {
        return this.f344org;
    }

    public String getURL() {
        return this.url;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public String getDisplayResult() {
        StringBuilder sb = new StringBuilder(100);
        maybeAppend(this.names, sb);
        maybeAppend(this.pronunciation, sb);
        maybeAppend(this.title, sb);
        maybeAppend(this.f344org, sb);
        maybeAppend(this.addresses, sb);
        maybeAppend(this.phoneNumbers, sb);
        maybeAppend(this.emails, sb);
        maybeAppend(this.instantMessenger, sb);
        maybeAppend(this.url, sb);
        maybeAppend(this.birthday, sb);
        maybeAppend(this.note, sb);
        return sb.toString();
    }
}
