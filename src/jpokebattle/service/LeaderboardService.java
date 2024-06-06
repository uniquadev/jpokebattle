package jpokebattle.service;

import java.util.ArrayList;

import jpokebattle.model.PlayerScore;

public class LeaderboardService {
    private static LeaderboardService singleton = null;
    public static LeaderboardService getSingleton() {
        synchronized (LeaderboardService.class) {
            if (singleton == null) {
                singleton = new LeaderboardService();
            }
        }
        return singleton;
    }

    ArrayList<PlayerScore> scores = new ArrayList<>();

    private LeaderboardService() {
    }

    public int getMinScore() {
        if (scores.size() <= 0)
            return 0;
        return scores.get(scores.size() - 1).getScore();
    }

    public void addScore(String name, int score) {
        scores.add(new PlayerScore(name, score));
    }

    public Integer addScore(PlayerScore score) {
        int minScore = getMinScore();
        if (score.getScore() < minScore)
            return null;
        if (scores.size() >= 10) {
            scores.remove(scores.size() - 1);
        }
        int index = 0;
        while (index < scores.size() && score.getScore() < scores.get(index).getScore()) {
            index++;
        }
        scores.add(index, score);
        return index+1;
    }
}
