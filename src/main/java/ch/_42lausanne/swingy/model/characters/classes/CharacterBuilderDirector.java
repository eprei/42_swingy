package ch._42lausanne.swingy.model.characters.classes;

import ch._42lausanne.swingy.model.characters.interfaces.CharacterBuilder;
import lombok.Setter;

@Setter
public class CharacterBuilderDirector {
    private CharacterBuilder characterBuilder;

    public CharacterBuilderDirector() {
    }

    public CharacterBuilderDirector(CharacterBuilder characterBuilder) {
        this.characterBuilder = characterBuilder;
    }

    public void buildCharacter(String heroName) {
        this.characterBuilder.reset();
        this.characterBuilder.buildName(heroName);
        this.characterBuilder.buildType();
        this.characterBuilder.buildLvl();
        this.characterBuilder.buildStats();
    }

    public Character getCharacter() {
        return this.characterBuilder.getCharacter();
    }
}
