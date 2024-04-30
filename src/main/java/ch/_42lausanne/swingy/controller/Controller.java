package ch._42lausanne.swingy.controller;

import ch._42lausanne.swingy.model.artifacts.Artifact;
import ch._42lausanne.swingy.model.characters.Hero;
import ch._42lausanne.swingy.model.game.Direction;
import ch._42lausanne.swingy.model.game.Map;
import ch._42lausanne.swingy.model.game.ObjectTypeEnum;
import ch._42lausanne.swingy.view.GuiViewer;
import ch._42lausanne.swingy.view.console.ConsoleViewer;

import java.util.List;

public interface Controller {
    void fightBattle(boolean wanted);

    void startHeroCreation();

    void createHero(String heroName, ObjectTypeEnum heroType);

    void handleMovement(Direction direction);

    void selectHero();

    Hero getActiveHero();

    void tryToRunFromBattle();

    void continueTheAdventure();

    void searchForDroppedArtifacts();

    void keepArtifact();

    void goToWelcomeWindow();

    void selectHeroByIndex(String heroIndex);

    void goToNextMap();

    void runApplication(String selectedViewer);

    void setConsoleViewer(ConsoleViewer consoleViewer);

    void setGuiViewer(GuiViewer guiViewer);

    List<Hero> getHeroes();

    void printMap();

    String getVillain();

    Artifact getDroppedArtifact();

    Map getMap();

    void switchActiveViewer();
}
