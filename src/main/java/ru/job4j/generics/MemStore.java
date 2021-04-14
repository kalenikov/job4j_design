package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int idx = findIndexById(id);
        if (idx == -1) {
            return false;
        }
        mem.set(idx, model);
        return true;
    }

    @Override
    public boolean delete(String id) {
        int idx = findIndexById(id);
        if (idx == -1) {
            return false;
        }
        mem.remove(idx);
        return true;
    }

    @Override
    public T findById(String id) {
        int idx = findIndexById(id);
        if (idx == -1) {
            return null;
        }
        return mem.get(idx);
    }

    private int findIndexById(String id) {
        for (int i = 0; i < mem.size(); i++) {
            if (id.equals(mem.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }
}