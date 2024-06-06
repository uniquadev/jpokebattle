package jpokebattle.utils;

import java.util.Random;

import jpokebattle.cpu.CPUHandler;
import jpokebattle.cpu.EasyCPUHandler;
import jpokebattle.interfaces.CPUInterface;
import jpokebattle.model.CPUAction;
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
        GenerationSettings settings = new GenerationSettings(state.getCurrentDifficulty());
        TrainerBuilder builder = new TrainerBuilder()
            .setMinTeamSize(settings.getMinTeamSize())
            .setMaxTeamSize(settings.getMaxTeamSize())
            .setMinPokeLevel(player.getTeamLevel() + settings.getMinLevelOffset())
            .setMaxPokeLevel(player.getTeamLevel() + settings.getMaxLevelOffset())
            .setMinEvolution(player.getBestEvolution() + settings.getMinEvolutionOffset())
            .setMaxEvolution(player.getBestEvolution() + settings.getMaxEvolutionOffset());
        start(player, builder.generate());
        setupCPU();
        System.out.println("CPU generata!");
    }

    @Override
    public Pokemon handlePokemonChange() {
        return cpuHandler.handlePokemonChange();
    }

    @Override
    public CPUAction handleTurn() {
        return cpuHandler.handleTurn();
    }

    public Pokemon playerCatchPokemon() {
        Random random = new Random();
        int chance = random.nextInt(1, 3);
        if (chance == 1)
            return null;
        var pokemons = opponent.getAllPokemons();
        Pokemon pokemon = pokemons.get(random.nextInt(0, pokemons.size()));
        pokemon.restore();
        return pokemon;
    }
}
