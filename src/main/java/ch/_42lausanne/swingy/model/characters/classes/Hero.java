package ch._42lausanne.swingy.model.characters.classes;

import ch._42lausanne.swingy.model.artifacts.classes.Artifact;
import ch._42lausanne.swingy.model.artifacts.classes.ArtifactType;
import ch._42lausanne.swingy.view.classes.console.UserMessages;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Hero extends Character {

    public void gainExperience(Character villain) {
        gainExperienceFromVillain(villain);
        updateHeroLevel();
        System.out.printf(this.toString());
    }

    private void gainExperienceFromVillain(Character villain) {
        int experienceGained = villain.getStrength() * 60;
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

    public String toString() {
        String text = "---------------------------\n" +
                "| name: " + name + "\n" +
                "| attack: " + stats.getAttack() + "\n" +
                "| defense: " + stats.getDefense() + "\n" +
                "| hitPoints: " + stats.getHitPoints() + "\n";

        if (artifact != null) {
            text += "| artifact: " + artifact.getType() +
                    ", att: " + artifact.getStats().getAttack() +
                    ", def: " + artifact.getStats().getDefense() +
                    ", HP: " + artifact.getStats().getHitPoints() + "\n";
        }

        return text + "---------------------------\n";
    }

    public String toString(int id) {
        String text = "---------------------------\n" +
                "| id: " + id + "\n" +
                "| name: " + name + "\n" +
                "| type: " + type + "\n" +
                "| level: " + level + "\n" +
                "| experience: " + experience + "\n" +
                "| attack: " + stats.getAttack() + "\n" +
                "| defense: " + stats.getDefense() + "\n" +
                "| hitPoints: " + stats.getHitPoints() + "\n";

        if (artifact != null) {
            text += "| artifact: " + artifact.getType() +
                    ", att: " + artifact.getStats().getAttack() +
                    ", def: " + artifact.getStats().getDefense() +
                    ", HP: " + artifact.getStats().getHitPoints() + "\n";
        }

        return text + "---------------------------\n";
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
        if (artifact.getType() == ArtifactType.HELM) {
            this.stats.setHitPoints(this.stats.getHitPoints() + artifact.getStats().getHitPoints());
        }
    }
}
