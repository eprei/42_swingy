package ch._42lausanne.swingy;

import ch._42lausanne.swingy.controller.classes.ControllerImpl;
import ch._42lausanne.swingy.model.game.classes.Model;
import ch._42lausanne.swingy.view.classes.console.ConsoleViewer;
import ch._42lausanne.swingy.view.classes.console.UserMessages;
import ch._42lausanne.swingy.view.classes.gui.GuiViewer;
import ch._42lausanne.swingy.view.interfaces.GenericViewer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@Slf4j
@SpringBootApplication
public class Application {
    private static Model model;
    private static GenericViewer viewer;
    private static ControllerImpl controller;

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
//        SpringApplication.run(Application.class, args);
    }

    private static void launchGame() {
        UserMessages.printBanner();
        model = new Model();
        controller = new ControllerImpl(model, viewer);
        viewer.setController(controller);
        controller.runApplication();
    }
}

