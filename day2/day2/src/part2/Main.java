package part2;

import helper.FileParser;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        var reports = FileParser.loadFile("input.txt");
        AtomicInteger safeCount = new AtomicInteger();

        reports.forEach(report -> {
            boolean isSafe = true;
            int tolerance = 0;
            Boolean isAscending = null;
            for (int i = 0; i < report.length - 1; i++) {
                if (i == 0) {
                    isAscending = report[i] < report[i + 1];
                } else {
                    if ((isAscending && (report[i] > report[i + 1]))
                            || (!isAscending && (report[i] < report[i + 1]))
                            || Objects.equals(report[i], report[i + 1])) {
                        if (tolerance < 1) {
                            tolerance++;
                        } else {
                            isSafe = false;
                            break;
                        }
                    }
                }

                var distance = Math.abs(report[i] - report[i + 1]);
                if (distance > 3) {
                    if (tolerance < 1) {
                        tolerance++;
                    } else {
                        isSafe = false;
                        break;

                    }
                }
            }
            if (isSafe) {
                safeCount.getAndIncrement();
            }
        });
        System.out.println(safeCount);
    }
}