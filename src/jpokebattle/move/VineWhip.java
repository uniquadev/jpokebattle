package jpokebattle.move;

import jpokebattle.enums.MoveType;
import jpokebattle.enums.PokeElement;

public class VineWhip extends BaseMove {

    public VineWhip(int requiredLevel) {
        super(requiredLevel);
    }

    @Override
    public String getName() {
        return "Vine Whip";
    }

    @Override
    public MoveType getType() {
        return MoveType.PHYSICAL;
    }

    @Override
    public PokeElement getElement() {
        return PokeElement.GRASS;
    }

    @Override
    public int getPower() {
        return 45;
    }

    @Override
    public int getAccuracy() {
        return 100;
    }
    
    @Override
    public int getMaxUses() {
        return 25;
    }
}
