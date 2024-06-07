package jpokebattle.move;

import jpokebattle.enums.MoveType;
import jpokebattle.enums.PokeElement;

public class WaterGun extends BaseMove {

    public WaterGun(int requiredLevel) {
        super(requiredLevel);
    }

    @Override
    public String getName() {
        return "Water Gun";
    }

    @Override
    public MoveType getType() {
        return MoveType.SPECIAL;
    }

    @Override
    public int getAccuracy() {
        return 100;
    }

    @Override
    public int getPower() {
        return 40;
    }

    @Override
    public PokeElement getElement() {
        return PokeElement.WATER;
    }

    @Override
    public int getMaxUses() {
        return 25;
    }
    
}
