package com.rp.cache.cacheableauthenticator.auth;

import com.rp.cache.cacheableauthenticator.model.AuthDetailsCacheValue;
import com.rp.cache.cacheableauthenticator.model.CacheValue;
import com.rp.cache.cacheableauthenticator.provider.AuthenticationProvider;

import java.util.Objects;

public class CachedCredentialsMatcher<V extends CacheValue> implements CredentialsMatcher<V> {

    private AuthenticationProvider authenticationProvider;

    private int maxLimit;

    public CachedCredentialsMatcher(AuthenticationProvider authenticationProvider, int maxLimit) {
        this.authenticationProvider = authenticationProvider;
        this.maxLimit = maxLimit;
    }

    @Override
    public CacheValue match(String username, String password, V v) {
        if(Objects.equals(password, v.getPassword())) {
            return v;
        } else if(v.getFailedAttempts() < maxLimit) {
            boolean isAuthenticated = authenticationProvider.authenticate(username, password);
            if(isAuthenticated) {
                return new AuthDetailsCacheValue(password, isAuthenticated);
            } else {
                v.incrementFailedAttempt(password);
                return v;
            }
        }
        return v;
    }
}
