package ru.job4j.io;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Shell {
    private Path path = Paths.get(System.getProperty("user.home"));

    public Shell cd(String path) {
        this.path = this.path.resolve(path).normalize();
        return this;
    }

    public String pwd() {
        return path.toString();
    }

    public static void main(String[] args) {

        System.out.println(Paths.get("c:\\temp\\java\\jdk\\").resolve(Paths.get("..")).normalize());

        Shell shell = new Shell();
        assert shell.pwd().equals("C:\\Users\\serj");

        shell.cd("c:\\temp\\java\\jdk\\");
        assert shell.pwd().equals("c:\\temp\\java\\jdk");

        shell.cd("..");
        assert shell.pwd().equals("c:\\temp\\java");

        shell.cd("c:\\temp").cd("py");
        assert shell.pwd().equals("c:\\temp\\py");
    }
}