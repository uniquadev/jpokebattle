package jpokebattle.controller;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import jpokebattle.JPokeBattle;
import jpokebattle.services.SoundService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StartController implements Initializable {

    private FadeTransition fadeTransition;

    @FXML
    private Label label;

    @FXML
    private VBox vbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fadeTransition = new FadeTransition(Duration.seconds(1), label);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.1);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();

        SoundService.getSingleton().playPurchase();

        vbox.sceneProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                newValue.setOnKeyPressed(e -> {
                    moveToMenu();
                });
            }
        });
        
        vbox.setOnMouseClicked(e -> {
            moveToMenu();
        });
    }

    private void moveToMenu() {
        vbox.getScene().setOnKeyPressed(null);
        vbox.setOnMouseClicked(null);
        label.setOpacity(0);

        fadeTransition.stop();
        fadeTransition = new FadeTransition(Duration.seconds(0.8), vbox);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setOnFinished(e -> {
            JPokeBattle.getInstance().setView("test");
        });
        fadeTransition.play();

        SoundService.getSingleton().playPress();
    }
}
