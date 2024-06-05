package jpokebattle.service;

import jpokebattle.model.Trainer;
import jpokebattle.utils.AIGenerationSettings;
import jpokebattle.utils.TrainerAI;
import jpokebattle.utils.TrainerBuilder;

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
    public static enum Difficulty {
        EASY,
        MEDIUM,
        HARD,
        IMPOSSIBLE
    }
    
    private State state = State.MENU;
    private Trainer player;
    private TrainerAI trainer;
    
    private int score = 0;
    private Trainer currentTrainerTurn;

    public void increaseScore() {
        score++;
    }

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
        currentTrainerTurn = player;
    }
    public TrainerAI getAI() {
        return trainer;
    }
    public void setAI(TrainerAI trainer) {
        this.trainer = trainer;
    }

    public Difficulty getCurrentDifficulty() {
        if (score < 10)
            return Difficulty.EASY;
        if (score < 20)
            return Difficulty.MEDIUM;
        if (score < 30)
            return Difficulty.HARD;
        return Difficulty.IMPOSSIBLE;
    }

    public boolean isPlayerTurn() {
        return currentTrainerTurn == player;
    }
    public boolean isAITurn() {
        return currentTrainerTurn != player;
    }

    public void generateAI() {        
        AIGenerationSettings settings = new AIGenerationSettings(getCurrentDifficulty());
        TrainerBuilder builder = new TrainerBuilder()
            .setTeamSize(player.getTeamSize() + settings.getTeamDelta())
            .setMinPokeLevel(player.getTeamLevel() + settings.getLevelMinDelta())
            .setMaxPokeLevel(player.getTeamLevel() + settings.getLevelMaxDelta())
            .setMaxEvolution(player.getBestEvolution() + settings.getEvolutionDelta());
        setAI(builder.generate());
        System.out.println("AI generato!");
    }
}
