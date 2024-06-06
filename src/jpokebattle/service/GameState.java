package jpokebattle.service;

import jpokebattle.enums.Difficulty;
import jpokebattle.enums.GameModeState;
import jpokebattle.model.Trainer;

public class GameState {
    private static GameState singleton;
    public static GameState getSingleton() {
        if (singleton == null) {
            singleton = new GameState();
        }
        return singleton;
    }
    
    private GameModeState state = GameModeState.MENU;
    private Trainer player;
    private int score = 0;

    public void increaseScore() {
        score++;
    }
    public int getScore() {
        return score;
    }
    public void resetScore() {
        score = 0;
    }

    public GameModeState getState() {
        return state;
    }
    public void setState(GameModeState state) {
        this.state = state;
    }
    
    public void setPlayer(Trainer player) {
        this.player = player;
    }
    public Trainer getPlayer() {
        return player;
    }

    public Difficulty getCurrentDifficulty() {
        if (score < 10)
            return Difficulty.EASY;
        if (score < 20)
            return Difficulty.MEDIUM;
        if (score < 30)
            return Difficulty.HARD;
        return Difficulty.IMPOSSIBLE;
    }
}
