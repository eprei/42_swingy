package ch._42lausanne.swingy.view.classes.gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow {

    private JFrame frame;
    private JPanel panel;
    private JLabel label;

    public MainWindow() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setLayout(new BorderLayout(5, 5));
        frame.setTitle("Game");
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setResizable(false);

        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
//        panel = new JPanel(new GridLayout(5,5,1,1));
        panel.setBackground(Color.PINK);

        for (int i = 0; i < 3; i++) {
            JButton button = new JButton("button_" + Integer.toString(i));
            button.setFocusable(false);
            panel.add(button);
        }

        label = new JLabel("Some text");
        label.setForeground(Color.WHITE);
        panel.add(label);

        JTextField textField = new JTextField(10);
        panel.add(textField);

//        Button button1 = new Button("Button");
//        panel.add(button1);
//
//        Button button2 = new Button("Button");
//        panel.add(button2);
//
//        Button button3 = new Button("SOUTH");
//        frame.add(button3, BorderLayout.SOUTH);
//
//        frame.add(new JButton("North"), BorderLayout.NORTH);
//        frame.add(new JButton("South"), BorderLayout.SOUTH);
//        frame.add(new JButton("East"), BorderLayout.EAST);
//        frame.add(new JButton("West"), BorderLayout.WEST);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
