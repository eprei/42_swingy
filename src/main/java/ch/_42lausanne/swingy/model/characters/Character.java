package ch._42lausanne.swingy.model.characters;

import ch._42lausanne.swingy.model.artifacts.Artifact;
import ch._42lausanne.swingy.model.game.Game;
import ch._42lausanne.swingy.model.game.ObjectTypeEnum;
import ch._42lausanne.swingy.model.game.Stats;
import ch._42lausanne.swingy.model.util.RandomnessGenerator;
import ch._42lausanne.swingy.view.console.ConsoleColors;
import jakarta.persistence.*;
import lombok.Data;

import java.awt.*;

@MappedSuperclass
@Data
public class Character {
    protected String name;
    protected ObjectTypeEnum type;
    protected Dimension position;
    protected Boolean isAlive = true;
    protected int level;
    protected int experience;
    protected int initialHp;
    @OneToOne(cascade = CascadeType.ALL)
    protected Stats stats;
    @OneToOne(mappedBy = "character", cascade = CascadeType.ALL)
    protected Artifact artifact;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public static ObjectTypeEnum getHeroType(String usersChoiceOfHero) {
        return switch (usersChoiceOfHero) {
            case "a" -> ObjectTypeEnum.ARCHER;
            case "b" -> ObjectTypeEnum.BLACKSMITH;
            case "w" -> ObjectTypeEnum.WARRIOR;
            case "m" -> ObjectTypeEnum.MAGICIAN;
            default -> throw new IllegalStateException("Unexpected value: " + usersChoiceOfHero);
        };
    }

    public String toString() {
        return ConsoleColors.RED + "+----------------------------+\n" + ConsoleColors.RESET +
                ConsoleColors.RED + "| name: " + ConsoleColors.RESET + name + "\n" + ConsoleColors.RESET +
                ConsoleColors.RED + "| attack: " + ConsoleColors.RESET + stats.getAttack() + "\n" +
                ConsoleColors.RED + "| defense: " + ConsoleColors.RESET + stats.getDefense() + "\n" +
                ConsoleColors.RED + "| hitPoints: " + ConsoleColors.RESET + stats.getHitPoints() + "\n" +
                ConsoleColors.RED + "+-----------------------------+\n" + ConsoleColors.RESET;
    }

    public int getAttack() {
        return this.artifact == null ? stats.getAttack() : stats.getAttack() + artifact.getStats().getAttack();
    }

    public int getDefense() {
        return this.artifact == null ? stats.getDefense() : stats.getDefense() + artifact.getStats().getDefense();
    }

    public void attackEnemy(Character enemy) {
        int damage = ((10 - enemy.getDefense()) * this.getAttack()) / 10;
        damage = Math.max(damage, 1);

        if (RandomnessGenerator.rollDice(0.7)) {
            enemy.takeDamage(damage);
            if (Game.is_verbose()) {
                System.out.println(this.name + " attacks " + enemy.getName() + " for " + damage + " damage!");
            }
        } else {
            if (Game.is_verbose()) {
                System.out.println(this.name + " misses his attack on " + enemy.getName());
            }
        }
    }

    public void takeDamage(int damage) {
        this.stats.setHitPoints(this.stats.getHitPoints() - damage);
        this.isAlive = this.stats.getHitPoints() > 0;
    }

    public int getStrength() {
        return stats.getAttack() + stats.getDefense();
    }

    public void restartHp() {
        this.stats.setHitPoints(this.initialHp);
        this.isAlive = true;
    }

}
