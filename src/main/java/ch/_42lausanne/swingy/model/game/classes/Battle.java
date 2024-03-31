package ch._42lausanne.swingy.model.game.classes;

import ch._42lausanne.swingy.model.artifacts.classes.Artifact;
import ch._42lausanne.swingy.model.characters.classes.Character;
import ch._42lausanne.swingy.model.characters.classes.Hero;
import ch._42lausanne.swingy.model.utils.classes.RandomnessGenerator;
import ch._42lausanne.swingy.view.classes.console.UserMessages;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class Battle {
    private final Hero hero;
    private final List<Character> villains;
    @Getter
    private Character villain;
    @Getter
    private Dimension battleCoordinates;
    @Getter
    private Artifact artifactDropped;

    public Battle(Hero hero, List<Character> villains, int newWidth, int newHeight) {
        this.hero = hero;
        this.villains = villains;
        battleCoordinates = new Dimension(newWidth, newHeight);
        Collections.shuffle(villains);
        villain = villains.getFirst();
    }

    private void performBattleRounds(boolean heroAttacksFirst) {
        Character[] contenders = getContendersOrder(heroAttacksFirst);

        while (contenders[0].getIsAlive() && contenders[1].getIsAlive()) {
            contenders[0].attackEnemy(contenders[1]);
            if (contenders[1].getIsAlive()) {
                contenders[1].attackEnemy(contenders[0]);
            }
        }
    }

    @NotNull
    private Character[] getContendersOrder(boolean heroAttacksFirst) {
        Character[] contenders = new Character[2];

        if (heroAttacksFirst) {
            contenders[0] = this.hero;
            contenders[1] = this.villain;
        } else {
            contenders[0] = this.villain;
            contenders[1] = this.hero;
        }
        return contenders;
    }

    private void findWinner() {
        if (hero.getIsAlive()) {
            UserMessages.printYouWinTheBattle();
            hero.gainExperience(villain);
            villains.remove(villain);
            dropArtifact();
        } else {
            UserMessages.printYouLoseTheBattle();
        }
    }

    public void simulateBattle(boolean heroAttacksFirst) {
        performBattleRounds(heroAttacksFirst);
        findWinner();
    }

    private void dropArtifact() {
        if (RandomnessGenerator.rollDice(0.5)) {
            artifactDropped = Artifact.buildRandomArtifact(villain);
        }
    }
    
}
