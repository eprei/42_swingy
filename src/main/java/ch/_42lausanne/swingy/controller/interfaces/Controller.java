package ch._42lausanne.swingy.controller.interfaces;

import ch._42lausanne.swingy.model.game.enums.Direction;

public interface Controller {
    void runApplication();

    void createHero();


    void handleMovement(Direction direction);

    void fightBattle();

    void selectHero();

    void tryToRunFromBattle();

    void continueTheAdventure();

    void searchForDroppedArtifacts();

    void keepArtifact();

    void goToWelcomeWindow();

    void selectHeroIndex(String heroIndex);
}
