package ch._42lausanne.swingy.model.characters.classes;

import ch._42lausanne.swingy.model.characters.interfaces.CharacterBuilder;
import ch._42lausanne.swingy.model.game.classes.Stats;
import ch._42lausanne.swingy.model.game.enums.ObjectType;
import lombok.Getter;

@Getter
public class BlackSmithBuilder implements CharacterBuilder {
    private Character blackSmith;

    public BlackSmithBuilder() {
        this.blackSmith = new Hero();
    }

    @Override
    public void reset() {
        this.blackSmith = new Hero();
    }

    @Override
    public CharacterBuilder buildName(String heroName) {
        this.blackSmith.setName(heroName);
        return this;
    }

    @Override
    public CharacterBuilder buildType() {
        this.blackSmith.setType(ObjectType.BLACKSMITH);
        return this;
    }

    @Override
    public CharacterBuilder buildLvl() {
        this.blackSmith.setLevel(0);
        return this;
    }

    @Override
    public CharacterBuilder buildStats() {
        this.blackSmith.setStats(Stats.blacksmithStats());
        this.blackSmith.setInitialHp(this.blackSmith.getStats().getHitPoints());
        return this;
    }

    @Override
    public Character getCharacter() {
        return blackSmith;
    }

}
