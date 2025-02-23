package hexlet.code;

import org.junit.jupiter.api.Test;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    private static final String FILE1 = Paths.get("src", "test", "resources", "file1.json").toString();
    private static final String FILE2 = Paths.get("src", "test", "resources", "file2.json").toString();

    @Test
    void testGenerate() throws Exception {
        String expected = """
            {
              - follow: false
                host: hexlet.io
              - proxy: 123.234.53.22
              - timeout: 50
              + timeout: 20
              + verbose: true
            }""";

        String actual = Differ.generate(FILE1, FILE2);
        assertEquals(expected, actual);
    }

    @Test
    void testSameFiles() throws Exception {
        String expected = """
            {
                follow: false
                host: hexlet.io
                proxy: 123.234.53.22
                timeout: 50
            }""";

        String actual = Differ.generate(FILE1, FILE1);
        assertEquals(expected, actual);
    }

    @Test
    void testEmptyFile() throws Exception {
        String emptyFile = Paths.get("src", "test", "resources", "empty.json").toString();
        String expected = """
            {
              - follow: false
              - host: hexlet.io
              - proxy: 123.234.53.22
              - timeout: 50
            }""";

        String actual = Differ.generate(FILE1, emptyFile);
        assertEquals(expected, actual);
    }
}
