package jpokebattle.cpu;

import java.util.ArrayList;

import jpokebattle.enums.MoveType;
import jpokebattle.model.BattleAction;
import jpokebattle.model.Pokemon;
import jpokebattle.model.Trainer;

public class EasyCPUHandler extends CPUHandler {

    public EasyCPUHandler(Trainer trainer, Trainer opponent) {
        super(trainer, opponent);
    }

    @Override
    public Pokemon handlePokemonChange() {
        ArrayList<Pokemon> available = cpu.getAvailablePokemons();
        Pokemon selected = null;
        if (available.size() == 0) {
            return null;
        }
        else {
            selected = available.get(0);
        }
        cpu.setActivePokemon(selected);
        return selected;
    }
    
    @Override
    public BattleAction handleTurn() {
        int attDecision = random.nextInt(5); // chance of using status move
        var moves = cpu.getActivePokemon().getMoves();
        if (moves.size() == 0) {
            return null;
        }
        if (attDecision == 0) {
            for (var move : moves) {
                if (move.getType() == MoveType.STATUS)
                    continue;
                return makeAction(move);
            }
        }
        else {
            for (var move : moves) {
                if (move.getType() == MoveType.PHYSICAL)
                    continue;
                return makeAction(move);
            }
        }
        // just use the first move if no move is found for the decision
        return makeAction(moves.get(0));
    }
    
}
