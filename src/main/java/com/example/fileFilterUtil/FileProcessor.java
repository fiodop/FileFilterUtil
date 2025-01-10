package com.example.fileFilterUtil;

import java.io.*;
import java.util.List;

public class FileProcessor {
    private final String outputPath;
    private final String prefix;
    private final boolean appendMode;
    private final boolean shortStats;
    private final boolean fullStats;

    private int integerCount = 0;
    private int doubleCount = 0;
    private int stringCount = 0;

    private long integerSum = 0;
    private int minInteger = Integer.MAX_VALUE;
    private int maxInteger = Integer.MIN_VALUE;

    private double doubleSum = 0;
    private double minDouble = Double.MAX_VALUE;
    private double maxDouble = Double.MIN_VALUE;

    private int shortestString = Integer.MAX_VALUE;
    private int longestString = Integer.MIN_VALUE;

    public FileProcessor(String outputPath, String prefix, boolean appendMode, boolean shortStats, boolean fullStats) {
        this.outputPath = outputPath;
        this.prefix = prefix;
        this.appendMode = appendMode;
        this.shortStats = shortStats;
        this.fullStats = fullStats;
    }

    public void processFiles(List<String> filenames){
        for(String filename : filenames){
            File file = new File(filename);
            if(!file.exists()){
                System.out.println("File not found: " + filename);
            }

            try(BufferedReader reader = new BufferedReader(new FileReader(file))){
                long lineNumber = 1;
                String line;
                while ((line = reader.readLine()) != null){
                    processLine(line.trim(), filename,lineNumber);
                    lineNumber++;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        writeResults();
    }

    private void processLine(String line, String filename, long lineNumber){
        try {
            if (line.matches("-?\\d+")) {
                int value = Integer.parseInt(line);
                integerSum += value;
                integerCount++;
                minInteger = Math.min(minInteger, value);
                maxInteger = Math.max(maxInteger, value);
                writeToFile("integers.txt", line);
                return;
            }

            if (line.matches("-?\\d*\\.\\d+")) {
                double value = Double.parseDouble(line);
                doubleSum += value;
                doubleCount++;
                maxDouble = Math.max(maxDouble, value);
                minDouble = Math.min(minDouble, value);
                writeToFile("doubles.txt", line);
            } else {
                stringCount++;
                shortestString = Math.min(shortestString, line.length());
                longestString = Math.max(longestString, line.length());
                writeToFile("strings.txt", line);
            }
        } catch (NumberFormatException e) {
            System.err.printf("Error in file '%s' at line %d: %s%n", filename, lineNumber, line);
        }

    }

    private void writeToFile(String filename, String content){
        File file = new File(outputPath, prefix + filename);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file, appendMode))){
            writer.write(content);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + file.getAbsolutePath());
        }
    }

    private void writeResults() {
        if(shortStats){
            System.out.println("Processing complete. Short statistics:");
            if (integerCount > 0) {
                System.out.printf("Integers: %d%n", integerCount);
            }
            if (doubleCount > 0) {
                System.out.printf("Doubles: %d%n", doubleCount);
            }
            if (stringCount > 0) {
                System.out.printf("Strings: %d%n", stringCount);
            }
        }
        if(fullStats) {
            System.out.println("Processing complete. Full statistics:");
            if (integerCount > 0) {
                System.out.printf("Integers: %d, Min: %d, Max: %d, Sum: %d, Average: %.2f%n",
                        integerCount, minInteger, maxInteger, integerSum, (double) integerSum / integerCount);
            }
            if (doubleCount > 0) {
                System.out.printf("Doubles: %d, Min: %.2f, Max: %.2f, Sum: %.2f, Average: %.2f%n",
                        doubleCount, minDouble, maxDouble, doubleSum, doubleSum / doubleCount);
            }
            if (stringCount > 0) {
                System.out. printf("Strings: %d, Shortest: %d, Longest: %d%n",
                        stringCount, shortestString, longestString);
            }
        }
    }
}
