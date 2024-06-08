package jpokebattle.model;

import jpokebattle.enums.BattleActionType;

public class BattleAction implements Comparable<BattleAction> {
    private BattleActionType type;
    private Trainer trainer;
    private Trainer opponent;
    private Pokemon pokemon;
    private PokeMove move;

    private BattleAction(Trainer trainer, Trainer opponent) {
        this.trainer = trainer;
        this.opponent = opponent;
    }

    public BattleAction(Trainer trainer, Trainer opponent, PokeMove move) {
        this(trainer, opponent);
        this.move = move;
        this.type = BattleActionType.ATTACK;
    }

    public BattleAction(Trainer trainer, Pokemon pokemon) {
        this.trainer = trainer;
        this.pokemon = pokemon;
        this.type = BattleActionType.POKEMON_SWITCH;
    }

    public BattleAction() {
        type = BattleActionType.SKIP;
    }

    public BattleActionType getType() {
        return type;
    }
    public Trainer getTrainer() {
        return trainer;
    }
    public Trainer getOpponent() {
        return opponent;
    }
    public Pokemon getPokemon() {
        return pokemon;
    }
    public PokeMove getMove() {
        return move;
    }

    @Override
    public int compareTo(BattleAction o) {
        if (type == BattleActionType.POKEMON_SWITCH) return Integer.MAX_VALUE;
        if (type == BattleActionType.ATTACK) return trainer.getActivePokemon().getTotSpeed();
        return 0;
    }

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

    public MoveResult performAction() {
        Pokemon attacker = trainer.getActivePokemon();
        MoveResult result = null;
        switch (type) {
            case ATTACK:
                result = move.perform(attacker, opponent.getActivePokemon());
                break;
            case POKEMON_SWITCH:
                trainer.setActivePokemon(pokemon);
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
