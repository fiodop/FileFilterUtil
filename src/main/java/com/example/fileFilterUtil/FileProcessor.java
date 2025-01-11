package com.example.fileFilterUtil;


import java.util.List;

public class FileProcessor {
    private final FileReader fileReader;
    private final FileWriter fileWriter;
    private final LineProcessor lineProcessor;
    private final CommandLineOptions options;
    private final Statistic statistic;

    public FileProcessor(FileReader fileReader, FileWriter fileWriter, LineProcessor lineProcessor, CommandLineOptions options, Statistic statistic) {
        this.fileReader = fileReader;
        this.fileWriter = fileWriter;
        this.lineProcessor = lineProcessor;
        this.options = options;
        this.statistic = statistic;
    }

    public List<List<String>> processFiles(List<String> filePaths){
        for(String filePath : filePaths){
            try{
                List<String> lines = fileReader.readFile(filePath);
                for(String line : lines){
                    lineProcessor.process(line);
                }
            }catch (Exception e){
                System.err.println("error reading file: " + filePath);
            }
        }
        return lineProcessor.getProcessedData();
    }

    public void writeData(List<List<String>> processedData){
        int cnt = 0;
        for(List<String> data : processedData){
            if (!data.isEmpty()){
                switch (cnt){
                    case 0:
                        System.out.println("Writing file: " + options.getPrefix() + "integers.txt");
                        fileWriter.write(options.getPrefix() + "integers.txt", data);
                        cnt++;
                        break;
                    case 1:
                        System.out.println("Writing file: " + options.getPrefix() + "doubles.txt");
                        fileWriter.write(options.getPrefix() + "doubles.txt", data);
                        cnt++;
                        break;
                    case 2:
                        System.out.println("Writing file: " + options.getPrefix() + "strings.txt");
                        fileWriter.write(options.getPrefix() + "strings.txt", data);
                        cnt++;
                        break;
                }
            } else {
                cnt++;
            }
        }
    }

    public void getResults(){
        System.out.println(statistic.getStatistics(options.isFullStats(), options.isShortStats()));
    }
}

