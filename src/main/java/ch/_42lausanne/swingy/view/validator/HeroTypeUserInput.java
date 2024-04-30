package ch._42lausanne.swingy.view.validator;

import jakarta.validation.constraints.Pattern;

public class HeroTypeUserInput {
    @Pattern(regexp = "(?i)^(a|b|w|m|switch)$", message = """
                *** Invalid entry ***
            Please choose a valid hero type:
                        
                    (a) Archer
                    (b) Blacksmith
                    (w) Warrior
                    (m) Magician""")
    private final String heroTypeUserInput;

    public HeroTypeUserInput(String heroTypeUserInput) {
        this.heroTypeUserInput = heroTypeUserInput;
    }
}