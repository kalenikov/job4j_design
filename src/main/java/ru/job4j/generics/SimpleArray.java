package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] elementData;

    public SimpleArray(int initialCapacity) {
        elementData = new Object[initialCapacity];
    }

    public void add(T model) {
        // добавляем в первую свободную ячейку
        // учитываем, что можно сделать set(i, null) и в массиве будут null-элементы
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] == null) {
                elementData[i] = model;
                return;
            }
        }
        // нет пустых мест для добавления
        throw new ArrayIndexOutOfBoundsException();
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, elementData.length);
        elementData[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, elementData.length);
        System.arraycopy(elementData, index + 1, elementData, index, elementData.length - index - 1);
    }

    T get(int index) {
        Objects.checkIndex(index, elementData.length);
        return (T) elementData[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }

    @Override
    public Iterator<T> iterator() {
        return (Iterator<T>) Arrays.asList(elementData).iterator();
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
