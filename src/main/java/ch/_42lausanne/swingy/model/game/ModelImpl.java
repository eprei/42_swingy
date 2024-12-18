package ch._42lausanne.swingy.model.game;

import ch._42lausanne.swingy.model.artifacts.Artifact;
import ch._42lausanne.swingy.model.builders.*;
import ch._42lausanne.swingy.model.characters.Hero;
import ch._42lausanne.swingy.model.util.RandomnessGenerator;
import ch._42lausanne.swingy.service.HeroService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("modelImpl")
@Data
public class ModelImpl implements Model {

    private final CharacterBuilderDirector builderDirector;
    private final Game game;
    private HeroService heroService;
    private Hero hero;
    private Map map;

    @Autowired
    public ModelImpl(HeroService heroService, CharacterBuilderDirector builderDirector, Game game) {
        this.heroService = heroService;
        this.builderDirector = builderDirector;
        this.game = game;
    }

    @Override
    public List<Hero> getHeroes() {
        return heroService.findAll();
    }

    @Override
    public String getVillain() {
        return map.getBattle().getVillain().toString();
    }

    @Override
    public Artifact getDroppedArtifact() {
        return map.getBattle().getDroppedArtifact();
    }

    @Override
    public void printMap() {
        map.printMap();
    }

    @Override
    public void movingHandler(Direction direction) {
        switch (direction) {
            case Direction.NORTH:
                map.moveHeroToNorth();
                break;
            case Direction.EAST:
                map.moveHeroToEast();
                break;
            case Direction.SOUTH:
                map.moveHeroToSouth();
                break;
            case Direction.WEST:
                map.moveHeroToWest();
                break;
        }
    }

    @Override
    public void selectHero(int heroIndex) {
        hero = heroService.findAll().get(heroIndex);
        Map.setMapId(0);
        map = new Map(this);
    }

    @Override
    public void fight(boolean heroAttacksFirst) {
        map.doTheBattle(heroAttacksFirst);
        if (game.getPhase() == Game.Phase.LOOSE_BATTLE) {
            heroService.save(hero);
        }
    }

    @Override
    public void searchForDroppedArtifacts() {
        map.searchForDroppedArtifacts();
    }

    @Override
    public void keepArtifact() {
        Artifact dropedArtifact = map.getBattle().getDroppedArtifact();
        hero.setArtifact(dropedArtifact);
    }

    @Override
    public void tryToRunFromBattle() {
        if (RandomnessGenerator.rollDice(0.5)) {
            map.successfulEscapeFromBattle();
            game.setPhase(Game.Phase.RUN_SUCCESSFUL);
        } else {
            game.setPhase(Game.Phase.RUN_FAILED);
        }
    }

    @Override
    public void createNewHero(String heroName, ObjectTypeEnum heroType) {
        switch (heroType) {
            case ARCHER -> builderDirector.setCharacterBuilder(new ArcherBuilder());
            case BLACKSMITH -> builderDirector.setCharacterBuilder(new BlackSmithBuilder());
            case WARRIOR -> builderDirector.setCharacterBuilder(new WarriorBuilder());
            case MAGICIAN -> builderDirector.setCharacterBuilder(new MagicianBuilder());
        }

        builderDirector.buildCharacter(heroName);
        Hero newHero = (Hero) builderDirector.getCharacter();
        heroService.save(newHero);
    }

    @Override
    public void createNextMap() {
        map = new Map(this);
    }

    @Override
    public void goToNextMap() {
        if (map.maximumLevelReached()) {
            heroService.save(hero);
            game.setPhase(Game.Phase.WIN_GAME);
        } else {
            createNextMap();
            game.setPhase(Game.Phase.MAP);
        }
    }

    @Override
    public Map getMap() {
        return map;
    }
}
