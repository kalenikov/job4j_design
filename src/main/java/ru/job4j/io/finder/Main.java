package ru.job4j.io.finder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
//1. Создать программу для поиска файла. Все классы, относящиеся к этому заданию должны быть в отдельном пакете
//2. Программа должна искать данные в заданном каталоге и подкаталогах.
//3. Имя файла может задаваться, целиком, по маске, по регулярному выражению(не обязательно).
//4. Программа должна собираться в jar и запускаться через java -jar find.jar -d=c:/ -n=*.txt -t=mask -o=log.txt
//Ключи
//-d - директория, в которой начинать поиск.
//-n - имя файла, маска, либо регулярное выражение.
//-t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
//-o - результат записать в файл.
//5. Программа должна записывать результат в файл.
//6. В программе должна быть валидация ключей и подсказка.


public class Main {
    public static void main(String[] args) {
//        FinderVisiter finderVisiter = new FinderVisiter();
//        Files.walkFileTree("c:\\", finderVisiter);
//        List<Path> foundFiles = finderVisiter.getFiles();
//        for (Path file : foundFiles) {
//            System.out.println(file);
//        }
    }
}
