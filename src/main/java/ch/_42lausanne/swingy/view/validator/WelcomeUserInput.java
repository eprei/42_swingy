package ch._42lausanne.swingy.view.validator;

import jakarta.validation.constraints.Pattern;

public class WelcomeUserInput {
    @Pattern(regexp = "[cpCP]", message = """
                *** Invalid entry ***
            You can choose between:
                        
                    (c) Create Character
                    (p) Play""")
    private final String welcomeUserInput;

    public WelcomeUserInput(String welcomeUserInput) {
        this.welcomeUserInput = welcomeUserInput;
    }
}