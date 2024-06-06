package jpokebattle.move;

import jpokebattle.enums.MoveType;

public class Scratch extends BaseMove {

    public Scratch(int requiredLevel) {
        super(requiredLevel);
    }

    @Override
    public String getName() {
        return "Scratch";
    }

    @Override
    public MoveType getType() {
        return MoveType.ATTACK;
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
    public int getMaxUses() {
        return 35;
    }
}
