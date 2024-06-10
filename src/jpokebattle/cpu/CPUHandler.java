package jpokebattle.cpu;

import java.util.Random;

import jpokebattle.enums.AttackEffectiveness;
import jpokebattle.enums.MoveType;
import jpokebattle.interfaces.CPUInterface;
import jpokebattle.model.BattleAction;
import jpokebattle.model.PokeMove;
import jpokebattle.model.Pokemon;
import jpokebattle.model.Trainer;

/**
 * The abstract class representing a CPU handler in the JPokeBattle system.
 * It implements the CPUInterface and provides common functionality for CPU handlers.
 */
public abstract class CPUHandler implements CPUInterface {
    protected Trainer cpu;
    protected Trainer opponent;
    protected Random random = new Random();

    /**
     * Constructs a new CPUHandler with the given CPU and opponent trainers.
     *
     * @param cpu      the CPU trainer
     * @param opponent the opponent trainer
     */
    public CPUHandler(Trainer cpu, Trainer opponent) {
        this.cpu = cpu;
        this.opponent = opponent;
    }

    /**
     * Returns the first available move of the specified type for the CPU's active Pokemon.
     *
     * @param type the type of move to search for
     * @return the first move of the specified type, or null if no such move is found
     */
    protected PokeMove getFirstMove(MoveType type) {
        return getFirstMove(type, null);
    }

    /**
     * Returns the first available move of the specified type and effectiveness for the CPU's active Pokemon.
     *
     * @param type the type of move to search for
     * @param eff  the effectiveness of the move against the opponent's active Pokemon
     * @return the first move of the specified type and effectiveness, or null if no such move is found
     */
    protected PokeMove getFirstMove(MoveType type, AttackEffectiveness eff) {
        for (PokeMove move : cpu.getActivePokemon().getMoves()) {
            if (move.getType() == type && (eff == null || move.getElement().getAttackResultTo(opponent.getActivePokemon().getElement()) == eff)) {
                return move;
            }
        }
        return null;
    }

    /**
     * Creates a BattleAction for the specified Pokemon switch.
     *
     * @param pokemon the Pokemon to perform the action with
     * @return the BattleAction for the specified Pokemon
     */
    protected BattleAction makeAction(Pokemon pokemon) {
        return new BattleAction(cpu, pokemon);
    }

    /**
     * Creates a BattleAction for the specified move.
     *
     * @param move the move to perform the action with
     * @return the BattleAction for the specified move
     */
    protected BattleAction makeAction(PokeMove move) {
        if (move == null) return new BattleAction();
        return new BattleAction(cpu, opponent, move);
    }
}
