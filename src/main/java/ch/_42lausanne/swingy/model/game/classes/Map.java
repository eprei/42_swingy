package ch._42lausanne.swingy.model.game.classes;

import ch._42lausanne.swingy.model.characters.classes.Character;
import ch._42lausanne.swingy.model.characters.classes.Hero;
import ch._42lausanne.swingy.model.characters.classes.VillainBuilder;
import ch._42lausanne.swingy.model.game.enums.ObjectType;
import ch._42lausanne.swingy.model.game.enums.Phase;
import lombok.Data;
import model.characters.interfaces.MoveHero;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class Map implements MoveHero {
    private Hero hero;
    private Dimension mapSize;
    private int[][] mapGrid;
    private List<Character> villains;
    private Battle battle;
    private Model model;

    public Map(Model model) {
        this.model = model;
        this.hero = model.getHero();
        this.villains = new ArrayList<>();
        setMapSize();
        createMap();
        populateMapWithVillains();
        setHeroPosition();
    }

    private void setHeroPosition() {
        int width = mapSize.height / 2;
        int height = mapSize.height / 2;

        hero.setPosition(new Dimension(width, height));
        mapGrid[width][height] = hero.getType().ordinal();
    }

    private void populateMapWithVillains() {
        model.getBuilderDirector().setCharacterBuilder(new VillainBuilder());

        for (int i = 0; i < mapSize.getHeight(); i++) {
            for (int j = 0; j < mapSize.getWidth(); j++) {
                if ((i + j) % 3 == 0) {
                    mapGrid[i][j] = ObjectType.VILLAIN.ordinal();
                    // TODO create villains of varying power
                    model.getBuilderDirector().buildCharacter();
                    Character villain = model.getBuilderDirector().getCharacter();
                    villains.add(villain);
                }
            }
        }
    }

    private void setMapSize() {
        int width = (hero.getLevel() - 1) * 5 + 10;
        int height = (hero.getLevel() - 1) * 5 + 10;
        mapSize = new Dimension();
        mapSize.setSize(width, height);
    }

    private void createMap() {
        mapGrid = new int[(int) mapSize.width][(int) mapSize.height];

        for (int i = 0; i < mapSize.getHeight(); i++) {
            for (int j = 0; j < mapSize.getWidth(); j++) {
                mapGrid[i][j] = ObjectType.EMPTY_SPACE.ordinal();
            }
        }
    }

    public void printMap() {
        for (int i = 0; i < mapSize.getWidth(); i++) {
            for (int j = 0; j < mapSize.getHeight(); j++) {
                System.out.printf("| %s ", mapGrid[j][i]);
            }
            System.out.println("|");
        }
        System.out.println();
    }

    private void verifyDirection(int widthVariation, int heightVariation) {
        int oldWidth = hero.getPosition().width;
        int oldHeight = hero.getPosition().height;
        int newWidth = oldWidth + widthVariation;
        int newHeight = oldHeight + heightVariation;

        checkMapBoundaries(newWidth, newHeight);

        if (model.getPhase() != Phase.WIN_MAP) {
            checkVillains(newWidth, newHeight);
            mapGrid[oldWidth][oldHeight] = ObjectType.EMPTY_SPACE.ordinal();
            mapGrid[newWidth][newHeight] = hero.getType().ordinal();
            hero.setPosition(new Dimension(newWidth, newHeight));
        }
    }

    private void checkMapBoundaries(int newWidth, int newHeight) {
        if (0 > newWidth || mapSize.width <= newWidth || 0 > newHeight || mapSize.height <= newHeight) {
            model.setPhase(Phase.WIN_MAP);
        }
    }

    private void checkVillains(int newWidth, int newHeight) {
        if (mapGrid[newWidth][newHeight] == ObjectType.VILLAIN.ordinal()) {
            battle = new Battle(hero, villains, newWidth, newHeight);
            model.setPhase(Phase.FIGHT_OR_RUN);
        }
    }

    @Override
    public void moveHeroToNorth() {
        verifyDirection(0, -1);
    }

    @Override
    public void moveHeroToEast() {
        verifyDirection(1, 0);
    }

    @Override
    public void moveHeroToSouth() {
        verifyDirection(0, 1);
    }

    @Override
    public void moveHeroToWest() {
        verifyDirection(-1, 0);
    }

    public void doBattle() {
        battle.simulateBattle();

        if (hero.getIsAlive()) {
            updateHeroPosition();
            model.setPhase(Phase.WIN_BATTLE);
        } else {
            model.setPhase(Phase.LOOSE_BATTLE);
        }
    }

    private void updateHeroPosition() {
        int oldWidth = hero.getPosition().width;
        int oldHeight = hero.getPosition().height;
        int newWidth = battle.getBattleCoordinates().width;
        int newHeight = battle.getBattleCoordinates().height;

        mapGrid[oldWidth][oldHeight] = ObjectType.EMPTY_SPACE.ordinal();
        mapGrid[newWidth][newHeight] = hero.getType().ordinal();
        hero.setPosition(new Dimension(newWidth, newHeight));
    }

    public void searchForDroppedArtifacts() {
        if (battle.getArtifactDropped() == null) {
            model.setPhase(Phase.MAP);
        } else {
            model.setPhase(Phase.ARTIFACT_DROPPED);
        }
    }
}
