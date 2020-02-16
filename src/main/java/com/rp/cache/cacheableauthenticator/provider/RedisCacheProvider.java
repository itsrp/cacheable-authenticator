package com.rp.cache.cacheableauthenticator.provider;

import java.util.HashMap;
import java.util.Map;

public class RedisCacheProvider<K, V> implements CacheProvider<K, V> {

    private Map<K, V> store = new HashMap<>();

    @Override
    public V get(K k) {
        return store.get(k);
    }

    @Override
    public void put(K k, V v) {
        store.put(k, v);
    }
}
