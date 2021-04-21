package ru.job4j.collection.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {

    @Test
    public void whenInsertThenGet() {
        var sm = new SimpleHashMap<String, Integer>();
        sm.insert("key1", 1);
        sm.insert("key2", 2);
        sm.insert("key3", 3);
        assertThat(sm.get("key3"), is(3));
        assertThat(sm.get("key2"), is(2));
    }

    @Test
    public void whenSameKeysSameHash() {
        var sm = new SimpleHashMap<Integer, Integer>();
        assertTrue(sm.insert(1, 10));
        assertTrue(sm.insert(1, 20));
        assertThat(sm.get(1), is(20));
    }

    @Test
    public void whenDiffKeysSameHash() {
        var sm = new SimpleHashMap<Integer, Integer>();
        assertTrue(sm.insert(1, 1));
        assertFalse(sm.insert(17, 1));
        assertThat(sm.get(1), is(1));
    }

    @Test
    public void whenIterate() {
        var sm = new SimpleHashMap<String, Integer>();
        sm.insert("key1", 1);
        sm.insert("key2", 2);
        sm.insert("key3", 3);
        var it = sm.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is("key1"));
        assertThat(it.next(), is("key2"));
        assertThat(it.next(), is("key3"));
        assertFalse(it.hasNext());
    }

    @Test
    public void whenDelete() {
        var sm = new SimpleHashMap<String, Integer>();
        sm.insert("key1", 1);
        assertThat(sm.get("key1"), is(1));
        assertTrue(sm.delete("key1"));
        assertNull(sm.get("key1"));
        assertFalse(sm.delete("key1"));
    }

    @Test
    public void whenResize() {
        var sm = new SimpleHashMap<Integer, Integer>(4);
        for (int key = 3; key < 10; key++) {
            sm.insert(key, key);
        }
        assertThat(sm.get(9), is(9));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        var sm = new SimpleHashMap<Integer, Integer>();
        sm.insert(1, 1);
        var it = sm.iterator();
        sm.insert(2, 2);
        it.next();
    }

}