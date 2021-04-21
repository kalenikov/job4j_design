package ru.job4j.collection.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable<K> {
    private static int DEFAULT_CAPACITY = 16;
    private static float DEFAULT_LOAD_FACTOR = 0.75f;
    private Node<K, V>[] table;
    private int size;
    private int modCount;
    private int threshold;

    public SimpleHashMap(int capacity) {
        table = new Node[capacity];
        // когда кол-во добавленных элементов достигнет этого значения, размер таблицы будет увеличен
        threshold = (int) (capacity * DEFAULT_LOAD_FACTOR);
    }

    public SimpleHashMap() {
        this(DEFAULT_CAPACITY);
    }

    private static class Node<K, V> {
        K key;
        V value;
        int hash;

        public Node(K key, V value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }

        @Override
        public String toString() {
            return String.format("(%s, %s, %d)", key, value, hash);
        }
    }

    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        int hk = key.hashCode();
        return hk ^ (hk >>> 16);
    }

    @Override
    public String toString() {
        return Arrays.toString(table);
    }

    protected void resize() {
        if (size > threshold) {
            int newCapacity = table.length * 2;
            this.threshold = (int) (newCapacity * DEFAULT_LOAD_FACTOR);
            Node<K, V> row;
            Node<K, V>[] newTable = new Node[newCapacity];
            System.out.println(String.format("resize to %d, new threshold: %d", newCapacity, threshold));
            for (int i = 0; i < table.length; i++) {
                if ((row = table[i]) == null) {
                    continue;
                }
                int idx = row.hash & (newCapacity - 1);
                newTable[idx] = row;
            }
            table = newTable;
        }
    }

    public boolean insert(K key, V value) {
        Node<K, V> row;
        int hash;
        int i = (hash = hash(key)) & (table.length - 1);
        // если в этой ячейке еще ничего нет, добавляем туда элемент
        if ((row = table[i]) == null) {
            table[i] = new Node<>(key, value, hash);
            size++;
            modCount++;
            resize();
            return true;
        } else {
            // если в эту ячейку уже что-то записано
            // и ключи совпадают
            if (row.hash == hash
                    && row.key.equals(key)) {
                table[i].value = value;
                modCount++;
                return true;
            } else {
                // ключи не совпадают (произошла коллизия)
                return false;
            }
        }
    }

    public V get(K key) {
        int hash = hash(key);
        Node<K, V> node = table[hash & (table.length - 1)];
        if (node != null
                && node.hash == hash
                && node.key.equals(key)) {
            return node.value;
        }
        return null;
    }

    public boolean delete(K key) {
        int hash = hash(key);
        Node<K, V> node = table[hash & (table.length - 1)];
        if (node != null
                && node.hash == hash
                && node.key.equals(key)) {
            table[hash & (table.length - 1)] = null;
            size--;
            modCount++;
            return true;
        }
        return false;
    }


    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int seek = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                // дальше текущей позиции есть непустые ячейки
                while (seek < table.length && table[seek] == null) {
                    seek++;
                }
                return seek < table.length;
            }

            @Override
            public K next() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[seek++].key;
            }
        };
    }


    public static void main(String[] args) {
        var sm = new SimpleHashMap<Integer, Integer>();
        sm.insert(10, 1);
        sm.insert(11, 2);

        var it = sm.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
