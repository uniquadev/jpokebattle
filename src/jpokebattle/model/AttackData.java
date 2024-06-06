package jpokebattle.model;

import jpokebattle.enums.AttackResult;
import jpokebattle.enums.MoveType;

public class AttackData {
    private int damage;
    private AttackResult moveResult;
    private AttackResult elemResult;
    private boolean critical;
    private PokeStats effects;
    private MoveType type;

    public AttackData(int damage, boolean critical, AttackResult moveResult, AttackResult elemResult) {
        this.damage = damage;
        this.moveResult = moveResult;
        this.elemResult = elemResult;
        this.critical = critical;
        type = MoveType.ATTACK;
    }
    public AttackData(PokeStats effects)
    {
        this.effects = effects;
        type = MoveType.STATUS;
    }
    public AttackData() {
        type = MoveType.MISS;
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
    public boolean isCritical() {
        return critical;
    }
}
