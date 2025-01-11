package com.example.fileFilterUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CommandLineOptions {
    private List<String> inputFiles;
    private String outputPath = "C:\\Users\\artem\\IdeaProjects\\RefactoringFileFilterUtil\\src\\main\\resources";
    private String prefix;
    private boolean appendMode;
    private boolean shortStats;
    private boolean fullStats;

    public List<String> getInputFiles() {
        return inputFiles;
    }

    public boolean isFullStats() {
        return fullStats;
    }

    public boolean isShortStats() {
        return shortStats;
    }

    public boolean isAppendMode() {
        return appendMode;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public static CommandLineOptions parse(String[] args) {
        CommandLineOptions options = new CommandLineOptions();
        options.inputFiles = new ArrayList<>();
        options.prefix = "";
        options.appendMode = false;
        options.shortStats = false;
        options.fullStats = false;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-o":
                    if (i + 1 < args.length) {
                        options.outputPath = args[++i];
                    } else {
                        System.err.println("Error: new output path is empty");
                    }
                    break;
                case "-p":
                    if (i + 1 < args.length) {
                        String prefix = args[++i];
                        if (!containsInvalidSymbols(prefix)) {
                            options.prefix = prefix;
                        } else {
                            System.err.println("Error: prefix contains invalid symbols");
                        }
                    } else {
                        System.err.println("Error: prefix argument is empty, files will be saved in path: " + options.outputPath);
                    }
                    break;
                case "-a":
                    options.appendMode = true;
                    break;
                case "-s":
                    options.shortStats = true;
                    break;
                case "-f":
                    options.fullStats = true;
                    break;
                default:
                    if (args[i].startsWith("-")) {
                        System.err.println("Error: unrecognized option: " + args[i]);
                    } else {
                        File file = new File(args[i]);
                        if (file.exists() && file.isFile()) {
                            options.inputFiles.add(args[i]);
                        } else {
                            System.err.println("Error: file does not exist or is not a valid file: " + args[i]);
                        }
                    }
                    break;
            }
        }

        if (options.inputFiles.isEmpty()) {
            System.err.println("Error: no input files specified");
        }

        return options;
    }


    /**
     *
     * @param prefix
     * @return true if prefix contains invalid symbols
     * @return false if prefix doesn't contain invalid symbols
     */
    private static boolean containsInvalidSymbols(String prefix) {
        char[] invalidSymbols = {'\\', '/', ':', '*', '?', '"', '<', '>', '|', '}', '{', '[', ']', '+', '^', ' '};
        for (char c : invalidSymbols) {
            if (prefix.indexOf(c) != -1) {
                return true;
            }
        }
        return false;
    }
}
