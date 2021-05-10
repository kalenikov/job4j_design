package ru.job4j.io.finder.filters;

import java.nio.file.Path;

public class MaskFilter implements Filter {

    @Override
    public boolean test(Path path, String pattern) {
        String regex = pattern
                .replace("?", ".?")
                .replace("*", ".*?");
        return path.toString().matches(regex);
    }
}
