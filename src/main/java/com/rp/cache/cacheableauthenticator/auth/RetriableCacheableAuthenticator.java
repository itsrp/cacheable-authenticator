package com.rp.cache.cacheableauthenticator.auth;

import com.rp.cache.cacheableauthenticator.model.AuthDetailsCacheValue;
import com.rp.cache.cacheableauthenticator.model.CacheKey;
import com.rp.cache.cacheableauthenticator.model.CacheValue;
import com.rp.cache.cacheableauthenticator.model.UsernameCacheKey;
import com.rp.cache.cacheableauthenticator.provider.AuthenticationProvider;
import com.rp.cache.cacheableauthenticator.provider.CacheProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class RetriableCacheableAuthenticator implements CacheableAuthenticator {

    private static final Logger LOG = LoggerFactory.getLogger(RetriableCacheableAuthenticator.class);

    private CacheProvider<CacheKey, CacheValue> cacheProvider;

    private AuthenticationProvider authenticationProvider;

    private CredentialsMatcher<CacheValue> credentialsMatcher =
            new CachedCredentialsMatcher<>(authenticationProvider, 3);

    public RetriableCacheableAuthenticator(CacheProvider<CacheKey, CacheValue> cacheProvider,
                                           AuthenticationProvider authenticationProvider) {
        this.cacheProvider = cacheProvider;
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    public boolean authenticate(String username, String password) {
        CacheKey cacheKey = new UsernameCacheKey(username);
        CacheValue cacheValue = cacheProvider.get(cacheKey);
        CacheValue newCacheValue;
        if(cacheValue == null) {
            LOG.info("Data not found in cache for key: {}", cacheKey);
            newCacheValue = authenticateAgainstServer(cacheKey, password);
        } else {
            LOG.info("Data found in cache for key: {}", cacheKey);
            newCacheValue = credentialsMatcher.match(username, password, cacheValue);
        }
        if(!Objects.equals(cacheValue, newCacheValue)) {
            LOG.info("Updating data in cache for key: {}", cacheKey);
            cacheProvider.put(cacheKey, newCacheValue);
        }
        return newCacheValue.isAuthenticated();
    }

    private CacheValue authenticateAgainstServer(CacheKey cacheKey, String password) {
        boolean isAuthenticated = authenticationProvider.authenticate(cacheKey.getUsername(), password);
        return new AuthDetailsCacheValue(password, isAuthenticated);
    }

}