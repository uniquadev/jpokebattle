package jpokebattle.battle;

import jpokebattle.cpu.CPUHandler;
import jpokebattle.cpu.EasyCPUHandler;
import jpokebattle.interfaces.CPUInterface;
import jpokebattle.model.BattleAction;
import jpokebattle.model.Pokemon;
import jpokebattle.model.Trainer;
import jpokebattle.service.GameState;
import jpokebattle.utils.GenerationSettings;
import jpokebattle.utils.TrainerFactory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

// aggiunge la gestione della battaglia
// generando nuovi avversari in base allo stato del gioco

public class BattleHandler extends TurnHandler implements CPUInterface {
    private GameState state;
    private CPUHandler cpuHandler;

    public BattleHandler(GameState state) {
        this.state = state;
        this.player1 = state.getPlayer();
        generateNewOpponent();
    }

    public Trainer getCPU() {
        return player2;
    }

    private void setupCPU() {
        switch (state.getCurrentDifficulty()) {
            case EASY:
                cpuHandler = new EasyCPUHandler(player2, player1);
                break;
            default:
                cpuHandler = new EasyCPUHandler(player2, player1);
                break;
        }
    }

    /**
     * Generates a new opponent trainer based on the current game state.
     */
    public void generateNewOpponent() {
        GenerationSettings settings = new GenerationSettings(state.getCurrentDifficulty());
        TrainerFactory builder = new TrainerFactory()
            .setMinTeamSize(settings.getMinTeamSize())
            .setMaxTeamSize(settings.getMaxTeamSize())
            .setMinPokeLevel(player1.getTeamLevel() + settings.getMinLevelOffset())
            .setMaxPokeLevel(player1.getTeamLevel() + settings.getMaxLevelOffset())
            .setMinEvolution(settings.getMinEvolution())
            .setMaxEvolution(settings.getMaxEvolution());
        player2 = builder.generate();
        setupCPU();
    }

    public Queue<BattleAction> startTurn() {
        setPlayer2Action(handleTurn());
        return new LinkedList<>(getOrderedActions());
    }

    public Pokemon playerCatchPokemon() {
        Random random = new Random();
        int chance = random.nextInt(1, 3);
        if (chance == 1)
            return null;
        var pokemons = player2.getAllPokemons();
        Pokemon pokemon = pokemons.get(random.nextInt(0, pokemons.size()));
        pokemon.restore();
        return pokemon;
    }

    @Override
    public Pokemon handlePokemonChange() {
        return cpuHandler.handlePokemonChange();
    }

    @Override
    public BattleAction handleTurn() {
        BattleAction cpuAction = cpuHandler.handleTurn();
        if (cpuAction == null) cpuAction = new BattleAction();
        return cpuAction;
    }
}
