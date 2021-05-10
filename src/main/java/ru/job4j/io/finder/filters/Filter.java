package ru.job4j.io.finder.filters;

import java.nio.file.Path;
import java.util.function.BiPredicate;

public interface Filter extends BiPredicate<Path, String> {
    @Override
    boolean test(Path path, String s);
}
