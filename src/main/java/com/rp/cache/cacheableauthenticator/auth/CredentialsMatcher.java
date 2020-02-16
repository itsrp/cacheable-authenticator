package com.rp.cache.cacheableauthenticator.auth;

import com.rp.cache.cacheableauthenticator.model.CacheValue;

public interface CredentialsMatcher<V> {

    CacheValue match(String username, String password, V v);
}