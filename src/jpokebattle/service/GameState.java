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
    private Trainer player;
    private TrainerAI trainer;
    
    private int score = 0;
    private Trainer currentTrainerTurn = player;

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }
    
    public Trainer getPlayer() {
        return player;
    }
    public void setPlayer(Trainer player) {
        this.player = player;
    }
    public Trainer getAI() {
        return trainer;
    }
    public void setAI(TrainerAI trainer) {
        this.trainer = trainer;
    }

    public boolean isPlayerTurn() {
        return currentTrainerTurn == player;
    }
    public boolean isAITurn() {
        return currentTrainerTurn != player;
    }
}
