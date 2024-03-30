package ch._42lausanne.swingy.controller.interfaces;

import ch._42lausanne.swingy.model.characters.classes.Hero;
import ch._42lausanne.swingy.model.game.enums.Direction;

public interface Controller {
    void runApplication();

    void createHero();

    void selectHero(Hero heroSelected);

    void handleMovement(Direction direction);

    void fightBattle();

    void runFromBattle();

    void continueTheAdventure();

    void searchForDroppedArtifacts();

    void keepArtifact();
}
