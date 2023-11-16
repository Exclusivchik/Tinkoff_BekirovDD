package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public final class FileCloner {
    private FileCloner() {
    }

    public static Path cloneFile(String stringFilePath) {
        Path filePath = Path.of(stringFilePath);
        String fileName = filePath.getFileName().toString();
        String fileExtension = "";
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex != -1) {
            fileExtension = fileName.substring(dotIndex);
            fileName = fileName.substring(0, dotIndex);
        }
        String directory = "";
        if (filePath.getParent() == null) {
            directory = "";
        } else {
            directory += filePath.getParent().toString();
        }
        int count = 1;
        String stringNewFilePath = filePath.toString();
        while (Files.exists(Path.of(stringNewFilePath))) {
            String newFileName;
            if (count > 1) {
                newFileName = fileName + " — копия (" + count + ")";
            } else {
                newFileName = fileName + " — копия";
            }
            stringNewFilePath = directory + newFileName + fileExtension;
            count++;
        }
        Path newFilePath = Path.of(stringNewFilePath);
        try {
            Files.copy(filePath, newFilePath, StandardCopyOption.COPY_ATTRIBUTES);
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return newFilePath;
    }
}
