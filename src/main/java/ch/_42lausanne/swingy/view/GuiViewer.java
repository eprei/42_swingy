package ch._42lausanne.swingy.view;

import ch._42lausanne.swingy.controller.Controller;
import ch._42lausanne.swingy.model.characters.Hero;
import ch._42lausanne.swingy.model.game.Game;
import ch._42lausanne.swingy.model.game.ObjectTypeEnum;
import ch._42lausanne.swingy.view.validator.HeroNameUserInput;
import ch._42lausanne.swingy.view.viewer.BaseViewer;
import ch._42lausanne.swingy.view.viewer.UserInputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component("guiViewer")
public class GuiViewer extends BaseViewer implements ActionListener {
    private final UserInputValidator inputValidator;
    private JFrame globalFrame;
    private JPanel mainPanel;
    private JPanel backgroundPanel;
    private JPanel welcomePanel;
    private JPanel playPanel;
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
    private JComboBox cmbHeroList;
    private JLabel invalidInputTxt1;
    private JLabel invalidInputTxt2;
    private JPanel mapContainer;
    private JLabel lblVillainFound;
    private JButton btnFight;
    private JButton btnRun;
    private JTextPane lblFight;
    private JTextPane lblRun;
    private JLabel lblWhatToDo;
    private JPanel fightPanel;
    private JPanel runPanel;
    private JPanel fightOrRunPanel;
    private JPanel dialogPanel;
    private JPanel moveInstructionsPanel;
    private JPanel itemPanel;
    private MapPanel mapPanel;


    @Autowired
    public GuiViewer(UserInputValidator inputValidator, Controller controller, Game game) {
        this.inputValidator = inputValidator;
        this.controller = controller;
        this.game = game;
        this.controller.setGuiViewer(this);
        SwingUtilities.invokeLater(this::initializeSwingComponentsViaEventDispatchThread);
    }

    private void initializeSwingComponentsViaEventDispatchThread() {
//        set global frame
        globalFrame = new JFrame("swingy");
        globalFrame.setContentPane(mainPanel);
        globalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        globalFrame.pack();
        globalFrame.setLocationRelativeTo(null);
        globalFrame.setResizable(false);
        globalFrame.setSize(1066, 600);
        globalFrame.setVisible(true);
//        add custom Map Panel
        mapPanel = new MapPanel(mapContainer.getSize());
        mapContainer.add(mapPanel, BorderLayout.CENTER);
//        add event manager to elements
        cmbHeroList.addActionListener(this);
        nameInput.addActionListener(this);
        btnCreate.addActionListener(this);
        btnPlay.addActionListener(this);
        for (ObjectTypeEnum heroType : ObjectTypeEnum.values()) {
            if (0 < heroType.ordinal() && heroType.ordinal() < 5) {
                cmbTypeSelection.addItem(heroType);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cmbHeroList) {
            showStatsOfSelectedHero();
        } else if (e.getSource() == btnCreate || e.getSource() == nameInput) {
            createHero();
        } else if (e.getSource() == btnPlay) {
            controller.selectHeroByIndex(Integer.toString(cmbHeroList.getSelectedIndex()));
        }
    }

    private void createHero() {
        boolean validInput = inputValidator.validateGuiInput(nameInput.getText(), HeroNameUserInput.class);
        invalidInputTxt1.setVisible(!validInput);
        invalidInputTxt2.setVisible(!validInput);
        if (validInput) {
            controller.createHero(nameInput.getText(), (ObjectTypeEnum) cmbTypeSelection.getSelectedItem());
        }
    }

    private void showStatsOfSelectedHero() {
        if (cmbHeroList.getItemCount() != 0) {
            int chosenHeroIndex = cmbHeroList.getSelectedIndex();
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

    @Override
    public void welcomeView() {
        welcomePanel.setVisible(true);
        btnPlay.setEnabled(!controller.getHeroes().isEmpty());

        cmbHeroList.removeAllItems();
        for (Hero hero : controller.getHeroes()) {
            cmbHeroList.addItem(hero.getName());
        }
//        controller.getHeroes().forEach(hero -> {
//            cmbHeroList.addItem(hero.getName());
//        });
    }

    @Override
    public void createHeroView() {

    }

    @Override
    public void selectHeroView() {

    }

    @Override
    public void mapView() {
        welcomePanel.setVisible(false);
        playPanel.setVisible(true);
        mapPanel.setMap(controller.getMap());
        mapPanel.repaint();
    }

    @Override
    public void fightOrRunView() {
        moveInstructionsPanel.setVisible(false);
        itemPanel.setVisible(false);
        fightOrRunPanel.setVisible(true);
    }

    @Override
    public void runSuccessfulView() {

    }

    @Override
    public void runFailedView() {

    }

    @Override
    public void winBattleView() {

    }

    @Override
    public void looseBattleView() {

    }

    @Override
    public void artifactDroppedView() {
        moveInstructionsPanel.setVisible(false);
        fightOrRunPanel.setVisible(false);
        itemPanel.setVisible(true);
    }

    @Override
    public void winMapView() {

    }

    @Override
    public void winGameView() {

    }

}
