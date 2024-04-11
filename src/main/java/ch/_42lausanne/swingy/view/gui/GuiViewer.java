package ch._42lausanne.swingy.view.gui;

import ch._42lausanne.swingy.controller.Controller;
import ch._42lausanne.swingy.view.viewer.ViewerImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component("guiViewer")
public class GuiViewer extends ViewerImpl {

    @Autowired
    public GuiViewer(Controller controller) {
        this.controller = controller;
        this.controller.setGuiViewer(this);
    }

    @Override
    public void welcomeView() {

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
