package jpokebattle.controller;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import jpokebattle.JPokeBattle;
import jpokebattle.gui.ArrowButton;
import jpokebattle.gui.ArrowTextButton;
import jpokebattle.model.Trainer;
import jpokebattle.service.GameState;
import jpokebattle.service.SoundService;;

public class SetupController {
    private final int MAX_NAME_LEN = 16;
    ChangeListener<Boolean> focusListener;

    @FXML
    ArrowTextButton backButton;

    @FXML
    TextField nameField;

    @FXML
    ArrowButton submitArrow;

    public void initialize() {
        backButton.setText("< Indietro");
        backButton.removeArrow();
        
        submitArrow.resize(40, 50);

        backButton.getButton().setOnMouseClicked(e -> {
            JPokeBattle.getInstance().setView("menu");
        });
        
        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() <= MAX_NAME_LEN && oldValue.length() <= MAX_NAME_LEN)
                SoundService.getSingleton().playPress();
            if (newValue.length() > MAX_NAME_LEN) {
                nameField.setText(oldValue);
                newValue = oldValue;
            }
        });

        submitArrow.setOnAction(e -> {
            submit();
        });
        nameField.setOnAction(e -> {
            SoundService.getSingleton().playPress();
            submit();
        });

        // ringraziamo javafx per triggerare il focus automaticamente :D
        // https://stackoverflow.com/questions/29051225/remove-default-focus-from-textfield-javafx
        focusListener = (observable, oldValue, newValue) -> {
            if (newValue) {
                nameField.getParent().requestFocus();
                nameField.setPromptText("Allenatore");
                nameField.focusedProperty().removeListener(focusListener);
            }
        };

        nameField.focusedProperty().addListener(focusListener);
    }

    private String getSanitizedName() {
        String text = nameField.getText();
        return text.trim().isEmpty() ? "Allenatore" : text;
    }

    private void submit() {
        GameState game = GameState.getSingleton();
        Trainer player = game.getPlayer();
        player.setName(getSanitizedName());
        player.generateRandomTeam();
        JPokeBattle.getInstance().setView("battle");
    }
}
