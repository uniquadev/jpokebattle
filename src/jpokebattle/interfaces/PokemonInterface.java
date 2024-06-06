package jpokebattle.interfaces;

import jpokebattle.enums.LevelUpGroup;
import jpokebattle.enums.PokeElement;
import jpokebattle.enums.PokeEnum;

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
