package jpokebattle.pokemon;

import jpokebattle.enums.LevelUpGroup;
import jpokebattle.interfaces.PokemonInterface;
import jpokebattle.interfaces.StatsInterface;
import jpokebattle.model.PokeStats;

/**
 * The abstract class PokemonInfo represents the basic information and stats of a Pokemon.
 * It implements the PokemonInterface and StatsInterface interfaces.
 */
public abstract class PokemonInfo implements PokemonInterface, StatsInterface {

    protected PokeStats baseStats = new PokeStats();

    /**
     * Returns the level up group of the Pokemon.
     * @return The level up group of the Pokemon.
     */
    public LevelUpGroup getLevelType() {
        return LevelUpGroup.MEDIUM_SLOW;
    }

    /**
     * Returns the amount of experience points dropped by the Pokemon when defeated.
     * @return The amount of experience points dropped by the Pokemon.
     */
    public int getDropExp() {
        return 0;
    }

    /**
     * Returns the current health points of the Pokemon.
     * @return The current health points of the Pokemon.
     */
    public int getPS() {
        return baseStats.getPS();
    }

    /**
     * Returns the attack stat of the Pokemon.
     * @return The attack stat of the Pokemon.
     */
    public int getAttack() {
        return baseStats.getAttack();
    }

    /**
     * Returns the defense stat of the Pokemon.
     * @return The defense stat of the Pokemon.
     */
    public int getDefense() {
        return baseStats.getDefense();
    }

    /**
     * Returns the special stat of the Pokemon.
     * @return The special stat of the Pokemon.
     */
    public int getSpecial() {
        return baseStats.getSpecial();
    }

    /**
     * Returns the speed stat of the Pokemon.
     * @return The speed stat of the Pokemon.
     */
    public int getSpeed() {
        return baseStats.getSpeed();
    }

    /**
     * Checks if the Pokemon can evolve.
     * @return true if the Pokemon can evolve, false otherwise.
     */
    public boolean canEvolve() {
        return getCurrentEvolution() < getMaxEvolution();
    }

    /**
     * Returns the required experience points to reach the specified level.
     * @param level The level to calculate the required experience points for.
     * @return The required experience points to reach the specified level.
     */
    public int getLeveRequiredExp(int level) {
        int result = 0;
        switch (getLevelType()) {
            case MEDIUM_FAST:
                result = (int) Math.pow(level, 3);
                break;
            case MEDIUM_SLOW:
                result = (int) (6/5*Math.pow(level, 3) - 15*Math.pow(level, 2) + 100*level - 140);
                break;
            case FAST:
                result = (int) (4 * Math.pow(level, 3) / 5);
                break;
            case SLOW:
                result = (int) (5 * Math.pow(level, 3) / 5);
                break;
        }
        return Math.max(0, result);
    }
}
