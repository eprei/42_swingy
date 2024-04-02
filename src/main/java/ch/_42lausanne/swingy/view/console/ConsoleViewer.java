package ch._42lausanne.swingy.view.console;

import ch._42lausanne.swingy.model.artifacts.Artifact;
import ch._42lausanne.swingy.model.characters.Character;
import ch._42lausanne.swingy.model.game.Direction;
import ch._42lausanne.swingy.model.game.ObjectType;
import ch._42lausanne.swingy.view.viewer.ViewerImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsoleViewer extends ViewerImpl {

    @Override
    public void welcomeView() {
        if (model.getHeroes().isEmpty()) {
            UserMessages.printNoHeroFound();
            controller.startHeroCreation();
        }

        UserMessages.printWelcome();

        String userChoice = inputReader.nextLine();

        // TODO validate input: Annotation based user input validation
        switch (userChoice) {
            case "c" -> controller.startHeroCreation();
            case "p" -> controller.selectHero();
        }
    }

    @Override
    public void createHeroView() {
        // TODO validate input: Annotation based user input validation

        UserMessages.printChoseHeroName();
        String heroName = inputReader.nextLine();

        UserMessages.printChoseHeroType();
        String chosenHeroType = inputReader.nextLine();

        ObjectType heroType = Character.getHeroTypeObject(chosenHeroType.toLowerCase());

        controller.createHero(heroName, heroType);

    }

    @Override
    public void selectHeroView() {
        UserMessages.printAvailableHeroes(model.getHeroes());

        String chosenHeroIndex = inputReader.nextLine();

        // TODO validate input: Annotation based user input validation
        // TODO Check that its a valid index
        controller.selectHeroByIndex(chosenHeroIndex);
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
    public void fightOrRunView() {
        UserMessages.printFightOrRun(model.getMap().getBattle().getVillain().toString());

        String userChoice = inputReader.nextLine();

        // TODO validate input: Annotation based user input validation
        switch (userChoice) {
            case "f" -> controller.fightBattle(true);
            case "r" -> controller.tryToRunFromBattle();
        }
    }

    @Override
    public void runSuccessfulView() {
        UserMessages.printRunSuccessful();
        controller.continueTheAdventure();
    }

    @Override
    public void runFailedView() {
        UserMessages.printRunFailed();
        inputReader.nextLine();
        controller.fightBattle(false);
    }

    @Override
    public void winBattleView() {
        controller.searchForDroppedArtifacts();
    }

    @Override
    public void looseBattleView() {
        controller.goToWelcomeWindow();
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
        controller.goToNextMap();
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