package ch._42lausanne.swingy.model.artifacts.classes;

import ch._42lausanne.swingy.model.game.classes.Stats;
import lombok.Data;

@Data
public class Artifact {

    private final ArtifactType type;
    private final Stats stats;

    public Artifact(ArtifactType artifactType, Stats stats) {
        this.type = artifactType;
        this.stats = stats;
    }
}
