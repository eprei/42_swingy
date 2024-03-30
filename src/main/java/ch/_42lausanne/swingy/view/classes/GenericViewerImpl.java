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
        switch (model.getPhase()) {
            case WELCOME -> welcomeView();
            case MAP -> mapView();
            case FIGHT_OR_RUN -> fightOrRunStageView();
            case RUN -> runView();
            case FIGHT -> fightView();
            case WIN_BATTLE -> winBattleView();
            case LOOSE_BATTLE -> looseBattleView();
            case ARTIFACT_DROPPED -> artifactDroppedView();
            case WIN_MAP -> winMapView();
            case WIN_GAME -> winGameView();
            case EXIT_MAP -> exitMapView();
            case EXIT_GAME -> exitGameView();
        }
    }
}
