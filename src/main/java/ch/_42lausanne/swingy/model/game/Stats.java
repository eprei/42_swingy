package ch._42lausanne.swingy.model.game;

import ch._42lausanne.swingy.model.artifacts.Artifact;
import ch._42lausanne.swingy.model.characters.Hero;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "STAT")
@Data
public class Stats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int attack;
    private int defense;

    private int hitPoints;
    @OneToOne(mappedBy = "stats")
    private Hero character;
    @OneToOne(mappedBy = "stats", cascade = CascadeType.ALL)
    private Artifact artifact;

    public Stats(int attack, int defense, int hitPoints) {
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
    }

    public Stats() {
    }

    public static Stats archerStats() {
        return new Stats(8, 4, 80);
    }

    public static Stats blacksmithStats() {
        return new Stats(4, 8, 120);
    }

    public static Stats warriorStats() {
        return new Stats(7, 6, 100);
    }

    public static Stats magicianStats() {
        return new Stats(7, 5, 90);
    }

    public static Stats weaponStats(int villainAttack) {
        return new Stats(villainAttack / 2, 0, 0);
    }

    public static Stats armorStats(int villainDefense) {
        return new Stats(0, villainDefense / 2, 0);
    }

    public static Stats helmStats(int villainInitialHitPoints) {
        return new Stats(0, 0, villainInitialHitPoints / 4);
    }

}
