package ru.job4j.io.console_chat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
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
    private List<String> log = new ArrayList<>();

    public ConsoleChat(String path, String botAnswersPath) {
        this.path = path;
        this.botAnswersPath = botAnswersPath;
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(
                "src/main/resources/log.txt",
                "src/main/resources/ans.txt");
        cc.run();
    }

    private void run() {
        try (Scanner sc = new Scanner(System.in);
             BufferedReader in = new BufferedReader(new FileReader(botAnswersPath));
             PrintWriter out = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251")))) {
            List<String> answerList = in.lines().collect(Collectors.toList());
            String nextInput = nextInput(sc);
            boolean silentMode = false;
            while (!nextInput.equals(OUT)) {
                if (nextInput.equals(STOP)) {
                    silentMode = true;
                } else if (nextInput.equals(CONTINUE)) {
                    silentMode = false;
                }
                if (!silentMode) {
                    String nextOutput = answerList.get(new Random().nextInt(answerList.size()));
                    System.out.println(nextOutput);
                    log.add(nextOutput);
                }
                nextInput = sc.nextLine();
                log.add(nextInput);
            }
            log.forEach(out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String nextInput(Scanner sc) {
        String nextInput = sc.nextLine();
        log.add(nextInput);
        return nextInput;
    }
}
