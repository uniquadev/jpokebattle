package jpokebattle.interfaces;

import jpokebattle.enums.LevelUpGroup;
import jpokebattle.enums.PokeElement;
import jpokebattle.enums.PokeEnum;

/**
 * PokemonInterface is an interface that defines the basic characteristics and behaviors of a Pokemon.
 */
public interface PokemonInterface {

    /**
     * Returns the name of the Pokemon.
     *
     * @return the name of the Pokemon
     */
    public String getName();

    /**
     * Returns the ID of the Pokemon.
     *
     * @return the ID of the Pokemon
     */
    public PokeEnum getId();

    /**
     * Returns the element type of the Pokemon.
     *
     * @return the element type of the Pokemon
     */
    public PokeElement getElement();

    /**
     * Returns the level up group of the Pokemon.
     *
     * @return the level up group of the Pokemon
     */
    public LevelUpGroup getLevelType();

    /**
     * Returns the current evolution stage of the Pokemon.
     *
     * @return the current evolution stage of the Pokemon
     */
    public int getCurrentEvolution();

    /**
     * Returns the maximum evolution stage of the Pokemon.
     *
     * @return the maximum evolution stage of the Pokemon
     */
    public int getMaxEvolution();

    /**
     * Returns the experience points the Pokemon gives when defeated.
     *
     * @return the experience points the Pokemon gives when defeated
     */
    public int getDropExp();

    /**
     * Checks if the Pokemon can evolve further.
     *
     * @return true if the Pokemon can evolve, false otherwise
     */
    public boolean canEvolve();
}