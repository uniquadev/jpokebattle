package jpokebattle.service;

import java.util.ArrayList;
import java.util.HashMap;

import jpokebattle.enums.PokeEnum;
import jpokebattle.pokemon.*;

public class PokeService {
    static private PokeService singleton = null;
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
        registerDatas(); 
    }

    private void registerDatas() {
        registerData(PokeEnum.BULBASAUR, new Bulbasaur());
        registerData(PokeEnum.IVYSAUR, new Ivysaur());
        registerData(PokeEnum.VENUSAUR, new Venusaur());
        registerData(PokeEnum.CHARMANDER, new Charmander());
        registerData(PokeEnum.CHARMELEON, new Charmeleon());
        registerData(PokeEnum.CHARIZARD, new Charizard());
        registerData(PokeEnum.SQUIRTLE, new Squirtle());
        registerData(PokeEnum.WARTORTLE, new Wartortle());
        registerData(PokeEnum.BLASTOISE, new Blastoise());
    }

    private PokemonInfo registerData(PokeEnum id, PokemonInfo info) {
        library.put(
            id,
            info
        );
        return info;
    }
    
    public ArrayList<PokemonInfo> getAvailablePokemons() {
        return new ArrayList<>(library.values());
    } 
    public PokemonInfo getData(PokeEnum id) {
        return library.get(id);
    }
    static public PokemonInfo getDataStatic(PokeEnum id) {
        return getSingleton().getData(id);
    }

    public ArrayList<PokemonInfo> getStarters() {
        return new ArrayList<PokemonInfo>() {{
            add(getData(PokeEnum.BULBASAUR));
            add(getData(PokeEnum.CHARMANDER));
            add(getData(PokeEnum.SQUIRTLE));
        }};
    }
}
