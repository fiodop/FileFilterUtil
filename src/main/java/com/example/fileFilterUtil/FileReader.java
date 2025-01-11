package com.example.fileFilterUtil;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    /**
     *
     * @param filePath file's path for reading
     * @return list of lines in file
     */
    public List<String> readFile(String filePath){
        List<String> lines = new ArrayList<String>();

        try(BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))){
            String line;
            while ((line = reader.readLine()) != null){
                lines.add(line.trim());
            }
        } catch (Exception e) {
            System.err.println("Error reading file: " + filePath);
        }
        return lines;
    }
}
