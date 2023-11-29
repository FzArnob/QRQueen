package org.jose4j.jwt.consumer;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwt.NumericDate;
import org.jose4j.keys.resolvers.DecryptionKeyResolver;
import org.jose4j.keys.resolvers.VerificationKeyResolver;

public class JwtConsumerBuilder {
    private AudValidator audValidator;
    private List<ErrorCodeValidator> customValidators = new ArrayList();
    private NumericDateValidator dateClaimsValidator = new NumericDateValidator();
    private DecryptionKeyResolver decryptionKeyResolver = new SimpleKeyResolver((Key) null);
    private String expectedSubject;
    private IssValidator issValidator;
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
    private boolean requireJti;
    private boolean requireSignature = true;
    private boolean requireSubject;
    private boolean skipAllDefaultValidators = false;
    private boolean skipAllValidators = false;
    private boolean skipDefaultAudienceValidation;
    private boolean skipSignatureVerification = false;
    private boolean skipVerificationKeyResolutionOnNone;
    private TypeValidator typeValidator;
    private VerificationKeyResolver verificationKeyResolver = new SimpleKeyResolver((Key) null);

    public JwtConsumerBuilder setEnableRequireEncryption() {
        this.requireEncryption = true;
        return this;
    }

    public JwtConsumerBuilder setEnableRequireIntegrity() {
        this.requireIntegrity = true;
        return this;
    }

    public JwtConsumerBuilder setDisableRequireSignature() {
        this.requireSignature = false;
        return this;
    }

    public JwtConsumerBuilder setEnableLiberalContentTypeHandling() {
        this.liberalContentTypeHandling = true;
        return this;
    }

    public JwtConsumerBuilder setSkipSignatureVerification() {
        this.skipSignatureVerification = true;
        return this;
    }

    public JwtConsumerBuilder setSkipAllValidators() {
        this.skipAllValidators = true;
        return this;
    }

    public JwtConsumerBuilder setSkipAllDefaultValidators() {
        this.skipAllDefaultValidators = true;
        return this;
    }

    public JwtConsumerBuilder setJwsAlgorithmConstraints(AlgorithmConstraints algorithmConstraints) {
        this.jwsAlgorithmConstraints = algorithmConstraints;
        return this;
    }

    public JwtConsumerBuilder setJweAlgorithmConstraints(AlgorithmConstraints algorithmConstraints) {
        this.jweAlgorithmConstraints = algorithmConstraints;
        return this;
    }

    public JwtConsumerBuilder setJweContentEncryptionAlgorithmConstraints(AlgorithmConstraints algorithmConstraints) {
        this.jweContentEncryptionAlgorithmConstraints = algorithmConstraints;
        return this;
    }

    public JwtConsumerBuilder setJwsAlgorithmConstraints(AlgorithmConstraints.ConstraintType constraintType, String... strArr) {
        this.jwsAlgorithmConstraints = new AlgorithmConstraints(constraintType, strArr);
        return this;
    }

    public JwtConsumerBuilder setJweAlgorithmConstraints(AlgorithmConstraints.ConstraintType constraintType, String... strArr) {
        this.jweAlgorithmConstraints = new AlgorithmConstraints(constraintType, strArr);
        return this;
    }

    public JwtConsumerBuilder setJweContentEncryptionAlgorithmConstraints(AlgorithmConstraints.ConstraintType constraintType, String... strArr) {
        this.jweContentEncryptionAlgorithmConstraints = new AlgorithmConstraints(constraintType, strArr);
        return this;
    }

    public JwtConsumerBuilder setVerificationKey(Key key) {
        return setVerificationKeyResolver(new SimpleKeyResolver(key));
    }

    public JwtConsumerBuilder setVerificationKeyResolver(VerificationKeyResolver verificationKeyResolver2) {
        this.verificationKeyResolver = verificationKeyResolver2;
        return this;
    }

    public JwtConsumerBuilder setSkipVerificationKeyResolutionOnNone() {
        this.skipVerificationKeyResolutionOnNone = true;
        return this;
    }

    public JwtConsumerBuilder setDecryptionKey(Key key) {
        return setDecryptionKeyResolver(new SimpleKeyResolver(key));
    }

    public JwtConsumerBuilder setDecryptionKeyResolver(DecryptionKeyResolver decryptionKeyResolver2) {
        this.decryptionKeyResolver = decryptionKeyResolver2;
        return this;
    }

    public JwtConsumerBuilder setExpectedAudience(String... strArr) {
        return setExpectedAudience(true, strArr);
    }

    public JwtConsumerBuilder setExpectedAudience(boolean z, String... strArr) {
        this.audValidator = new AudValidator(new HashSet(Arrays.asList(strArr)), z);
        return this;
    }

    public JwtConsumerBuilder setSkipDefaultAudienceValidation() {
        this.skipDefaultAudienceValidation = true;
        return this;
    }

    public JwtConsumerBuilder setExpectedIssuers(boolean z, String... strArr) {
        this.issValidator = new IssValidator(z, strArr);
        return this;
    }

    public JwtConsumerBuilder setExpectedIssuer(boolean z, String str) {
        this.issValidator = new IssValidator(str, z);
        return this;
    }

    public JwtConsumerBuilder setExpectedIssuer(String str) {
        return setExpectedIssuer(true, str);
    }

    public JwtConsumerBuilder setRequireSubject() {
        this.requireSubject = true;
        return this;
    }

    public JwtConsumerBuilder setExpectedSubject(String str) {
        this.expectedSubject = str;
        return setRequireSubject();
    }

