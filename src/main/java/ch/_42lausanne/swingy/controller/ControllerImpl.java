package ch._42lausanne.swingy.controller;

import ch._42lausanne.swingy.model.characters.Hero;
import ch._42lausanne.swingy.model.game.Direction;
import ch._42lausanne.swingy.model.game.Model;
import ch._42lausanne.swingy.model.game.ObjectType;
import ch._42lausanne.swingy.model.game.PhasesOfTheGame;
import ch._42lausanne.swingy.view.console.ConsoleViewer;
import ch._42lausanne.swingy.view.gui.GuiViewer;
import ch._42lausanne.swingy.view.viewer.Viewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("controllerImpl")
public class ControllerImpl implements Controller {
    private final Model model;
    private Viewer consoleViewer;
    private Viewer guiViewer;
    private Viewer activeViewer;

    @Autowired
    public ControllerImpl(Model model) {
        this.model = model;
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
        changePhaseAndUpdateView(PhasesOfTheGame.CREATE_HERO);
    }

    private void changePhaseAndUpdateView(PhasesOfTheGame phase) {
        model.setPhase(phase);
        activeViewer.updateView();
    }

    @Override
    public void createHero(String heroName, ObjectType heroType) {
        model.createNewHero(heroName, heroType);
        changePhaseAndUpdateView(PhasesOfTheGame.WELCOME);
    }

    @Override
    public void selectHero() {
        changePhaseAndUpdateView(PhasesOfTheGame.SELECT_HERO);
    }

    @Override
    public void tryToRunFromBattle() {
        model.tryToRunFromBattle();
        activeViewer.updateView();
    }

    public void continueTheAdventure() {
        changePhaseAndUpdateView(PhasesOfTheGame.MAP);
    }

    @Override
    public void searchForDroppedArtifacts() {
        model.searchForDroppedArtifacts();
        activeViewer.updateView();
    }

    @Override
    public void keepArtifact() {
        model.keepArtifact();
        changePhaseAndUpdateView(PhasesOfTheGame.MAP);
    }

    @Override
    public void goToWelcomeWindow() {
        model.setPhase(PhasesOfTheGame.WELCOME);
    }

    @Override
    public void selectHeroByIndex(String heroIndex) {
        model.selectHero(Integer.parseInt(heroIndex));
        changePhaseAndUpdateView(PhasesOfTheGame.MAP);
    }

    @Override
    public void goToNextMap() {
        if (model.maximumLevelReached()) {
            model.getSelectedHero().restartHp();
            model.setPhase(PhasesOfTheGame.WIN_GAME);
        } else {
            model.createNextMap();
            model.setPhase(PhasesOfTheGame.MAP);
        }
        activeViewer.updateView();
    }

    @Override
    public void setActiveViewer(String typeOfViewerChosenByTheUser) {
        switch (typeOfViewerChosenByTheUser) {
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
}
