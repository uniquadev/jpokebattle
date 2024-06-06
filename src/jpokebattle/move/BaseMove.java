package jpokebattle.move;

import java.util.Random;

import jpokebattle.controller.BattleController;
import jpokebattle.enums.AttackResult;
import jpokebattle.enums.MoveType;
import jpokebattle.enums.PokeElement;
import jpokebattle.model.AttackData;
import jpokebattle.model.PokeStats;
import jpokebattle.model.Pokemon;

/**
 * The BaseMove class is an abstract class that represents a basic move in a Pokemon battle.
 * It implements the MoveInterface interface and provides common functionality for all moves.
 */
public abstract class BaseMove implements MoveInterface {
    private int requiredLevel = 1;

    /**
     * Constructs a BaseMove object with the specified required level.
     * @param requiredLevel the level required to use this move
     */
    public BaseMove(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    /**
     * Gets the required level to use this move.
     * @return the required level
     */
    public int getRequiredLevel() {
        return requiredLevel;
    }

    /**
     * Gets the effects of this move.
     * @return the effects of this move as a PokeStats object
     */
    public PokeStats getEffects() {
        return new PokeStats();
    }

    /**
     * Performs the move by calculating the damage or applying the status effects.
     * @param attacker the attacking Pokemon
     * @param defender the defending Pokemon
     * @return an AttackData object representing the result of the move
     */
    @Override
    public AttackData perform(Pokemon attacker, Pokemon defender) {
        if (getType() == MoveType.STATUS) {
            PokeStats effects = getEffects();
            return new AttackData(effects);
        }
        return calculateDamage(attacker, defender);
    }

    /**
     * Calculates the damage of the move based on the attacker's and defender's stats.
     * @param attacker the attacking Pokemon
     * @param defender the defending Pokemon
     * @return an AttackData object representing the damage and other information of the move
     */
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
        damage = Math.ceil(damage);
        
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

    /**
     * Gets the power of the move.
     * @return the power of the move
     */
    @Override
    public int getPower() {
        return 0;
    }

    /**
     * Gets the element type of the move.
     * @return the element type of the move
     */
    @Override
    public PokeElement getElement() {
        return PokeElement.NORMAL;
    }

    /**
     * Gets the maximum number of uses for the move.
     * @return the maximum number of uses
     */
    @Override
    public int getMaxUses() {
        return 10;
    }

    /**
     * Draws the move on the battle controller.
     * @param bc the battle controller
     * @param attacker the attacking Pokemon
     * @param defender the defending Pokemon
     */
    @Override
    public void draw(BattleController bc, Pokemon attacker, Pokemon defender) {}
}
