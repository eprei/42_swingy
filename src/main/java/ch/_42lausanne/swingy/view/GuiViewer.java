package ch._42lausanne.swingy.view;

import ch._42lausanne.swingy.controller.Controller;
import ch._42lausanne.swingy.model.artifacts.Artifact;
import ch._42lausanne.swingy.model.characters.Hero;
import ch._42lausanne.swingy.model.game.Direction;
import ch._42lausanne.swingy.model.game.Game;
import ch._42lausanne.swingy.model.game.ObjectTypeEnum;
import ch._42lausanne.swingy.view.console.UserMessages;
import ch._42lausanne.swingy.view.validator.HeroNameUserInput;
import ch._42lausanne.swingy.view.viewer.BaseViewer;
import ch._42lausanne.swingy.view.viewer.UserInputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@Component("guiViewer")
public class GuiViewer extends BaseViewer implements ActionListener, KeyListener {
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
    private JPanel artifactPanel;
    private JPanel singleMessagePanel;
    private JLabel singleMessageLabel;
    private JButton btnOkWinBattle;
    private JButton btnGoToWelcome;
    private JButton btnLeaveIt;
    private JButton btnKeepIt;
    private JLabel artifactType;
    private JLabel artifactAttack;
    private JLabel artifactDefense;
    private JLabel artifactHitPoints;
    private JButton btnSuccessfullyRun;
    private JButton btnStartUnwantedBattle;
    private JButton btnOkGoNextMap;
    private JButton btnGoToWelcomeWindow;
    private JLabel lblLvlUp1;
    private JLabel lblLvlUp2;
    private MapPanel mapPanel;
    private boolean keyboardEnabled = true;


    @Autowired
    public GuiViewer(UserInputValidator inputValidator, Controller controller, Game game) {
        this.inputValidator = inputValidator;
        this.controller = controller;
        this.game = game;
        this.controller.setGuiViewer(this);
        // initialize swing components using the Event Dispatch Thread to avoid concurrency problems
        SwingUtilities.invokeLater(this::initializeSwingComponents);
    }

    private void initializeSwingComponents() {
        // set global frame
        globalFrame = new JFrame("swingy");
        globalFrame.setContentPane(mainPanel);
        globalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        globalFrame.pack();
        globalFrame.setLocationRelativeTo(null);
        globalFrame.setResizable(false);
        globalFrame.setSize(1066, 600);
        globalFrame.setVisible(true);
        // add custom Map Panel
        mapPanel = new MapPanel(mapContainer.getSize());
        mapContainer.add(mapPanel, BorderLayout.CENTER);
        addActionListeners();
        for (ObjectTypeEnum heroType : ObjectTypeEnum.values()) {
            if (0 < heroType.ordinal() && heroType.ordinal() < 5) {
                cmbTypeSelection.addItem(heroType);
            }
        }
    }

    private void addActionListeners() {
        // add event manager to elements
        cmbHeroList.addActionListener(this);
        nameInput.addActionListener(this);
        btnCreate.addActionListener(this);
        btnPlay.addActionListener(this);
        playPanel.addKeyListener(this);
        btnFight.addActionListener(this);
        btnRun.addActionListener(this);
        btnOkWinBattle.addActionListener(this);
        btnGoToWelcome.addActionListener(this);
        btnKeepIt.addActionListener(this);
        btnLeaveIt.addActionListener(this);
        btnOkWinBattle.addActionListener(this);
        btnSuccessfullyRun.addActionListener(this);
        btnStartUnwantedBattle.addActionListener(this);
        btnOkGoNextMap.addActionListener(this);
        btnGoToWelcomeWindow.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cmbHeroList) {
            showStatsOfSelectedHero();
        } else if (e.getSource() == btnCreate || e.getSource() == nameInput) {
            createHero();
        } else if (e.getSource() == btnPlay) {
            controller.selectHeroByIndex(Integer.toString(cmbHeroList.getSelectedIndex()));
        } else if (e.getSource() == btnFight) {
            controller.fightBattle(true);
        } else if (e.getSource() == btnRun) {
            controller.tryToRunFromBattle();
        } else if (e.getSource() == btnOkWinBattle) {
            singleMessagePanel.setVisible(false);
            lblLvlUp1.setVisible(false);
            lblLvlUp2.setVisible(false);
            controller.searchForDroppedArtifacts();
        } else if (e.getSource() == btnGoToWelcome) {
            controller.goToWelcomeWindow();
        } else if (e.getSource() == btnKeepIt) {
            controller.keepArtifact();
        } else if (e.getSource() == btnLeaveIt || e.getSource() == btnOkWinBattle || e.getSource() == btnSuccessfullyRun) {
            controller.continueTheAdventure();
        } else if (e.getSource() == btnStartUnwantedBattle) {
            controller.fightBattle(false);
        } else if (e.getSource() == btnOkGoNextMap) {
            controller.goToNextMap();
        } else if (e.getSource() == btnGoToWelcomeWindow) {
            controller.goToWelcomeWindow();
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
        singleMessagePanel.setVisible(false);
        welcomePanel.setVisible(true);
        btnPlay.setEnabled(!controller.getHeroes().isEmpty());

        // TODO: fix bug when multiple characters has the same name
        cmbHeroList.removeAllItems();
        for (Hero hero : controller.getHeroes()) {
            cmbHeroList.addItem(hero.getName());
        }
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
        moveInstructionsPanel.setVisible(true);
        fightOrRunPanel.setVisible(false);
        artifactPanel.setVisible(false);
        singleMessageLabel.setVisible(false);
        btnSuccessfullyRun.setVisible(false);
        btnGoToWelcome.setVisible(false);
        btnOkWinBattle.setVisible(false);
        btnOkGoNextMap.setVisible(false);
        btnGoToWelcomeWindow.setVisible(false);
        playPanel.setVisible(true);
        playPanel.setFocusable(true);
        playPanel.requestFocusInWindow();
        mapPanel.setMap(controller.getMap());
        mapPanel.repaint();
        enableKeyboard();
    }

