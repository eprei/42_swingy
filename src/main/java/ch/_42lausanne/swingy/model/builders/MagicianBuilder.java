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
        this.magician = new Hero();
    }

    @Override
    public void reset() {
        this.magician = new Hero();
    }

    @Override
    public CharacterBuilder buildName(String heroName) {
        this.magician.setName(heroName);
        return this;
    }

    @Override
    public CharacterBuilder buildType() {
        this.magician.setType(ObjectType.MAGICIAN);
        return this;
    }

    @Override
    public CharacterBuilder buildLvl() {
        this.magician.setLevel(0);
        return this;
    }

    @Override
    public CharacterBuilder buildStats() {
        this.magician.setStats(Stats.magicianStats());
        this.magician.setInitialHp(this.magician.getStats().getHitPoints());
        return this;
    }

    @Override
    public Character getCharacter() {
        return magician;
    }

}
