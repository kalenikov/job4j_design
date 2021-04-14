package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> newNode = new Node<T>(value, null);
        if (head == null) {
            head = newNode;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = newNode;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> prevHead = head;
        head = head.next;
        prevHead.next = null;
        return prevHead.value;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        var ll = new ForwardLinked<String>();
        ll.add("1");
        ll.add("2");
        ll.add("3");
        ll.deleteFirst();
        ll.deleteFirst();
        ll.add("4");
        ll.deleteFirst();
        var it = ll.iterator();
        while (it.hasNext())
            System.out.println(it.next());
    }
}