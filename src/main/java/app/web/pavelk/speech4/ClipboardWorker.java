package app.web.pavelk.speech4;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

public class ClipboardWorker {
    public static AtomicReference<String> atomicReference = new AtomicReference<>();
    public static AtomicReference<Boolean> booleanAtomicReference = new AtomicReference<>();

    public static void run() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.addFlavorListener(e -> {
            try {
                if (Boolean.TRUE.equals(booleanAtomicReference.get())) {
                    booleanAtomicReference.set(false);
                    return;
                }
//                Ui.updatePosition();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public static String getText() {
        String result = null;
        try {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable contents = clipboard.getContents(new Object());
            result = String.valueOf(contents.getTransferData(DataFlavor.stringFlavor));
            atomicReference.set(result);
            System.out.println(result);
            clipboard.setContents(contents, null);
            booleanAtomicReference.set(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static String text2 = "";

    public static void run2() {
        CompletableFuture.runAsync(() -> {
            while (true) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable contents = clipboard.getContents(new Object());
                try {
                    String text = String.valueOf(contents.getTransferData(DataFlavor.stringFlavor));
                    if (!text.equals(text2)) {
                        Ui.updatePosition();
                        text2 = text;
                    }
                } catch (UnsupportedFlavorException | IOException e) {
                    e.printStackTrace();
                }
                clipboard.setContents(contents, null);
            }
        });
    }


}
