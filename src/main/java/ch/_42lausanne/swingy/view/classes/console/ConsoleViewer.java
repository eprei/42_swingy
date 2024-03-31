package ch._42lausanne.swingy.view.classes.console;

import ch._42lausanne.swingy.model.artifacts.classes.Artifact;
import ch._42lausanne.swingy.model.game.enums.Direction;
import ch._42lausanne.swingy.view.classes.GenericViewerImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsoleViewer extends GenericViewerImpl {

    @Override
    public void welcomeView() {
        UserMessages.printWelcome();

        String userChoice = inputReader.nextLine();

        // TODO validate input: Annotation based user input validation
        switch (userChoice) {
            case "c" -> controller.createHero();
            case "p" -> controller.selectHero();
        }

    }

    @Override
    public void createHeroView() {
        // TODO
    }

    @Override
    public void selectHeroView() {
        UserMessages.printAvailableHeroes(model.getHeroes());

        String heroIndex = inputReader.nextLine();

        // TODO validate input: Annotation based user input validation
        // TODO Check that its a valid index
        controller.selectHeroIndex(heroIndex);
    }

    @Override
    public void mapView() {
        model.getMap().printMap();

        UserMessages.printMovementInstructions();
        String direction = inputReader.nextLine();

        // TODO validate input: Annotation based user input validation

        switch (direction.toLowerCase()) {
            case "w" -> controller.handleMovement(Direction.NORTH);
            case "d" -> controller.handleMovement(Direction.EAST);
            case "s" -> controller.handleMovement(Direction.SOUTH);
            case "a" -> controller.handleMovement(Direction.WEST);
        }
    }

    @Override
    public void fightOrRunStageView() {
        UserMessages.printFightOrRun(model.getMap().getBattle().getVillain().toString());

        String userChoice = inputReader.nextLine();

        // TODO validate input: Annotation based user input validation
        switch (userChoice) {
            case "f" -> controller.fightBattle();
            case "r" -> controller.tryToRunFromBattle();
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
        UserMessages.printYouWinTheGame();
        inputReader.nextLine();
        controller.goToWelcomeWindow();
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