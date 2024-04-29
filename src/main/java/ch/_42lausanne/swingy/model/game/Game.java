package ch._42lausanne.swingy.model.game;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Game {
    // CONFIGURABLE
    @Getter
    private static final boolean _verbose = false;
    private Phase phase;

    public Game() {
        this.phase = Phase.WELCOME;
    }

    public enum Phase {
        WELCOME,
        CREATE_HERO,
        SELECT_HERO,
        MAP,
        FIGHT_OR_RUN,
        RUN_SUCCESSFUL,
        RUN_FAILED,
        WIN_BATTLE,
        LOOSE_BATTLE,
        ARTIFACT_DROPPED,
        WIN_MAP,
        WIN_GAME,
        EXIT_MAP,
        EXIT_GAME,
    }
}
