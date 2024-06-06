package jpokebattle.utils;

import jpokebattle.enums.Difficulty;

/**
 * The GenerationSettings class represents the settings for cpu trainers generation.
 * It defines the minimum and maximum evolution, level, and team size values based on the difficulty level.
 */
public class GenerationSettings {
    private int maxEvolution = 0;
    private int minEvolution = 0;
    private int maxLevel = 1;
    private int minLevel = 1;
    private int maxTeamSize = 1;
    private int minTeamSize = 1;

    /**
     * Constructs a new GenerationSettings object with the specified difficulty level.
     *
     * @param difficulty the difficulty level of the generation
     */
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

    /**
     * Returns the maximum evolution offset.
     *
     * @return the maximum evolution offset
     */
    public int getMaxEvolutionOffset() {
        return maxEvolution;
    }

    /**
     * Returns the minimum evolution offset.
     *
     * @return the minimum evolution offset
     */
    public int getMinEvolutionOffset() {
        return minEvolution;
    }

    /**
     * Returns the maximum level offset.
     *
     * @return the maximum level offset
     */
    public int getMaxLevelOffset() {
        return maxLevel;
    }

    /**
     * Returns the minimum level offset.
     *
     * @return the minimum level offset
     */
    public int getMinLevelOffset() {
        return minLevel;
    }

    /**
     * Returns the maximum team size.
     *
     * @return the maximum team size
     */
    public int getMaxTeamSize() {
        return maxTeamSize;
    }

    /**
     * Returns the minimum team size.
     *
     * @return the minimum team size
     */
    public int getMinTeamSize() {
        return minTeamSize;
    }
}
