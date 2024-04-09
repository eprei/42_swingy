package ch._42lausanne.swingy.controller;

import ch._42lausanne.swingy.model.artifacts.Artifact;
import ch._42lausanne.swingy.model.characters.Hero;
import ch._42lausanne.swingy.model.game.Direction;
import ch._42lausanne.swingy.model.game.ObjectType;
import ch._42lausanne.swingy.view.console.ConsoleViewer;
import ch._42lausanne.swingy.view.gui.GuiViewer;

import java.util.List;

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

    void setActiveViewer(String selectedViewer);

    void setConsoleViewer(ConsoleViewer consoleViewer);

    void setGuiViewer(GuiViewer guiViewer);

    List<Hero> getHeroes();

    void printMap();

    String getVillain();

    Artifact getDroppedArtifact();
}
