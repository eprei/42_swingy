package ch._42lausanne.swingy.view.validator;

import ch._42lausanne.swingy.view.viewer.UserInputValidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.util.Scanner;
import java.util.Set;

@Component("userInputValidatorImpl")
@Slf4j
public class UserInputValidatorImpl implements UserInputValidator {
    private final Scanner inputReader;
    private final Validator validator;


    public UserInputValidatorImpl() {
        this.inputReader = new Scanner(System.in);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Override
    public String getAndValidateConsoleInput(Class<?> clazz) {
        String userInput = null;
        boolean validInput = false;
        Constructor<?> constructor;

        try {
            constructor = clazz.getConstructor(String.class);

            while (!validInput) {
                userInput = inputReader.nextLine();
                Object inputToValidate = constructor.newInstance(userInput);
                Set<ConstraintViolation<Object>> constraintViolations = validator.validate(inputToValidate);
                validInput = constraintViolations.isEmpty();
                if (!validInput) {
                    System.out.println((constraintViolations.iterator().next().
                            getMessage()));
                }
            }
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }

        return userInput;
    }

    @Override
    public boolean validateGuiInput(String guiInput, Class<?> clazz) {
        Constructor<?> constructor;

        try {
            constructor = clazz.getConstructor(String.class);
            Object inputToValidate = constructor.newInstance(guiInput);
            Set<ConstraintViolation<Object>> constraintViolations = validator.validate(inputToValidate);
            return constraintViolations.isEmpty();
        } catch (Exception e) {
            log.error(String.valueOf(e));
            return false;
        }
    }
}
