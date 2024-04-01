package ch._42lausanne.swingy.model.game.classes;

import ch._42lausanne.swingy.model.characters.classes.Character;
import ch._42lausanne.swingy.model.characters.classes.Hero;
import ch._42lausanne.swingy.model.characters.classes.VillainBuilder;
import ch._42lausanne.swingy.model.game.enums.ObjectType;
import ch._42lausanne.swingy.model.game.enums.PhasesOfTheGame;
import ch._42lausanne.swingy.model.utils.classes.NameGenerator;
import ch._42lausanne.swingy.view.classes.console.UserMessages;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import model.characters.interfaces.MoveHero;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class Map implements MoveHero {
    @Getter
    private static final int FINAL_MAP = 3;
    private static final double VILLAIN_SPREAD_COEFFICIENT = (double) 1 / 1.5;
    @Setter
    @Getter
    private static int mapId = 0;

    private Hero hero;
    private Dimension mapSize;
    private int[][] mapGrid;
    private List<Character> villains;
    private Battle battle;
    private Model model;
    private int villainsToPlace;

    public Map(Model model) {
        mapId++;
        this.model = model;
        this.hero = model.getHero();
        this.villains = new ArrayList<>();
        setMapSize();
        createMap();
        populateMapWithVillains();
        setHeroPosition();
        hero.restartHp();
        UserMessages.printMapCreated(mapId);
    }

    public boolean maximumLevelReached() {
        return mapId >= FINAL_MAP;
    }

    private void setHeroPosition() {
        int width = mapSize.height / 2;
        int height = mapSize.height / 2;

        hero.setPosition(new Dimension(width, height));
        mapGrid[width][height] = hero.getType().ordinal();
    }

    private void populateMapWithVillains() {
        int villainsPlaced = 0;

        for (int o = 0; o < mapSize.getWidth(); o++) {
            for (int p = 0; p < mapSize.getHeight(); p++) {
                if (o == 0 || o == mapSize.getHeight() - 1
                        || p == 0 || p == mapSize.getWidth() - 1) {
                    mapGrid[o][p] = ObjectType.VILLAIN.ordinal();
                    buildVillains();
                    villainsPlaced++;
                }
            }

            Random random = new Random();
            int i = random.nextInt((int) mapSize.getHeight());
            int j = random.nextInt((int) mapSize.getWidth());
            if (mapGrid[i][j] == ObjectType.EMPTY_SPACE.ordinal()) {
                mapGrid[i][j] = ObjectType.VILLAIN.ordinal();
                buildVillains();
                villainsPlaced++;
            }
        }
    }

    private void buildVillains() {
        // TODO vary stats of the villains
        model.getBuilderDirector().setCharacterBuilder(new VillainBuilder());
        model.getBuilderDirector().buildCharacter(NameGenerator.generateRandomName());
        Character villain = model.getBuilderDirector().getCharacter();
        setVillainStats(villain);
//        System.out.printf(villain.toString());
        villains.add(villain);
    }

    private void setVillainStats(Character villain) {
        int enemyAttack = (int) (hero.getStats().getAttack() * VILLAIN_SPREAD_COEFFICIENT);
        int enemyDefense = (int) (hero.getStats().getDefense() * VILLAIN_SPREAD_COEFFICIENT);
        int enemyHitPoints = (int) (hero.getStats().getHitPoints() * VILLAIN_SPREAD_COEFFICIENT);

        Stats enemyStats = new Stats(enemyAttack, enemyDefense, enemyHitPoints);

        villain.setStats(enemyStats);
        villain.setInitialHp(enemyHitPoints);
    }

    private void setMapSize() {
        int width = (hero.getLevel() - 1) * 5 + 10;
        int height = (hero.getLevel() - 1) * 5 + 10;
        mapSize = new Dimension();
        mapSize.setSize(width, height);
        villainsToPlace = (int) (width * height * VILLAIN_SPREAD_COEFFICIENT);
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
                String charToPrint = switch (mapGrid[j][i]) {
                    case 0 -> " ";
                    case 1, 2, 3, 4 -> "h";
                    case 5 -> "v";
                    default -> throw new IllegalStateException("Unexpected value: " + mapGrid[j][i]);
                };
                System.out.printf("| %s ", charToPrint);
            }
            System.out.println("|");
        }
        System.out.println();
    }

    private void checkAndMoveHero(int widthVariation, int heightVariation) {
        int oldWidth = hero.getPosition().width;
        int oldHeight = hero.getPosition().height;
        int newWidth = oldWidth + widthVariation;
        int newHeight = oldHeight + heightVariation;

        checkMapBoundaries(newWidth, newHeight);

        if (model.getPhase() == PhasesOfTheGame.WIN_MAP) {
            return;
        }

        checkVillains(newWidth, newHeight);

        if (model.getPhase() == PhasesOfTheGame.FIGHT_OR_RUN) {
            return;
        }

        moveHeroToEmptySquare(oldWidth, oldHeight, newWidth, newHeight);
    }

    private void moveHeroToEmptySquare(int oldWidth, int oldHeight, int newWidth, int newHeight) {
        mapGrid[oldWidth][oldHeight] = ObjectType.EMPTY_SPACE.ordinal();
        mapGrid[newWidth][newHeight] = hero.getType().ordinal();
        hero.setPosition(new Dimension(newWidth, newHeight));
    }

    private void checkMapBoundaries(int newWidth, int newHeight) {
        if (0 > newWidth || mapSize.width <= newWidth || 0 > newHeight || mapSize.height <= newHeight) {
            model.setPhase(PhasesOfTheGame.WIN_MAP);
        }
    }

    private void checkVillains(int newWidth, int newHeight) {
        if (mapGrid[newWidth][newHeight] == ObjectType.VILLAIN.ordinal()) {
            battle = new Battle(hero, villains, newWidth, newHeight);
            model.setPhase(PhasesOfTheGame.FIGHT_OR_RUN);
        }
    }

    @Override
    public void moveHeroToNorth() {
        checkAndMoveHero(0, -1);
    }

    @Override
    public void moveHeroToEast() {
        checkAndMoveHero(1, 0);
    }

    @Override
    public void moveHeroToSouth() {
        checkAndMoveHero(0, 1);
    }

    @Override
    public void moveHeroToWest() {
        checkAndMoveHero(-1, 0);
    }

    public void doTheBattle(boolean heroAttacksFirst) {
        battle.simulateBattle(heroAttacksFirst);

        if (hero.getIsAlive()) {
            moveHeroToTheWonSquare();
            model.setPhase(PhasesOfTheGame.WIN_BATTLE);
        } else {
            hero.restartHp();
            model.setPhase(PhasesOfTheGame.LOOSE_BATTLE);
        }
    }

    private void moveHeroToTheWonSquare() {
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
            model.setPhase(PhasesOfTheGame.MAP);
        } else {
            model.setPhase(PhasesOfTheGame.ARTIFACT_DROPPED);
        }
    }

    public void successfulEscapeFromBattle() {
        battle = null;
    }
}
