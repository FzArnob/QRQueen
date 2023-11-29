package org.jose4j.jwt;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jose4j.base64url.Base64Url;
import org.jose4j.json.JsonUtil;
import org.jose4j.jwt.consumer.ErrorCodeValidator;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtContext;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.JoseException;

public class JwtClaims {
    private Map<String, Object> claimsMap;
    private String rawJson;

    public JwtClaims() {
        this.claimsMap = new LinkedHashMap();
    }

    private JwtClaims(String str, JwtContext jwtContext) throws InvalidJwtException {
        this.rawJson = str;
        try {
            this.claimsMap = new LinkedHashMap(JsonUtil.parseJson(str));
        } catch (JoseException e) {
            throw new InvalidJwtException("Unable to parse what was expected to be the JWT Claim Set JSON: \"" + str + "\"", new ErrorCodeValidator.Error(16, "Invalid JSON."), e, jwtContext);
        }
    }

    public static JwtClaims parse(String str, JwtContext jwtContext) throws InvalidJwtException {
        return new JwtClaims(str, jwtContext);
    }

    public static JwtClaims parse(String str) throws InvalidJwtException {
        return new JwtClaims(str, (JwtContext) null);
    }

    public String getIssuer() throws MalformedClaimException {
        return (String) getClaimValue(ReservedClaimNames.ISSUER, String.class);
    }

    public void setIssuer(String str) {
        this.claimsMap.put(ReservedClaimNames.ISSUER, str);
    }

    public String getSubject() throws MalformedClaimException {
        return (String) getClaimValue(ReservedClaimNames.SUBJECT, String.class);
    }

    public void setSubject(String str) {
        this.claimsMap.put(ReservedClaimNames.SUBJECT, str);
    }

    public void setAudience(String str) {
        this.claimsMap.put(ReservedClaimNames.AUDIENCE, str);
    }

    public void setAudience(String... strArr) {
        setAudience((List<String>) Arrays.asList(strArr));
    }

    public void setAudience(List<String> list) {
        if (list.size() == 1) {
            setAudience(list.get(0));
        } else {
            this.claimsMap.put(ReservedClaimNames.AUDIENCE, list);
        }
    }

    public boolean hasAudience() {
        return hasClaim(ReservedClaimNames.AUDIENCE);
    }

    public List<String> getAudience() throws MalformedClaimException {
        Object obj = this.claimsMap.get(ReservedClaimNames.AUDIENCE);
        if (obj instanceof String) {
            return Collections.singletonList((String) obj);
        }
        if ((obj instanceof List) || obj == null) {
            return toStringList((List) obj, ReservedClaimNames.AUDIENCE);
        }
        throw new MalformedClaimException("The value of the 'aud' claim is not an array of strings or a single string value.");
    }

