package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;
    private int modCount;

    public static void main(String[] args) {
        var ll = new SimpleLinkedList<String>();
        ll.add("1");
        ll.add("2");
        ll.add("3");
        ll.add("4");
        var it = ll.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Override
    public void add(E value) {
        Node<E> prevLast = last;
        Node<E> newNode = new Node<>(last, value, null);
        last = newNode;
        if (prevLast == null) {
            first = newNode;
        } else {
            prevLast.next = newNode;
        }
        modCount++;
        size++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> el = first;
        for (int i = 0; i < index; i++) {
            el = el.next;
        }
        return el.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> currentNode = first;
            private int index = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                index++;
                E rsl = currentNode.item;
                currentNode = currentNode.next;
                return rsl;
            }
        };
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        public Node(Node<E> prev, E item, Node<E> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }
}