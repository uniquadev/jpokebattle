package jpokebattle.utils;

import jpokebattle.cpu.CPUHandler;
import jpokebattle.cpu.EasyCPUHandler;
import jpokebattle.interfaces.CPUInterface;
import jpokebattle.model.AIAction;
import jpokebattle.model.Pokemon;
import jpokebattle.model.Trainer;
import jpokebattle.service.GameState;

public class TurnManager implements CPUInterface {
    private GameState state;
    private Trainer player;
    private Trainer opponent;
    private Trainer currentTrainerTurn;
    private CPUHandler cpuHandler;

    public TurnManager(GameState state, Trainer player) {
        this.state = state;
        this.player = player;
        generateNewOpponent();
    }

    public void start(Trainer player, Trainer opponent) {
        this.player = player;
        this.opponent = opponent;
        currentTrainerTurn = player;
    }

    public boolean isPlayerTurn() {
        return currentTrainerTurn == player;
    }
    public boolean isAITurn() {
        return currentTrainerTurn != player;
    }

    public Trainer getOpponent() {
        return opponent;
    }

    private void setupCPU() {
        switch (state.getCurrentDifficulty()) {
            case EASY:
                cpuHandler = new EasyCPUHandler(opponent);
                break;
            default:
                cpuHandler = new EasyCPUHandler(opponent);
                break;
        }
    }

    public void generateNewOpponent() {
        AIGenerationSettings settings = new AIGenerationSettings(state.getCurrentDifficulty());
        TrainerBuilder builder = new TrainerBuilder()
            .setTeamSize(player.getTeamSize() + settings.getTeamDelta())
            .setMinPokeLevel(player.getTeamLevel() + settings.getLevelMinDelta())
            .setMaxPokeLevel(player.getTeamLevel() + settings.getLevelMaxDelta())
            .setMaxEvolution(player.getBestEvolution() + settings.getEvolutionDelta());
        start(player, builder.generate());
        setupCPU();
        System.out.println("AI generato!");
    }

    @Override
    public Pokemon handlePokemonChange() {
        return cpuHandler.handlePokemonChange();
    }

    @Override
    public AIAction handleTurn() {
        return cpuHandler.handleTurn();
    }
}
