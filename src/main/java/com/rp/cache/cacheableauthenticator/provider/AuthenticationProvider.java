package com.rp.cache.cacheableauthenticator.provider;

public interface AuthenticationProvider {

    boolean authenticate(String username, String password);

}
