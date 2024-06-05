package jpokebattle.utils;

import jpokebattle.service.GameState.Difficulty;

public class AIGenerationSettings {
    private int evolutionDelta = 0;
    private int levelMaxDelta = 0;
    private int levelMinDelta = 0;
    private int teamDelta = 0;

    public AIGenerationSettings(Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                evolutionDelta = 0;
                levelMinDelta = -3;
                levelMaxDelta = -1;
                teamDelta = 0;
                break;
            case MEDIUM:
                evolutionDelta = 1;
                levelMinDelta = -1;
                levelMaxDelta = 4;
                teamDelta = 1;
                break;
            case HARD:
                evolutionDelta = 2;
                levelMinDelta = 1;
                levelMaxDelta = 9;
                teamDelta = 2;
                break;
            case IMPOSSIBLE:
                evolutionDelta = 3;
                levelMinDelta = 3;
                levelMaxDelta = 18;
                teamDelta = 3;
                break;
        }
    }

    public int getEvolutionDelta() {
        return evolutionDelta;
    }
    public int getLevelMinDelta() {
        return levelMinDelta;
    }
    public int getLevelMaxDelta() {
        return levelMaxDelta;
    }
    public int getTeamDelta() {
        return teamDelta;
    }
}
