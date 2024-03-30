package ch._42lausanne.swingy.view.classes.console;

import ch._42lausanne.swingy.model.artifacts.classes.Artifact;
import ch._42lausanne.swingy.model.characters.classes.Hero;
import ch._42lausanne.swingy.model.game.enums.Direction;
import ch._42lausanne.swingy.view.classes.GenericViewerImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsoleViewer extends GenericViewerImpl {

    @Override
    public void welcomeView() {
        Hero heroSelected = model.getHeroes().get(1);
        controller.selectHero(heroSelected);
    }

    @Override
    public void mapView() {
        model.getMap().printMap();

        UserMessages.printMovementInstructions();
        String direction = inputReader.nextLine();

        // TODO validate input: Annotation based user input validation

        switch (direction) {
            case "w" -> controller.handleMovement(Direction.NORTH);
            case "d" -> controller.handleMovement(Direction.EAST);
            case "s" -> controller.handleMovement(Direction.SOUTH);
            case "a" -> controller.handleMovement(Direction.WEST);
        }
    }

    @Override
    public void fightOrRunStageView() {
        model.getMap().printMap();

        UserMessages.printFightOrRun(model.getMap().getBattle().getVillain().toString());

        String userChoice = inputReader.nextLine();

        // TODO validate input: Annotation based user input validation
        switch (userChoice) {
            case "f" -> controller.fightBattle();
            case "r" -> controller.runFromBattle();
        }
    }

    @Override
    public void runView() {
        log.info("runView");
    }

    @Override
    public void fightView() {
        log.info("fightView");
    }

    @Override
    public void winBattleView() {
        controller.searchForDroppedArtifacts();
    }

    @Override
    public void looseBattleView() {
        log.info("looseBattleView");
    }

    @Override
    public void artifactDroppedView() {
        Artifact artifact = model.getMap().getBattle().getArtifactDropped();

        UserMessages.printDroppedArtifact(artifact);

        String userChoice = inputReader.nextLine();

        // TODO validate input: Annotation based user input validation
        switch (userChoice) {
            case "k" -> controller.keepArtifact();
            case "l" -> controller.continueTheAdventure();
        }
    }

    @Override
    public void winMapView() {
        UserMessages.printYouWinTheMap();
    }

    @Override
    public void winGameView() {
        log.info("winGameView");
        UserMessages.printYouWinTheGame();
    }

    @Override
    public void exitMapView() {
        log.info("exitMapView");
    }

    @Override
    public void exitGameView() {
        log.info("exitGameView");
    }

}