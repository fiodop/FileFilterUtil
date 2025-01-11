package com.example.fileFilterUtil;



import java.util.List;

public class Main {
    public static void main(String[] args) {
        CommandLineOptions options = CommandLineOptions.parse(args);
        FileReader fileReader = new FileReader();
        FileWriter fileWriter = new FileWriter(options.getOutputPath(), options.isAppendMode());
        LineProcessor lineProcessor = new LineProcessor();
        Statistic statistic = new Statistic();
        FileProcessor fileProcessor = new FileProcessor(fileReader, fileWriter, lineProcessor, options, statistic);

        List<List<String>> processedData = fileProcessor.processFiles(options.getInputFiles());
        fileProcessor.writeData(processedData);
        fileProcessor.getResults();
    }
}

