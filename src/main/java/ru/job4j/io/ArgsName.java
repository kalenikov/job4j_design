package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        Arrays.stream(args)
                .map(el -> el.replace("-", "").split("="))
                .forEach(ar -> {
                    if (ar.length < 2) {
                        throw new IllegalArgumentException();
                    }
                    values.put(ar[0], ar[1]);
                });
    }
}