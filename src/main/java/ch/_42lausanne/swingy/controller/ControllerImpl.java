package ch._42lausanne.swingy.controller;

import ch._42lausanne.swingy.model.game.Direction;
import ch._42lausanne.swingy.model.game.Model;
import ch._42lausanne.swingy.model.game.ObjectType;
import ch._42lausanne.swingy.model.game.PhasesOfTheGame;
import ch._42lausanne.swingy.view.viewer.Viewer;

public record ControllerImpl(Model model, Viewer viewer) implements Controller {

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
    public Model getModel() {
        return this.model;
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
        viewer.updateView();
    }
}
