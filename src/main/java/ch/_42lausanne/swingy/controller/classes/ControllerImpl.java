package ch._42lausanne.swingy.controller.classes;

import ch._42lausanne.swingy.controller.interfaces.Controller;
import ch._42lausanne.swingy.model.game.classes.Model;
import ch._42lausanne.swingy.model.game.enums.Direction;
import ch._42lausanne.swingy.model.game.enums.ObjectType;
import ch._42lausanne.swingy.model.game.enums.PhasesOfTheGame;
import ch._42lausanne.swingy.view.interfaces.GenericViewer;

public record ControllerImpl(Model model, GenericViewer viewer) implements Controller {

    @Override
    public void runApplication() {
        viewer.updateView();
    }

    @Override
    public void handleMovement(Direction direction) {
        model.movingHandler(direction);
        viewer.updateView();
    }

    @Override
    public void fightBattle(boolean isTheBattleDesired) {
        model.fight(isTheBattleDesired);
        viewer.updateView();
    }

    @Override
    public void startHeroCreation() {
        changePhaseAndUpdateView(PhasesOfTheGame.CREATE_HERO);
    }

    private void changePhaseAndUpdateView(PhasesOfTheGame phase) {
        model.setPhase(phase);
        viewer.updateView();
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
        viewer.updateView();
    }

    public void continueTheAdventure() {
        changePhaseAndUpdateView(PhasesOfTheGame.MAP);
    }

    @Override
    public void searchForDroppedArtifacts() {
        model.searchForDroppedArtifacts();
        viewer.updateView();
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
        model.setHero(Integer.parseInt(heroIndex));
        changePhaseAndUpdateView(PhasesOfTheGame.MAP);
    }

    @Override
    public void goToNextMap() {
        if (model.maximumLevelReached()) {
            model.getHero().restartHp();
            model.setPhase(PhasesOfTheGame.WIN_GAME);
        } else {
            model.createNextMap();
            model.setPhase(PhasesOfTheGame.MAP);
        }
        viewer.updateView();
    }
}
