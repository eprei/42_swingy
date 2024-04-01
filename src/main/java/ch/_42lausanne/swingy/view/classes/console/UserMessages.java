package ch._42lausanne.swingy.view.classes.console;

import ch._42lausanne.swingy.model.artifacts.classes.Artifact;
import ch._42lausanne.swingy.model.artifacts.classes.ArtifactType;
import ch._42lausanne.swingy.model.characters.classes.Hero;
import ch._42lausanne.swingy.model.game.classes.Map;
import ch._42lausanne.swingy.model.game.enums.ObjectType;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UserMessages {
    private static final String USAGE = """
            Usage:
                    java -jar swingy.jar console
                    java -jar swingy.jar gui
            """;
    private static final String BANNER = """
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
    private static final String WELCOME = """
            Do you wish to:
                        
                    (c) Create Character: Forge your own hero with unique abilities and appearance.
                    (p) Play: Select one of your heroes to start your journey immediately.""";
    private static final String MOVEMENT_INSTRUCTIONS = """
            Choose your direction!
                        
                    (w): North
                    (s): South
                    (d): East
                    (a): West""";
    private static final String[] FIGHT_OR_RUN = {
            "You have encountered a villain!\n",
            """
            What do you want to do?
            
                    Fight (f): You will engage the enemy in battle. Your chances of success depend on your strength and skills.
                    Run (r): You will try to escape from the enemy but if you don't succeed you will have to fight him and he will attack you first."""
    };
    private static final String[] YOU_WIN_THE_BATTLE = {
            "Congratulations! You have emerged victorious from the battle!",
            "Your skills and bravery have led you to triumph!",
            "You have conquered your foes and stand triumphant!"
    };
    private static final String[] YOU_LOSE_THE_BATTLE = new String[]{
            "Oh no! You have been defeated in battle! Better luck next time!",
            "Your enemies have overwhelmed you. Seek your revenge!",
            "You have fallen in battle. Your quest is over.",
            "Better luck next time, brave warrior!"
    };
    private static final String[] YOU_WIN_THE_MAP = new String[]{
            "Congratulations! You have explored the entire map!\n",
            "You have uncovered all the secrets of the map and emerged victorious!\n",
            "Your journey across the map is complete. You are a master explorer!\n",
    };
    private static final String GET_READY_FOR_THE_NEXT_MAP = "Get ready for the next one";
    private static final String[] YOU_WIN_THE_GAME = new String[]{
            "Congratulations! You have conquered the last Map (",
            """
            ).
            You have won the game!
            Press any key to continue.""",
    };
    private static final String[] YOU_LOSE_THE_GAME = new String[]{
            "The game is over, and you have lost.",
            "You have failed to complete the game.",
            "You have succumbed to the challenges of the game.",
            "Your journey has come to an end in defeat.",
            "Better luck next time!"
    };
    private static final String PRESS_ANY_KEY_TO_CONTINUE = "Press any key to continue.";
    private static final String RUN_SUCCESSFUL = "You have eluded your enemy! Well done!";
    private static final String RUN_FAILED = """
            The enemy is closing in! You must fight or die!
            Press any key to continue:""";
    private static final String[] LEVEL_UP = {"Congratulations! Your ", " has reached level "};
    private static final String[] ARTIFACT_KEPT = {"The artifact ", " has been kept\n"};
    private static final String[] DROPPED_ARTIFACT = {"You have found an Artifact\n", "\nYou can keep it (k) or leave it (l)"};
    private static final String[] EXPERIENCIE_GAINED = {" has gained ", " points of experience (XP)"};
    private static final String SELECT_HERO = "Select one of the following available heroes by entering its id.\n";
    private static final String CHOSE_HERO_TYPE = """ 
            What kind of hero do you want to create?
                
                    (a) Archer
                    (b) Blacksmith
                    (w) Warrior
                    (m) Magician""";
    private static final String CHOSE_HERO_NAME = """
            Please enter the name of the hero you want to create:""";
    private static final int[] randomSeeds = new int[]{0, 0, 0, 0, 0, 0};
    private static final String NO_HERO_FOUND = """
            Looks like we didn't find any heroes available.
            Let's create one so you can start playing.""";
    private static final String HERO_SUCCESSFULLY_CREATED = "has been successfully created.";
    private static final String[] NEW_MAP = {"""
            \t**************
            \t*\tMAP\s\s""",
            """
            \t *
            \t**************
            """};
    private static final String[] HERO_CHOICE = {"You have chosen ", ". May the force be with you!\n"};

    public static String printBanner() {
        System.out.println(BANNER);
        return BANNER;
    }

    public static String printUsage() {
        System.out.println(USAGE);
        return USAGE;
    }

    public static String printMovementInstructions() {
        System.out.println(MOVEMENT_INSTRUCTIONS);
        return MOVEMENT_INSTRUCTIONS;
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

    public static String printGetReadyForTheNextMap() {
        String message = GET_READY_FOR_THE_NEXT_MAP;
        System.out.println(message);
        return message;
    }

    public static String printYouWinTheGame() {
        String message = YOU_WIN_THE_GAME[0] + Map.getFINAL_MAP() + YOU_WIN_THE_GAME[1];
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
        System.out.println("id of the chosen hero:");
    }

    public static String printChoseHeroType() {
        System.out.println(CHOSE_HERO_TYPE);
        return CHOSE_HERO_TYPE;
    }

    public static String printChoseHeroName() {
        System.out.println(CHOSE_HERO_NAME);
        return CHOSE_HERO_NAME;
    }

    public static String printNoHeroFound() {
        System.out.println(NO_HERO_FOUND);
        return NO_HERO_FOUND;
    }

    public static String printHeroSuccessfullyCreated(Hero hero) {
        String message = hero.getName() + " the " + hero.getType() + " " + HERO_SUCCESSFULLY_CREATED;
        System.out.println(message);
        return message;
    }

    public static String printMapCreated(int mapId) {
        String message = NEW_MAP[0] + mapId + NEW_MAP[1];
        System.out.println(message);
        return message;
    }

    public static String printHeroChoice(String name) {
        String message = HERO_CHOICE[0] + name + HERO_CHOICE[1];
        System.out.println(message);
        return message;
    }
}
