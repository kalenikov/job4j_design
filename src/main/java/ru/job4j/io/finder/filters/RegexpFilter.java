package ru.job4j.io.finder.filters;

import java.nio.file.Path;

public class RegexpFilter implements Filter {
    @Override
    public boolean test(Path path, String pattern) {
        return path.toString().matches(pattern);
    }
}
