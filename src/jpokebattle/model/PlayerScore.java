package jpokebattle.model;

public class PlayerScore implements Comparable<PlayerScore> {
    private final String name;
    private final int score;

    public PlayerScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

    @Override
    public int compareTo(PlayerScore o) {
        return Integer.compare(o.score, score);
    }
}
