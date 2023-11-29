package org.jose4j.jwa;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.jose4j.jwa.Algorithm;
import org.jose4j.lang.ExceptionHelp;
import org.jose4j.lang.InvalidAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlgorithmFactory<A extends Algorithm> {
    private final Map<String, A> algorithms = new LinkedHashMap();
    private final Logger log;
    private String parameterName;

    public AlgorithmFactory(String str, Class<A> cls) {
        this.parameterName = str;
        this.log = LoggerFactory.getLogger(getClass().getName() + "->" + cls.getSimpleName());
    }

    public A getAlgorithm(String str) throws InvalidAlgorithmException {
        A a = (Algorithm) this.algorithms.get(str);
        if (a != null) {
            return a;
        }
        throw new InvalidAlgorithmException(str + " is an unknown, unsupported or unavailable " + this.parameterName + " algorithm (not one of " + getSupportedAlgorithms() + ").");
    }

    public boolean isAvailable(String str) {
        return this.algorithms.containsKey(str);
    }

    public Set<String> getSupportedAlgorithms() {
        return Collections.unmodifiableSet(this.algorithms.keySet());
    }

    public void registerAlgorithm(A a) {
        String algorithmIdentifier = a.getAlgorithmIdentifier();
        if (isAvailable(a)) {
            this.algorithms.put(algorithmIdentifier, a);
            this.log.debug("{} registered for {} algorithm {}", a, this.parameterName, algorithmIdentifier);
            return;
        }
        this.log.debug("{} is unavailable so will not be registered for {} algorithms.", (Object) algorithmIdentifier, (Object) this.parameterName);
    }

    private boolean isAvailable(A a) {
        try {
            return a.isAvailable();
        } catch (Throwable th) {
            Logger logger = this.log;
            logger.debug("Unexpected problem checking for availability of " + a.getAlgorithmIdentifier() + " algorithm: " + ExceptionHelp.toStringWithCauses(th));
            return false;
        }
    }

    public void unregisterAlgorithm(String str) {
        this.algorithms.remove(str);
    }
}
