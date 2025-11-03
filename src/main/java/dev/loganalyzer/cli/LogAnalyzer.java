
package dev.loganalyzer.cli;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;


public class LogAnalyzer {

    public static void main(String[] args) {
        Map<String, String> params = CLI.parseArgs(args);

        if (!params.containsKey("--file")) {
            CLI.printUsage();
            return;
        }



        Path filePath = Paths.get(params.get("--file"));

        if (!Files.exists(filePath)) {
            System.out.println("file not found: " + filePath);
            return;
        }


        try {
            LogParser parser = new LogParser(filePath);
            List<String> filtered = parser.filterByKeyword(""); 

            if (params.containsKey("--errors")) {
                filtered = parser.filterByKeyword("ERROR");
            }

            if (params.containsKey("--regex")) {
                filtered = parser.filterByRegex(params.get("--regex"));
            }

            if (params.containsKey("--since")) {
                filtered = parser.filterByDate(params.get("--since"));
            }

            System.out.println("\nfound " + filtered.size() + " matching lines.\n");
            filtered.forEach(System.out::println);

            if (params.containsKey("--export")) {
                parser.export(filtered, params.get("--export"));
            }

        } catch (IOException e) {
            System.out.println("error reading file: " + e.getMessage());
        }
    }

}