package jpokebattle.service;

import java.util.ArrayList;
import java.util.HashMap;

import jpokebattle.enums.PokeEnum;
import jpokebattle.pokemon.*;

/**
 * The PokeService class is responsible for managing the Pokemon data and providing access to it.
 */
public class PokeService {
    static private PokeService singleton = null;

    /**
     * Returns the singleton instance of the PokeService class.
     * @return The singleton instance of the PokeService class.
     */
    public static PokeService getSingleton() {
        synchronized (PokeService.class) {
            if (singleton == null) {
                singleton = new PokeService();
            }
        }
        return singleton;
    }

    private HashMap<PokeEnum, PokemonInfo> library = new HashMap<>();

    private PokeService() {
        register();
    }

    /**
     * Registers the initial Pokemon data in the library.
     */
    private void register() {
        registerInfo(PokeEnum.BULBASAUR, new Bulbasaur());
        registerInfo(PokeEnum.IVYSAUR, new Ivysaur());
        registerInfo(PokeEnum.VENUSAUR, new Venusaur());
        registerInfo(PokeEnum.CHARMANDER, new Charmander());
        registerInfo(PokeEnum.CHARMELEON, new Charmeleon());
        registerInfo(PokeEnum.CHARIZARD, new Charizard());
        registerInfo(PokeEnum.SQUIRTLE, new Squirtle());
        registerInfo(PokeEnum.WARTORTLE, new Wartortle());
        registerInfo(PokeEnum.BLASTOISE, new Blastoise());
    }

    /**
     * Registers a Pokemon info in the library.
     * @param id The ID of the Pokemon.
     * @param info The PokemonInfo object containing the data of the Pokemon.
     * @return The PokemonInfo object that was registered.
     */
    private PokemonInfo registerInfo(PokeEnum id, PokemonInfo info) {
        library.put(
            id,
            info
        );
        return info;
    }

    /**
     * Returns a list of all available Pokemon.
     * @return A list of all available Pokemon.
     */
    public ArrayList<PokemonInfo> getAvailablePokemons() {
        return new ArrayList<>(library.values());
    }

    /**
     * Returns the PokemonInfo object for the specified Pokemon ID.
     * @param id The ID of the Pokemon.
     * @return The PokemonInfo object for the specified Pokemon ID.
     */
    public PokemonInfo getInfo(PokeEnum id) {
        return library.get(id);
    }

    /**
     * Returns the PokemonInfo object for the specified Pokemon ID (static method).
     * @param id The ID of the Pokemon.
     * @return The PokemonInfo object for the specified Pokemon ID.
     */
    static public PokemonInfo getInfoStatic(PokeEnum id) {
        return getSingleton().getInfo(id);
    }

    /**
     * Returns a list of starter Pokemon.
     * @return A list of starter Pokemon.
     */
    public ArrayList<PokemonInfo> getStarters() {
        return new ArrayList<PokemonInfo>() {{
            add(getInfo(PokeEnum.BULBASAUR));
            add(getInfo(PokeEnum.CHARMANDER));
            add(getInfo(PokeEnum.SQUIRTLE));
        }};
    }
}
