package jpokebattle.controller;

import javafx.fxml.FXML;
import jpokebattle.JPokeBattle;
import jpokebattle.gui.ArrowTextButton;
import jpokebattle.gui.LeaderboardGUI;
import jpokebattle.service.Leaderboard;

/**
 * The controller class for the Leaderboard view.
 * This class handles the initialization and event handling for the Leaderboard view.
 */
public class LeaderController {
    @FXML
    private ArrowTextButton backButton;
    @FXML
    private LeaderboardGUI leaderView;

    private Leaderboard leaderboard;

    public void initialize() {
        leaderboard = Leaderboard.getSingleton();

        backButton.setText("< Indietro");
        backButton.removeArrow();
        leaderView.update(leaderboard.getScores());

        backButton.getButton().setOnAction(e -> {
            JPokeBattle.getInstance().setView("menu");
        });
    } 
}
