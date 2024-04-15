package ch._42lausanne.swingy.view.gui;

import ch._42lausanne.swingy.controller.Controller;
import ch._42lausanne.swingy.model.game.Game;
import ch._42lausanne.swingy.view.SwingUi;
import ch._42lausanne.swingy.view.viewer.BaseViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("guiViewer")
public class GuiViewer extends BaseViewer {
    public static boolean welcomeBannerHasBeenShowed = false;
    private final SwingUi swingUi;


    @Autowired
    public GuiViewer(Controller controller, Game game, SwingUi swingUi) {
        this.swingUi = swingUi;
        this.controller = controller;
        this.controller.setGuiViewer(this);
        this.game = game;
    }

    @Override
    public void welcomeView() {
        swingUi.enableWelcomeView();
    }

    @Override
    public void createHeroView() {

    }

    @Override
    public void selectHeroView() {

    }

    @Override
    public void mapView() {

    }

    @Override
    public void fightOrRunView() {

    }

    @Override
    public void runSuccessfulView() {

    }

    @Override
    public void runFailedView() {

    }

    @Override
    public void winBattleView() {

    }

    @Override
    public void looseBattleView() {

    }

    @Override
    public void artifactDroppedView() {

    }

    @Override
    public void winMapView() {

    }

    @Override
    public void winGameView() {

    }

}
