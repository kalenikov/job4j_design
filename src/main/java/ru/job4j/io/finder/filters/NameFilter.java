package ru.job4j.io.finder.filters;

import java.nio.file.Path;

public class NameFilter implements Filter {

    @Override
    public boolean test(Path path, String pattern) {
        return path.getFileName().toString().equals(pattern);
    }
}
