package ch._42lausanne.swingy.view.console;

import ch._42lausanne.swingy.controller.Controller;
import ch._42lausanne.swingy.model.artifacts.Artifact;
import ch._42lausanne.swingy.model.characters.Character;
import ch._42lausanne.swingy.model.characters.Hero;
import ch._42lausanne.swingy.model.game.Direction;
import ch._42lausanne.swingy.model.game.Game;
import ch._42lausanne.swingy.model.game.Map;
import ch._42lausanne.swingy.model.game.ObjectTypeEnum;
import ch._42lausanne.swingy.view.validator.*;
import ch._42lausanne.swingy.view.viewer.BaseViewer;
import ch._42lausanne.swingy.view.viewer.UserInputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("consoleViewer")
public class ConsoleViewer extends BaseViewer {
    public static boolean welcomeBannerHasBeenShown = false;
    private final UserInputValidator userInputValidator;

    @Autowired
    public ConsoleViewer(Controller controller, Game game, UserInputValidator userInputValidator) {
        this.userInputValidator = userInputValidator;
        this.controller = controller;
        this.controller.setConsoleViewer(this);
        this.game = game;
    }

    @Override
    public void welcomeView() {
        printBanner();

        if (controller.getHeroes().isEmpty()) {
            UserMessages.printNoHeroFound();
            controller.startHeroCreation();
        }

        UserMessages.printWelcome();
        String userInput = userInputValidator.getAndValidateConsoleInput(WelcomeUserInput.class);

        switch (userInput.toLowerCase()) {
            case "c" -> controller.startHeroCreation();
            case "p" -> controller.selectHero();
            case "switch" -> controller.switchActiveViewer();
        }
    }

    private void printBanner() {
        if (!welcomeBannerHasBeenShown) {
            UserMessages.printBanner();
            welcomeBannerHasBeenShown = true;
        }
    }

    @Override
    public void createHeroView() {
        UserMessages.printChoseHeroName();
        String heroName = userInputValidator.getAndValidateConsoleInput(HeroNameUserInput.class);

        UserMessages.printChoseHeroType();
        String chosenHeroType = userInputValidator.getAndValidateConsoleInput(HeroTypeUserInput.class);

        ObjectTypeEnum heroType = Character.getHeroType(chosenHeroType.toLowerCase());

        UserMessages.printHeroSuccessfullyCreated(heroName, heroType);
        controller.createHero(heroName, heroType);
    }

    @Override
    public void selectHeroView() {
        String chosenHeroIndex;
        do {
            UserMessages.printAvailableHeroes(controller.getHeroes());
            chosenHeroIndex = userInputValidator.getAndValidateConsoleInput(HeroIndexUserInput.class);
        } while (controller.getHeroes().size() <= Integer.parseInt(chosenHeroIndex)
                || Integer.parseInt(chosenHeroIndex) < 0);

        UserMessages.printHeroChoice(controller.getHeroes().get(Integer.parseInt(chosenHeroIndex)).getName());
        controller.selectHeroByIndex(chosenHeroIndex);
    }

    @Override
    public void mapView() {
        UserMessages.printMapHeader(Map.getMapId());
        controller.printMap();

        UserMessages.printMovementInstructions();
        String direction = userInputValidator.getAndValidateConsoleInput(DirectionUserInput.class);

        switch (direction.toLowerCase()) {
            case "w" -> controller.handleMovement(Direction.NORTH);
            case "d" -> controller.handleMovement(Direction.EAST);
            case "s" -> controller.handleMovement(Direction.SOUTH);
            case "a" -> controller.handleMovement(Direction.WEST);
            case "switch" -> controller.switchActiveViewer();
        }
    }

    @Override
    public void fightOrRunView() {
        UserMessages.printFightOrRun(controller.getVillain());

        String userChoice = userInputValidator.getAndValidateConsoleInput(FightOrRunUserInput.class);

        switch (userChoice.toLowerCase()) {
            case "f" -> controller.fightBattle(true);
            case "r" -> controller.tryToRunFromBattle();
            case "switch" -> controller.switchActiveViewer();
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
        UserMessages.printPressEnter();
        userInputValidator.getAndValidateConsoleInput(AnyKeyIsValidUserInput.class);
        controller.fightBattle(false);
    }

    @Override
    public void winBattleView() {
        Hero activeHero = controller.getActiveHero();

        UserMessages.printYouWinTheBattle();
        UserMessages.printExperienceGained(activeHero);
        if (activeHero.isLeveledUp()) {
            UserMessages.printLevelUp(activeHero);
        }
        System.out.print(activeHero);
        controller.searchForDroppedArtifacts();
    }

    @Override
    public void looseBattleView() {
        UserMessages.printYouLoseTheBattle();
        controller.goToWelcomeWindow();
    }

    @Override
    public void artifactDroppedView() {
        Artifact artifact = controller.getDroppedArtifact();

        UserMessages.printDroppedArtifact(artifact);
        String userChoice = userInputValidator.getAndValidateConsoleInput(ArtifactDroppedUserInput.class);

        switch (userChoice.toLowerCase()) {
            case "k" -> {
                UserMessages.printArtifactKept(controller.getDroppedArtifact());
                controller.keepArtifact();
            }
            case "l" -> controller.continueTheAdventure();
            case "switch" -> controller.switchActiveViewer();
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
        System.out.println("Press enter to continue");
        userInputValidator.getAndValidateConsoleInput(AnyKeyIsValidUserInput.class);
        controller.goToWelcomeWindow();
    }

    @Override
    public void becomeActiveViewer() {
        welcomeBannerHasBeenShown = true;
        System.out.println(ConsoleColors.YELLOW
                + "View changed successfully. Now you can continue playing from the terminal."
                + ConsoleColors.RESET);
        updateView();
    }
}