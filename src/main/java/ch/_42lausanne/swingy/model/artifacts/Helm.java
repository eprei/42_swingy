package ch._42lausanne.swingy.model.artifacts;

import ch._42lausanne.swingy.model.game.Stats;

public class Helm extends Artifact {
    public Helm(int villainInitialHitPoints) {
        super(ArtifactType.HELM, Stats.helmStats(villainInitialHitPoints));
    }
}
