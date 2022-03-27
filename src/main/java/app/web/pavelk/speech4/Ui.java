package app.web.pavelk.speech4;

import app.web.pavelk.speech4.speech.Speech;
import app.web.pavelk.speech4.translate.Google;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CompletableFuture;

public class Ui {

    public static final int SIZE_MOUSE = 350;
    static JFrame jFrame = new JFrame();
    private static int x;
    private static int y;
    private static boolean translate = false;

    public static void create(Speech speech) {
        JPanel jPanel = new JPanel();

        jFrame.setBounds(500, 500, 175, 75);

        if (translate) {
            JButton jButtonTranslate = new JButton("Translate");
            jButtonTranslate.setSize(50, 50);
            jPanel.add(jButtonTranslate, Component.RIGHT_ALIGNMENT);
            jButtonTranslate.addActionListener(e -> {
                String translate = Google.translate(ClipboardWorker.getText());
                speech.stop();
                speech.say(translate);
            });
        } else {
            //JDialog JFrame
            jFrame.setType(javax.swing.JFrame.Type.UTILITY);
            jFrame.setUndecorated(true);
            jFrame.setAlwaysOnTop(true);
        }

        JButton jButtonSay = new JButton("Say");
        JButton jButtonStop = new JButton("Stop");
        jPanel.setLayout(new GridLayout(1, 2, 0, 0));
        jPanel.add(jButtonSay);
        jPanel.add(jButtonStop);
        jPanel.add(jButtonSay);
        jPanel.add(jButtonStop);
        jFrame.add(jPanel);

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        changeVision();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setLocation((int) screenSize.getWidth() - 250, (int) screenSize.getHeight() - 250);
        jButtonSay.addActionListener(e -> {
            speech.stop();
            speech.say(ClipboardWorker.text2);
        });
        jButtonStop.addActionListener(e -> {
            speech.stop();
            jFrame.setVisible(false);
        });

    }

    private static void changeVision() {
        CompletableFuture.runAsync(() -> {
//            KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(event -> {
//                System.out.println(event.getKeyCode());
//                return false;
//            });
            while (true) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int xCurrent = (int) MouseInfo.getPointerInfo().getLocation().getX();
                int yCurrent = (int) MouseInfo.getPointerInfo().getLocation().getY();

                if (x + SIZE_MOUSE < xCurrent) {
                    jFrame.setVisible(false);
                }

                if (x - SIZE_MOUSE > xCurrent) {
                    jFrame.setVisible(false);
                }

                if (y + SIZE_MOUSE < yCurrent) {
                    jFrame.setVisible(false);
                }

                if (y - SIZE_MOUSE > yCurrent) {
                    jFrame.setVisible(false);
                }

            }
        });
    }

    static void updatePosition() {
        x = (int) MouseInfo.getPointerInfo().getLocation().getX();
        y = (int) MouseInfo.getPointerInfo().getLocation().getY();
        jFrame.setLocation(x, y);
        jFrame.setVisible(true);
    }

}
