package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public static void main(String[] args) {
//        int[][] array = new int[][]{{1}, {2, 3, 4, 5,}, {6, 7}, {8, 9, 10, 11, 12,}};
//        int[][] array = new int[][]{{1}, {}, {}, {2, 3, 4, 5}};
//        int[][] array = new int[][]{{}, {1}, {}, {}, {}, {2}};
        int[][] array = new int[][]{{1, 2, 3}};
//        int[][] array = new int[][]{{1}, {2}, {3}};
//        int[][] array = new int[][]{{11, 12}, {}, {31}, {}, {51}};
//        int[][] array = new int[][]{{1, 2}, {3}};
//        int[][] array = new int[][]{{}, {}};
//        int[][] array = new int[][]{{}, {21}};
//        int[][] array = new int[][]{{}, {}, {31}, {}, {}, {61}};
        MatrixIt i = new MatrixIt(array);
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        System.out.println("column" + column);
        // выполнится если оба условия отработали:
        // 1. строка < колво строк (еще не прошли все строки)
        // 2. длина строки == номер колонки (дошлит до конца текущей строки)
        while (row < data.length && data[row].length == column) {
            row++;
            column = 0;
        }
        return row < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}
