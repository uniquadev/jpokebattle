package jpokebattle.move;

import jpokebattle.enums.MoveType;
import jpokebattle.model.AttackData;
import jpokebattle.model.PokeStats;
import jpokebattle.model.Pokemon;

public class Growl extends BaseMove {

    public Growl(int requiredLevel) {
        super(requiredLevel);
    }

    @Override
    public String getName() {
        return "Growl";
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
        return 40;
    }
    
    @Override
    public PokeStats getEffects() {
        PokeStats effects = new PokeStats();
        effects.setAttack(-1);
        return effects;
    }
}
