package ch._42lausanne.swingy.controller.interfaces;

import ch._42lausanne.swingy.model.game.enums.Direction;
import ch._42lausanne.swingy.model.game.enums.ObjectType;

public interface Controller {
    void runApplication();

    void fightBattle(boolean wanted);

    void startHeroCreation();

    void createHero(String heroName, ObjectType heroType);

    void handleMovement(Direction direction);

    void selectHero();

    void tryToRunFromBattle();

    void continueTheAdventure();

    void searchForDroppedArtifacts();

    void keepArtifact();

    void goToWelcomeWindow();

    void selectHeroByIndex(String heroIndex);

    void goToNextMap();
}
