package jpokebattle.utils;

import jpokebattle.service.GameState.Difficulty;

public class AIGenerationSettings {
    private int evolutionDelta = 0;
    private int levelDelta = 0;
    private int teamDelta = 0;

    public AIGenerationSettings(Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                evolutionDelta = 0;
                levelDelta = -3;
                teamDelta = 0;
                break;
            case MEDIUM:
                evolutionDelta = 1;
                levelDelta = 3;
                teamDelta = 1;
                break;
            case HARD:
                evolutionDelta = 2;
                levelDelta = 9;
                teamDelta = 2;
                break;
            case IMPOSSIBLE:
                evolutionDelta = 3;
                levelDelta = 18;
                teamDelta = 3;
                break;
        }
    }

    public int getEvolutionDelta() {
        return evolutionDelta;
    }
    public int getLevelDelta() {
        return levelDelta;
    }
    public int getTeamDelta() {
        return teamDelta;
    }
}
