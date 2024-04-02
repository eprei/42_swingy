package ch._42lausanne.swingy.model.artifacts;

import ch._42lausanne.swingy.model.game.Stats;

public class Armor extends Artifact {
    public Armor(int villainDefense) {
        super(ArtifactType.ARMOR, Stats.armorStats(villainDefense));
    }
}
