package ch._42lausanne.swingy.model.game;

import ch._42lausanne.swingy.model.artifacts.Artifact;
import ch._42lausanne.swingy.model.characters.Character;
import ch._42lausanne.swingy.model.characters.Hero;
import ch._42lausanne.swingy.model.util.RandomnessGenerator;
import ch._42lausanne.swingy.view.console.UserMessages;
import lombok.Getter;

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
    private Artifact droppedArtifact;

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
//            TimerSimulator.Sleep(50);
        }
    }


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
        // TODO debug blacksmith loop when fighting
    }

    private void dropArtifact() {
        if (RandomnessGenerator.rollDice(0.5)) {
            droppedArtifact = Artifact.buildRandomArtifact(villain);
        }
    }

}
