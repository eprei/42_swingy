package ch._42lausanne.swingy.model.builders;

import ch._42lausanne.swingy.model.characters.Character;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component("characterBuilderDirector")
@Setter
public class CharacterBuilderDirector {
    private CharacterBuilder characterBuilder;

    public void buildCharacter(String heroName) {
        characterBuilder
                .reset()
                .buildName(heroName)
                .buildType()
                .buildLvl()
                .buildExperience()
                .buildStats();
    }

    public Character getCharacter() {
        return characterBuilder.getCharacter();
    }
}
