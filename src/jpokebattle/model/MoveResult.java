package jpokebattle.model;

import jpokebattle.enums.AttackEffectiveness;
import jpokebattle.enums.MoveType;

public class MoveResult {
    private int damage;
    private AttackEffectiveness moveResult;
    private AttackEffectiveness elemResult;
    private boolean critical;
    private PokeStats effects;
    private MoveType type;

    public MoveResult(int damage, boolean critical, AttackEffectiveness moveResult, AttackEffectiveness elemResult) {
        this.damage = damage;
        this.moveResult = moveResult;
        this.elemResult = elemResult;
        this.critical = critical;
        type = MoveType.PHYSICAL;
    }
    public MoveResult(PokeStats effects)
    {
        this.effects = effects;
        moveResult = AttackEffectiveness.NORMAL;
        elemResult = AttackEffectiveness.NORMAL;
        type = MoveType.STATUS;
    }
    public MoveResult() {
        type = MoveType.MISS;
        moveResult = AttackEffectiveness.MISS;
        elemResult = AttackEffectiveness.MISS;
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
    public AttackEffectiveness getMoveResult() {
        return moveResult;
    }
    public AttackEffectiveness getElemResult() {
        return elemResult;
    }
    public boolean isCritical() {
        return critical;
    }
}
