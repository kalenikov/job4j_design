package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        String code = "404";
        var rsl = new ArrayList<String>();
        try (var in = new BufferedReader(new FileReader(file))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] split = line.split("\\s");
                if (split[split.length - 2].equals(code)) {
                    rsl.add(line);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rsl;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}
