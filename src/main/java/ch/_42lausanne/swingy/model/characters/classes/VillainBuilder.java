package ch._42lausanne.swingy.model.characters.classes;

import ch._42lausanne.swingy.model.characters.interfaces.CharacterBuilder;
import ch._42lausanne.swingy.model.game.classes.Stats;
import ch._42lausanne.swingy.model.game.enums.ObjectType;
import ch._42lausanne.swingy.model.utils.classes.NameGenerator;

public class VillainBuilder implements CharacterBuilder {
    private Character villain;

    public VillainBuilder() {
        this.villain = new Character();
    }

    @Override
    public void reset() {
        this.villain = new Character();
    }

    @Override
    public CharacterBuilder buildName() {
        this.villain.setName(NameGenerator.generateRandomName());
        return this;
    }

    @Override
    public CharacterBuilder buildType() {
        this.villain.setType(ObjectType.VILLAIN);
        return this;
    }

    @Override
    public CharacterBuilder buildLvl() {
        // TODO calculate lvl based on Hero's lvl
        this.villain.setLevel(0);
        return this;
    }

    @Override
    public CharacterBuilder buildStats() {
        // TODO calculate stats based in Villain's lvl
        this.villain.setStats(new Stats(6, 10, 80));
        return this;
    }

    @Override
    public Character getCharacter() {
        return this.villain;
    }
}
