package com.example.fileFilterUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.util.List;

public class FileWriter {

    private final String outputPath;
    private final boolean appendMode;

    public FileWriter(String outputPath, boolean appendMode) {
        this.outputPath = outputPath;
        this.appendMode = appendMode;
    }

    public void write(String fileName, List<String> data) {
        File file = new File(outputPath, fileName);
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(file, appendMode))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        }catch (Exception e){
            System.err.println("Error writing file: " + fileName);
            e.printStackTrace();
    }
}
}
