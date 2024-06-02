package jpokebattle;

import jpokebattle.model.Trainer;
import jpokebattle.utils.TrainerAI;

public class GameState {
    public static enum State {
        MENU,
        BATTLE
    }
    
    private State state = State.MENU;
    private Trainer player;
    private TrainerAI trainer;
    private int score = 0;

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }
    
    public Trainer getPlayer() {
        return player;
    }
    public Trainer getAI() {
        return trainer;
    }

    public void startBattle() {
         
    }
}
