package jpokebattle.utils;

import java.util.ArrayList;
import java.util.Random;

import jpokebattle.pokemon.PokemonInfo;
import jpokebattle.service.PokeService;

public class PokeRandomizer {
    private PokeService service = PokeService.getSingleton();
    private Random random = new Random();
    private int minEvolution = 0;
    private int maxEvolution = 5;

    public void setMaxEvolution(int maxEvolution) {
        this.maxEvolution = maxEvolution;
    }
    public void setMinEvolution(int minEvolution) {
        this.minEvolution = minEvolution;
    }

    private ArrayList<PokemonInfo> getAvailablePokemons() {
        ArrayList<PokemonInfo> result = new ArrayList<>();
        for (PokemonInfo info : service.getAvailablePokemons()) {
            if (info.getCurrentEvolution() <= maxEvolution && info.getCurrentEvolution() >= minEvolution) {
                result.add(info);
            }
        }
        return result;
    }


    public PokemonInfo[] getRandomData(int size)
    {
        PokemonInfo[] result = new PokemonInfo[size];
        ArrayList<PokemonInfo> keys = getAvailablePokemons();
        for (int i = 0; i < size; i++) {
            PokemonInfo data = keys.get(random.nextInt(keys.size()));
            result[i] = data;
        }
        return result;
    }

    public ArrayList<PokemonInfo> getRandomUniqueData(int size) {
        ArrayList<PokemonInfo> result = new ArrayList<>();
        ArrayList<PokemonInfo> keys = getAvailablePokemons();
        
        while (!keys.isEmpty() && result.size() < size) {
            PokemonInfo data = keys.get(random.nextInt(keys.size()));
            result.add(data);
            keys.remove(data);
        }
        return result;
    }

    public ArrayList<PokemonInfo> getRandomStarters(int size) {
        ArrayList<PokemonInfo> result = new ArrayList<>(size);
        ArrayList<PokemonInfo> keys = service.getStarters();
        for (int i = 0; i < size; i++) {
            PokemonInfo data = keys.get(random.nextInt(keys.size()));
            result.add(data);
        }
        return result;
    }
}
