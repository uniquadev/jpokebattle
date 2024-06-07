package jpokebattle.cpu;

import java.util.ArrayList;

import jpokebattle.enums.MoveType;
import jpokebattle.model.CPUAction;
import jpokebattle.model.Pokemon;
import jpokebattle.model.Trainer;

public class EasyCPUHandler extends CPUHandler {

    public EasyCPUHandler(Trainer trainer, Trainer opponent) {
        super(trainer, opponent);
    }

    @Override
    public Pokemon handlePokemonChange() {
        ArrayList<Pokemon> available = trainer.getAvailablePokemons();
        Pokemon selected = null;
        if (available.size() == 0) {
            return null;
        }
        else {
            selected = available.get(0);
        }
        trainer.setActivePokemon(selected);
        return selected;
    }
    
    @Override
    public CPUAction handleTurn() {
        int attDecision = random.nextInt(3); // 66 chance of using status move
        var moves = trainer.getActivePokemon().getMoves();
        if (moves.size() == 0) {
            return null;
        }
        if (attDecision < 1) {
            for (var move : moves) {
                if (move.getType() == MoveType.PHYSICAL)
                    continue;
                return new CPUAction(move);
            }
        }
        else {
            for (var move : moves) {
                if (move.getType() == MoveType.STATUS)
                    continue;
                return new CPUAction(move);
            }
        }
        // just use the first move if no move is found for the decision
        return new CPUAction(moves.get(0));
    }
    
}
