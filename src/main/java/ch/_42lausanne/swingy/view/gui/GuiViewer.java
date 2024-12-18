package ch._42lausanne.swingy.view.gui;

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
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

enum ViewType {
    WELCOME, MAP, FIGHT_OR_RUN, RUN_SUCCESSFUL, RUN_FAILED, WIN_BATTLE, LOOSE_BATTLE, ARTIFACT_DROPPED, WIN_MAP,
}

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
    private JButton btnSwitchToConsole1;
    private JComponent[] welcomeComponents;
    private JButton btnSwitchToConsole2;
    private JComponent[] mapComponents;
    private JComponent[] fightOrRunCompoments;
    private JComponent[] runSuccessfulComponents;
    private JComponent[] runFailedComponents;
    private JComponent[] winBattleComponents;
    private JComponent[] looseBattleComponents;
    private JComponent[] artifactDroppedComponents;
    private JComponent[] winMapComponents;

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

    @PostConstruct
    private void initializeArrays() {
        welcomeComponents = new JComponent[]{mainPanel, backgroundPanel, welcomePanel, createCharacterPanel, btnCreate, selectCharacterPanel, btnPlay, btnSwitchToConsole1};
        mapComponents = new JComponent[]{moveInstructionsPanel, mapContainer, dialogPanel, playPanel, btnSwitchToConsole2};
        fightOrRunCompoments = new JComponent[]{playPanel, fightOrRunPanel, fightPanel, runPanel, btnFight, lblFight, btnRun, lblRun, btnSwitchToConsole2};
        runSuccessfulComponents = new JComponent[]{singleMessageLabel, btnSuccessfullyRun, singleMessagePanel, btnSwitchToConsole2};
        runFailedComponents = new JComponent[]{singleMessageLabel, btnStartUnwantedBattle, singleMessagePanel, btnSwitchToConsole2};
        winBattleComponents = new JComponent[]{singleMessageLabel, btnOkWinBattle, singleMessagePanel, btnSwitchToConsole2};
        looseBattleComponents = new JComponent[]{singleMessageLabel, btnGoToWelcome, singleMessagePanel, btnSwitchToConsole2};
        artifactDroppedComponents = new JComponent[]{artifactPanel, btnKeepIt, btnLeaveIt, btnSwitchToConsole2};
        winMapComponents = new JComponent[]{singleMessageLabel, singleMessagePanel, btnSwitchToConsole2};
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
        btnSwitchToConsole1.addActionListener(this);
        btnSwitchToConsole2.addActionListener(this);
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
            controller.goToNextMap();
        } else if (e.getSource() == btnSwitchToConsole1 || e.getSource() == btnSwitchToConsole2) {
            globalFrame.setVisible(false);
            controller.switchActiveViewer();
        }
    }

    private void hideGraphicElements() {
        JComponent[] componentsToHide = {
                btnGoToWelcomeWindow, btnOkGoNextMap, btnPlay, btnStartUnwantedBattle,
                btnLeaveIt, btnKeepIt, btnFight, btnRun, btnCreate, welcomePanel,
                moveInstructionsPanel, fightOrRunPanel, artifactPanel, singleMessageLabel,
                btnSuccessfullyRun, btnGoToWelcome, btnOkWinBattle, lblLvlUp1, lblLvlUp2,
                btnSwitchToConsole1, btnSwitchToConsole2
        };

        for (JComponent component : componentsToHide) {
            component.setVisible(false);
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
        showElementsOf(ViewType.WELCOME);
        btnPlay.setEnabled(!controller.getHeroes().isEmpty());
        cmbHeroList.removeAllItems();
        for (Hero hero : controller.getHeroes()) {
            cmbHeroList.addItem(hero.getId() + ": " + hero.getName());
        }
    }

    private void showElementsOf(ViewType viewType) {
        JComponent[] componentsToShow = null;

        hideGraphicElements();

        switch (viewType) {
            case WELCOME -> componentsToShow = welcomeComponents;
            case MAP -> componentsToShow = mapComponents;
            case FIGHT_OR_RUN -> componentsToShow = fightOrRunCompoments;
            case RUN_SUCCESSFUL -> componentsToShow = runSuccessfulComponents;
            case RUN_FAILED -> componentsToShow = runFailedComponents;
            case WIN_BATTLE -> componentsToShow = winBattleComponents;
            case LOOSE_BATTLE -> componentsToShow = looseBattleComponents;
            case ARTIFACT_DROPPED -> componentsToShow = artifactDroppedComponents;
            case WIN_MAP -> componentsToShow = winMapComponents;
        }

        for (JComponent component : componentsToShow) {
            component.setVisible(true);
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
        enableKeyboard();
        showElementsOf(ViewType.MAP);
        playPanel.requestFocusInWindow();
        mapPanel.setMap(controller.getMap());
        mapPanel.repaint();
        enableKeyboard();
    }

    @Override
    public void fightOrRunView() {
        disableKeyboard();
        showElementsOf(ViewType.FIGHT_OR_RUN);
        mapPanel.setMap(controller.getMap());
    }

    @Override
    public void runSuccessfulView() {
        disableKeyboard();
        showElementsOf(ViewType.RUN_SUCCESSFUL);
        singleMessageLabel.setText(UserMessages.RUN_SUCCESSFUL);
    }

    @Override
    public void runFailedView() {
        disableKeyboard();
        showElementsOf(ViewType.RUN_FAILED);
        singleMessageLabel.setText(UserMessages.RUN_FAILED);
    }

    @Override
    public void winBattleView() {
        disableKeyboard();
        showElementsOf(ViewType.WIN_BATTLE);
        singleMessageLabel.setText(UserMessages.getYouWinTheBattle());
        if (controller.getActiveHero().isLeveledUp()) {
            lblLvlUp1.setText(UserMessages.getLevelUp(controller.getActiveHero())[0]);
            lblLvlUp2.setText(UserMessages.getLevelUp(controller.getActiveHero())[1]);
            lblLvlUp1.setVisible(true);
            lblLvlUp2.setVisible(true);
        }
    }

    @Override
    public void looseBattleView() {
        disableKeyboard();
        showElementsOf(ViewType.LOOSE_BATTLE);
        singleMessageLabel.setText(UserMessages.getYouLoseTheBattle());
    }

    @Override
    public void artifactDroppedView() {
        disableKeyboard();
        showElementsOf(ViewType.ARTIFACT_DROPPED);
        Artifact artifact = controller.getDroppedArtifact();
        artifactType.setText("Type: " + artifact.getType());
        artifactAttack.setText("Attack: " + artifact.getStats().getAttack());
        artifactDefense.setText("Defense: " + artifact.getStats().getDefense());
        artifactHitPoints.setText("HitPoints: " + artifact.getStats().getHitPoints());
    }

    @Override
    public void winMapView() {
        disableKeyboard();
        showElementsOf(ViewType.WIN_MAP);
        if (controller.getMap().maximumLevelReached()) {
            singleMessageLabel.setText(UserMessages.YOU_WIN_THE_GAME);
            btnGoToWelcomeWindow.setVisible(true);
        } else {
            singleMessageLabel.setText(UserMessages.getYouWinTheMap());
            btnOkGoNextMap.setVisible(true);
        }
    }

    @Override
    public void winGameView() {
        // implement multilevel management for the GUI here if necessary as there is in the console
        controller.goToWelcomeWindow();
    }

    @Override
    public void becomeActiveViewer() {
        globalFrame.setVisible(true);
        updateView();
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new CardLayout(0, 0));
        backgroundPanel.setBackground(new Color(-10257269));
        backgroundPanel.setEnabled(true);
        mainPanel.add(backgroundPanel, BorderLayout.CENTER);
        welcomePanel = new JPanel();
        welcomePanel.setLayout(new GridLayoutManager(4, 5, new Insets(0, 0, 0, 0), -1, -1));
        welcomePanel.setBackground(new Color(-7790813));
        welcomePanel.setEnabled(true);
        backgroundPanel.add(welcomePanel, "Card1");
        createCharacterPanel = new JPanel();
        createCharacterPanel.setLayout(new GridLayoutManager(11, 5, new Insets(0, 0, 0, 0), -1, -1));
        welcomePanel.add(createCharacterPanel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Create a hero");
        createCharacterPanel.add(label1, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(103, 20), null, 0, false));
        cmbTypeSelection = new JComboBox();
        createCharacterPanel.add(cmbTypeSelection, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(103, 38), null, 0, false));
        btnCreate = new JButton();
        btnCreate.setEnabled(true);
        btnCreate.setText("Create");
        createCharacterPanel.add(btnCreate, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(103, 38), null, 0, false));
        final Spacer spacer1 = new Spacer();
        createCharacterPanel.add(spacer1, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 30), null, 0, false));
        lblType = new JLabel();
        lblType.setText("Type");
        createCharacterPanel.add(lblType, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameInput = new JTextField();
        createCharacterPanel.add(nameInput, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lblName = new JLabel();
        lblName.setText("Name");
        createCharacterPanel.add(lblName, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        createCharacterPanel.add(spacer2, new GridConstraints(10, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 30), null, 0, false));
        final Spacer spacer3 = new Spacer();
        createCharacterPanel.add(spacer3, new GridConstraints(8, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 30), null, 0, false));
        final Spacer spacer4 = new Spacer();
        createCharacterPanel.add(spacer4, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 30), null, 0, false));
        final Spacer spacer5 = new Spacer();
        createCharacterPanel.add(spacer5, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(30, -1), null, 0, false));
        final Spacer spacer6 = new Spacer();
        createCharacterPanel.add(spacer6, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), new Dimension(-1, 30), null, 0, false));
        final Spacer spacer7 = new Spacer();
        createCharacterPanel.add(spacer7, new GridConstraints(7, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(30, -1), null, 0, false));
        invalidInputTxt1 = new JLabel();
        invalidInputTxt1.setBackground(new Color(-14540254));
        invalidInputTxt1.setEnabled(true);
        invalidInputTxt1.setFocusable(true);
        invalidInputTxt1.setForeground(new Color(-7790813));
        invalidInputTxt1.setOpaque(false);
        invalidInputTxt1.setText("Invalid Name: Please enter a name between");
        invalidInputTxt1.setVisible(false);
        createCharacterPanel.add(invalidInputTxt1, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, 5), null, 0, false));
        invalidInputTxt2 = new JLabel();
        invalidInputTxt2.setBackground(new Color(-14540254));
        invalidInputTxt2.setEnabled(true);
        invalidInputTxt2.setFocusable(true);
        invalidInputTxt2.setForeground(new Color(-7790813));
        invalidInputTxt2.setOpaque(false);
        invalidInputTxt2.setText("1 and 20 alphanumeric characters.");
        invalidInputTxt2.setVisible(false);
        createCharacterPanel.add(invalidInputTxt2, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(50, 5), null, 0, false));
        selectCharacterPanel = new JPanel();
        selectCharacterPanel.setLayout(new GridLayoutManager(21, 3, new Insets(0, 0, 0, 0), -1, -1));
        welcomePanel.add(selectCharacterPanel, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        selectCharacterPanel.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final Spacer spacer8 = new Spacer();
        selectCharacterPanel.add(spacer8, new GridConstraints(20, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), null, null, 0, false));
        btnPlay = new JButton();
        btnPlay.setEnabled(false);
        btnPlay.setText("Play");
        selectCharacterPanel.add(btnPlay, new GridConstraints(19, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Select a hero");
        selectCharacterPanel.add(label2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cmbHeroList = new JComboBox();
        selectCharacterPanel.add(cmbHeroList, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        selectCharacterPanel.add(spacer9, new GridConstraints(18, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), null, null, 0, false));
        final Spacer spacer10 = new Spacer();
        selectCharacterPanel.add(spacer10, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), null, null, 0, false));
        final Spacer spacer11 = new Spacer();
        selectCharacterPanel.add(spacer11, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 30), new Dimension(-1, 30), null, 0, false));
        final Spacer spacer12 = new Spacer();
        selectCharacterPanel.add(spacer12, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(75, -1), new Dimension(80, -1), null, 0, false));
        final Spacer spacer13 = new Spacer();
        selectCharacterPanel.add(spacer13, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(30, -1), new Dimension(75, -1), null, 0, false));
        characterStatsPanel = new JPanel();
        characterStatsPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        selectCharacterPanel.add(characterStatsPanel, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lblCharName = new JLabel();
        lblCharName.setText("name:");
        characterStatsPanel.add(lblCharName, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer14 = new Spacer();
        selectCharacterPanel.add(spacer14, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 10), null, null, 0, false));
        lblCharType = new JLabel();
        lblCharType.setText("type:");
        selectCharacterPanel.add(lblCharType, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblCharLevel = new JLabel();
        lblCharLevel.setText("level:");
        selectCharacterPanel.add(lblCharLevel, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblCharExp = new JLabel();
        lblCharExp.setText("experience:");
        selectCharacterPanel.add(lblCharExp, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblCharAtt = new JLabel();
        lblCharAtt.setText("attack:");
        selectCharacterPanel.add(lblCharAtt, new GridConstraints(13, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblCharDef = new JLabel();
        lblCharDef.setText("defense:");
        selectCharacterPanel.add(lblCharDef, new GridConstraints(15, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblCharHP = new JLabel();
        lblCharHP.setText("hit points:");
        selectCharacterPanel.add(lblCharHP, new GridConstraints(17, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer15 = new Spacer();
        selectCharacterPanel.add(spacer15, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 10), null, null, 0, false));
        final Spacer spacer16 = new Spacer();
        selectCharacterPanel.add(spacer16, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 10), null, null, 0, false));
        final Spacer spacer17 = new Spacer();
        selectCharacterPanel.add(spacer17, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 10), null, null, 0, false));
        final Spacer spacer18 = new Spacer();
        selectCharacterPanel.add(spacer18, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 10), null, null, 0, false));
        final Spacer spacer19 = new Spacer();
        selectCharacterPanel.add(spacer19, new GridConstraints(14, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 10), null, null, 0, false));
        final Spacer spacer20 = new Spacer();
        selectCharacterPanel.add(spacer20, new GridConstraints(16, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 10), null, null, 0, false));
        final Spacer spacer21 = new Spacer();
        welcomePanel.add(spacer21, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(5, -1), null, 0, false));
        final Spacer spacer22 = new Spacer();
        welcomePanel.add(spacer22, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(15, -1), null, 0, false));
        final Spacer spacer23 = new Spacer();
        welcomePanel.add(spacer23, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(15, -1), null, 0, false));
        final Spacer spacer24 = new Spacer();
        welcomePanel.add(spacer24, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 15), null, 0, false));
        final Spacer spacer25 = new Spacer();
        welcomePanel.add(spacer25, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 15), null, 0, false));
        btnSwitchToConsole1 = new JButton();
        btnSwitchToConsole1.setText("Switch to console viewer");
        welcomePanel.add(btnSwitchToConsole1, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        playPanel = new JPanel();
        playPanel.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        playPanel.setBackground(new Color(-9984674));
        playPanel.setEnabled(true);
        backgroundPanel.add(playPanel, "Card2");
        mapContainer = new JPanel();
        mapContainer.setLayout(new BorderLayout(0, 0));
        mapContainer.setForeground(new Color(-11004394));
        mapContainer.setVisible(true);
        playPanel.add(mapContainer, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(816, 816), null, 5, false));
        dialogPanel = new JPanel();
        dialogPanel.setLayout(new CardLayout(0, 0));
        dialogPanel.setVisible(true);
        playPanel.add(dialogPanel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(200, -1), null, 2, false));
        moveInstructionsPanel = new JPanel();
        moveInstructionsPanel.setLayout(new GridLayoutManager(6, 1, new Insets(80, 50, 150, 60), -1, -1));
        dialogPanel.add(moveInstructionsPanel, "Card2");
        final Spacer spacer26 = new Spacer();
        moveInstructionsPanel.add(spacer26, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 5), new Dimension(-1, 5), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Use arrows to move");
        moveInstructionsPanel.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("⬆");
        moveInstructionsPanel.add(label4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("⬅  ⬇  ➡");
        moveInstructionsPanel.add(label5, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("or use WASD");
        moveInstructionsPanel.add(label6, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer27 = new Spacer();
        moveInstructionsPanel.add(spacer27, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 80), new Dimension(-1, 5), null, 0, false));
        fightOrRunPanel = new JPanel();
        fightOrRunPanel.setLayout(new GridLayoutManager(7, 1, new Insets(80, 50, 80, 60), -1, -1));
        fightOrRunPanel.setVisible(false);
        dialogPanel.add(fightOrRunPanel, "Card1");
        lblVillainFound = new JLabel();
        lblVillainFound.setText("You have encountered a VILLAIN \uD83D\uDC7A");
        fightOrRunPanel.add(lblVillainFound, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblWhatToDo = new JLabel();
        lblWhatToDo.setText("What do you want to do?");
        fightOrRunPanel.add(lblWhatToDo, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer28 = new Spacer();
        fightOrRunPanel.add(spacer28, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 15), null, 0, false));
        fightPanel = new JPanel();
        fightPanel.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        fightOrRunPanel.add(fightPanel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        btnFight = new JButton();
        btnFight.setText("⚔ Fight");
        fightPanel.add(btnFight, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblFight = new JTextPane();
        lblFight.setOpaque(false);
        lblFight.setText("You will engage the enemy in battle. Your chances of success depend on your strength and skills.");
        fightPanel.add(lblFight, new GridConstraints(1, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(180, 50), null, 0, false));
        final Spacer spacer29 = new Spacer();
        fightOrRunPanel.add(spacer29, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 15), null, 0, false));
        runPanel = new JPanel();
        runPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        fightOrRunPanel.add(runPanel, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        btnRun = new JButton();
        btnRun.setText("\uD83C\uDFC3 Run");
        runPanel.add(btnRun, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblRun = new JTextPane();
        lblRun.setOpaque(false);
        lblRun.setText("You will try to escape from the enemy but if you don't succeed you will have to fight him and he will attack you first.");
        runPanel.add(lblRun, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(180, 50), null, 0, false));
        final Spacer spacer30 = new Spacer();
        fightOrRunPanel.add(spacer30, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 15), null, 0, false));
        artifactPanel = new JPanel();
        artifactPanel.setLayout(new GridLayoutManager(13, 1, new Insets(80, 50, 80, 60), -1, -1));
        artifactPanel.setVisible(false);
        dialogPanel.add(artifactPanel, "Card3");
        final JLabel label7 = new JLabel();
        label7.setText("You have found an artifact");
        artifactPanel.add(label7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("What do you want to do?");
        artifactPanel.add(label8, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer31 = new Spacer();
        artifactPanel.add(spacer31, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 15), null, 0, false));
        final Spacer spacer32 = new Spacer();
        artifactPanel.add(spacer32, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 15), null, 0, false));
        final Spacer spacer33 = new Spacer();
        artifactPanel.add(spacer33, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 15), null, 0, false));
        btnLeaveIt = new JButton();
        btnLeaveIt.setText("Leave it");
        artifactPanel.add(btnLeaveIt, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnKeepIt = new JButton();
        btnKeepIt.setText("Keep it");
        artifactPanel.add(btnKeepIt, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        artifactType = new JLabel();
        artifactType.setText("");
        artifactPanel.add(artifactType, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        artifactAttack = new JLabel();
        artifactAttack.setText("");
        artifactPanel.add(artifactAttack, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        artifactDefense = new JLabel();
        artifactDefense.setText("");
        artifactPanel.add(artifactDefense, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        artifactHitPoints = new JLabel();
        artifactHitPoints.setText("");
        artifactPanel.add(artifactHitPoints, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer34 = new Spacer();
        artifactPanel.add(spacer34, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer35 = new Spacer();
        artifactPanel.add(spacer35, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        singleMessagePanel = new JPanel();
        singleMessagePanel.setLayout(new GridLayoutManager(13, 3, new Insets(0, 0, 0, 0), -1, -1));
        dialogPanel.add(singleMessagePanel, "Card4");
        singleMessageLabel = new JLabel();
        singleMessageLabel.setText("");
        singleMessagePanel.add(singleMessageLabel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnOkWinBattle = new JButton();
        btnOkWinBattle.setText("Ok");
        singleMessagePanel.add(btnOkWinBattle, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnGoToWelcome = new JButton();
        btnGoToWelcome.setText("Go to welcome");
        singleMessagePanel.add(btnGoToWelcome, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnSuccessfullyRun = new JButton();
        btnSuccessfullyRun.setText("Ok");
        singleMessagePanel.add(btnSuccessfullyRun, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnStartUnwantedBattle = new JButton();
        btnStartUnwantedBattle.setText("Start battle");
        singleMessagePanel.add(btnStartUnwantedBattle, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnOkGoNextMap = new JButton();
        btnOkGoNextMap.setText("Ok go to the next map");
        singleMessagePanel.add(btnOkGoNextMap, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnGoToWelcomeWindow = new JButton();
        btnGoToWelcomeWindow.setText("Go to welcome window");
        singleMessagePanel.add(btnGoToWelcomeWindow, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer36 = new Spacer();
        singleMessagePanel.add(spacer36, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer37 = new Spacer();
        singleMessagePanel.add(spacer37, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        lblLvlUp1 = new JLabel();
        lblLvlUp1.setText("");
        singleMessagePanel.add(lblLvlUp1, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblLvlUp2 = new JLabel();
        lblLvlUp2.setText("");
        singleMessagePanel.add(lblLvlUp2, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer38 = new Spacer();
        singleMessagePanel.add(spacer38, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer39 = new Spacer();
        singleMessagePanel.add(spacer39, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer40 = new Spacer();
        singleMessagePanel.add(spacer40, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer41 = new Spacer();
        singleMessagePanel.add(spacer41, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        btnSwitchToConsole2 = new JButton();
        btnSwitchToConsole2.setText("Switch to console viewer");
        playPanel.add(btnSwitchToConsole2, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
