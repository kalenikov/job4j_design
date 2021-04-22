package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (var input = new FileInputStream("even.txt")) {
            var sb = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                sb.append((char) read);
            }
            String[] lines = sb.toString().split(System.lineSeparator());
            for (String line : lines) {
                boolean even = Integer.parseInt(line) % 2 == 0;
                System.out.println(line + " четное: " + even);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
