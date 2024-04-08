package ch._42lausanne.swingy.view.viewer;

public interface Viewer {
    String CONSOLE_VIEW = "console";
    String GUI_VIEW = "gui";

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
