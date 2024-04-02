package ch._42lausanne.swingy.view.viewer;

import ch._42lausanne.swingy.controller.Controller;

public interface Viewer {

    void setController(Controller controller);

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
