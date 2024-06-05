package jpokebattle.service;

import java.util.ArrayList;
import java.util.HashMap;

import jpokebattle.model.PokeData;
import jpokebattle.model.PokeData.LevelGroup;
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

    private HashMap<PokeEnum, PokeData> library = new HashMap<>();
    
    private PokeService() {
        registerDatas(); 
    }

    private void registerDatas() {
        registerData(
            PokeEnum.BULBASAUR, "Bulbasaur", PokeElement.GRASS,
            45, 49, 49, 65, 45,
            LevelGroup.MEDIUM_SLOW, 64,
            0, 2,
            false, false
        );
        registerData(
            PokeEnum.IVYSAUR, "Ivysaur", PokeElement.GRASS,
            60, 62, 63, 80, 60,
            LevelGroup.MEDIUM_SLOW, 142,
            1, 2,
            false, false
        );
        registerData(
            PokeEnum.VENUSAUR, "Venusaur", PokeElement.GRASS,
            80, 82, 83, 100, 80,
            LevelGroup.MEDIUM_SLOW, 263,
            2, 2,
            false, false
        );
        registerData(
            PokeEnum.CHARMANDER, "Charmander", PokeElement.FIRE,
            39, 52, 43, 60, 65,
            LevelGroup.MEDIUM_SLOW, 62,
            0, 2,
            false, false
        );
        registerData(
            PokeEnum.CHARMELEON, "Charmelion", PokeElement.FIRE,
            58, 64, 58, 80, 80,
            LevelGroup.MEDIUM_SLOW, 162,
            1, 2,
            false, false
        );
        registerData(
            PokeEnum.CHARIZARD, "Charizard", PokeElement.FIRE,
            78, 84, 78, 109, 100,
            LevelGroup.MEDIUM_SLOW, 267,
            2, 2,
            false, false
        );
        registerData(
            PokeEnum.SQUIRTLE, "Squirtle", PokeElement.WATER,
            44, 48, 65, 50, 43,
            LevelGroup.MEDIUM_SLOW, 63,
            0, 2,       
            false, false
        );
        registerData(
            PokeEnum.WARTORTLE, "Wartortle", PokeElement.WATER,
            59, 63, 80, 65, 58,
            LevelGroup.MEDIUM_SLOW, 142,
            1, 2,
            false, false
        );
        registerData(
            PokeEnum.BLASTOISE, "Blastoise", PokeElement.WATER,
            79, 83, 100, 85, 78,
            LevelGroup.MEDIUM_SLOW, 265,
            2, 2,
            false, false
        );
    }

    private PokeData registerData(
        PokeEnum id, String name, PokeElement element, 
        int ps, int attack, int defense, int special, int speed,
        LevelGroup levelType, int exp,
        int currentEvolution, int maxEvolution,
        boolean isFemale, boolean isLegendary) {
        PokeData data = new PokeData(
            id, name, element,
            ps, attack, defense, special, speed,
            levelType, exp,
            currentEvolution, maxEvolution,
            isFemale, isLegendary
        );
        library.put(
            id,
            data
        );
        return data;
    }
    
    public ArrayList<PokeData> getAvailablePokemons() {
        return new ArrayList<>(library.values());
    } 
    public PokeData getData(PokeEnum id) {
        return library.get(id);
    }
    static public PokeData getDataStatic(PokeEnum id) {
        return getSingleton().getData(id);
    }

    public ArrayList<PokeData> getStarters() {
        return new ArrayList<PokeData>() {{
            add(getData(PokeEnum.BULBASAUR));
            add(getData(PokeEnum.CHARMANDER));
            add(getData(PokeEnum.SQUIRTLE));
        }};
    }
}
