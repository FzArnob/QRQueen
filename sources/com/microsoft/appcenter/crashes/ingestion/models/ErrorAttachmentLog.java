package com.microsoft.appcenter.crashes.ingestion.models;

import android.util.Base64;
import com.microsoft.appcenter.ingestion.models.AbstractLog;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import com.microsoft.appcenter.ingestion.models.json.JSONUtils;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class ErrorAttachmentLog extends AbstractLog {
    static final Charset CHARSET = Charset.forName("UTF-8");
    private static final String CONTENT_TYPE = "contentType";
    public static final String CONTENT_TYPE_TEXT_PLAIN = "text/plain";
    static final String DATA = "data";
    private static final String ERROR_ID = "errorId";
    private static final String FILE_NAME = "fileName";
    public static final String TYPE = "errorAttachment";
    private String contentType;
    private byte[] data;
    private UUID errorId;
    private String fileName;
    private UUID id;

    public String getType() {
        return TYPE;
    }

    public static ErrorAttachmentLog attachmentWithText(String str, String str2) {
        if (str == null) {
            str = "";
        }
        return attachmentWithBinary(str.getBytes(CHARSET), str2, "text/plain");
    }

    public static ErrorAttachmentLog attachmentWithBinary(byte[] bArr, String str, String str2) {
        ErrorAttachmentLog errorAttachmentLog = new ErrorAttachmentLog();
        errorAttachmentLog.setData(bArr);
        errorAttachmentLog.setFileName(str);
        errorAttachmentLog.setContentType(str2);
        return errorAttachmentLog;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID uuid) {
        this.id = uuid;
    }

    public UUID getErrorId() {
        return this.errorId;
    }

    public void setErrorId(UUID uuid) {
        this.errorId = uuid;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String str) {
        this.contentType = str;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] bArr) {
        this.data = bArr;
    }

    public boolean isValid() {
        return (getId() == null || getErrorId() == null || getContentType() == null || getData() == null) ? false : true;
    }

    public void read(JSONObject jSONObject) throws JSONException {
        super.read(jSONObject);
        setId(UUID.fromString(jSONObject.getString(CommonProperties.ID)));
        setErrorId(UUID.fromString(jSONObject.getString(ERROR_ID)));
        setContentType(jSONObject.getString(CONTENT_TYPE));
        setFileName(jSONObject.optString(FILE_NAME, (String) null));
        try {
            setData(Base64.decode(jSONObject.getString(DATA), 0));
        } catch (IllegalArgumentException e) {
            throw new JSONException(e.getMessage());
        }
    }

    public void write(JSONStringer jSONStringer) throws JSONException {
        super.write(jSONStringer);
        JSONUtils.write(jSONStringer, CommonProperties.ID, getId());
        JSONUtils.write(jSONStringer, ERROR_ID, getErrorId());
        JSONUtils.write(jSONStringer, CONTENT_TYPE, getContentType());
        JSONUtils.write(jSONStringer, FILE_NAME, getFileName());
        JSONUtils.write(jSONStringer, DATA, Base64.encodeToString(getData(), 2));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        ErrorAttachmentLog errorAttachmentLog = (ErrorAttachmentLog) obj;
        UUID uuid = this.id;
        if (uuid == null ? errorAttachmentLog.id != null : !uuid.equals(errorAttachmentLog.id)) {
            return false;
        }
        UUID uuid2 = this.errorId;
        if (uuid2 == null ? errorAttachmentLog.errorId != null : !uuid2.equals(errorAttachmentLog.errorId)) {
            return false;
        }
        String str = this.contentType;
        if (str == null ? errorAttachmentLog.contentType != null : !str.equals(errorAttachmentLog.contentType)) {
            return false;
        }
        String str2 = this.fileName;
        if (str2 == null ? errorAttachmentLog.fileName == null : str2.equals(errorAttachmentLog.fileName)) {
            return Arrays.equals(this.data, errorAttachmentLog.data);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        UUID uuid = this.id;
        int i = 0;
        int hashCode2 = (hashCode + (uuid != null ? uuid.hashCode() : 0)) * 31;
        UUID uuid2 = this.errorId;
        int hashCode3 = (hashCode2 + (uuid2 != null ? uuid2.hashCode() : 0)) * 31;
        String str = this.contentType;
        int hashCode4 = (hashCode3 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.fileName;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return ((hashCode4 + i) * 31) + Arrays.hashCode(this.data);
    }
}
