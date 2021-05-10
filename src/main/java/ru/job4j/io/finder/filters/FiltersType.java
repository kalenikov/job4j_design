package ru.job4j.io.finder.filters;

public enum FiltersType {
    MASK,
    NAME,
    REGEXP;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
