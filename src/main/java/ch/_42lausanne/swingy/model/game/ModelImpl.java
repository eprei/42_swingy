package ch._42lausanne.swingy.model.game;

import ch._42lausanne.swingy.model.artifacts.Artifact;
import ch._42lausanne.swingy.model.builders.*;
import ch._42lausanne.swingy.model.characters.Hero;
import ch._42lausanne.swingy.model.util.RandomnessGenerator;
import ch._42lausanne.swingy.service.HeroService;
import ch._42lausanne.swingy.view.console.UserMessages;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("modelImpl")
@Data
public class ModelImpl implements Model {

    private final List<Hero> heroes;
    private final CharacterBuilderDirector builderDirector;
    private HeroService heroService;
    private Hero selectedHero;
    private Map map;
    private PhasesOfTheGame phase;


    @Autowired
    public ModelImpl(HeroService heroService, CharacterBuilderDirector builderDirector) {
        this.heroService = heroService;
        this.builderDirector = builderDirector;
        this.heroes = new ArrayList<>();
        this.phase = PhasesOfTheGame.WELCOME;
    }

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

    public void selectHero(int heroIndex) {
        this.selectedHero = heroes.get(heroIndex);
        UserMessages.printHeroChoice(this.selectedHero.getName());
        Map.setMapId(0);
        map = new Map(this);
    }

    public void fight(boolean heroAttacksFirst) {
        map.doTheBattle(heroAttacksFirst);
    }

    public void searchForDroppedArtifacts() {
        map.searchForDroppedArtifacts();
    }

    public void keepArtifact() {
        Artifact dropedArtifact = map.getBattle().getArtifactDropped();
        selectedHero.setArtifact(dropedArtifact);
        UserMessages.printArtifactKept(dropedArtifact.getType());
    }

    public void tryToRunFromBattle() {
        if (RandomnessGenerator.rollDice(0.5)) {
            map.successfulEscapeFromBattle();
            setPhase(PhasesOfTheGame.RUN_SUCCESSFUL);
        } else {
            setPhase(PhasesOfTheGame.RUN_FAILED);
        }
    }

    public void createNewHero(String heroName, ObjectType heroType) {
        switch (heroType) {
            case ARCHER -> builderDirector.setCharacterBuilder(new ArcherBuilder());
            case BLACKSMITH -> builderDirector.setCharacterBuilder(new BlackSmithBuilder());
            case WARRIOR -> builderDirector.setCharacterBuilder(new WarriorBuilder());
            case MAGICIAN -> builderDirector.setCharacterBuilder(new MagicianBuilder());
        }

        builderDirector.buildCharacter(heroName);
        Hero newHero = (Hero) builderDirector.getCharacter();
        heroes.add(newHero);
        heroService.save(newHero);
        UserMessages.printHeroSuccessfullyCreated(newHero);
    }

    public void createNextMap() {
        map = new Map(this);
    }

    public boolean maximumLevelReached() {
        return map.maximumLevelReached();
    }
}

