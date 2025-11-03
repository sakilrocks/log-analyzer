# log-analyzer

A command line log analysis tool built in Java designed to quickly filter, analyze, and export specific log entries from large log files.  
It supports filtering by *keywords*, *regex* & *date ranges* and lets you export the filtered output for further use.

---

## Features

- Filter logs containing specific keywords or patterns  
- Search using *custom regex*    
- Filter logs by date (e.g., show logs since `2025-10-25`)  
- Export filtered results to another file  


---

## Project Structure

```
log-analyzer/
├── src/
│   └── main/
│       └── java/
│           └── dev/loganalyzer/cli/
│               ├── CLI.java
│               ├── LogParser.java
│               └── LogAnalyzer.java
├── logs/
└── README.md
```

---

## Usage

1. Compile:
```
javac -d out src/main/java/dev/loganalyzer/cli/*.java
```
this creates ``` out/dev/loganalyzer/cli/*.class ```

2. Run:
```
java -cp out dev.loganalyzer.cli.LogAnalyzer --file logs/abc.log --errors
```

---

## How It Works

1. The user specifies a log file via `--file`
2. The program reads all log lines
3. Based on arguments (`--errors`, `--regex`, `--since`) it filters the data
4. The filtered output is printed to the console or exported to another file
