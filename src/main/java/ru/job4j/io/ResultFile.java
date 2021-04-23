package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ResultFile {
    public static void main(String[] args) {
        var sb = new StringBuilder();
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                sb.append(String.format("%d x %d = %d%n", i, j, i * j));
            }
        }
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("result.txt")))) {
            out.write(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}