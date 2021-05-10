package ru.job4j.io.finder.filters;

public class FilterFactory {
    public Filter getFilter(FiltersType filtersType) {
        return switch (filtersType) {
            case MASK -> new MaskFilter();
            case NAME -> new NameFilter();
            case REGEXP -> new RegexpFilter();
        };
    }
}
