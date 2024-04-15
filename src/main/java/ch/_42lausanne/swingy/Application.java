package ch._42lausanne.swingy;

import ch._42lausanne.swingy.controller.Controller;
import ch._42lausanne.swingy.view.console.UserMessages;
import ch._42lausanne.swingy.view.viewer.Viewer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class Application {
    private static final JFrame frame = new JFrame();
    private static String selectedViewer;

    public static void main(String[] args) {
        if (!validArguments(args)) {
            UserMessages.printUsage();
            return;
        }
        startGame(args);
    }

    private static void startGame(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        Controller controller = context.getBean(Controller.class);
        controller.setActiveViewer(selectedViewer);
        controller.runApplication();
    }

    private static boolean validArguments(String[] args) {
        if (args.length != 1) {
            return false;
        }
        selectedViewer = args[0].toLowerCase();
        return selectedViewer.equals(Viewer.CONSOLE_VIEW) || selectedViewer.equals(Viewer.GUI_VIEW);
    }
}

