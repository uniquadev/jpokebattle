package jpokebattle.utils;

import java.util.ArrayList;

import jpokebattle.model.PokeMove;
import jpokebattle.model.Pokemon;
import jpokebattle.model.Trainer;

// Decoratore per Trainer, gestisce le azioni del computer
public class TrainerAI extends Trainer {
    
    public class AIAction {
        public enum ActionType {
            ATTACK,
            ITEM,
            POKEMON
        }
        private ActionType type;
        private Pokemon pokemon;
        private PokeMove move;

        public AIAction(ActionType type, Pokemon pokemon, PokeMove move) {
            this.type = type;
            this.pokemon = pokemon;
            this.move = move;
        }

        public ActionType getType() {
            return type;
        }
        public Pokemon getPokemon() {
            return pokemon;
        }
        public PokeMove getMove() {
            return move;
        }
    }

    public Pokemon handlePokemonChange() {
        ArrayList<Pokemon> available = getAvailablePokemons();
        Pokemon selected = null;
        if (available.size() == 0) {
            return selected;
        }
        else if (available.size() == 1) {
            selected = available.get(0);
        }
        else {
            // TODO: choose the best pokemon based on ai difficulty
            selected = available.get(0);
        }
        if (selected != null)
            setCurrentPokemon(selected);
        return selected;
    }
    

    public AIAction handleTurn() {
        return null;
    }
}
