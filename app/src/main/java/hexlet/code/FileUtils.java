package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class FileUtils {

    public static Map<String, Object> readJsonFile(String filePath) throws Exception {
        Path path = Path.of(filePath).toAbsolutePath();
        String content = Files.readString(path);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, new TypeReference<Map<String, Object>>() {});
    }
}
