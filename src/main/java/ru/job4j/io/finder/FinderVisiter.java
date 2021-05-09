package ru.job4j.io.finder;

import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.util.ArrayList;
import java.util.List;

public class FinderVisiter extends SimpleFileVisitor<Path> {
    private List<Path> files = new ArrayList<>();

    public List<Path> getFiles() {
        return files;
    }
}
