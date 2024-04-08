package ch._42lausanne.swingy.model.builders;

import ch._42lausanne.swingy.model.characters.Character;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component("characterBuilderDirector")
@Setter
public class CharacterBuilderDirector {
    private CharacterBuilder characterBuilder;

    public void buildCharacter(String heroName) {
        this.characterBuilder.reset();
        this.characterBuilder.buildName(heroName);
        this.characterBuilder.buildType();
        this.characterBuilder.buildLvl();
        this.characterBuilder.buildExperience();
        this.characterBuilder.buildStats();
    }

    public Character getCharacter() {
        return this.characterBuilder.getCharacter();
    }
}
