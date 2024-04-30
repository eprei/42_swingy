package ch._42lausanne.swingy.view.validator;

import jakarta.validation.constraints.Pattern;

public class WelcomeUserInput {
    @Pattern(regexp = "(?i)^(c|p|switch)$", message = """
                *** Invalid entry ***
            You can choose between:
                        
                    (c) Create Character
                    (p) Play
                    (switch) Switch to graphic user interface""")
    private final String welcomeUserInput;

    public WelcomeUserInput(String welcomeUserInput) {
        this.welcomeUserInput = welcomeUserInput;
    }
}