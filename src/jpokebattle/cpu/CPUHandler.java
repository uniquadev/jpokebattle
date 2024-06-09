package jpokebattle.cpu;

import java.util.Random;

import jpokebattle.enums.AttackEffectiveness;
import jpokebattle.enums.MoveType;
import jpokebattle.interfaces.CPUInterface;
import jpokebattle.model.BattleAction;
import jpokebattle.model.PokeMove;
import jpokebattle.model.Pokemon;
import jpokebattle.model.Trainer;

public abstract class CPUHandler implements CPUInterface {
    protected Trainer cpu;
    protected Trainer opponent;
    protected Random random = new Random();
    public CPUHandler(Trainer cpu, Trainer opponent) {
        this.cpu = cpu;
        this.opponent = opponent;
    }

    protected PokeMove getFirstMove(MoveType type) {
        return getFirstMove(type, null);
    }

    protected PokeMove getFirstMove(MoveType type, AttackEffectiveness eff) {
        for (PokeMove move : cpu.getActivePokemon().getMoves()) {
            if (
                move.getType() == type &&
                (eff == null || move.getElement().getAttackResultTo(opponent.getActivePokemon().getElement()) == eff)
            ){
                return move;
            }
        }
        return null;
    }

    protected BattleAction makeAction(Pokemon pokemon) {
        return new BattleAction(
            cpu,
            pokemon
        );
    }
    protected BattleAction makeAction(PokeMove move) {
        if (move == null) return new BattleAction();
        return new BattleAction(
            cpu,
            opponent,
            move
        );
    }
}
