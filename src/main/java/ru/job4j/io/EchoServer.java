package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final String END = "bye";

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String input = in.readLine();
                    System.out.println(input);
                    out.write("HTTP/1.1 200 OK\r\n\".getBytes()".getBytes());
                    if (input.toLowerCase().contains(END)) {
                        server.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}