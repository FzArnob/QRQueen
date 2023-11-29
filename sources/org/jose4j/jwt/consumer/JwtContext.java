package org.jose4j.jwt.consumer;

import java.util.List;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwx.JsonWebStructure;

public class JwtContext {
    private List<JsonWebStructure> joseObjects;
    private String jwt;
    private JwtClaims jwtClaims;

    public JwtContext(JwtClaims jwtClaims2, List<JsonWebStructure> list) {
        this.jwtClaims = jwtClaims2;
        this.joseObjects = list;
    }

    public JwtContext(String str, JwtClaims jwtClaims2, List<JsonWebStructure> list) {
        this.jwt = str;
        this.jwtClaims = jwtClaims2;
        this.joseObjects = list;
    }

    public JwtClaims getJwtClaims() {
        return this.jwtClaims;
    }

    /* access modifiers changed from: package-private */
    public void setJwtClaims(JwtClaims jwtClaims2) {
        this.jwtClaims = jwtClaims2;
    }

    public List<JsonWebStructure> getJoseObjects() {
        return this.joseObjects;
    }

    public String getJwt() {
        return this.jwt;
    }
}
