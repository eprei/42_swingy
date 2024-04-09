package ch._42lausanne.swingy.view.validator;

import jakarta.validation.constraints.Pattern;

public class HeroIndexUserInput {
    @Pattern(regexp = "^[0-9]{1,3}$", message = """
                *** Invalid entry ***
            Invalid index number.
            The hero index must be a number between 0 and the max. character id.
            Please enter a valid index number:""")
    private final String heroIndexUserInput;

    public HeroIndexUserInput(String heroIndexUserInput) {
        this.heroIndexUserInput = heroIndexUserInput;
    }
}