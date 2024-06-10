package jpokebattle.interfaces;

import jpokebattle.controller.BattleController;
import jpokebattle.enums.MoveType;
import jpokebattle.enums.PokeElement;
import jpokebattle.model.MoveResult;
import jpokebattle.model.Pokemon;

/**
 * The MoveInterface represents a move that a Pokemon can perform in a battle.
 * It defines methods to get information about the move and perform the move on
 * a target Pokemon.
 */
public interface MoveInterface {
    /**
     * Gets the name of the move.
     *
     * @return the name of the move
     */
    public String getName();

    /**
     * Gets the element type of the move.
     *
     * @return the element type of the move
     */
    public PokeElement getElement();

    /**
     * Gets the type of the move.
     *
     * @return the type of the move
     */
    public MoveType getType();

    /**
     * Gets the maximum number of uses for the move.
     *
     * @return the maximum number of uses for the move
     */
    public int getMaxUses();

    /**
     * Gets the power of the move.
     *
     * @return the power of the move
     */
    public int getPower();

    /**
     * Gets the accuracy of the move.
     *
     * @return the accuracy of the move
     */
    public int getAccuracy();

    /**
     * Gets the required level to use the move.
     *
     * @return the required level to use the move
     */
    public int getRequiredLevel();

    /**
     * Performs the move on the target Pokemon.
     *
     * @param attacker the Pokemon performing the move
     * @param defender the target Pokemon
     * @return the result of the move
     */
    public MoveResult perform(Pokemon attacker, Pokemon defender);
}
