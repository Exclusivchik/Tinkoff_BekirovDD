package edu.hw9.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DirectorySearchTask extends RecursiveTask<List<File>> {
    private final File directory;
    private final int filesCount;

    public DirectorySearchTask(File directory, int filesCount) {
        this.directory = directory;
        this.filesCount = filesCount;
    }

    @Override
    protected List<File> compute() {
        List<File> bigDirectories = new ArrayList<>();

        File[] files = directory.listFiles();
        if (files != null) {
            int fileCounter = 0;
            List<DirectorySearchTask> subTasks = new ArrayList<>();
            for (File file : files) {
                if (file.isDirectory()) {
                    DirectorySearchTask subTask = new DirectorySearchTask(file, filesCount);
                    subTask.fork();
                    subTasks.add(subTask);
                } else {
                    fileCounter++;
                }
            }

            for (DirectorySearchTask subTask : subTasks) {
                bigDirectories.addAll(subTask.join());
            }

            if (fileCounter > filesCount) {
                bigDirectories.add(directory);
            }
        }
        return bigDirectories;
    }
}
