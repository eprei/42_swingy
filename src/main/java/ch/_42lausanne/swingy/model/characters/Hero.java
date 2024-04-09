package ch._42lausanne.swingy.model.characters;

import ch._42lausanne.swingy.model.artifacts.Artifact;
import ch._42lausanne.swingy.model.artifacts.ArtifactType;
import ch._42lausanne.swingy.view.console.UserMessages;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "HERO")
public class Hero extends Character {

    public void gainExperience(Character villain) {
        gainExperienceFromVillain(villain);
        updateHeroLevel();
    }

    private void gainExperienceFromVillain(Character villain) {
        int experienceGained = villain.getStrength() * 60;
        experience += experienceGained;
        UserMessages.printExeriencieGained(type, name, experienceGained);
    }

    private void updateHeroLevel() {
        int actualLevel = 0;
        int nextLevel;
        int experienceRequiredForNextLevel = 1000;

        while (experience >= experienceRequiredForNextLevel) {
            actualLevel++;
            nextLevel = actualLevel + 1;
            experienceRequiredForNextLevel = nextLevel * 1000 + (nextLevel - 1) * (nextLevel - 1) * 450;
        }

        if (level < actualLevel) {
            level = actualLevel;
            UserMessages.printLevelUp(type, name, level);
        }
    }

    public String toString() {
        String text = "╔═════════════════════════╗\n" +
                "║ name: " + name + "\n" +
                "║ hitPoints: " + stats.getHitPoints() + "\n";

        if (artifact != null) {
            text += "║ artifact:" + "\n" +
                    "║ \t\t- type: " + artifact.getType() + "\n" +
                    "║ \t\t- att: " + artifact.getStats().getAttack() + "\n" +
                    "║ \t\t- def: " + artifact.getStats().getDefense() + "\n" +
                    "║ \t\t- HP: " + artifact.getStats().getHitPoints() + "\n";
        }

        return text + "╚═════════════════════════╝\n";
    }

    public String toString(int id) {
        String text = "╔═════════════════════════╗\n" +
                "║ id: (" + id + ")\n" +
                "║ name: " + name + "\n" +
                "║ type: " + type + "\n" +
                "║ level: " + level + "\n" +
                "║ experience: " + experience + "\n" +
                "║ attack: " + stats.getAttack() + "\n" +
                "║ defense: " + stats.getDefense() + "\n" +
                "║ hitPoints: " + stats.getHitPoints() + "\n";

        if (artifact != null) {
            text += "║ artifact: " + artifact.getType() + "\n" +
                    "║ \t\t- att: " + artifact.getStats().getAttack() + "\n" +
                    "║ \t\t- def: " + artifact.getStats().getDefense() + "\n" +
                    "║ \t\t- HP: " + artifact.getStats().getHitPoints() + "\n";
        }

        return text + "╚═════════════════════════╝\n";
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
        if (artifact.getType() == ArtifactType.HELM) {
            stats.setHitPoints(this.stats.getHitPoints() + artifact.getStats().getHitPoints());
        }
    }

}

