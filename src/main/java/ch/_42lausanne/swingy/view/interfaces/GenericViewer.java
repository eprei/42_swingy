package ch._42lausanne.swingy.view.interfaces;

import ch._42lausanne.swingy.controller.classes.ControllerImpl;

public interface GenericViewer {

    void setController(ControllerImpl controller);

    void welcomeView();

    void createHeroView();

    void selectHeroView();

    void updateView();

    void mapView();

    void fightOrRunView();

    void runSuccessfulView();

    void runFailedView();

    void winBattleView();

    void looseBattleView();

    void artifactDroppedView();

    void winMapView();

    void winGameView();

    void exitMapView();

    void exitGameView();

}
