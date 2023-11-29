package org.jose4j.jwt.consumer;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.ErrorCodeValidator;
import org.jose4j.jwx.JsonWebStructure;
import org.jose4j.keys.KeyPersuasion;
import org.jose4j.keys.resolvers.DecryptionKeyResolver;
import org.jose4j.keys.resolvers.VerificationKeyResolver;
import org.jose4j.lang.JoseException;

public class JwtConsumer {
    private DecryptionKeyResolver decryptionKeyResolver;
    private AlgorithmConstraints jweAlgorithmConstraints;
    private AlgorithmConstraints jweContentEncryptionAlgorithmConstraints;
    private JweCustomizer jweCustomizer;
    private ProviderContext jweProviderContext;
    private AlgorithmConstraints jwsAlgorithmConstraints;
    private JwsCustomizer jwsCustomizer;
    private ProviderContext jwsProviderContext;
    private boolean liberalContentTypeHandling;
    private boolean relaxDecryptionKeyValidation;
    private boolean relaxVerificationKeyValidation;
    private boolean requireEncryption;
    private boolean requireIntegrity;
    private boolean requireSignature = true;
    private boolean skipSignatureVerification;
    private boolean skipVerificationKeyResolutionOnNone;
    private List<ErrorCodeValidator> validators;
    private VerificationKeyResolver verificationKeyResolver;

    JwtConsumer() {
    }

    /* access modifiers changed from: package-private */
    public void setJwsAlgorithmConstraints(AlgorithmConstraints algorithmConstraints) {
        this.jwsAlgorithmConstraints = algorithmConstraints;
    }

    /* access modifiers changed from: package-private */
    public void setJweAlgorithmConstraints(AlgorithmConstraints algorithmConstraints) {
        this.jweAlgorithmConstraints = algorithmConstraints;
    }

    /* access modifiers changed from: package-private */
    public void setJweContentEncryptionAlgorithmConstraints(AlgorithmConstraints algorithmConstraints) {
        this.jweContentEncryptionAlgorithmConstraints = algorithmConstraints;
    }

    /* access modifiers changed from: package-private */
    public void setVerificationKeyResolver(VerificationKeyResolver verificationKeyResolver2) {
        this.verificationKeyResolver = verificationKeyResolver2;
    }

    /* access modifiers changed from: package-private */
    public void setDecryptionKeyResolver(DecryptionKeyResolver decryptionKeyResolver2) {
        this.decryptionKeyResolver = decryptionKeyResolver2;
    }

    /* access modifiers changed from: package-private */
    public void setValidators(List<ErrorCodeValidator> list) {
        this.validators = list;
    }

    /* access modifiers changed from: package-private */
    public void setRequireSignature(boolean z) {
        this.requireSignature = z;
    }

    /* access modifiers changed from: package-private */
    public void setRequireEncryption(boolean z) {
        this.requireEncryption = z;
    }

    /* access modifiers changed from: package-private */
    public void setRequireIntegrity(boolean z) {
        this.requireIntegrity = z;
    }

    /* access modifiers changed from: package-private */
    public void setLiberalContentTypeHandling(boolean z) {
        this.liberalContentTypeHandling = z;
    }

    /* access modifiers changed from: package-private */
    public void setSkipSignatureVerification(boolean z) {
        this.skipSignatureVerification = z;
    }

    /* access modifiers changed from: package-private */
    public void setRelaxVerificationKeyValidation(boolean z) {
        this.relaxVerificationKeyValidation = z;
    }

    public void setSkipVerificationKeyResolutionOnNone(boolean z) {
        this.skipVerificationKeyResolutionOnNone = z;
    }

    /* access modifiers changed from: package-private */
    public void setRelaxDecryptionKeyValidation(boolean z) {
        this.relaxDecryptionKeyValidation = z;
    }

    /* access modifiers changed from: package-private */
    public void setJwsProviderContext(ProviderContext providerContext) {
        this.jwsProviderContext = providerContext;
    }

    /* access modifiers changed from: package-private */
    public void setJweProviderContext(ProviderContext providerContext) {
        this.jweProviderContext = providerContext;
    }

