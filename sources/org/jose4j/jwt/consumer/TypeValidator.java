package org.jose4j.jwt.consumer;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;
import net.lingala.zip4j.util.InternalZipConstants;
import org.jose4j.jwt.consumer.ErrorCodeValidator;
import org.jose4j.lang.UncheckedJoseException;
import org.slf4j.Marker;

public class TypeValidator implements ErrorCodeValidator {
    private static final String APPLICATION_PRIMARY_TYPE = "application";
    private MimeType expectedType;
    private boolean requireType;

    public TypeValidator(boolean z, String str) {
        try {
            MimeType mediaType = toMediaType(str);
            this.expectedType = mediaType;
            if (!mediaType.getSubType().equals(Marker.ANY_MARKER)) {
                this.requireType = z;
                return;
            }
            throw new MimeTypeParseException("cannot use wildcard in subtype");
        } catch (MimeTypeParseException e) {
            throw new UncheckedJoseException("The given expected type '" + str + "' isn't a valid media type in this context.", e);
        }
    }

    public ErrorCodeValidator.Error validate(JwtContext jwtContext) {
        return validate(jwtContext.getJoseObjects().get(0).getHeader("typ"));
    }

    /* access modifiers changed from: package-private */
    public ErrorCodeValidator.Error validate(String str) {
        if (str == null) {
            if (this.requireType) {
                return new ErrorCodeValidator.Error(21, "No typ header parameter present in the innermost JWS/JWE");
            }
            return null;
        } else if (this.expectedType == null) {
            return null;
        } else {
            try {
                MimeType mediaType = toMediaType(str);
                if (this.expectedType.match(mediaType) && !mediaType.getSubType().equals(Marker.ANY_MARKER)) {
                    return null;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid typ header parameter value '");
                sb.append(str);
                sb.append("'. Expecting '");
                sb.append(this.expectedType);
                sb.append("'");
                if (this.expectedType.getPrimaryType().equals(APPLICATION_PRIMARY_TYPE)) {
                    sb.append(" or just '");
                    sb.append(this.expectedType.getSubType());
                    sb.append("'");
                }
                sb.append(".");
                return new ErrorCodeValidator.Error(22, sb.toString());
            } catch (MimeTypeParseException e) {
                return new ErrorCodeValidator.Error(22, "typ header parameter value '" + str + "' not parsable as a media type " + e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public MimeType toMediaType(String str) throws MimeTypeParseException {
        return str.contains(InternalZipConstants.ZIP_FILE_SEPARATOR) ? new MimeType(str) : new MimeType(APPLICATION_PRIMARY_TYPE, str);
    }
}
