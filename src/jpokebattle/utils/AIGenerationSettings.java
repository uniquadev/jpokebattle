package jpokebattle.utils;

import jpokebattle.enums.Difficulty;

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
                levelMaxDelta = 2;
                teamDelta = 1;
                break;
            case HARD:
                evolutionDelta = 2;
                levelMinDelta = 1;
                levelMaxDelta = 3;
                teamDelta = 2;
                break;
            case IMPOSSIBLE:
                evolutionDelta = 3;
                levelMinDelta = 3;
                levelMaxDelta = 9;
                teamDelta = 4;
                break;
            case HAHAHAHAHA:
                evolutionDelta = 3;
                levelMinDelta = 5;
                levelMaxDelta = 25;
                teamDelta = 4;
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
