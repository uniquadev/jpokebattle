package jpokebattle.cpu;

import java.util.Random;

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

    public BattleAction makeAction(Pokemon pokemon) {
        return new BattleAction(
            cpu,
            pokemon
        );
    }
    public BattleAction makeAction(PokeMove move) {
        return new BattleAction(
            cpu,
            opponent,
            move
        );
    }
}
