package jpokebattle.utils;

import java.util.ArrayList;
import java.util.Random;

import jpokebattle.model.PokeData;
import jpokebattle.service.PokeService;

public class PokeRandomizer {
    private PokeService service = PokeService.getSingleton();
    private Random random = new Random();
    private int maxEvolution = 5;

    public void setMaxEvolution(int maxEvolution) {
        this.maxEvolution = maxEvolution;
    }

    private ArrayList<PokeData> getAvailablePokemons() {
        ArrayList<PokeData> result = new ArrayList<>();
        for (PokeData data : service.getAvailablePokemons()) {
            if (data.getCurrentEvolution() <= maxEvolution) {
                result.add(data);
            }
        }
        return result;
    }


    public PokeData[] getRandomData(int size)
    {
        PokeData[] result = new PokeData[size];
        ArrayList<PokeData> keys = getAvailablePokemons();
        for (int i = 0; i < size; i++) {
            PokeData data = keys.get(random.nextInt(keys.size()));
            result[i] = data;
        }
        return result;
    }

    public ArrayList<PokeData> getRandomUniqueData(int size) {
        ArrayList<PokeData> result = new ArrayList<>();
        ArrayList<PokeData> keys = getAvailablePokemons();
        
        while (!keys.isEmpty() && result.size() < size) {
            PokeData data = keys.get(random.nextInt(keys.size()));
            result.add(data);
            keys.remove(data);
        }
        return result;
    }

    public ArrayList<PokeData> getRandomStarters(int size) {
        ArrayList<PokeData> result = new ArrayList<>(size);
        ArrayList<PokeData> keys = service.getStarters();
        for (int i = 0; i < size; i++) {
            PokeData data = keys.get(random.nextInt(keys.size()));
            result.add(data);
        }
        return result;
    }
}
