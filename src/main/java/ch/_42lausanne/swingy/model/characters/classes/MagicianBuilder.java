package ch._42lausanne.swingy.model.characters.classes;

import ch._42lausanne.swingy.model.characters.interfaces.CharacterBuilder;
import ch._42lausanne.swingy.model.game.classes.Stats;
import ch._42lausanne.swingy.model.game.enums.ObjectType;
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
