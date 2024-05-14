package com.demoblaze.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.Gson;
import java.io.FileReader;
public class JsonUtils {

    // Method to get nested value from JSON object using dot notation
    public static String getTestData(JsonObject json, String key) {
        String[] keys = key.split("\\."); // Split the key using dot notation
        JsonElement element = json; // Initialize the current element with the root JSON object
        
        // Traverse the JSON object using the keys
        for (String k : keys) {
            if (element.isJsonObject()) {
                JsonObject obj = element.getAsJsonObject();
                if (obj.has(k)) {
                    element = obj.get(k);
                } else {
                    return null; // Key not found
                }
            } else {
                return null; // Invalid JSON structure
            }
        }
        
        // Return the value if it's a primitive type, otherwise return null
        return element.isJsonPrimitive() ? element.getAsString() : null;
    }

    public static JsonObject parseJsonFile(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, JsonObject.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
