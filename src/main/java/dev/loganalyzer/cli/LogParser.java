
package dev.loganalyzer.cli;

import java.io.IOException;
import java.nio.file.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.regex.*;



public class LogParser {

    private final List<String> lines;

    public LogParser(Path filePath) throws IOException {
        this.lines = Files.readAllLines(filePath);
    }

    public List<String> filterByKeyword(String keyword) {
        List<String> result = new ArrayList<>();
        for (String line : lines) {
            if (line.contains(keyword)) result.add(line);
        }
        return result;
    }

    public List<String> filterByRegex(String regex) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        for (String line : lines) {
            if (pattern.matcher(line).find()) result.add(line);
        }
        return result;
    }

    public List<String> filterByDate(String since) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        LocalDate sinceDate = LocalDate.parse(since, fmt);
        List<String> result = new ArrayList<>();
        Pattern datePattern = Pattern.compile("(\\d{4}-\\d{2}-\\d{2})");

        for (String line : lines) {
            Matcher m = datePattern.matcher(line);
            if (m.find()) {
                LocalDate logDate = LocalDate.parse(m.group(1), fmt);
                if (!logDate.isBefore(sinceDate)) result.add(line);
            }
        }
        return result;
    }

    public void export(List<String> lines, String outputPath) {
        try {
            Files.write(Paths.get(outputPath), lines);
            System.out.println("\nexported filtered logs to: " + outputPath);
        } catch (IOException e) {
            System.out.println("failed to export logs: " + e.getMessage());
        }
    }
    
}