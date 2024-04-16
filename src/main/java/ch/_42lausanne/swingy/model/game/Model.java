package ch._42lausanne.swingy.model.game;

import ch._42lausanne.swingy.model.artifacts.Artifact;
import ch._42lausanne.swingy.model.characters.Hero;

import java.util.List;

public interface Model {

    List<Hero> getHeroes();

    String getVillain();

    Artifact getDroppedArtifact();

    void printMap();

    void movingHandler(Direction direction);

    void selectHero(int heroIndex);

    void fight(boolean heroAttacksFirst);

    void searchForDroppedArtifacts();

    void keepArtifact();

    void tryToRunFromBattle();

    void createNewHero(String heroName, ObjectTypeEnum heroType);

    void createNextMap();

    void goToNextMap();

    Map getMap();
}
