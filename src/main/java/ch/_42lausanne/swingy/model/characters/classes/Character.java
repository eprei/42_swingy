package ch._42lausanne.swingy.model.characters.classes;

import ch._42lausanne.swingy.model.game.classes.Stats;
import ch._42lausanne.swingy.model.game.enums.ObjectType;
import lombok.Data;

import java.awt.*;
import java.util.Random;

@Data
public class Character {
    protected String name;
    protected ObjectType type;
    protected Dimension position;
    protected Stats stats;
    protected Boolean isAlive = true;
    protected int level;
    protected int experience = 0;

    public String toString() {

        return "---------------------------\n" +
                "| name: " + name + "\n" +
                "| attack: " + stats.getAttack() + "\n" +
                "| defense: " + stats.getDefense() + "\n" +
                "| hitPoints: " + stats.getHitPoints() + "\n" +
                "---------------------------\n";
    }

    public void attackEnemy(Character enemy) {
        // TODO calculate attack with artifacts
        int damage = this.stats.getAttack() - enemy.getStats().getDefense();
        damage = Math.max(damage, 0);

        Random random = new Random();
        int randomSeed = random.nextInt(11);

        if (randomSeed < 7) {
            enemy.takeDamage(damage);
            System.out.println(this.name + " attacks " + enemy.getName() + " for " + damage + " damage!");
        } else {
            System.out.println(this.name + " misses his attack on " + enemy.getName());
        }
    }

    public void takeDamage(int damage) {
        this.stats.setHitPoints(this.stats.getHitPoints() - damage);
        this.isAlive = this.stats.getHitPoints() > 0;
    }

    public int getStrenght() {
        return stats.getAttack() + stats.getDefense();
    }
}
