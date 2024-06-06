package jpokebattle.utils;

import jpokebattle.enums.Difficulty;

public class GenerationSettings {
    private int maxEvolution = 0;
    private int minEvolution = 0;
    private int maxLevel = 1;
    private int minLevel = 1;
    private int maxTeamSize = 1;
    private int minTeamSize = 1;

    public GenerationSettings(Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                minLevel = -4;
                maxLevel = 0;
                break;
            case MEDIUM:
                minLevel = -3;
                maxLevel = 2;
                minEvolution = -1;
                maxEvolution = 0;
                minTeamSize = 2;
                maxTeamSize = 3;
                break;
            case HARD:
                minLevel = 0;
                maxLevel = 5;
                minEvolution = 0;
                maxEvolution = 1;
                minTeamSize = 3;
                maxTeamSize = 4;
                break;
            case IMPOSSIBLE:
                minLevel = 4;
                maxLevel = 8;
                minEvolution = 0;
                maxEvolution = 1;
                minTeamSize = 4;
                maxTeamSize = 5;
                break;
            case HAHAHAHAHA:
                minLevel = 8;
                maxLevel = 12;
                minEvolution = 1;
                maxEvolution = 2;
                minTeamSize = 6;
                maxTeamSize = 6;
                break;
        }
    }

    public int getMaxEvolutionOffset() {
        return maxEvolution;
    }
    public int getMinEvolutionOffset() {
        return minEvolution;
    }
    public int getMaxLevelOffset() {
        return maxLevel;
    }
    public int getMinLevelOffset() {
        return minLevel;
    }
    public int getMaxTeamSize() {
        return maxTeamSize;
    }
    public int getMinTeamSize() {
        return minTeamSize;
    }
}
