package ch._42lausanne.swingy.view.validator;

import jakarta.validation.constraints.Pattern;

public class FightOrRunUserInput {
    @Pattern(regexp = "(?i)^(f|r|switch)$", message = """
                *** Invalid entry ***
            You can choose between:

                    (f) Fight
                    (r) Run
                    (switch) Switch to graphic user interface""")
    private final String fightOrRunUserInput;

    public FightOrRunUserInput(String fightOrRunUserInput) {
        this.fightOrRunUserInput = fightOrRunUserInput;
    }
}