package com.rp.cache.cacheableauthenticator.provider;

public interface CacheProvider<K, V> {

    V get(K k);

    void put(K k, V v);
}
