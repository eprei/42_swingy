package ch._42lausanne.swingy.model.builders;

import ch._42lausanne.swingy.model.characters.Character;
import ch._42lausanne.swingy.model.game.Stats;
import ch._42lausanne.swingy.model.game.ObjectType;

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
    public CharacterBuilder buildName(String heroName) {
        this.villain.setName(heroName);
        return this;
    }

    @Override
    public CharacterBuilder buildType() {
        this.villain.setType(ObjectType.VILLAIN);
        return this;
    }

    @Override
    public CharacterBuilder buildLvl() {
        this.villain.setLevel(0);
        return this;
    }

    @Override
    public CharacterBuilder buildStats() {
        this.villain.setStats(new Stats(6, 10, 80));
        return this;
    }

    @Override
    public Character getCharacter() {
        return this.villain;
    }
}
