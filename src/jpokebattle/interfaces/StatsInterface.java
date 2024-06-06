package jpokebattle.interfaces;

/**
 * The StatsInterface interface represents the statistics of a pokemon in a battle.
 * It provides methods to retrieve the pokemon's health points (PS), attack, defense,
 * special attack, and speed.
 */
public interface StatsInterface {
    /**
     * Retrieves the pokemon's health points (PS).
     *
     * @return the pokemon's health points (PS)
     */
    public int getPS();

    /**
     * Retrieves the pokemon's attack stat.
     *
     * @return the pokemon's attack stat
     */
    public int getAttack();

    /**
     * Retrieves the pokemon's defense stat.
     *
     * @return the pokemon's defense stat
     */
    public int getDefense();

    /**
     * Retrieves the pokemon's special attack stat.
     *
     * @return the pokemon's special attack stat
     */
    public int getSpecial();

    /**
     * Retrieves the pokemon's speed stat.
     *
     * @return the pokemon's speed stat
     */
    public int getSpeed();
}
