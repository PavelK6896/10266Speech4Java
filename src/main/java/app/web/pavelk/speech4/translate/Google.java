package app.web.pavelk.speech4.translate;

import app.web.pavelk.speech4.util.Property;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Google {

    public static String translate(String text) {
        return translate("en", "ru", text);
    }

    public static String translate(String langFrom, String langTo, String text) {
        try {
            String urlStr = Property.getProperty("google.url") +
                    "?q=" + URLEncoder.encode(text, "UTF-8") +
                    "&target=" + langTo +
                    "&source=" + langFrom;
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            return new String(con.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException("error translate");
        }
    }
}
