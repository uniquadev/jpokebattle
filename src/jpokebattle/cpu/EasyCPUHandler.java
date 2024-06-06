package jpokebattle.cpu;

import java.util.ArrayList;

import jpokebattle.model.AIAction;
import jpokebattle.model.Pokemon;
import jpokebattle.model.Trainer;

public class EasyCPUHandler extends CPUHandler {

    public EasyCPUHandler(Trainer trainer) {
        super(trainer);
    }

    @Override
    public Pokemon handlePokemonChange() {
        ArrayList<Pokemon> available = trainer.getAvailablePokemons();
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
            trainer.setCurrentPokemon(selected);
        return selected;
    }
    
    @Override
    public AIAction handleTurn() {
        return null;
    }
    
}
