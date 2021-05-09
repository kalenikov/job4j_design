package ru.job4j.io.finder;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//-d - директория, в которой начинать поиск.
//-n - имя файла, маска, либо регулярное выражение.
//-t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
//-o - результат записать в файл.
public class FinderOptions {

    @Option(name = "-d", metaVar = "<dir>", usage = "specifies a path to search")
    private String dir;

    @Option(name = "-n", metaVar = "<pattern>", usage = "specifies a pattern to search")
    private String pattern;

    @Option(name = "-t", usage = "specifies a search type")
    private SearchType searchType;

    @Option(name = "-o", metaVar = "<file name>", usage = "specifies a file to output")
    private String outFile;

    public static void main(String[] args) {
        new FinderOptions().doMain(args);
    }

    private void doMain(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            if (args.length != 4) {
                throw new CmdLineException(parser, "Invalid number of arguments");
            }
            System.out.println(Arrays.toString(args));
            System.out.println(outFile);
            System.out.println(searchType);
        } catch (CmdLineException e) {
            System.out.println(e.getMessage());
            parser.printUsage(System.out);
            System.out.println("Use example: java -jar find.jar -d=c:/ -n=*.txt -t=mask -o=log.txt");
        }
    }

}
