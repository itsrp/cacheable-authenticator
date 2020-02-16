package com.rp.cache.cacheableauthenticator.auth;

public interface CacheableAuthenticator {

    boolean authenticate(String username, String password);

}
