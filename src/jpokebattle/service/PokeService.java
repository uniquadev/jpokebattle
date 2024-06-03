package jpokebattle.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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
            PokeEnum.BULBASAUR, "Bulbasaur", PokeElement.GRASS,
            45, 49, 49, 65, 45,
            false, false
        );
        registerData(
            PokeEnum.CHARMANDER, "Charmander", PokeElement.FIRE,
            39, 52, 43, 60, 65,
            false, false
        );
        registerData(
            PokeEnum.SQUIRTLE, "Squirtle", PokeElement.WATER,
            44, 48, 65, 50, 43,
            false, false
        );
    }

    private PokeData registerData(
        PokeEnum id, String name, PokeElement element, 
        int ps, int attack, int defense, int special, int speed,
        boolean isFemale, boolean isLegendary) {
        PokeData data = new PokeData(
            id, name, element,
            ps, attack, defense, special, speed,
            isFemale, isLegendary
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

    public PokeData[] getRandomData(int size)
    {
        PokeData[] result = new PokeData[size];
        Random random = new Random();
        PokeEnum[] keys = (PokeEnum[])library.keySet().toArray();
        for (int i = 0; i < size; i++) {
            PokeEnum id = keys[random.nextInt(keys.length)];
            PokeData data = getData(id);
            result[i] = data;
        }
        return result;
    }

    public ArrayList<PokeData> getRandomUniqueData(int size) {
        ArrayList<PokeData> result = new ArrayList<>();
        Random random = new Random();
        ArrayList<PokeEnum> keys = new ArrayList<>(library.keySet());
        for (int i = 0; i < size && keys.size() > 0; i++) {
            PokeEnum id = keys.get(random.nextInt(keys.size()));
            PokeData data = getData(id);
            result.add(data);
            keys.remove(id);
        }
        return result;
    }
}
