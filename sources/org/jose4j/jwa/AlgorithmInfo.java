package org.jose4j.jwa;

import org.jose4j.keys.KeyPersuasion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AlgorithmInfo implements Algorithm {
    private String algorithmIdentifier;
    private String javaAlgorithm;
    private KeyPersuasion keyPersuasion;
    private String keyType;
    protected final Logger log = LoggerFactory.getLogger(getClass());

    public void setAlgorithmIdentifier(String str) {
        this.algorithmIdentifier = str;
    }

    public void setJavaAlgorithm(String str) {
        this.javaAlgorithm = str;
    }

    public String getJavaAlgorithm() {
        return this.javaAlgorithm;
    }

    public String getAlgorithmIdentifier() {
        return this.algorithmIdentifier;
    }

    public KeyPersuasion getKeyPersuasion() {
        return this.keyPersuasion;
    }

    public void setKeyPersuasion(KeyPersuasion keyPersuasion2) {
        this.keyPersuasion = keyPersuasion2;
    }

    public String getKeyType() {
        return this.keyType;
    }

    public void setKeyType(String str) {
        this.keyType = str;
    }
}
