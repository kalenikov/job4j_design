package ru.job4j.io.finder;

public enum SearchType {
    MASK,
    NAME,
    REGEXP;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
