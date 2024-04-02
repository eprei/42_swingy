package ch._42lausanne.swingy.model.builders;

import ch._42lausanne.swingy.model.characters.Character;
import ch._42lausanne.swingy.model.characters.Hero;
import ch._42lausanne.swingy.model.game.ObjectType;
import ch._42lausanne.swingy.model.game.Stats;
import lombok.Getter;

@Getter
public class WarriorBuilder implements CharacterBuilder {
    private Character warrior;

    public WarriorBuilder() {
        this.warrior = new Hero();
    }

    @Override
    public void reset() {
        this.warrior = new Hero();
    }

    @Override
    public CharacterBuilder buildName(String heroName) {
        this.warrior.setName(heroName);
        return this;
    }

    @Override
    public CharacterBuilder buildType() {
        this.warrior.setType(ObjectType.WARRIOR);
        return this;
    }

    @Override
    public CharacterBuilder buildLvl() {
        this.warrior.setLevel(0);
        return this;
    }

    @Override
    public CharacterBuilder buildStats() {
        this.warrior.setStats(Stats.warriorStats());
        this.warrior.setInitialHp(this.warrior.getStats().getHitPoints());
        return this;
    }

    @Override
    public Character getCharacter() {
        return warrior;
    }

}
