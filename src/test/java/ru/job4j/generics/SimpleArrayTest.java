package ru.job4j.generics;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleArrayTest {
    @Test
    public void whenAddThenGet() {
        SimpleArray<String> array = new SimpleArray<>(5);
        array.add("1");
        assertThat(array.get(0), is("1"));
    }

    @Test
    public void whenSetThenGet() {
        SimpleArray<String> array = new SimpleArray<>(5);
        array.add("1");
        array.set(0, "2");
        assertThat(array.get(0), is("2"));
    }

    @Test
    public void whenAddThenRemove() {
        SimpleArray<String> array = new SimpleArray<>(5);
        array.add("1");
        array.add("2");
        array.add("3");
        array.remove(0);
        System.out.println(array);
        assertThat(array.get(0), is("2"));
    }

    @Test
    public void whenSetNullThenAdd() {
        SimpleArray<String> array = new SimpleArray<>(5);
        array.add("1");
        array.add(null);
        array.add("3");
        System.out.println(array);
        assertNull(array.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenOverflow() {
        SimpleArray<String> array = new SimpleArray<>(1);
        array.add("1");
        array.add("2");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        SimpleArray<String> array = new SimpleArray<>(1);
        array.add("1");
        array.get(1);
    }

    @Test
    public void whenIterate() {
        SimpleArray<String> array = new SimpleArray<>(2);
        array.add("1");
        array.add("2");
        Iterator<String> it = array.iterator();
        assertThat(it.next(), is("1"));
        assertThat(it.next(), is("2"));
    }
}