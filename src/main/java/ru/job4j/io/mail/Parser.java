package ru.job4j.io.mail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Parser {
    private final String path;
    private final String separator = ";";

    public Parser(String path) {
        this.path = path;
    }

    public Map<String, Set<String>> parse() {
        Map<String, Set<String>> rsl = new HashMap<>();
        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            for (String line : lines) {
                String[] ar = line.split(separator);
                if (ar.length < 2) {
                    throw new IllegalArgumentException("wrong file format, use: user;mail;mail...]");
                }
                rsl.put(ar[0], new HashSet<>(Arrays.asList(ar).subList(1, ar.length)));
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return rsl;
    }
}
