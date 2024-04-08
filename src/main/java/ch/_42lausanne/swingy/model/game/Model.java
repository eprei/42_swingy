package ch._42lausanne.swingy.model.game;

import ch._42lausanne.swingy.model.characters.Hero;

import java.util.List;

public interface Model {
    PhasesOfTheGame getPhase();

    void setPhase(PhasesOfTheGame phase);

    Map getMap();

    List<Hero> getHeroes();

    Hero getSelectedHero();

    void movingHandler(Direction direction);

    void selectHero(int heroIndex);

    void fight(boolean heroAttacksFirst);

    void searchForDroppedArtifacts();

    void keepArtifact();

    void tryToRunFromBattle();

    void createNewHero(String heroName, ObjectType heroType);

    void createNextMap();

    boolean maximumLevelReached();
}