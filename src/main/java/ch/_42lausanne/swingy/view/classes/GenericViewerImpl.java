package ch._42lausanne.swingy.view.classes;

import ch._42lausanne.swingy.controller.classes.ControllerImpl;
import ch._42lausanne.swingy.model.game.classes.Model;
import ch._42lausanne.swingy.view.interfaces.GenericViewer;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public abstract class GenericViewerImpl implements GenericViewer {
    protected ControllerImpl controller;
    protected Model model;
    protected Scanner inputReader;

    public GenericViewerImpl() {
        this.inputReader = new Scanner(System.in);
    }

    @Override
    public void setController(ControllerImpl controller) {
        this.controller = controller;
        this.model = controller.model();
    }

    @Override
    public void updateView() {
        handleActiveState();
    }

    private void handleActiveState() {
        switch (model.getActiveStage()) {
            case WELCOME_STAGE -> welcomeView();
            case MAP_STAGE -> mapView();
            case FIGHT_OR_RUN_STAGE -> fightOrRunStageView();
            case RUN_STAGE -> runView();
            case FIGHT_STAGE -> fightView();
            case WIN_BATTLE_STAGE -> winBattleView();
            case LOOSE_BATTLE_STAGE -> looseBattleView();
            case WIN_MAP_STAGE -> winMapView();
            case WIN_GAME_STAGE -> winGameView();
            case LOSE_GAME_STAGE -> loseGameView();
            case EXIT_MAP_STAGE -> exitMapView();
            case EXIT_GAME_STAGE -> exitGameView();
        }
    }
}
