package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, List<Path>> files = new HashMap<>();
    private Path startPath;
    private String fileExtension;

    public DuplicatesVisitor(String startDir, String fileExtension) {
        this.startPath = Path.of(startDir);
        this.fileExtension = fileExtension;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!file.getFileName().toString().endsWith(fileExtension)) {
            return super.visitFile(file, attrs);
        }
        var fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
        if (null == files.computeIfPresent(fileProperty, (k, v) -> {
            v.add(file.toAbsolutePath());
            return v;
        })) {
            files.put(fileProperty, new ArrayList<>(Arrays.asList(file.toAbsolutePath())));
        }
        return super.visitFile(file, attrs);
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        if (Files.isSameFile(dir, startPath)) {
            files.entrySet().stream()
                    .filter(el -> el.getValue().size() > 1)
                    .forEach(el -> System.out.println(el.getKey().getName()));
            return FileVisitResult.TERMINATE;
        }
        return super.postVisitDirectory(dir, exc);
    }

}