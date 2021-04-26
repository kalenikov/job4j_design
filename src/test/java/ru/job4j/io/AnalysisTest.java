package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class AnalysisTest {

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Test
    public void whenAnalysis() throws IOException {
        File source = temp.newFile("server.log");
        File target = temp.newFile("unavailable.csv");
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
        new Analysis().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        BufferedReader reader = new BufferedReader(new FileReader(target));
        List<String> rsl = reader.lines().collect(Collectors.toList());
        List<String> expected = List.of(
                "11:00:00;13:00:00",
                "15:00:00;18:00:00");
        assertEquals(rsl, expected);
    }
}