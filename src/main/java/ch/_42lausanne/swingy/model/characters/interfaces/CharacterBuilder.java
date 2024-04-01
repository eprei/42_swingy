package ch._42lausanne.swingy.model.characters.interfaces;

import ch._42lausanne.swingy.model.characters.classes.Character;


public interface CharacterBuilder {
    void reset();

    CharacterBuilder buildName(String heroName);

    CharacterBuilder buildType();

    CharacterBuilder buildLvl();

    CharacterBuilder buildStats();

    Character getCharacter();
}
