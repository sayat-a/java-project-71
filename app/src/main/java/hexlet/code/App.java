package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.Map;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "1.0",
        description = "Compares two configuration files and shows a difference."
)
public class App implements Runnable {
    @Parameters(index = "0", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", description = "path to second file")
    private String filepath2;

    @Option(
            names = { "-f", "--format" },
            paramLabel = "format",
            description = "output format [default: stylish]",
            defaultValue = "stylish"
    )
    private String format;

    public static void main(String[] args) {
        try {
            int exitCode = new CommandLine(new App()).execute(args);
            System.exit(exitCode);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
    @Override
    public void run() {
        try {
            Map<String, Object> data1 = FileUtils.readJsonFile(filepath1);
            Map<String, Object> data2 = FileUtils.readJsonFile(filepath2);
            System.out.println("File 1 data: " + data1);
            System.out.println("File 2 data: " + data2);
        } catch (Exception e) {
            throw new RuntimeException("Failed to process files: " + e.getMessage());
        }
    }
}