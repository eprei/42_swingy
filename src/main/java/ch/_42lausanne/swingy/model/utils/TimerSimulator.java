package ch._42lausanne.swingy.model.utils;

public class TimerSimulator {
    public static void Sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception ignored) {
        }
    }
}
