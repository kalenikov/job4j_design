package ru.job4j.io.finder;

import ru.job4j.io.finder.filters.Filter;
import ru.job4j.io.finder.filters.FilterFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Finder {
    public static void main(String[] args) {
        FinderOptions options = new FinderOptions();
        if (!options.parseArgs(args)) {
            return;
        }
        Filter filter = new FilterFactory().getFilter(options.getFilterType());
        FinderVisiter finderVisiter = new FinderVisiter(filter, options.getPattern());
        try (PrintWriter out = new PrintWriter(options.getOutFile())) {
            Files.walkFileTree(Path.of(options.getDir()), finderVisiter);
            List<Path> files = finderVisiter.getFiles();
            files.forEach(out::println);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
