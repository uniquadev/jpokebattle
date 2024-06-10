package jpokebattle.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import jpokebattle.JPokeBattle;
import jpokebattle.gui.ArrowTextButton;

/**
 * The controller class for the Info view.
 * Used mainly for debugging purposes to display information about the JavaFX version.
 */
public class InfoController {

    @FXML
    private Label label;

    @FXML
    private ArrowTextButton backButton;

    public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        label.setText("JavaFX " + javafxVersion + "\nEseguendo su Java " + javaVersion + ".");

        backButton.setText("< Indietro");
        backButton.removeArrow();
        backButton.getButton().setOnMouseClicked(e -> {
            JPokeBattle.getInstance().setView("menu");
        });
    }
}