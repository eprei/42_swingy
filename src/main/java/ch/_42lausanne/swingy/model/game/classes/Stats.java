package ch._42lausanne.swingy.model.game.classes;

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
        return new Stats(16, 8, 80);
    }

    @Contract(value = " -> new", pure = true)
    public static @NotNull Stats blacksmithStats() {
        return new Stats(8, 16, 120);
    }

    @Contract(value = " -> new", pure = true)
    public static @NotNull Stats warriorStats() {
        return new Stats(14, 12, 100);
    }

    @Contract(value = " -> new", pure = true)
    public static @NotNull Stats magicianStats() {
        return new Stats(12, 10, 90);
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
    public static @NotNull Stats helmStats(int villainHitPoints) {
        return new Stats(0, 0, villainHitPoints / 6);
    }

}
