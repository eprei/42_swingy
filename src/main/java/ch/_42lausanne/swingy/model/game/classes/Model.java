package ch._42lausanne.swingy.model.game.classes;

import ch._42lausanne.swingy.model.artifacts.classes.Artifact;
import ch._42lausanne.swingy.model.characters.classes.BlackSmithBuilder;
import ch._42lausanne.swingy.model.characters.classes.CharacterBuilderDirector;
import ch._42lausanne.swingy.model.characters.classes.Hero;
import ch._42lausanne.swingy.model.characters.classes.MagicianBuilder;
import ch._42lausanne.swingy.model.game.enums.Direction;
import ch._42lausanne.swingy.model.game.enums.Phase;
import ch._42lausanne.swingy.view.classes.console.UserMessages;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Model {

    @Getter
    private Hero hero;
    @Getter
    private List<Hero> heroes;
    @Getter
    private Map map;
    @Getter
    private CharacterBuilderDirector builderDirector;
    @Getter
    @Setter
    private Phase phase;

    public Model() {
        heroes = new ArrayList<>();
        builderDirector = new CharacterBuilderDirector(new BlackSmithBuilder());
        builderDirector.buildCharacter();
        Hero blackSmith = (Hero) builderDirector.getCharacter();
        heroes.add(blackSmith);
        builderDirector = new CharacterBuilderDirector(new MagicianBuilder());
        builderDirector.buildCharacter();
        Hero magician = (Hero) builderDirector.getCharacter();
        heroes.add(magician);
        magician.setLevel(2);
        this.phase = Phase.WELCOME;
    }

    public void movingHandler(Direction direction) {
        moveHero(direction);
    }

    private void moveHero(Direction direction) {
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

    public void setHero(Hero hero) {
        this.hero = hero;
        map = new Map(this);
        this.phase = Phase.MAP;
    }

    public void fight() {
        map.doBattle();
    }

    public void searchForDroppedArtifacts() {
        map.searchForDroppedArtifacts();
    }

    public void keepArtifact() {
        Artifact dropedArtifact = map.getBattle().getArtifactDropped();
        hero.setArtifacts(dropedArtifact);
        UserMessages.printArtifactKept(dropedArtifact.getType());

        this.phase = Phase.MAP;
    }
}
