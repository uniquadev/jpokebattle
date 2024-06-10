package jpokebattle.model;

/**
 * Represents a player's score in a game.
 * Implements the Comparable interface to allow for comparison of scores.
 */
public class PlayerScore implements Comparable<PlayerScore> {
    private final String name;
    private final int score;

    /**
     * Constructs a PlayerScore object with the specified name and score.
     *
     * @param name  the name of the player
     * @param score the score of the player
     */
    public PlayerScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Returns the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the score of the player.
     *
     * @return the score of the player
     */
    public Integer getScore() {
        return score;
    }

    /**
     * Compares this PlayerScore object with another PlayerScore object based on their scores.
     *
     * @param o the other PlayerScore object to compare to
     * @return a negative integer, zero, or a positive integer if this score is less than, equal to,
     *         or greater than the other score, respectively
     */
    @Override
    public int compareTo(PlayerScore o) {
        return Integer.compare(o.score, score);
    }
}
