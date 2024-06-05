package jpokebattle.model;

import jpokebattle.utils.AttackResult;
import jpokebattle.utils.MoveType;

public class AttackData {
    private int damage;
    private AttackResult moveResult;
    private AttackResult elemResult;
    private PokeStats effects;
    private MoveType type;

    public AttackData(int damage, AttackResult moveResult, AttackResult elemResult) {
        this.damage = damage;
        this.moveResult = moveResult;
        this.elemResult = elemResult;
        type = MoveType.PHYSICAL;
    }
    public AttackData(PokeStats effects)
    {
        this.effects = effects;
        type = MoveType.STATUS;
    }

    public MoveType getType() {
        return type;
    }
    public PokeStats getEffects() {
        return effects;
    }
    public int getDamage() {
        return damage;
    }
    public AttackResult getMoveResult() {
        return moveResult;
    }
    public AttackResult getElemResult() {
        return elemResult;
    }
}
