package jpokebattle;

/**
 * Plain entry point that starts the JavaFX application.
 * A main class that does not extend {@link javafx.application.Application}
 * lets the game run from a classpath-only (fat) jar, where JavaFX is not on
 * the module path.
 */
public final class Launcher {

    private Launcher() {
    }

    public static void main(String[] args) {
        JPokeBattle.main(args);
    }
}
