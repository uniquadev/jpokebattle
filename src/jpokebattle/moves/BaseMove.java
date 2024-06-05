package jpokebattle.moves;

import java.util.Random;

import jpokebattle.controller.BattleController;
import jpokebattle.model.AttackData;
import jpokebattle.model.Pokemon;
import jpokebattle.utils.AttackResult;
import jpokebattle.utils.PokeElement;

public abstract class BaseMove implements MoveInterface {
    private int usesLeft = 0;
    private int requiredLevel = 0;
    private int slot = 0;

    public BaseMove(int requiredLevel) {
        this.requiredLevel = requiredLevel;
        this.usesLeft = getMaxUses();
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
    public int getSlot() {
        return slot;
    }

    public int getUsesLeft() {
        return usesLeft;
    }
    public void consumeUse() {
        usesLeft--;
    }
    public void resetUses() {
        usesLeft = getMaxUses();
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
    public int getRequiredLevel() {
        return requiredLevel;
    }

    @Override
    public AttackData perform(Pokemon attacker, Pokemon defender) {
        return calculateDamage(attacker, defender);
    }

    @Override
    public void draw(BattleController bc, Pokemon attacker, Pokemon defender) {
        
    }
}
