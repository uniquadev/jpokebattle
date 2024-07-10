package jpokebattle.utils;

import jpokebattle.model.PlayerScore;
import jpokebattle.service.Leaderboard;

import java.io.*;

/**
 * The LeaderboardFile class provides methods to save and load leaderboard data to/from a file.
 */
public class LeaderboardFile {

    /**
     * Saves the leaderboard data to a file.
     *
     * @param leaderboard The leaderboard object containing the scores to be saved.
     * @param filename    The name of the file to save the leaderboard data to.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public static void saveToFile(Leaderboard leaderboard, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (PlayerScore score : leaderboard.getScores()) {
                writer.write(score.getName() + "," + score.getScore());
                writer.newLine();
            }
        }
    }

    /**
     * Loads the leaderboard data from a file.
     *
     * @param filename The name of the file to load the leaderboard data from.
     * @return The leaderboard object containing the loaded scores.
     * @throws IOException If an I/O error occurs while reading from the file.
     */
    public static Leaderboard loadFromFile(String filename) throws IOException {
        Leaderboard leaderboard = new Leaderboard();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String playerName = parts[0];
                int score = Integer.parseInt(parts[1]);
                leaderboard.addScore(playerName, score);
            }
        }
        return leaderboard;
    }
}
