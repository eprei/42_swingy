package ch._42lausanne.swingy.model.game;

import lombok.Data;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@Data
public class Stats {

    private int attack;
    private int defense;
    private int hitPoints;

    public Stats(int attack, int defense, int hitPoints) {
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
    }

    @Contract(value = " -> new", pure = true)
    public static @NotNull Stats archerStats() {
        return new Stats(8, 4, 80);
    }

    @Contract(value = " -> new", pure = true)
    public static @NotNull Stats blacksmithStats() {
        return new Stats(8, 16, 120);
    }

    @Contract(value = " -> new", pure = true)
    public static @NotNull Stats warriorStats() {
        return new Stats(7, 6, 100);
    }

    @Contract(value = " -> new", pure = true)
    public static @NotNull Stats magicianStats() {
        return new Stats(6, 5, 90);
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull Stats weaponStats(int villainAttack) {
        return new Stats(villainAttack / 2, 0, 0);
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull Stats armorStats(int villainDefense) {
        return new Stats(0, villainDefense / 2, 0);
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull Stats helmStats(int villainInitialHitPoints) {
        return new Stats(0, 0, villainInitialHitPoints / 4);
    }

}
