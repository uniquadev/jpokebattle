package jpokebattle.moves;

import jpokebattle.utils.MoveType;

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
        return MoveType.PHYSICAL;
    }

    @Override
    public int getAccuracy() {
        return 100;
    }
}
