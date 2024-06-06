package jpokebattle.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;
import jpokebattle.JPokeBattle;
import jpokebattle.gui.ArrowTextButton;
import jpokebattle.gui.LeaderboardGUI;
import jpokebattle.model.Trainer;
import jpokebattle.service.GameState;
import jpokebattle.service.Leaderboard;
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
    private AudioClip victoryMusic;
    private Leaderboard leaderboard;

    public void initialize() {
        victoryMusic = SoundService.getSingleton().getVictoryMusic();
        victoryMusic.play();

        state = GameState.getSingleton();
        player = state.getPlayer();
        leaderboard = Leaderboard.getSingleton();

        leaderboard.addScore(player.getName(), state.getScore());
        leaderView.update(leaderboard.getScores());

        backButton.setText("< Menu");
        backButton.removeArrow();

        finishLabel.setText("Hai totalizzato " + state.getScore() + " punti!");
    
        backButton.getButton().setOnAction(e -> {
            victoryMusic.stop();
            JPokeBattle.getInstance().setView("menu");
        });
    }
}
