package jpokebattle.battle;

import jpokebattle.cpu.CPUHandler;
import jpokebattle.cpu.EasyCPUHandler;
import jpokebattle.model.BattleAction;
import jpokebattle.service.GameState;
import jpokebattle.utils.GenerationSettings;
import jpokebattle.utils.TrainerFactory;

import java.util.List;

// aggiunge la gestione della battaglia
// generando nuovi avversari in base allo stato del gioco

public class BattleHandler extends TurnHandler {
    private GameState state;
    private CPUHandler cpuHandler;

    public BattleHandler(GameState state) {
        this.state = state;
        this.player = state.getPlayer();
        generateNewOpponent();
    }

    private void setupCPU() {
        switch (state.getCurrentDifficulty()) {
            case EASY:
                cpuHandler = new EasyCPUHandler(cpu, player);
                break;
            default:
                cpuHandler = new EasyCPUHandler(cpu, player);
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
            .setMinPokeLevel(player.getTeamLevel() + settings.getMinLevelOffset())
            .setMaxPokeLevel(player.getTeamLevel() + settings.getMaxLevelOffset())
            .setMinEvolution(player.getBestEvolution() + settings.getMinEvolutionOffset())
            .setMaxEvolution(player.getBestEvolution() + settings.getMaxEvolutionOffset());
        cpu = builder.generate();
        setupCPU();
    }

    public List<BattleAction> startTurn() {
        BattleAction cpuAction = cpuHandler.handleTurn();
        if (cpuAction == null) cpuAction = new BattleAction();
        setCPUAction(cpuAction);
        return getOrderedActions();
    }
}
