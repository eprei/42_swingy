package ch._42lausanne.swingy.view.console;

import ch._42lausanne.swingy.model.artifacts.Artifact;
import ch._42lausanne.swingy.model.characters.Hero;
import ch._42lausanne.swingy.model.game.ObjectTypeEnum;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UserMessages {
    public static final String PRESS_ENTER = "Press enter to start the battle:";
    private static final String USAGE = """
            Usage:
                    java -jar swingy.jar console
                    java -jar swingy.jar gui
            """;
    private static final String BANNER =
            ConsoleColors.BLACK_BACKGROUND + ConsoleColors.YELLOW_BOLD + "_____/\\\\\\\\\\\\\\\\\\\\\\____________________________________________________________________" + ConsoleColors.RESET + "\n" +
                    ConsoleColors.BLACK_BACKGROUND + ConsoleColors.YELLOW_BOLD + "___/\\\\\\/////////\\\\\\__________________________________________________________________" + ConsoleColors.RESET + "\n" +
                    ConsoleColors.BLACK_BACKGROUND + ConsoleColors.YELLOW_BOLD + "__\\//\\\\\\______\\///_____________________/\\\\\\_________________/\\\\\\\\\\\\\\\\_____/\\\\\\__/\\\\\\_" + ConsoleColors.RESET + "\n" +
                    ConsoleColors.BLACK_BACKGROUND + ConsoleColors.YELLOW_BOLD + "___\\////\\\\\\__________/\\\\____/\\\\___/\\\\_\\///___/\\\\/\\\\\\\\\\\\____/\\\\\\////\\\\\\___\\//\\\\\\/\\\\\\__" + ConsoleColors.RESET + "\n" +
                    ConsoleColors.BLACK_BACKGROUND + ConsoleColors.YELLOW_BOLD + "______\\////\\\\\\______\\/\\\\\\__/\\\\\\\\_/\\\\\\__/\\\\\\_\\/\\\\\\////\\\\\\__\\//\\\\\\\\\\\\\\\\\\____\\//\\\\\\\\\\___" + ConsoleColors.RESET + "\n" +
                    ConsoleColors.BLACK_BACKGROUND + ConsoleColors.YELLOW_BOLD + "_________\\////\\\\\\___\\//\\\\\\/\\\\\\\\\\/\\\\\\__\\/\\\\\\_\\/\\\\\\__\\//\\\\\\__\\///////\\\\\\_____\\//\\\\\\____" + ConsoleColors.RESET + "\n" +
                    ConsoleColors.BLACK_BACKGROUND + ConsoleColors.YELLOW_BOLD + "__/\\\\\\______\\//\\\\\\___\\//\\\\\\\\\\/\\\\\\\\\\___\\/\\\\\\_\\/\\\\\\___\\/\\\\\\__/\\\\_____\\\\\\__/\\\\_/\\\\\\_____" + ConsoleColors.RESET + "\n" +
                    ConsoleColors.BLACK_BACKGROUND + ConsoleColors.YELLOW_BOLD + "_\\///\\\\\\\\\\\\\\\\\\\\\\/_____\\//\\\\\\\\//\\\\\\____\\/\\\\\\_\\/\\\\\\___\\/\\\\\\_\\//\\\\\\\\\\\\\\\\__\\//\\\\\\\\/______" + ConsoleColors.RESET + "\n" +
                    ConsoleColors.BLACK_BACKGROUND + ConsoleColors.YELLOW_BOLD + "___\\///////////________\\///__\\///_____\\///__\\///____\\///___\\////////____\\////________" + ConsoleColors.RESET + "\n";
    private static final String WELCOME = ConsoleColors.BLUE_UNDERLINED + """
            Do you wish to:
            """ + ConsoleColors.RESET + """
            (c) Create Character: Forge your own hero with unique abilities and appearance.
            (p) Play: Select one of your heroes to start your journey immediately.""";
    private static final String MOVEMENT_INSTRUCTIONS = ConsoleColors.BLUE_UNDERLINED + """
            Choose your direction!
            """ + ConsoleColors.RESET
            + """
            (w): North
            (s): South
            (d): East
            (a): West""";
    private static final String[] FIGHT_OR_RUN = {
            "You have encountered a " + ConsoleColors.RED + "VILLAIN 👺" + ConsoleColors.RESET + "!\n",
            ConsoleColors.BLUE_UNDERLINED + """
                    What do you want to do?
                    """ + ConsoleColors.RESET + """            
                    (f) ⚔️ Fight: You will engage the enemy in battle. Your chances of success depend on your strength and skills.
                    (r) 🏃 Run : You will try to escape from the enemy but if you don't succeed you will have to fight him and he will attack you first."""
    };
    private static final String[] YOU_WIN_THE_BATTLE = {
            "You have emerged victorious from the battle!",
            "Your skills have led you to triumph!",
    };
    private static final String[] YOU_LOSE_THE_BATTLE = new String[]{
            "You have been defeated! Better luck next time!",
            "Your enemies have overwhelmed you.",
            "You have fallen in battle. Your quest is over.",
            "Better luck next time, brave warrior!"
    };
    private static final String[] YOU_WIN_THE_MAP = new String[]{
            "Congratulations! You have explored the entire map!",
            "You have uncovered all the secrets of the map and emerged victorious!",
            "Your journey across the map is complete. You are a master explorer!",
    };
    @Getter
    private static final String YOU_WIN_THE_GAME = "You have won the game!";
    @Getter
    private static final String RUN_SUCCESSFUL = "You have eluded your enemy! Well done!";
    @Getter
    private static final String RUN_FAILED = "The enemy is closing in! You must fight or die!";
    private static final String[] LEVEL_UP = {"Congratulations! Your ", " has reached level "};
    private static final String[] ARTIFACT_KEPT = {"The " + ConsoleColors.BLUE_UNDERLINED + "ARTIFACT" + ConsoleColors.RESET, " has been kept\n"};
    private static final String[] DROPPED_ARTIFACT = {"You have found an" + ConsoleColors.PURPLE + " ARTIFACT\n" + ConsoleColors.RESET,
            "You can " + ConsoleColors.BLUE_UNDERLINED + "keep it"
                    + ConsoleColors.RESET + " (k) or " + ConsoleColors.BLUE_UNDERLINED +
                    "leave it" + ConsoleColors.RESET + " (l)"};
    private static final String[] EXPERIENCE_GAINED = {" has gained ", " points of experience (XP)"};
    private static final String SELECT_HERO = ConsoleColors.BLUE_UNDERLINED +
            "Select one of the heroes shown above by entering its" + ConsoleColors.RESET + " id:\n";
    private static final String CHOSE_HERO_TYPE = ConsoleColors.BLUE_UNDERLINED +
            "What kind of hero do you want to create?\n" + ConsoleColors.RESET + """
            (a) Archer
            (b) Blacksmith
            (w) Warrior
            (m) Magician""";
    private static final String CHOSE_HERO_NAME = ConsoleColors.BLUE_UNDERLINED
            + "Please enter the name of the hero you want to create:"
            + ConsoleColors.RESET;
    private static final int[] randomSeeds = new int[]{0, 0, 0, 0, 0, 0};
    private static final String NO_HERO_FOUND = """
            Looks like we didn't find any heroes available.
            Let's create one so you can start playing.""";
    private static final String HERO_SUCCESSFULLY_CREATED = "has been successfully created.";
    private static final String[] NEW_MAP = {
            ConsoleColors.CYAN + "\t**************\n\t*\t" + ConsoleColors.RESET +
                    "MAP\s\s",
            ConsoleColors.CYAN + "\t *\n\t**************\n" + ConsoleColors.RESET
                    + ConsoleColors.RED + "v" + ConsoleColors.RESET + " = villain\t\t"
                    + ConsoleColors.GREEN + "h" + ConsoleColors.RESET + " = hero"
    };
    private static final String[] HERO_CHOICE = {"You have chosen " + ConsoleColors.GREEN, ConsoleColors.RESET + ". May the force be with you!\n"};

    public static void printBanner() {
        System.out.println(BANNER);
    }

    public static void printUsage() {
        System.out.println(USAGE);
    }

    public static void printMovementInstructions() {
        System.out.println(MOVEMENT_INSTRUCTIONS);
    }

    public static void printFightOrRun(String villainInfos) {
        String message = FIGHT_OR_RUN[0] + villainInfos + FIGHT_OR_RUN[1];
        System.out.println(message);
    }

    public static void printYouWinTheBattle() {
        String message = YOU_WIN_THE_BATTLE[randomSeeds[1]++ % YOU_WIN_THE_BATTLE.length];
        System.out.println(message);
    }

    public static String getYOU_WIN_THE_BATTLE() {
        return YOU_WIN_THE_BATTLE[randomSeeds[1]++ % YOU_WIN_THE_BATTLE.length];
    }

    public static void printYouLoseTheBattle() {
        String message = YOU_LOSE_THE_BATTLE[randomSeeds[2]++ % YOU_LOSE_THE_BATTLE.length];
        System.out.println(message);
    }

    public static String getYOU_LOSE_THE_BATTLE() {
        return YOU_LOSE_THE_BATTLE[randomSeeds[2]++ % YOU_LOSE_THE_BATTLE.length];
    }

    public static void printYouWinTheMap() {
        String message = YOU_WIN_THE_MAP[randomSeeds[3]++ % YOU_WIN_THE_MAP.length];
        System.out.println(message);
    }

    public static String getYOU_WIN_THE_MAP() {
        return YOU_WIN_THE_MAP[randomSeeds[3]++ % YOU_WIN_THE_MAP.length];
    }

    public static void printYouWinTheGame() {
        System.out.println(YOU_WIN_THE_GAME);
    }

    public static void printDroppedArtifact(Artifact artifact) {
        String message = DROPPED_ARTIFACT[0] + artifact + DROPPED_ARTIFACT[1];
        System.out.println(message);
    }

    public static void printArtifactKept(Artifact artifact) {
        String message = ARTIFACT_KEPT[0] + " " + artifact.getType() + ARTIFACT_KEPT[1];
        System.out.println(message);
    }

    public static void printLevelUp(Hero hero) {
        String message = LEVEL_UP[0] + hero.getType() + " " + ConsoleColors.GREEN + hero.getName() +
                ConsoleColors.RESET + LEVEL_UP[1] + ConsoleColors.YELLOW + hero.getLevel() + ConsoleColors.RESET + ".";
        System.out.println(message);
    }

    public static String[] getLevelUp(Hero hero) {
        return new String[]{LEVEL_UP[0] + hero.getType() + " " + hero.getName(), LEVEL_UP[1] + hero.getLevel() + "."};
    }

    public static void printExperienceGained(Hero hero) {
        String message = hero.getType() + " " + ConsoleColors.GREEN + hero.getName() +
                ConsoleColors.RESET + EXPERIENCE_GAINED[0] + ConsoleColors.YELLOW +
                hero.getExperienceGained() + ConsoleColors.RESET + EXPERIENCE_GAINED[1] + ".";
        System.out.println(message);
    }

    public static void printRunSuccessful() {
        System.out.println(RUN_SUCCESSFUL);
    }

    public static void printRunFailed() {
        System.out.println(RUN_FAILED);
    }

    public static void printPressEnter() {
        System.out.println(PRESS_ENTER);
    }

    public static void printWelcome() {
        System.out.println(WELCOME);
    }

    public static void printAvailableHeroes(List<Hero> heroes) {
        AtomicInteger i = new AtomicInteger();
        heroes.forEach(hero -> System.out.printf(hero.toString(i.getAndIncrement())));
        System.out.print(SELECT_HERO);
    }

    public static void printChoseHeroType() {
        System.out.println(CHOSE_HERO_TYPE);
    }

    public static void printChoseHeroName() {
        System.out.println(CHOSE_HERO_NAME);
    }

    public static void printNoHeroFound() {
        System.out.println(NO_HERO_FOUND);
    }

    public static void printHeroSuccessfullyCreated(String name, ObjectTypeEnum type) {
        String message = ConsoleColors.GREEN + name + ConsoleColors.RESET + " the " + type + " " + HERO_SUCCESSFULLY_CREATED;
        System.out.println(message);
    }

    public static void printMapHeader(int mapId) {
        String message = NEW_MAP[0] + mapId + NEW_MAP[1];
        System.out.println(message);
    }

    public static void printHeroChoice(String name) {
        String message = HERO_CHOICE[0] + name + HERO_CHOICE[1];
        System.out.println(message);
    }
}
