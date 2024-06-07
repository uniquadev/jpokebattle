package jpokebattle.model;

import jpokebattle.enums.CPUActionType;

public class BattleAction {
    private CPUActionType type;
    private Pokemon pokemon;
    private PokeMove move;

    public BattleAction(PokeMove move) {
        this.move = move;
        this.type = CPUActionType.ATTACK;
    }

    public BattleAction(Pokemon pokemon) {
        this.pokemon = pokemon;
        this.type = CPUActionType.POKEMON_SWITCH;
    }

    public BattleAction() {
        type = CPUActionType.SKIP;
    }

    public CPUActionType getType() {
        return type;
    }
    public Pokemon getPokemon() {
        return pokemon;
    }
    public PokeMove getMove() {
        return move;
    }
}
