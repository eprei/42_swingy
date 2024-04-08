package ch._42lausanne.swingy.controller;

import ch._42lausanne.swingy.model.artifacts.Artifact;
import ch._42lausanne.swingy.model.characters.Hero;
import ch._42lausanne.swingy.model.game.Direction;
import ch._42lausanne.swingy.model.game.Game;
import ch._42lausanne.swingy.model.game.Model;
import ch._42lausanne.swingy.model.game.ObjectType;
import ch._42lausanne.swingy.view.console.ConsoleViewer;
import ch._42lausanne.swingy.view.gui.GuiViewer;
import ch._42lausanne.swingy.view.viewer.Viewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("controllerImpl")
public class ControllerImpl implements Controller {
    private final Model model;
    private final Game game;
    private Viewer consoleViewer;
    private Viewer guiViewer;
    private Viewer activeViewer;

    @Autowired
    public ControllerImpl(Model model, Game game) {
        this.model = model;
        this.game = game;
    }

    @Override
    public void runApplication() {
        activeViewer.updateView();
    }

    @Override
    public void handleMovement(Direction direction) {
        model.movingHandler(direction);
        activeViewer.updateView();
    }

    @Override
    public void fightBattle(boolean isTheBattleDesired) {
        model.fight(isTheBattleDesired);
        activeViewer.updateView();
    }

    @Override
    public Model getModel() {
        return this.model;
    }

    @Override
    public void startHeroCreation() {
        changePhaseAndUpdateView(Game.Phase.CREATE_HERO);
    }

    private void changePhaseAndUpdateView(Game.Phase phase) {
        game.setPhase(phase);
        activeViewer.updateView();
    }

    @Override
    public void createHero(String heroName, ObjectType heroType) {
        model.createNewHero(heroName, heroType);
        changePhaseAndUpdateView(Game.Phase.WELCOME);
    }

    @Override
    public void selectHero() {
        changePhaseAndUpdateView(Game.Phase.SELECT_HERO);
    }

    @Override
    public void tryToRunFromBattle() {
        model.tryToRunFromBattle();
        activeViewer.updateView();
    }

    public void continueTheAdventure() {
        changePhaseAndUpdateView(Game.Phase.MAP);
    }

    @Override
    public void searchForDroppedArtifacts() {
        model.searchForDroppedArtifacts();
        activeViewer.updateView();
    }

    @Override
    public void keepArtifact() {
        model.keepArtifact();
        changePhaseAndUpdateView(Game.Phase.MAP);
    }

    @Override
    public void goToWelcomeWindow() {
        game.setPhase(Game.Phase.WELCOME);
    }

    @Override
    public void selectHeroByIndex(String heroIndex) {
        model.selectHero(Integer.parseInt(heroIndex));
        changePhaseAndUpdateView(Game.Phase.MAP);
    }

    @Override
    public void goToNextMap() {
        model.goToNextMap();
        activeViewer.updateView();
    }

    @Override
    public void setActiveViewer(String selectedViewer) {
        switch (selectedViewer) {
            case Viewer.CONSOLE_VIEW -> activeViewer = consoleViewer;
            case Viewer.GUI_VIEW -> activeViewer = guiViewer;
        }
    }

    @Override
    public void setConsoleViewer(ConsoleViewer consoleViewer) {
        this.consoleViewer = consoleViewer;
    }

    @Override
    public void setGuiViewer(GuiViewer guiViewer) {
        this.guiViewer = guiViewer;
    }

    @Override
    public List<Hero> getHeroes() {
        return model.getHeroes();
    }

    @Override
    public void printMap() {
        model.getMap().printMap();
    }

    @Override
    public String getVillain() {
        return model.getMap().getBattle().getVillain().toString();
    }

    @Override
    public Artifact getDroppedArtifact() {
        return model.getMap().getBattle().getDroppedArtifact();
    }
}
