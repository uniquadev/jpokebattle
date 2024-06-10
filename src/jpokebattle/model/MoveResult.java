package jpokebattle.model;

import jpokebattle.enums.AttackEffectiveness;
import jpokebattle.enums.MoveType;

/**
 * Represents the result of a move in a Pokémon battle.
 */
public class MoveResult {
    private int damage;
    private AttackEffectiveness moveResult;
    private AttackEffectiveness elemResult;
    private boolean critical;
    private PokeStats effects;
    private MoveType type;

    /**
     * Constructs a MoveResult object with the specified damage, critical flag, move result, and element result.
     *
     * @param damage The amount of damage caused by the move.
     * @param critical Indicates whether the move was a critical hit.
     * @param moveResult The effectiveness of the move against the target.
     * @param elemResult The effectiveness of the move against the target's element.
     */
    public MoveResult(int damage, boolean critical, AttackEffectiveness moveResult, AttackEffectiveness elemResult) {
        this.damage = damage;
        this.moveResult = moveResult;
        this.elemResult = elemResult;
        this.critical = critical;
        type = MoveType.PHYSICAL;
    }

    /**
     * Constructs a MoveResult object with the specified effects.
     *
     * @param effects The additional effects caused by the move.
     */
    public MoveResult(PokeStats effects) {
        this.effects = effects;
        moveResult = AttackEffectiveness.NORMAL;
        elemResult = AttackEffectiveness.NORMAL;
        type = MoveType.STATUS;
    }

    /**
     * Constructs a MoveResult object representing a missed move.
     */
    public MoveResult() {
        type = MoveType.MISS;
        moveResult = AttackEffectiveness.MISS;
        elemResult = AttackEffectiveness.MISS;
    }

    /**
     * Returns the type of the move result.
     *
     * @return The type of the move result.
     */
    public MoveType getType() {
        return type;
    }

    /**
     * Returns the additional effects caused by the move.
     *
     * @return The additional effects caused by the move.
     */
    public PokeStats getEffects() {
        return effects;
    }

    /**
     * Returns the amount of damage caused by the move.
     *
     * @return The amount of damage caused by the move.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Returns the effectiveness of the move element against the target's element.
     *
     * @return The effectiveness.
     */
    public AttackEffectiveness getMoveResult() {
        return moveResult;
    }

    /**
     * Returns the effectiveness of the pokemon element against the target's element.
     *
     * @return The effectiveness.
     */
    public AttackEffectiveness getElemResult() {
        return elemResult;
    }

    /**
     * Returns whether the move was a critical hit.
     *
     * @return {@code true} if the move was a critical hit, {@code false} otherwise.
     */
    public boolean isCritical() {
        return critical;
    }
}
