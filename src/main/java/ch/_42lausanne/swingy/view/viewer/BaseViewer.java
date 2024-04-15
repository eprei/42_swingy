package ch._42lausanne.swingy.view.viewer;

import ch._42lausanne.swingy.controller.Controller;
import ch._42lausanne.swingy.model.game.Game;

public abstract class BaseViewer implements Viewer {
    protected Controller controller;
    protected Game game;

    public BaseViewer() {
    }

    @Override
    public void updateView() {
        switch (game.getPhase()) {
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
        }
    }

}
