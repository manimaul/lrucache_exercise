package com.willkamp;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class LRUCache<K, V> {

    private final Impl<K, V> implementation;

    public LRUCache(int capacity) {
        implementation = new Impl<>(capacity);
    }

    Optional<V> get(K key) {
        return Optional.ofNullable(implementation.get(key));
    }

    V get(K key, Supplier<V> factory) {
        V value = get(key).orElseGet(factory);
        implementation.put(key, value);
        return value;
    }

    void set(K key, V value) {
        implementation.put(key, value);
    }

    boolean contains(K key) {
        return implementation.containsKey(key);
    }

    private static class Impl<K, V> extends LinkedHashMap<K, V> {
        private final int cacheSize;

        private Impl(int cacheSize) {
            super((int) Math.ceil(cacheSize * 1.25f), 0.75f, true);
            this.cacheSize = cacheSize;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > cacheSize;
        }
    }

}
