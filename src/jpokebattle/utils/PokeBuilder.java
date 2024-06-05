package jpokebattle.utils;

import java.util.ArrayList;
import java.util.Random;

import jpokebattle.model.PokeData;
import jpokebattle.model.PokeMove;
import jpokebattle.model.Pokemon;
import jpokebattle.moves.BaseMove;
import jpokebattle.service.MoveService;
import jpokebattle.service.PokeService;

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

    public PokeBuilder(PokeEnum id) {
        this.id = id;
    }

    public PokeBuilder()
    {
        this(PokeEnum.BULBASAUR);
    }

    public void setId(PokeEnum id) {
        this.id = id;
    }
    public void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
    }
    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public Pokemon generate()
    {
        PokeData data = PokeService.getDataStatic(id);
        if (data == null)
            throw new RuntimeException("Pokemon not found: " + id);
        
        Pokemon pokemon = new Pokemon();
        pokemon.setData(data);

        pokemon.setPS(random.nextInt(maxPS));
        pokemon.setAttack(random.nextInt(maxAttack));
        pokemon.setDefense(random.nextInt(maxDefense));
        pokemon.setSpecial(random.nextInt(maxSpecial));
        pokemon.setSpeed(random.nextInt(maxSpeed));

        pokemon.setLevel(Math.max(1, nextInt(minLevel, maxLevel)));
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
        // seleziona mosse casuali
        int counter = 0;
        while (counter < 4) {
            BaseMove move = moves.get(random.nextInt(moves.size()));
            pokemon.setMove(counter, new PokeMove(move));
            counter++;
            moves.remove(move);
        }
        return;
    }
    
    
    private int nextInt(int min, int max)
    {
        if (min >= max)
            return min;
        return random.nextInt(max - min + 1) + min;
    }
}
