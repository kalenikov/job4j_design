package ru.job4j.io;

import java.util.*;
import java.util.stream.Collectors;

public class ShellEmulator {
    private LinkedList<String> dirs = new LinkedList<>();

    // Поскольку это эмулятор, то мы НЕ обращаемся к ФС с проверками,
    // а работаем только с переданными параметрами.
    // Основная идея: класть передаваемые директории в стек, а при движении назад забирать их оттуда

    public ShellEmulator cd(String path) {
        if (path.startsWith("/")) {
            dirs.clear();
        }
        path = path.replaceAll("/+", "/");
        List<String> ar = Arrays.stream(path.split("/"))
                .filter(s -> s.length() > 0 && !s.equals("."))
                .collect(Collectors.toList());
        for (String el : ar) {
            if (el.equals("..")) {
                dirs.removeLast();
            } else {
                dirs.addLast(el);
            }
        }
        return this;
    }

    public String path() {
        StringJoiner rsl = new StringJoiner("/", "/", "");
        for (String dir : dirs) {
            rsl.add(dir);
        }
        return rsl.toString();
    }

    public static void main(String[] args) {

        final ShellEmulator shell = new ShellEmulator();
        assert shell.path().equals("/");

        shell.cd("/");
        assert shell.path().equals("/");

        shell.cd("usr/..");
        assert shell.path().equals("/");

        shell.cd("usr").cd("local");
        shell.cd("../local").cd("./");
        assert shell.path().equals("/usr/local");

        shell.cd("..");
        assert shell.path().equals("/usr");

        shell.cd("//lib///");
        assert shell.path().equals("/lib");

    }
}