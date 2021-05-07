package ru.job4j.generics.get_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        List<AA> v1 = new ArrayList<>();
//        v1.add(new AA());
//
//        List<? super AA> v2 = new ArrayList<>();
//        v2.add(new AA());
//        v2.add(new BB());
//
//        printExt(new CC());

        //    ? => ? extends Object
        //    Следовательно, согласно принципу PECS,
        //    list – это producer. А producer только продюсирует элементы.
//        ываем метод add(), т.е. пытаемся записать в list.
//        И поэтому упираемся в защиту Java, что не позволяет установить какое-то значение по индексу.
//        List<? extends Object> v3 = new ArrayList<>();
//        v3.add(new AA());

//        add(new AA());
//
//        printExtList(List.of(new AA(), new CC()));
//        printSupList(List.of(new CC(), new BB()));
//        print1(List.of(new AA(), new BB(), new CC(), new ZZ()));
//        print2(List.of(new AA(), new BB(), new CC(), new ZZ()));
//        print3(List.of(new BB(), new CC()));
//        print4(List.of(new AA(), new BB(), new CC(), new ZZ()));


//        АА - родитель BB
//        BB - родитель CC
//        List<? super BB> aa = new ArrayList<>();
//        aa.add(new AA());
//        aa.add(new BB());
//        aa.add(new CC());
//
//        print4(aa);

        ArrayList list = new ArrayList();
        list.add("test");
        list.add(100);
        list.add(null);
        System.out.println(hasNulls(list));
        System.out.println(hasNulls2(list));
    }

    public static boolean hasNulls(ArrayList<?> elements) {
        for (Object e : elements) {
            if (e == null) return true;
        }
        return false;
    }

    public static <T> boolean hasNulls2(ArrayList<T> elements) {
        for (Object e : elements) {
            if (e == null) return true;
        }
        return false;
    }

    //        public static <T> boolean hasNulls(ArrayList<T>  elements) {
//
    private static void print4(Collection<? super BB> e) {
        System.out.println(e);
    }


    public static <T> void add(T el) {
        List<T> rsl = new ArrayList<>();
        rsl.add(el);
    }


    private static <E extends BB> void printExt(E e) {
        System.out.println(e);
    }

//    private static <E> void printSup(<E super>e) {
////        System.out.println(e);
////    }

    private static void printExtList(List<? extends AA> e) {
        System.out.println(e);
    }

    private static void printSupList(List<? super BB> e) {
        System.out.println(e);
    }


    private static void print1(List<?> e) {
        System.out.println(e);
    }

    private static <T> void print2(List<T> e) {
        System.out.println(e);
    }

    private static <T extends BB> void print3(List<T> e) {
        System.out.println(e);
    }


}
