package jpokebattle.move;

import jpokebattle.enums.MoveType;
import jpokebattle.enums.PokeElement;

public class DragonBreath extends BaseMove {

    public DragonBreath(int requiredLevel) {
        super(requiredLevel);
    }

    @Override
    public String getName() {
        return "Dragon Breath";
    }

    @Override
    public MoveType getType() {
        return MoveType.SPECIAL;
    }

    @Override
    public PokeElement getElement() {
        return PokeElement.DRAGON;
    }

    @Override
    public int getPower() {
        return 60;
    }

    @Override
    public int getAccuracy() {
        return 100;
    }

    @Override
    public int getMaxUses() {
        return 20;
    }

    
}
