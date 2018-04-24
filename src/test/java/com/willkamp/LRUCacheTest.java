package com.willkamp;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

@RunWith(JUnit4.class)
public class LRUCacheTest extends Assert {

    private static final int CAPACITY = 3;
    private LRUCache<String, Integer> subject;

    @Before
    public void beforeEach() {
        subject = new LRUCache<>(CAPACITY);
    }

    @Test
    public void testSetAndGet() throws Exception {
        // given an an item has been inserted
        subject.set("key", 10);

        // then the item can be retrieved
        expectValue(new Pair<>("key", 10));
    }

    @Test
    public void testCapacity() throws Exception {
        // when more than the capacity is inserted
        givenItemsInserted(CAPACITY * 2);

        // then the last 3 inserted
        expectValues(new Pair<>("3", 3), new Pair<>("4", 4), new Pair<>("5", 5));
    }

    @Test
    public void testNoneAccessedLru() throws Exception {
        // given an lru cache has reached it's capacity
        givenItemsInserted(CAPACITY);

        // when a new item is inserted
        subject.set("3", 3);

        // then the least recently used was removed
        expectNoValue("0");

        // and the other items remain
        expectValues(new Pair<>("1", 1), new Pair<>("2", 2), new Pair<>("3", 3));
    }

    @Test
    public void testLru() throws Exception {
        // given an lru cache has reached it's capacity
        givenItemsInserted(CAPACITY);

        // and items have been accessed
        subject.get("2");
        subject.get("0");

        // when a new item is inserted
        subject.set("3", 3);

        // then the least recently used was removed
        expectNoValue("1");

        // and the other items remain
        expectValue(new Pair<>("0", 0));
    }

    @Test
    public void testGetFactoryValuePreExists() throws Exception {
        // given an lru cache has not reached it's capacity
        givenItemsInserted(CAPACITY -1 );

        // when an item is retrieved with a factory
        Integer value = subject.get("0", () -> 9);

        // then the value is correct
        assertEquals(value, Integer.valueOf(0));
    }

    @Test
    public void testGetFactoryNoValuePreExists() throws Exception {
        // when an item is retrieved with a factory
        Integer value = subject.get("key", () -> 9);

        // then the value is correct
        assertEquals(value, Integer.valueOf(9));
        expectValue(new Pair<>("key", 9));
    }

    @SafeVarargs
    private final void expectValues(Pair<String, Integer>... values) {
        Arrays.stream(values).forEach(this::expectValue);
    }

    private void expectValue(Pair<String, Integer> pair) {
        assertEquals(pair.getValue(), subject.get(pair.getKey()).orElse(-1));
    }

    private void expectNoValues(String... keys) {
        Arrays.stream(keys).forEach(this::expectNoValue);
    }

    private void expectNoValue(String key) {
        assertFalse(subject.get(key).isPresent());
        assertFalse(subject.contains(key));
    }

    private void givenItemsInserted(int numItems) {
        for (int i = 0; i < numItems; i++) {
            subject.set(String.valueOf(i), i);
        }
    }
}