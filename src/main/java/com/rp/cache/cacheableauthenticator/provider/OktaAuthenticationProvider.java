package com.rp.cache.cacheableauthenticator.provider;

import com.rp.cache.cacheableauthenticator.auth.RetriableCacheableAuthenticator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OktaAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOG = LoggerFactory.getLogger(OktaAuthenticationProvider.class);

    Map<String, String> dummyData = new HashMap<>();

    public OktaAuthenticationProvider() {
        dummyData.put("abc1", "abc1");
        dummyData.put("abc2", "abc2");
    }

    @Override
    public boolean authenticate(String username, String password) {
        LOG.info("Calling OKTA for username: {}", username);
        return Objects.equals(dummyData.get(username), password);
    }
}
