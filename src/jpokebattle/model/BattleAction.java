package jpokebattle.model;

import jpokebattle.enums.BattleActionType;

/**
 * Represents a battle action performed by a trainer in a Pokemon battle.
 * A battle action can be an attack, a Pokemon switch, or skipping a turn.
 */
public class BattleAction implements Comparable<BattleAction> {
    private BattleActionType type;
    private Trainer trainer;
    private Trainer opponent;
    private Pokemon pokemon;
    private PokeMove move;

    /**
     * Constructs a BattleAction object for an attack action.
     * 
     * @param trainer The trainer performing the action.
     * @param opponent The opponent trainer.
     * @param move The move to be used for the attack.
     */
    public BattleAction(Trainer trainer, Trainer opponent, PokeMove move) {
        this.trainer = trainer;
        this.opponent = opponent;
        this.move = move;
        this.type = BattleActionType.ATTACK;
    }

    /**
     * Constructs a BattleAction object for a Pokemon switch action.
     * 
     * @param trainer The trainer performing the action.
     * @param pokemon The Pokemon to be switched in.
     */
    public BattleAction(Trainer trainer, Pokemon pokemon) {
        this.trainer = trainer;
        this.pokemon = pokemon;
        this.type = BattleActionType.POKEMON_SWITCH;
    }

    /**
     * Constructs a BattleAction object for skipping a turn.
     */
    public BattleAction() {
        type = BattleActionType.SKIP;
    }

    /**
     * Gets the type of the battle action.
     * 
     * @return The type of the battle action.
     */
    public BattleActionType getType() {
        return type;
    }

    /**
     * Gets the trainer performing the action.
     * 
     * @return The trainer performing the action.
     */
    public Trainer getTrainer() {
        return trainer;
    }

    /**
     * Gets the opponent trainer if the trainer is attacking.
     * 
     * @return The opponent trainer.
     */
    public Trainer getOpponent() {
        return opponent;
    }

    /**
     * Gets the Pokemon associated with the action.
     * 
     * @return The Pokemon associated with the action.
     */
    public Pokemon getPokemon() {
        return pokemon;
    }

    /**
     * Gets the move associated with the attack action.
     * 
     * @return The move associated with the attack action.
     */
    public PokeMove getMove() {
        return move;
    }

    /**
     * Compares this BattleAction with another BattleAction for sorting purposes.
     * 
     * @param o The BattleAction to compare with.
     * @return -1 if this BattleAction should be placed before the other BattleAction,
     *         1 if this BattleAction should be placed after the other BattleAction,
     *         0 if they are considered equal.
     */
    @Override
    public int compareTo(BattleAction o) {
        if (o.type != type) {
            if (type == BattleActionType.POKEMON_SWITCH) return -1;
            if (o.type == BattleActionType.POKEMON_SWITCH) return 1;
        }
        if (trainer.getActivePokemon().getTotSpeed() > opponent.getActivePokemon().getTotSpeed()) return -1;
        return 0;
    }

    /**
     * Applies the effects of an attack to the opponent's active Pokemon.
     * 
     * @param attack The result of the attack.
     */
    private void applyAttack(MoveResult attack) {
        Pokemon defender = opponent.getActivePokemon();
        switch (attack.getType()) {
            case PHYSICAL:
            case SPECIAL:
                defender.damage(attack.getDamage());
                break;
            case STATUS:
                defender.getEffects().sum(attack.getEffects());
            default:
                break;
        }
        move.consumeUses();
    }

    /**
     * Performs the battle action and returns the result of the action.
     * 
     * @return The result of the attack action, or null if the action has no result.
     */
    public MoveResult performAction() {
        Pokemon attacker = trainer.getActivePokemon();
        MoveResult result = null;
        switch (type) {
            case ATTACK:
                result = move.perform(attacker, opponent.getActivePokemon());
                break;
            case POKEMON_SWITCH:
                Pokemon oldPokemon = trainer.getActivePokemon();
                trainer.setActivePokemon(pokemon);
                this.pokemon = oldPokemon; // store the old pokemon to hold information after the switch
                break;
            default:
                break;
        }
        if (result != null) {
            applyAttack(result);
        }
        return result;
    }
}
