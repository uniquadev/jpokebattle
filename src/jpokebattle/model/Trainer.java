package jpokebattle.model;

import java.util.ArrayList;

public class Trainer {
    private Pokemon[] pokemons = new Pokemon[6];
    private BaseItem[] items = new BaseItem[10];
    private String name = "Trainer";

    private int currentPokemon = 0;

    public int getTeamSize() {
        int count = 0;
        for (int i = 0; i < 6; i++) {
            if (pokemons[i] != null)
                count++;
        }
        return count;
    }

    public Pokemon[] getPokemons() {
        return pokemons;
    }
    public Pokemon getPokemon(int index) {
        assert index >= 0 && index < 6;
        return pokemons[index];
    }
    public void setPokemon(int index, Pokemon pokemon) {
        assert index >= 0 && index < 6;
        pokemons[index] = pokemon;
    }

    public Pokemon getCurrentPokemon() {
        return pokemons[currentPokemon];
    }
    public void setCurrentPokemon(int index) {
        assert index >= 0 && index < 6;
        currentPokemon = index;
    }
    public boolean setCurrentPokemon(Pokemon pokemon) {
        for (int i = 0; i < 6; i++) {
            if (pokemons[i] == pokemon) {
                currentPokemon = i;
                return true;
            }
        }
        return false;
    }

    public ArrayList<Pokemon> getAvailablePokemons() {
        ArrayList<Pokemon> available = new ArrayList<Pokemon>();
        for (int i = 0; i < 6; i++) {
            if (pokemons[i] == null || pokemons[i].isDead())
                continue;
            available.add(pokemons[i]);
        }
        return available;
    }

    public Pokemon getFirstAvailablePokemon() {
        for (int i = 0; i < 6; i++) {
            if (pokemons[i] == null || pokemons[i].isDead())
                continue;
            return pokemons[i];
        }
        return null;
    }

    public int getTeamLevel() {
        int count = 0;
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            if (pokemons[i] == null)
                continue;
            count++;
            sum += pokemons[i].getLevel();
        }
        return count == 0 ? 0 : sum / count;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public int getBestEvolution() {
        int max = 0;
        for (int i = 0; i < 6; i++) {
            if (pokemons[i] == null)
                continue;
            max = Math.max(max, pokemons[i].getInfo().getCurrentEvolution());
        }
        return max;
    }
}
