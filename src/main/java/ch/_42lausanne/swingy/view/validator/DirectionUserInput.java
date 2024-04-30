package ch._42lausanne.swingy.view.validator;

import jakarta.validation.constraints.Pattern;

public class DirectionUserInput {
    @Pattern(regexp = "(?i)^(w|d|s|a|switch)$", message = """
                *** Invalid entry ***
            Choose your direction!
                        
                    (w): North
                    (s): South
                    (d): East
                    (a): West
                    (switch) Switch to graphic user interface""")
    private final String directionUserInput;

    public DirectionUserInput(String directionUserInput) {
        this.directionUserInput = directionUserInput;
    }
}