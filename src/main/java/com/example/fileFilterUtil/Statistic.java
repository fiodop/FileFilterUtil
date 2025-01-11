package com.example.fileFilterUtil;

public class Statistic {
    private static long longCounter = 0;
    private static long longSum = 0;
    private static long longMin = Long.MAX_VALUE;
    private static long longMax = Long.MIN_VALUE;

    private static long doubleCounter = 0;
    private static double doubleSum = 0;
    private static double doubleMin = Double.MAX_VALUE;
    private static double doubleMax = Double.MIN_VALUE;

    private static long stringCounter = 0;
    private static long shortestString = Long.MAX_VALUE;
    private static long longestString = Long.MIN_VALUE;

    public void updateIntegerStats(long value) {
        longCounter++;
        longSum += value;
        longMin = Math.min(value, longMin);
        longMax = Math.max(value, longMax);
        System.out.println("updateIntegerStats value: " + value);
    }

    public void updateDoubleStats(double value) {
        doubleCounter++;
        doubleSum += value;
        doubleMin = Math.min(value, doubleMin);
        doubleMax = Math.max(value, doubleMax);
        System.out.println("updateDoubleStats value: " + value);
    }

    public void updateStringStats(String value) {
        stringCounter++;
        shortestString = Math.min(shortestString, value.length());
        longestString = Math.max(longestString, value.length());
        System.out.println("updateStringStats value: " + value);
    }

    public String getStatistics(boolean fullStats, boolean shortStats) {
        StringBuilder stats = new StringBuilder();

        double longAvg = longCounter > 0 ? (double) longSum / longCounter : 0;
        double doubleAvg = doubleCounter > 0 ? doubleSum / doubleCounter : 0;

        if (fullStats) {
            stats.append("Full stats:\n");
            stats.append(String.format("Integers: cnt: %d, min: %d, max: %d, sum: %d, avg: %.2f\n",
                    longCounter,
                    longMin,
                    longMax,
                    longSum,
                    longAvg));
            stats.append(String.format("Doubles: cnt: %d, min: %.2f, max: %.2f, sum: %.2f, avg: %.2f\n",
                    doubleCounter,
                    doubleMin,
                    doubleMax,
                    doubleSum,
                    doubleAvg));
            stats.append(String.format("Strings: cnt: %d, shortest: %d, longest: %d\n",
                    stringCounter,
                    shortestString,
                    longestString));
        }

        if (shortStats) {
            stats.append("Short stats:\n");
            stats.append(String.format("Integers: %d\n", longCounter));
            stats.append(String.format("Doubles: %d\n", doubleCounter));
            stats.append(String.format("Strings: %d\n", stringCounter));
        }

        return stats.toString();
    }
}
