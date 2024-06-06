package jpokebattle.model;

import jpokebattle.enums.AIActionType;

public class AIAction {
    private AIActionType type;
    private Pokemon pokemon;
    private PokeMove move;

    public AIAction(AIActionType type, Pokemon pokemon, PokeMove move) {
        this.type = type;
        this.pokemon = pokemon;
        this.move = move;
    }

    public AIActionType getType() {
        return type;
    }
    public Pokemon getPokemon() {
        return pokemon;
    }
    public PokeMove getMove() {
        return move;
    }
}
