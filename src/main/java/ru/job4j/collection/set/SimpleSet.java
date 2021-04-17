package ru.job4j.collection.set;

import ru.job4j.collection.SimpleArray;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {
    private SimpleArray<T> elementData;

    public SimpleSet() {
        elementData = new SimpleArray<>();

    }

    @Override
    public boolean add(T value) {
        if (!contains(value)) {
            elementData.add(value);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T value) {
        for (T o : elementData) {
            if (Objects.equals(o, value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return elementData.iterator();
    }

    public static void main(String[] args) {
        Set<Integer> ss = new SimpleSet<>();
        ss.add(null);
        ss.add(2);
        ss.contains(null);
        ss.add(3);
        ss.add(null);
        ss.add(4);
        System.out.println(ss);

    }
}
