package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Utility class for reading test data from JSON files.
 */
public class TestDataReader {

    /**
     * Reads a JSON file and maps it into a Map<String, String>.
     * Suitable for flat JSON objects where all values are strings.
     *
     * Example:
     * {
     *   "username": "testuser",
     *   "password": "testpass"
     * }
     *
     * @param path Path to the JSON file.
     * @return Map containing key-value pairs from the JSON file.
     */
    public static Map<String, String> readJson(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(path), Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Could not read test data from " + path, e);
        }
    }

    /**
     * Reads a JSON file and maps it into a Map<String, Object>.
     * Useful for more complex JSON structures like nested objects or arrays.
     *
     * Example:
     * {
     *   "dropdownItems": ["Tennis", "Football"],
     *   "selectedSports": ["Tennis", "Football"]
     * }
     *
     * @param path Path to the JSON file.
     * @return Map with string keys and object values (can be cast as needed).
     */
    public static Map<String, Object> readJsonArray(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(path), Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Could not read test data from " + path, e);
        }
    }
}
