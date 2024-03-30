package ch._42lausanne.swingy.model.characters.classes;

import ch._42lausanne.swingy.model.artifacts.classes.Artifact;
import ch._42lausanne.swingy.view.classes.console.UserMessages;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Hero extends Character {
    protected Artifact artifacts;

    public void gainExperience(Character villain) {
        gainExperienceFromVillain(villain);
        updateHeroLevel();
    }

    private void gainExperienceFromVillain(Character villain) {
        int experienceGained = villain.getStrenght() * 60;
        this.experience += experienceGained;
        UserMessages.printExeriencieGained(this.type, this.name, experienceGained);
    }

    private void updateHeroLevel() {
        int actualLevel = 0;
        int nextLevel;
        int experienceRequiredForNextLevel = 1000;

        while (this.experience >= experienceRequiredForNextLevel) {
            actualLevel++;
            nextLevel = actualLevel + 1;
            experienceRequiredForNextLevel = nextLevel * 1000 + (nextLevel - 1) * (nextLevel - 1) * 450;
        }

        if (this.level < actualLevel) {
            this.level = actualLevel;
            UserMessages.printLevelUp(this.type, this.name, this.level);
        }
    }
}
