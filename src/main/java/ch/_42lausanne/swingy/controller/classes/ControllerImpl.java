package ch._42lausanne.swingy.controller.classes;

import ch._42lausanne.swingy.controller.interfaces.Controller;
import ch._42lausanne.swingy.model.characters.classes.Hero;
import ch._42lausanne.swingy.model.game.classes.Model;
import ch._42lausanne.swingy.model.game.enums.ActiveStage;
import ch._42lausanne.swingy.model.game.enums.Direction;
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
        model.fight();
        viewer.updateView();
    }

    @Override
    public void createHero() {
        viewer.updateView();
    }

    @Override
    public void selectHero(Hero heroSelected) {
        model.setHero(heroSelected);
        viewer.updateView();
    }

    @Override
    public void runFromBattle() {
        viewer.updateView();
    }

    @Override
    public void continueTheAdventure() {
        model.setActiveStage(ActiveStage.MAP_STAGE);
        viewer.updateView();
    }
}