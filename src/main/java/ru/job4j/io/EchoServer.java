package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final String END = "exit";
    private static final String HELLO = "hello";
    private static final Logger log = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String input = in.readLine();
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String response;
                    if (input.toLowerCase().contains(HELLO)) {
                        response = "Hello, dear friend";
                    } else if (input.toLowerCase().contains(END)) {
                        response = END;
                    } else {
                        response = input.toLowerCase().split("=")[1].split("\\s")[0];
                    }
                    out.write(response.getBytes());
                    if (response.equals(END)) {
                        server.close();
                    }
                }
            }
        } catch (Exception e) {
            log.error("Exception in ServerSocket", e);
        }
    }
}