    /* access modifiers changed from: package-private */
    public void setJwsCustomizer(JwsCustomizer jwsCustomizer2) {
        this.jwsCustomizer = jwsCustomizer2;
    }

    /* access modifiers changed from: package-private */
    public void setJweCustomizer(JweCustomizer jweCustomizer2) {
        this.jweCustomizer = jweCustomizer2;
    }

    public JwtClaims processToClaims(String str) throws InvalidJwtException {
        return process(str).getJwtClaims();
    }

    public void processContext(JwtContext jwtContext) throws InvalidJwtException {
        JwtContext jwtContext2 = jwtContext;
        ArrayList arrayList = new ArrayList(jwtContext.getJoseObjects());
        int size = arrayList.size() - 1;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (size >= 0) {
            List subList = arrayList.subList(size + 1, arrayList.size());
            List unmodifiableList = Collections.unmodifiableList(subList);
            JsonWebStructure jsonWebStructure = (JsonWebStructure) arrayList.get(size);
            try {
                if (jsonWebStructure instanceof JsonWebSignature) {
                    JsonWebSignature jsonWebSignature = (JsonWebSignature) jsonWebStructure;
                    boolean equals = AlgorithmIdentifiers.NONE.equals(jsonWebSignature.getAlgorithmHeaderValue());
                    if (!this.skipSignatureVerification) {
                        ProviderContext providerContext = this.jwsProviderContext;
                        if (providerContext != null) {
                            jsonWebSignature.setProviderContext(providerContext);
                        }
                        if (this.relaxVerificationKeyValidation) {
                            jsonWebSignature.setDoKeyValidation(false);
                        }
                        AlgorithmConstraints algorithmConstraints = this.jwsAlgorithmConstraints;
                        if (algorithmConstraints != null) {
                            jsonWebSignature.setAlgorithmConstraints(algorithmConstraints);
                        }
                        if (!equals || !this.skipVerificationKeyResolutionOnNone) {
                            jsonWebSignature.setKey(this.verificationKeyResolver.resolveKey(jsonWebSignature, unmodifiableList));
                        }
                        JwsCustomizer jwsCustomizer2 = this.jwsCustomizer;
                        if (jwsCustomizer2 != null) {
                            jwsCustomizer2.customize(jsonWebSignature, unmodifiableList);
                        }
                        if (!jsonWebSignature.verifySignature()) {
                            throw new InvalidJwtSignatureException(jsonWebSignature, jwtContext2);
                        }
                    }
                    if (!equals) {
                        z = true;
                    }
                } else {
                    JsonWebEncryption jsonWebEncryption = (JsonWebEncryption) jsonWebStructure;
                    Key resolveKey = this.decryptionKeyResolver.resolveKey(jsonWebEncryption, unmodifiableList);
                    if (resolveKey != null) {
                        if (!resolveKey.equals(jsonWebEncryption.getKey())) {
                            throw new InvalidJwtException("The resolved decryption key is different than the one originally used to decrypt the JWE.", Collections.singletonList(new ErrorCodeValidator.Error(17, "Key resolution problem.")), jwtContext2);
                        }
                    }
                    AlgorithmConstraints algorithmConstraints2 = this.jweAlgorithmConstraints;
                    if (algorithmConstraints2 != null) {
                        algorithmConstraints2.checkConstraint(jsonWebEncryption.getAlgorithmHeaderValue());
                    }
                    AlgorithmConstraints algorithmConstraints3 = this.jweContentEncryptionAlgorithmConstraints;
                    if (algorithmConstraints3 != null) {
                        algorithmConstraints3.checkConstraint(jsonWebEncryption.getEncryptionMethodHeaderParameter());
                    }
                    z3 = jsonWebEncryption.getKeyManagementModeAlgorithm().getKeyPersuasion() == KeyPersuasion.SYMMETRIC;
                    z2 = true;
                }
                size--;
            } catch (JoseException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to process");
                if (!subList.isEmpty()) {
                    sb.append(" nested");
                }
                sb.append(" JOSE object (cause: ");
                sb.append(e);
                sb.append("): ");
                sb.append(jsonWebStructure);
                throw new InvalidJwtException("JWT processing failed.", new ErrorCodeValidator.Error(17, sb.toString()), e, jwtContext2);
            } catch (InvalidJwtException e2) {
                throw e2;
            } catch (Exception e3) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Unexpected exception encountered while processing");
                if (!subList.isEmpty()) {
                    sb2.append(" nested");
                }
                sb2.append(" JOSE object (");
                sb2.append(e3);
                sb2.append("): ");
                sb2.append(jsonWebStructure);
                throw new InvalidJwtException("JWT processing failed.", new ErrorCodeValidator.Error(17, sb2.toString()), e3, jwtContext2);
            }
        }
        if (this.requireSignature && !z) {
            throw new InvalidJwtException("The JWT has no signature but the JWT Consumer is configured to require one: " + jwtContext.getJwt(), Collections.singletonList(new ErrorCodeValidator.Error(10, "Missing signature.")), jwtContext2);
        } else if (this.requireEncryption && !z2) {
            throw new InvalidJwtException("The JWT has no encryption but the JWT Consumer is configured to require it: " + jwtContext.getJwt(), Collections.singletonList(new ErrorCodeValidator.Error(19, "No encryption.")), jwtContext2);
        } else if (!this.requireIntegrity || z || z3) {
            validate(jwtContext);
        } else {
            throw new InvalidJwtException("The JWT has no integrity protection (signature/MAC or symmetric AEAD encryption) but the JWT Consumer is configured to require it: " + jwtContext.getJwt(), Collections.singletonList(new ErrorCodeValidator.Error(20, "Missing Integrity Protection")), jwtContext2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0081, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0082, code lost:
        r6 = new java.lang.StringBuilder();
        r6.append("Unexpected exception encountered while processing");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0090, code lost:
        if (r3.isEmpty() == false) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0092, code lost:
        r6.append(" nested");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0095, code lost:
        r6.append(" JOSE object (");
        r6.append(r13);
        r6.append("): ");
        r6.append(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00b1, code lost:
        throw new org.jose4j.jwt.consumer.InvalidJwtException("JWT processing failed.", new org.jose4j.jwt.consumer.ErrorCodeValidator.Error(17, r6.toString()), r13, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00b2, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00b3, code lost:
        throw r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00b4, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00b5, code lost:
        r6 = new java.lang.StringBuilder();
        r6.append("Unable to process");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00c3, code lost:
        if (r3.isEmpty() == false) goto L_0x00c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00c5, code lost:
        r6.append(" nested");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00c8, code lost:
        r6.append(" JOSE object (cause: ");
        r6.append(r13);
        r6.append("): ");
        r6.append(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00e4, code lost:
        throw new org.jose4j.jwt.consumer.InvalidJwtException("JWT processing failed.", new org.jose4j.jwt.consumer.ErrorCodeValidator.Error(17, r6.toString()), r13, r4);
     */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0081 A[ExcHandler: Exception (r13v3 'e' java.lang.Exception A[CUSTOM_DECLARE]), PHI: r5 
      PHI: (r5v4 java.lang.String) = (r5v2 java.lang.String), (r5v5 java.lang.String), (r5v2 java.lang.String), (r5v2 java.lang.String), (r5v2 java.lang.String), (r5v2 java.lang.String), (r5v2 java.lang.String), (r5v2 java.lang.String) binds: [B:3:0x001a, B:34:0x007b, B:26:0x006a, B:29:0x0073, B:30:?, B:32:0x0077, B:33:?, B:27:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:3:0x001a] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00b2 A[ExcHandler: InvalidJwtException (r13v2 'e' org.jose4j.jwt.consumer.InvalidJwtException A[CUSTOM_DECLARE]), Splitter:B:3:0x001a] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00b4 A[ExcHandler: JoseException (r13v1 'e' org.jose4j.lang.JoseException A[CUSTOM_DECLARE]), PHI: r5 
      PHI: (r5v3 java.lang.String) = (r5v2 java.lang.String), (r5v5 java.lang.String), (r5v2 java.lang.String), (r5v2 java.lang.String), (r5v2 java.lang.String), (r5v2 java.lang.String) binds: [B:3:0x001a, B:34:0x007b, B:26:0x006a, B:29:0x0073, B:30:?, B:27:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:3:0x001a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.jose4j.jwt.consumer.JwtContext process(java.lang.String r13) throws org.jose4j.jwt.consumer.InvalidJwtException {
        /*
            r12 = this;
            java.lang.String r0 = " nested"
            java.lang.String r1 = "JWT processing failed."
            java.lang.String r2 = "): "
            java.util.LinkedList r3 = new java.util.LinkedList
            r3.<init>()
            org.jose4j.jwt.consumer.JwtContext r4 = new org.jose4j.jwt.consumer.JwtContext
            java.util.List r5 = java.util.Collections.unmodifiableList(r3)
            r6 = 0
            r4.<init>(r13, r6, r5)
            r5 = r13
        L_0x0016:
            if (r6 != 0) goto L_0x00e5
            r7 = 17
            org.jose4j.jwx.JsonWebStructure r8 = org.jose4j.jwx.JsonWebStructure.fromCompactSerialization(r5)     // Catch:{ JoseException -> 0x00b4, InvalidJwtException -> 0x00b2, Exception -> 0x0081 }
            boolean r9 = r8 instanceof org.jose4j.jws.JsonWebSignature     // Catch:{ JoseException -> 0x00b4, InvalidJwtException -> 0x00b2, Exception -> 0x0081 }
            if (r9 == 0) goto L_0x002a
            r9 = r8
            org.jose4j.jws.JsonWebSignature r9 = (org.jose4j.jws.JsonWebSignature) r9     // Catch:{ JoseException -> 0x00b4, InvalidJwtException -> 0x00b2, Exception -> 0x0081 }
            java.lang.String r9 = r9.getUnverifiedPayload()     // Catch:{ JoseException -> 0x00b4, InvalidJwtException -> 0x00b2, Exception -> 0x0081 }
            goto L_0x0062
        L_0x002a:
            r9 = r8
            org.jose4j.jwe.JsonWebEncryption r9 = (org.jose4j.jwe.JsonWebEncryption) r9     // Catch:{ JoseException -> 0x00b4, InvalidJwtException -> 0x00b2, Exception -> 0x0081 }
            org.jose4j.jca.ProviderContext r10 = r12.jweProviderContext     // Catch:{ JoseException -> 0x00b4, InvalidJwtException -> 0x00b2, Exception -> 0x0081 }
            if (r10 == 0) goto L_0x0034
            r9.setProviderContext(r10)     // Catch:{ JoseException -> 0x00b4, InvalidJwtException -> 0x00b2, Exception -> 0x0081 }
        L_0x0034:
            boolean r10 = r12.relaxDecryptionKeyValidation     // Catch:{ JoseException -> 0x00b4, InvalidJwtException -> 0x00b2, Exception -> 0x0081 }
            if (r10 == 0) goto L_0x003c
            r10 = 0
            r9.setDoKeyValidation(r10)     // Catch:{ JoseException -> 0x00b4, InvalidJwtException -> 0x00b2, Exception -> 0x0081 }
        L_0x003c:
            org.jose4j.jwa.AlgorithmConstraints r10 = r12.jweContentEncryptionAlgorithmConstraints     // Catch:{ JoseException -> 0x00b4, InvalidJwtException -> 0x00b2, Exception -> 0x0081 }
            if (r10 == 0) goto L_0x0043
            r9.setContentEncryptionAlgorithmConstraints(r10)     // Catch:{ JoseException -> 0x00b4, InvalidJwtException -> 0x00b2, Exception -> 0x0081 }
        L_0x0043:
            java.util.List r10 = java.util.Collections.unmodifiableList(r3)     // Catch:{ JoseException -> 0x00b4, InvalidJwtException -> 0x00b2, Exception -> 0x0081 }
            org.jose4j.keys.resolvers.DecryptionKeyResolver r11 = r12.decryptionKeyResolver     // Catch:{ JoseException -> 0x00b4, InvalidJwtException -> 0x00b2, Exception -> 0x0081 }
            java.security.Key r11 = r11.resolveKey(r9, r10)     // Catch:{ JoseException -> 0x00b4, InvalidJwtException -> 0x00b2, Exception -> 0x0081 }
            r9.setKey(r11)     // Catch:{ JoseException -> 0x00b4, InvalidJwtException -> 0x00b2, Exception -> 0x0081 }
            org.jose4j.jwa.AlgorithmConstraints r11 = r12.jweAlgorithmConstraints     // Catch:{ JoseException -> 0x00b4, InvalidJwtException -> 0x00b2, Exception -> 0x0081 }
            if (r11 == 0) goto L_0x0057
            r9.setAlgorithmConstraints(r11)     // Catch:{ JoseException -> 0x00b4, InvalidJwtException -> 0x00b2, Exception -> 0x0081 }
        L_0x0057:
            org.jose4j.jwt.consumer.JweCustomizer r11 = r12.jweCustomizer     // Catch:{ JoseException -> 0x00b4, InvalidJwtException -> 0x00b2, Exception -> 0x0081 }
            if (r11 == 0) goto L_0x005e
            r11.customize(r9, r10)     // Catch:{ JoseException -> 0x00b4, InvalidJwtException -> 0x00b2, Exception -> 0x0081 }
        L_0x005e:
            java.lang.String r9 = r9.getPayload()     // Catch:{ JoseException -> 0x00b4, InvalidJwtException -> 0x00b2, Exception -> 0x0081 }
        L_0x0062:
            boolean r10 = r12.isNestedJwt(r8)     // Catch:{ JoseException -> 0x00b4, InvalidJwtException -> 0x00b2, Exception -> 0x0081 }
            if (r10 == 0) goto L_0x006a
        L_0x0068:
            r5 = r9
            goto L_0x007b
        L_0x006a:
            org.jose4j.jwt.JwtClaims r6 = org.jose4j.jwt.JwtClaims.parse(r9, r4)     // Catch:{ InvalidJwtException -> 0x0072, JoseException -> 0x00b4, Exception -> 0x0081 }
            r4.setJwtClaims(r6)     // Catch:{ InvalidJwtException -> 0x0072, JoseException -> 0x00b4, Exception -> 0x0081 }
            goto L_0x007b
        L_0x0072:
            r10 = move-exception
            boolean r11 = r12.liberalContentTypeHandling     // Catch:{ JoseException -> 0x00b4, InvalidJwtException -> 0x00b2, Exception -> 0x0081 }
            if (r11 == 0) goto L_0x0080
            org.jose4j.jwx.JsonWebStructure.fromCompactSerialization(r13)     // Catch:{ JoseException -> 0x007f, InvalidJwtException -> 0x00b2, Exception -> 0x0081 }
            goto L_0x0068
        L_0x007b:
            r3.addFirst(r8)     // Catch:{ JoseException -> 0x00b4, InvalidJwtException -> 0x00b2, Exception -> 0x0081 }
            goto L_0x0016
        L_0x007f:
            throw r10     // Catch:{ JoseException -> 0x00b4, InvalidJwtException -> 0x00b2, Exception -> 0x0081 }
        L_0x0080:
            throw r10     // Catch:{ JoseException -> 0x00b4, InvalidJwtException -> 0x00b2, Exception -> 0x0081 }
        L_0x0081:
            r13 = move-exception
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "Unexpected exception encountered while processing"
            r6.append(r8)
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x0095
            r6.append(r0)
        L_0x0095:
            java.lang.String r0 = " JOSE object ("
            r6.append(r0)
            r6.append(r13)
            r6.append(r2)
            r6.append(r5)
            org.jose4j.jwt.consumer.ErrorCodeValidator$Error r0 = new org.jose4j.jwt.consumer.ErrorCodeValidator$Error
            java.lang.String r2 = r6.toString()
            r0.<init>(r7, r2)
            org.jose4j.jwt.consumer.InvalidJwtException r2 = new org.jose4j.jwt.consumer.InvalidJwtException
            r2.<init>(r1, r0, r13, r4)
            throw r2
        L_0x00b2:
            r13 = move-exception
            throw r13
        L_0x00b4:
            r13 = move-exception
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "Unable to process"
            r6.append(r8)
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x00c8
            r6.append(r0)
        L_0x00c8:
            java.lang.String r0 = " JOSE object (cause: "
            r6.append(r0)
            r6.append(r13)
            r6.append(r2)
            r6.append(r5)
            org.jose4j.jwt.consumer.ErrorCodeValidator$Error r0 = new org.jose4j.jwt.consumer.ErrorCodeValidator$Error
            java.lang.String r2 = r6.toString()
            r0.<init>(r7, r2)
            org.jose4j.jwt.consumer.InvalidJwtException r2 = new org.jose4j.jwt.consumer.InvalidJwtException
            r2.<init>(r1, r0, r13, r4)
            throw r2
        L_0x00e5:
            r12.processContext(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jose4j.jwt.consumer.JwtConsumer.process(java.lang.String):org.jose4j.jwt.consumer.JwtContext");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x000b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void validate(org.jose4j.jwt.consumer.JwtContext r7) throws org.jose4j.jwt.consumer.InvalidJwtException {
        /*
            r6 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.List<org.jose4j.jwt.consumer.ErrorCodeValidator> r1 = r6.validators
            java.util.Iterator r1 = r1.iterator()
        L_0x000b:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0061
            java.lang.Object r2 = r1.next()
            org.jose4j.jwt.consumer.ErrorCodeValidator r2 = (org.jose4j.jwt.consumer.ErrorCodeValidator) r2
            org.jose4j.jwt.consumer.ErrorCodeValidator$Error r2 = r2.validate(r7)     // Catch:{ MalformedClaimException -> 0x004e, Exception -> 0x001c }
            goto L_0x005b
        L_0x001c:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Unexpected exception thrown from validator "
            r4.append(r5)
            java.lang.Class r2 = r2.getClass()
            java.lang.String r2 = r2.getName()
            r4.append(r2)
            java.lang.String r2 = ": "
            r4.append(r2)
            java.lang.Class r2 = r6.getClass()
            java.lang.String r2 = org.jose4j.lang.ExceptionHelp.toStringWithCausesAndAbbreviatedStack(r3, r2)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            org.jose4j.jwt.consumer.ErrorCodeValidator$Error r3 = new org.jose4j.jwt.consumer.ErrorCodeValidator$Error
            r4 = 17
            r3.<init>(r4, r2)
            goto L_0x005a
        L_0x004e:
            r2 = move-exception
            org.jose4j.jwt.consumer.ErrorCodeValidator$Error r3 = new org.jose4j.jwt.consumer.ErrorCodeValidator$Error
            r4 = 18
            java.lang.String r2 = r2.getMessage()
            r3.<init>(r4, r2)
        L_0x005a:
            r2 = r3
        L_0x005b:
            if (r2 == 0) goto L_0x000b
            r0.add(r2)
            goto L_0x000b
        L_0x0061:
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L_0x0068
            return
        L_0x0068:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "JWT (claims->"
            r1.append(r2)
            org.jose4j.jwt.JwtClaims r2 = r7.getJwtClaims()
            java.lang.String r2 = r2.getRawJson()
            r1.append(r2)
            java.lang.String r2 = ") rejected due to invalid claims or other invalid content."
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            org.jose4j.jwt.consumer.InvalidJwtException r2 = new org.jose4j.jwt.consumer.InvalidJwtException
            r2.<init>(r1, r0, r7)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jose4j.jwt.consumer.JwtConsumer.validate(org.jose4j.jwt.consumer.JwtContext):void");
    }

    private boolean isNestedJwt(JsonWebStructure jsonWebStructure) {
        String contentTypeHeaderValue = jsonWebStructure.getContentTypeHeaderValue();
        return contentTypeHeaderValue != null && (contentTypeHeaderValue.equalsIgnoreCase("jwt") || contentTypeHeaderValue.equalsIgnoreCase("application/jwt"));
    }
}
