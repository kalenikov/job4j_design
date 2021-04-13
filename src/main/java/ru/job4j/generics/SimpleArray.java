package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] elementData;
    private int count;

    public SimpleArray(int initialCapacity) {
        elementData = new Object[initialCapacity];
    }

    public void add(T model) {
        Objects.checkIndex(count, elementData.length);
        elementData[count++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, count);
        elementData[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, count);
        System.arraycopy(elementData, index + 1, elementData, index, count - index - 1);
        count--;
    }

    public T get(int index) {
        Objects.checkIndex(index, count);
        return (T) elementData[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int seek;

            @Override
            public boolean hasNext() {
                return seek < count;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) elementData[seek++];
            }
        };
    }

    public static void main(String[] args) {
        SimpleArray<String> array = new SimpleArray<>(5);
        array.add("1");
        array.add("2");
        array.add("3");
        array.add("4");
//        System.out.println(array);
//        array.remove(0);
//        System.out.println(array.get(0));
        System.out.println(array);
        array.set(0, null);
        System.out.println(array);
        array.add("1");
        System.out.println(array);
//        System.out.println(array);
//        System.out.println(array);
    }
}
