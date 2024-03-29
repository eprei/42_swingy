package ch._42lausanne.swingy.view.classes.gui;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class Launcher {
    public static void main(String[] args) {
        // Run this program on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        try {
                            UIManager.setLookAndFeel(info.getClassName());
                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                                 UnsupportedLookAndFeelException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    }
                }
                new Test();
//                new MainWindow();
            }
        });
    }
}
