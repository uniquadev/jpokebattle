package jpokebattle.service;

import jpokebattle.enums.Difficulty;
import jpokebattle.model.Trainer;

/**
 * The GameState class represents the state of the game.
 * It keeps track of the player, score, and current difficulty level.
 */
public class GameState {
    private static GameState singleton;

    /**
     * Returns the singleton instance of the GameState class.
     * If the instance does not exist, it creates a new one.
     *
     * @return the singleton instance of the GameState class
     */
    public static GameState getSingleton() {
        synchronized (GameState.class) {
            if (singleton == null) {
                singleton = new GameState();
            }
        }
        return singleton;
    }

    private Trainer player;
    private int score = 0;

    /**
     * Increases the score by 1.
     */
    public void increaseScore() {
        score++;
    }

    /**
     * Returns the current score.
     *
     * @return the current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Resets the score to 0.
     */
    public void resetScore() {
        score = 0;
    }

    /**
     * Sets the player.
     *
     * @param player the player to set
     */
    public void setPlayer(Trainer player) {
        this.player = player;
    }

    /**
     * Returns the player.
     *
     * @return the player
     */
    public Trainer getPlayer() {
        return player;
    }

    /**
     * Returns the current difficulty based on the score.
     *
     * @return the current difficulty
     */
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
