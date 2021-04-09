package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] data;
    private int seek = 0;

    public static void main(String[] args) {
        EvenNumbersIterator it = new EvenNumbersIterator(new int[]{4, 2, 1, 1});
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (seek < data.length && data[seek] % 2 != 0) {
            seek++;
        }
        return seek < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[seek++];
    }
}
