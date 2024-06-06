package jpokebattle.service;

import java.util.ArrayList;

import jpokebattle.model.PlayerScore;

public class Leaderboard {
    private static Leaderboard singleton = null;
    public static Leaderboard getSingleton() {
        synchronized (Leaderboard.class) {
            if (singleton == null) {
                singleton = new Leaderboard();
            }
        }
        return singleton;
    }

    private ArrayList<PlayerScore> scores = new ArrayList<>();

    private Leaderboard() {
    }

    public int getMinScore() {
        if (scores.size() <= 0)
            return 0;
        return scores.get(scores.size() - 1).getScore();
    }

    public ArrayList<PlayerScore> getScores() {
        return scores;
    }

    public void addScore(String name, int score) {
        addScore(new PlayerScore(name, score));
    }

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
