package com.rp.cache.cacheableauthenticator.auth;

import com.rp.cache.cacheableauthenticator.model.CacheKey;
import com.rp.cache.cacheableauthenticator.model.CacheValue;
import com.rp.cache.cacheableauthenticator.provider.AuthenticationProvider;
import com.rp.cache.cacheableauthenticator.provider.CacheProvider;

public class CacheableAuthenticationBuilder<K extends CacheKey, V extends CacheValue> {

    private CacheProvider cacheProvider;

    private AuthenticationProvider authenticationProvider;

    private CredentialsMatcher credentialsMatcher;

    public CacheableAuthenticationBuilder() {
    }

    public CacheableAuthenticationBuilder cacheProvider(CacheProvider cacheProvider) {
        this.cacheProvider = cacheProvider;
        return this;
    }

    public CacheableAuthenticationBuilder authenticationProvider(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
        return this;
    }

    /*public CacheableAuthenticationBuilder cachedCredentialsMatcher(CredentialsMatcher<K, V> credentialsMatcher) {
        this.credentialsMatcher = credentialsMatcher;
        return this;
    }*/

    public CacheableAuthenticator build() {
        return new RetriableCacheableAuthenticator(cacheProvider, authenticationProvider);
    }
}
