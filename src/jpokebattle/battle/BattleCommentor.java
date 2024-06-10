package jpokebattle.battle;

import jpokebattle.enums.AttackEffectiveness;
import jpokebattle.model.BattleAction;
import jpokebattle.model.MoveResult;
import jpokebattle.model.Trainer;

/**
 * The BattleCommentor class is responsible for generating comments for battle actions in a Pokemon battle.
 * It provides methods to get the effectiveness text for a move, as well as generate comments for different types of battle actions.
 */
public class BattleCommentor {

    public static String getEffectivenessText(AttackEffectiveness effectiveness) {
        switch (effectiveness) {
            case MISS:
                return "ma il colpo non e' andato a buon fine";
            case NOT_EFFECTIVE:
                return "ma il colpo non e' molto efficace";
            case NORMAL:
                return "";
            case EFFECTIVE:
                return "facendo molti danni!";
            default:
                return "";
        }
    }

    public static String comment(BattleAction action) {
        return comment(action, null);
    }

    public static String comment(BattleAction action, MoveResult result) {
        Trainer trainer = action.getTrainer();
        switch (action.getType()) {
            case POKEMON_SWITCH:
                return trainer.getName() + " ha richiamato " + action.getPokemon().getName() + " e ha mandato in campo " + trainer.getActivePokemon().getName();
            case ATTACK:
                return trainer.getName() + ": " + trainer.getActivePokemon().getName() + " ha attaccato con " + action.getMove().getName() + " " + getEffectivenessText(result.getMoveResult());
            case SKIP:
                return trainer.getName() + " ha saltato il turno";
        }
        return "";
    }
}
