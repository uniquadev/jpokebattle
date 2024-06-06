package jpokebattle.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;
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
    private AudioClip victoryMusic;

    public void initialize() {
        SoundService sound = SoundService.getSingleton();
        sound.getBattleMusic().stop();
        victoryMusic = sound.getVictoryMusic();
        victoryMusic.play();

        state = GameState.getSingleton();
        player = state.getPlayer();

        backButton.setText("< Menu");
        backButton.removeArrow();

        finishLabel.setText("Hai totalizzato " + state.getScore() + " punti!");
    
        backButton.getButton().setOnAction(e -> {
            victoryMusic.stop();
            JPokeBattle.getInstance().setView("menu");
        });
    }
}
