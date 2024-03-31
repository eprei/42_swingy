package ch._42lausanne.swingy.controller.classes;

import ch._42lausanne.swingy.controller.interfaces.Controller;
import ch._42lausanne.swingy.model.characters.classes.Hero;
import ch._42lausanne.swingy.model.game.classes.Model;
import ch._42lausanne.swingy.model.game.enums.Direction;
import ch._42lausanne.swingy.model.game.enums.Phase;
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
    public void fightBattle() {
        model.fight(true);
        viewer.updateView();
    }

    @Override
    public void createHero() {
        model.setPhase(Phase.CREATE_HERO);
        viewer.updateView();
    }

    @Override
    public void selectHero() {
        model.setPhase(Phase.SELECT_HERO);
        viewer.updateView();
    }

    @Override
    public void tryToRunFromBattle() {
        model.tryToRunFromBattle();
        viewer.updateView();
    }

    public void continueTheAdventure() {
        model.setPhase(Phase.MAP);
        viewer.updateView();
    }

    @Override
    public void searchForDroppedArtifacts() {
        model.searchForDroppedArtifacts();
        viewer.updateView();
    }

    @Override
    public void keepArtifact() {
        model.keepArtifact();
        viewer.updateView();
    }

    @Override
    public void goToWelcomeWindow() {
        model.setPhase(Phase.WELCOME);
    }

    @Override
    public void selectHeroIndex(String heroIndex) {
        Hero heroSelected = model.getHeroes().get(Integer.parseInt(heroIndex));
        model.setHero(heroSelected);
        model.setPhase(Phase.MAP);
        viewer.updateView();
    }
}
