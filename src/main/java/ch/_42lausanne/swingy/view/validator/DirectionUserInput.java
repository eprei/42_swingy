package ch._42lausanne.swingy.view.validator;

import jakarta.validation.constraints.Pattern;

public class DirectionUserInput {
    @Pattern(regexp = "[wdsaWDSA]", message = """
                *** Invalid entry ***
            Choose your direction!
                        
                    (w): North
                    (s): South
                    (d): East
                    (a): West""")
    private final String directionUserInput;

    public DirectionUserInput(String directionUserInput) {
        this.directionUserInput = directionUserInput;
    }
}