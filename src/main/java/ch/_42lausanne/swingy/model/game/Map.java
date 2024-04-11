package ch._42lausanne.swingy.model.game;

import ch._42lausanne.swingy.model.builders.VillainBuilder;
import ch._42lausanne.swingy.model.characters.Character;
import ch._42lausanne.swingy.model.characters.Hero;
import ch._42lausanne.swingy.model.util.NameGenerator;
import ch._42lausanne.swingy.view.console.ConsoleColors;
import ch._42lausanne.swingy.view.console.UserMessages;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class Map implements MoveHero {
    @Getter
    private static final int FINAL_MAP = 1;
    private static final double VILLAIN_SPREAD_COEFFICIENT = (double) 1 / 1.4;
    private static final double VILLAIN_POWER_COEFFICIENT = VILLAIN_SPREAD_COEFFICIENT / 1.2;
    @Setter
    @Getter
    private static int mapId = 0;
    private final Game game;
    private Hero hero;
    private Dimension mapSize;
    private int[][] mapGrid;
    private List<Character> villains;
    private Battle battle;
    private ModelImpl model;
    private int villainsToPlace;


    public Map(ModelImpl model) {
        mapId++;
        this.model = model;
        this.game = this.model.getGame();
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

        // Addition of villains all over the map contour
        for (int i = 0; i < mapSize.getWidth(); i++) {
            for (int j = 0; j < mapSize.getHeight(); j++) {
                if (i == 0 || i == mapSize.getHeight() - 1
                        || j == 0 || j == mapSize.getWidth() - 1) {
                    mapGrid[i][j] = ObjectType.VILLAIN.ordinal();
                    buildVillains();
                    villainsToPlace--;
                }
            }
        }

        // Addition of villains randomly within the rest of the map
        while (villainsToPlace >= 0) {
            Random random = new Random();
            int x = random.nextInt((int) mapSize.getHeight());
            int y = random.nextInt((int) mapSize.getWidth());
            if (mapGrid[x][y] == ObjectType.EMPTY_SPACE.ordinal()) {
                mapGrid[x][y] = ObjectType.VILLAIN.ordinal();
                buildVillains();
                villainsToPlace--;
            }
        }
    }

    private void buildVillains() {
        // TODO vary stats of the villains
        model.getBuilderDirector().setCharacterBuilder(new VillainBuilder());
        model.getBuilderDirector().buildCharacter(NameGenerator.generateRandomName());
        Character villain = model.getBuilderDirector().getCharacter();
        setVillainStats(villain);
        villains.add(villain);
    }

    private void setVillainStats(Character villain) {
        int enemyAttack = (int) (hero.getStats().getAttack() * VILLAIN_POWER_COEFFICIENT);
        int enemyDefense = (int) (hero.getStats().getDefense() * VILLAIN_POWER_COEFFICIENT);
        int enemyHitPoints = (int) (hero.getStats().getHitPoints() * VILLAIN_POWER_COEFFICIENT);

        Stats enemyStats = new Stats(enemyAttack, enemyDefense, enemyHitPoints);

        villain.setStats(enemyStats);
        villain.setInitialHp(enemyHitPoints);
    }

    private void setMapSize() {
        int width = (hero.getLevel() - 1) * 5 + 10 - 1;
        int height = (hero.getLevel() - 1) * 5 + 10 - 1;
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
                    case 1, 2, 3, 4 -> ConsoleColors.GREEN + "h" + ConsoleColors.RESET;
                    case 5 -> ConsoleColors.RED + "v" + ConsoleColors.RESET;
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

        if (game.getPhase() == Game.Phase.WIN_MAP) {
            return;
        }

        checkVillains(newWidth, newHeight);

        if (game.getPhase() == Game.Phase.FIGHT_OR_RUN) {
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
            game.setPhase(Game.Phase.WIN_MAP);
        }
    }

    private void checkVillains(int newWidth, int newHeight) {
        if (mapGrid[newWidth][newHeight] == ObjectType.VILLAIN.ordinal()) {
            battle = new Battle(hero, villains, newWidth, newHeight);
            game.setPhase(Game.Phase.FIGHT_OR_RUN);
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
            game.setPhase(Game.Phase.WIN_BATTLE);
        } else {
            game.setPhase(Game.Phase.LOOSE_BATTLE);
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
        if (battle.getDroppedArtifact() == null) {
            game.setPhase(Game.Phase.MAP);
        } else {
            game.setPhase(Game.Phase.ARTIFACT_DROPPED);
        }
    }

    public void successfulEscapeFromBattle() {
        battle = null;
    }
}
