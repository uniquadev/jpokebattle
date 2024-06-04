package jpokebattle.controller;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import jpokebattle.JPokeBattle;
import jpokebattle.service.SoundService;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StartController {

    private FadeTransition fadeTransition;

    @FXML
    private Label label;

    @FXML
    private VBox vbox;

    public void initialize() {
        fadeTransition = new FadeTransition(Duration.seconds(1), label);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.1);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();

        SoundService.getSingleton().playPurchase();
        SoundService.getSingleton().getTitleMusic().play();

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
            JPokeBattle.getInstance().setView("menu");
        });
        fadeTransition.play();

        SoundService.getSingleton().playPress();
    }
}
