package jpokebattle.service;

import java.util.HashMap;

import jpokebattle.model.PokeData;
import jpokebattle.utils.PokeElement;
import jpokebattle.utils.PokeEnum;

public class PokeService {
    static private PokeService singleton = null;
    public static PokeService getSingleton() {
        if (singleton == null) {
            singleton = new PokeService();
        }
        return singleton;
    }

    private static HashMap<PokeEnum, PokeData> library = new HashMap<>();

    private PokeService() {
        registerData(
            PokeEnum.BULBASAUR,
            "Bulbasaur",
            PokeElement.GRASS,
            false,
            false
        );
    }

    private PokeData registerData(PokeEnum id, String name, PokeElement element, boolean isFemale, boolean isLegendary) {
        PokeData data = new PokeData(
            id, name, element, isFemale, isLegendary
        );
        library.put(
            id,
            data
        );
        return data;
    }

    public PokeData getData(PokeEnum id) {
        return library.get(id);
    }
    static public PokeData getDataStatic(PokeEnum id) {
        return getSingleton().getData(id);
    }
}
