package com.example.fileFilterUtil;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String outputPath = ".";
        String prefix = "";
        boolean appendMode = false;
        boolean shortStats = false;
        boolean fullStats = false;
        List<String> filenames = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-o":
                    if (i + 1 < args.length) {
                        outputPath = args[++i];
                    } else {
                        System.err.println("Error: after option -o argument is required");
                        return;
                    }
                    break;

                case "-p":
                    if (i + 1 < args.length) {
                        prefix = args[++i];
                    } else {
                        System.err.println("Error: after option -p argument is required");
                        return;
                    }
                    break;

                case "-a":
                    appendMode = true;
                    break;

                case "-s":
                    shortStats = true;
                    break;

                case "-f":
                    fullStats = true;
                    break;

                default:
                    if (args[i].startsWith("-")) {
                        System.err.println("Error: unknown option: " + args[i]);
                        return;
                    }

                    System.out.println("Added file: " + args[i]);
                    filenames.add(args[i]);
            }
        }

        System.out.printf("""
                Params:
                Path for results: %s
                Prefix: %s
                Append mode: %s
                Short stats: %s
                Full stats: %s
                """,
                outputPath,
                prefix,
                appendMode ? "enabled" : "disabled",
                shortStats ? "enabled" : "disabled",
                fullStats ? "enabled" : "disabled"
        );

        FileProcessor processor = new FileProcessor(outputPath, prefix, appendMode, shortStats, fullStats);

        processor.processFiles(filenames);
    }
}

