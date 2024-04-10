package ch._42lausanne.swingy.model.characters;

import ch._42lausanne.swingy.model.artifacts.Artifact;
import ch._42lausanne.swingy.model.artifacts.ArtifactType;
import ch._42lausanne.swingy.view.console.ConsoleColors;
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
        System.out.printf(this.toString());
    }

    private void gainExperienceFromVillain(Character villain) {
        int experienceGained = villain.getStrength() * 60;
        experience += experienceGained;
        UserMessages.printExeriencieGained(type, name, experienceGained);
    }

    private void updateHeroLevel() {
        int actualLevel = 1;
        int experienceRequiredForNextLevel = 1000;

        while (experience >= experienceRequiredForNextLevel) {
            actualLevel++;
            experienceRequiredForNextLevel = actualLevel * 1000 + (actualLevel - 1) * (actualLevel - 1) * 450;
        }

        if (this.level < actualLevel) {
            this.level = actualLevel;
            UserMessages.printLevelUp(type, name, this.level);
        }
    }

    public String toString() {
        String text = ConsoleColors.YELLOW + "╔═════════════════════════╗\n" +
                ConsoleColors.YELLOW + "║ name: " + ConsoleColors.GREEN_BOLD + name + ConsoleColors.RESET + "\n" +
                ConsoleColors.YELLOW + "║ hitPoints: " + ConsoleColors.RESET + stats.getHitPoints() + "\n" +
                ConsoleColors.YELLOW + "║ level: " + ConsoleColors.RESET + level + "\n" +
                ConsoleColors.YELLOW + "║ experience: " + ConsoleColors.RESET + experience + "\n";

        if (artifact != null) {
            text += ConsoleColors.YELLOW + "║ artifact:" + "\n" +
                    ConsoleColors.YELLOW + "║ \t\t- type: " + ConsoleColors.RESET + artifact.getType() + "\n" +
                    ConsoleColors.YELLOW + "║ \t\t- att: " + ConsoleColors.RESET + artifact.getStats().getAttack() + "\n" +
                    ConsoleColors.YELLOW + "║ \t\t- def: " + ConsoleColors.RESET + artifact.getStats().getDefense() + "\n" +
                    ConsoleColors.YELLOW + "║ \t\t- HP: " + ConsoleColors.RESET + artifact.getStats().getHitPoints() + "\n";
        }

        return text + ConsoleColors.YELLOW + "╚═════════════════════════╝\n" + ConsoleColors.RESET;
    }

    public String toString(int id) {
        String text = ConsoleColors.YELLOW + "╔═════════════════════════╗\n" +
                ConsoleColors.YELLOW + "║ id:" + ConsoleColors.RESET + " (" + id + ")\n" +
                ConsoleColors.YELLOW + "║ name: " + ConsoleColors.GREEN_BOLD + name + "\n" + ConsoleColors.RESET +
                ConsoleColors.YELLOW + "║ type: " + ConsoleColors.RESET + type + "\n" +
                ConsoleColors.YELLOW + "║ level: " + ConsoleColors.RESET + level + "\n" +
                ConsoleColors.YELLOW + "║ experience: " + ConsoleColors.RESET + experience + "\n" +
                ConsoleColors.YELLOW + "║ attack: " + ConsoleColors.RESET + stats.getAttack() + "\n" +
                ConsoleColors.YELLOW + "║ defense: " + ConsoleColors.RESET + stats.getDefense() + "\n" +
                ConsoleColors.YELLOW + "║ hitPoints: " + ConsoleColors.RESET + stats.getHitPoints() + "\n";

        if (artifact != null) {
            text += ConsoleColors.YELLOW + "║ artifact: " + ConsoleColors.RESET + artifact.getType() + "\n" +
                    ConsoleColors.YELLOW + "║ \t\t- att: " + ConsoleColors.RESET + artifact.getStats().getAttack() + "\n" +
                    ConsoleColors.YELLOW + "║ \t\t- def: " + ConsoleColors.RESET + artifact.getStats().getDefense() + "\n" +
                    ConsoleColors.YELLOW + "║ \t\t- HP: " + ConsoleColors.RESET + artifact.getStats().getHitPoints() + "\n";
        }

        return text + ConsoleColors.YELLOW + "╚═════════════════════════╝\n" + ConsoleColors.RESET;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
        if (artifact.getType() == ArtifactType.HELM) {
            stats.setHitPoints(this.stats.getHitPoints() + artifact.getStats().getHitPoints());
        }
    }

}

