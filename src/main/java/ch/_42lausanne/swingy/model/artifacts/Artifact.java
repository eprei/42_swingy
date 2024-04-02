package ch._42lausanne.swingy.model.artifacts;

import ch._42lausanne.swingy.model.characters.Character;
import ch._42lausanne.swingy.model.game.Stats;
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
            case 2 -> artifact = new Helm(villain.getInitialHp());
        }

        return artifact;
    }

    @Override
    public String toString() {
        return "type: " + type + "\n" +
                "attack: " + stats.getAttack() + "\n" +
                "defense: " + stats.getDefense() + "\n" +
                "hitPoints: " + stats.getHitPoints() + "\n";
    }
}
