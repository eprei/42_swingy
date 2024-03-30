package ch._42lausanne.swingy.model.artifacts.classes;

import ch._42lausanne.swingy.model.characters.classes.Character;
import ch._42lausanne.swingy.model.game.classes.Stats;
import lombok.Data;

import java.util.Random;

@Data
public class Artifact {

    private final ArtifactType type;
    private final Stats stats;

    public Artifact(ArtifactType artifactType, Stats stats) {
        this.type = artifactType;
        this.stats = stats;
    }

    public static Artifact buildRandomArtifact(Character villain) {
        Random random = new Random();
        int randomSeed = random.nextInt(3);
        Artifact artifact = null;

        switch (randomSeed) {
            case 0 -> artifact = new Weapon(villain.getStats().getAttack());
            case 1 -> artifact = new Armor(villain.getStats().getDefense());
            case 2 -> artifact = new Helm(villain.getStats().getHitPoints());
        }

        return artifact;
    }

    @Override
    public String toString() {
        return "type: " + type +
                "\nattack: " + stats.getAttack() +
                "\ndefense: " + stats.getDefense() +
                "\nhitPoints: " + stats.getHitPoints()
                + "\n";
    }
}