    public JwtConsumerBuilder setRequireJwtId() {
        this.requireJti = true;
        return this;
    }

    public JwtConsumerBuilder setRequireExpirationTime() {
        this.dateClaimsValidator.setRequireExp(true);
        return this;
    }

    public JwtConsumerBuilder setRequireIssuedAt() {
        this.dateClaimsValidator.setRequireIat(true);
        return this;
    }

    public JwtConsumerBuilder setIssuedAtRestrictions(int i, int i2) {
        this.dateClaimsValidator.setIatAllowedSecondsInTheFuture(i);
        this.dateClaimsValidator.setIatAllowedSecondsInThePast(i2);
        return this;
    }

    public JwtConsumerBuilder setRequireNotBefore() {
        this.dateClaimsValidator.setRequireNbf(true);
        return this;
    }

    public JwtConsumerBuilder setEvaluationTime(NumericDate numericDate) {
        this.dateClaimsValidator.setEvaluationTime(numericDate);
        return this;
    }

    public JwtConsumerBuilder setAllowedClockSkewInSeconds(int i) {
        this.dateClaimsValidator.setAllowedClockSkewSeconds(i);
        return this;
    }

    public JwtConsumerBuilder setMaxFutureValidityInMinutes(int i) {
        this.dateClaimsValidator.setMaxFutureValidityInMinutes(i);
        return this;
    }

    public JwtConsumerBuilder setRelaxVerificationKeyValidation() {
        this.relaxVerificationKeyValidation = true;
        return this;
    }

    public JwtConsumerBuilder setRelaxDecryptionKeyValidation() {
        this.relaxDecryptionKeyValidation = true;
        return this;
    }

    public JwtConsumerBuilder registerValidator(Validator validator) {
        this.customValidators.add(new ErrorCodeValidatorAdapter(validator));
        return this;
    }

    public JwtConsumerBuilder registerValidator(ErrorCodeValidator errorCodeValidator) {
        this.customValidators.add(errorCodeValidator);
        return this;
    }

    public JwtConsumerBuilder setJwsCustomizer(JwsCustomizer jwsCustomizer2) {
        this.jwsCustomizer = jwsCustomizer2;
        return this;
    }

    public JwtConsumerBuilder setJweCustomizer(JweCustomizer jweCustomizer2) {
        this.jweCustomizer = jweCustomizer2;
        return this;
    }

    public JwtConsumerBuilder setJwsProviderContext(ProviderContext providerContext) {
        this.jwsProviderContext = providerContext;
        return this;
    }

    public JwtConsumerBuilder setJweProviderContext(ProviderContext providerContext) {
        this.jweProviderContext = providerContext;
        return this;
    }

    public JwtConsumerBuilder setExpectedType(boolean z, String str) {
        this.typeValidator = new TypeValidator(z, str);
        return this;
    }

    public JwtConsumer build() {
        ArrayList arrayList = new ArrayList();
        if (!this.skipAllValidators) {
            if (!this.skipAllDefaultValidators) {
                if (!this.skipDefaultAudienceValidation) {
                    if (this.audValidator == null) {
                        this.audValidator = new AudValidator(Collections.emptySet(), false);
                    }
                    arrayList.add(this.audValidator);
                }
                if (this.issValidator == null) {
                    this.issValidator = new IssValidator((String) null, false);
                }
                arrayList.add(this.issValidator);
                arrayList.add(this.dateClaimsValidator);
                arrayList.add(this.expectedSubject == null ? new SubValidator(this.requireSubject) : new SubValidator(this.expectedSubject));
                arrayList.add(new JtiValidator(this.requireJti));
                TypeValidator typeValidator2 = this.typeValidator;
                if (typeValidator2 != null) {
                    arrayList.add(typeValidator2);
                }
            }
            arrayList.addAll(this.customValidators);
        }
        JwtConsumer jwtConsumer = new JwtConsumer();
        jwtConsumer.setValidators(arrayList);
        jwtConsumer.setVerificationKeyResolver(this.verificationKeyResolver);
        jwtConsumer.setDecryptionKeyResolver(this.decryptionKeyResolver);
        jwtConsumer.setJwsAlgorithmConstraints(this.jwsAlgorithmConstraints);
        jwtConsumer.setJweAlgorithmConstraints(this.jweAlgorithmConstraints);
        jwtConsumer.setJweContentEncryptionAlgorithmConstraints(this.jweContentEncryptionAlgorithmConstraints);
        jwtConsumer.setRequireSignature(this.requireSignature);
        jwtConsumer.setRequireEncryption(this.requireEncryption);
        jwtConsumer.setRequireIntegrity(this.requireIntegrity);
        jwtConsumer.setLiberalContentTypeHandling(this.liberalContentTypeHandling);
        jwtConsumer.setSkipSignatureVerification(this.skipSignatureVerification);
        jwtConsumer.setSkipVerificationKeyResolutionOnNone(this.skipVerificationKeyResolutionOnNone);
        jwtConsumer.setRelaxVerificationKeyValidation(this.relaxVerificationKeyValidation);
        jwtConsumer.setRelaxDecryptionKeyValidation(this.relaxDecryptionKeyValidation);
        jwtConsumer.setJwsCustomizer(this.jwsCustomizer);
        jwtConsumer.setJweCustomizer(this.jweCustomizer);
        jwtConsumer.setJwsProviderContext(this.jwsProviderContext);
        jwtConsumer.setJweProviderContext(this.jweProviderContext);
        return jwtConsumer;
    }
}
