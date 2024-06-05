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
        Random randomGen = new Random();
        int critical = randomGen.nextInt(1, 3);
        int power = getPower();
        AttackResult elemResult = attacker.getElement().getAttackResult(defender.getElement());
        AttackResult moveResult = getElement().getAttackResult(defender.getElement());
        float stab = attacker.getElement() == defender.getElement() ? 1.5f : 1.0f;
        int level = attacker.getLevel();
        int attack, defense;

        if (getType() == MoveType.ATTACK) {
            attack = attacker.getTotAttack();
            defense = defender.getTotDefense();
        } else {
            attack = attacker.getTotSpecial();
            defense = defender.getTotSpecial();
        }

        Double damage = ((((2*level*critical)/5 + 2) * power * (attack/defense)) / 50 + 2) * stab * elemResult.getEffectiveness() * moveResult.getEffectiveness();
        double random = randomGen.nextDouble(217, 255) / 255;
        if (damage.intValue() == 1)
            random = 1;
        damage *= random;

        if (damage == 0)
            return new AttackData();

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
