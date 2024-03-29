package ch._42lausanne.swingy.model.utils.classes;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class NameGenerator {
    private static final List<String> ADJECTIVES = List.of(
            "Apocalyptic", "Cruel", "Cunning", "Dark", "Devious", "Dreadful",
            "Evil", "Fiendish", "Malevolent", "Malicious", "Nefarious", "Sinister",
            "Terrible", "Tormented", "Wicked", "Wrathful"
    );

    private static final List<String> NOUNS = List.of(
            "Bane", "Blight", "Bringer", "Corrupter", "Destroyer", "Devourer",
            "Doom", "Eradicator", "Exterminator", "Malice", "Oblivion", "Perdition",
            "Pestilence", "Scourge", "Torment", "Tyrant", "Vengeance"
    );

    private static final Random random = new Random();

    public static @NotNull String generateRandomName() {
        String name = ADJECTIVES.get(random.nextInt(ADJECTIVES.size())) + " " +
                NOUNS.get(random.nextInt(NOUNS.size()));
        System.out.println(name);
        return name;
//        return ADJECTIVES.get(random.nextInt(ADJECTIVES.size())) + " " +
//                NOUNS.get(random.nextInt(NOUNS.size()));
    }
}
