package app.web.pavelk.speech4;

import app.web.pavelk.speech4.speech.Factory;
import app.web.pavelk.speech4.speech.Speech;

public class Main {
    public static void main(String[] args) {
        ClipboardWorker.run();
        Speech speech = Factory.create();
        Ui.create(speech);
    }
}
