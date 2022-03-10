package app.web.pavelk.speech4.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PropertyTest {

    @Test
    void getPropertyTrue() {
        Assertions.assertEquals("https://script/exec", Property.getProperty("google.url"));
    }

    @Test
    void getPropertyFalse() {
        IllegalArgumentException url = assertThrows(IllegalArgumentException.class, () -> {
            Property.getProperty("url");
        });
        Assertions.assertEquals("error property", url.getMessage());
    }

}