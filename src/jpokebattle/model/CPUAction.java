package jpokebattle.model;

import jpokebattle.enums.CPUActionType;

public class CPUAction {
    private CPUActionType type;
    private Pokemon pokemon;
    private PokeMove move;

    public CPUAction(PokeMove move) {
        this.move = move;
        this.type = CPUActionType.ATTACK;
    }

    public CPUAction(Pokemon pokemon) {
        this.pokemon = pokemon;
        this.type = CPUActionType.POKEMON_SWITCH;
    }

    public CPUAction() {
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
