package app.web.pavelk.speech4.util;

import org.json.JSONObject;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Property {
    public static String getProperty(String name) {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("property.json");
        if (resource == null) {
            throw new IllegalArgumentException("not property");
        }
        try {
            Path path = Paths.get(resource.toURI());
            byte[] bytes = Files.readAllBytes(path);
            String jsonString = new String(bytes);
            JSONObject obj = new JSONObject(jsonString);
            return obj.get(name).toString();
        } catch (Exception e) {
            throw new IllegalArgumentException("error property");
        }
    }
}
