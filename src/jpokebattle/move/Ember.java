package jpokebattle.move;

import jpokebattle.enums.MoveType;
import jpokebattle.enums.PokeElement;

public class Ember extends BaseMove {

    public Ember(int requiredLevel) {
        super(requiredLevel);
    }

    @Override
    public String getName() {
        return "Ember";
    }

    @Override
    public MoveType getType() {
        return MoveType.SPECIAL;
    }

    @Override
    public PokeElement getElement() {
        return PokeElement.FIRE;
    }

    @Override
    public int getAccuracy() {
        return 100;
    }

    @Override
    public int getPower() {
        return 40;
    }
    
}
