package com.example.demo2.cecurity;

public interface SecurityService {
    String createToken(String subject, long ttlmillis);
    String getSubject(String token);
}
