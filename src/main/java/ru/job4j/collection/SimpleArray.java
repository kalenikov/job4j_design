package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] elementData;
    private int modCount;
    private int size;
    private final int DEFAULT_CAPACITY = 10;

    public SimpleArray() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public SimpleArray(int initialCapacity) {
        elementData = new Object[initialCapacity];
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) elementData[index];
    }

    public void add(T model) {
        if (size == elementData.length) {
            elementData = Arrays.copyOf(elementData, elementData.length + DEFAULT_CAPACITY);
        }
        elementData[size++] = model;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int seek;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return seek < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) elementData[seek++];
            }
        };

    }
}