package jpokebattle.model;

import jpokebattle.controller.BattleController;
import jpokebattle.enums.MoveType;
import jpokebattle.enums.PokeElement;
import jpokebattle.interfaces.MoveInterface;
import jpokebattle.move.BaseMove;

/**
 * Represents a move that a Pokemon can use in battle.
 */
public class PokeMove implements MoveInterface {
    private BaseMove move;
    private int usesLeft = 0;
    private int slot = 0;

    /**
     * Constructs a PokeMove object with the given BaseMove.
     * @param move the BaseMove associated with this PokeMove
     */
    public PokeMove(BaseMove move) {
        this.move = move;
        this.usesLeft = getMaxUses();
    }

    /**
     * Returns the BaseMove associated with this PokeMove.
     * @return the BaseMove associated with this PokeMove
     */
    public BaseMove getMove() {
        return move;
    }

    /**
     * Sets the slot of this PokeMove.
     * @param slot the slot to set
     */
    public void setSlot(int slot) {
        this.slot = slot;
    }

    /**
     * Returns the slot of this PokeMove.
     * @return the slot of this PokeMove
     */
    public int getSlot() {
        return slot;
    }

    /**
     * Returns the number of uses left for this PokeMove.
     * @return the number of uses left for this PokeMove
     */
    public int getUsesLeft() {
        return usesLeft;
    }

    /**
     * Decreases the number of uses left for this PokeMove by 1.
     */
    public void consumeUses() {
        usesLeft--;
    }

    /**
     * Resets the number of uses left for this PokeMove to its maximum value.
     */
    public void resetUses() {
        usesLeft = getMaxUses();
    }

    /**
     * Checks if this PokeMove can be used.
     * @return true if this PokeMove can be used, false otherwise
     */
    public boolean canUse() {
        return usesLeft > 0;
    }

    @Override
    public int getRequiredLevel() {
        return move.getRequiredLevel();
    }

    @Override
    public String getName() {
        return move.getName();
    }

    @Override
    public PokeElement getElement() {
        return move.getElement();
    }

    @Override
    public MoveType getType() {
        return move.getType();
    }

    @Override
    public int getMaxUses() {
        return move.getMaxUses();
    }

    @Override
    public int getPower() {
        return move.getPower();
    }

    @Override
    public int getAccuracy() {
        return move.getAccuracy();
    }

    @Override
    public MoveResult perform(Pokemon attacker, Pokemon defender) {
        return move.perform(attacker, defender);
    }
}
