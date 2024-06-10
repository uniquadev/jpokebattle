package jpokebattle.battle;

import jpokebattle.model.BattleAction;
import jpokebattle.model.Trainer;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

/**
 * The abstract class TurnHandler represents a handler for a turn in a battle between two trainers.
 * It provides methods to set the actions of the players and retrieve the ordered actions for the turn.
 */
public abstract class TurnHandler {
    protected Trainer player1;
    protected Trainer player2;

    private BattleAction player1Action;
    private BattleAction player2Action;

    public void setPlayer1Action(BattleAction action) {
        player1Action = action;
    }
    public void setPlayer2Action(BattleAction action) {
        player2Action = action;
    }
    
    protected List<BattleAction> getOrderedActions() {
        if (player1Action == null || player2Action == null) {
            throw new IllegalStateException("Both player and CPU actions must be set before starting the turn.");
        }

        List<BattleAction> actions = new ArrayList<>(2);
        actions.add(player1Action);
        actions.add(player2Action);
        Collections.sort(actions);

        player1Action = null;
        player2Action = null;

        return actions;
    }
}
