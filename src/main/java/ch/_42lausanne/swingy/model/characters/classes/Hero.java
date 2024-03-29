package ch._42lausanne.swingy.model.characters.classes;

import ch._42lausanne.swingy.model.artifacts.classes.Artifact;
import ch._42lausanne.swingy.model.characters.interfaces.Combatant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import model.characters.interfaces.Runner;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Hero extends Character implements Combatant, Runner {
    protected List<Artifact> artifacts;

    public Hero() {
        this.artifacts = new ArrayList<>();
    }

    @Override
    public void fight(Character villain) {
        // TODO implement
    }

    @Override
    public void run() {
        // TODO implement
    }
}
