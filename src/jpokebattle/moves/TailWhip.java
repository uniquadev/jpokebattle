package jpokebattle.moves;

import jpokebattle.model.PokeStats;
import jpokebattle.utils.MoveType;

public class TailWhip extends BaseMove {

    public TailWhip(int requiredLevel) {
        super(requiredLevel);
    }

    @Override
    public String getName() {
        return "Tail Whip";
    }

    @Override
    public MoveType getType() {
        return MoveType.STATUS;
    }

    @Override
    public int getAccuracy() {
        return 100;
    }

    @Override
    public int getMaxUses() {
        return 30;
    }

    @Override
    public PokeStats getEffects() {
        PokeStats effects = new PokeStats();
        effects.setDefense(-1);
        return effects;
    }
}
