package ch._42lausanne.swingy.model.builders;


import ch._42lausanne.swingy.model.characters.Character;

public interface CharacterBuilder {
    void reset();

    CharacterBuilder buildName(String heroName);

    CharacterBuilder buildType();

    CharacterBuilder buildLvl();

    CharacterBuilder buildStats();

    Character getCharacter();
}
