package jpokebattle.moves;

import jpokebattle.model.AttackData;
import jpokebattle.model.PokeStats;
import jpokebattle.model.Pokemon;
import jpokebattle.utils.MoveType;

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
    public AttackData perform(Pokemon attacker, Pokemon defender) {
        PokeStats effects = new PokeStats();
        effects.setAttack(-1);
        return new AttackData(effects);
    }
}
