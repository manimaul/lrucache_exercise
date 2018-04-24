package com.willkamp;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class LRUCache<K, V> {

    public LRUCache(int capacity) {
    }

    Optional<V> get(K key) {
        //todo: implement me
        return Optional.empty();
    }

    V get(K key, Supplier<V> factory) {
        //todo: implement me
        throw new NotImplementedException();
    }

    void set(K key, V value) {
        //todo: implement me
    }

    boolean contains(K key) {
        //todo: implement me
        throw new NotImplementedException();
    }

    List<V> values() {
        //todo: implement me
        return Collections.emptyList();
    }

}
