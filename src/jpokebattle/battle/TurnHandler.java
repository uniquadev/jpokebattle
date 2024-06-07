package jpokebattle.battle;

import jpokebattle.model.BattleAction;
import jpokebattle.model.Trainer;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public abstract class TurnHandler {
    protected Trainer player1;
    protected Trainer player2;

    private BattleAction playerAction;
    private BattleAction cpuAction;

    public void setPlayerAction(BattleAction action) {
        playerAction = action;
    }
    public void setCPUAction(BattleAction action) {
        cpuAction = action;
    }
    
    protected List<BattleAction> getOrderedActions() {
        if (playerAction == null || cpuAction == null) {
            throw new IllegalStateException("Both player and CPU actions must be set before starting the turn.");
        }

        List<BattleAction> actions = new ArrayList<>(2);
        actions.add(playerAction);
        actions.add(cpuAction);
        Collections.sort(actions);

        return actions;
    }
}
