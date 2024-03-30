package ch._42lausanne.swingy.model.artifacts.classes;

import ch._42lausanne.swingy.model.game.classes.Stats;

public class Helm extends Artifact {
    public Helm(int villainHitPoints) {
        super(ArtifactType.HELM, Stats.helmStats(villainHitPoints));
    }
}
