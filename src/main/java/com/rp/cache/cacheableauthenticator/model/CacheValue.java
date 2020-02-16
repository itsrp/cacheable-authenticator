package com.rp.cache.cacheableauthenticator.model;

public interface CacheValue {

    String getPassword();

    boolean isAuthenticated();

    int getFailedAttempts();

    void incrementFailedAttempt(String password);
}