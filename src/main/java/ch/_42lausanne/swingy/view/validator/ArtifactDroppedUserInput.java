package ch._42lausanne.swingy.view.validator;

import jakarta.validation.constraints.Pattern;

public class ArtifactDroppedUserInput {
    @Pattern(regexp = "[klKL]", message = """
                *** Invalid entry ***
            You have found an Artifact, you can:
                        
                    (k) Keep it
                    (l) Leave it""")
    private final String artifactDroppedUserInput;

    public ArtifactDroppedUserInput(String artifactDroppedUserInput) {
        this.artifactDroppedUserInput = artifactDroppedUserInput;
    }
}