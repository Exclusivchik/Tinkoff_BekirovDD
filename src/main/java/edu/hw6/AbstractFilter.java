package edu.hw6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

@FunctionalInterface
public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    boolean accept(Path entry) throws IOException;

    default AbstractFilter and(AbstractFilter other) {
        return (entry) -> this.accept(entry) && other.accept(entry);
    }

    static AbstractFilter regularFile() {
        return Files::isRegularFile;
    }

    static AbstractFilter readable() {
        return Files::isReadable;
    }

    static AbstractFilter largerThan(long size) {
        return entry -> Files.size(entry) > size;
    }


    static AbstractFilter globMatches(String extension) {
        return entry -> entry.getFileName().toString().endsWith(extension);
    }

    static AbstractFilter regexContains(String regex) {
        return entry -> entry.getFileName().toString().matches(regex);
    }

    static AbstractFilter magicNumber(byte... magicBytes) {
        return entry -> {
            if (Files.isRegularFile(entry) && Files.isReadable(entry)) {
                try {
                    byte[] fileBytes = Files.readAllBytes(entry);
                    if (fileBytes.length >= magicBytes.length) {
                        for (int i = 0; i < magicBytes.length; i++) {
                            if (fileBytes[i] != magicBytes[i]) {
                                return false;
                            }
                        }
                        return true;
                    }
                } catch (IOException e) {
                    return false;
                }
            }
            return false;
        };
    }
}
