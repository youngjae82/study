package com.example.demo2.cecurity;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SecurityServiceImpl implements SecurityService{

    private  static final String secretKey = "ThisisHyoJunSecretKeyWelcomeMyFirstJwt";

    @Override
    public String createToken(String subject, long ttlmillis) {
        return "";
    }

    @Override
    public String getSubject(String token) {
        return null;
    }
}
