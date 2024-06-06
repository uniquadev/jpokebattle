package jpokebattle.utils;

import java.util.ArrayList;
import java.util.Random;

import jpokebattle.enums.PokeEnum;
import jpokebattle.model.PokeMove;
import jpokebattle.model.Pokemon;
import jpokebattle.move.BaseMove;
import jpokebattle.pokemon.PokemonInfo;
import jpokebattle.service.MoveService;
import jpokebattle.service.PokeService;

/**
 * The PokeBuilder class is responsible for generating Pokemon objects with customizable attributes.
 */
public class PokeBuilder {
    private Random random = new Random();
    private int minLevel = 1;
    private int maxLevel = 1;
    private int maxPS = 15;
    private int maxAttack = 15;
    private int maxDefense = 15;
    private int maxSpecial = 15;
    private int maxSpeed = 15;
    
    private PokeEnum id = PokeEnum.NONE;

    /**
     * Constructs a PokeBuilder object with the specified Pokemon ID.
     * @param id The ID of the Pokemon to generate.
     */
    public PokeBuilder(PokeEnum id) {
        this.id = id;
    }

    /**
     * Constructs a PokeBuilder object with the default Pokemon ID (Bulbasaur).
     */
    public PokeBuilder() {
        this(PokeEnum.BULBASAUR);
    }

    /**
     * Sets the ID of the Pokemon to generate.
     * @param id The ID of the Pokemon.
     */
    public void setId(PokeEnum id) {
        this.id = id;
    }

    /**
     * Sets the minimum level of the generated Pokemon.
     * @param minLevel The minimum level.
     */
    public void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
    }

    /**
     * Sets the maximum level of the generated Pokemon.
     * @param maxLevel The maximum level.
     */
    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    /**
     * Generates a Pokemon with the specified attributes.
     * @return The generated Pokemon.
     * @throws RuntimeException if the Pokemon with the specified ID is not found.
     */
    public Pokemon generate() {
        PokemonInfo info = PokeService.getInfoStatic(id);
        if (info == null)
            throw new RuntimeException("Pokemon not found: " + id);
        
        Pokemon pokemon = new Pokemon(info);

        pokemon.setPS(random.nextInt(maxPS + 1));
        pokemon.setAttack(random.nextInt(maxAttack + 1));
        pokemon.setDefense(random.nextInt(maxDefense + 1));
        pokemon.setSpecial(random.nextInt(maxSpecial + 1));
        pokemon.setSpeed(random.nextInt(maxSpeed + 1));

        pokemon.setLevel(Math.max(1, random.nextInt(minLevel, maxLevel+1)));
        pokemon.restoreHealth();

        generateMoves(pokemon);
        
        return pokemon;
    }

    private void generateMoves(Pokemon pokemon) {
        ArrayList<BaseMove> moves = MoveService.getSingleton().getSupportedMoves(pokemon);
        if (moves.isEmpty())
            return;
        if (moves.size() <= 4) {
            for (int i = 0; i < moves.size(); i++) {
                pokemon.setMove(i, new PokeMove(
                    moves.get(i)
                ));
            }
            return;
        }
        int counter = 0;
        while (counter < 4) {
            BaseMove move = moves.get(random.nextInt(moves.size()));
            pokemon.setMove(counter, new PokeMove(move));
            counter++;
            moves.remove(move);
        }
        return;
    }
}
