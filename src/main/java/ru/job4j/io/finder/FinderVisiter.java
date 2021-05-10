package ru.job4j.io.finder;

import ru.job4j.io.finder.filters.Filter;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FinderVisiter extends SimpleFileVisitor<Path> {
    private final Filter filter;
    private final String pattern;
    private List<Path> files = new ArrayList<>();

    public FinderVisiter(Filter filter, String pattern) {
        this.filter = filter;
        this.pattern = pattern;
    }

    public List<Path> getFiles() {
        return files;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (filter.test(file, pattern)) {
            files.add(file.getFileName());
        }
        return super.visitFile(file, attrs);
    }
}
