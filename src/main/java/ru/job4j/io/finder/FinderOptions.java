package ru.job4j.io.finder;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import ru.job4j.io.finder.filters.FiltersType;

public class FinderOptions {

    @Option(name = "-d", metaVar = "<dir>", usage = "specifies a path to search")
    private String dir;

    @Option(name = "-n", metaVar = "<pattern>", usage = "specifies a pattern to search")
    private String pattern;

    @Option(name = "-t", usage = "specifies a search type")
    private FiltersType filterType;

    @Option(name = "-o", metaVar = "<file name>", usage = "specifies a file to output")
    private String outFile;

    public boolean parseArgs(String[] args) {
        boolean isSuccessParsing = true;
        CmdLineParser parser = new CmdLineParser(this);
        try {
            if (args.length != 4) {
                throw new IllegalArgumentException("Invalid number of arguments");
            }
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            isSuccessParsing = false;
            System.out.println(e.getMessage());
            parser.printUsage(System.out);
            System.out.println("Use example: java -jar find.jar -d=c:/ -n=*.txt -t=mask -o=log.txt");
        }
        return isSuccessParsing;
    }

    public String getDir() {
        return dir;
    }

    public String getPattern() {
        return pattern;
    }

    public FiltersType getFilterType() {
        return filterType;
    }

    public String getOutFile() {
        return outFile;
    }
}
