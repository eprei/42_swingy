package ch._42lausanne.swingy.model.builders;

import ch._42lausanne.swingy.model.characters.Character;
import ch._42lausanne.swingy.model.characters.Hero;
import ch._42lausanne.swingy.model.game.ObjectTypeEnum;
import ch._42lausanne.swingy.model.game.Stats;
import lombok.Getter;

@Getter
public class BlackSmithBuilder implements CharacterBuilder {
    private Character blackSmith;

    public BlackSmithBuilder() {
        blackSmith = new Hero();
    }

    @Override
    public CharacterBuilder reset() {
        blackSmith = new Hero();
        return this;
    }

    @Override
    public CharacterBuilder buildName(String heroName) {
        blackSmith.setName(heroName);
        return this;
    }

    @Override
    public CharacterBuilder buildType() {
        blackSmith.setType(ObjectTypeEnum.BLACKSMITH);
        return this;
    }

    @Override
    public CharacterBuilder buildLvl() {
        blackSmith.setLevel(1);
        return this;
    }

    @Override
    public CharacterBuilder buildExperience() {
        blackSmith.setExperience(0);
        return this;
    }

    @Override
    public CharacterBuilder buildStats() {
        blackSmith.setStats(Stats.blacksmithStats());
        blackSmith.setInitialHp(blackSmith.getStats().getHitPoints());
        return this;
    }

    @Override
    public Character getCharacter() {
        return blackSmith;
    }

}
