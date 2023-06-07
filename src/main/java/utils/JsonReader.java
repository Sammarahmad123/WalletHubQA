package utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonReader {
    private static final String JSON_FILE_PATH = "src/main/resources/testdata.json";

    public static String getProperty(String propertyName) {
        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            JsonElement element = JsonParser.parseReader(reader);
            JsonObject jsonObject = element.getAsJsonObject();
            return jsonObject.get(propertyName).getAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
