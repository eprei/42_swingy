package ch._42lausanne.swingy.controller;

import ch._42lausanne.swingy.model.characters.Hero;
import ch._42lausanne.swingy.model.game.Direction;
import ch._42lausanne.swingy.model.game.Model;
import ch._42lausanne.swingy.model.game.ObjectType;
import ch._42lausanne.swingy.view.console.ConsoleViewer;
import ch._42lausanne.swingy.view.gui.GuiViewer;

import java.util.List;

public interface Controller {
    void runApplication();

    void fightBattle(boolean wanted);

    Model getModel();

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

    void setActiveViewer(String typeOfViewerChosenByTheUser);

    void setConsoleViewer(ConsoleViewer consoleViewer);

    void setGuiViewer(GuiViewer guiViewer);

    List<Hero> getHeroes();
}
