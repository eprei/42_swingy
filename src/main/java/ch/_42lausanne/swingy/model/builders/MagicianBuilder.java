package ch._42lausanne.swingy.model.builders;

import ch._42lausanne.swingy.model.characters.Character;
import ch._42lausanne.swingy.model.characters.Hero;
import ch._42lausanne.swingy.model.game.ObjectType;
import ch._42lausanne.swingy.model.game.Stats;
import lombok.Getter;

@Getter
public class MagicianBuilder implements CharacterBuilder {
    private Character magician;

    public MagicianBuilder() {
        magician = new Hero();
    }

    @Override
    public CharacterBuilder reset() {
        magician = new Hero();
        return this;
    }

    @Override
    public CharacterBuilder buildName(String heroName) {
        magician.setName(heroName);
        return this;
    }

    @Override
    public CharacterBuilder buildType() {
        magician.setType(ObjectType.MAGICIAN);
        return this;
    }

    @Override
    public CharacterBuilder buildLvl() {
        magician.setLevel(0);
        return this;
    }

    @Override
    public CharacterBuilder buildExperience() {
        magician.setExperience(0);
        return this;
    }

    @Override
    public CharacterBuilder buildStats() {
        magician.setStats(Stats.magicianStats());
        magician.setInitialHp(magician.getStats().getHitPoints());
        return this;
    }

    @Override
    public Character getCharacter() {
        return magician;
    }

}
