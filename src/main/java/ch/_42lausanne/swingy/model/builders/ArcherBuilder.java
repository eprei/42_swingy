package ch._42lausanne.swingy.model.builders;

import ch._42lausanne.swingy.model.characters.Character;
import ch._42lausanne.swingy.model.characters.Hero;
import ch._42lausanne.swingy.model.game.ObjectTypeEnum;
import ch._42lausanne.swingy.model.game.Stats;

public class ArcherBuilder implements CharacterBuilder {
    private Character archer;

    public ArcherBuilder() {
        archer = new Hero();
    }

    @Override
    public CharacterBuilder reset() {
        archer = new Hero();
        return this;
    }

    @Override
    public CharacterBuilder buildName(String heroName) {
        archer.setName(heroName);
        return this;
    }

    @Override
    public CharacterBuilder buildType() {
        archer.setType(ObjectTypeEnum.ARCHER);
        return this;
    }

    @Override
    public CharacterBuilder buildLvl() {
        archer.setLevel(1);
        return this;
    }

    @Override
    public CharacterBuilder buildExperience() {
        archer.setExperience(0);
        return this;
    }

    @Override
    public CharacterBuilder buildStats() {
        archer.setStats(Stats.archerStats());
        archer.setInitialHp(archer.getStats().getHitPoints());
        return this;
    }

    @Override
    public Character getCharacter() {
        return archer;
    }
}
