package ch._42lausanne.swingy.model.game.classes;

import ch._42lausanne.swingy.model.characters.classes.Character;
import ch._42lausanne.swingy.model.characters.classes.Hero;
import lombok.Getter;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class Battle {
    private Hero hero;
    private List<Character> villains;
    @Getter
    private Character villain;
    @Getter
    private Dimension battleCoordinates;

    public Battle(Hero hero, List<Character> villains, int newWidth, int newHeight) {
        this.hero = hero;
        this.villains = villains;
        battleCoordinates = new Dimension(newWidth, newHeight);
        Collections.shuffle(villains);
        villain = villains.getFirst();
    }

    public boolean isTheHeroTheWinner() {
        villains.remove(villain);
        return true;
    }
}
