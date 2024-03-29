package ch._42lausanne.swingy.view.interfaces;

import ch._42lausanne.swingy.controller.classes.ControllerImpl;

public interface GenericViewer {

    void setController(ControllerImpl controller);

    void updateView();

    void welcomeView();

    void mapView();

    void fightOrRunStageView();

    void runView();

    void fightView();

    void winBattleView();

    void looseBattleView();

    void winMapView();

    void winGameView();

    void loseGameView();

    void exitMapView();

    void exitGameView();

}
