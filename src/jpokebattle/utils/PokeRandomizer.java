package jpokebattle.utils;

import java.util.ArrayList;
import java.util.Random;

import jpokebattle.pokemon.PokemonInfo;
import jpokebattle.service.PokeService;

/**
 * The PokeRandomizer class is responsible for generating random Pokemon info based on certain criteria.
 * This class is useful for generating random Pokemon for battles or for other purposes.
 */
public class PokeRandomizer {
    private PokeService service = PokeService.getSingleton();
    private Random random = new Random();
    private int minEvolution = 0;
    private int maxEvolution = 5;

    /**
     * Sets the maximum evolution level for the generated Pokemon.
     * @param maxEvolution The maximum evolution level.
     */
    public void setMaxEvolution(int maxEvolution) {
        this.maxEvolution = maxEvolution;
    }

    /**
     * Sets the minimum evolution level for the generated Pokemon.
     * @param minEvolution The minimum evolution level.
     */
    public void setMinEvolution(int minEvolution) {
        this.minEvolution = minEvolution;
    }

    /**
     * Retrieves the available Pokemon that meet the evolution level criteria.
     * @return An ArrayList of PokemonInfo objects.
     */
    private ArrayList<PokemonInfo> getAvailablePokemons() {
        ArrayList<PokemonInfo> result = new ArrayList<>();
        for (PokemonInfo info : service.getAvailablePokemons()) {
            if (info.getCurrentEvolution() <= maxEvolution && info.getCurrentEvolution() >= minEvolution) {
                result.add(info);
            }
        }
        return result;
    }

    /**
     * Generates an array of random Pokemon data.
     * @param size The number of Pokemon data to generate.
     * @return An array of PokemonInfo objects.
     */
    public PokemonInfo[] getRandomData(int size) {
        PokemonInfo[] result = new PokemonInfo[size];
        ArrayList<PokemonInfo> keys = getAvailablePokemons();
        for (int i = 0; i < size; i++) {
            PokemonInfo data = keys.get(random.nextInt(keys.size()));
            result[i] = data;
        }
        return result;
    }

    /**
     * Generates a list of random unique Pokemon data.
     * @param size The number of unique Pokemon data to generate.
     * @return An ArrayList of PokemonInfo objects.
     */
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

    /**
     * Generates a list of random starter Pokemon data.
     * @param size The number of starter Pokemon data to generate.
     * @return An ArrayList of PokemonInfo objects.
     */
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
