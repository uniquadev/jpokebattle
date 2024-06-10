package jpokebattle.model;

import java.util.ArrayList;

/**
 * The Trainer class represents a trainer in a Pokémon battle.
 * A trainer can have a team of up to 6 Pokémon.
 */
public class Trainer {
    private Pokemon[] pokemons = new Pokemon[6];
    private String name = "Trainer";

    private int currentPokemon = 0;

    /**
     * Returns the number of Pokémon in the trainer's team.
     *
     * @return The team size.
     */
    public int getTeamSize() {
        int count = 0;
        for (int i = 0; i < 6; i++) {
            if (pokemons[i] != null)
                count++;
        }
        return count;
    }

    /**
     * Returns an array of all the Pokémon in the trainer's team.
     *
     * @return An array of Pokémon.
     */
    public Pokemon[] getPokemons() {
        return pokemons;
    }

    /**
     * Returns the Pokémon at the specified index in the trainer's team.
     *
     * @param index The index of the Pokémon.
     * @return The Pokémon at the specified index.
     * @throws AssertionError if the index is out of bounds.
     */
    public Pokemon getPokemon(int index) {
        assert index >= 0 && index < 6;
        return pokemons[index];
    }

    /**
     * Sets the Pokémon at the specified index in the trainer's team.
     *
     * @param index   The index of the Pokémon.
     * @param pokemon The Pokémon to set.
     * @throws AssertionError if the index is out of bounds.
     */
    public void setPokemon(int index, Pokemon pokemon) {
        assert index >= 0 && index < 6;
        pokemons[index] = pokemon;
    }

    /**
     * Adds a Pokémon to the trainer's team.
     *
     * @param pokemon The Pokémon to add.
     */
    public void addPokemon(Pokemon pokemon) {
        for (int i = 0; i < 6; i++) {
            if (pokemons[i] == null) {
                pokemons[i] = pokemon;
                return;
            }
        }
    }

    /**
     * Returns the active Pokémon in the trainer's team.
     *
     * @return The active Pokémon.
     */
    public Pokemon getActivePokemon() {
        return pokemons[currentPokemon];
    }

    /**
     * Sets the active Pokémon in the trainer's team.
     *
     * @param index The index of the Pokémon to set as active.
     * @throws AssertionError if the index is out of bounds.
     */
    public void setActivePokemon(int index) {
        assert index >= 0 && index < 6;
        currentPokemon = index;
    }

    /**
     * Sets the active Pokémon in the trainer's team.
     *
     * @param pokemon The Pokémon to set as active.
     * @return true if the Pokémon was set as active, false otherwise.
     */
    public boolean setActivePokemon(Pokemon pokemon) {
        if (pokemon == null) return false;
        for (int i = 0; i < 6; i++) {
            if (pokemons[i] == pokemon) {
                currentPokemon = i;
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a list of available Pokémon in the trainer's team.
     * An available Pokémon is one that is not null and not dead.
     *
     * @return A list of available Pokémon.
     */
    public ArrayList<Pokemon> getAvailablePokemons() {
        ArrayList<Pokemon> available = new ArrayList<Pokemon>();
        for (int i = 0; i < 6; i++) {
            if (pokemons[i] == null || pokemons[i].isDead())
                continue;
            available.add(pokemons[i]);
        }
        return available;
    }

    /**
     * Returns a list of all Pokémon in the trainer's team.
     *
     * @return A list of all Pokémon.
     */
    public ArrayList<Pokemon> getAllPokemons() {
        ArrayList<Pokemon> all = new ArrayList<Pokemon>();
        for (int i = 0; i < 6; i++) {
            if (pokemons[i] == null)
                continue;
            all.add(pokemons[i]);
        }
        return all;
    }

    /**
     * Returns the first available Pokémon in the trainer's team.
     * An available Pokémon is one that is not null and not dead.
     *
     * @return The first available Pokémon, or null if none are available.
     */
    public Pokemon getFirstAvailablePokemon() {
        for (int i = 0; i < 6; i++) {
            if (pokemons[i] == null || pokemons[i].isDead())
                continue;
            return pokemons[i];
        }
        return null;
    }

    /**
     * Returns the average level of the Pokémon in the trainer's team.
     *
     * @return The average level of the team.
     */
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

    /**
     * Sets the name of the trainer.
     *
     * @param name The name of the trainer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the trainer.
     *
     * @return The name of the trainer.
     */
    public String getName() {
        return name;
    }
}
