package ch._42lausanne.swingy.model.artifacts;

import ch._42lausanne.swingy.model.game.Stats;

public class Weapon extends Artifact {
    public Weapon(int villainAttack) {
        super(ArtifactType.WEAPON, Stats.weaponStats(villainAttack));
    }
}
