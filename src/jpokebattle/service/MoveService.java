package jpokebattle.service;

import java.util.ArrayList;
import java.util.HashMap;

import jpokebattle.utils.PokeEnum;
import jpokebattle.model.Pokemon;
import jpokebattle.move.*;

public class MoveService {
    private static MoveService singleton = null;
    public static MoveService getSingleton() {
        if (singleton == null) {
            singleton = new MoveService();
        }
        return singleton;
    }

    private HashMap<PokeEnum, ArrayList<BaseMove>> library = new HashMap<>();

    private MoveService() {
        registerMoves();
    }

    private void registerMoves() {
        registerMove(PokeEnum.BULBASAUR, new Growl(1));
        registerMove(PokeEnum.BULBASAUR, new Tackle(1));
        registerMove(PokeEnum.CHARMANDER, new Growl(1));
        registerMove(PokeEnum.CHARMANDER, new Scratch(1));
        registerMove(PokeEnum.SQUIRTLE, new TailWhip(1));
        registerMove(PokeEnum.SQUIRTLE, new Tackle(1));

    }

    private void registerMove(PokeEnum id, BaseMove move) {
        if (!library.containsKey(id)) {
            library.put(id, new ArrayList<>());
        }
        library.get(id).add(move);
    }

    public ArrayList<BaseMove> getMoves(PokeEnum id) {
        ArrayList<BaseMove> res = library.get(id);
        return res == null ? new ArrayList<>() : res;
    }

    public ArrayList<BaseMove> getSupportedMoves(Pokemon pokemon) {
        ArrayList<BaseMove> moves = getMoves(pokemon.getData().getId());
        ArrayList<BaseMove> result = new ArrayList<>();

        for (BaseMove move : moves) {
            if (move.getRequiredLevel() <= pokemon.getLevel()) {
                result.add(move);
            }
        }
        return result;
    }
}
