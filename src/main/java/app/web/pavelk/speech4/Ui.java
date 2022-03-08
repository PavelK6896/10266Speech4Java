package app.web.pavelk.speech4;

import javax.swing.*;
import java.awt.*;

public class Ui {

    static JFrame jFrame = new JFrame();

    public static void create(Speech speech) {
        JPanel jPanel = new JPanel();

        jFrame.setBounds(500, 500, 200, 100);
        JButton jButtonSay = new JButton("Say");
        JButton jButtonStop = new JButton("Stop");

        jButtonSay.setSize(50, 50);
        jButtonStop.setSize(50, 50);
        jPanel.add(jButtonSay, Component.LEFT_ALIGNMENT);
        jPanel.add(jButtonStop, Component.RIGHT_ALIGNMENT);
        jFrame.add(jPanel);
        //JDialog JFrame
//        jFrame.setType(javax.swing.JFrame.Type.UTILITY);
//        jFrame.setUndecorated(true);
//        jFrame.setAlwaysOnTop(true);

        jFrame.setVisible(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setLocation((int) screenSize.getWidth() - 250, (int) screenSize.getHeight() - 250);
        jButtonSay.addActionListener(e -> {
            speech.stop();
            speech.say(ClipboardWorker.getText());
        });
        jButtonStop.addActionListener(e -> {
            speech.stop();
        });

    }

    static void updatePosition() {
        jFrame.setLocation((int) MouseInfo.getPointerInfo().getLocation().getX(),
                (int) MouseInfo.getPointerInfo().getLocation().getY());
    }

}
