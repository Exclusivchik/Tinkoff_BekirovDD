package edu.hw9.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public class FilesSearchTask extends RecursiveTask<List<File>> {
    private final File directory;
    private final Predicate<File> predicate;

    public FilesSearchTask(File directory, Predicate<File> predicate) {
        this.directory = directory;
        this.predicate = predicate;
    }

    @Override
    protected List<File> compute() {
        List<File> appropriateFiles = new ArrayList<>();

        File[] files = directory.listFiles();
        if (files != null) {
            List<FilesSearchTask> subTasks = new ArrayList<>();
            for (File file : files) {
                if (file.isDirectory()) {
                    FilesSearchTask subTask = new FilesSearchTask(file, predicate);
                    subTask.fork();
                    subTasks.add(subTask);
                } else {
                    if (predicate.test(file)) {
                        appropriateFiles.add(file);
                    }
                }
            }

            for (FilesSearchTask subTask : subTasks) {
                appropriateFiles.addAll(subTask.join());
            }
        }
        return appropriateFiles;
    }
}
