package com.rp.cache.cacheableauthenticator.model;

import java.util.Objects;

public class UsernameCacheKey implements CacheKey {

    private String username;

    public UsernameCacheKey(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsernameCacheKey that = (UsernameCacheKey) o;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "UsernameCacheKey{" +
                "username='" + username + '\'' +
                '}';
    }
}
