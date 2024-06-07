package jpokebattle.move;

import jpokebattle.enums.MoveType;

public class Tackle extends BaseMove {

    public Tackle(int requiredLevel) {
        super(requiredLevel);
    }

    @Override
    public String getName() {
        return "Tackle";
    }
    
    @Override
    public int getPower() {
        return 40;
    }

    @Override
    public int getAccuracy() {
        return 95;
    }

    @Override
    public int getMaxUses() {
        return 35;
    }

    @Override
    public MoveType getType() {
        return MoveType.PHYSICAL;
    }
}
