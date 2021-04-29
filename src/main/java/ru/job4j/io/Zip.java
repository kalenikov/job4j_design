package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private static File file;

    public static void main(String[] args) throws IOException {
        ArgsName params = ArgsName.of(args);
        String sourceDir = params.get("d");
        String targetFile = params.get("o");
        String ext = params.get("e");

        validateArgs(args, sourceDir, targetFile, ext);

        SearchFiles searcher = new SearchFiles(p -> !p.toFile().getName().endsWith(ext));
        Files.walkFileTree(Path.of(sourceDir), searcher);
        List<File> sources = searcher.getPaths().stream().map(Path::toFile).collect(Collectors.toList());
        new Zip().packFiles(sources, new File(targetFile));
    }

    private static void validateArgs(String[] args, String sourceDir, String targetFile, String ext) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Incorrect numbers of params. Usage example java -jar pack.jar -d=ROOT_DIR -e=FILE_EXT -o=OUT_FILE");
        }
        if (sourceDir == null) {
            throw new IllegalArgumentException("Parameter not specified: ROOT_DIR");
        }
        if (ext == null) {
            throw new IllegalArgumentException("Parameter not specified: FILE_EXT");
        }
        if (targetFile == null) {
            throw new IllegalArgumentException("Parameter not specified: OUT_FILE");
        }
        file = new File(sourceDir);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
    }

    public void packFiles(List<File> sources, File target) {
        try (var zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                Path innerPath = file.toPath().relativize(source.toPath());
                try (var out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.putNextEntry(new ZipEntry(innerPath.toString()));
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
