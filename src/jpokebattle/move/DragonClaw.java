package jpokebattle.move;

import jpokebattle.enums.MoveType;
import jpokebattle.enums.PokeElement;

public class DragonClaw extends BaseMove {

    public DragonClaw(int requiredLevel) {
        super(requiredLevel);
    }

    @Override
    public String getName() {
        return "Dragon Claw";
    }

    @Override
    public MoveType getType() {
        return MoveType.PHYSICAL;
    }

    @Override
    public PokeElement getElement() {
        return PokeElement.DRAGON;
    }

    @Override
    public int getPower() {
        return 80;
    }

    @Override
    public int getAccuracy() {
        return 100;
    }

    @Override
    public int getMaxUses() {
        return 15;
    }
    
}
