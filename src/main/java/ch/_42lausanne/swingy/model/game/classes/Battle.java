package ch._42lausanne.swingy.model.game.classes;

import ch._42lausanne.swingy.model.artifacts.classes.Artifact;
import ch._42lausanne.swingy.model.characters.classes.Character;
import ch._42lausanne.swingy.model.characters.classes.Hero;
import ch._42lausanne.swingy.view.classes.console.UserMessages;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

    private static void performBattleRounds(Character @NotNull [] contenders) {
        while (contenders[0].getIsAlive() && contenders[1].getIsAlive()) {
            contenders[0].attackEnemy(contenders[1]);
            if (contenders[1].getIsAlive()) {
                contenders[1].attackEnemy(contenders[0]);
            }
        }
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

    public void simulateBattle() {
        Character[] contenders = mixContenders(hero, villain);

        performBattleRounds(contenders);
        findWinner();
    }

    private void dropArtifact() {
        Random random = new Random();
        int randomSeed = random.nextInt(11);
        if (5 < randomSeed) {
            artifactDropped = Artifact.buildRandomArtifact(villain);
        }
    }

    private Character[] mixContenders(Hero hero, Character villain) {
        Character[] contenders = new Character[2];
        Random random = new Random();

        boolean randomBoolean = random.nextBoolean();
        if (randomBoolean) {
            contenders[0] = hero;
            contenders[1] = villain;
        } else {
            contenders[0] = villain;
            contenders[1] = hero;
        }

        return contenders;
    }

}
