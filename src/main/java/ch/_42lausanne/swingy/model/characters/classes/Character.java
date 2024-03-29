package ch._42lausanne.swingy.model.characters.classes;

import ch._42lausanne.swingy.model.game.classes.Stats;
import ch._42lausanne.swingy.model.game.enums.ObjectType;
import lombok.Data;

import java.awt.*;

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
        return "name: " + name + "\n" +
                "attack: " + stats.getAttack() + "\n" +
                "defense: " + stats.getDefense() + "\n" +
                "hitPoints: " + stats.getHitPoints();
    }
}
