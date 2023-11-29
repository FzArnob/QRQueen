package org.jose4j.jwx;

import java.util.LinkedHashMap;
import java.util.Map;
import org.jose4j.base64url.Base64Url;
import org.jose4j.json.JsonUtil;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.PublicJsonWebKey;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.JsonHelp;

public class Headers {
    protected Base64Url base64url = new Base64Url();
    private String encodedHeader;
    private String header;
    private Map<String, Object> headerMap = new LinkedHashMap();

    public String getFullHeaderAsJsonString() {
        if (this.header == null) {
            this.header = JsonUtil.toJson(this.headerMap);
        }
        return this.header;
    }

    public String getEncodedHeader() {
        if (this.encodedHeader == null) {
            this.encodedHeader = this.base64url.base64UrlEncodeUtf8ByteRepresentation(getFullHeaderAsJsonString());
        }
        return this.encodedHeader;
    }

    public void setStringHeaderValue(String str, String str2) {
        setObjectHeaderValue(str, str2);
    }

    public void setObjectHeaderValue(String str, Object obj) {
        this.headerMap.put(str, obj);
        this.header = null;
        this.encodedHeader = null;
    }

    public void setJwkHeaderValue(String str, JsonWebKey jsonWebKey) {
        setObjectHeaderValue(str, jsonWebKey.toParams(JsonWebKey.OutputControlLevel.PUBLIC_ONLY));
    }

    public String getStringHeaderValue(String str) {
        return JsonHelp.getString(this.headerMap, str);
    }

    public Long getLongHeaderValue(String str) {
        return JsonHelp.getLong(this.headerMap, str);
    }

    public Object getObjectHeaderValue(String str) {
        return this.headerMap.get(str);
    }

    public JsonWebKey getJwkHeaderValue(String str) throws JoseException {
        Map map = (Map) getObjectHeaderValue(str);
        if (map != null) {
            return JsonWebKey.Factory.newJwk((Map<String, Object>) map);
        }
        return null;
    }

    public PublicJsonWebKey getPublicJwkHeaderValue(String str, String str2) throws JoseException {
        Map map = (Map) getObjectHeaderValue(str);
        if (map != null) {
            return PublicJsonWebKey.Factory.newPublicJwk((Map<String, Object>) map, str2);
        }
        return null;
    }

    public void setFullHeaderAsJsonString(String str) throws JoseException {
        this.encodedHeader = null;
        this.header = str;
        this.headerMap = JsonUtil.parseJson(str);
    }

    /* access modifiers changed from: package-private */
    public void setEncodedHeader(String str) throws JoseException {
        this.encodedHeader = str;
        String base64UrlDecodeToUtf8String = this.base64url.base64UrlDecodeToUtf8String(str);
        this.header = base64UrlDecodeToUtf8String;
        this.headerMap = JsonUtil.parseJson(base64UrlDecodeToUtf8String);
    }
}
