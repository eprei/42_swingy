package ch._42lausanne.swingy.view.viewer;

import ch._42lausanne.swingy.controller.Controller;
import ch._42lausanne.swingy.model.game.Model;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public abstract class ViewerImpl implements Viewer {
    protected Controller controller;
    protected Model model;
    protected Scanner inputReader;

    public ViewerImpl() {
        this.inputReader = new Scanner(System.in);
    }

    @Override
    public void updateView() {
        handleActiveState();
    }

    private void handleActiveState() {
        switch (model.getPhase()) {
            case WELCOME -> welcomeView();
            case CREATE_HERO -> createHeroView();
            case SELECT_HERO -> selectHeroView();
            case MAP -> mapView();
            case FIGHT_OR_RUN -> fightOrRunView();
            case RUN_SUCCESSFUL -> runSuccessfulView();
            case RUN_FAILED -> runFailedView();
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
