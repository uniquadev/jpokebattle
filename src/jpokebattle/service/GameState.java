package jpokebattle.service;

import jpokebattle.model.Trainer;
import jpokebattle.utils.TrainerAI;

public class GameState {
    private static GameState singleton;
    public static GameState getSingleton() {
        if (singleton == null) {
            singleton = new GameState();
        }
        return singleton;
    }

    public static enum State {
        MENU,
        BATTLE
    }
    
    private State state = State.MENU;
    private Trainer player = new Trainer();
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
