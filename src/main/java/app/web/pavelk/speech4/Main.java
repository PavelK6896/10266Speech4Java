package app.web.pavelk.speech4;

public class Main {
    public static void main(String[] args) {
        ClipboardWorker.run();
        Speech speech = Factory.create();
        Ui.create(speech);
    }
}
