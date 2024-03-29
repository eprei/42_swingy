package ch._42lausanne.swingy.model.artifacts.classes;

import ch._42lausanne.swingy.model.game.classes.Stats;

public class Helmet extends Artifact {
    public Helmet() {
        super(ArtifactType.HELMET, Stats.helmetStats());
    }
}
