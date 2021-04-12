package ru.job4j.generics;

import java.util.*;

public class GenericUsage {
    public static void main(String[] args) {
        List<Integer> l = List.of(1, 2, 3, 4, 5);
        new GenericUsage().printRsl(l);
    }

    public void printRsl(Collection<?> col) {
        for (Iterator<?> it = col.iterator(); it.hasNext(); ) {
            Object next = it.next();
            System.out.println(next);
        }
    }

}