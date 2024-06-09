package jpokebattle.cpu;

import java.util.ArrayList;

import jpokebattle.enums.AttackEffectiveness;
import jpokebattle.enums.MoveType;
import jpokebattle.model.BattleAction;
import jpokebattle.model.PokeMove;
import jpokebattle.model.Pokemon;
import jpokebattle.model.Trainer;

public class MediumCPUHandler extends EasyCPUHandler {

    public MediumCPUHandler(Trainer trainer, Trainer opponent) {
        super(trainer, opponent);
    }

    @Override
    public Pokemon handlePokemonChange() {
        ArrayList<Pokemon> available = cpu.getAvailablePokemons();
        if (available.size() == 0) return null;
        Pokemon toAttack = opponent.getActivePokemon();
        Pokemon active = cpu.getActivePokemon();

        if (active.isDead())
            return super.handlePokemonChange();

        int bestEff = 0;
        int bestLevel = 0;
        Pokemon bestPokemon = null;

        for (Pokemon pokemon : available) {
            if (pokemon == active) continue;
            AttackEffectiveness eff = pokemon.getElement().getAttackResultTo(toAttack.getElement());
            if (eff == AttackEffectiveness.NOT_EFFECTIVE) continue;
            if (eff.ordinal() > bestEff) {
                bestEff = eff.ordinal();
                bestPokemon = pokemon;
            } else if (eff.ordinal() == bestEff && pokemon.getLevel() > bestLevel) {
                bestLevel = pokemon.getLevel();
                bestPokemon = pokemon;
            }
        }

        return bestPokemon;
    }

    @Override
    public BattleAction handleTurn() {
        Pokemon toAttack = opponent.getActivePokemon();
        Pokemon myPokemon = cpu.getActivePokemon();
        // check elemental effectiveness
        AttackEffectiveness effectiveness = myPokemon.getElement().getAttackResultTo(toAttack.getElement());
        if (effectiveness == AttackEffectiveness.NOT_EFFECTIVE) {
            // try change pokemon
            Pokemon newPokemon = handlePokemonChange();
            if (newPokemon != null)
                return makeAction(newPokemon);
        }
        // check if there are moves
        var moves = cpu.getActivePokemon().getMoves();
        if (moves.size() == 0) {
            // try change pokemon
            Pokemon newPokemon = handlePokemonChange();
            if (newPokemon != null)
                return makeAction(newPokemon);
            return null;
        }
        // try to attack 1/3 of the time
        int attDecision = random.nextInt(3);
        PokeMove move = null;
        if (attDecision == 0) {
            move = getFirstMove(MoveType.PHYSICAL);
        }
        else {
            move = getFirstMove(MoveType.STATUS);
        }
        if (move == null) {
            move = moves.get(0);    // just use the first move if no move is found for the decision
        }
        return makeAction(move);
    }
    
}
