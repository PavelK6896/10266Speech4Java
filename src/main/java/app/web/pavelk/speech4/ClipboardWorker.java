package app.web.pavelk.speech4;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
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

}
