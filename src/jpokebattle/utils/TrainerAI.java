package jpokebattle.utils;

import java.util.ArrayList;

import jpokebattle.model.Pokemon;
import jpokebattle.model.Trainer;

// Decoratore per Trainer, gestisce le azioni del computer
public class TrainerAI extends Trainer {
    
    public void handlePokemonChange() {
        ArrayList<Pokemon> available = getAvailablePokemons();
        if (available.size() == 0) {
            return;
        }
        else if (available.size() == 1) {
            setCurrentPokemon(available.get(0));
        }
        else {
            // TODO: choose the best pokemon based on ai difficulty
            setCurrentPokemon(available.get(0));
        }
    }

    public void handleTurn() {
    }
}
