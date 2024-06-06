package jpokebattle.interfaces;

import jpokebattle.utils.LevelUpGroup;
import jpokebattle.utils.PokeElement;
import jpokebattle.utils.PokeEnum;

public interface PokemonInterface {
    public String getName();
    public PokeEnum getId();
    public PokeElement getElement();
    public LevelUpGroup getLevelType();
    public int getCurrentEvolution();
    public int getMaxEvolution();
    public int getDropExp();
    public boolean canEvolve();
}
