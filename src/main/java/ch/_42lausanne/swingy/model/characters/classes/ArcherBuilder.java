package ch._42lausanne.swingy.model.characters.classes;

import ch._42lausanne.swingy.model.characters.interfaces.CharacterBuilder;
import ch._42lausanne.swingy.model.game.classes.Stats;
import ch._42lausanne.swingy.model.game.enums.ObjectType;
import ch._42lausanne.swingy.model.utils.classes.NameGenerator;

public class ArcherBuilder implements CharacterBuilder {
    private Character character;

    public ArcherBuilder() {
        this.character = new Hero();
    }

    @Override
    public void reset() {
        this.character = new Hero();
    }

    @Override
    public CharacterBuilder buildName() {
        this.character.setName(NameGenerator.generateRandomName());
        return this;
    }

    @Override
    public CharacterBuilder buildType() {
        this.character.setType(ObjectType.ARCHER);
        return this;
    }

    @Override
    public CharacterBuilder buildLvl() {
        this.character.setLevel(0);
        return this;
    }

    @Override
    public CharacterBuilder buildStats() {
        this.character.setStats(Stats.archerStats());
        return this;
    }

    @Override
    public Character getCharacter() {
        return this.character;
    }
}
