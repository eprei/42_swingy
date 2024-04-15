package ch._42lausanne.swingy.view;

import ch._42lausanne.swingy.controller.Controller;
import ch._42lausanne.swingy.model.characters.Hero;
import ch._42lausanne.swingy.model.game.Game;
import ch._42lausanne.swingy.model.game.ObjectTypeEnum;
import ch._42lausanne.swingy.view.validator.HeroNameUserInput;
import ch._42lausanne.swingy.view.viewer.UserInputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component("swingUi")
public class SwingUi implements ActionListener {
    private final UserInputValidator inputValidator;
    private final JFrame globalFrame;
    private final Controller controller;
    private Game game;
    private JPanel mainPanel;
    private JPanel backgroundPanel;
    private JPanel welcomePanel;
    private JButton selectCharacterButton;
    private JPanel mapPanel;
    private JButton btnCreate;
    private JButton btnPlay;
    private JLabel lblName;
    private JLabel lblType;
    private JTextField nameInput;
    private JComboBox cmbTypeSelection;
    private JPanel createCharacterPanel;
    private JPanel selectCharacterPanel;
    private JPanel characterStatsPanel;
    private JLabel lblCharName;
    private JLabel lblCharType;
    private JLabel lblCharLevel;
    private JLabel lblCharExp;
    private JLabel lblCharAtt;
    private JLabel lblCharDef;
    private JLabel lblCharHP;
    private JComboBox cmbCharacterList;
    private JLabel invalidInputTxt1;
    private JLabel invalidInputTxt2;


    @Autowired
    public SwingUi(UserInputValidator inputValidator, Controller controller, Game game, JFrame globalFrame) {
        this.inputValidator = inputValidator;
        this.controller = controller;
        this.game = game;
        this.globalFrame = globalFrame;
        setUIElements(globalFrame);
    }

    private void setUIElements(JFrame globalFrame) {
//        set global frame
        globalFrame.setContentPane(mainPanel);
        globalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        globalFrame.pack();
        globalFrame.setLocationRelativeTo(null);
        globalFrame.setResizable(false);
        globalFrame.setSize(1066, 600);
        globalFrame.setVisible(true);
//        add event manager to elements
        cmbCharacterList.addActionListener(this);
        nameInput.addActionListener(this);
        btnCreate.addActionListener(this);
    }

    public void enableWelcomeView() {
        welcomePanel.setVisible(true);
        btnPlay.setEnabled(!controller.getHeroes().isEmpty());

        controller.getHeroes().forEach(hero -> {
            cmbCharacterList.addItem(hero.getName());
        });

        for (ObjectTypeEnum heroType : ObjectTypeEnum.values()) {
            if (0 < heroType.ordinal() && heroType.ordinal() < 5) {
                cmbTypeSelection.addItem(heroType);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cmbCharacterList) {
            showStatsOfSelectedHero();
        } else if (e.getSource() == btnCreate || e.getSource() == nameInput) {
            boolean validInput = inputValidator.validateGuiInput(nameInput.getText(), HeroNameUserInput.class);
            invalidInputTxt1.setVisible(!validInput);
            invalidInputTxt2.setVisible(!validInput);
            if (validInput) {
                controller.createHero(nameInput.getText(), (ObjectTypeEnum) cmbTypeSelection.getSelectedItem());
//                TODO Continue here. index out of bound exception
            }
        }
    }

    private void showStatsOfSelectedHero() {
        int chosenHeroIndex = cmbCharacterList.getSelectedIndex();
        Hero chosenhero = controller.getHeroes().get(chosenHeroIndex);
        lblCharName.setText("name: " + chosenhero.getName());
        lblCharType.setText("type: " + chosenhero.getType());
        lblCharLevel.setText("level: " + chosenhero.getLevel());
        lblCharExp.setText("experience: " + chosenhero.getExperience());
        lblCharAtt.setText("attack: " + chosenhero.getStats().getAttack());
        lblCharDef.setText("defense: " + chosenhero.getStats().getDefense());
        lblCharHP.setText("hit points: " + chosenhero.getStats().getHitPoints());
    }
}
