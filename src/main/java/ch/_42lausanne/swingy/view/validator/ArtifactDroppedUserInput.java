package ch._42lausanne.swingy.view.validator;

import jakarta.validation.constraints.Pattern;

public class ArtifactDroppedUserInput {
    @Pattern(regexp = "(?i)^(k|l|switch)$", message = """
                *** Invalid entry ***
            You have found an Artifact, you can:
                        
                    (k) Keep it
                    (l) Leave it
                    (switch) Switch to graphic user interface""")
    private final String artifactDroppedUserInput;

    public ArtifactDroppedUserInput(String artifactDroppedUserInput) {
        this.artifactDroppedUserInput = artifactDroppedUserInput;
    }
}