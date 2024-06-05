package jpokebattle.moves;

import java.util.Random;

import jpokebattle.controller.BattleController;
import jpokebattle.model.AttackData;
import jpokebattle.model.PokeStats;
import jpokebattle.model.Pokemon;
import jpokebattle.utils.AttackResult;
import jpokebattle.utils.MoveType;
import jpokebattle.utils.PokeElement;

public abstract class BaseMove implements MoveInterface {
    private int requiredLevel = 1;

    public BaseMove(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public PokeStats getEffects() {
        return new PokeStats();
    }

    @Override
    public AttackData perform(Pokemon attacker, Pokemon defender) {
        if (getType() == MoveType.STATUS) {
            PokeStats effects = getEffects();
            return new AttackData(effects);
        }
        return calculateDamage(attacker, defender);
    }

    public AttackData calculateDamage(Pokemon attacker, Pokemon defender) {
        Random random = new Random();
        int critical = random.nextInt(1, 2);
        int power = getPower();
        AttackResult elemResult = attacker.getElement().getAttackResult(defender.getElement());
        AttackResult moveResult = getElement().getAttackResult(defender.getElement());
        float stab = attacker.getElement() == defender.getElement() ? 1.5f : 1.0f;
        int level = attacker.getLevel();
        int attack, defense;

        if (getElement() == PokeElement.NORMAL) {
            attack = attacker.getAttack();
            defense = defender.getDefense();
        } else {
            attack = attacker.getTotSpecial();
            defense = defender.getTotSpecial();
        }

        Double damage = ((((2*level*critical)/5 + 2) * power * (attack/defense)) / 50 + 2) * stab * elemResult.getEffectiveness() * moveResult.getEffectiveness();
        return new AttackData(damage.intValue(),
            critical == 2,
            moveResult,
            elemResult
        );
    }

    @Override
    public int getPower() {
        return 0;
    }

    @Override
    public PokeElement getElement() {
        return PokeElement.NORMAL;
    }

    @Override
    public int getMaxUses() {
        return 10;
    }

    @Override
    public void draw(BattleController bc, Pokemon attacker, Pokemon defender) {}
}
