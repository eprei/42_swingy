package ch._42lausanne.swingy.model.artifacts.classes;

import ch._42lausanne.swingy.model.game.classes.Stats;

public class Armor extends Artifact {
    public Armor() {
        super(ArtifactType.ARMOR, Stats.armorStats());
    }
}