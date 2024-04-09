package ch._42lausanne.swingy.view.validator;

import jakarta.validation.constraints.Pattern;

public class FightOrRunUserInput {
    @Pattern(regexp = "[frFR]", message = """
                *** Invalid entry ***
            You can choose between:

                    (f) Fight
                    (r) Run""")
    private final String fightOrRunUserInput;

    public FightOrRunUserInput(String fightOrRunUserInput) {
        this.fightOrRunUserInput = fightOrRunUserInput;
    }
}