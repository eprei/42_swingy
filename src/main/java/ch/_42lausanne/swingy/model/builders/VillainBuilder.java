package ch._42lausanne.swingy.model.builders;

import ch._42lausanne.swingy.model.characters.Character;
import ch._42lausanne.swingy.model.game.ObjectType;
import ch._42lausanne.swingy.model.game.Stats;

public class VillainBuilder implements CharacterBuilder {
    private Character villain;

    public VillainBuilder() {
        villain = new Character();
    }

    @Override
    public CharacterBuilder reset() {
        villain = new Character();
        return this;
    }

    @Override
    public CharacterBuilder buildName(String heroName) {
        villain.setName(heroName);
        return this;
    }

    @Override
    public CharacterBuilder buildType() {
        villain.setType(ObjectType.VILLAIN);
        return this;
    }

    @Override
    public CharacterBuilder buildLvl() {
        villain.setLevel(0);
        return this;
    }

    @Override
    public CharacterBuilder buildExperience() {
        villain.setExperience(0);
        return this;
    }

    @Override
    public CharacterBuilder buildStats() {
        villain.setStats(new Stats(6, 10, 80));
        return this;
    }

    @Override
    public Character getCharacter() {
        return villain;
    }
}
