package jpokebattle.model;

import jpokebattle.interfaces.StatsInterface;

/**
 * The PokeStats class represents the statistics of a Pokemon.
 * It implements the StatsInterface interface.
 */
public class PokeStats implements StatsInterface {
    private int attack = 0;
    private int defense = 0;
    private int special = 0;
    private int speed = 0;
    private int ps = 0;

    private int maxValue;

    /**
     * Constructs a PokeStats object with a default maximum value.
     */
    public PokeStats() {
        this(999);
    }

    /**
     * Constructs a PokeStats object with the specified maximum value.
     *
     * @param maxValue the maximum value for the stats
     */
    public PokeStats(int maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * Resets all the stats to zero.
     */
    public void reset() {
        attack = 0;
        defense = 0;
        special = 0;
        speed = 0;
        ps = 0;
    }

    private int sanitizeValue(int value) {
        int res = value;
        if (value < -maxValue)
            res = -maxValue;
        else if (value > maxValue)
            res = maxValue;
        return res;
    }

    @Override
    public int getPS() {
        return ps;
    }

    /**
     * Sets the PS (Power Stat) value of the Pokemon.
     * The value is sanitized to be within the maximum value range.
     *
     * @param ps the PS value to set
     */
    public void setPS(int ps) {
        this.ps = sanitizeValue(ps);
    }

    @Override
    public int getAttack() {
        return attack;
    }

    /**
     * Sets the attack stat of the Pokemon.
     * The value is sanitized to be within the maximum value range.
     *
     * @param attack the attack stat to set
     */
    public void setAttack(int attack) {
        this.attack = sanitizeValue(attack);
    }

    @Override
    public int getDefense() {
        return defense;
    }

    /**
     * Sets the defense stat of the Pokemon.
     * The value is sanitized to be within the maximum value range.
     *
     * @param defense the defense stat to set
     */
    public void setDefense(int defense) {
        this.defense = sanitizeValue(defense);
    }

    @Override
    public int getSpecial() {
        return special;
    }

    /**
     * Sets the special stat of the Pokemon.
     * The value is sanitized to be within the maximum value range.
     *
     * @param special the special stat to set
     */
    public void setSpecial(int special) {
        this.special = sanitizeValue(special);
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets the speed stat of the Pokemon.
     * The value is sanitized to be within the maximum value range.
     *
     * @param speed the speed stat to set
     */
    public void setSpeed(int speed) {
        this.speed = sanitizeValue(speed);
    }

    /**
     * Sums the stats of this PokeStats object with the stats of another PokeStats object.
     *
     * @param other the other PokeStats object to sum with
     */
    public void sum(PokeStats other) {
        setAttack(attack + other.attack);
        setDefense(defense + other.defense);
        setSpecial(special + other.special);
        setSpeed(speed + other.speed);
        setPS(ps + other.ps);
    }
}
