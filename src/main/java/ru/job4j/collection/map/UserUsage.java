package ru.job4j.collection.map;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserUsage {
    public static void main(String[] args) {

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "test1");
        map.put(2, "test2");
        map.put(3, "test3_1");
        map.put(18, "test3_2");

        System.out.println(true & false);
//        User user1 = new User("u1", 10, new GregorianCalendar(2010, 1, 1));
//        User user1_copy = new User("u1", 10, new GregorianCalendar(2010, 1, 1));
//        User user2 = new User("u2", 20, new GregorianCalendar(2020, 2, 2));
//
//        HashMap<User, Object> map = new HashMap<>();
////        map.hashCode()
//        map.put(user1, new Object());
//        map.put(user1_copy, new Object());

//        System.out.println(user1.equals(user1_copy));

//        System.out.println(user1.hashCode());
//        System.out.println(user1_copy.hashCode());

//        for (var entry : map.entrySet()) {
//            System.out.println(entry);
//        }
//        for (int i = 1; i <= 10; i++) {
//            System.out.println(i << i);
//            System.out.println(i * Math.pow(2, i));
//
//        }
//        int src = 123456789;
//        System.out.println(Integer.toBinaryString(src));
//        System.out.println(Integer.toBinaryString(-src));
//        System.out.println(binary(src));
//        System.out.println(binary(src >>> 16));
//        System.out.println(binary(src ^ (src >>> 16)));


//        System.out.println(binary(src ^ 1));

    }

    public static String binary(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 31; i++) {
            sb.append(num % 2 == 0 ? 0 : 1);
            sb.append((i + 1) % 8 == 0 ? " " : "");
            num /= 2;
        }
        return sb.reverse().toString();
    }
}
