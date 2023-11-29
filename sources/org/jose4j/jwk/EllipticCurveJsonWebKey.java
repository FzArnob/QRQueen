package org.jose4j.jwk;

import java.math.BigInteger;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.util.HashMap;
import java.util.Map;
import org.jose4j.keys.EcKeyUtil;
import org.jose4j.keys.EllipticCurves;
import org.jose4j.lang.JoseException;

public class EllipticCurveJsonWebKey extends PublicJsonWebKey {
    public static final String CURVE_MEMBER_NAME = "crv";
    public static final String KEY_TYPE = "EC";
    public static final String PRIVATE_KEY_MEMBER_NAME = "d";
    public static final String X_MEMBER_NAME = "x";
    public static final String Y_MEMBER_NAME = "y";
    private String curveName;

    public String getKeyType() {
        return "EC";
    }

    public EllipticCurveJsonWebKey(ECPublicKey eCPublicKey) {
        super((PublicKey) eCPublicKey);
        this.curveName = EllipticCurves.getName(eCPublicKey.getParams().getCurve());
    }

    public EllipticCurveJsonWebKey(Map<String, Object> map) throws JoseException {
        this(map, (String) null);
    }

    public EllipticCurveJsonWebKey(Map<String, Object> map, String str) throws JoseException {
        super(map, str);
        String string = getString(map, CURVE_MEMBER_NAME, true);
        this.curveName = string;
        ECParameterSpec spec = EllipticCurves.getSpec(string);
        BigInteger bigIntFromBase64UrlEncodedParam = getBigIntFromBase64UrlEncodedParam(map, X_MEMBER_NAME, true);
        BigInteger bigIntFromBase64UrlEncodedParam2 = getBigIntFromBase64UrlEncodedParam(map, Y_MEMBER_NAME, true);
        EcKeyUtil ecKeyUtil = new EcKeyUtil(str, (SecureRandom) null);
        this.key = ecKeyUtil.publicKey(bigIntFromBase64UrlEncodedParam, bigIntFromBase64UrlEncodedParam2, spec);
        checkForBareKeyCertMismatch();
        if (map.containsKey("d")) {
            this.privateKey = ecKeyUtil.privateKey(getBigIntFromBase64UrlEncodedParam(map, "d", false), spec);
        }
        removeFromOtherParams(CURVE_MEMBER_NAME, X_MEMBER_NAME, Y_MEMBER_NAME, "d");
    }

    public ECPublicKey getECPublicKey() {
        return (ECPublicKey) this.key;
    }

    public ECPrivateKey getEcPrivateKey() {
        return (ECPrivateKey) this.privateKey;
    }

    public String getCurveName() {
        return this.curveName;
    }

    private int getCoordinateByteLength() {
        return (int) Math.ceil(((double) EllipticCurves.getSpec(getCurveName()).getCurve().getField().getFieldSize()) / 8.0d);
    }

    /* access modifiers changed from: protected */
    public void fillPublicTypeSpecificParams(Map<String, Object> map) {
        ECPoint w = getECPublicKey().getW();
        int coordinateByteLength = getCoordinateByteLength();
        putBigIntAsBase64UrlEncodedParam(map, X_MEMBER_NAME, w.getAffineX(), coordinateByteLength);
        putBigIntAsBase64UrlEncodedParam(map, Y_MEMBER_NAME, w.getAffineY(), coordinateByteLength);
        map.put(CURVE_MEMBER_NAME, getCurveName());
    }

    /* access modifiers changed from: protected */
    public void fillPrivateTypeSpecificParams(Map<String, Object> map) {
        ECPrivateKey ecPrivateKey = getEcPrivateKey();
        if (ecPrivateKey != null) {
            putBigIntAsBase64UrlEncodedParam(map, "d", ecPrivateKey.getS(), getCoordinateByteLength());
        }
    }

    /* access modifiers changed from: protected */
    public String produceThumbprintHashInput() {
        HashMap hashMap = new HashMap();
        fillPublicTypeSpecificParams(hashMap);
        return String.format("{\"crv\":\"%s\",\"kty\":\"EC\",\"x\":\"%s\",\"y\":\"%s\"}", new Object[]{hashMap.get(CURVE_MEMBER_NAME), hashMap.get(X_MEMBER_NAME), hashMap.get(Y_MEMBER_NAME)});
    }
}
