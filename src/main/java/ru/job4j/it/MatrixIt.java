package ru.job4j.it;

import java.util.Arrays;
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
//        int[][] array = new int[][]{{1, 2, 3}};
//        int[][] array = new int[][]{{1}, {2}, {3}};
//        int[][] array = new int[][]{{11, 12}, {}, {31}, {}, {51}};
//        int[][] array = new int[][]{{1, 2}, {3}};
        int[][] array = new int[][]{{}, {11}};
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
        // здесь мы должны понять, есть ли дальше (начиная с текущей позиции) элементы в матрице
        // для оптимизации можно сразу передвинуть указатели, чтобы не повторять поиск дальше в методе next()
        // (например для случая, если в матрице много пустых строк)

        for (int row = this.row; row < data.length; row++) {
            for (int column = this.column; column < data[row].length; column++) {
                this.row = row;
                this.column = column;
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        // здесь мы должны вернуть элемент с позиции текущих указателей (которые определили в hasNext)
        // и передвинуть их на шаг вперед
        int next = data[row][column];
        System.out.print("строка " + row + ", колонок в строке " + data[row].length + ", значение ячейки: ");
        column++;
        // если стоим на последней колонке строки
        if (column == data[row].length) {
            // переходим в 1ю колонку
            column = 0;
            // новой строки
            row++;
        }
        return next;
    }
}
