package ru.job4j.io;

import java.io.*;

public class IOTest {
    public static void main(String[] args) throws IOException {
        String path = "c:\\temp\\java\\toxic_data\\result.json";
        FileInputStream fis = new FileInputStream(path);
        InputStreamReader is = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(is);

        String line = br.readLine();
        while(line != null){
            System.out.println(line);
            line = br.readLine();
        }
    }
}