    @Override
    public void fightOrRunView() {
        disableKeyboard();
        moveInstructionsPanel.setVisible(false);
        fightOrRunPanel.setVisible(true);
    }

    @Override
    public void runSuccessfulView() {
        fightOrRunPanel.setVisible(false);
        singleMessageLabel.setText(UserMessages.getRUN_SUCCESSFUL());
        singleMessageLabel.setVisible(true);
        btnStartUnwantedBattle.setVisible(false);
        btnSuccessfullyRun.setVisible(true);
        singleMessagePanel.setVisible(true);
    }

    @Override
    public void runFailedView() {
        fightOrRunPanel.setVisible(false);
        singleMessageLabel.setVisible(true);
        singleMessageLabel.setText(UserMessages.getRUN_FAILED());
        btnSuccessfullyRun.setVisible(false);
        btnStartUnwantedBattle.setVisible(true);
        singleMessagePanel.setVisible(true);
    }

    @Override
    public void winBattleView() {
        moveInstructionsPanel.setVisible(false);
        btnStartUnwantedBattle.setVisible(false);
        fightOrRunPanel.setVisible(false);
        singleMessageLabel.setVisible(true);
        singleMessageLabel.setText(UserMessages.getYOU_WIN_THE_BATTLE());
        if (controller.getActiveHero().isLeveledUp()) {
            lblLvlUp1.setText(UserMessages.getLevelUp(controller.getActiveHero())[0]);
            lblLvlUp2.setText(UserMessages.getLevelUp(controller.getActiveHero())[1]);
            lblLvlUp1.setVisible(true);
            lblLvlUp2.setVisible(true);
        }
        btnOkWinBattle.setVisible(true);
        singleMessagePanel.setVisible(true);
    }

    @Override
    public void looseBattleView() {
        fightOrRunPanel.setVisible(false);
        btnStartUnwantedBattle.setVisible(false);
        singleMessageLabel.setText(UserMessages.getYOU_LOSE_THE_BATTLE());
        singleMessageLabel.setVisible(true);
        btnGoToWelcome.setVisible(true);
        singleMessagePanel.setVisible(true);
    }

    @Override
    public void artifactDroppedView() {
        disableKeyboard();
        Artifact artifact = controller.getDroppedArtifact();
        artifactType.setText("Type: " + artifact.getType());
        artifactAttack.setText("Attack: " + artifact.getStats().getAttack());
        artifactDefense.setText("Defense: " + artifact.getStats().getDefense());
        artifactHitPoints.setText("HitPoints: " + artifact.getStats().getHitPoints());

        moveInstructionsPanel.setVisible(false);
        fightOrRunPanel.setVisible(false);
        artifactPanel.setVisible(true);
    }

    @Override
    public void winMapView() {
        disableKeyboard();
        moveInstructionsPanel.setVisible(false);
        fightOrRunPanel.setVisible(false);
        singleMessageLabel.setVisible(true);
        btnStartUnwantedBattle.setVisible(false);
        btnSuccessfullyRun.setVisible(false);
        btnOkWinBattle.setVisible(false);
        singleMessagePanel.setVisible(true);
        if (controller.getMap().maximumLevelReached()) {
            singleMessageLabel.setText(UserMessages.getYOU_WIN_THE_GAME());
            btnGoToWelcomeWindow.setVisible(true);
            btnOkGoNextMap.setVisible(false);
        } else {
            singleMessageLabel.setText(UserMessages.getYOU_WIN_THE_MAP());
            btnGoToWelcomeWindow.setVisible(false);
            btnOkGoNextMap.setVisible(true);
        }
    }

    @Override
    public void winGameView() {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (keyboardEnabled) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP, KeyEvent.VK_W -> controller.handleMovement(Direction.NORTH);
                case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> controller.handleMovement(Direction.EAST);
                case KeyEvent.VK_DOWN, KeyEvent.VK_S -> controller.handleMovement(Direction.SOUTH);
                case KeyEvent.VK_LEFT, KeyEvent.VK_A -> controller.handleMovement(Direction.WEST);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void enableKeyboard() {
        keyboardEnabled = true;
    }

    private void disableKeyboard() {
        keyboardEnabled = false;
    }

}
