
package dev.loganalyzer.cli;


import java.util.HashMap;
import java.util.Map;


public class CLI {

    public static Map<String, String> parseArgs(String[] args) {
        Map<String, String> params = new HashMap<>();

        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--")) {
                if (i + 1 < args.length && !args[i + 1].startsWith("--")) {
                    params.put(args[i], args[i + 1]);
                    i++;
                } else {
                    params.put(args[i], "true");
                }
            }
        }

        return params;
    }


    public static void printUsage() {
        System.out.println("""
        usage:
          java -jar loganalyzer.jar --file <path> [options]

        options:
          --errors              filter lines containing 'ERROR'
          --regex <pattern>     filter lines by custom regex
          --since <yyyy-mm-dd>  show logs from this date onward
          --export <output>     export filtered logs to file

        """);
    }

}