    private List<String> toStringList(List list, String str) throws MalformedClaimException {
        if (list == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (Object next : list) {
            try {
                arrayList.add((String) next);
            } catch (ClassCastException e) {
                throw new MalformedClaimException("The array value of the '" + str + "' claim contains non string values " + classCastMsg(e, next), e);
            }
        }
        return arrayList;
    }

    public NumericDate getExpirationTime() throws MalformedClaimException {
        return getNumericDateClaimValue(ReservedClaimNames.EXPIRATION_TIME);
    }

    public void setExpirationTime(NumericDate numericDate) {
        setNumericDateClaim(ReservedClaimNames.EXPIRATION_TIME, numericDate);
    }

    public void setExpirationTimeMinutesInTheFuture(float f) {
        setExpirationTime(offsetFromNow(f));
    }

    private NumericDate offsetFromNow(float f) {
        NumericDate now = NumericDate.now();
        now.addSeconds((long) (f * 60.0f));
        return now;
    }

    public NumericDate getNotBefore() throws MalformedClaimException {
        return getNumericDateClaimValue(ReservedClaimNames.NOT_BEFORE);
    }

    public void setNotBefore(NumericDate numericDate) {
        setNumericDateClaim(ReservedClaimNames.NOT_BEFORE, numericDate);
    }

    public void setNotBeforeMinutesInThePast(float f) {
        setNotBefore(offsetFromNow(f * -1.0f));
    }

    public NumericDate getIssuedAt() throws MalformedClaimException {
        return getNumericDateClaimValue(ReservedClaimNames.ISSUED_AT);
    }

    public void setIssuedAt(NumericDate numericDate) {
        setNumericDateClaim(ReservedClaimNames.ISSUED_AT, numericDate);
    }

    public void setIssuedAtToNow() {
        setIssuedAt(NumericDate.now());
    }

    public String getJwtId() throws MalformedClaimException {
        return (String) getClaimValue(ReservedClaimNames.JWT_ID, String.class);
    }

    public void setJwtId(String str) {
        this.claimsMap.put(ReservedClaimNames.JWT_ID, str);
    }

    public void setGeneratedJwtId(int i) {
        setJwtId(Base64Url.encode(ByteUtil.randomBytes(i)));
    }

    public void setGeneratedJwtId() {
        setGeneratedJwtId(16);
    }

    public void unsetClaim(String str) {
        this.claimsMap.remove(str);
    }

    public <T> T getClaimValue(String str, Class<T> cls) throws MalformedClaimException {
        Object obj = this.claimsMap.get(str);
        try {
            return cls.cast(obj);
        } catch (ClassCastException e) {
            throw new MalformedClaimException("The value of the '" + str + "' claim is not the expected type " + classCastMsg(e, obj), e);
        }
    }

    public Object getClaimValue(String str) {
        return this.claimsMap.get(str);
    }

    public boolean hasClaim(String str) {
        return getClaimValue(str) != null;
    }

    private String classCastMsg(ClassCastException classCastException, Object obj) {
        return "(" + obj + " - " + classCastException.getMessage() + ")";
    }

    public NumericDate getNumericDateClaimValue(String str) throws MalformedClaimException {
        Number number = (Number) getClaimValue(str, Number.class);
        if (number instanceof BigInteger) {
            throw new MalformedClaimException(number + " is unreasonable for a NumericDate");
        } else if (number != null) {
            return NumericDate.fromSeconds(number.longValue());
        } else {
            return null;
        }
    }

    public String getStringClaimValue(String str) throws MalformedClaimException {
        return (String) getClaimValue(str, String.class);
    }

    public String getClaimValueAsString(String str) {
        Object claimValue = getClaimValue(str);
        if (claimValue != null) {
            return claimValue.toString();
        }
        return null;
    }

    public List<String> getStringListClaimValue(String str) throws MalformedClaimException {
        return toStringList((List) getClaimValue(str, List.class), str);
    }

    public void setNumericDateClaim(String str, NumericDate numericDate) {
        this.claimsMap.put(str, numericDate != null ? Long.valueOf(numericDate.getValue()) : null);
    }

    public void setStringClaim(String str, String str2) {
        this.claimsMap.put(str, str2);
    }

    public void setStringListClaim(String str, List<String> list) {
        this.claimsMap.put(str, list);
    }

    public void setStringListClaim(String str, String... strArr) {
        this.claimsMap.put(str, Arrays.asList(strArr));
    }

    public void setClaim(String str, Object obj) {
        this.claimsMap.put(str, obj);
    }

    public boolean isClaimValueOfType(String str, Class cls) {
        try {
            return getClaimValue(str, cls) != null;
        } catch (MalformedClaimException unused) {
            return false;
        }
    }

    public boolean isClaimValueString(String str) {
        return isClaimValueOfType(str, String.class);
    }

    public boolean isClaimValueStringList(String str) {
        try {
            return hasClaim(str) && getStringListClaimValue(str) != null;
        } catch (MalformedClaimException unused) {
            return false;
        }
    }

    public Map<String, List<Object>> flattenClaims() {
        return flattenClaims((Set<String>) null);
    }

    public Map<String, List<Object>> flattenClaims(Set<String> set) {
        if (set == null) {
            set = Collections.emptySet();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry next : this.claimsMap.entrySet()) {
            String str = (String) next.getKey();
            if (!set.contains(str)) {
                dfs((String) null, str, next.getValue(), linkedHashMap);
            }
        }
        return linkedHashMap;
    }

    private void dfs(String str, String str2, Object obj, Map<String, List<Object>> map) {
        String str3;
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str3 = "";
        } else {
            str3 = str + ".";
        }
        sb.append(str3);
        sb.append(str2);
        String sb2 = sb.toString();
        if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            for (Object next : (List) obj) {
                if (next instanceof Map) {
                    for (Map.Entry entry : ((Map) next).entrySet()) {
                        dfs(sb2, entry.getKey().toString(), entry.getValue(), map);
                    }
                } else {
                    arrayList.add(next);
                }
            }
            map.put(sb2, arrayList);
        } else if (obj instanceof Map) {
            for (Map.Entry entry2 : ((Map) obj).entrySet()) {
                dfs(sb2, entry2.getKey().toString(), entry2.getValue(), map);
            }
        } else {
            map.put(sb2, Collections.singletonList(obj));
        }
    }

    public Map<String, Object> getClaimsMap(Set<String> set) {
        if (set == null) {
            set = Collections.emptySet();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.claimsMap);
        for (String remove : set) {
            linkedHashMap.remove(remove);
        }
        return linkedHashMap;
    }

    public Map<String, Object> getClaimsMap() {
        return getClaimsMap((Set<String>) null);
    }

    public Collection<String> getClaimNames(Set<String> set) {
        return getClaimsMap(set).keySet();
    }

    public Collection<String> getClaimNames() {
        return getClaimNames((Set<String>) null);
    }

    public String toJson() {
        return JsonUtil.toJson(this.claimsMap);
    }

    public String getRawJson() {
        return this.rawJson;
    }

    public String toString() {
        return "JWT Claims Set:" + this.claimsMap;
    }
}
