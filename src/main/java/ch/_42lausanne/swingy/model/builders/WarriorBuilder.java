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
        warrior = new Hero();
    }

    @Override
    public CharacterBuilder reset() {
        warrior = new Hero();
        return this;
    }

    @Override
    public CharacterBuilder buildName(String heroName) {
        warrior.setName(heroName);
        return this;
    }

    @Override
    public CharacterBuilder buildType() {
        warrior.setType(ObjectType.WARRIOR);
        return this;
    }

    @Override
    public CharacterBuilder buildLvl() {
        warrior.setLevel(1);
        return this;
    }

    @Override
    public CharacterBuilder buildExperience() {
        warrior.setExperience(0);
        return this;
    }

    @Override
    public CharacterBuilder buildStats() {
        warrior.setStats(Stats.warriorStats());
        warrior.setInitialHp(warrior.getStats().getHitPoints());
        return this;
    }

    @Override
    public Character getCharacter() {
        return warrior;
    }

}
