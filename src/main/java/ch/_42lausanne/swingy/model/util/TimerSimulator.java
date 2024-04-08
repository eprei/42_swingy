package ch._42lausanne.swingy.model.util;

public class TimerSimulator {
    public static void Sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception ignored) {
        }
    }
}
