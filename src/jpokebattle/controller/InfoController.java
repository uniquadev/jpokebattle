package jpokebattle.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import jpokebattle.JPokeBattle;
import jpokebattle.gui.ArrowTextButton;

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