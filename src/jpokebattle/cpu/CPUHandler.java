package jpokebattle.cpu;

import java.util.Random;

import jpokebattle.interfaces.CPUInterface;
import jpokebattle.model.BattleAction;
import jpokebattle.model.PokeMove;
import jpokebattle.model.Pokemon;
import jpokebattle.model.Trainer;

public abstract class CPUHandler implements CPUInterface {
    protected Trainer trainer;
    protected Trainer opponent;
    protected Random random = new Random();
    public CPUHandler(Trainer trainer, Trainer opponent) {
        this.trainer = trainer;
    }

    public BattleAction makeAction(Pokemon pokemon) {
        return new BattleAction(
            trainer,
            opponent,
            pokemon
        );
    }
    public BattleAction makeAction(PokeMove move) {
        return new BattleAction(
            trainer,
            opponent,
            move
        );
    }
}
