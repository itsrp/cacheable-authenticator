package com.rp.cache.cacheableauthenticator.model;

import java.util.Objects;

public class AuthDetailsCacheValue implements CacheValue {

    private String password;

    private boolean authenticated;

    private int failedAttempts;

    public AuthDetailsCacheValue(String password, boolean authenticated) {
        this.password = password;
        this.authenticated = authenticated;
        this.failedAttempts = authenticated ? 0 : 1;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public int getFailedAttempts() {
        return failedAttempts;
    }

    @Override
    public void incrementFailedAttempt(String password) {
        this.password = password;
        this.failedAttempts = failedAttempts + 1;
        this.authenticated = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthDetailsCacheValue that = (AuthDetailsCacheValue) o;
        return authenticated == that.authenticated &&
                failedAttempts == that.failedAttempts &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, authenticated, failedAttempts);
    }

    @Override
    public String toString() {
        return "AuthDetailsCacheValue{" +
                "authenticated=" + authenticated +
                ", failedAttempts=" + failedAttempts +
                '}';
    }
}