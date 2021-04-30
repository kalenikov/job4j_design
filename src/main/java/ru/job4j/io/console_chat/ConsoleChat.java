package ru.job4j.io.console_chat;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswersPath;

    public ConsoleChat(String path, String botAnswersPath) {
        this.path = path;
        this.botAnswersPath = botAnswersPath;
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(
                "C:\\temp\\java\\job4j_design\\src\\main\\resources\\log.txt",
                "C:\\temp\\java\\job4j_design\\src\\main\\resources\\ans.txt");
        try {
            cc.run();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


    private void run() throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedReader in = new BufferedReader(new FileReader(botAnswersPath));
        BufferedWriter out = new BufferedWriter(new FileWriter(path, Charset.forName("WINDOWS-1251")));
        List<String> answerList = in.lines().collect(Collectors.toList());
        boolean run = true;
        boolean silentMode = false;
        while (run) {
            String nextInput = sc.nextLine();
            out.write(nextInput);
            if (!silentMode) {
                String nextOutput = answerList.get(new Random().nextInt(answerList.size()));
                System.out.println(nextOutput);
                out.newLine();
                out.write(nextOutput);
            }
            if (nextInput.equals(STOP)) {
                run = false;
            } else if (nextInput.equals(OUT)) {
                silentMode = true;
            } else if (nextInput.equals(CONTINUE)) {
                silentMode = false;
            }
            out.newLine();
        }
        sc.close();
        in.close();
        out.close();
    }
}
