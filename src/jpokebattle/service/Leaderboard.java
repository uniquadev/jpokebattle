package jpokebattle.service;

import java.util.ArrayList;

import jpokebattle.model.PlayerScore;
import jpokebattle.utils.LeaderboardFile;

/**
 * The Leaderboard class represents a leaderboard that keeps track of player scores.
 */
public class Leaderboard {
    private static Leaderboard singleton = null;

    /**
     * Returns the singleton instance of the Leaderboard class.
     * @return The singleton instance of the Leaderboard class.
     */
    public static Leaderboard getSingleton() {
        synchronized (Leaderboard.class) {
            if (singleton == null) {
                try {
                    singleton = LeaderboardFile.loadLeaderboardFromFile("leaderboard.txt");
                } catch (Exception e) {
                    singleton = new Leaderboard();
                }
            }
        }
        return singleton;
    }

    private ArrayList<PlayerScore> scores = new ArrayList<>();

    /**
     * Returns the minimum score in the leaderboard.
     * @return The minimum score in the leaderboard.
     */
    public int getMinScore() {
        if (scores.size() <= 0)
            return 0;
        return scores.get(scores.size() - 1).getScore();
    }

    /**
     * Returns the list of player scores in the leaderboard.
     * @return The list of player scores in the leaderboard.
     */
    public ArrayList<PlayerScore> getScores() {
        return scores;
    }

    /**
     * Adds a player score to the leaderboard.
     * @param name The name of the player.
     * @param score The score of the player.
     */
    public void addScore(String name, int score) {
        addScore(new PlayerScore(name, score));
    }

    /**
     * Saves the leaderboard to a file.
     */
    public void save() {
        try {
            LeaderboardFile.saveLeaderboardToFile(this, "leaderboard.txt");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to save leaderboard to file.");
        }
    }

    /**
     * Adds a player score to the leaderboard.
     * @param score The player score to add.
     * @return The position at which the score was added in the leaderboard.
     */
    public Integer addScore(PlayerScore score) {
        int minScore = getMinScore();
        if (score.getScore() <= minScore && scores.size() >= 10)
            return null;
        if (scores.size() >= 10) {
            scores.remove(scores.size() - 1);
        }
        int index = 0;
        while (index < scores.size() && score.getScore() <= scores.get(index).getScore()) {
            index++;
        }
        scores.add(index, score);
        return index+1;
    }
}
