package ch._42lausanne.swingy.view.viewer;

public interface UserInputValidator {
    String getAndValidateConsoleInput(Class<?> clazz);

    boolean validateGuiInput(String guiInput, Class<?> clazz);
}
