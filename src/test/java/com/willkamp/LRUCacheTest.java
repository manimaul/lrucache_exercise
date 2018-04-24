package com.willkamp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LRUCacheTest extends Assert {

    private LRUCache subject;

    @Before
    public void beforeEach() {

    }

    @Test
    public void test() {
        assertNotNull(subject);
    }
}