package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, Object> map1 = readJson(filePath1);
        Map<String, Object> map2 = readJson(filePath2);

        Set<String> keys = new TreeSet<>(map1.keySet()); // Сортировка по алфавиту
        keys.addAll(map2.keySet());

        StringBuilder result = new StringBuilder("{\n");
        for (String key : keys) {
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                result.append("  - ").append(key).append(": ").append(map1.get(key)).append("\n");
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                result.append("  + ").append(key).append(": ").append(map2.get(key)).append("\n");
            } else if (!Objects.equals(map1.get(key), map2.get(key))) {
                result.append("  - ").append(key).append(": ").append(map1.get(key)).append("\n");
                result.append("  + ").append(key).append(": ").append(map2.get(key)).append("\n");
            } else {
                result.append("    ").append(key).append(": ").append(map1.get(key)).append("\n");
            }
        }
        result.append("}");
        return result.toString();
    }

    private static Map<String, Object> readJson(String filePath) throws Exception {
        String content = Files.readString(Path.of(filePath));
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, Map.class);
    }
}
