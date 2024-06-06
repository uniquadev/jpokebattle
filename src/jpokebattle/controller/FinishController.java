package jpokebattle.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import jpokebattle.JPokeBattle;
import jpokebattle.gui.ArrowTextButton;
import jpokebattle.gui.LeaderboardGUI;
import jpokebattle.model.Trainer;
import jpokebattle.service.GameState;
import jpokebattle.service.SoundService;

public class FinishController {

    @FXML
    private ArrowTextButton backButton;
    @FXML
    private Label finishLabel;
    @FXML
    private LeaderboardGUI leaderView;

    private GameState state;
    private Trainer player;

    public void initialize() {
        SoundService.getSingleton().getBattleMusic().stop();
        state = GameState.getSingleton();
        player = state.getPlayer();

        backButton.setText("< Menu");
        backButton.removeArrow();

        finishLabel.setText("Hai totalizzato " + state.getScore() + " punti!");
    
        backButton.getButton().setOnAction(e -> {
            JPokeBattle.getInstance().setView("menu");
        });
    }
}
