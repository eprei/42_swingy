package ch._42lausanne.swingy.view.classes.console;

public class UserMessages {
    private static final int[] randomSeeds = new int[]{0, 0, 0, 0, 0};
    public static String USAGE = """
            Usage:
                    java -jar swingy.jar console
                    java -jar swingy.jar gui
            """;
    public static String[] MOVEMENT_INSTRUCTIONS = new String[]{"""
            Choose your direction!

            North (w): You will move north.
            South (s): You will move south.
            East (d): You will move east.
            West (a): You will move west.

            Enter the command corresponding to the direction you want to move:""",

            "Enter your move (w, a, s, d):",

            "Where to next? (w, a, s, d):",

            "Which direction do you want to go? (w, a, s, d):"};
    public static String[] FIGHT_OR_RUN = new String[]
            {"You have encountered a villain!\n",
                    """
            
            What do you want to do?
            Fight (f): You will engage the enemy in battle. Your chances of success depend on your strength and skills.
            Run (r): You will try to escape from the enemy. Your chances of success depend on your speed and stealth.

            Choose your action by entering the letter "f" to fight or the letter "r" to run:"""
            };

    public static String[] YOU_WIN_THE_BATTLE = new String[]{
            "Congratulations! You have emerged victorious from the battle!",
            "Your skills and bravery have led you to triumph!",
            "You have conquered your foes and stand triumphant!"
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

    public static String printYouWinTheMap() {
        String message = YOU_WIN_THE_MAP[randomSeeds[2]++ % YOU_WIN_THE_MAP.length];
        System.out.println(message);
        return message;
    }

    public static String printYouWinTheGame() {
        String message = YOU_WIN_THE_GAME[randomSeeds[3]++ % YOU_WIN_THE_GAME.length];
        System.out.println(message);
        return message;
    }

    public static String printLoseGame() {
        String message = YOU_LOSE_THE_GAME[randomSeeds[4]++ % YOU_LOSE_THE_GAME.length];
        System.out.println(message);
        return message;
    }
}
