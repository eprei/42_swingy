package ch._42lausanne.swingy.view.classes.console;

import ch._42lausanne.swingy.model.artifacts.classes.Artifact;
import ch._42lausanne.swingy.model.artifacts.classes.ArtifactType;
import ch._42lausanne.swingy.model.characters.classes.Hero;
import ch._42lausanne.swingy.model.game.enums.ObjectType;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UserMessages {
    private static final int[] randomSeeds = new int[]{0, 0, 0, 0, 0, 0};

    public static String BANNER = """
            _____/\\\\\\\\\\\\\\\\\\\\\\____________________________________________________________________       \s
             ___/\\\\\\/////////\\\\\\__________________________________________________________________      \s
              __\\//\\\\\\______\\///_____________________/\\\\\\_________________/\\\\\\\\\\\\\\\\_____/\\\\\\__/\\\\\\_     \s
               ___\\////\\\\\\__________/\\\\____/\\\\___/\\\\_\\///___/\\\\/\\\\\\\\\\\\____/\\\\\\////\\\\\\___\\//\\\\\\/\\\\\\__    \s
                ______\\////\\\\\\______\\/\\\\\\__/\\\\\\\\_/\\\\\\__/\\\\\\_\\/\\\\\\////\\\\\\__\\//\\\\\\\\\\\\\\\\\\____\\//\\\\\\\\\\___   \s
                 _________\\////\\\\\\___\\//\\\\\\/\\\\\\\\\\/\\\\\\__\\/\\\\\\_\\/\\\\\\__\\//\\\\\\__\\///////\\\\\\_____\\//\\\\\\____  \s
                  __/\\\\\\______\\//\\\\\\___\\//\\\\\\\\\\/\\\\\\\\\\___\\/\\\\\\_\\/\\\\\\___\\/\\\\\\__/\\\\_____\\\\\\__/\\\\_/\\\\\\_____ \s
                   _\\///\\\\\\\\\\\\\\\\\\\\\\/_____\\//\\\\\\\\//\\\\\\____\\/\\\\\\_\\/\\\\\\___\\/\\\\\\_\\//\\\\\\\\\\\\\\\\__\\//\\\\\\\\/______\s
                    ___\\///////////________\\///__\\///_____\\///__\\///____\\///___\\////////____\\////________
            """;
    public static String USAGE = """
            Usage:
                    java -jar swingy.jar console
                    java -jar swingy.jar gui
            """;

    public static String WELCOME = """
            Choose your destiny:
                        
                    (c) Create Character: Forge your own hero with unique abilities and appearance.
                    (p) Play: Select a predefined character to start your journey immediately.""";

    public static String[] MOVEMENT_INSTRUCTIONS = {"""
            Choose your direction!
            
                    (w): North
                    (s): South
                    (d): East
                    (a): West""",

            "Enter your move (w, a, s, d):",

            "Where to next? (w, a, s, d):",

            "Which direction do you want to go? (w, a, s, d):"};
    public static String[] FIGHT_OR_RUN = {
            "You have encountered a villain!\n",
            """
            What do you want to do?
            
                    Fight (f): You will engage the enemy in battle. Your chances of success depend on your strength and skills.
                    Run (r): You will try to escape from the enemy but if you don't succeed you will have to fight him and he will attack you first."""
    };

    public static String[] YOU_WIN_THE_BATTLE = {
            "Congratulations! You have emerged victorious from the battle!",
            "Your skills and bravery have led you to triumph!",
            "You have conquered your foes and stand triumphant!"
    };

    public static String[] YOU_LOSE_THE_BATTLE = new String[]{
            "Oh no! You have been defeated in battle!",
            "Your enemies have overwhelmed you.",
            "You have fallen in battle. Your quest is over.",
            "Better luck next time, brave warrior!"
    };

    public static String[] YOU_WIN_THE_MAP = new String[]{
            "Congratulations! You have explored the entire map!",
            "You have uncovered all the secrets of the map and emerged victorious!",
            "Your journey across the map is complete. You are a master explorer!",
    };

    public static String[] YOU_WIN_THE_GAME = new String[]{
            "The game is over, and you have won!",
            "You have successfully completed the game.",
            "You have achieved the ultimate goal of the game.",
            "Your victory marks the end of this epic adventure.",
            "Congratulations on your triumph in this challenging game."
    };

    public static String[] YOU_LOSE_THE_GAME = new String[]{
            "The game is over, and you have lost.",
            "You have failed to complete the game.",
            "You have succumbed to the challenges of the game.",
            "Your journey has come to an end in defeat.",
            "Better luck next time!"
    };

    public static String PRESS_ANY_KEY_TO_CONTINUE = "Press any key to continue.";

    public static String RUN_SUCCESSFUL = "You have eluded your enemy! Well done!";
    public static String RUN_FAILED = "The enemy is closing in! You must fight or die!";

    public static String[] LEVEL_UP = {"Congratulations! Your ", " has reached level "};

    public static String[] ARTIFACT_KEPT = {"The artifact ", " has been kept\n"};

    public static String[] DROPPED_ARTIFACT = {"You have found an Artifact\n", "\nYou can keep it (k) or leave it (l)"};

    public static String[] EXPERIENCIE_GAINED = {" has gained ", " points of experience (XP)"};

    public static String SELECT_HERO = "Select one of the following available heroes by entering its id.\n";

    public static String printBanner() {
        System.out.println(BANNER);
        return BANNER;
    }

    public static String printUsage() {
        System.out.println(USAGE);
        return USAGE;
    }

    public static String printMovementInstructions() {
        String message = MOVEMENT_INSTRUCTIONS[randomSeeds[0]++ % MOVEMENT_INSTRUCTIONS.length];
        System.out.println(message);
        return message;
    }

    public static String printFightOrRun(String villainInfos) {
        String message = FIGHT_OR_RUN[0] + villainInfos + FIGHT_OR_RUN[1];
        System.out.println(message);
        return message;
    }

    public static String printYouWinTheBattle() {
        String message = YOU_WIN_THE_BATTLE[randomSeeds[1]++ % YOU_WIN_THE_BATTLE.length];
        System.out.println(message);
        return message;
    }

    public static String printYouLoseTheBattle() {
        String message = YOU_LOSE_THE_BATTLE[randomSeeds[2]++ % YOU_LOSE_THE_BATTLE.length];
        System.out.println(message);
        return message;
    }

    public static String printYouWinTheMap() {
        String message = YOU_WIN_THE_MAP[randomSeeds[3]++ % YOU_WIN_THE_MAP.length];
        System.out.println(message);
        return message;
    }

    public static String printYouWinTheGame() {
        String message = YOU_WIN_THE_GAME[randomSeeds[4]++ % YOU_WIN_THE_GAME.length] + PRESS_ANY_KEY_TO_CONTINUE;
        System.out.println(message);
        return message;
    }

    public static String printLoseGame() {
        String message = YOU_LOSE_THE_GAME[randomSeeds[5]++ % YOU_LOSE_THE_GAME.length];
        System.out.println(message);
        return message;
    }

    public static String printDroppedArtifact(Artifact artifact) {
        String message = DROPPED_ARTIFACT[0] + artifact + DROPPED_ARTIFACT[1];
        System.out.println(message);
        return message;
    }

    public static String printArtifactKept(ArtifactType type) {
        String message = ARTIFACT_KEPT[0] + type + ARTIFACT_KEPT[1];
        System.out.println(message);
        return message;
    }

    public static String printLevelUp(ObjectType type, String name, int level) {
        String message = LEVEL_UP[0] + type + " " + name + LEVEL_UP[1] + level + ".";
        System.out.println(message);
        return message;
    }

    public static String printExeriencieGained(ObjectType type, String name, int experienceGained) {
        String message = type + " " + name + EXPERIENCIE_GAINED[0] + experienceGained + EXPERIENCIE_GAINED[1] + ".";
        System.out.println(message);
        return message;
    }

    public static String printRunSuccessful() {
        System.out.println(RUN_SUCCESSFUL);
        return RUN_SUCCESSFUL;
    }

    public static String printRunFailed() {
        System.out.println(RUN_FAILED);
        return RUN_FAILED;
    }

    public static String printWelcome() {
        System.out.println(WELCOME);
        return WELCOME;
    }

    public static void printAvailableHeroes(List<Hero> heroes) {
        System.out.print(SELECT_HERO);
        AtomicInteger i = new AtomicInteger();
        heroes.forEach(hero -> System.out.printf(hero.toString(i.getAndIncrement())));
    }
}
