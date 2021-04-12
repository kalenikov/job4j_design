package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenTest {
    static void print(Collection<String> list){
        for (String s : list) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
//        List<String> strList = new ArrayList<>(List.of("1","2"));
//        List<Object> objList = new ArrayList<>();
//
//        print(strList);
//        print(objList);
    }
}
