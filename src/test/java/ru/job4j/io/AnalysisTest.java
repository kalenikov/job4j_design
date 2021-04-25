package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class AnalysisTest {

    @Test
    public void whenAnalysis() throws IOException {
        String source = "src/test/resources/server.log";
        String target = "src/test/resources/unavailable.csv";
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("300 10:00:00");
            out.println("400 11:00:00");
            out.println("500 12:00:00");
            out.println("300 13:00:00");
            out.println("300 14:00:00");
            out.println("500 15:00:00");
            out.println("500 16:00:00");
            out.println("400 17:00:00");
            out.println("200 18:00:00");
            out.println("300 19:00:00");
            out.println("300 20:00:00");
        }
        new Analysis().unavailable(source, target);
        BufferedReader reader = new BufferedReader(new FileReader(target));
        List<String> rsl = reader.lines().collect(Collectors.toList());
        List<String> expected = List.of(
                "11:00:00;13:00:00",
                "15:00:00;18:00:00");
        assertEquals(rsl, expected);
    }
}