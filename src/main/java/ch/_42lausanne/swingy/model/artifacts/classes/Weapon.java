package ch._42lausanne.swingy.model.artifacts.classes;

import ch._42lausanne.swingy.model.game.classes.Stats;

public class Weapon extends Artifact {
    public Weapon(int villainAttack) {
        super(ArtifactType.WEAPON, Stats.weaponStats(villainAttack));
    }
}
