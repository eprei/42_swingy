package ch._42lausanne.swingy.view.validator;

import jakarta.validation.constraints.Pattern;

public class HeroNameUserInput {
    @Pattern(regexp = "[a-zA-Z]{1,20}", message = """
                *** Invalid entry ***
            The hero's name must have a maximum of 20 alphabetic characters.
            Please enter the name you wish to give to the hero you are creating:""")
    private final String heroNameUserInput;

    public HeroNameUserInput(String heroNameUserInput) {
        this.heroNameUserInput = heroNameUserInput;
    }
}