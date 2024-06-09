package jpokebattle.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jpokebattle.enums.PokeEnum;
import jpokebattle.model.Pokemon;
import jpokebattle.move.*;

/**
 * The MoveService class is responsible for managing moves for different Pokemon.
 */
public class MoveService {
    private static MoveService singleton = null;

    /**
     * Returns the singleton instance of the MoveService class.
     * @return The singleton instance of the MoveService class.
     */
    public static MoveService getSingleton() {
        synchronized (MoveService.class) {
            if (singleton == null) {
                singleton = new MoveService();
            }
        }
        return singleton;
    }

    private HashMap<PokeEnum, ArrayList<BaseMove>> library = new HashMap<>();

    private MoveService() {
        registerMoves();
    }

    /**
     * Registers the available moves for different Pokemon.
     */
    private void registerMoves() {
        registerMove(PokeEnum.BULBASAUR, new Growl(1));
        registerMove(PokeEnum.BULBASAUR, new Tackle(1));
        registerMove(PokeEnum.BULBASAUR, new VineWhip(3));

        registerMove(PokeEnum.IVYSAUR, new Growl(1));
        registerMove(PokeEnum.IVYSAUR, new Tackle(1));
        registerMove(PokeEnum.IVYSAUR, new VineWhip(1));

        registerMove(PokeEnum.VENUSAUR, new Growl(1));
        registerMove(PokeEnum.VENUSAUR, new Tackle(1));
        registerMove(PokeEnum.VENUSAUR, new VineWhip(1));
        
        registerMove(PokeEnum.CHARMANDER, new Growl(1));
        registerMove(PokeEnum.CHARMANDER, new Scratch(1));
        registerMove(PokeEnum.CHARMANDER, new Ember(4));
        registerMove(PokeEnum.CHARMANDER, new DragonBreath(12));
        
        registerMove(PokeEnum.CHARMELEON, new Growl(1));
        registerMove(PokeEnum.CHARMELEON, new Ember(1));
        registerMove(PokeEnum.CHARMELEON, new DragonBreath(12));

        registerMove(PokeEnum.CHARIZARD, new DragonClaw(1));
        registerMove(PokeEnum.CHARIZARD, new Ember(1));
        registerMove(PokeEnum.CHARIZARD, new DragonBreath(12));

        registerMove(PokeEnum.SQUIRTLE, new TailWhip(1));
        registerMove(PokeEnum.SQUIRTLE, new Tackle(1));
        registerMove(PokeEnum.SQUIRTLE, new WaterGun(3));

        registerMove(PokeEnum.WARTORTLE, new TailWhip(1));
        registerMove(PokeEnum.WARTORTLE, new Tackle(1));
        registerMove(PokeEnum.WARTORTLE, new WaterGun(1));
    }

    /**
     * Registers a move for a specific Pokemon.
     * @param id The ID of the Pokemon.
     * @param move The move to be registered.
     */
    private void registerMove(PokeEnum id, BaseMove move) {
        if (!library.containsKey(id)) {
            library.put(id, new ArrayList<>());
        }
        library.get(id).add(move);
    }

    /**
     * Returns the available moves for a specific Pokemon.
     * @param id The ID of the Pokemon.
     * @return The available moves for the Pokemon.
     */
    public ArrayList<BaseMove> getMoves(PokeEnum id) {
        ArrayList<BaseMove> res = library.get(id);
        return res == null ? new ArrayList<>() : res;
    }

    public List<BaseMove> getMovesAtLevel(PokeEnum id, int level) {
        ArrayList<BaseMove> moves = getMoves(id);
        ArrayList<BaseMove> result = new ArrayList<>();
        for (BaseMove move : moves) {
            if (move.getRequiredLevel() <= level) {
                result.add(move);
            }
        }
        return result;
    }

    /**
     * Returns the supported moves for a specific Pokemon based on its level.
     * @param pokemon The Pokemon.
     * @return The supported moves for the Pokemon.
     */
    public ArrayList<BaseMove> getSupportedMoves(Pokemon pokemon) {
        ArrayList<BaseMove> moves = getMoves(pokemon.getId());
        ArrayList<BaseMove> result = new ArrayList<>();

        for (BaseMove move : moves) {
            if (move.getRequiredLevel() <= pokemon.getLevel()) {
                result.add(move);
            }
        }
        return result;
    }
}
