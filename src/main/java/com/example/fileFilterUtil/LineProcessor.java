package com.example.fileFilterUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LineProcessor {
    private final List<String> integers = new ArrayList<>();
    private final List<String> doubles = new ArrayList<>();
    private final List<String> strings = new ArrayList<>();

    private final Statistic statistic = new Statistic();

    /**
     * processing lines
     * @param line line from file
     */
    public void process(String line) {
        if (line.matches("-?\\d+")) {
            long value = Long.parseLong(line);
            integers.add(line);
            System.out.println("Processing line: " + line);
            statistic.updateIntegerStats(value);
        } else if (line.matches("-?\\d*\\.\\d+")) {
            double value = Double.parseDouble(line);
            doubles.add(line);
            System.out.println("Processing line: " + line);
            statistic.updateDoubleStats(value);
        } else {
            strings.add(line);
            System.out.println("Processing line: " + line);
            statistic.updateStringStats(line);
        }
    }


    public boolean hasData() {
        return !integers.isEmpty() || !doubles.isEmpty() || !strings.isEmpty();
    }


    public List<List<String>> getProcessedData() {
        List<List<String>> allData = new ArrayList<>();
        allData.add(integers);
        allData.add(doubles);
        allData.add(strings);
        return allData;
    }






}
