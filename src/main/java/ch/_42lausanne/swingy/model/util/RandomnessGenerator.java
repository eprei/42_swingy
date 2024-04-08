package ch._42lausanne.swingy.model.util;

import java.util.Random;

public class RandomnessGenerator {
    public static boolean rollDice(double probability) {
        Random random = new Random();
        return random.nextDouble() < probability;
    }
}