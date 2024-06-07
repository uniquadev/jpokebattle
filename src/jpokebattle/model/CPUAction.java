package jpokebattle.model;

import jpokebattle.enums.CPUActionType;

public class CPUAction {
    private CPUActionType type;
    private Pokemon pokemon;
    private PokeMove move;

    public CPUAction(CPUActionType type, Pokemon pokemon, PokeMove move) {
        this.type = type;
        this.pokemon = pokemon;
        this.move = move;
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
