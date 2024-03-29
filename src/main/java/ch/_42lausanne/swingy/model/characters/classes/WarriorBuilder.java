package ch._42lausanne.swingy.model.characters.classes;

import ch._42lausanne.swingy.model.characters.interfaces.CharacterBuilder;
import ch._42lausanne.swingy.model.game.classes.Stats;
import ch._42lausanne.swingy.model.game.enums.ObjectType;
import ch._42lausanne.swingy.model.utils.classes.NameGenerator;
import lombok.Getter;

@Getter
public class WarriorBuilder implements CharacterBuilder {
    private Character character;

    public WarriorBuilder() {
        this.character = new Hero();
    }

    @Override
    public void reset() {
        this.character = new Hero();
    }

    @Override
    public CharacterBuilder buildName() {
        this.character.setName(NameGenerator.generateRandomName());
        return this;
    }

    @Override
    public CharacterBuilder buildType() {
        this.character.setType(ObjectType.WARRIOR);
        return this;
    }

    @Override
    public CharacterBuilder buildLvl() {
        this.character.setLevel(0);
        return this;
    }

    @Override
    public CharacterBuilder buildStats() {
        this.character.setStats(Stats.warriorStats());
        return this;
    }

}
