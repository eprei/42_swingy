package ch._42lausanne.swingy.view.classes.gui;

import javax.swing.*;

public class Test {
    private JFrame frame;
    private JPanel MainPanel;
    private JLabel etiqueta;

    public Test() {
        frame = new JFrame();
        frame.setTitle("Title");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.add(MainPanel);
        frame.setVisible(true);
    }
}
