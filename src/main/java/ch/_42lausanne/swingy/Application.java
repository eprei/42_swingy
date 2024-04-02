package ch._42lausanne.swingy;

import ch._42lausanne.swingy.controller.Controller;
import ch._42lausanne.swingy.controller.ControllerImpl;
import ch._42lausanne.swingy.model.game.Model;
import ch._42lausanne.swingy.model.game.ModelImpl;
import ch._42lausanne.swingy.view.console.ConsoleViewer;
import ch._42lausanne.swingy.view.console.UserMessages;
import ch._42lausanne.swingy.view.gui.GuiViewer;
import ch._42lausanne.swingy.view.viewer.Viewer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@Slf4j
@SpringBootApplication
public class Application {
    private static Model model;
    private static Viewer viewer;
    private static Controller controller;

    public static void main(String[] args) {

        if (Objects.equals(args[0].toLowerCase(), "console")) {
            viewer = new ConsoleViewer();
        } else if (Objects.equals(args[0].toLowerCase(), "gui")) {
            viewer = new GuiViewer();
        } else {
            UserMessages.printUsage();
            return;
        }
        launchGame();
    }

    private static void launchGame() {
        UserMessages.printBanner();
        model = new ModelImpl();
        controller = new ControllerImpl(model, viewer);
        viewer.setController(controller);
        controller.runApplication();
    }
}

