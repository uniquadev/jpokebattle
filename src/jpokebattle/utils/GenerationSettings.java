package jpokebattle.utils;

import jpokebattle.enums.Difficulty;

/**
 * The GenerationSettings class represents the settings for cpu trainers generation.
 * It defines the minimum and maximum evolution, level, and team size values based on the difficulty level.
 */
public class GenerationSettings {
    private int maxEvolution = 0;
    private int minEvolution = 0;
    private int maxLevelOff = 1;
    private int minLevelOff = 1;
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
                minLevelOff = -2;
                maxLevelOff = 0;
                break;
            case MEDIUM:
                minLevelOff = -1;
                maxLevelOff = 2;
                minEvolution = 0;
                maxEvolution = 1;
                minTeamSize = 1;
                maxTeamSize = 2;
                break;
            case HARD:
                minLevelOff = 0;
                maxLevelOff = 3;
                minEvolution = 1;
                maxEvolution = 1;
                minTeamSize = 2;
                maxTeamSize = 3;
                break;
            case IMPOSSIBLE:
                minLevelOff = 3;
                maxLevelOff = 8;
                minEvolution = 2;
                maxEvolution = 2;
                minTeamSize = 4;
                maxTeamSize = 5;
                break;
            case HAHAHAHAHA:
                minLevelOff = 8;
                maxLevelOff = 15;
                minEvolution = 2;
                maxEvolution = 3;
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
    public int getMaxEvolution() {
        return maxEvolution;
    }

    /**
     * Returns the minimum evolution offset.
     *
     * @return the minimum evolution offset
     */
    public int getMinEvolution() {
        return minEvolution;
    }

    /**
     * Returns the maximum level offset.
     *
     * @return the maximum level offset
     */
    public int getMaxLevelOffset() {
        return maxLevelOff;
    }

    /**
     * Returns the minimum level offset.
     *
     * @return the minimum level offset
     */
    public int getMinLevelOffset() {
        return minLevelOff;
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
