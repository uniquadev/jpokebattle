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

    public BattleAction(Trainer trainer, Trainer opponent,Pokemon pokemon) {
        this(trainer, opponent);
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

    public void perform() {

    }
}
