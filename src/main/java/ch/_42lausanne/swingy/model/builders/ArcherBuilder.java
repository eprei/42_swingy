package ch._42lausanne.swingy.model.builders;

import ch._42lausanne.swingy.model.characters.Character;
import ch._42lausanne.swingy.model.characters.Hero;
import ch._42lausanne.swingy.model.game.ObjectType;
import ch._42lausanne.swingy.model.game.Stats;

public class ArcherBuilder implements CharacterBuilder {
    private Character archer;

    public ArcherBuilder() {
        this.archer = new Hero();
    }

    @Override
    public void reset() {
        this.archer = new Hero();
    }

    @Override
    public CharacterBuilder buildName(String heroName) {
        this.archer.setName(heroName);
        return this;
    }

    @Override
    public CharacterBuilder buildType() {
        this.archer.setType(ObjectType.ARCHER);
        return this;
    }

    @Override
    public CharacterBuilder buildLvl() {
        this.archer.setLevel(0);
        return this;
    }

    @Override
    public CharacterBuilder buildExperience() {
        this.archer.setExperience(0);
        return null;
    }

    @Override
    public CharacterBuilder buildStats() {
        this.archer.setStats(Stats.archerStats());
        this.archer.setInitialHp(this.archer.getStats().getHitPoints());
        return this;
    }

    @Override
    public Character getCharacter() {
        return this.archer;
    }
}
