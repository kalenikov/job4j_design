package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringJoiner;

public class Analysis {
    public void unavailable(String source, String target) {
        final String delimiter = ";";
        try (var reader = new BufferedReader(new FileReader(source));
             var out = new PrintWriter(new FileOutputStream(target))) {

            // флаг того, что сервер был недоступен ранее (допускаем, что в начале лога сервер всегда доступен)
            boolean serverWasDown = false;

            var sj = new StringJoiner(delimiter);
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] split = line.split("\\s");
                String status = split[0];
                String time = split[1];

                // флаг того, что сервер перешел в состояние недоступности в текущей строке лога
                boolean serverIsDown = status.equals("400") || status.equals("500");

                // сервер недоступен, хотя ранее был доступен
                if (serverIsDown && !serverWasDown) {
                    sj.add(time);
                    serverWasDown = true;

                // сервер был недоступен, а теперь стал доступен
                } else if (!serverIsDown && serverWasDown) {
                    sj.add(time);
                    serverWasDown = false;
                    out.println(sj.toString());
                    sj = new StringJoiner(delimiter);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
         }
    }
}
