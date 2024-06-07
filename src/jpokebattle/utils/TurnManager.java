package jpokebattle.utils;

import java.util.Random;

import jpokebattle.cpu.CPUHandler;
import jpokebattle.cpu.EasyCPUHandler;
import jpokebattle.enums.CPUActionType;
import jpokebattle.interfaces.CPUInterface;
import jpokebattle.model.AttackData;
import jpokebattle.model.CPUAction;
import jpokebattle.model.PokeMove;
import jpokebattle.model.Pokemon;
import jpokebattle.model.Trainer;
import jpokebattle.service.GameState;

/**
 * The TurnManager class handles the turn-based logic of the game.
 * It determines which trainer's turn it is and provides methods for handling the CPU's actions.
 */
public class TurnManager implements CPUInterface {
    private GameState state;
    private Trainer player;
    private Trainer opponent;
    private Trainer currentTrainerTurn;
    private CPUHandler cpuHandler;

    /**
     * Constructs a TurnManager object with the specified game state and player.
     * @param state The game state.
     * @param player The player trainer.
     */
    public TurnManager(GameState state, Trainer player) {
        this.state = state;
        this.player = player;
        generateNewOpponent();
    }

    /**
     * Starts the battle between the player and the opponent.
     * @param player The player trainer.
     * @param opponent The opponent trainer.
     */
    public void start(Trainer player, Trainer opponent) {
        this.player = player;
        this.opponent = opponent;
        currentTrainerTurn = player;
    }

    /**
     * Checks if it is currently the player's turn.
     * @return true if it is the player's turn, false otherwise.
     */
    public boolean isPlayerTurn() {
        return currentTrainerTurn == player;
    }

    /**
     * Checks if it is currently the AI's turn.
     * @return true if it is the AI's turn, false otherwise.
     */
    public boolean isAITurn() {
        return currentTrainerTurn != player && currentTrainerTurn != null;
    }

    /**
     * Gets the opponent trainer.
     * @return The opponent trainer.
     */
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

    /**
     * Performs an attack by an attacker Pokemon on a defender Pokemon using the given move.
     * 
     * @param attacker the Pokemon performing the attack
     * @param defender the Pokemon being attacked
     * @param move the move being used for the attack
     * @return the AttackData object representing the attack result
     */
    public AttackData performAttack(Pokemon attacker, Pokemon defender, PokeMove move) {
        AttackData attack = move.perform(defender, defender);
        switch (attack.getType()) {
            case PHYSICAL:
            case SPECIAL:
                defender.damage(attack.getDamage());
                break;
            case STATUS:
                defender.getEffects().sum(attack.getEffects());
            default:
                break;
        }
        move.consumeUse();
        return attack;
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
        start(player, builder.generate());
        setupCPU();
        System.out.println("CPU generata!");
    }

    @Override
    public Pokemon handlePokemonChange() {
        return cpuHandler.handlePokemonChange();
    }

    /**
     * Handles the turn for the CPU player.
     * This method determines the action to be taken by the CPU player and performs the necessary operations.
     * If the action type is ATTACK, the CPU player's active Pokemon attacks the opponent's Pokemon with the specified move.
     * If the action type is POKEMON_SWITCH, the CPU player switches its active Pokemon to the specified Pokemon.
     * 
     * @return The CPU action performed during the turn.
     */
    @Override
    public CPUAction handleTurn() {
        currentTrainerTurn = opponent;
        CPUAction action = cpuHandler.handleTurn();
        if (action == null) {
            action = new CPUAction();
        }
        else if (action.getType() == CPUActionType.ATTACK) {
            Pokemon defender = action.getPokemon();
            Pokemon attacker = opponent.getActivePokemon();
            PokeMove move = action.getMove();
            performAttack(attacker, defender, move);
        }
        else if (action.getType() == CPUActionType.POKEMON_SWITCH) {
            Pokemon pokemon = action.getPokemon();
            opponent.setActivePokemon(pokemon);
        }
        currentTrainerTurn = player;
        return action;
    }

    /**
     * Simulates the player catching the opponent Pokemon.
     * @return The caught Pokemon, or null if the catch attempt fails.
     */
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
